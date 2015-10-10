package com.runlion.shop.vo.app.b2c;

import java.util.List;
import java.util.Map;

import com.runlion.shop.entity.BspProductsWithPics;
import com.runlion.shop.entity.app.b2c.B2cIndexAdEntity;
import com.runlion.shop.entity.app.b2c.B2cIndexBrandEntity;

public class B2cIndexVo {

	private Map<String, Object> mapStringObject;

	public Map<String, Object> getMapStringObject() {
		return mapStringObject;
	}

	public void setMapStringObject(Map<String, Object> mapStringObject) {
		this.mapStringObject = mapStringObject;
	}

	private List<BspProductsWithPics> bspProductsWithPics;

	public List<BspProductsWithPics> getBspProductsWithPics() {
		return bspProductsWithPics;
	}

	public void setBspProductsWithPics(
			List<BspProductsWithPics> bspProductsWithPics) {
		this.bspProductsWithPics = bspProductsWithPics;
	}

	private List<B2cIndexBrandEntity> b2cIndexBrandEntity;

	public List<B2cIndexBrandEntity> getB2cIndexBrandEntity() {
		return b2cIndexBrandEntity;
	}

	public void setB2cIndexBrandEntity(
			List<B2cIndexBrandEntity> b2cIndexBrandEntity) {
		this.b2cIndexBrandEntity = b2cIndexBrandEntity;
	}

	private List<B2cIndexAdEntity> b2cIndexAdEntity;

	public List<B2cIndexAdEntity> getB2cIndexAdEntity() {
		return b2cIndexAdEntity;
	}

	public void setB2cIndexAdEntity(List<B2cIndexAdEntity> b2cIndexAdEntity) {
		this.b2cIndexAdEntity = b2cIndexAdEntity;
	}

}
