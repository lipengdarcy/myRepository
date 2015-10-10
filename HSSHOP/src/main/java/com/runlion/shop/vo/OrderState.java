package com.runlion.shop.vo;

/**
 * 订单状态
 * 
 * @author hsrj05
 *
 */
public enum OrderState {

	/** 0 待确认 */
	Confirming {
		public String getName() {
			return "待确认";
		}
	},
	/** 1 已确认 */
	Confirmed {
		public String getName() {
			return "已确认";
		}
	},
	/** 2 备货中 */
	PreProducting {
		public String getName() {
			return "备货中";
		}
	},
	/** 3 已发货 */
	Sended {
		public String getName() {
			return "已发货";
		}
	},
	/**4  已完成 */
	Completed {
		public String getName() {
			return "已完成";
		}
	},
	/** 5 锁定 */
	Locked {
		public String getName() {
			return "锁定";
		}
	},
	/** 6 已取消 */
	cancel {
		public String getName() {
			return "已取消";
		}
	};

	public abstract String getName();
}
