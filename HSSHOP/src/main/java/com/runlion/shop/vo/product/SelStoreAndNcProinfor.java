package com.runlion.shop.vo.product;

public class SelStoreAndNcProinfor {
	// 当前要查询的页码
	private Integer pageNum;
	// 每页的条数
	private Integer numPerPage;
	// 查询关键字
	private String keywords;
	//
	private String storeKeyWord;
	//
	private String facKeyWord;
	//
	private String proKeyWord;

	public String getStoreKeyWord() {
		return storeKeyWord;
	}

	public void setStoreKeyWord(String storeKeyWord) {
		this.storeKeyWord = storeKeyWord;
	}

	public String getFacKeyWord() {
		return facKeyWord;
	}

	public void setFacKeyWord(String facKeyWord) {
		this.facKeyWord = facKeyWord;
	}

	public String getProKeyWord() {
		return proKeyWord;
	}

	public void setProKeyWord(String proKeyWord) {
		this.proKeyWord = proKeyWord;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
