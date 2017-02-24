package com.enation.app.base.core.model;

/**
 * 插件数据对象
 * @author xulipeng
 * 2016年02月25日11:12:46
 */

public class PluginDataMapper {

	
	private Integer order;		//排序
	
	private String tabTitle;	//tab标题
	
	private String tabHtml;		//tab数据
	

	//set  get
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}


	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public String getTabHtml() {
		return tabHtml;
	}

	public void setTabHtml(String tabHtml) {
		this.tabHtml = tabHtml;
	}
	
	
}
