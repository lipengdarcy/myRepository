package com.runlion.shop.service.ncinterface;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.alibaba.fastjson.JSON;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.vo.WebConfigVO;

public class NcInterface implements Runnable {

	// 方法的代码
	private String code = "";

	// nc返回信息
	private Boolean success = false;

	private NcRequestParam ncRequestParam;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public NcInterface(String code, NcRequestParam param) {
		this.code = code;
		this.ncRequestParam = param;
	}

	public NcInterface() {

	}

	public NcRequestParam getNcRequestParam() {
		return ncRequestParam;
	}

	public void setNcRequestParam(NcRequestParam ncRequestParam) {
		this.ncRequestParam = ncRequestParam;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code, NcRequestParam param) {
		this.code = code;
		this.ncRequestParam = param;
	}

	// 在线付款信息传回nc String code = "D2";
	// NC销售订单信用检查 String code = "30";
	// NC发货信用检查 String code = "4C";
	// 如果信用检查不通过，NC作废订单，返回提示信息
	// {"state":"failure","msg":""信用余额不足，尚缺金额："+xyye+"元""}
	public static boolean isNCSuccess(String code, NcRequestParam param)
			throws Exception {
		Object ret = callWebService(code, param); // {"state":"success","msg":""}
		NcReturnResult r = JSON.parseObject((String) ret, NcReturnResult.class);
		if (r.getState().equals("success"))
			return true;
		return false;
	}

	// NC销售订单信用检查,返回的msg为订单的NC编号
	public static NcReturnResult checkOrderSuccess(NcRequestParam param)
			throws Exception {
		String code = "30";
		Object ret = callWebService(code, param); // {"state":"success","msg":""}
		NcReturnResult r = JSON.parseObject((String) ret, NcReturnResult.class);

		return r;
	}

	public static Object callWebService(String code, NcRequestParam param)
			throws Exception {
		String url = "http://192.168.158.241:56/uapws/service/com.itf.snds.so.service.ISndsService";

		// String url =
		// "http://192.168.1.28:83/uapws/service/com.itf.snds.so.service.ISndsService";
		// 尝试从配置文件获取nc的配置信息
		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		if (configVo != null && !configVo.getNcUrl().trim().equals("")) {
			url = configVo.getNcUrl().trim();
		}

		String methodname = "methodPort";
		String[] paramnames = new String[] { "djlx", "dataStr" };
		Object[] params = new Object[] { code, JSON.toJSONString(param) };
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(new QName(url, methodname)); //
			for (int i = 0, ilen = paramnames.length; i < ilen; i++) {
				call.addParameter(paramnames[i],
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);
			}
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);// （标准的类型）
			Object o = call.invoke(params);
			return o;

		} catch (Exception ex) {
			throw ex;
		}

	}

	// test
	public static void main(String[] args) {
		NcRequestParam param = new NcRequestParam("100", "", "100028");

		NcInterface myThread = new NcInterface("D2", param);
		Thread thread = new Thread(myThread);
		thread.start();

	}

	// 与NC交互,最多5次
	@Override
	public void run() {
		boolean r = false;
		int count = 5;
		// 时间间隔：10分钟
		// long time_sleep = 1000;
		// this.code = "D2";
		long time_sleep = 1000 * 60 * 10;
		// if (this.getCode().equals("D2")) {
		while (count > 0 && !r) {
			count--;
			try {
				r = isNCSuccess(this.getCode(), this.getNcRequestParam());
				if (r)
					break;
				Thread.sleep(time_sleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println("第" + (5 - count) + "次与NC交互");

		}

		// }
		// 记录结果
		this.setSuccess(r);

	}
}
