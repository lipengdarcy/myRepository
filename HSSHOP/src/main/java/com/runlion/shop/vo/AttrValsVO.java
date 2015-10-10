package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributevalues;

/**
 * 复合VO 属性和属性值的复合VO
 * 
 * @author lwt
 *
 */
public class AttrValsVO {

	/**
	 * 
	 */
	private BspAttributes attributes;

	/**
	 * 
	 */
	private List<BspAttributevalues> attrValList = new ArrayList();

	public BspAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(BspAttributes attributes) {
		this.attributes = attributes;
	}

	public List<BspAttributevalues> getAttrValList() {
		return attrValList;
	}

	public void setAttrValList(List<BspAttributevalues> attrValList) {
		this.attrValList = attrValList;
	}
}
