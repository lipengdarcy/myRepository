package com.itf.snds.so.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISndsService {

	@WebMethod
	String methodPort(String djlx, String dataStr);
	/*
	 * djlx表示是哪一种单据：NC中销售收款单的单据类型是D2；销售订单的单据类型是30；销售出库单的单据类型是4C
	 * 
	 * 销售收款单的dataStr传的是json字符串，电商传以下数据 bankacc_fk 付款银行账号 dzsj 到账时间 je 金额 pk_corp
	 * 公司（水泥电商对应的工厂） fkyhmc 付款银行名称 custcode 客户编号
	 * 
	 * 其中银行帐号和银行名称可为空，可不传。
	 */

}
