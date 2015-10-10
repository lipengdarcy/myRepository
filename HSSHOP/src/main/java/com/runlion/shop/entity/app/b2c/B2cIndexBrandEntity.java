package com.runlion.shop.entity.app.b2c;

/**
 * Filename: B2cIndexBrandEntity.java <br>
 * Title: APP首页品牌推荐实体<br>
 * De.ion: 首页品牌推荐<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月2日 <br>
 * Time: 下午5:24:53 <br>
 * Version: 1.0.0 <br>
 */
public class B2cIndexBrandEntity {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private String name;
	private String url;
	private String img;
}
