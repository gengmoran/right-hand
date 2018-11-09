package com.misterfat.framework.util;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ResponseUtil {

	private final static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	/**
	 * 
	 * 功能描述：
	 *
	 * @param url
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月31日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getRedirectURL(String url) {
		return "redirect:" + url;
	}

	/**
	 * 
	 * 功能描述：获取HttpServletResponse对象
	 *
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月31日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static HttpServletResponse getHttpServletResponse() {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return resp;
	}

}