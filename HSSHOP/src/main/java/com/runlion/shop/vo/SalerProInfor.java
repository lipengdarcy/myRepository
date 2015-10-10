package com.runlion.shop.vo;

/**
 * 经销商允销产品的VO
 * 
 * @author hsrj05
 *
 */
public class SalerProInfor {
	/**
	 * 产品id
	 */
	private Integer pid;
	/**
	 * nc产品信息表的id
	 * 
	 */
	private Integer id;
	/**
	 * 产品编号
	 */
	private String psn;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品的nc编号
	 */
	private String ncpronum;
	/**
	 * 品牌id
	 */
	private Integer brandid;
	/**
	 * 品牌名称
	 */
	private String branname;
	/**
	 * 厂商id
	 */
	private Integer placeid;
	/**
	 * 厂商名称
	 */
	private String placename;
	/**
	 * 代理购买价格
	 */
	private String ncprice;
	/**
	 * 数量
	 */
	private Integer quantity;
	/**
	 * 图片的名称
	 */
	private String imgName;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public String getNcpronum() {
		return ncpronum;
	}

	public void setNcpronum(String ncpronum) {
		this.ncpronum = ncpronum;
	}

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	public String getBranname() {
		return branname;
	}

	public void setBranname(String branname) {
		this.branname = branname;
	}

	public Integer getPlaceid() {
		return placeid;
	}

	public void setPlaceid(Integer placeid) {
		this.placeid = placeid;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getNcprice() {
		return ncprice;
	}

	public void setNcprice(String ncprice) {
		this.ncprice = ncprice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
