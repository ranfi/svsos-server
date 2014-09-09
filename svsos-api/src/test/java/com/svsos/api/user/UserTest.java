package com.svsos.api.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jersey.repackaged.com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.svsos.api.request.BaseClientTest;

public class UserTest extends BaseClientTest{
	private final static String API_URL = "http://localhost:8080/lianxi-api/command";
	
	private String accessToken="02A48277D31E344D2AEE9FA16685776C";
	
	
	
	@Test
	public void login() {
		Map<String, Object> map = Maps.newHashMap();
		
		map.put("appVersion", "testBase");
		map.put("phoneModel", "eclipse");
		map.put("platformType", "Java");
		map.put("accessToken", null);
		map.put("command", "10001001");
		
		Map<String, Object> param = Maps.newHashMap();
		
		param.put("snsId", 168177);
		param.put("nickname", "小燕子");
		param.put("phone", "13012344321");
		param.put("avatarUrl", "http://image.lianxi.com/dir/cm/userid/112/603/1362462179025_2.jpg");
		param.put("gender", 1);
		param.put("age", 18);
		
		
		map.put("param", param);
		String result = post(API_URL, map, String.class);
		
		System.out.println(result);
	}
	
	@Test
	public void getNearBy(){
		Map<String, Object> map = Maps.newHashMap();
		
		map.put("appVersion", "testBase");
		map.put("phoneModel", "eclipse");
		map.put("platformType", "Java");
		map.put("accessToken", accessToken);
		map.put("command", "10001002");
		
		Map<String, Object> param = Maps.newHashMap();
		//{"pageSize":20,"gender":1,"lng":20,"range":2,"lat":20,"pageNum":1}
		param.put("pageSize", 20);
		param.put("gender", 1);
		param.put("lng", 20);
		param.put("range", 2);
		param.put("lat", 20);
		param.put("pageNum", 1);
		
		
		map.put("param", param);
		String result = post(API_URL, map, String.class);
		
		System.out.println(result);
	}
	
	@Test
	public void testAccessToken(){
		Map<String, Object> map = Maps.newHashMap();
		
		map.put("appVersion", "testBase");
		map.put("phoneModel", "eclipse");
		map.put("platformType", "Java");
		map.put("accessToken", accessToken);
		map.put("command", "10001400");
		
		
		
		
		
		String result = post(API_URL, map, String.class);
		
		System.out.println(result);
	}
	
	@Test
	public void createUser(){
		String s = "";
		List<String> avatarList = new ArrayList<String>();
		avatarList.add("http://image.lianxi.com/dir/cm/userid/112/603/1362462179025_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/100/5/1284954819433_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/162/566/1404443130828_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/169/672/1407893718290_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/162/705/1400227347131_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/245/236/1396421417652_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/214/908/1397458119300_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/161/932/1403157537091_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/247/589/1399591185328_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/220/280/1399977802045_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/userid/166/181/1407149184488_2.jpg");
		avatarList.add("http://image.lianxi.com/dir/cm/default2.png");
		Random r = new Random();
		List<String> nameList = getNameList();

		
		Map<String, Object> map = Maps.newHashMap();
		
		map.put("appVersion", "testBase");
		map.put("phoneModel", "eclipse");
		map.put("platformType", "Java");
		map.put("accessToken", accessToken);
		map.put("command", "10001001");
		
		Map<String, Object> param = Maps.newHashMap();
		
		for(int i=0; i<nameList.size(); i++){
			param.put("snsId", 168177+i);
			param.put("nickname", nameList.get(i));
			param.put("phone", "1301234"+(1000+i));
			param.put("avatarUrl", avatarList.get(r.nextInt(avatarList.size())));
			param.put("gender", 1+(r.nextInt(2)));
			param.put("age", 18+(r.nextInt(20)));
			
			map.put("param", param);
			String result = post(API_URL, map, String.class);
			System.out.println(result);
		}
	}
	
	public List<String> getNameList(){
		List<String> list = new ArrayList<String>();
		try {
			
			File f = new File("D:\\name.txt");    
	    	if(f.isFile()&&f.exists())
	    	{     
	    		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");     
	    		BufferedReader reader=new BufferedReader(read);     
	    		String line;     
	    		while ((line = reader.readLine()) != null) 
	    		{      
	    			String[] ss = line.split(" ");
	 	            for(String name : ss){
	 	            	
	 	            	if(!StringUtils.isEmpty(name)){
	 	            		System.out.println(name);
	 	            		list.add(name);
	 	            	}
	 	            	
	 	            }    
	    		}       
	    		read.close();    
	    	}   
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //可以换成工程目录下的其他文本文件
        
        
		return list;
	}
	
	public List<String> getSignatureList(){
		List<String> list = new ArrayList<String>();
		try {
			
			File f = new File("D:\\signature.txt");    
	    	if(f.isFile()&&f.exists())
	    	{     
	    		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");     
	    		BufferedReader reader=new BufferedReader(read);     
	    		String line;     
	    		while ((line = reader.readLine()) != null) 
	    		{   if(!StringUtils.isEmpty(line)){
		    			String signature = line;
		    			String[] ss = line.split("、");
		    			if(ss.length>1){
		    				if(ss.length>2) continue;
		    				signature = ss[1];
		    			}
		    			signature = signature.trim();
		    			System.out.println(signature);
		    			list.add(signature);
	    			}
	    			
	 	            
	    		}       
	    		read.close();    
	    	}   
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //可以换成工程目录下的其他文本文件
        
        
		return list;
	}

	
}
