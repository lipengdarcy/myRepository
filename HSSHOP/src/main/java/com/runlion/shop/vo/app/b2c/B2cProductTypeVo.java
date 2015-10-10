package com.runlion.shop.vo.app.b2c;

import java.util.List;

import com.runlion.shop.entity.BspAttributevalues;

public class B2cProductTypeVo {

	private List<BspAttributevalues> brandList;
	private List<BspAttributevalues> strengthList;
	private List<BspAttributevalues> typeList;

	public List<BspAttributevalues> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<BspAttributevalues> brandList) {
		this.brandList = brandList;
	}

	public List<BspAttributevalues> getStrengthList() {
		return strengthList;
	}

	public void setStrengthList(List<BspAttributevalues> strengthList) {
		this.strengthList = strengthList;
	}

	public List<BspAttributevalues> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<BspAttributevalues> typeList) {
		this.typeList = typeList;
	}

}
