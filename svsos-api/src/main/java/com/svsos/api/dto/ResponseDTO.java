package com.svsos.api.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.svsos.api.common.exception.ExceptionCode;

public class ResponseDTO  implements Serializable {
	
	private static final long serialVersionUID = -1671607286610174732L;
	private String errorCode = ExceptionCode.NORMAL.errorCode;
	private String errorMsg = ExceptionCode.NORMAL.errorMsg;
	private final Map<String, Object> result = new HashMap<>();
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
