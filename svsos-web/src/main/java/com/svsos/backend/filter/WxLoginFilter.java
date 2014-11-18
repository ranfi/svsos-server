package com.svsos.backend.filter;
import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.lang.StringUtils;

import com.svsos.backend.weixin.util.WeixinUtil;

public class WxLoginFilter implements Filter {  

	public void destroy() {  
	    // TODO Auto-generated method stub  
	  
	    }  

    public void doFilter(ServletRequest arg0, ServletResponse arg1,  
            FilterChain arg2) throws IOException, ServletException 
    {  
        HttpServletRequest request = (HttpServletRequest) arg0;  
        HttpServletResponse response = (HttpServletResponse) arg1;
        String code = request.getParameter("code");
        String openid = "";
        if(StringUtils.isNotBlank(code))
        {
        	 openid= WeixinUtil.getOpenid(code);
        }	       
        System.out.println("openid="+openid);
        Cookie[] cookies = request.getCookies();  
        String[] cooks = null;  
        String username = null;  
     // String password = null; 
        if (cookies != null)
        {  
            for (Cookie coo : cookies) 
            {  
                String aa = coo.getValue();  
                cooks = aa.split("==");  
	            if (cooks.length == 2) 
	            {  
	                username = cooks[0];  
	              // password = cooks[1];  
	            }  
            } 
            if(username != null)
            {		       
		       arg2.doFilter(request,response );		         
		    }
            else if(request.getRequestURI().indexOf("/wx/login") != -1)
            {
            	arg2.doFilter(request,response );
            }
            else
            {
            	  response.sendRedirect(request.getContextPath() + "/wx/login.do?openid=" + openid);
            }
       }
	    else
	    {  
	            response.sendRedirect(request.getContextPath() + "/wx/login.do?openid=" + openid);
	    }                
   }     
  
    public void init(FilterConfig arg0) throws ServletException 
    {  
        // TODO Auto-generated method stub   
    }    
}  
