package com.svsos.backend.controller.wx;
import java.sql.Timestamp;
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
 * 全部工单查询 以及操作
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
	
	@Resource ServiceOrderDao serviceOrderDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletResponse response , HttpServletRequest request ,Model model) {
		List<ServiceOrder> neworderLists = new ArrayList<ServiceOrder>();
		List<ServiceOrder> acporderLists = new ArrayList<ServiceOrder>();
		List<ServiceOrder> allorderLists = new ArrayList<ServiceOrder>();
	    String username = WeixinUtil.getCookieValue(request);
	    if(username != null){	    	
	        WorkUser user = workUserDao.findWorkUserByAccount(username);
	        if(user != null){	            	
            	List<ServiceOrder> newres = serviceOrderDao.findServiceOrderByacceptWorkerIdAndStatusForNew(user.getId());
            	if(null != newres && newres.size() > 0)
    			{
    				for(int i = 0;i < newres.size(); i ++)
    				{
    					ServiceOrder orderlist = new ServiceOrder();
    					orderlist.setOrderLsh(newres.get(i).getOrderLsh());
    					orderlist.setCreateTime(newres.get(i).getCreateTime());
    					orderlist.setStatus(newres.get(i).getStatus());
    					orderlist.setName(newres.get(i).getName());
    					orderlist.setTel(newres.get(i).getTel());
    					orderlist.setAddress(newres.get(i).getAddress());
    					orderlist.setRemark(newres.get(i).getRemark());    					
    					neworderLists.add(orderlist);
    				}
    			}
            	List<ServiceOrder> acpres = serviceOrderDao.findServiceOrderByacceptWorkerIdAndStatusForAcp(user.getId());
            	if(null != acpres && acpres.size() > 0)
    			{
    				for(int i = 0;i < acpres.size(); i ++)
    				{
    					ServiceOrder orderlist = new ServiceOrder();
    					orderlist.setOrderLsh(acpres.get(i).getOrderLsh());
    					orderlist.setCreateTime(acpres.get(i).getCreateTime());
    					orderlist.setStatus(acpres.get(i).getStatus());
    					orderlist.setName(acpres.get(i).getName());
    					orderlist.setTel(acpres.get(i).getTel());
    					orderlist.setAddress(acpres.get(i).getAddress());
    					orderlist.setRemark(acpres.get(i).getRemark());    					
    					acporderLists.add(orderlist);
    				}
    			}
            	List<ServiceOrder> allres = serviceOrderDao.findServiceOrderByacceptWorkerIdAndStatusForAll(user.getId());
            	if(null != allres && allres.size() > 0)
    			{
    				for(int i = 0;i < allres.size(); i ++)
    				{
    					ServiceOrder orderlist = new ServiceOrder();
    					orderlist.setOrderLsh(allres.get(i).getOrderLsh());
    					orderlist.setCreateTime(allres.get(i).getCreateTime());
    					orderlist.setStatus(allres.get(i).getStatus());
    					orderlist.setName(allres.get(i).getName());
    					orderlist.setTel(allres.get(i).getTel());
    					orderlist.setAddress(allres.get(i).getAddress());
    					orderlist.setRemark(allres.get(i).getRemark());    					
    					allorderLists.add(orderlist);
    				}
    			}
            }            	
        }
		model.addAttribute("neworderLists", neworderLists);
		model.addAttribute("acporderLists", acporderLists);
		model.addAttribute("allorderLists", allorderLists);
		return "weixin/order";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String lists(HttpServletResponse response , HttpServletRequest request ,Model model) {
		List<ServiceOrder> orderLists = new ArrayList<ServiceOrder>(); 
		List<ServiceOrder> res = null;
	    String username = WeixinUtil.getCookieValue(request);
	    String selected = request.getParameter("num");
	    System.out.println("selected="+selected);
	    if(username != null){	    	
	        WorkUser user = workUserDao.findWorkUserByAccount(username);
	        if(user != null){	
	        	if("1".equals(selected))
	        	{
	        		res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentDate(user.getId());
	        	}
	        	else if("2".equals(selected))
	        	{
	        		res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentWeek(user.getId());
	        	}	
	        	else if("3".equals(selected))
	        	{
	        		res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrentMonth(user.getId());
	        	}
	        	else if("4".equals(selected))
	        	{
	        		res = serviceOrderDao.findServiceOrderByacceptWorkerIdAndCurrenYear(user.getId());
	        	}	
            	
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
    					orderlist.setRemark(res.get(i).getRemark());   					
    					orderLists.add(orderlist);
    				}
    			}
            }            	
        }
		model.addAttribute("orderLists", orderLists);
		return "weixin/order";
	}
	
	@RequestMapping(value = "/change",method = RequestMethod.GET)
	public String change(HttpServletResponse response , HttpServletRequest request ,Model model) {
	    String username = WeixinUtil.getCookieValue(request);
	    String orderLsh = request.getParameter("orderLsh");
	    ServiceOrder order = new ServiceOrder();
	    Timestamp createTime = commonService.getCurrentTime();
	    if(username != null){	    	
	        WorkUser user = workUserDao.findWorkUserByAccount(username);
	        if(user != null){
	        	 order = serviceOrderDao.findServiceOrderByOrderLsh(user.getId(),orderLsh);
	        	 if(order != null){
    	
        			order.setStatus(3);
        			order.setCreateTime(createTime);
        			serviceOrderDao.save(order);
 	        	        		 
	        	 }	
	        }	        		
        }
	    return "redirect:/wx/order";
	}
}
