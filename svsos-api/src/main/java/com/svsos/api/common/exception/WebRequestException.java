package com.svsos.api.common.exception;


public class WebRequestException extends RuntimeException {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8025434620321706646L;
	private String exceptionDetails;
	private ExceptionCode exceptionCode;

	public WebRequestException() {
	}

	/**
	 * @param message
	 */
	public WebRequestException(int message) {
		super(String.valueOf(message));
	}

	/**
	 * @param message
	 */
	public WebRequestException(String message) {
		super(message);
	}
	
	/**
	 * @param ExceptionCode
	 */
	public WebRequestException(ExceptionCode exceptionCode) {
		this.setExceptionCode(exceptionCode);
	}
	
	public WebRequestException(ExceptionCode exceptionCode, String details) {
		this.setExceptionCode(exceptionCode);
		this.exceptionDetails = details;
	}

	/**
	 * @param cause
	 */
	public WebRequestException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WebRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(String exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	public ExceptionCode getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}


}
