package com.svsos.backend.controller.wx;

import com.svsos.backend.model.ServiceOrder;
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
@RequestMapping("/wx/orderDetail")
public class OrderDetailController {

    @Resource
    private ServiceOrderDao serviceOrderDao;

    @Resource
    private WorkUserDao workUserDao;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletResponse response, HttpServletRequest request, Model model) {

        String orderLsh = request.getParameter("orderLsh");
        String username = WeixinUtil.getCookieValue(request);
        ServiceOrder order = new ServiceOrder();
        if (username != null) {
            WorkUser user = workUserDao.findWorkUserByAccount(username);
            if (user != null) {
                order = serviceOrderDao.findServiceOrderByOrderLsh(user.getId(), orderLsh);
            }
        }
        model.addAttribute("order", order);
        return "weixin/orderdetail";

    }
}
