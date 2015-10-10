package com.runlion.shop.controller.business;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.HttpRequest;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspUsercarinfor;
import com.runlion.shop.service.BspNcenterinforService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.BspUserCarService;
import com.runlion.shop.service.CartService;
import com.runlion.shop.service.OrderProductPriceService;
import com.runlion.shop.service.ShipAddressService;
import com.runlion.shop.service.UserService;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.service.business.DealOrderService;
import com.runlion.shop.service.business.DealOrderServiceHelper;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.DealOrderVO;

/**
 * 订单相关controller
 * */
@Controller
@RequestMapping("/salerorder")
public class SalerOrderController {
	private static Logger logger = Logger.getLogger(SalerOrderController.class);
	@Autowired
	private ShipAddressService shipAddressService;
	@Autowired
	private RegionsService regionsService;

	@Autowired
	private DealOrderService dealOrderService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderProductPriceService orderProductPriceService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private UserService userService;

	@Autowired
	private BusinessService businessService;

	@Autowired
	BspNcenterinforService bspNcenterinforService;
	@Autowired
	BspUserCarService bspUserCarService;

	/**
	 * 
	 * 经销商提交订单，是否需要一个表示该订单为经销商的订单的字段
	 * 
	 * @param httpSession
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/submitOrder")
	@ResponseBody
	public Map<String, Object> submitOrder(HttpSession httpSession,
			HttpServletRequest request, DealOrderVO delOrder, String besttime)
			throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		//
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId == null) {
			map.put("state", "NOT_LOGIN");
			map.put("content", "请先登录");
		} else {
			// 对提交的信息进行验证
			StringBuffer content = new StringBuffer();
			//
			if (content.length() > 0) {
				content.deleteCharAt(content.length() - 1);
				map.put("state", "error");
				map.put("content", content.toString());

				return map;
			}
			try {
				String ip = HttpRequest.getRemortIP(request);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(besttime);
				delOrder.getOrder().setExpireTime(date);
				delOrder.getOrder().setIp(ip);
				delOrder.getOrder().setUid(userId);

				// 修改车辆使用时间,更新成功或者失败都要去保存订单信息
				try {
					BspUsercarinfor buc = this.getSelUsercarinfor(delOrder,
							userId);
					if (buc != null) {
						bspUserCarService.upUserCarinforLastuptime(buc);
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
				// 保存订单
				map = dealOrderService.saveDealOrder(delOrder, httpSession);
				if (!map.get("state").equals("error")) {
					map.put("state", "waiting");
					map.put("sessionName",
							DealOrderServiceHelper.ORDER_NOW_STATE
									+ delOrder.getOrder().getOid());
					map.put("content", "submitDealOrderOK.do?paymodel="
							+ delOrder.getOrder().getPaymode() + "&orderid="
							+ delOrder.getOrder().getOid() + "&ordernum="
							+ delOrder.getOrder().getOsn());
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				map.put("state", "error");
				map.put("content", "提交失败:" + e.getMessage());
			}
		}
		return map;
	}

	@RequestMapping("/getSaveOrderState")
	@ResponseBody
	public Map<String, Object> getSaveOrderState(HttpSession httpSession,
			HttpServletRequest request, String sessionName)
			throws ParseException {
		//
		Map<String, Object> rsmap = (Map<String, Object>) httpSession
				.getAttribute(sessionName);
		//
		if (rsmap == null) {
			rsmap = new HashMap();
			rsmap.put("state", "error");
			rsmap.put("content", "没有找到订单的状态信息！");
		} else if (!rsmap.get("state").equals("waiting")) {
			httpSession.removeAttribute(sessionName);
		}
		return rsmap;
	}

	/**
	 * 
	 * 提交订单
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/submitDealOrderOK")
	public String submitOrderSuccess(HttpServletRequest request) {
		String payModel = request.getParameter("paymodel");
		String orderid = request.getParameter("orderid");
		String ordernum = request.getParameter("ordernum");
		request.setAttribute("paymodel", payModel);
		request.setAttribute("orderid", orderid);
		request.setAttribute("ordernum", ordernum);
		return "home/order/dealsubmitresult";
	}

	/**
	 * 获取发票模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getInvoiceTmp")
	public ModelAndView getInvoiceTmp() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/home/order/invoiceTab");
		return mv;
	}

	/**
	 * 经销商下单处理页
	 * 
	 * @param httpSession
	 * @param selectedCartItemKeyList
	 * @param pickway
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/confirmorder")
	public ModelAndView dealconfirmorder(HttpSession httpSession, Integer pid,
			Double buycount, String pickway) throws Exception {
		ModelAndView mv = new ModelAndView();
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId != null) {
			BspProducts entity = bspProductsService.selectByPrimaryKey(pid);
			if (entity == null) {
				mv.addObject("msg", "该信息不存在，请重新访问！");
				mv.setViewName("error/noSecurity");
				return mv;
			}
			// 查找用户关联的门店的信息
			BspSaleaddress salerinfor = businessService.getStoreInfor(userId,
					Constant.UserType_store);
			mv.addObject("salerinfor", salerinfor);
			//
			List<BspNcenterinfor> ncelist = bspNcenterinforService
					.getBspNcenterinfor(entity.getNcpronum(),
							salerinfor.getPkcorp());
			BspNcenterinfor nce = null;
			if (ncelist != null && !ncelist.isEmpty()) {
				nce = ncelist.get(0);
				BigDecimal price = BigDecimal.ZERO;
				try {
					price = new BigDecimal(nce.getPrice());
				} catch (Exception e) {

				}
				entity.setMarketprice(price);
			}
			//
			mv.addObject("entity", entity);
			// productPrice
			BigDecimal count = new BigDecimal(buycount);
			entity.setSalecount(buycount);
			//
			mv.addObject("pickway", pickway);
			//
			mv.addObject("count", buycount);
			mv.setViewName("/home/order/dealConfirm");
			return mv;
		} else {
			return new ModelAndView("redirect:/account/login.do");
		}
	}

	private BspUsercarinfor getSelUsercarinfor(DealOrderVO delOrder,
			Integer userId) {
		BspUsercarinfor buc = null;
		List<BspOrderext> extlist = delOrder.getOrderExtList();
		String carNum = delOrder.getOrder().getRemark();
		String motoNum = delOrder.getOrder().getRemark();
		if (extlist != null) {
			for (int ei = 0; ei < extlist.size(); ei++) {
				BspOrderext extinfor = extlist.get(ei);
				if ("1".equals(extinfor.getOexttype())) {
					carNum = extinfor.getOextvalue();
				}
				if ("3".equals(extinfor.getOexttype())) {
					motoNum = extinfor.getOextvalue();
				}
			}
			buc = new BspUsercarinfor();
			buc.setUserid(userId);
			buc.setCarnum(carNum);
			buc.setMotornum(motoNum);
			bspUserCarService.upUserCarinforLastuptime(buc);
		}
		return buc;

	}

	@RequestMapping("/getFactoryInfor")
	@ResponseBody
	public Map<String, Object> getFactoryInfor(HttpSession httpSession,
			HttpServletRequest request, String pids) throws ParseException {
		//
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap = dealOrderService.getProductFactoryIds(pids);
		return rsmap;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 忽略字段绑定异常
		// binder.setIgnoreInvalidFields(true);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "insertTime",
				new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class, "modifyTime",
				new CustomDateEditor(format, true));

	}
}
