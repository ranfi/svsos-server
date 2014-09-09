package com.svsos.api.common.exception;

public enum ExceptionCode{
	
	NORMAL("1", "OK!"), 
	
	//系统级
	SERVEREXCEPTION("10001", "Server exception!"),   				//服务器异常
	METHODDOESNOTEXIST("10002","Method does not exist!"),			//接口不存在
	
	ACCOUNTEXCEPTION("20000","Invalid accessToken!"),				//无效的accessToken
	INVALIDACCESSTOKEN("20001","Invalid accessToken!"),				//无效的accessToken
	PARAMEXCEPTION("20101","Param to bean exception!"),				//Param参数转换异常
	USERNOTEMPTY("20102","User cannot be empty!"),
	ONEPARAMISEMPTY("20103","One param is empty!"),
	MISSINGPARAMETER("20104","Missing parameter!"),
	UPLOADFILEEXCEPTION("20105","upload file exception!"),
	
	
	;
	
	public final String errorCode;
	public final String errorMsg;
	
	private ExceptionCode(String errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public static ExceptionCode getExceptionCode(final String exceptionCode){
		try{
			System.out.println("----exceptionCode----"+exceptionCode);
			return ExceptionCode.valueOf(exceptionCode);
		}catch(Exception e){}
		
		return SERVEREXCEPTION;
	}
	
	
}
