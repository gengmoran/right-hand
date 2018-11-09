package com.misterfat.framework.mybatis.dao;

import java.io.Serializable;
import java.util.List;

import com.misterfat.framework.mybatis.query.QueryCondition;

public interface BaseRepository<T> {

	int insert(T entity);

	int insertSelective(T entity);

	int deleteById(Serializable id);

	int batchDelete(Serializable... ids);

	int update(T entity);

	int updateSelective(T entity);

	T findById(Serializable id);

	List<T> findAll();

	List<T> findList(QueryCondition query);

	int findTotalCount();

	int findCount(QueryCondition query);

}
