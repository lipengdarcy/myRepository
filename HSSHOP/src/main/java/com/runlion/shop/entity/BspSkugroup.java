package com.runlion.shop.entity;

public class BspSkugroup {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_skugroup.skugid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	private Integer skugid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_skugroup.skugname
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	private String skugname;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_skugroup.skugremarks
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	private String skugremarks;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_skugroup.cateid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	private Integer cateid;
	/**
	 * 辅助属性，不存入数据库
	 */
	private String cateName;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_skugroup.skugid
	 *
	 * @return the value of bsp_skugroup.skugid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public Integer getSkugid() {
		return skugid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_skugroup.skugid
	 *
	 * @param skugid
	 *            the value for bsp_skugroup.skugid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public void setSkugid(Integer skugid) {
		this.skugid = skugid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_skugroup.skugname
	 *
	 * @return the value of bsp_skugroup.skugname
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public String getSkugname() {
		return skugname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_skugroup.skugname
	 *
	 * @param skugname
	 *            the value for bsp_skugroup.skugname
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public void setSkugname(String skugname) {
		this.skugname = skugname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_skugroup.skugremarks
	 *
	 * @return the value of bsp_skugroup.skugremarks
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public String getSkugremarks() {
		return skugremarks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_skugroup.skugremarks
	 *
	 * @param skugremarks
	 *            the value for bsp_skugroup.skugremarks
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public void setSkugremarks(String skugremarks) {
		this.skugremarks = skugremarks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_skugroup.cateid
	 *
	 * @return the value of bsp_skugroup.cateid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public Integer getCateid() {
		return cateid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_skugroup.cateid
	 *
	 * @param cateid
	 *            the value for bsp_skugroup.cateid
	 *
	 * @mbggenerated Fri Jul 24 13:53:44 CST 2015
	 */
	public void setCateid(Integer cateid) {
		this.cateid = cateid;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}