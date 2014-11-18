package com.svsos.backend.weixin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svsos.backend.model.MsgResponse;
import com.svsos.backend.repositories.jpa.MsgResponseDao;
import com.svsos.backend.weixin.service.RespTest;
import com.svsos.core.utils.JsonMapper;

@Controller
@RequestMapping("/wx/test")
public class Test {
	
	@Autowired
	private RespTest rt;

	@Resource
    MsgResponseDao responseDao;
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		String Keyword = "工单";
       // Map<String, Object> map = rt.jsonToMap(Keyword);  	
		String title = "";
		String desc = "";
		String picUrl = "";
		String url = "";
		MsgResponse msgResponse = responseDao.findMsgResponseByKeyword(Keyword);        
		Map<String,Object> map =JsonMapper.nonDefaultMapper().fromJson(msgResponse.getContent(), Map.class); 
		Object msgType = map.get("msgType");
		if("news".equals(msgType)){
			List<Map<String,String>> items = (List<Map<String,String>>)map.get("items");
	        for(Map<String, String> item : items){
	        	title = item.get("title") == null ? "" : item.get("title").toString();
	        	desc = item.get("desc") == null ? "" : item.get("desc").toString();
	        	picUrl = item.get("picUrl") == null ? "" : item.get("picUrl").toString();
	        	url = item.get("picUrl") == null ? "" : item.get("picUrl").toString();
	        }
	        System.out.println("msgType:"+msgType);
	         System.out.println("title:"+title);
	         System.out.println("desc:"+desc);
	         System.out.println("picUrl:"+picUrl);
	         System.out.println("url:"+url);
		}
		else if("text".equals(msgType)){
			String titles = (String) map.get("title");
			String Url = (String) map.get("url");
			System.out.println("titles:"+"<a href="+Url+">"+titles+"</a>");
	        // System.out.println("Url:"+Url);
		}
		
        PrintWriter out = response.getWriter();  
        //out.write(respMessage);
        out.close();
    	
    }  

}
