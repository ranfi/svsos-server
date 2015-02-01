package com.svsos.backend.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.svsos.backend.weixin.WxConstant;

public class WxLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		System.out.println("url:" + request.getRequestURI());
		Cookie[] cookies = request.getCookies();
		String[] cooks = null;
		String username = null;
		String contextPath = request.getContextPath();
		String loginUrl = URLEncoder.encode(WxConstant.WX_API_URL + contextPath + "/wx/login", "utf-8");
		String oauthUrl = String.format(WxConstant.WX_OAUTH2_URL, WxConstant.WX_OPENID, loginUrl);
		// String password = null;
		if (cookies != null) {
			for (Cookie coo : cookies) {
				String aa = coo.getValue();
				cooks = aa.split("==");
				if (cooks.length == 2) {
					username = cooks[0];
					// password = cooks[1];
					break;
				}
			}
			System.out.println("url:" + request.getRequestURI());
			if (StringUtils.isNotBlank(username)) {
				arg2.doFilter(request, response);
			} else if (request.getRequestURI().indexOf("/wx/login") != -1) {
				arg2.doFilter(request, response);
			} else {
				response.sendRedirect(oauthUrl);
			}
		} else {
			if (request.getRequestURI().indexOf("/wx/login") != -1) {
				arg2.doFilter(request, response);
			} else {
				response.sendRedirect(oauthUrl);
			}

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String loginUrl = URLEncoder.encode(WxConstant.WX_API_URL + "/svsos-web/wx/login", "utf-8");
		String oauthUrl = String.format(WxConstant.WX_OAUTH2_URL, WxConstant.WX_OPENID, loginUrl);
		System.out.println(oauthUrl);
	}
}
