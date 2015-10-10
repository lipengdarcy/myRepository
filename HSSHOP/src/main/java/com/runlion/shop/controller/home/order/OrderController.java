package com.runlion.shop.controller.home.order;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.HttpRequest;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrderproductsExample;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspShipaddresses;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.Cart;
import com.runlion.shop.entity.common.ClientRequestParam;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.CartService;
import com.runlion.shop.service.OrderProductPriceService;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.ShipAddressService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.CartSnapVO;
import com.runlion.shop.vo.OrderProductPrice;
import com.runlion.shop.vo.OrderVO;
import com.runlion.shop.vo.ProductExtPriceVO;

/**
 * 订单相关controller
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
	private static Logger logger = Logger.getLogger(OrderController.class);
	@Autowired
	private ShipAddressService shipAddressService;

	@Autowired
	private BspOrderproductsMapper bspOrderproductsMapper;
	@Autowired
	private RegionsService regionsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderProductPriceService orderProductPriceService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	BspAreaManagerService bspAreaManagerService;

	@RequestMapping("/confirmorder")
	public ModelAndView confirmorder(HttpSession httpSession,
			String selectedCartItemKeyList, String pickway) throws Exception {

		ModelAndView mv = new ModelAndView();
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId != null) {
			// 默认收货地址
			BspShipaddresses defaultAddress = shipAddressService
					.getDefaultAddress(userId);
			// 区域 215236
			BspRegions regions = regionsService
					.getResionsByAddress(defaultAddress);

			// 工厂
			List<BspSaleaddress> factoryAddress = orderService
					.getAddressByList(defaultAddress, 2);
			// 门店
			List<BspSaleaddress> storeAddress = orderService.getAddressByList(
					defaultAddress, 1);

			mv.addObject("pickway", pickway);
			List<CartSnapVO> cartSnapList = cartService
					.getCartSnapByUserId(userId);

			// 选择选中的产品
			List<CartSnapVO> selectOrderProductList = orderService
					.selectProduct(selectedCartItemKeyList, cartSnapList);

			// 获取产品价格
			OrderProductPrice orderProductPrice = orderProductPriceService
					.portionPrice(selectOrderProductList, regions, 1);

			mv.addObject("defaultAddress", defaultAddress);
			mv.addObject("regions", regions);
			mv.addObject("orderProductList", selectOrderProductList);
			mv.addObject("productPrice",
					orderProductPrice.getTotalProductPrice());
			mv.addObject("shipFee", orderProductPrice.getShipFee());
			mv.addObject("handlingCost", orderProductPrice.getHandlingCost());
			mv.addObject("count", orderProductPrice.getTotalCount());
			mv.addObject("orderPrice", orderProductPrice.getTotalOrderPrice());
			mv.setViewName("/home/order/confirm");
			return mv;
		} else {
			return new ModelAndView("redirect:/account/login.do");
		}
	}

	/**
	 * 获取收货地址
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/shipaddresslist")
	@ResponseBody
	public HashMap<String, Object> shipaddresslist(HttpSession httpSession,
			int pickId) {
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		HashMap<String, Object> mapinfo = new HashMap<String, Object>();
		if (userId != null) {
			List<BspShipaddresses> addressList = shipAddressService
					.getShipAddress(userId);
			if (addressList != null) {
				HashMap<String, Object> mapinfo1 = new HashMap<String, Object>();
				mapinfo.put("state", "success");
				mapinfo.put("pickId", pickId);
				mapinfo1.put("count", addressList.size());

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (BspShipaddresses address : addressList) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 区域
					BspRegions regions = regionsService
							.getResionsByAddress(address);
					if (regions != null) {
						map.put("regionId", regions.getRegionid());
						map.put("saId", address.getSaid());
						map.put("user", address.getConsignee()
								+ "&nbsp;&nbsp;&nbsp;" + address.getMobile());
						map.put("address",
								regions.getProvincename() + "&nbsp;"
										+ regions.getCityname() + "&nbsp;"
										+ regions.getCountyname() + "&nbsp;"
										+ regions.getStreetname() + "&nbsp;"
										+ regions.getName() + "&nbsp;"
										+ address.getAddress());
						list.add(map);
						mapinfo1.put("list", list);
					}
				}
				mapinfo.put("content", mapinfo1);
			}

		} else {
			mapinfo.put("state", "NOT_LOGIN");
			mapinfo.put("content", "请先登录");
		}
		return mapinfo;
	}

	@RequestMapping("/getStoreAddress")
	@ResponseBody
	public HashMap<String, Object> getStoreAddress(int regionsId,
			String selectedCartItemKeyList) throws Exception {
		HashMap<String, Object> mapinfo = new HashMap<String, Object>();

		Map<String, List<BspSaleaddress>> map = orderService
				.getAddressByRid(regionsId);
		if (map != null) {
			for (Entry<String, List<BspSaleaddress>> entry : map.entrySet()) {
				List<BspSaleaddress> value = entry.getValue();
				String key = entry.getKey();
				if (OrderService.FACTORY.equals(key)) {
					mapinfo.put("factory", value);
				}
				if (OrderService.STORE.equals(key)) {
					mapinfo.put("store", value);
				}
				mapinfo.put("state", "success");
			}
		}

		return mapinfo;
	}

	/**
	 * 获取配置地址对应的价格
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/getShipAddressInfo")
	@ResponseBody
	public HashMap<String, Object> getShipAddressInfo(
			HttpSession httpSession,
			@RequestParam(value = "regionId", required = false) int regionId,
			@RequestParam(value = "productId", required = false) String productId,
			@RequestParam(value = "pickId", required = false) int pickId) {

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		HashMap<String, Object> mapinfo = new HashMap<String, Object>();
		if (userId != null) {
			List<CartSnapVO> cartSnapList = cartService
					.getCartSnapByUserId(userId);

			// 选择选中的产品
			List<CartSnapVO> selectOrderProductList = orderService
					.selectProduct(productId, cartSnapList);

			BspRegions regions = new BspRegions();
			regions.setRegionid(regionId);

			// OrderProductPrice orderProductPrice = orderProductPriceService
			// .caculatePrice(selectOrderProductList, regions);
			OrderProductPrice orderProductPrice = orderProductPriceService
					.portionPrice(selectOrderProductList, regions, pickId);
			// 运费(总)
			mapinfo.put("price", orderProductPrice.getShipFee().toString());
			// 装卸费（总）
			mapinfo.put("handlingCost", orderProductPrice.getHandlingCost()
					.toString());

			mapinfo.put("totalProductPrice", orderProductPrice
					.getTotalProductPrice().toString());

			mapinfo.put("totalOrderPrice", orderProductPrice
					.getTotalOrderPrice().toString());

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			selectOrderProductList = orderProductPrice.getCartSnapList();
			for (CartSnapVO csv : selectOrderProductList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Pid", csv.getPid());
				map.put("MarketPrice", csv.getMarketprice());
				map.put("freight", csv.getFreight());
				map.put("carry", csv.getCarry());
				map.put("quantityunitid", csv.getQuantityunitid());
				map.put("originalPrice", csv.getOriginalPrice());
				map.put("itemTotalMny", csv.getItemTotalMny());
				list.add(map);
			}
			mapinfo.put("cartproduct", list);

		} else {
			mapinfo.put("state", "NOT_LOGIN");
			mapinfo.put("content", "请先登录");
		}

		return mapinfo;
	}

	/**
	 * 
	 * 提交订单
	 * 
	 * @param httpSession
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/submitOrder")
	@ResponseBody
	public Map<String, String> submitOrder(HttpSession httpSession,
			HttpServletRequest request, OrderVO order) throws ParseException {
		Map<String, String> map = new HashMap<String, String>();

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		if (userId == null) {
			map.put("state", "NOT_LOGIN");
			map.put("content", "请先登录");
		} else {

			StringBuffer content = new StringBuffer();
			if (order.getSaId() == null) {
				content.append("收货地址为空！").append(",");
			}

			if (order.getProductids() == null) {
				content.append("商品为空").append(",");
			}

			if (order.getDate() == null) {
				content.append("配送时间为空").append(",");
			}

			if (content.length() > 0) {
				content.deleteCharAt(content.length() - 1);
				map.put("state", "error");

				map.put("content", content.toString());
				return map;
			}

			try {
				// 5位随机验证码
				// String verificationCode = OrderNoGenerator
				// .getVerificationCode();
				String ip = HttpRequest.getRemortIP(request);
				order.setIp(ip);
				map = orderService.placedOrder(userId, order, request);
				// 更新购物车数量
				int cartCount = cartService.getCartCountByUserId(userId);
				httpSession.setAttribute("cartCount", cartCount);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				map.put("state", "error");
				map.put("content", "提交失败");
			}
		}

		return map;
	}

	/**
	 * 
	 * 提交订单
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/submitOrderSuccess")
	public String submitOrderSuccess(HttpServletRequest request) {
		String payModel = request.getParameter("paymodel");
		String orderid = request.getParameter("orderid");
		String ordernum = request.getParameter("ordernum");
		String pickName = request.getParameter("pickName");
		String pickId = request.getParameter("pickId");
		String surmny = request.getParameter("surmny");
		request.setAttribute("paymodel", payModel);
		request.setAttribute("orderid", orderid);
		request.setAttribute("ordernum", ordernum);
		request.setAttribute("pickName", pickName);
		request.setAttribute("pickId", pickId);
		request.setAttribute("surmny", surmny);
		return "home/order/submitresult";
	}

	/**
	 * 立即购买
	 * 
	 * @param pid
	 * @param buyCount
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addproductToBuy")
	public ModelAndView addProductToBuy(int pid, int buyCount,
			HttpSession httpSession) throws Exception {
		ModelAndView mv = new ModelAndView();
		String msg = "";
		String state = "fail";
		Cart cart = new Cart();
		cart.setCount(buyCount);
		cart.setProductId(pid);
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId != null) {
			cart.setUserId(userId);
			cartService.insertOrUpdate(cart);
			// 默认收货地址
			BspShipaddresses defaultAddress = shipAddressService
					.getDefaultAddress(userId);
			// 区域 215236
			BspRegions regions = regionsService
					.getResionsByAddress(defaultAddress);
			// 自提地址
			// 工厂
			List<BspSaleaddress> factoryAddress = orderService
					.getAddressByList(defaultAddress, 2);
			// 门店
			List<BspSaleaddress> storeAddress = orderService.getAddressByList(
					defaultAddress, 1);
			List<BspSaleaddress> factorylist = new ArrayList<BspSaleaddress>();
			List<BspSaleaddress> storelist = new ArrayList<BspSaleaddress>();
			if (factoryAddress != null) {
				for (int i = 0; i < factoryAddress.size(); i++) {
					if (OrderService.FACTORY.equals(factoryAddress.get(i)
							.getType())) {
						factorylist.add(factoryAddress.get(i));
					}
				}
			}
			if (storeAddress != null) {
				for (int i = 0; i < storeAddress.size(); i++) {
					if (OrderService.FACTORY.equals(storeAddress.get(i)
							.getType())) {
						storelist.add(storeAddress.get(i));
					}
				}
			}

			// BspSaleaddress storeBean = bspProductsService.getProductAddress(
			// defaultAddress.getRegionid(), -1, 1);
			mv.addObject("store", storelist);
			mv.addObject("factory", factorylist);

			List<CartSnapVO> cartSnapList = cartService
					.getCartSnapByUserId(userId);

			// 选择选中的产品
			List<CartSnapVO> selectOrderProductList = orderService
					.selectProduct(Integer.toString(pid), cartSnapList);

			// 获取产品价格
			OrderProductPrice orderProductPrice = orderProductPriceService
					.caculatePrice(selectOrderProductList, regions);

			mv.addObject("defaultAddress", defaultAddress);
			mv.addObject("regions", regions);
			mv.addObject("orderProductList", selectOrderProductList);
			mv.addObject("productPrice",
					orderProductPrice.getTotalProductPrice());
			mv.addObject("shipFee", orderProductPrice.getShipFee());
			mv.addObject("handlingCost", orderProductPrice.getHandlingCost());
			mv.addObject("count", orderProductPrice.getTotalCount());
			mv.addObject("orderPrice", orderProductPrice.getTotalOrderPrice());
			mv.setViewName("/home/order/confirm");
			return mv;
		} else {
			return new ModelAndView("redirect:/account/login.do");
		}
	}

	/**
	 * 根据地点日期产生工作时间
	 * */
	@RequestMapping("/worktime")
	@ResponseBody
	public List<BspWorktime> worktime(int pid, String date) {
		List<BspWorktime> selectBspWorktime = orderService.selectByWorkTime(
				pid, date);
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		map.put("begin", selectBspWorktime.get(0).getWtbegin());
		SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm");
		if (now.equals(date)) {
			map.put("begin", sdf1.format(new Date()));
			selectBspWorktime.get(0).setWtbegin(map.get("begin"));
		}
		map.put("end", selectBspWorktime.get(0).getWtend());
		return selectBspWorktime;
	}

	/**
	 * 根据Id查找物品
	 * */
	@RequestMapping("/countProduct")
	@ResponseBody
	public String countProduct(int pid) {
		String result = "";
		int count = orderService.countProduct(pid);
		if (count == 0) {
			result = "no";
		}
		return result;
	}

	/**
	 * 查找区域可以匹配到的物品id； 返回格式为字符串形如 1,8,0
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getRegionProducts")
	@ResponseBody
	public Map<String, Object> getRegionProducts(int regionId, String productIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = orderService.getRegionProductIds(regionId, productIds);
		return map;
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

		// mv.addObject("orderProductList", selectOrderProductList);

		mv.setViewName("/home/order/invoiceTab");
		return mv;
	}

	/**
	 * 
	 * @param regionId
	 * @param productIds
	 * @return
	 */
	@RequestMapping("/getProductExtPrice")
	@ResponseBody
	public Map<String, Object> getProductExtPrice(int regionId,
			String productIds) {
		Map<String, Object> map = new HashMap();
		String[] idArr = productIds.split(",");
		for (int i = 0; i < idArr.length; i++) {
			String id = idArr[i];
			Integer iid = Integer.valueOf(id);
			BspProductsregions proExtRegion = bspAreaManagerService
					.getYesProidProregionsWithNotUp(regionId, iid);
			if (proExtRegion == null) {
				proExtRegion = bspAreaManagerService
						.getYesProidProregionsWithNotUp(
								Integer.valueOf(regionId), -1);
			}
			// 实验性质，正式移除该行
			BspProductsregions proExtRegion2 = bspAreaManagerService
					.getYesProidProregionsWithNotUp(Integer.valueOf(regionId),
							-1);
			//
			List<BspProregionextprice> extpriceList = bspAreaManagerService
					.getBspProregionextpriceList(proExtRegion2.getId());
			ProductExtPriceVO extPrice = new ProductExtPriceVO();
			for (int ei = 0; ei < extpriceList.size(); ei++) {
				BspProregionextprice price = extpriceList.get(ei);
				String type = price.getType();
				if (type.equals("1")) {
					extPrice.setStoreExtPrice(price.getValue());
				} else if (type.equals("2")) {
					extPrice.setFacExtPrice(price.getValue());
				} else if (type.equals("3")) {
					extPrice.setShiptoExtPrice(price.getValue());
				}
			}
			map.put("extPrice" + id, extPrice);
		}
		//

		return map;
	}

	// 订单查询: 普通用户订单
	@RequestMapping(value = "queryOrder")
	public String queryOrder(HttpSession httpSession, ClientRequestParam param,
			ModelMap m) throws ParseException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		int uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		param.setUserid(uid);

		int storeid = 0;

		if (param.getPage() == null) {
			Page<BspOrders> ps = new Page<BspOrders>(1,10);
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
		String paramString = "ordernum={0}&productname={1}&orderstate={2}&beginTime={3}&endTime={4}";
		paramString = StringHandler.formateString(paramString,
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

		String newPath = "queryOrder.do?" + paramString + "&page.pageNum=";

		m.put("pageDiv", StringHandler.generatePageDiv(total, param.getPage()
				.getPageSize(), param.getPage().getPageNum(), newPath));
		m.put("param", param);

		return "home/center/orderList";
	}

}
