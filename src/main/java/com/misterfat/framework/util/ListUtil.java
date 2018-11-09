package com.misterfat.framework.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUtil {

	private final static Logger logger = LoggerFactory.getLogger(ListUtil.class);

	/*
	 * 私有构造方法
	 */
	private ListUtil() {

	}

	/**
	 * 校验集合是是否为空或无长度
	 * 
	 * @param list
	 *            待校验集合
	 * @return 数据为空或无长度返回true，否则返回false
	 */
	public static boolean isEmpty(List<?> list) {
		return (list == null || list.size() == 0);
	}

	/**
	 * 校验集合访问索引是否越界
	 * 
	 * @param list
	 *            待校验集合
	 * @param index
	 *            集合访问索引
	 * @return 集合为空、无长度、索引值小于零或大于等于集合长度返回true， 否则返回false
	 */
	public static boolean isOutOfBound(List<?> list, int index) {
		if (isEmpty(list)) {
			return true;
		}
		return (index >= list.size() || index < 0);
	}

	/**
	 * 查找元素在集合中的位置，没有找到返回-1
	 * 
	 * @param list
	 * @param item
	 * @return
	 */
	public static int indexOf(List<?> list, Object item) {
		if (isEmpty(list)) {
			return -1;
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null && list.get(i).equals(item)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * 功能描述：用指定符号把集合元素分割成字符串
	 *
	 * @param list
	 * @param mark
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static <T> String join(List<T> list, String mark) {

		if (list == null) {
			return null;
		}
		String text = "";
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				text += mark;
			}
			text += list.get(i).toString();
		}
		return text;
	}

	/**
	 * 
	 * 功能描述：从一个对象的集合中取得某个属性的集合
	 *
	 * @param list
	 * @param columnName
	 * @param columnClass
	 * @return
	 * 
	 * @author 耿沫然
	 * @param <E>
	 *
	 * @since 2015年11月23日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T, E> List<T> getColumns(List<E> list, String columnName) {
		if (list == null) {
			return null;
		}

		List<T> resultList = new ArrayList<T>();

		for (E obj : list) {
			try {
				Field field = obj.getClass().getDeclaredField(columnName);
				field.setAccessible(true);
				T object = (T) field.get(obj);
				resultList.add(object);

			} catch (Exception e) {
				logger.error("{}", e);
				throw new RuntimeException("获取字段" + columnName + "失败", e);
			}
		}
		return resultList;
	}

	/**
	 * 
	 * 功能描述：集合去重
	 *
	 * @param list
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月24日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static <T> List<T> distinct(List<T> list) {
		return new ArrayList<T>(new HashSet<T>(list));
	}

	/**
	 * 
	 * 功能描述：集合排序
	 *
	 * @param list
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月24日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		Collections.sort(list);
		return list;
	}

	/**
	 * 
	 * 功能描述：切分list
	 *
	 * @param list
	 * @param size
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月19日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static <T> List<List<T>> split(List<T> list, int size) {
		List<List<T>> result = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			int listSize = list.size();
			int fromIndex = 0;
			while (listSize > 0) {
				if (listSize > size) {
					List<T> subList = list.subList(fromIndex, fromIndex + size);
					result.add(subList);
				} else {
					List<T> subList = list.subList(fromIndex, fromIndex + listSize);
					result.add(subList);
				}
				fromIndex += size;
				listSize -= size;

			}
		}
		return result;

	}

}