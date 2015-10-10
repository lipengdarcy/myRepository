package com.runlion.shop.vo.product;

import java.util.ArrayList;
import java.util.List;

public class SelStoreAndNcProinforRes {
	private Integer totalPage;
	private Integer totalnum;
	private Integer pagenum;
	private Integer totalpages;
	private Integer curpage;

	private List<StoreAndNcProinfor> resList = new ArrayList();

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public Integer getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}

	public Integer getCurpage() {
		return curpage;
	}

	public void setCurpage(Integer curpage) {
		this.curpage = curpage;
	}

	public List<StoreAndNcProinfor> getResList() {
		return resList;
	}

	public void setResList(List<StoreAndNcProinfor> resList) {
		this.resList = resList;
	}
}
