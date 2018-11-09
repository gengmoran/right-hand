package com.misterfat.framework.exception;

/**
 * 
 * 抛出此异常，给用户友好的提示信息
 * 
 */
public class GenericException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8776674766994508896L;

	private int code;

	private Object data;

	public GenericException(String message) {
		super(message);
		this.code = 500;

	}

	public GenericException(int code, String message) {
		super(message);
		this.code = code;

	}

	public GenericException(int code, String message, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
