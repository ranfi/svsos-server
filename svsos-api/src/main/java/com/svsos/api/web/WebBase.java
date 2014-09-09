package com.svsos.api.web;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.svsos.api.common.exception.ExceptionCode;
import com.svsos.api.common.exception.WebRequestException;
import com.svsos.api.dto.ResponseDTO;
import com.svsos.core.utils.JsonMapper;

public abstract class WebBase implements ExceptionMapper<Throwable>{
	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		ExceptionCode exceptionCode = null;
		
		if(exception instanceof WebRequestException){
			exceptionCode = ((WebRequestException)exception).getExceptionCode();
		}else if(exception.getCause() instanceof WebRequestException){
			exceptionCode = ((WebRequestException)exception.getCause()).getExceptionCode();
		}
		if(exceptionCode==null){
			exceptionCode=ExceptionCode.SERVEREXCEPTION;
		}
		responseDTO.setErrorCode(exceptionCode.errorCode);
		responseDTO.setErrorMsg(exceptionCode.errorMsg);
		responseDTO.getResult().put("error detail", exception);
		
		logger.error(exception.getMessage(), exception);
		logger.info(JsonMapper.nonDefaultMapper().toJson(responseDTO));
		return Response.ok(responseDTO).build();
	}

	public ResponseDTO createNormalResponseDTO(){
		ResponseDTO responseDTO = new ResponseDTO();
		
		responseDTO.setErrorCode(ExceptionCode.NORMAL.errorCode);
		responseDTO.setErrorMsg(ExceptionCode.NORMAL.errorMsg);
		
		return responseDTO;
	}

}
