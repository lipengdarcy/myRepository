package com.runlion.shop.entity.enums;

import com.runlion.shop.common.util.IEnum;

/**
 * 订单支付类型
 * 
 * @author hsrj05
 *
 */
public enum OrderPayTypeEnum implements IEnum {

	Cash(0, "现金"), AcceptanceBill(1, "承兑汇票"), Loan(2, "贷款"), Boan(3, "保证金");

	/** 编码 */
	private int code;

	/** 说明 */
	private String desc;

	OrderPayTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	OrderPayTypeEnum(String desc) {
		this.desc = desc;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}
}
