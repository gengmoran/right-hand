package com.misterfat.righthand.component;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class PasswordEncryptor {

	/**
	 * 
	 * 功能描述：加密
	 *
	 * @param password
	 *            原密码
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年6月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public String encrypt(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

}
