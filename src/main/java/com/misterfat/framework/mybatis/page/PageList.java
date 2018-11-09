package com.misterfat.framework.mybatis.page;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;

public class PageList<T> {

	/**
	 * 页码，从1开始
	 */
	private int pageNo;
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 起始行
	 */
	private int startRow;
	/**
	 * 末行
	 */
	private int endRow;
	/**
	 * 总数
	 */
	private long total;
	/**
	 * 总页数
	 */
	private int pages;
	/**
	 * count信号，3种情况，null的时候执行默认BoundSql,true的时候执行count，false执行分页
	 */
	private Boolean countSignal;
	/**
	 * 排序
	 */
	private String orderBy;
	/**
	 * 分页合理化
	 */
	private Boolean reasonable;
	/**
	 * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
	 */
	private Boolean pageSizeZero;

	private List<T> rows;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Boolean getCountSignal() {
		return countSignal;
	}

	public void setCountSignal(Boolean countSignal) {
		this.countSignal = countSignal;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getReasonable() {
		return reasonable;
	}

	public void setReasonable(Boolean reasonable) {
		this.reasonable = reasonable;
	}

	public Boolean getPageSizeZero() {
		return pageSizeZero;
	}

	public void setPageSizeZero(Boolean pageSizeZero) {
		this.pageSizeZero = pageSizeZero;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public PageList(Page<T> page) {
		super();
		this.pageNo = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.startRow = page.getStartRow();
		this.endRow = page.getEndRow();
		this.total = page.getTotal();
		this.pages = page.getPages();
		this.orderBy = page.getOrderBy();
		this.reasonable = page.getReasonable();
		this.pageSizeZero = page.getPageSizeZero();
		this.rows = new ArrayList<T>();
		if (page.getResult() != null) {
			this.rows.addAll(page.getResult());
		}
	}

	public PageList(Page<?> page, List<T> list) {
		super();
		this.pageNo = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.startRow = page.getStartRow();
		this.endRow = page.getEndRow();
		this.total = page.getTotal();
		this.pages = page.getPages();
		this.orderBy = page.getOrderBy();
		this.reasonable = page.getReasonable();
		this.pageSizeZero = page.getPageSizeZero();
		if (list != null) {
			this.rows = new ArrayList<T>();
		}
		if (!list.isEmpty()) {
			this.rows.addAll(list);
		}
	}

	public PageList() {
		super();
	}

	@Override
	public String toString() {
		return "PageList [pageNo=" + pageNo + ", pageSize=" + pageSize + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", total=" + total + ", pages=" + pages + ", countSignal=" + countSignal + ", orderBy=" + orderBy
				+ ", reasonable=" + reasonable + ", pageSizeZero=" + pageSizeZero + ", list=" + rows + "]";
	}

}
