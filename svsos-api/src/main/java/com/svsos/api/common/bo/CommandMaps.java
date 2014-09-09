package com.svsos.api.common.bo;

import com.svsos.api.common.exception.ExceptionCode;
import com.svsos.api.common.exception.WebRequestException;


public enum CommandMaps {
	
	
	

	COMMAND_10001001("userDomain", "loginFromLianxi", 0),					//	登录
	
	;
	
	
	
	
	
	
	public final String beanName;
	public final String methodName; 
	public final Integer level; 		// 1默认级别 需要accessToken登录    0 开放接口 无账号信息
	
	
	private CommandMaps(String beanName, String methodName){
		this(beanName, methodName, 1);
	}
	
	private CommandMaps(String beanName, String methodName, Integer level){
		this.beanName = beanName;
		this.methodName = methodName;
		this.level = level;
	}
	
	public static CommandMaps getCommandMap(String command){
		try{
			return CommandMaps.valueOf("COMMAND_"+command);
		}catch(Exception e){
			throw new WebRequestException(ExceptionCode.METHODDOESNOTEXIST);
		}
		
	}
}
