package com.svsos.backend.controller.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.svsos.backend.model.WorkUser;
import com.svsos.backend.model.WorkerSignin;
import com.svsos.backend.repositories.jpa.WorkUserDao;
import com.svsos.backend.repositories.jpa.WorkerSigninDao;
import com.svsos.backend.service.CommonService;
import com.svsos.backend.weixin.util.WeixinUtil;

/**
 * 用户每日签到
 * 
 * @author zhouliangjun
 * @date 2014-12-23
 * 
 */
@Controller
@RequestMapping("wx/signin")
public class WorkSigninController {
	@Resource
	private WorkUserDao workUserDao;
	@Resource
	private WorkerSigninDao workerSigninDao;
	@Resource
	private CommonService commonService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletResponse response, HttpServletRequest request, Model model) {
		List<WorkerSignin> signinLists = new ArrayList<WorkerSignin>();
		String username = WeixinUtil.getCookieValue(request);
		if (username != null) {
			WorkUser user = workUserDao.findWorkUserByAccount(username);
			if (user != null) {
				List<WorkerSignin> todayStatus = workerSigninDao.findWorkerSigninForCurrByWorkId(user.getId());
				if (null != todayStatus && todayStatus.size() > 0) {
					model.addAttribute("hasSingn", 1);
				} else {
					model.addAttribute("hasSingn", 0);
				}
				signinLists = workerSigninDao.findWorkerSigninByWorkId(user.getId());
			}
		}
		model.addAttribute("signinLists", signinLists);
		return "weixin/workSignin";
	}

	@RequestMapping(value = "/savelocation", method = RequestMethod.GET)
	public void savelocation(HttpServletResponse response, HttpServletRequest request, Model model) {
		Map<String, String> result = Maps.newHashMap();
		String username = WeixinUtil.getCookieValue(request);
		String location = request.getParameter("location");
		System.out.println("location:" + location);
		String[] locationArry = location.split(",");
		// 经度
		String lng = locationArry[0];
		// 纬度
		String lat = locationArry[1];
		BigDecimal lngBaidu = new BigDecimal(lng);
		BigDecimal latBaidu = new BigDecimal(lat);
		String addr = geocodeAddr(lng, lat);// (38.9146943,121.612382);
		System.out.println(addr);
		Timestamp signinTime = commonService.getCurrentTime();
		Date dateTime = commonService.getCurrentDate();
		WorkerSignin signin = new WorkerSignin();
		if (username != null) {
			WorkUser user = workUserDao.findWorkUserByAccount(username);
			if (user != null) {
				List<WorkerSignin> todayStatus = workerSigninDao.findWorkerSigninForCurrByWorkId(user.getId());
				if (todayStatus == null || todayStatus.isEmpty()) {
					signin.setWorkerId(user.getId());
					signin.setSigninDate(dateTime);
					signin.setSigninTime(signinTime);
					signin.setStatus(1);
					signin.setLatBaidu(latBaidu);
					signin.setLngBaidu(lngBaidu);
					workerSigninDao.save(signin);
				}
			}
		}
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String geocodeAddr(String latitude, String longitude) {
		String addr = "";

		// 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
		// 密钥可以随便写一个key=abc
		// output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
		String url = String.format("http://ditu.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s", latitude, longitude);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		try {
			httpsConn = myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					System.out.println(data);
					String[] retList = data.split(",");
					if (retList.length > 2 && ("200".equals(retList[0]))) {
						addr = retList[2];
						addr = addr.replace("\"", "");
					} else {
						addr = "";
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return addr;
	}
}
