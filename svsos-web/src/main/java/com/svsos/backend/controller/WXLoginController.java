
package com.svsos.backend.controller;

import com.svsos.backend.model.WorkUser;
import com.svsos.backend.model.WxUser;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.repositories.jpa.WxUserDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.util.DESTools;
import com.svsos.core.utils.EncryptUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * wxLoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * <p/>
 * 真正登录的POST请求由Filter完成,
 *
 * @author zhouliangjun
 */
@Controller
@RequestMapping(value = "/wx/login")
public class WXLoginController {

    @Resource
    private WorkUserDao workUserDao;

    @Resource
    private WxUserDao wxUserDao;

    @Resource
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String weixinlogin(HttpServletResponse response, HttpServletRequest request, Model model) {

        String idKey = request.getParameter("idKey");
        HttpSession sesson = request.getSession(true);
        sesson.setAttribute("idKey", idKey);
        model.addAttribute("idKey", idKey);
        return "weixin/login";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String weixinlogin(HttpServletResponse response, HttpServletRequest request, Model model, RedirectAttributes redirectAttr) throws UnsupportedEncodingException {


        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String message = "";
        String openid = request.getParameter("idKey");
        DESTools des = new DESTools();
        if(openid != null && !"".equals(openid))
        {
			openid = java.net.URLDecoder.decode(openid,"utf-8");
			openid = des.getDesString(openid);
			WxUser wxuser = wxUserDao.findWxUserByWxId(openid);
            WorkUser user = workUserDao.findWorkUserByAccount(userName);
            if (user != null) {
                String userPwd = user.getLoginPwd();
                String Pwd = EncryptUtils.md5Encrypt((EncryptUtils.md5Encrypt(passWord.getBytes("GBK"))).getBytes("GBK"));
                if (userPwd.endsWith(Pwd)) {
                    Timestamp loginTime = commonService.getCurrentTime();
					if(wxuser != null){
						user.setWxId(wxuser.getWxId());						
					}
                    user.setLoginTime(loginTime);
                    user.setStatus(2);
                    workUserDao.save(user);
                    int seconds = 30 * 24 * 60 * 60;
                    Cookie cookie = new Cookie("user", userName + "==" + Pwd);
                    cookie.setMaxAge(seconds);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return "redirect:/wx/order";

                } else {

                    message = "密码错误";
                }
            } else {

                message = "用户不存在";
            }
        }
		else{
			
			message = "请检查参数有效性";
		}
        
        model.addAttribute("message", message);
        return "weixin/login";
    }
}
