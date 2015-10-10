package com.hh.web.client;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/*
 测试地址：http://192.168.158.93:83/uapws/service/com.itf.snds.so.service.ISndsService

 方法名和参数：String methodPort(String djlx, String dataStr) 

 djlx表示是哪一种单据：NC中销售收款单的单据类型是D2；销售订单的单据类型是30；销售出库单的单据类型是4C

 销售收款单的dataStr传的是json字符串，电商传以下数据
 bankacc_fk 付款银行账号
 dzsj 到账时间
 je 金额
 pk_corp 公司（水泥电商对应的工厂）
 fkyhmc 付款银行名称
 custcode 客户编号

 其中银行帐号和银行名称可为空，可不传。


 销售收款单暂定以上。

 */

public class ClientTest {

	public static void main(String[] args) throws Exception {

		String djlx = "D2";// 销售收款单
		String dataStr = "{ 'bankacc_fk':'001002003', dzsj:'2015-09-14 11:03:01', 'je':'0.01','pk_corp':'001',fkyhmc: '002','custcode':'001'}";

		Object res = callWebService("", "methodPort", new String[] { "djlx",
				"dataStr" }, new Object[] { djlx, dataStr });

		System.out.println(res);

	}

	public static Object callWebService(String interclass, String methodname,
			String[] paramnames, Object[] params) throws Exception {

		String url = "http://192.168.158.93:83/uapws/service/com.itf.snds.so.service.ISndsService";
		String method = methodname;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(new URL(url));

			call.setOperationName(new QName(url, method)); //
			for (int i = 0, ilen = paramnames.length; i < ilen; i++) {
				call.addParameter(paramnames[i],
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);
			}

			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);// （标准的类型）

			Object o = call.invoke(params);
			return o;

		} catch (Exception ex) {
			// SCMEnv.error(ex);
			// ex.printStackTrace();
			throw ex;

		}

	}

}
