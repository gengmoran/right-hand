package com.misterfat.framework.util;

import java.util.Base64;

public class Base64Util {

	/**
	 * 
	 * 功能描述：BASE64加密
	 *
	 * @param bytes
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月5日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static byte[] encode(byte[] bytes) {
		return Base64.getEncoder().encode(bytes);
	}

	/**
	 * 
	 * 功能描述：BASE64加密
	 *
	 * @param bytes
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月5日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String encodeToString(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * 
	 * 功能描述：BASE64解密
	 *
	 * @param str
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月5日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static byte[] decode(byte[] bytes) {
		return Base64.getDecoder().decode(bytes);
	}

	/**
	 * 
	 * 功能描述：BASE64解密
	 *
	 * @param str
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月5日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static byte[] decode(String src) {
		return Base64.getDecoder().decode(src);
	}

	/**
	 * 
	 * 功能描述：BASE64解密
	 *
	 * @param src
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年10月12日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String decodeToString(String src) {
		return new String(Base64.getDecoder().decode(src));
	}
}