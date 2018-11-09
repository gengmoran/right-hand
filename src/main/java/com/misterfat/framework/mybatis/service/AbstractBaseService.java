package com.misterfat.framework.mybatis.service;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.misterfat.framework.mybatis.dao.BaseRepository;
import com.misterfat.framework.mybatis.page.PageList;
import com.misterfat.framework.mybatis.query.PageQueryCondition;
import com.misterfat.framework.mybatis.query.QueryCondition;

/**
 * 
 * 基础Service
 * 
 * @param <T>
 */
public abstract class AbstractBaseService<T> implements BaseService<T> {

	// 子类定义自己的Repository
	protected abstract BaseRepository<T> getRepository();

	// 添加对象
	@Override
	public int insert(T entity) {

		return getRepository().insert(entity);

	}

	// 添加指定字段
	@Override
	public int insertSelective(T entity) {

		return getRepository().insertSelective(entity);

	}

	// 通过ID删除对象
	@Override
	public int deleteById(Serializable id) {

		return getRepository().deleteById(id);

	}

	// 通过ID数组批量删除对象
	@Override
	public int batchDelete(Serializable... ids) {

		return getRepository().batchDelete(ids);

	}

	// 更新对象
	@Override
	public int update(T entity) {

		return getRepository().update(entity);

	}

	// 更新指定字段
	@Override
	public int updateSelective(T entity) {

		return getRepository().updateSelective(entity);

	}

	// 通过ID查询对象
	@Override
	public T findById(Serializable id) {

		return getRepository().findById(id);

	}

	// 查询所有
	@Override
	public List<T> findAll() {

		return getRepository().findAll();
	}

	// 通过条件查询对象
	@Override
	public List<T> findList(QueryCondition query) {
		PageHelper.orderBy(query.getOrderBy());
		return getRepository().findList(query);

	}

	// 通过条件查询对象
	@Override
	public PageList<T> findPaginationList(PageQueryCondition pageQuery) {

		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		return new PageList<T>((Page<T>) getRepository().findList(pageQuery));

	}

	// 查询总记录数
	@Override
	public int findTotalCount() {

		return getRepository().findTotalCount();

	}

	// 通过查询条件查询总数
	@Override
	public int findCount(QueryCondition query) {

		return getRepository().findCount(query);

	}

}
