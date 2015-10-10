package com.runlion.shop.controller.business;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspCancelorderPicMapper;
import com.runlion.shop.dao.BspCancelorderReasonMapper;
import com.runlion.shop.dao.BspCommentMapper;
import com.runlion.shop.dao.BspOrderextMapper;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.dao.BspPayorderMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspSendorderMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.entity.BspCancelorderPic;
import com.runlion.shop.entity.BspCancelorderReason;
import com.runlion.shop.entity.BspComment;
import com.runlion.shop.entity.BspCommentExample;
import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspOrderextExample;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrderproductsExample;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspPayorder;
import com.runlion.shop.entity.BspPayorderExample;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspSaleaddressExample;
import com.runlion.shop.entity.BspSendorder;
import com.runlion.shop.entity.BspUserApply;
import com.runlion.shop.entity.BspUsersPermission;
import com.runlion.shop.entity.BspUsersPermissionExample;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.common.BusinessRequestParam;
import com.runlion.shop.entity.common.JsonResponseData;
import com.runlion.shop.entity.common.ResponseData;
import com.runlion.shop.entity.enums.OrderPayTypeEnum;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.OrderNoGenerator;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.PriceUpdateJobService;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.service.ncinterface.NcInterface;
import com.runlion.shop.service.ncinterface.NcRequestParam;
import com.runlion.shop.service.pay.UnionPayService;
import com.runlion.shop.service.region.RegionsService;

@Controller
@RequestMapping("/business")
public class BusinessUserController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	private static SimpleDateFormat DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss"); // 2015-08-25 00:00:00

	@Autowired
	private UnionPayService unionPayService;

	@Autowired
	private BspCommentMapper bspCommentMapper;

	@Autowired
	private BusinessService businessService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BspOrderproductsMapper bspOrderproductsMapper;

	@Autowired
	private BspOrderextMapper bspOrderextMapper;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper;

	@Autowired
	private BspCancelorderPicMapper bspCancelorderPicMapper;

	@Autowired
	private BspSendorderMapper bspSendorderMapper;

	@Autowired
	private BspPayorderMapper bspPayorderMapper;

	@Autowired
	private BspCancelorderReasonMapper bspCancelorderReasonMapper;

	@Autowired
	private PriceUpdateJobService priceUpdateJobService;

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	// 申请经销商用户页面
	@RequestMapping(value = "applyStoreUserPage")
	public String applyStoreUserPage(HttpSession httpSession, ModelMap m) {
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		m.put("uid", uid);
		m.put("userType", Constant.UserType_store);
		return "home/center/business/applyStoreUser";
	}

	// 申请工厂用户页面
	@RequestMapping(value = "applyFactoryUserPage")
	public String applyFactoryUserPage(HttpSession httpSession, ModelMap m) {
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		m.put("uid", uid);
		m.put("userType", Constant.UserType_factory);
		return "home/center/business/applyFactoryUser";
	}

	// 申请为经销商/工厂用户
	@ResponseBody
	@RequestMapping(value = "applyStoreUser")
	public ResponseData applyStoreUser(
			HttpSession httpSession,
			BspUserApply apply,
			@RequestParam(required = false, defaultValue = "1", value = "userType") Integer userType,
			ModelMap m) {
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (businessService.isApplyedStoreUser(uid, userType))
			return new ResponseData("failed", "已经申请过，请不要重复申请！");

		int result = businessService.applyStoreUser(uid, userType, apply);
		if (result > 0)
			return new ResponseData("success", "申请成功！");
		else
			return new ResponseData("error", "申请失败！");
	}

	// 门店用户显示门店信息
	@RequestMapping(value = "storeInfo")
	public ModelAndView storeInfo(HttpSession httpSession, ModelMap m) {
		loggerinfo.info("加载门店信息");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		ModelAndView mv = new ModelAndView();

		Integer id = businessService.getStoreId(uid, Constant.UserType_store);
		String returnUrl = "";
		if (id != null) {
			BspSaleaddress store = bspSaleaddressMapper.selectByPrimaryKey(id);
			if (store != null) {
				mv.addObject("store", store);
				Map<String, Object> addMap = bspAreaManagerService
						.querySaleaddressByid(id);
				mv.addObject("s", addMap);
				if (addMap.get("regionid") != null) {
					int regid = (Integer) addMap.get("regionid");
					mv.addObject("cid", regionsService.calCid(regid));
				}
				mv.addObject("wtlist",
						bspAreaManagerService.selWtListByPickpointId(id));
				returnUrl = "/home/center/business/storeInfo";
			} else {
				returnUrl = "redirect:/index.do";
			}
		} else {
			returnUrl = "redirect:/index.do";
		}
		mv.setViewName(returnUrl);
		return mv;
	}

	// 订单查询: 门店管理&工厂管理 (经销商订单 and 普通用户订单)
	@RequestMapping(value = "queryOrder")
	public String queryOrder(HttpSession httpSession,
			BusinessRequestParam param, ModelMap m) throws ParseException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		loggerinfo.info("查询订单");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		param.setUserid(uid);

		int storeid = 0;
		if (param.getStoreType().equals(1))
			storeid = businessService.getStoreId(uid, Constant.UserType_store);
		if (param.getStoreType().equals(2))
			storeid = businessService
					.getStoreId(uid, Constant.UserType_factory);
		param.setStoreid(storeid);

		if (param.getPage() == null) {
			Page<BspOrders> ps = new Page<BspOrders>(1, 10);
			param.setPage(ps);
		} else
			param.getPage().setPageSize(10);
		// 订单列表
		List<BspOrders> list = orderService.queryOrder(param);
		m.put("orderList", list);

		// 产品列表

		for (BspOrders b : list) {
			BspOrderproductsExample e = new BspOrderproductsExample();
			e.createCriteria().andOidEqualTo(b.getOid());
			List<BspOrderproducts> plist = bspOrderproductsMapper
					.selectByExample(e);
			b.setBoplist(plist);
		}

		// 订单状态
		List<String> orderStateList = new ArrayList<String>();
		for (BspOrders order : list) {
			orderStateList.add(EnumsUtil.valueOf(OrderStateEnum.class,
					order.getOrderstate()).getDesc());

		}
		m.put("orderStateList", orderStateList);

		Page<BspOrders> page = (Page<BspOrders>) list;
		long total = page.getTotal();
		String paramString = "storeid={0}&storeType={1}&roleid={2}&ordernum={3}&productname={4}&orderstate={5}&beginTime={6}&endTime={7}";
		paramString = StringHandler.formateString(paramString, String
				.valueOf(storeid), String.valueOf(param.getStoreType()), String
				.valueOf(param.getRoleid() == null ? "" : param.getRoleid()),
				String.valueOf(param.getOrdernum() == null ? "" : param
						.getOrdernum()),
				String.valueOf(param.getProductname() == null ? "" : param
						.getProductname()), String.valueOf(param
						.getOrderstate() == null ? "" : param.getOrderstate()),
				String.valueOf(param.getBeginTime() == null ? "" : param
						.getBeginTime()),
				String.valueOf(param.getEndTime() == null ? "" : param
						.getEndTime())

		);

		// storeid=54&storeType=2&roleid=1&ordernum=null&productname=null&orderstate=null&beginTime=null&endTime=null&pageNumber=2

		String newPath = "queryOrder.do?" + paramString + "&page.pageNum=";

		m.put("pageDiv", StringHandler.generatePageDiv(total, param.getPage()
				.getPageSize(), param.getPage().getPageNum(), newPath));
		m.put("param", param);

		return "home/center/business/queryResult";
	}

	// 工厂用户显示工厂信息
	@RequestMapping(value = "factoryInfo")
	public String factoryInfo(HttpSession httpSession, ModelMap m) {
		loggerinfo.info("加载工厂信息");
		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		int id = businessService.getStoreId(uid, Constant.UserType_factory);
		BspSaleaddress store = bspSaleaddressMapper.selectByPrimaryKey(id);
		m.put("store", store);
		Map<String, Object> addMap = bspAreaManagerService
				.querySaleaddressByid(id);
		m.put("s", addMap);
		if (addMap.get("regionid") != null) {
			int regid = (Integer) addMap.get("regionid");
			m.put("cid", regionsService.calCid(regid));
		}
		m.put("wtlist", bspAreaManagerService.selWtListByPickpointId(id));
		return "home/center/business/factoryInfo";
	}

	@ResponseBody
	@RequestMapping(value = "factoryInfoEdit")
	public ResponseData factoryInfoEdit(HttpServletRequest request,
			HttpSession httpSession, ModelMap m) {
		loggerinfo.info("编辑工厂、门店信息");

		int id = integer("id", request);
		String name = str("name", request);
		String address = str("address", request);
		String contacts = str("contacts", request);
		String tel = str("tel", request);
		String mobile = str("mobile", request);
		String workTime = str("work_time", request);
		String content = str("content", request);
		int regionid = integer("regionid", request);
		String regionname = str("regionname", request);
		int orderexpire = integer("orderexpire", request);

		if (!StringHandler.isPhone(tel)) {
			return new ResponseData("error", "请填写正确的电话号码，格式：0571-8888888！");
		} else if (!StringHandler.isMobile(mobile)) {
			return new ResponseData("error", "请填写正确的手机号码！");
		}

		List<BspWorktime> workTimeList = new ArrayList<BspWorktime>();
		int i = 0;
		while (true) {
			// String wtid = request.getParameter("workTimeList" + i + ".wtid");
			String wttype = request
					.getParameter("workTimeList" + i + ".wttype");
			String wtbegin = request.getParameter("workTimeList" + i
					+ ".wtbegin");
			String wtend = request.getParameter("workTimeList" + i + ".wtend");
			String wtweekbegin = request.getParameter("workTimeList" + i
					+ ".wtweekbegin");
			String wtweekend = request.getParameter("workTimeList" + i
					+ ".wtweekend");

			if (wtbegin == null || wtend == null || wtweekbegin == null
					|| wtweekend == null) {
				break;
			}
			Short iwttype = Short.valueOf(wttype);
			Short iwtweekbegin = Short.valueOf(wtweekbegin);
			Short iwtweekend = Short.valueOf(wtweekend);
			// Integer iwtid = Integer.valueOf(wtid);
			BspWorktime bwt = new BspWorktime();
			bwt.setWttype(iwttype);
			bwt.setWtbegin(wtbegin);
			bwt.setWtend(wtend);
			bwt.setWtweekbegin(iwtweekbegin);
			bwt.setWtweekend(iwtweekend);

			// bwt.setWtid(iwtid);

			workTimeList.add(bwt);

			i++;
		}

		// Integer id, Integer regionsId,
		// String regionsName, String name, String address, String tel,
		// String contacts, String workTime, String content, String type,
		// Integer placeid, String ncpkcorp, int regionid, String regionaname

		BspSaleaddress u = new BspSaleaddress();
		u.setId(id);
		u.setName(name);
		u.setAddress(address);
		u.setTel(tel);
		u.setMobile(mobile);
		u.setContacts(contacts);
		u.setWorktime(workTime);
		u.setContent(content);
		u.setType(null);
		u.setPlaceid(null);
		u.setPkcorp(null);
		u.setRegionid(regionid);
		u.setRegionname(regionname);
		u.setOrderexpire(orderexpire);

		boolean result = bspAreaManagerService.updateSealAdressOnly(u,
				workTimeList);

		if (result)
			return new ResponseData("success", "编辑信息成功！");
		else
			return new ResponseData("error", "编辑信息失败！");
	}

	/**
	 * step 0：订单状态查看
	 * 
	 */
	@RequestMapping("/OrderStateQuery")
	public String OrderStateQuery(HttpSession session,
			BusinessRequestParam param, ModelMap m) {

		Integer userId = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		Map map = orderService.OrderStateQuery(param.getOrdernum());
		m.put("orderinfo", map.get("orderinfo"));
		m.put("regions", map.get("regions"));
		m.put("boalist", map.get("boalist"));
		m.put("formate", map.get("formate"));
		m.put("boplist", map.get("boplist"));
		m.put("orderext", map.get("orderext"));
		m.put("invoice", map.get("invoice"));

		m.put("sender", map.get("sender"));
		m.put("payorder", map.get("payorder"));

		m.put("reason", map.get("reason"));// 订单取消原因
		m.put("cancelpicList", map.get("cancelpicList"));// 订单取消图片
		m.put("factoryMoney", map.get("factoryMoney"));// 工厂代收信息

		BspOrders order = (BspOrders) map.get("orderinfo");
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("param", param);

		BspCancelorderReason reason = (BspCancelorderReason) map.get("reason");

		if (reason != null) {
			// 由本人取消
			if (order.getUid().equals(reason.getUid()))
				m.put("CanceledBy", 1);
			else {
				BspUsersPermissionExample e = new BspUsersPermissionExample();
				e.createCriteria()
						.andUidEqualTo(reason.getUid())
						.andRoleidEqualTo(
								String.valueOf(Constant.UserType_factory));
				List<BspUsersPermission> list = bspUsersPermissionMapper
						.selectByExample(e);
				// 由工厂取消
				if (list != null && list.size() > 0)
					m.put("CanceledBy", 3);
				else
					// 由经销商取消
					m.put("CanceledBy", 2);
			}
		}

		return "home/center/business/OrderStateQuery";
	}

	/**
	 * step 1：订单确认
	 * 
	 */
	@RequestMapping("/OrderConfirm")
	@ResponseBody
	public ResponseData OrderConfirm(HttpSession session, Integer orderid,
			BusinessRequestParam param, String message, ModelMap m)
			throws Exception {
		ResponseData r = null;

		Byte state = (byte) OrderStateEnum.Confirmed.ordinal();
		Integer userId = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		// 所有订单不做验证码验证
		/*
		 * BspOrders order = orderService.getOrderById(orderid); if (order !=
		 * null && order.getUserrole().equals(Constant.UserType_client)) { r =
		 * orderService.verifyOrder(userId, orderid, message); if
		 * (!r.getState().equals("success")) return r; }
		 */

		boolean isok = orderService.processOrder(userId, orderid, state, 0,
				null, null, param.getStoreType());

		// 待发货
		state = (byte) OrderStateEnum.PreSend.ordinal();
		isok = orderService.processOrder(userId, orderid, state, 0, null, null,
				param.getStoreType());
		if (isok)
			r = new ResponseData("订单确认成功！");
		else
			r = new ResponseData("failed", "发生了一些错误！请联系管理人员！");

		m.put("orderStateName", EnumsUtil.valueOf(OrderStateEnum.class, state)
				.getDesc());

		return r;
	}

	/**
	 * step 2：使用订单号和验证码验证订单
	 * 
	 * @throws Exception
	 * 
	 */
	@ResponseBody
	@RequestMapping("/OrderVerify")
	public ResponseData OrderVerify(HttpSession session, ModelMap m,
			Integer orderid, String vercode) throws Exception {
		Byte state = (byte) OrderStateEnum.Verified.ordinal();
		Integer userId = (Integer) session
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		m.put("orderStateName", EnumsUtil.valueOf(OrderStateEnum.class, state)
				.getDesc());

		return orderService.verifyOrder(userId, orderid, vercode);
	}

	/**
	 * 订单支付页面
	 * 
	 */
	@RequestMapping("/payOrderPage")
	public String payOrderPage(BspPayorder payorder,
			BusinessRequestParam param, ModelMap m) throws Exception {
		m.put("payorder", payorder);

		Map map = orderService.OrderStateQuery(payorder.getOsn());
		m.put("orderinfo", map.get("orderinfo"));
		m.put("regions", map.get("regions"));
		m.put("boalist", map.get("boalist"));
		m.put("formate", map.get("formate"));
		m.put("boplist", map.get("boplist"));
		m.put("orderext", map.get("orderext"));
		m.put("invoice", map.get("invoice"));

		BspOrders order = (BspOrders) map.get("orderinfo");
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("param", param);

		return "home/center/business/payOrder";
	}

	/**
	 * 订单支付
	 * 
	 */
	@ResponseBody
	@RequestMapping("/payOrder")
	public JsonResponseData<Integer> payOrder(BspPayorder payorder,
			HttpServletRequest request) throws Exception {

		JsonResponseData<Integer> out = new JsonResponseData<Integer>();

		if (payorder.getPaytype() == -1) {
			out.setErrorMsg("请选择收款方式");
			out.setSuccess(false);
			return out;
		}

		if (payorder.getPayamount() == null) {
			out.setErrorMsg("请输入收款金额");
			out.setSuccess(false);
			return out;
		}

		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);

		// 插入收款信息 (已支付)
		payorder.setStatus(1);
		payorder.setUid(userId);
		payorder.setCreatetime(new Date());
		payorder.setPaytime(new Date());
		OrderPayTypeEnum paytype = EnumsUtil.valueOf(OrderPayTypeEnum.class,
				payorder.getPaytype().byteValue());
		payorder.setPaytypename(paytype.getDesc());
		int count = bspPayorderMapper.insertSelective(payorder);
		int payorderid = 0;
		if (count > 0) {
			payorderid = priceUpdateJobService.last_insert_id();
			out.setSuccess(true);
			out.setResult(payorderid);
			out.setErrorMsg("提交成功！");
		} else {
			out.setSuccess(false);
			out.setErrorMsg("保存交款信息出错！");
		}

		// 更新订单支付状态
		orderService.updateOrderPayState(payorder.getOid(), "1");

		return out;
	}

	/**
	 * step1： 在线交款页面
	 * 
	 */
	@RequestMapping("/payOnlinePage")
	public String payOnlinePage(String ordernum, ModelMap m) throws Exception {
		// 查询所有的工厂
		BspSaleaddressExample e = new BspSaleaddressExample();
		e.createCriteria().andTypeEqualTo("2");
		List<BspSaleaddress> list = bspSaleaddressMapper.selectByExample(e);
		m.put("compantList", list);
		return "home/center/business/payOnline";
	}

	/**
	 * step2： 在线交款提交
	 * 
	 */
	@ResponseBody
	@RequestMapping("/payOnlinePagePost")
	public JsonResponseData<Integer> payOnlinePagePost(BspPayorder payorder,
			HttpServletRequest request) throws Exception {

		JsonResponseData<Integer> out = new JsonResponseData<Integer>();

		if (payorder.getPaytype() == -1) {
			out.setErrorMsg("请选择收款方式");
			out.setSuccess(false);
			return out;
		}

		if (payorder.getPayamount() == null) {
			out.setErrorMsg("请输入收款金额");
			out.setSuccess(false);
			return out;
		}

		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);
		// 虚构订单号
		String orderNum = OrderNoGenerator.getOrderNo();

		// 插入收款信息 (未支付)
		payorder.setOsn(orderNum);
		payorder.setUid(userId);
		payorder.setCreatetime(new Date());
		OrderPayTypeEnum paytype = EnumsUtil.valueOf(OrderPayTypeEnum.class,
				payorder.getPaytype().byteValue());
		payorder.setPaytypename(paytype.getDesc());
		int count = bspPayorderMapper.insertSelective(payorder);
		int payorderid = 0;
		if (count > 0) {
			payorderid = priceUpdateJobService.last_insert_id();
			out.setSuccess(true);
			out.setResult(payorderid);
			out.setErrorMsg("提交成功！");
		} else {
			out.setSuccess(false);
			out.setErrorMsg("保存交款信息出错！");
		}

		return out;
	}

	/**
	 * step3：在线交款方式选择（目前只有银联）
	 * 
	 */
	@RequestMapping("/payOnlineSelect")
	public String payOnlineSelect(String payorderid, ModelMap m)
			throws Exception {

		m.put("payorderid", payorderid);
		return "home/center/business/payOnlineSelect";
	}

	/**
	 * step4: 收款,前往银联支付页面
	 * 
	 */
	@RequestMapping("/payOnline")
	public String payOnline(Integer payorderid, ModelMap m) throws Exception {

		BspPayorder payorder = bspPayorderMapper.selectByPrimaryKey(payorderid);

		String submitTime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(payorder.getCreatetime());

		String html = unionPayService.submitOrder(payorder.getOsn(),
				submitTime, payorder.getPayamount());
		m.put("html", html);
		return "home/order/jumpToUnionPay";
	}

	/**
	 * step5: 在线交款成功页面
	 * 
	 */
	@RequestMapping("/payOnlineSuccess")
	public String payOnlineSuccess(HttpServletRequest request, String osn,
			ModelMap m) throws Exception {
		m.put("ordernum", osn);
		return "home/center/business/payOnlineSuccess";
	}

	// 在线交款与NC交互
	@RequestMapping("/payOnlineNC")
	@ResponseBody
	public ResponseData payOnlineNC(HttpServletRequest request, String osn,
			ModelMap m) throws Exception {
		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);
		BspPayorderExample e = new BspPayorderExample();
		e.createCriteria().andOsnEqualTo(osn);
		List<BspPayorder> list = bspPayorderMapper.selectByExample(e);
		BspPayorder payorder = null;
		if (list != null && list.size() > 0)
			payorder = list.get(0);

		// 与NC交互
		NcRequestParam param = new NcRequestParam(DateFormat.format(payorder
				.getCreatetime()), payorder.getPayamount().toString(),
				businessService.getUserNcNumber(userId));

		// 设置交款方
		if (payorder.getNccompany() != null) {
			int id = Integer.valueOf(payorder.getNccompany());
			BspSaleaddress BspSaleaddress = bspSaleaddressMapper
					.selectByPrimaryKey(id);
			param.setPk_corp(BspSaleaddress.getPkcorp());
		}

		boolean ncinfo = NcInterface.isNCSuccess("D2", param);
		if (ncinfo)
			return new ResponseData("收款信息与NC交互成功！");
		else
			return new ResponseData("failed", "收款信息与NC交互失败！");
	}

	/**
	 * 发送订单页面
	 * 
	 */
	@RequestMapping("/OrderSendPage")
	public String OrderSendPage(BusinessRequestParam param, ModelMap m)
			throws Exception {

		Map map = orderService.OrderStateQuery(param.getOrdernum());
		m.put("orderinfo", map.get("orderinfo"));
		m.put("regions", map.get("regions"));
		m.put("boalist", map.get("boalist"));
		m.put("formate", map.get("formate"));
		m.put("boplist", map.get("boplist"));
		m.put("orderext", map.get("orderext"));
		m.put("invoice", map.get("invoice"));

		BspOrders order = (BspOrders) map.get("orderinfo");
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("param", param);

		return "home/center/business/sendOrder";
	}

	/**
	 * step 3：发货
	 * 
	 */
	@ResponseBody
	@RequestMapping("/OrderSend")
	public ResponseData OrderSend(HttpServletRequest request,
			BspSendorder sendorder, ModelMap m) throws Exception {
		ResponseData r = new ResponseData("");
		Byte state = (byte) OrderStateEnum.Sended.ordinal();
		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);

		// 取车牌号、发动机号
		String carno = sendorder.getCarno();
		String engineno = sendorder.getEngineno();
		BspOrderextExample e2 = new BspOrderextExample();
		e2.createCriteria().andOidEqualTo(sendorder.getOid());
		List<BspOrderext> clist = bspOrderextMapper.selectByExample(e2);
		for (BspOrderext b : clist) {
			if (b.getOexttype().equals("1"))
				carno = b.getOextvalue();
			if (b.getOexttype().equals("3"))
				engineno = b.getOextvalue();
		}

		// 校验发货信息 ：发动机号等
		if (sendorder.getEngineno() != null
				&& !sendorder.getEngineno().equals(engineno))
			return new ResponseData("failed", "发动机号检验失败！");
		// if (sendorder.getCarno() != null &&
		// !sendorder.getCarno().equals(carno))
		// return new ResponseData("failed", "车牌号检验失败！");

		int count = 0;
		String recordidList = str("recordidList", request);
		String sendCountList = str("sendCountList", request);
		String sendbatchList = str("sendbatchList", request);

		String[] recordidArray = recordidList.split(",");
		String[] sendCountArray = sendCountList.split(",");
		String[] sendbatchArray = sendbatchList.split(",");

		// 发货前信用检查 (只有经销商订单需要)
		BspOrders order = orderService.getOrderById(sendorder.getOid());
		if (order.getUserrole().equals(Constant.UserType_store)) {
			BspOrderproductsExample e = new BspOrderproductsExample();
			e.createCriteria().andOidEqualTo(sendorder.getOid());
			List<BspOrderproducts> list = bspOrderproductsMapper
					.selectByExample(e);
			if (list == null || list.size() != 1)
				return new ResponseData("failed", "经销商的订单产品数量只能为一个！");

			// 取NC客户号
			BspSaleaddress address = businessService.getStoreInfor(
					order.getUid(), Constant.UserType_store);
			String customercode = "";
			if (address != null)
				customercode = address.getPkcorp();

			NcRequestParam param = new NcRequestParam(order.getNcordernum(),
					customercode, sendCountArray[0],
					DateFormat.format(new Date()), order.getOrderamount()
							.toString(), list.get(0).getMarketprice()
							.toString(), sendorder.getCarno());

			boolean ncinfo = NcInterface.isNCSuccess("4C", param);

			if (ncinfo)
				r.setContent(r.getContent() + " 发货前信用检查成功！");
			else
				return new ResponseData("failed", "发货前信用检查失败！");
		}

		// 更新订单发货数量,发货批次
		for (String recordid : recordidArray) {
			BspOrderproducts record = new BspOrderproducts();
			record.setRecordid(Integer.valueOf(recordid));
			record.setSendcount(Double.valueOf(sendCountArray[count]));
			record.setSendbatch(sendbatchArray[count]);
			count += bspOrderproductsMapper.updateByPrimaryKeySelective(record);
		}
		if (count == recordidArray.length)
			r = new ResponseData("发货数量更新成功！");
		else
			return new ResponseData("failed", "发货数量更新错误！请联系管理人员！");

		// 保存发货信息和图片路径
		count = bspSendorderMapper.insertSelective(sendorder);
		if (count == 1)
			r.setContent(r.getContent() + " 保存发货信息成功！");
		else
			return new ResponseData("failed", "保存发货信息错误！请联系管理人员！");

		// 更新订单状态：已发货
		boolean isok = orderService.processOrder(userId,
				Integer.valueOf(sendorder.getOid()), state, 0, null, null);

		if (isok)
			r.setContent("发货成功！");
		else
			return new ResponseData("failed", "更新订单状态为已发货错误！请联系管理人员！");

		m.put("orderStateName", EnumsUtil.valueOf(OrderStateEnum.class, state)
				.getDesc());

		return r;
	}

	/**
	 * 订单评价页面
	 * 
	 */
	@RequestMapping("/OrderEvaluatePage")
	public String OrderEvaluatePage(BusinessRequestParam param, ModelMap m)
			throws Exception {

		Map map = orderService.OrderStateQuery(param.getOrdernum());
		m.put("orderinfo", map.get("orderinfo"));
		m.put("regions", map.get("regions"));
		m.put("boalist", map.get("boalist"));
		m.put("formate", map.get("formate"));
		m.put("boplist", map.get("boplist"));
		m.put("orderext", map.get("orderext"));
		m.put("invoice", map.get("invoice"));

		BspOrders order = (BspOrders) map.get("orderinfo");
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("param", param);

		return "home/center/business/evaluateOrder";
	}

	/**
	 * step 3：评价
	 * 
	 */
	@ResponseBody
	@RequestMapping("/OrderEvaluate")
	public ResponseData OrderEvaluate(HttpServletRequest request,
			BspComment record, ModelMap m) throws Exception {
		ResponseData r = new ResponseData("");
		Byte state = (byte) OrderStateEnum.Commented.ordinal();
		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);

		record.setCreatdate(new Date());
		record.setUid(userId);
		// 新增评价订单
		int count = 0;
		count = bspCommentMapper.insertSelective(record);
		if (count == 1)
			r.setContent(r.getContent() + " 1.评价成功！");
		else
			return new ResponseData("failed", "1.评价错误！请联系管理人员！");

		// 更新订单状态：已评价
		boolean isok = orderService.processOrder(userId, record.getOid(),
				state, 0, null, null);
		if (isok)
			r.setContent(r.getContent() + " 2.更新订单状态为已评价成功！");
		else
			return new ResponseData("failed", "2.更新订单状态为已评价错误！请联系管理人员！");

		m.put("orderStateName", EnumsUtil.valueOf(OrderStateEnum.class, state)
				.getDesc());
		return r;
	}

	// 订单评价成功页面，查看评价内容
	@RequestMapping("/OrderEvaluateSuccess")
	public String OrderEvaluateSuccess(Integer oid, ModelMap m)
			throws Exception {
		BspCommentExample e = new BspCommentExample();
		e.createCriteria().andOidEqualTo(oid);
		List<BspComment> list = bspCommentMapper.selectByExample(e);
		m.put("commentList", list);

		BspOrders order = orderService.getOrderById(oid);
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("orderinfo", order);

		return "home/center/business/evaluateOrderSuccess";
	}

	/**
	 * 取消订单页面
	 * 
	 */
	@RequestMapping("/OrderCancelPage")
	public String OrderCancelPage(String ordernum, ModelMap m) throws Exception {
		Map map = orderService.OrderStateQuery(ordernum);
		m.put("orderinfo", map.get("orderinfo"));
		m.put("regions", map.get("regions"));
		m.put("boalist", map.get("boalist"));
		m.put("formate", map.get("formate"));
		m.put("boplist", map.get("boplist"));
		m.put("orderext", map.get("orderext"));
		m.put("invoice", map.get("invoice"));

		BspOrders order = (BspOrders) map.get("orderinfo");
		m.put("orderStateName",
				EnumsUtil.valueOf(OrderStateEnum.class, order.getOrderstate())
						.getDesc());

		m.put("orderid", order.getOid());
		return "home/center/business/cancelOrder";
	}

	/**
	 * 取消订单
	 * 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/OrderCancel")
	public ResponseData OrderCancel(Integer orderid,
			BspCancelorderReason reason, BspCancelorderPic pic,
			HttpServletRequest request, ModelMap m) throws Exception {
		ResponseData r = new ResponseData("");
		Integer userId = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);

		// 取消订单前NC检查 (只有经销商订单需要)
		BspOrders order = orderService.getOrderById(reason.getOid());
		if (order.getUserrole().equals(Constant.UserType_store)) {
			BspOrderproductsExample e = new BspOrderproductsExample();
			e.createCriteria().andOidEqualTo(reason.getOid());
			List<BspOrderproducts> list = bspOrderproductsMapper
					.selectByExample(e);
			if (list == null || list.size() != 1)
				return new ResponseData("failed", "经销商的订单产品数量只能为一个！");
			BspOrderproducts bspOrderproducts = list.get(0);

			/*
			 * 销售订单，单据类型： 30 NcRequestParam(String ncOrdernum, String client,
			 * String orderdate, String carno, String remark, String invcode,
			 * String quantity, String price, String amount, String state)
			 */

			// 取车牌号、证件号、发动机号
			BspOrderextExample e2 = new BspOrderextExample();
			e2.createCriteria().andOidEqualTo(orderid).andOexttypeEqualTo("1");
			List<BspOrderext> clist = bspOrderextMapper.selectByExample(e2);
			String carno = "";
			if (clist != null && clist.size() > 0)
				carno = clist.get(0).getOextvalue();

			// 取NC存货编码
			// String psn = bspOrderproducts.getPsn();

			NcRequestParam param = new NcRequestParam(order.getNcordernum(),
					String.valueOf(userId), DateFormat.format(order
							.getAddtime()), carno, order.getRemark(),
					bspOrderproducts.getPsn(), String.valueOf(bspOrderproducts
							.getSendcount()), bspOrderproducts.getMarketprice()
							.toString(), order.getOrderamount().toString(), "1");

			boolean ncinfo = NcInterface.isNCSuccess("30", param);
			if (ncinfo)
				r.setContent(r.getContent() + " 1.取消订单前NC检查成功！");
			else
				return new ResponseData("failed", "1.取消订单前NC检查失败！");
		}

		// 保存取消原因
		reason.setCreatetime(new Date());
		reason.setUid(userId);
		bspCancelorderReasonMapper.insertSelective(reason);
		// 保存上传的图片
		int reasonid = priceUpdateJobService.last_insert_id();
		pic.setReasonid(reasonid);
		bspCancelorderPicMapper.insertSelective(pic);

		Byte state = (byte) OrderStateEnum.Cancel.ordinal();
		boolean isok = orderService.processOrder(0, orderid, state, 0, null,
				null);
		if (isok)
			r = new ResponseData("订单取消成功！");
		else
			r = new ResponseData("failed", "发生了一些错误！请联系管理人员！");
		return r;
	}

	// 订单取消成功页面
	@RequestMapping("/OrderCancelSuccess")
	public String OrderCancelSuccess(String ordernum, ModelMap m)
			throws Exception {
		m.put("ordernum", ordernum);
		return "home/center/business/cancelOrderSuccess";
	}

}
