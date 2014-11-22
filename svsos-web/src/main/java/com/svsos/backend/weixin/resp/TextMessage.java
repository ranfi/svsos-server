package com.svsos.backend.weixin.resp;

/** 
 * 文本消息 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */  
public class TextMessage extends BaseMessage {  
    // 回复的消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}  
