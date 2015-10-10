package com.runlion.shop.service.pay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.dao.UnionpayresultMapper;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.unionpay.UnionPayParam;
import com.runlion.shop.entity.unionpay.UnionPayResult;
import com.runlion.shop.service.OrderService;
import com.unionpay.acp.sdk.HttpClient;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;
import com.unionpay.acp.sdk.SecureUtil;

/**
 * 银联支付相关service
 * */

@Service
public class UnionPayService {

	@Autowired
	private UnionpayresultMapper unionpayresultMapper;

	@Autowired
	private OrderService orderService;

	// 字符集编码 默认"UTF-8"
	public static String encoding = "UTF-8";

	/**
	 * 初始化发送报文的公共参数
	 * */
	public Map<String, String> initData() {
		// 装载配置文件的参数
		UnionPayParam param = (UnionPayParam) EHCacheUtil.get("UnionPayParam");
		Map<String, String> data = param.generateMapValue(param);
		return data;
	}

	// 提交订单： 跳转到商户的订单支付页面，然后跳转到银联的支付页面
	public String submitOrder(String orderId, String submitTime,
			BigDecimal amount) {

		Map<String, String> data = initData();

		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型
		data.put("bizType", "000201");

		// 商户订单号，8-40位数字字母
		data.put("orderId", orderId);
		// 订单发送时间
		data.put("txnTime", submitTime);
		// 交易金额，单位分,把默认的元转为分
		amount = amount.multiply(new BigDecimal(100));
		data.put("txnAmt", amount.toBigInteger().toString());

		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		// data.put("reqReserved", "透传信息");
		// 订单描述，可不上送，上送时控件中会显示该信息
		// data.put("orderDesc", "订单描述");

		data = signData(data);
		// 交易请求url 从配置文件读取
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
		// 创建表单
		String html = createHtml(requestFrontUrl, data);
		return html;
	}

	// 查询订单
	public Map<String, String> queryOrder(String orderId, String submitTime) {

		Map<String, String> data = initData();

		// 交易类型
		data.put("txnType", "00");
		// 交易子类型
		data.put("txnSubType", "00");
		// 业务类型
		data.put("bizType", "000000");
		// 商户订单号，请修改被查询的交易的订单号
		data.put("orderId", orderId);
		// 订单发送时间，请修改被查询的交易的订单发送时间
		data.put("txnTime", submitTime);
		data = signData(data);
		// 查询请求
		String url = SDKConfig.getConfig().getSingleQueryUrl();
		Map<String, String> resmap = submitUrl(data, url);
		return resmap;
	}

	// 查询订单
	public Map<String, String> queryOrder(String orderId) {
		BspOrders order = orderService.getOrderByOrderNo(orderId);
		if (order == null)
			return null;
		String submitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(order
				.getAddtime());

		Map<String, String> data = initData();

		// 交易类型
		data.put("txnType", "00");
		// 交易子类型
		data.put("txnSubType", "00");
		// 业务类型
		data.put("bizType", "000000");
		// 商户订单号，请修改被查询的交易的订单号
		data.put("orderId", orderId);
		// 订单发送时间，请修改被查询的交易的订单发送时间
		data.put("txnTime", submitTime);
		data = signData(data);
		// 查询请求
		String url = SDKConfig.getConfig().getSingleQueryUrl();
		Map<String, String> resmap = submitUrl(data, url);
		return resmap;
	}

	// 撤销订单
	public Map<String, String> cancleOrder(String orderId) {

		Map<String, String> queryResult = queryOrder(orderId);
		String origQryId = queryResult.get("queryId");
		String newOrderId = orderId + "01"; // 新订单号
		String queryTime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String settleAmt = queryResult.get("settleAmt");

		Map<String, String> data = initData();

		// 交易类型
		data.put("txnType", "31");
		// 交易子类型
		data.put("txnSubType", "00");
		// 业务类型
		data.put("bizType", "000201");

		// 原消费的queryId，可以从查询接口或者通知接口中获取
		data.put("origQryId", origQryId);
		// 商户订单号，8-40位数字字母，重新产生，不同于原消费
		data.put("orderId", newOrderId);
		// 订单发送时间，取系统时间
		data.put("txnTime", queryTime);
		// 交易金额，消费撤销时需和原消费一致
		data.put("txnAmt", settleAmt);

		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		// data.put("reqReserved", "透传信息");

		data = signData(data);

		// 交易请求url 从配置文件读取
		String url = SDKConfig.getConfig().getBackRequestUrl();
		Map<String, String> resmap = submitUrl(data, url);
		return resmap;
	}

	// 退货
	public Map<String, String> reFundOrder(String orderId) {

		Map<String, String> queryResult = queryOrder(orderId);
		String origQryId = queryResult.get("queryId");
		String newOrderId = orderId + "01"; // 新订单号
		String queryTime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String settleAmt = queryResult.get("settleAmt");

		Map<String, String> data = initData();

		// 交易类型
		data.put("txnType", "04");
		// 交易子类型
		data.put("txnSubType", "00");
		// 业务类型
		data.put("bizType", "000201");

		// 原消费的queryId，可以从查询接口或者通知接口中获取
		data.put("origQryId", origQryId);
		// 商户订单号，8-40位数字字母，重新产生，不同于原消费
		data.put("orderId", orderId);
		// 订单发送时间，取系统时间
		data.put("txnTime", queryTime);
		// 交易金额
		data.put("txnAmt", settleAmt);
		data = signData(data);

		String url = SDKConfig.getConfig().getBackRequestUrl();
		Map<String, String> resmap = submitUrl(data, url);
		return resmap;
	}

	/**
	 * 对数据进行签名
	 * 
	 * @param contentData
	 * @return　签名后的map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> signData(Map<String, ?> contentData) {
		Entry<String, String> obj = null;
		Map<String, String> submitFromData = new HashMap<String, String>();
		for (Iterator<?> it = contentData.entrySet().iterator(); it.hasNext();) {
			obj = (Entry<String, String>) it.next();
			String value = obj.getValue();
			if (StringUtils.isNotBlank(value)) {
				// 对value值进行去除前后空处理
				submitFromData.put(obj.getKey(), value.trim());
			}
		}

		SDKUtil.sign(submitFromData, encoding);

		return submitFromData;
	}

	/**
	 * 数据提交到后台
	 * 
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitUrl(
			Map<String, String> submitFromData, String requestUrl) {
		String resultString = "";
		HttpClient hc = new HttpClient(requestUrl, 30000, 30000);
		try {
			int status = hc.send(submitFromData, encoding);
			if (200 == status) {
				resultString = hc.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> resData = new HashMap<String, String>();
		if (null != resultString && !"".equals(resultString)) {
			// 将返回结果转换为map
			resData = SDKUtil.convertResultStringToMap(resultString);
			if (!SDKUtil.validate(resData, encoding)) {
				return null;
			}
		}
		return resData;
	}

	/**
	 * 解析返回文件
	 */
	public static void deCodeFileContent(Map<String, String> resData) {
		// 解析返回文件
		String fileContent = resData.get(SDKConstants.param_fileContent);
		if (null != fileContent && !"".equals(fileContent)) {
			try {
				byte[] fileArray = SecureUtil.inflater(SecureUtil
						.base64Decode(fileContent.getBytes(encoding)));
				String root = "D:\\";
				String filePath = null;
				if (SDKUtil.isEmpty(resData.get("fileName"))) {
					filePath = root + File.separator + resData.get("merId")
							+ "_" + resData.get("batchNo") + "_"
							+ resData.get("txnTime") + ".txt";
				} else {
					filePath = root + File.separator + resData.get("fileName");
				}
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(fileArray, 0, fileArray.length);
				out.flush();
				out.close();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 持卡人信息域操作
	 * 
	 * @param encoding
	 *            编码方式
	 * @return base64后的持卡人信息域字段
	 */
	public static String getCustomer(String encoding) {
		StringBuffer sf = new StringBuffer("{");
		// 证件类型
		String certifTp = "01";
		// 证件号码
		String certifId = "1301212386859081945";
		// 姓名
		String customerNm = "测试";
		// 手机号
		String phoneNo = "18613958987";
		// 短信验证码
		String smsCode = "123311";
		// 持卡人密码
		String pin = "123213";
		// cvn2
		String cvn2 = "400";
		// 有效期
		String expired = "1212";
		sf.append("certifTp=" + certifTp + SDKConstants.AMPERSAND);
		sf.append("certifId=" + certifId + SDKConstants.AMPERSAND);
		sf.append("customerNm=" + customerNm + SDKConstants.AMPERSAND);
		sf.append("phoneNo=" + phoneNo + SDKConstants.AMPERSAND);
		sf.append("smsCode=" + smsCode + SDKConstants.AMPERSAND);
		// 密码加密
		sf.append("pin=" + SDKUtil.encryptPin("622188123456789", pin, encoding)
				+ SDKConstants.AMPERSAND);
		// 密码不加密
		// sf.append("pin="+pin + SDKConstants.AMPERSAND);
		// cvn2加密
		// sf.append(SDKUtil.encrptCvn2(cvn2, encoding) +
		// SDKConstants.AMPERSAND);
		// cvn2不加密
		sf.append("cvn2=" + cvn2 + SDKConstants.AMPERSAND);
		// 有效期加密
		// sf.append(SDKUtil.encrptAvailable(expired, encoding));
		// 有效期不加密
		sf.append("expired=" + expired);
		sf.append("}");
		String customerInfo = sf.toString();
		try {
			return new String(SecureUtil.base64Encode(sf.toString().getBytes(
					encoding)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customerInfo;
	}

	/**
	 * 构造HTTP POST交易表单的方法示例
	 * 
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + action
				+ "\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}

	public int saveBankReplyInfo(UnionPayResult record) {
		return unionpayresultMapper.insertBankInfo(record);
	}
}
