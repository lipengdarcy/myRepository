package com.runlion.shop.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.HttpRequest;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.dao.BspPayorderMapper;
import com.runlion.shop.entity.BspMessage;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspPayorder;
import com.runlion.shop.entity.BspPayorderExample;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.entity.unionpay.UnionPayResult;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.UserService;
import com.runlion.shop.service.pay.UnionPayService;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;

/**
 * 银联支付测试 Controller
 * */
@Controller
@RequestMapping("/unionpay")
public class UnionPayController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private UnionPayService unionPayService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private BspPayorderMapper bspPayorderMapper;

	// 下单
	/**
	 * orderId: 8~32位， 商户订单号，不能含“-” 或“_”
	 * queryTime：14位，格式：YYYYMMDDhhmmss，商户发送交易时间
	 */
	@RequestMapping(value = "submitOrder")
	public String submitOrder(HttpSession httpSession,
			@RequestParam(defaultValue = "20150812144323") String orderId,
			ModelMap m) throws Exception {
		// 判断是否登录状态
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId == null)
			return "redirect:/account/login.do";
		BspOrders order = orderService.getOrderByOrderNo(orderId);
		if (order == null)
			return "home/order/OrderNotExists";
		String submitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(order
				.getAddtime());
		BigDecimal amount = order.getOrderamount();
		String html = unionPayService.submitOrder(orderId, submitTime, amount);
		m.put("html", html);
		return "home/order/jumpToUnionPay";
	}

	// 撤销订单
	@ResponseBody
	@RequestMapping(value = "cancleOrder")
	public Map<String, String> cancleOrder(
			@RequestParam(defaultValue = "20150812144323") String orderId,
			ModelMap m) throws Exception {
		Map<String, String> resmap = unionPayService.cancleOrder(orderId);
		return resmap;
	}

	// 退货
	@ResponseBody
	@RequestMapping(value = "reFundOrder")
	public Map<String, String> reFundOrder(
			@RequestParam(defaultValue = "20150812144323") String orderId,
			ModelMap m) throws Exception {
		Map<String, String> resmap = unionPayService.reFundOrder(orderId);
		return resmap;
	}

	// 查询订单
	@ResponseBody
	@RequestMapping(value = "queryOrder")
	public Map<String, String> queryOrder(
			@RequestParam(defaultValue = "20150812144323") String orderId,
			ModelMap m) throws Exception {
		Map<String, String> resmap = unionPayService.queryOrder(orderId);
		return resmap;
	}

	@RequestMapping(value = "acp_front_url")
	public String acp_front_url(ModelMap m, HttpSession httpSession,
			HttpServletRequest req) throws Exception {

		LogUtil.writeLog("FrontRcvResponse前台接收报文返回开始");

		req.setCharacterEncoding("ISO-8859-1");
		String encoding = req.getParameter(SDKConstants.param_encoding);
		LogUtil.writeLog("返回报文中encoding=[" + encoding + "]");

		Map<String, String> respParam = getAllRequestParam(req);

		// 打印请求报文
		LogUtil.printRequestLog(respParam);

		Map<String, String> valideData = null;
		UnionPayResult result = new UnionPayResult();

		if (null != respParam && !respParam.isEmpty()) {
			Iterator<Entry<String, String>> it = respParam.entrySet()
					.iterator();
			valideData = new HashMap<String, String>(respParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("ISO-8859-1"), encoding);
				valideData.put(key, value);
			}
		}
		if (!SDKUtil.validate(valideData, encoding)) {

			LogUtil.writeLog("验证签名结果[失败].");
		} else {
			LogUtil.writeLog("验证签名结果[成功].");
			System.out.println(valideData.get("orderId")); // 其他字段也可用类似方式获取
		}
		result = (UnionPayResult) result.mapToObject(result, valideData);

		// 保存银行返回信息
		unionPayService.saveBankReplyInfo(result);

		LogUtil.writeLog("FrontRcvResponse前台接收报文返回结束");

		// b2c商品跳转到订单详情页
		BspOrders order = orderService.getOrderByOrderNo(respParam
				.get("orderId"));
		if (order != null) {
			// 更新订单状态
			orderService.updateOrderPayState(order.getOid(), ""
					+ Constant.OrderPayState_finished);
			// 订单支付成功，发送短信通知用户
			informCustomer(order);
			// 如果是订单支付的，返回到订单详情页面
			return "redirect:/ucenter/orderinfo.do?oid=" + order.getOid();
		} else {
			// 在线交款
			BspPayorderExample e = new BspPayorderExample();
			e.createCriteria().andOsnEqualTo(result.getOrderId());
			List<BspPayorder> bspPayorder = bspPayorderMapper
					.selectByExample(e);

			String osn = "";
			if (bspPayorder != null && bspPayorder.size() > 0) {
				BspPayorder a = bspPayorder.get(0);
				a.setStatus(1);
				bspPayorderMapper.updateByPrimaryKeySelective(a);
				osn = a.getOsn();

			}
			return "redirect:/business/payOnlineSuccess.do?osn=" + osn;
		}

	}

	@RequestMapping(value = "acp_back_url")
	public String acp_back_url(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		LogUtil.writeLog("BackRcvResponse接收后台通知开始");

		req.setCharacterEncoding("ISO-8859-1");
		String encoding = req.getParameter(SDKConstants.param_encoding);
		// 获取请求参数中所有的信息
		Map<String, String> reqParam = getAllRequestParam(req);
		// 打印请求报文
		LogUtil.printRequestLog(reqParam);

		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("ISO-8859-1"), encoding);
				valideData.put(key, value);
			}
		}

		// 验证签名
		if (!SDKUtil.validate(valideData, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
		} else {
			System.out.println(valideData.get("orderId")); // 其他字段也可用类似方式获取
			LogUtil.writeLog("验证签名结果[成功].");
		}

		LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		return "";
	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}

	/**
	 * 订单支付成功，发送短信通知用户
	 * 
	 * @param BspOrders
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> informCustomer(BspOrders order) throws Exception {
		Map<String, String> res = new HashMap<String, String>();
		// 发送短信通知用户
		String template = "";
		switch (Integer.valueOf(order.getShipsystemname())) {
		case 1:
			template = Constant.MessageTemplate_store;
			break;
		case 2:
			template = Constant.MessageTemplate_factory;
			break;
		case 3:
			template = Constant.MessageTemplate_home;
			break;
		}
		String name = userService.getUserdetails(order.getUid()).getRealname();
		Date shiptime = order.getBesttime(); // 配送时间
		Calendar c = Calendar.getInstance();
		c.setTime(shiptime);
		String year = "" + c.get(Calendar.YEAR), month = ""
				+ (c.get(Calendar.MONDAY) + 1), day = "" + c.get(Calendar.DATE);
		String telnum = order.getMobile();
		// saleid
		BspSaleaddress address = bspProductsService.getBspSaleaddressById(order
				.getSalerid());
		if (address == null)
			return null;

		String msg = StringHandler.formateString(template, name,
				order.getOsn(), year, month, day, address.getTel());

		String rs = HttpRequest.sendMsm(telnum, msg);
		if (rs.equals("1")) {// 短信状态1 添加短信记录
			BspMessage bspMessage = new BspMessage();
			bspMessage.setMobile(telnum);
			bspMessage.setMessage(msg);
			bspMessage.setState("0");
			bspMessage.setOrderNumber(order.getOsn());// 订单号
			orderService.insertMessageAll(bspMessage);
			res.put("state", "success");
		} else {
			res.put("state", "fail");
			res.put("msg", "短信发送失败，请单击按钮重新发送。");
		}

		orderService.addOrderAction(order.getUid(), "本人", (short) 2,
				(byte) OrderStateEnum.PreConfirm.ordinal(), "本人", "在线支付完成",
				order.getOid());

		return res;
	}

}
