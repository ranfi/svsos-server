package com.svsos.backend.controller.wx;

import com.svsos.backend.model.WorkUser;
import com.svsos.backend.repositories.jpa.ServiceOrderDao;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.weixin.util.WeixinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/wx/userInfoDetail")
public class UserInfoDetailController {

    @Resource
    private WorkUserDao workUserDao;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletResponse response, HttpServletRequest request, Model model) {
        String username = WeixinUtil.getCookieValue(request);
        WorkUser user = new WorkUser();
        if (username != null) {
            user = workUserDao.findWorkUserByAccount(username);
        }
        model.addAttribute("user", user);
        return "weixin/userInfodetail";

    }
}
