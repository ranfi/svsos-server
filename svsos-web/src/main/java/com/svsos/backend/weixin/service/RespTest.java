package com.svsos.backend.weixin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svsos.backend.model.MsgResponse;
import com.svsos.backend.repositories.jpa.MsgResponseDao;
import net.sf.json.JSONException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class RespTest {

	@Resource
    MsgResponseDao responseDao;
	public Map<String, Object> jsonToMap(String Keyword) throws JSONException {
		 
       // HashMap<String, String> map = new HashMap<String, String>();
       
        MsgResponse ms = new MsgResponse();
        ms = responseDao.findMsgResponseByKeyword(Keyword); 
        String rs = ms.getContent();
        JsonNode jsonNode = null; 
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			jsonNode = objectMapper.readTree(rs);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//将json字符串转成jsonNode 
        return JsonToMap(jsonNode);
    }

    //jsonNode转成map
    public static Map<String, Object> JsonToMap(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        Iterator<String> paramsIterator = jsonNode.fieldNames();//获得一个迭代器 存储jsonNode的所有key
        Map<String, Object> map = new HashMap<String, Object>();
        while (paramsIterator.hasNext()) {
            String paramName = paramsIterator.next();//得到一个key
            JsonNode jsonSonNode = jsonNode.get(paramName);
            if (jsonSonNode.isInt()) {//如果是int类型数据
                map.put(paramName, jsonSonNode.intValue());
            } else if (jsonSonNode.isLong()) {//如果是long类型数据
                map.put(paramName, jsonSonNode.longValue());
            } else if (jsonSonNode.isTextual()) {//如果是文本
                map.put(paramName, jsonSonNode.textValue());
            } else if (jsonSonNode.isObject()) {//如果是对象
                Map<String, Object> tempMap = JsonToMap(jsonSonNode);//递归调用
                if (tempMap != null) {
                    map.put(paramName, tempMap);
                }
            }  else if (jsonSonNode.isArray()) {//如果是数组
                Iterator<JsonNode> nodeIterator = jsonSonNode.elements();//获取数组里的值
                ArrayList<Map<String, Object>> tempMaplist = new ArrayList<Map<String, Object>>();
                while (nodeIterator.hasNext()) {
                    JsonNode tempJsonNode = nodeIterator.next();//得到数组里的一个对象
                    Map<String, Object> tempM = JsonToMap(tempJsonNode);//递归调用
                    if (tempM != null) {
                        tempMaplist.add(tempM);
                    }
                }
                map.put(paramName, tempMaplist);
            }
        } 
        return map; 
    }
    
}
