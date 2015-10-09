package org.darcy.webservice;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

/*
 "vreceiptcode":"SO1509110016" --NC订单号,如果要取消订单，务必传订单号
 "custcode": "2010100263",  --客户编号
 "pk_corp": "1002",		--公司编码 
 "dbilldate": "2015-09-11",   --订单日期
 "cvehicle": "111111",		--车号
 "vnote": "测试水泥电商的数据", --备注
 "invcode": "060201001",  -存货编码 
 "so_qty": "3",  -数量
 "dj": "295",  --单价
 "je": "885"   --金额
 "stats":"0"   --状态   ，0表示是新的订单，1表示关闭该订单


 //水泥电商传输订单guolai，如果NC信用检查通过，直接会返回
 {"state":"success","msg":"NC订单编号（水泥电商需要记录）"}

 //如果信用检查不通过，NC作废订单，返回提示信息

 {"state":"failure","msg":""信用余额不足，尚缺金额："+xyye+"元""}





 */
public class Client {

	public static void main(String[] args) throws Exception {
		ClientProxyFactoryBean clientFactory = new ClientProxyFactoryBean();
		clientFactory
				.setAddress("http://192.168.158.93:83/uapws/service/com.itf.snds.so.service.ISndsService");
		RemoteService service = clientFactory.create(RemoteService.class);
		String djlx = "D2";// 销售收款单
		String dataStr = "{ 'bankacc_fk':'001002003', dzsj:'2015-09-14 11:03:01', 'je':'0.01','pk_corp':'001',fkyhmc: '002','custcode':'001'}";
		String r = service.methodPort(djlx, dataStr);

		System.out.println(r);

		Object res = callWebService("", "methodPort", new String[] { "djlx",
				"dataStr" }, new Object[] { "D2", "fasfas" });

		System.out.println(res);
	}

	// 调用方法
	/*
	 * com.util.MobileSrv.callWebService("", "msgsSendToOneUserNew", new
	 * String[]{"arg0", "arg1","arg2","arg3","arg4","arg5", "arg6"}, new
	 * Object[] { message_source,cubadoc,message_type,message_content,isreturn,
	 * resourceID,title});
	 */
	public static Object callWebService(String interclass, String methodname,
			String[] paramnames, Object[] params) throws Exception {
		// TODO Auto-generated method stub
		// String ipaddr = "127.0.0.1:83";
		// String addr = "localhost:8080";
		// SysInitDMO sysdmo = new SysInitDMO();
		// String addrn = sysdmo.getParaString("0001", "YSWEBADDR");
		// String soapaction = IPAddressSet.getNC56Address() + "/";

		// String url = "http://" + ipaddr +
		// "/uapwsrvice/com.cxbj.mobile.service.IMobileWebService";
		// String method = "getCorpInfo"; http://192.168.1.126:
		// http://localhost:8080/HSSN/MSGWebServicePort?wsdl
		// String url = "http://" + addrn + "/HSSN/MSGWebServicePort" + "?wsdl";
		String url = "http://192.168.158.93:83/uapws/service/com.itf.snds.so.service.ISndsService";
		System.out.println(url);
		String method = methodname;
		String soapaction = "http://192.168.158.93:83/uapws/service/com.itf.snds.so.service.ISndsService";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(new URL(url));

			call.setOperationName(new QName(soapaction, method)); //
			// call.addParameter("pkcorp1",
			// org.apache.axis.encoding.XMLType.XSD_STRING,
			// javax.xml.rpc.ParameterMode.IN);
			// // 设置要调用哪个方法

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
