package com.svsos.backend.weixin.resp;
/** 
 * 客服消息基类（开发者 -> 普通用户） 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */  
public class BaseBean {

	//普通用户openid 
	private String touser;
 	//消息类型，text 
	private String msgtype;

	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
