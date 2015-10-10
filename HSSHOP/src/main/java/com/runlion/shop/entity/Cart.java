package com.runlion.shop.entity;

/**
 * 购物车
 *
 */
public class Cart {
	// 主键
	private Integer id;
	// 产品id
	private Integer productId;
	// 产品数量
	private Integer count;
	// 用户id
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
