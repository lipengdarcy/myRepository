package com.runlion.shop.vo;

/**
 * 允销目录查询参数
 * 
 * @author hsrj05
 *
 */
public class SalerProSelParaVO {
	private Integer pageNumber = 1;
	private Integer pageSize = 9;
	private String ncpronum;
	private String proname;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getNcpronum() {
		return ncpronum;
	}

	public void setNcpronum(String ncpronum) {
		this.ncpronum = ncpronum;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
}
