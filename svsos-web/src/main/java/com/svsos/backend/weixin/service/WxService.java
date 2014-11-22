package com.svsos.backend.weixin.service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.svsos.backend.model.MsgResponse;
import com.svsos.backend.model.WxUser;
import com.svsos.backend.repositories.jpa.MsgResponseDao;
import com.svsos.backend.repositories.jpa.WxUserDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.resp.Article;
import com.svsos.backend.weixin.resp.NewsMessage;
import com.svsos.backend.weixin.resp.TextMessage;
import com.svsos.backend.weixin.util.DESTools;
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

	@Resource
	private WxUserDao wxUserDao;

	@Resource
	private CommonService commonService;

	@Resource
	private RespTest rt;
	
	@Resource
	private MsgResponseDao responseDao;
	
	public void saveWxUser(WxUser user) {
		wxUserDao.save(user);
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "随售欢迎您！";

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
			textMessage.setFuncFlag(0);
			// 文本消息		    
		    if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				//respContent = "您发送的是文本消息！";
		    	WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
				Timestamp createTime = commonService.getCurrentTime();
				if(user != null)
				{
					user.setCreateTime(createTime);
					user.setExpireTime(createTime.getTime() + 24 * 3600);
					wxUserDao.save(user);
				}
				
			    String content = requestMap.get("Content");
			    String title = "";
				String desc = "";
				String picUrl = "";
				String url = "";
				MsgResponse msgResponse = responseDao.findMsgResponseByKeyword(content); 
				if(msgResponse != null){
					
					Map<String,Object> map =JsonMapper.nonDefaultMapper().fromJson(msgResponse.getContent(), Map.class);   
					Object type = map.get("msgType");
					if ("news".equals(type)){
						List<Map<String,String>> items = (List<Map<String,String>>)map.get("items");
				        for(Map<String, String> item : items){
				        	title = item.get("title") == null ? "" : item.get("title").toString();
				        	desc = item.get("desc") == null ? "" : item.get("desc").toString();
				        	picUrl = item.get("picUrl") == null ? "" : item.get("picUrl").toString();
				        	url = item.get("url") == null ? "" : item.get("url").toString();
				        }
					    // 创建图文消息  
			            NewsMessage newsMessage = new NewsMessage();  
			            newsMessage.setToUserName(fromUserName);  
			            newsMessage.setFromUserName(toUserName);  
			            newsMessage.setCreateTime(new Date().getTime());  
			            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
			            newsMessage.setFuncFlag(0);  
						
						List<Article> articleList = new ArrayList<Article>();  				
						 Article article = new Article();  
		                 article.setTitle(title);  
		                 article.setDescription(desc);  
		                 article.setPicUrl(picUrl);  
		                 article.setUrl(url);  
		                 articleList.add(article);  
		                 // 设置图文消息个数  
		                 newsMessage.setArticleCount(articleList.size());  
		                 // 设置图文消息包含的图文集合  
		                 newsMessage.setArticles(articleList);  
		                 // 将图文消息对象转换成xml字符串  
		                 respMessage = MessageUtil.newsMessageToXml(newsMessage); 						
					}
					else if("text".equals(type)){
						String titles = (String) map.get("title");
						String Url = (String) map.get("url");
						if(Url != null){
							textMessage.setContent("<a href=\""+ Url +"\">"+titles+"</a>");
						}
						else{
							textMessage.setContent(titles);
						}
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}										 					 
				}
				else{
					//respContent = "您发送的是文本消息！";
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
			 }		
			
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				//respContent = "您发送的是图片消息！";
				WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
				Timestamp createTime = commonService.getCurrentTime();
				if(user != null)
				{
					user.setCreateTime(createTime);
					user.setExpireTime(createTime.getTime() + 24 * 3600);
					wxUserDao.save(user);
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				//respContent = "您发送的是地理位置消息！";
				WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
				Timestamp createTime = commonService.getCurrentTime();
				if(user != null)
				{
					user.setCreateTime(createTime);
					user.setExpireTime(createTime.getTime() + 24 * 3600);
					wxUserDao.save(user);
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				//respContent = "您发送的是链接消息！";
				WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
				Timestamp createTime = commonService.getCurrentTime();
				if(user != null)
				{
					user.setCreateTime(createTime);
					user.setExpireTime(createTime.getTime() + 24 * 3600);
					wxUserDao.save(user);
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				//respContent = "您发送的是音频消息！";
				WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
				Timestamp createTime = commonService.getCurrentTime();
				if(user != null)
				{
					user.setCreateTime(createTime);
					user.setExpireTime(createTime.getTime() + 24 * 3600);
					wxUserDao.save(user);
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					Timestamp createTime = commonService.getCurrentTime();
					WxUser user = wxUserDao.findWxUserByWxId(fromUserName);					
					if(user != null)						
					{
						user.setFollowStatus(1);
						user.setCreateTime(createTime);
						user.setExpireTime(createTime.getTime() + 24 * 3600);
					}	
					else
					{
						user = new WxUser();
						user.setWxId(fromUserName);
						user.setCreateTime(createTime);
						user.setExpireTime(createTime.getTime() + 24 * 3600);
					}	
					wxUserDao.save(user);
					DESTools des = new DESTools();
					String openid = URLEncoder.encode(des.getEncString(fromUserName), "utf-8");
					String url = "http://weixin.svsos.com/svsos-web/wx/login?idKey="+openid;
					respContent = "谢谢您的关注，请先点击<a href=\""+ url +"\">绑定</a>";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					
					//更新微信用户表的时候，将状态改为“取消”
					WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
					if(user != null)
					{
						user.setFollowStatus(0);
						wxUserDao.save(user);
					}										
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("21")) {
						respContent = "21菜单项被点击！";
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("22")) {
						respContent = "22菜单项被点击！";
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("23")) {
						respContent = "23菜单项被点击！";
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("24")) {
						respContent = "24菜单项被点击！";
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("25")) {
						respContent = "25菜单项被点击！";
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					}
				}
				//点击菜单跳转到新页面时间
				else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
					// 事件VIEW值，与创建自定义菜单时指定的URL值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("http://www.baidu.com")) {
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}	
						
					} else if (eventKey.equals("12")) {
						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("13")) {

						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("14")) {

						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("31")) {

						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("32")) {

						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					} else if (eventKey.equals("33")) {

						WxUser user = wxUserDao.findWxUserByWxId(fromUserName);
						Timestamp createTime = commonService.getCurrentTime();
						if(user != null)
						{
							user.setCreateTime(createTime);
							user.setExpireTime(createTime.getTime() + 24 * 3600);
							wxUserDao.save(user);
						}
					}
					
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}
