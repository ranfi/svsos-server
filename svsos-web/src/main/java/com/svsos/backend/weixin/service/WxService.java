package com.svsos.backend.weixin.service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.svsos.backend.model.MsgResponse;
import com.svsos.backend.model.MsgSubscribe;
import com.svsos.backend.model.WorkerSignin;
import com.svsos.backend.model.WxUser;
import com.svsos.backend.repositories.jpa.MsgResponseDao;
import com.svsos.backend.repositories.jpa.MsgSubscribeDao;
import com.svsos.backend.repositories.jpa.WorkerSigninDao;
import com.svsos.backend.repositories.jpa.WxUserDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.WxConstant;
import com.svsos.backend.weixin.resp.Article;
import com.svsos.backend.weixin.resp.NewsMessage;
import com.svsos.backend.weixin.resp.TextMessage;
import com.svsos.backend.weixin.util.MessageUtil;
import com.svsos.core.utils.JsonMapper;

/**
 * 核心服务类
 * 
 * @author zhouliangjun
 * @date 2014-10-22
 */
@Service
public class WxService {

	private static Logger logger = LoggerFactory.getLogger(WxService.class);

	@Resource
	private WxUserDao wxUserDao;

	@Resource
	private CommonService commonService;

	@Resource
	private RespTest rt;

	@Resource
	private MsgResponseDao responseDao;
	@Resource
	private MsgSubscribeDao msgSubscribeDao;

	@Resource
	private WorkerSigninDao workerSigninDao;

	public void saveWxUser(WxUser user) {
		wxUserDao.save(user);
	}

	public void updateWxUser(String fromUserName) {
		WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
		Timestamp createTime = commonService.getCurrentTime();
		if (user != null) {
			user.setCreateTime(createTime);
			user.setExpireTime(createTime.getTime() + 24 * 3600);
			wxUserDao.save(user);
		}
	}

	/**
	 * 返回消息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getResponseMsg(String fromUserName, String toUserName, String content) {
		Map<String, Object> map = JsonMapper.nonDefaultMapper().fromJson(content, Map.class);
		String type = (String) map.get("msgType");
		if ("news".equals(type)) {
			List<Map<String, String>> items = (List<Map<String, String>>) map.get("items");
			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			String title = "", desc = "", picUrl = "", url = "";
			List<Article> articleList = new ArrayList<Article>();
			for (Map<String, String> item : items) {
				title = item.get("title") == null ? "" : item.get("title").toString();
				desc = item.get("desc") == null ? "" : item.get("desc").toString();
				picUrl = item.get("picUrl") == null ? "" : item.get("picUrl").toString();
				url = item.get("url") == null ? "" : item.get("url").toString();
				Article article = new Article();
				article.setTitle(title);
				article.setDescription(desc);
				article.setPicUrl(picUrl);
				article.setUrl(url);
				articleList.add(article);
			}

			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			return MessageUtil.newsMessageToXml(newsMessage);

		} else if ("text".equals(type)) {
			String titles = (String) map.get("content");
			String Url = (String) map.get("url");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			if (StringUtils.isNotBlank("url")) {
				textMessage.setContent("<a href=\"" + Url + "\">" + titles + "</a>");
			} else {
				textMessage.setContent(titles);
			}
			return MessageUtil.textMessageToXml(textMessage);
		}
		return "";
	}

	public void createWxUser(String fromUserName) {
		Timestamp createTime = commonService.getCurrentTime();
		WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
		if (user != null) {
			user.setFollowStatus(1);
			user.setCreateTime(createTime);
			user.setExpireTime(createTime.getTime() + 24 * 3600);
		} else {
			user = new WxUser();
			user.setWxId(fromUserName);
			user.setCreateTime(createTime);
			user.setExpireTime(createTime.getTime() + 24 * 3600);
		}
		wxUserDao.save(user);
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "欢迎您使用随售服务平台";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			updateWxUser(fromUserName); // 更新微信用户信息
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				String content = requestMap.get("Content");
				MsgResponse msgResponse = responseDao.findMsgResponseByKeyword(content);
				if (msgResponse != null) {
					System.out.println(msgResponse.getContent());
					respMessage = getResponseMsg(fromUserName, toUserName, msgResponse.getContent());
				} else {
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				return respMessage;
			}

			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String loginUrl = URLEncoder.encode(WxConstant.WX_API_URL + request.getContextPath() + "/wx/login",
							"utf-8");
					String oauthUrl = String.format(WxConstant.WX_OAUTH2_URL, WxConstant.WX_OPENID, loginUrl);
					respContent = "谢谢您的关注，请先点击<a href=\"" + oauthUrl + "\">绑定</a>";

					createWxUser(fromUserName); // 创建用户
					MsgSubscribe subscribe = msgSubscribeDao.findMsgSubscribeByStatus(1);
					if (subscribe != null) {
						respMessage = getResponseMsg(fromUserName, toUserName, subscribe.getContent());
						return respMessage;
					}
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 更新微信用户表的时候，将状态改为“取消”
					WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
					if (user != null) {
						user.setFollowStatus(0);
						wxUserDao.save(user);
					}
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					// TODO
				}
				// 点击菜单跳转到新页面时间
				else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
					// 事件VIEW值，与创建自定义菜单时指定的URL值对应
					String eventKey = requestMap.get("EventKey");
					// TODO

					// 上传用户地理位置
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// 发送方帐号（open_id）
					String longitude = requestMap.get("Longitude");
					String latitude = requestMap.get("Latitude");
					updateUserLocation(fromUserName, longitude, latitude);
					return "";
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	public void updateUserLocation(String wxId, String longitude, String latitude) {
		WxUser user = wxUserDao.findWxUserByWxId(wxId);
		java.sql.Date currentDate = commonService.getCurrentDate();
		Timestamp currentTime = commonService.getCurrentTime();
		if (null != user) {
			BigDecimal lng = new BigDecimal(StringUtils.isBlank(longitude) ? "0" : longitude);
			BigDecimal lat = new BigDecimal(StringUtils.isBlank(latitude) ? "0" : latitude);
			user.setLongitude(lng);
			user.setLatitude(lat);
			Integer workId = user.getWorkerId();
			WorkerSignin workerSignin = workerSigninDao.findWorkerSigninByWorkIdAndSignindate(workId, currentDate);
			if (null != workerSignin) {
				workerSignin.setLngBaidu(lng);
				workerSignin.setLatBaidu(lat);
			} else {
				workerSignin = new WorkerSignin();
				workerSignin.setWorkerId(workId);
				workerSignin.setSigninDate(currentDate);
				workerSignin.setSigninTime(currentTime);
				workerSignin.setStatus(1);
				workerSignin.setLatBaidu(lat);
				workerSignin.setLngBaidu(lng);
			}
			workerSigninDao.save(workerSignin);
			wxUserDao.save(user);
		}

	}
}
