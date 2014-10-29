package com.svsos.backend.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.svsos.backend.weixin.resp.TextMessage;
import com.svsos.backend.weixin.util.MessageUtil;

/** 
 * 核心服务类 
 *  
 * @author zhouliangjun 
 * @date 2014-10-22 
 */  
@Service
public class WxService {  
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
            String respContent = "请求处理异常，请稍候尝试！";  
  
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
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                respContent = "您发送的是文本消息！";  
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                	// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("11")) {  
                        respContent = "11菜单项被点击！";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "12菜单项被点击！";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "13菜单项被点击！";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "14菜单项被点击！";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "21菜单项被点击！";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "22菜单项被点击！";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "23菜单项被点击！";  
                    } else if (eventKey.equals("24")) {  
                        respContent = "24菜单项被点击！";  
                    } else if (eventKey.equals("25")) {  
                        respContent = "25菜单项被点击！";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "31菜单项被点击！";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "32菜单项被点击！";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "33菜单项被点击！";  
                    }  
                }  
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
} 
