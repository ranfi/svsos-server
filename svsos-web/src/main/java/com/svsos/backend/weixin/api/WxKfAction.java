package com.svsos.backend.weixin.api;

import com.svsos.backend.weixin.pojo.AccessToken;
import com.svsos.backend.weixin.resp.Article;
import com.svsos.backend.weixin.resp.BaseNews;
import com.svsos.backend.weixin.resp.Kfnews;
import com.svsos.backend.weixin.util.DESTools;
import com.svsos.backend.weixin.util.JSONUtil;
import com.svsos.backend.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/** 
 * 客服开发 
 *  
 * @author zhouliangjun 
 * @date 2014-10-28 
 */ 
@Controller
@RequestMapping("/gotokf")
public class WxKfAction extends MultiActionController{
	private static Logger log = LoggerFactory.getLogger(WxKfAction.class);

	@RequestMapping(method = RequestMethod.GET)
    public  void doGet(HttpServletRequest request, HttpServletResponse response) {  
    	String openid = request.getParameter("openid");
        // 第三方用户唯一凭证  
        String appId = "wx05df8f67d2213386";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "a7c5877fd3ad5c508d297eb8171ca9ab";  
  
        // 调用接口获取access_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        if (null != at) {  
            // 调用客服接口  
            int result = WeixinUtil.Runkf(getkfnews(openid), at.getToken());  
  
            // 判断调用客服接口结果  
            if (0 == result)  
                log.info("调用客服信息发送成功！");  
            else  
                log.info("调用客服信息发送失败，错误码：" + result);  
           JSONUtil.outResult(response, result);
        }  
    }  
    
    private static BaseNews getkfnews(String openid) {
    	DESTools des = new DESTools();
    	String idKey = "";
    	try {
			 idKey = URLEncoder.encode(des.getEncString(openid), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Article art1=new Article();
		art1.setDescription("1");
		art1.setPicurl("http://img01.js.10086.cn/mall/userfiles/images/goods/hm4g/NLB1.jpg");
		art1.setTitle("测试1");
		art1.setUrl("http://192.168.0.232:8080/svsos-web/wx/login?"+"idKey="+idKey);

		Article art2=new Article();
		art2.setDescription("1");
		art2.setPicurl("http://img01.js.10086.cn/mall/userfiles/images/goods/xx1h/NB1.jpg");
		art2.setTitle("测试1");
		art2.setUrl("http://www.baidu.com");
		List<Article> list = new ArrayList<Article>();
		Kfnews  news = new Kfnews();
		list.add(art1);
		list.add(art2);
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
