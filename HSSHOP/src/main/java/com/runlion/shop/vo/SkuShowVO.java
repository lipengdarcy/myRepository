package com.runlion.shop.vo;

public class SkuShowVO {

	private int attrid;
	private int attrValid;
	private String attrVal;
	private Byte isInput;
	private String attrName;
	private String inputVal;

	public void setInputVal(String inputVal) {
		this.inputVal = inputVal;
	}

	public int getAttrid() {
		return attrid;
	}

	public void setAttrid(int attrid) {
		this.attrid = attrid;
	}

	public int getAttrValid() {
		return attrValid;
	}

	public void setAttrValid(int attrValid) {
		this.attrValid = attrValid;
	}

	public String getAttrVal() {
		return attrVal;
	}

	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}

	public Byte getIsInput() {
		return isInput;
	}

	public void setIsInput(Byte isInput) {
		this.isInput = isInput;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getInputVal() {
		return inputVal;
	}

}
