package com.misterfat.framework.mybatis.service;

import java.io.Serializable;
import java.util.List;

import com.misterfat.framework.mybatis.page.PageList;
import com.misterfat.framework.mybatis.query.PageQueryCondition;
import com.misterfat.framework.mybatis.query.QueryCondition;

public interface BaseService<T> {

	// 添加对象
	int insert(T entity);

	// 添加指定字段
	int insertSelective(T entity);

	// 通过ID删除对象
	int deleteById(Serializable id);

	// 通过ID数组批量删除对象
	int batchDelete(Serializable... ids);

	// 更新对象
	int update(T entity);

	// 更新指定字段
	int updateSelective(T entity);

	// 通过ID查询对象
	T findById(Serializable id);

	// 查询所有
	List<T> findAll();

	// 通过条件查询对象
	List<T> findList(QueryCondition query);

	// 查询分页记录
	PageList<T> findPaginationList(PageQueryCondition pageQuery);

	// 查询总记录数
	int findTotalCount();

	// 通过查询条件查询总数
	int findCount(QueryCondition query);

}