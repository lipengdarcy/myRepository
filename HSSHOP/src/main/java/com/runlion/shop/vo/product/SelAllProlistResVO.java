package com.runlion.shop.vo.product;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspProducts;

public class SelAllProlistResVO {

	private Integer totalPage;
	private Integer totalnum;
	private Integer pagenum;
	private Integer totalpages;
	private Integer curpage;
	private List<BspProducts> prolist = new ArrayList();

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

	public List<BspProducts> getProlist() {
		return prolist;
	}

	public void setProlist(List<BspProducts> prolist) {
		this.prolist = prolist;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
