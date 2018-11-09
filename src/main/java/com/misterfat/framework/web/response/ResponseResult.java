package com.misterfat.framework.web.response;

public class ResponseResult<T> {

	private static final int SUCCESS_CODE = 200;

	private int code;

	private String message;

	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseResult(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResponseResult() {
		super();
	};

	public static <T> ResponseResult<T> success(String msg, T data) {
		return new ResponseResult<T>(SUCCESS_CODE, msg, data);
	}

	public static <T> ResponseResult<T> success(String msg) {
		return success(msg, null);
	}

	public static <T> ResponseResult<T> success(T data) {
		return success(null, data);
	}

	public static <T> ResponseResult<T> error(int code, String msg, T data) {
		return new ResponseResult<T>(code, msg, data);
	}

	public static <T> ResponseResult<T> error(int code, String msg) {
		return error(code, msg, null);
	}

}
