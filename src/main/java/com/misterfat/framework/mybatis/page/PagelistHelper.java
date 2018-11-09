package com.misterfat.framework.mybatis.page;

import java.util.List;

import com.github.pagehelper.Page;

public class PagelistHelper {

	public static <T> PageList<T> pagelist(Page<T> page) {
		return new PageList<T>(page);
	}

	public static <T, E> PageList<E> pagelist(Page<T> page, List<E> list) {
		return new PageList<E>(page, list);
	}

}
