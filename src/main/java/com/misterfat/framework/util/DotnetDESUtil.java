package com.misterfat.framework.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DotnetDESUtil {

	/**
	 * 
	 * 功能描述：dotnet DES加密
	 *
	 * @param key
	 * @param message
	 * @return
	 * @throws Exception
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年10月12日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String encrypt(String key, String message) throws Exception {
		if (message == null || message.length() == 0) {
			return null;
		}
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

		return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));

	}

	/**
	 * 
	 * 功能描述： dotnet DES解密
	 *
	 * @param key
	 * @param message
	 * @return
	 * @throws Exception
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年10月12日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String decrypt(String key, String message) throws Exception {
		if (message == null || message.length() == 0) {
			return null;
		}

		byte[] src = Base64.getDecoder().decode(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(src);
		return new String(retByte);
	}

}
