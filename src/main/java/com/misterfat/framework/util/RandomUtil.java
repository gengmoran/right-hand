package com.misterfat.framework.util;

import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * 
 *
 * @author 耿沫然
 *
 * @version
 *
 * @since 2017年2月8日
 */
public class RandomUtil {

	/**
	 * 生成随机uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * 功能描述：生成指定位数随机整数
	 *
	 * @param len
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Integer randomInteger(int len) {
		int min = 0;
		if (len > 1) {
			min = (int) Math.pow(10, len - 1);
		}
		int max = (int) Math.pow(10, len) - 1;
		return randomInteger(min, max);
	}

	/**
	 * 
	 * 功能描述：生成指定范围随机整数
	 *
	 * @param min
	 *            最小值（包含）
	 * @param max
	 *            最大值（包含）
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Integer randomInteger(int min, int max) {
		// int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
		Random rand = new Random();
		int number = rand.nextInt(max - min + 1) + min;
		return number;
	}

}
