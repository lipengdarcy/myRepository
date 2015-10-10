package com.runlion.shop.entity.enums;

import com.runlion.shop.common.util.IEnum;

/**
 * 订单状态
 * 
 * @author hsrj05
 *
 */
public enum OrderStateEnum implements IEnum {

	PreConfirm(0, "待确认"), Confirmed(1, "已确认"), PreVerify(2, "待验证"), Verified(3,
			"已验证"), PrePaid(4, "待支付"), Paid(5, "已支付"), PreSend(6, "待发货"), Sended(
			7, "已发货"), NoConfiremGet(8, "待确认收货"), ConfiremGet(9, "已收货"), PreComment(
			10, "待评价"), Commented(11, "已评价"), Locked(12, "锁定"), Expired(13,
			"已失效"), Cancel(14, "已取消"), PreReceive(15, "待收款"), PrePayCheck(16,
			"待付款");

	/** 编码 */
	private int code;

	/** 说明 */
	private String desc;

	OrderStateEnum(int code, String desc) {
		this.code = code;
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
