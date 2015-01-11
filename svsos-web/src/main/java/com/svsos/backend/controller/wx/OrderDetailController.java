package com.svsos.backend.controller.wx;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.svsos.backend.common.bo.SystemGlobal;
import com.svsos.backend.model.ServiceOrder;
import com.svsos.backend.model.WorkUser;
import com.svsos.backend.repositories.jpa.ServiceOrderDao;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.weixin.util.WeixinUtil;
import com.svsos.core.utils.JsonMapper;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String list(HttpServletResponse response,
			HttpServletRequest request, Model model) {

		String orderLsh = request.getParameter("orderLsh");
		String username = WeixinUtil.getCookieValue(request);
		String message = request.getParameter("message");
		System.out.println("messahe:" + message);
		ServiceOrder order = new ServiceOrder();
		if (username != null) {
			WorkUser user = workUserDao.findWorkUserByAccount(username);
			if (user != null) {
				order = serviceOrderDao.findServiceOrderByOrderLsh(
						user.getId(), orderLsh);
			}
		}
		model.addAttribute("sysurl", SystemGlobal.loc);
		model.addAttribute("message", message);
		model.addAttribute("order", order);
		return "weixin/orderdetail";

	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map<String, String> upload(@RequestParam MultipartFile fileToUpload,
			HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> result = Maps.newHashMap();
		try {

			String url = UUID.randomUUID().toString().replace("-", "");
			String imgPath = SystemGlobal.serverloc + url;
			String resourceSavePath = "" + imgPath + ".jpg";
			String imgPath1 = SystemGlobal.loc + "/img/" + url;
			FileOutputStream out = new FileOutputStream(new File(
					resourceSavePath));
			IOUtils.copy(fileToUpload.getInputStream(), out);
			result.put("status", "success");
			result.put("imgPath", imgPath1 + ".jpg");
			result.put("url", "/img/" + url + ".jpg");
			// result.put("urlPrefix", "");//
			// SystemGlobal.DOWNLOAD_MATERIAL_URL_PREFIX);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
		}
		System.out.println(JsonMapper.nonDefaultMapper().toJson(result));

		return result;
	}
}
