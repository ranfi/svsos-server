package com.svsos.backend.controller.wx;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svsos.backend.model.WorkUser;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.repositories.jpa.WxUserDao;
import com.svsos.backend.weixin.util.WeixinUtil;

@Controller
@RequestMapping("/wx/user")
public class UserController {
	@Resource
	private WorkUserDao workUserDao;

	@Resource
	private WxUserDao wxUserDao;

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(HttpServletResponse response, HttpServletRequest request, Model model) {
		String username = WeixinUtil.getCookieValue(request);
		WorkUser user = new WorkUser();
		if (username != null) {
			user = workUserDao.findWorkUserByAccount(username);
		}
		model.addAttribute("user", user);
		return "weixin/userProfile";
	}

	@RequestMapping(value = "help", method = RequestMethod.GET)
	public String help(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "";
	}

	@RequestMapping(value = "aboutus", method = RequestMethod.GET)
	public String aboutus(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "";
	}

}
