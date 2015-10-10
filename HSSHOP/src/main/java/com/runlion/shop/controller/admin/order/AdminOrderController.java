package com.runlion.shop.controller.admin.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.date.DateStyle;
import com.runlion.shop.common.util.date.DateUtil;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.dao.BspInvoiceMapper;
import com.runlion.shop.dao.BspOrderextMapper;
import com.runlion.shop.dao.MessageMapper;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspInvoice;
import com.runlion.shop.entity.BspInvoiceExample;
import com.runlion.shop.entity.BspMessage;
import com.runlion.shop.entity.BspOrderactions;
import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspOrderextExample;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspUserdetails;
import com.runlion.shop.entity.BspUserranks;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.UserService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.OrdersStateVO;
import com.runlion.shop.vo.WebConfigVO;

/**
 * 后台订单相关controller
 * */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
	private static Logger logger = Logger.getLogger(AdminOrderController.class);
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	@Autowired
	private RegionsService resionsService;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	BspOrderextMapper bspOrderextMapper;
	@Autowired
	BspInvoiceMapper bspInvoiceMapper;

	/**
	 * 订单列表
	 * 
	 * @param pageNoStr
	 * @param pageSizeStr
	 * @param orderNum
	 * @param username
	 * @param consignee
	 * @param mobile
	 * @param orderstate
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(
			@RequestParam(value = "pageNum", required = false) String pageNoStr,
			@RequestParam(value = "numPerPage", required = false) String pageSizeStr,
			@RequestParam(value = "orderNum", required = false) String orderNum,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "consignee", required = false) String consignee,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "orderstate", required = false) String orderstate,
			Integer roleid) {
		ModelAndView mv = new ModelAndView();

		pageNoStr = pageNoStr == null || "".equals(pageNoStr) ? "1" : pageNoStr;
		pageSizeStr = pageSizeStr == null || "".equals(pageSizeStr) ? "30"
				: pageSizeStr;

		int pageNo = Integer.parseInt(pageNoStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		orderstate = StringHandler.isEmpty(orderstate) ? null : orderstate;

		int startNum = (pageNo - 1) * pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNum", orderNum);
		params.put("username", username);
		params.put("consignee", consignee);
		params.put("mobile", mobile);
		params.put("orderstate", orderstate);
		params.put("startNum", startNum);
		params.put("limitNum", pageSize);
		params.put("roleid", roleid);

		List<OrdersStateVO> ordersStateVO = new ArrayList<OrdersStateVO>();
		List<BspOrders> orderList = orderService.getOrderList(params);
		for (BspOrders bspOrders : orderList) {
			OrdersStateVO e = new OrdersStateVO();
			e.setBspOrders(bspOrders);
			e.setStatename(EnumsUtil.valueOf(OrderStateEnum.class,
					bspOrders.getOrderstate()).getDesc());
			ordersStateVO.add(e);
		}
		int count = orderService.getOrderCount(params);
		mv.addObject("status", "success");
		mv.addObject("orderList", ordersStateVO);
		mv.addObject("totalCount", count);
		mv.addObject("currentPage", pageNo);
		mv.addObject("numPerPage", pageSize);
		mv.addObject("pageNum", pageNo);

		List<OrderStateEnum> orderstateList = EnumsUtil
				.valueOfList(OrderStateEnum.class);
		mv.addObject("orderstateList", orderstateList);
		mv.addObject("orderNum", orderNum);
		mv.addObject("username", username);
		mv.addObject("consignee", consignee);
		mv.addObject("mobile", mobile);
		mv.addObject("orderstate", orderstate);
		mv.addObject("roleid", roleid);

		mv.setViewName("/admin/order/orderList");

		return mv;
	}

	/**
	 * 订单详情
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(int oid) {
		ModelAndView mv = new ModelAndView();
		BspOrders order = orderService.getOrderById(oid);
		List<BspOrderproducts> orderproductsList = orderService
				.getOrderproductsByOrderId(oid);
		List<BspOrderactions> orderactionsList = orderService
				.getOrderActionByOrderId(oid);

		List<BspOrderext> orderext = new ArrayList();
		BspInvoice invoice = null;
		if (order != null) {
			mv.addObject(
					"enumoderState",
					EnumsUtil.valueOf(OrderStateEnum.class,
							order.getOrderstate()).getDesc());

			// 区域
			BspRegions regions = resionsService.getResionsById(order
					.getRegionid());
			mv.addObject("regions", regions);
			// 用户
			BspUsers user = userService.getUsersById(order.getUid());
			mv.addObject("user", user);
			if (user != null) {
				// 用户等级
				BspUserranks userrank = userService.getUserranks(user
						.getUserrid());
				mv.addObject("userrank", userrank);
				// 用户详细信息
				BspUserdetails userdetails = userService.getUserdetails(user
						.getUid());
				mv.addObject("userdetails", userdetails);
			}

			// 1，2 表示自提，查询自提证件信息
			String pickWay = order.getShipsystemname();
			if ("1".equals(pickWay) || "2".equals(pickWay)) {
				BspOrderextExample boee = new BspOrderextExample();
				boee.createCriteria().andOidEqualTo(oid);
				orderext = bspOrderextMapper.selectByExample(boee);
			}
			//
			// 1，2 表示自提，查询自提证件信息
			String needInvoice = order.getNeedinv();
			if ("1".equals(needInvoice)) {
				BspInvoiceExample boee = new BspInvoiceExample();
				boee.createCriteria().andOidEqualTo(oid);
				List<BspInvoice> invlist = bspInvoiceMapper
						.selectByExample(boee);
				if (invlist != null && !invlist.isEmpty()) {
					invoice = invlist.get(0);
				}
			}
		}

		mv.addObject("order", order);
		mv.addObject("orderproductsList", orderproductsList);
		mv.addObject("orderactionsList", orderactionsList);
		mv.addObject("orderext", orderext);
		mv.addObject("invoice", invoice);

		mv.setViewName("/admin/order/orderDetail");
		return mv;
	}

	/**
	 * 处理订单
	 * 
	 * @param orderId
	 * @param optType
	 * @return
	 */
	@RequestMapping("operate")
	public ModelAndView operate(int orderId, int optType, String notpay) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderId", orderId);
		mv.addObject("optType", optType);
		mv.addObject("notpay", notpay);
		mv.setViewName("/admin/order/operateOrder");
		return mv;
	}

	/**
	 * 更新订单状态
	 * 
	 * @param session
	 * @param orderId
	 * @param optType
	 * @param shipNO
	 * @param actionDes
	 * @return
	 */
	@RequestMapping("processOrder")
	@ResponseBody
	public Map<String, String> processOrder(HttpSession session, int orderId,
			byte optType,
			@RequestParam(value = "shipNo", required = false) String shipNo,
			String actionDes) {

		BspAdminuser adminuser = (BspAdminuser) session
				.getAttribute("adminuser");
		Map<String, String> map = new HashMap<String, String>();
		try {
			orderService.processOrder(adminuser.getUid(), orderId, optType,
					(short) 1, actionDes, shipNo);
			map.put("statusCode", "200");
			map.put("message", "\u64cd\u4f5c\u6210\u529f");
			map.put("navTabId", "");
			map.put("rel", "");
			map.put("callbackType", "closeCurrent");
			map.put("forwardUrl", "order/detail.do?oid=" + orderId);
			map.put("confirmMsg", "");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("statusCode", "300");
			map.put("message", "操作错误");
		}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public StatusHtml delete(int oid) {
		StatusHtml statusHtml = new StatusHtml();
		if (orderService.deleteOrder(oid)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page44");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}
		return statusHtml;
	}

	/**
	 * 打印订单
	 * 
	 * @param orderId
	 * @param optType
	 * @return
	 */
	@RequestMapping("print")
	public ModelAndView print(int oid) {
		ModelAndView mv = new ModelAndView();
		BspOrders order = orderService.getOrderById(oid);
		List<BspOrderproducts> orderproductsList = orderService
				.getOrderproductsByOrderId(oid);
		List<BspOrderactions> orderactionsList = orderService
				.getOrderActionByOrderId(oid);

		if (order != null) {
			// 区域
			BspRegions regions = resionsService.getResionsById(order
					.getRegionid());
			mv.addObject("regions", regions);
			// 用户
			BspUsers user = userService.getUsersById(order.getUid());
			mv.addObject("user", user);
			if (user != null) {
				// 用户等级
				BspUserranks userrank = userService.getUserranks(user
						.getUserrid());
				mv.addObject("userrank", userrank);
				// 用户详细信息
				BspUserdetails userdetails = userService.getUserdetails(user
						.getUid());
				mv.addObject("userdetails", userdetails);
			}
		}

		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		mv.addObject("webinfor", configVo);
		mv.addObject("order", order);
		mv.addObject("orderproductsList", orderproductsList);
		mv.addObject("orderactionsList", orderactionsList);
		mv.addObject("date", new Date());
		mv.setViewName("/admin/order/orderPrint");
		return mv;
	}

	@RequestMapping("deleteSelect")
	@ResponseBody
	public StatusHtml deleteSelect(String ids) {
		StatusHtml statusHtml = new StatusHtml();
		try {
			orderService.deleteSelected(ids);
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page44");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("删除失败");
		}

		return statusHtml;
	}

	/**
	 * 验证码验证
	 * 
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/verification")
	@ResponseBody
	public Map<String, String> verification(String orderNum,
			String VerificationCode, String mobile, String textobj)
			throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		String msg = orderService.getPorderValidity(orderNum, VerificationCode);

		if ("1".equals(msg)) {
			BspMessage bspMessage = new BspMessage();
			bspMessage.setMobile(mobile);
			bspMessage.setMessage(textobj);
			bspMessage.setState("0");
			bspMessage.setValid("");
			bspMessage.setCode("");
			String message = "MsgId=&DeptId=&EmpId=&From=test&To="
					+ mobile
					+ "&Time="
					+ DateUtil.DateToString(new Date(),
							DateStyle.YYYY_MM_DD_HH_MM)
					+ "&Flag=1&Success=-1&Cardid=&msgtype=SMS&sendtype=Text&subject=&Body="
					+ textobj;

			messageMapper.insertMessage(bspMessage);
			msg = "验证成功！";
		}

		result.put("state", msg);
		result.put("content", msg);
		return result;
	}

	/**
	 * 验证码验证页面
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/getVerification")
	public String getVerification(int oid, ModelMap m) throws Exception {
		BspOrders order = orderService.getOrderById(oid);
		List<BspOrderproducts> orderproductsList = orderService
				.getOrderproductsByOrderId(oid);

		String body = "";

		for (BspOrderproducts bspOrderproducts : orderproductsList) {
			body += bspOrderproducts.getName() + "合计"
					+ bspOrderproducts.getBuycount() + "吨,";
		}
		String content = order.getConsignee() + "客户，您好！"
				+ DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM)
				+ "由" + "    " + "车从" + "    " + "公司提货" + body + "共计"
				+ order.getSurplusmoney() + "元。（水泥电商处：0579-88256171）";
		m.put("order", order);
		m.put("content", content);
		return "admin/message/verification";
	}

}
