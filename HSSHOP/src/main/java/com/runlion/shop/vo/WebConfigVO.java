package com.runlion.shop.vo;

import java.io.Serializable;

/**
 * 网站配置文件对应的VO
 * 
 * @author hsrj05
 *
 */
public class WebConfigVO implements Serializable {

	private String ShopName;
	private String SiteUrl;
	private String SiteTitle;
	private String SEOKeyword;
	private String SEODescription;
	private String ICP;
	private String Script;
	private String IsLicensed;
	private String AfterSale;
	private String NcUrl;

	/**
	 * 获取商店的名字
	 * 
	 * @return String 商店的名字
	 */
	public String getShopName() {
		return ShopName;
	}

	/**
	 * 设置商店的名字
	 * 
	 * @param shopName
	 */
	public void setShopName(String shopName) {
		ShopName = shopName;
	}

	/**
	 * 获取网站的地址
	 * 
	 * @return String
	 */
	public String getSiteUrl() {
		return SiteUrl;
	}

	/**
	 * 设置网站的地址
	 * 
	 * @param siteUrl
	 */
	public void setSiteUrl(String siteUrl) {
		SiteUrl = siteUrl;
	}

	/**
	 * 获取网站的标题
	 * 
	 * @return
	 */
	public String getSiteTitle() {
		return SiteTitle;
	}

	/**
	 * 设置网站的标题
	 * 
	 * @param siteTitle
	 */
	public void setSiteTitle(String siteTitle) {
		SiteTitle = siteTitle;
	}

	/**
	 * 获取网站的SEO关键字
	 * 
	 * @return String
	 */
	public String getSEOKeyword() {
		return SEOKeyword;
	}

	/**
	 * 设置网站的SEO关键字
	 * 
	 * @param sEOKeyword
	 */
	public void setSEOKeyword(String sEOKeyword) {
		SEOKeyword = sEOKeyword;
	}

	/**
	 * 获取网站的SEO描述
	 * 
	 * @return String
	 */
	public String getSEODescription() {
		return SEODescription;
	}

	/**
	 * 设置网站的SEO描述
	 * 
	 * @param sEODescription
	 */
	public void setSEODescription(String sEODescription) {
		SEODescription = sEODescription;
	}

	/**
	 * 获取网站的备案icp
	 * 
	 * @return
	 */
	public String getICP() {
		return ICP;
	}

	/**
	 * 设置网站的备案icp
	 * 
	 * @param iCP
	 */
	public void setICP(String iCP) {
		ICP = iCP;
	}

	/**
	 * 设置脚本
	 * 
	 * @return
	 */
	public String getScript() {
		return Script;
	}

	/**
	 * 获取脚本
	 * 
	 * @param script
	 */
	public void setScript(String script) {
		Script = script;
	}

	/**
	 * 获取是否显示注册信息
	 * 
	 * @return
	 */
	public String getIsLicensed() {
		return IsLicensed;
	}

	/**
	 * 设置是否显示注册信息
	 * 
	 * @param isLicensed
	 */
	public void setIsLicensed(String isLicensed) {
		IsLicensed = isLicensed;
	}

	/**
	 * 获取售后保障信息
	 * 
	 * @return
	 */
	public String getAfterSale() {
		return AfterSale;
	}

	/**
	 * 设置售后保障信息
	 * 
	 * @param afterSale
	 */
	public void setAfterSale(String afterSale) {
		AfterSale = afterSale;
	}

	/**
	 * 获取NC的URL地址
	 * 
	 * @return
	 */
	public String getNcUrl() {
		return NcUrl;
	}

	/**
	 * 设置NC的URL地址
	 * 
	 * @param ncUrl
	 */
	public void setNcUrl(String ncUrl) {
		NcUrl = ncUrl;
	}

}
