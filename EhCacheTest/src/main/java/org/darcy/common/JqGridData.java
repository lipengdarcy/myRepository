package org.darcy.common;

import java.io.Serializable;
import java.util.List;

//1.按照JqGrid的格式定义一个bean
public class JqGridData<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * 成员变量 对应 prmNames 对应 jsonReader 注释 rows rows - 每页中现实的记录行数 search search -
	 * 是否是用于查询的请求 sidx sort - 用于排序的列名 sord order - 排序的方式 page page 当前页码
	 * gridModel - root 用于得到实际数据的数组名称 total - total 总页数 record - records 总记录数
	 */
	// 和JqGrid组件相关的参数属性
	/**
	 * 查询返回的数组 $responce->page = $page; $responce->total = $total_pages;
	 * $responce->records = $count; $i=0; while($row =
	 * mysql_fetch_array($result,MYSQL_ASSOC)) {
	 * $responce->rows[$i]['id']=$row[id]; $responce->rows[$i][
	 * 'cell']=array($row[id],$row[invdate],$row[name],$row[amount],$row[tax],$row[total],$row[note
	 * ] ) ; $i++; }
	 */

	private String sord; // 排序的方式
	private String sidx; // 用于排序的列名
	private String search; // 是否用于查询的请求

	/** Total number of pages */
	private int total;
	/** The current page number */
	private int page;
	/** Total number of records */
	private int records;
	/** The actual data */
	private List<T> rows;

	public JqGridData(int total, int page, int records, List<T> rows) {
		this.total = total;
		this.page = page;
		this.records = records;
		this.rows = rows;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
