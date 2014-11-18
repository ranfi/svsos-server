package com.svsos.backend.weixin.util;

import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON工具类
 *
 */
public class JSONUtil {
	/**
	 * 转换object为JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj){
		return JSONObject.fromObject(obj).toString();
	}
	
	/**
	 * 转换object为JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJSONArrayString(Collection c){
		return JSONArray.fromObject(c).toString();
	}
	
	/**
	 * 以JSON格式输出数据
	 * @param obj
	 * @return
	 */
	public static void outResult(HttpServletResponse response,Object object)
	{	
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out=response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(JSONUtil.toJSONString(object));
		out.flush();
		out.close();
	}
	
	/**
	 * 将obj(一般为jsonString)转换为Bean
	 * @param obj
	 * @param objClass
	 * @return
	 */
	public static Object toBean(Object obj , Class objClass){
		return JSONObject.toBean(JSONObject.fromObject(obj), objClass);
	}
}
