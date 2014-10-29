package com.svsos.backend.weixin.req;

/** 
 * 图片消息 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */  
public class ImageMessage extends BaseMessage {  
    // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}  
