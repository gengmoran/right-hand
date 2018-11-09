package com.misterfat.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlResolveUtil {

	/**
	 * 
	 * 功能描述：解析URL
	 *
	 * @param url
	 * @param params
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String resolve(String url, String... params) {
		if (url != null && params != null) {
			String regex = "\\{.*?\\}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			int index = 0;
			while (matcher.find()) {
				String param = params[index];
				if (param != null) {
					url = url.replace(matcher.group(0), param);
				}
				index++;
			}
		}
		return url;
	}

}
