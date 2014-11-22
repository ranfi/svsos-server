package com.svsos.backend.weixin.resp;
/** 
 * 客服消息 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */
public class BaseNews  extends BaseBean{
	// 图文消息 
	private Kfnews news;

	public Kfnews getNews() {
		return news;
	}

	public void setNews(Kfnews news) {
		this.news = news;
	}
	
	
}
