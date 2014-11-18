package com.svsos.backend.weixin.resp;
import java.util.List;
/** 
 * 客服消息 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */
public class Kfnews {
  
	// 图文消息描述  
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
