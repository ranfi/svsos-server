package com.svsos.backend.controller.wx;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svsos.backend.model.ServiceOrder;
import com.svsos.backend.model.WorkUser;
import com.svsos.backend.repositories.jpa.ServiceOrderDao;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.util.WeixinUtil;
/** 
 * 待完成工单
 * @author zhouliangjun 
 * @date 2014-11-15 
 */ 
@Controller
@RequestMapping("/wx/unorder")
public class UoDoOrderController {

	@Resource 
    private WorkUserDao workUserDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource ServiceOrderDao serviceOrderDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletResponse response, HttpServletRequest request ,Model model) {
		List<ServiceOrder> orderLists = new ArrayList<ServiceOrder>(); 
	    String username = WeixinUtil.getCookieValue(request);
	    if(username != null){	    	
	        WorkUser user = workUserDao.findWorkUserByAccount(username);
	        if(user != null){	            	
            	List<ServiceOrder> res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndStatus(user.getId());
            	if(null != res && res.size() > 0)
    			{
    				for(int i = 0;i < res.size(); i ++)
    				{
    					ServiceOrder orderlist = new ServiceOrder();
    					orderlist.setOrderLsh(res.get(i).getOrderLsh());
    					orderlist.setCreateTime(res.get(i).getCreateTime());
    					orderlist.setStatus(res.get(i).getStatus());
    					orderlist.setName(res.get(i).getName());
    					orderlist.setTel(res.get(i).getTel());
    					orderlist.setAddress(res.get(i).getAddress());
    					orderlist.setProductBrand(res.get(i).getProductBrand());    					
    					orderLists.add(orderlist);
    				}
    			}
            }            	
        }
		model.addAttribute("orderLists", orderLists);
		return "weixin/undoorder";
	}
}
