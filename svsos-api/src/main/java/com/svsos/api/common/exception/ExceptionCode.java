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
	
	SERVANTISFULL("30100", "您的仆人已满"),							//您的仆人已满
	SERVANTHASMASTER("30102", "该仆人已有主人"),						//您的仆人已满
	RELATIONSHIPEXPIRED("30103", "该关系不存在了"),					//关系不存在了
	REPEATREQUEST("30104", "您已经申请过了"),						//您已经申请过了
	ISYOURMASTER("30105", "已经是您的主人了"),						//您已经申请过了
	ISYOURSERVANT("30106", "已经是您的仆人了"),						//您已经申请过了
	
	IMMSGERROR("30150", "消息发送失败"),								//关系不存在了
	
	FLOWERNOTENOUGH("30200", "鲜花不足"),							//关系不存在了
	
	REGISTED("30250", "今日已经签到了"),								//关系不存在了
	
	
	
	
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
	
	public static void main(String[] args) {
		System.out.println(getExceptionCode(ExceptionCode.SERVANTISFULL.toString()));
	}

}
