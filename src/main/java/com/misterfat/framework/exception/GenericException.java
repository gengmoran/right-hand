package com.misterfat.framework.exception;

import java.io.Serializable;

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

	private final int code;

	private final Serializable data;

	public GenericException(String message) {
		super(message);
		this.code = 500;
		this.data = null;

	}

	public GenericException(int code, String message) {
		super(message);
		this.code = code;
		this.data = null;
	}

	public GenericException(int code, String message, Serializable data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

}
