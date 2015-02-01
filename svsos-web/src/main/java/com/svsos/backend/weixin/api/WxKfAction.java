package com.svsos.backend.weixin.api;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svsos.backend.weixin.pojo.AccessToken;
import com.svsos.backend.weixin.pojo.CustomerServiceRequest;
import com.svsos.backend.weixin.resp.Article;
import com.svsos.backend.weixin.resp.BaseNews;
import com.svsos.backend.weixin.resp.Kfnews;
import com.svsos.backend.weixin.util.WeixinUtil;

/**
 * 客服开发
 * 
 * @author zhouliangjun
 * @date 2014-10-28
 */
@Controller
@RequestMapping("/gotokf")
public class WxKfAction {
	private static Logger log = LoggerFactory.getLogger(WxKfAction.class);

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Object sendMessage(@RequestBody CustomerServiceRequest request) {
		String openid = request.getOpenid();
		String picUrl = request.getPicUrl();
		String url = request.getUrl();
		String title = request.getTitle();
		// 第三方用户唯一凭证
		String appId = "wx05df8f67d2213386";
		// 第三方用户唯一凭证密钥
		String appSecret = "a7c5877fd3ad5c508d297eb8171ca9ab";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用客服接口
			int result = WeixinUtil.Runkf(getkfnews(openid, title, picUrl, url), at.getToken());

			// 判断调用客服接口结果
			if (0 == result)
				log.info("调用客服信息发送成功！");
			else
				log.info("调用客服信息发送失败，错误码：" + result);
			return result;
		}
		return null;
	}

	private static BaseNews getkfnews(String openid, String title, String picUrl, String url) {
		Article art1 = new Article();
		art1.setDescription(title);
		art1.setPicurl(picUrl);
		art1.setTitle(title);
		art1.setUrl(url);

		List<Article> list = new ArrayList<Article>();
		Kfnews news = new Kfnews();
		list.add(art1);
		news.setArticles(list);

		BaseNews kfbean = new BaseNews();
		kfbean.setMsgtype("news");
		kfbean.setTouser(openid);
		kfbean.setNews(news);
		String jsonkfbean = JSONObject.fromObject(kfbean).toString();
		System.out.println(jsonkfbean);
		return kfbean;
	}
}
