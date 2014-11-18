package com.svsos.backend.controller.wx;

import com.svsos.backend.model.ServiceOrder;
import com.svsos.backend.model.WorkUser;
import com.svsos.backend.repositories.jpa.ServiceOrderDao;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.util.WeixinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 工单查询
 *
 * @author zhouliangjun
 * @date 2014-11-15
 */
@Controller
@RequestMapping("/wx/order")
public class OrderController {

    @Resource
    private WorkUserDao workUserDao;

    @Resource
    private CommonService commonService;

    @Resource
    ServiceOrderDao serviceOrderDao;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletResponse response, HttpServletRequest request, Model model) {
        List<ServiceOrder> orderLists = new ArrayList<ServiceOrder>();
        String username = WeixinUtil.getCookieValue(request);
        if (username != null) {
            WorkUser user = workUserDao.findWorkUserByAccount(username);
            if (user != null) {
                List<ServiceOrder> res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentDate(user.getId());
                if (null != res && res.size() > 0) {
                    for (int i = 0; i < res.size(); i++) {
                        ServiceOrder orderlist = new ServiceOrder();
                        orderlist.setAddress(res.get(i).getAddress());
                        orderlist.setProductBrand(res.get(i).getProductBrand());
                        orderlist.setName(res.get(i).getName());
                        orderlist.setTel(res.get(i).getTel());
                        orderlist.setCreateTime(res.get(i).getCreateTime());
                        orderLists.add(orderlist);
                    }
                }
            }
        }
        model.addAttribute("orderLists", orderLists);
        return "weixin/order";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String lists(HttpServletResponse response, HttpServletRequest request, Model model) {
        List<ServiceOrder> orderLists = new ArrayList<ServiceOrder>();
        List<ServiceOrder> res = null;
        String username = WeixinUtil.getCookieValue(request);
        String selected = request.getParameter("num");
        System.out.println("selected=" + selected);
        if (username != null) {
            WorkUser user = workUserDao.findWorkUserByAccount(username);
            if (user != null) {
                if ("1".equals(selected)) {
                    res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentDate(user.getId());
                } else if ("2".equals(selected)) {
                    res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentWeek(user.getId());
                } else if ("3".equals(selected)) {
                    res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentMonth(user.getId());
                } else if ("4".equals(selected)) {
                    res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrenYear(user.getId());
                }

                if (null != res && res.size() > 0) {
                    for (int i = 0; i < res.size(); i++) {
                        ServiceOrder orderlist = new ServiceOrder();
                        orderlist.setAddress(res.get(i).getAddress());
                        orderlist.setProductBrand(res.get(i).getProductBrand());
                        orderlist.setName(res.get(i).getName());
                        orderlist.setTel(res.get(i).getTel());
                        orderlist.setCreateTime(res.get(i).getCreateTime());
                        orderLists.add(orderlist);
                    }
                }
            }
        }
        model.addAttribute("orderLists", orderLists);
        return "weixin/order";
    }
}
