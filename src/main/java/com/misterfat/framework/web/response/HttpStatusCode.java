package com.misterfat.framework.web.response;

public class HttpStatusCode {
	public static final int OK = 200;

	public static final String OK_MESSAGE = "Successful — 请求已完成";

	public static final int BAD_REQUEST = 400;

	public static final String BAD_REQUEST_MESSAGE = "请求中有语法问题，或不能满足请求";

	public static final int UNAUTHORIZED = 401;

	public static final String UNAUTHORIZED_MESSAGE = "授权客户机访问数据";

	public static final int NOT_FOUND = 404;

	public static final String NOT_FOUND_MESSAGE = "服务器找不到给定的资源；文档不存在";

	public static final int INTERNAL_SERVER_ERROR = 500;

	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "服务器不能完成请求";

}
