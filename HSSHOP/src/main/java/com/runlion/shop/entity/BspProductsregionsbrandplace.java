package com.runlion.shop.entity;

public class BspProductsregionsbrandplace {

	public BspProductsregionsbrandplace() {

	}

	public BspProductsregionsbrandplace(int id, int brandid,
			int productregionsid, int placeid) {
		this.id = id;
		this.brandid = brandid;
		this.productregionsid = productregionsid;
		this.placeid = placeid;
	}

	public BspProductsregionsbrandplace(int brandid, int productregionsid,
			int placeid) {
		this.brandid = brandid;
		this.productregionsid = productregionsid;
		this.placeid = placeid;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_productsregionsbrandplace.id
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_productsregionsbrandplace.brandId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	private Integer brandid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_productsregionsbrandplace.productregionsId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	private Integer productregionsid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column bsp_productsregionsbrandplace.placeId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	private Integer placeid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_productsregionsbrandplace.id
	 *
	 * @return the value of bsp_productsregionsbrandplace.id
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_productsregionsbrandplace.id
	 *
	 * @param id
	 *            the value for bsp_productsregionsbrandplace.id
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_productsregionsbrandplace.brandId
	 *
	 * @return the value of bsp_productsregionsbrandplace.brandId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public Integer getBrandid() {
		return brandid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_productsregionsbrandplace.brandId
	 *
	 * @param brandid
	 *            the value for bsp_productsregionsbrandplace.brandId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column
	 * bsp_productsregionsbrandplace.productregionsId
	 *
	 * @return the value of bsp_productsregionsbrandplace.productregionsId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public Integer getProductregionsid() {
		return productregionsid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column
	 * bsp_productsregionsbrandplace.productregionsId
	 *
	 * @param productregionsid
	 *            the value for bsp_productsregionsbrandplace.productregionsId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public void setProductregionsid(Integer productregionsid) {
		this.productregionsid = productregionsid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column bsp_productsregionsbrandplace.placeId
	 *
	 * @return the value of bsp_productsregionsbrandplace.placeId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public Integer getPlaceid() {
		return placeid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column bsp_productsregionsbrandplace.placeId
	 *
	 * @param placeid
	 *            the value for bsp_productsregionsbrandplace.placeId
	 *
	 * @mbggenerated Fri Aug 07 17:10:39 CST 2015
	 */
	public void setPlaceid(Integer placeid) {
		this.placeid = placeid;
	}
}