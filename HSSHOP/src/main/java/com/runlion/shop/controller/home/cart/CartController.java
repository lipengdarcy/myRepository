package com.runlion.shop.controller.home.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.Cart;
import com.runlion.shop.service.CartService;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.vo.CartSnapVO;

/**
 * 购物车相关controller
 * */
@Controller
@RequestMapping("/cart")
public class CartController {
	// 购物车
	public static final String CART_TYPE = "0";
	// 购物车快照
	public static final String CART_SNAP = "1";

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	/**
	 * 购物车页面
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpSession httpSession) {
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		ModelAndView mv = new ModelAndView();
		// 获取cookie中的区域id
		Cookie[] cookie = request.getCookies();
		int areaId = cartService.getAreaIdFromCookies(cookie);

		String html = "";
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		if (userId != null) {
			List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);
			Map<Integer, Boolean> overStockMap = new HashMap<Integer, Boolean>();
			html = cartService.createCartHtml(list, areaId, realPath, null,
					overStockMap);
		} else {
			html = "<ul id=\"noBuy\">\n" + "\t  <li>购物车内暂时没有商品，<a href="
					+ realPath + "/account/login.do>登录</a>后，将显示您之前加入的商品</li>\n"
					+ "\t  <li><a href=\"" + realPath
					+ "/index.do\">去首页</a>挑选喜欢的商品</li>\n" + "</ul>";
		}

		mv.setViewName("/home/cart/index");
		mv.addObject("html", html);
		return mv;
	}

	/**
	 * 购物车商品全选
	 * 
	 * @param m
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/selectallcartitem")
	public String selectallcartitem(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		// 获取cookie中的区域id
		Cookie[] cookie = request.getCookies();
		int areaId = cartService.getAreaIdFromCookies(cookie);

		String html = "";
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		if (userId != null) {
			List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);
			Map<Integer, Boolean> overStockMap = new HashMap<Integer, Boolean>();
			html = cartService.createCartHtml(list, areaId, realPath, null,
					overStockMap);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(html);
		return null;
	}

	/**
	 * 选择或取消选择商品
	 * 
	 * @param m
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cancelorselectcartitem")
	public String cancelOrSelectCartItem(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession,
			String selectedCartItemKeyList) throws IOException {

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		List<Integer> pidList = new ArrayList<Integer>();
		String[] pids = null;
		if (selectedCartItemKeyList != null
				&& !"_".equals(selectedCartItemKeyList)
				&& selectedCartItemKeyList.length() > 0) {
			pids = selectedCartItemKeyList.split(",");
		}

		// 获取cookie中的区域id
		Cookie[] cookie = request.getCookies();
		int areaId = cartService.getAreaIdFromCookies(cookie);

		String html = "";
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		if (userId != null) {
			List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);

			if (pids != null) {
				for (String pid : pids) {
					pidList.add(Integer.parseInt(pid));
				}
			}
			Map<Integer, Boolean> overStockMap = new HashMap<Integer, Boolean>();
			html = cartService.createCartHtml(list, areaId, realPath, pidList,
					overStockMap);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(html);
		return null;
	}

	/**
	 * 添加产品到购物车
	 * 
	 * @param pid
	 * @param buyCount
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/addproduct")
	@ResponseBody
	public String addproduct(int pid, int buyCount, String areaId,
			HttpSession httpSession) {
		String msg = "";
		String state = "";

		Cart cart = new Cart();
		cart.setCount(buyCount);
		cart.setProductId(pid);
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		if (userId != null) {
			boolean canAdd = true;
			if (areaId != null && !areaId.trim().equals("")) {
				int regionId = Integer.valueOf(areaId);
				Map rsMap = orderService
						.getRegionProductIds(regionId, pid + "");
				List mdSaleaddrList = (List) rsMap.get("mdSaleaddrList");
				List gcSaleaddrList = (List) rsMap.get("gcSaleaddrList");
				if (mdSaleaddrList.size() == 0 && gcSaleaddrList.size() == 0) {
					msg = "商品添加到购物车失败！该区域没有工厂和门店。";
					state = "{'status':'fail','msg':'商品添加到购物车失败！该区域没有工厂和门店。'}";
					canAdd = false;
				}
			}
			if (canAdd) {
				cart.setUserId(userId);
				cartService.insertOrUpdate(cart);
				int cartCount = cartService.getCartCountByUserId(userId);
				httpSession.setAttribute("cartCount", cartCount);
				msg = "商品成功添加到购物车！";
				state = "{'status':'ok','pid':" + pid + "}";
			}

		} else {
			msg = "请先登录！";
			state = "{'status':'fail','msg':'请先登录！'}";
		}

		// mv.setViewName("/home/cart/addsuccess");
		// mv.addObject("pid", pid);
		// mv.addObject("state", state);
		// mv.addObject("msg", msg);

		return state;
	}

	/**
	 * 添加产品到购物车成功跳转
	 * 
	 * @return
	 */
	@RequestMapping("/addproductSucess")
	public ModelAndView addproductSucess(String pid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/home/cart/addsuccess");
		mv.addObject("pid", pid);
		mv.addObject("state", "success");
		mv.addObject("msg", "商品成功添加到购物车！");
		return mv;
	}

	/**
	 * 购物车快照
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/snap")
	public String snap(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession, int areaId)
			throws IOException {

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		if (userId != null) {
			List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);
			String realPath = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath();
			String html = cartService
					.createCartSnapHtml(list, areaId, realPath);

			int cartCount = cartService.getCartCountByUserId(userId);
			html = html + "<input type='hidden' id='productCount' value='"
					+ cartCount + "'/>";
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(html);
		}

		return null;

	}

	/**
	 * 删除购物车产品
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/delpruduct")
	public String delpruduct(HttpServletResponse response,
			HttpServletRequest request, HttpSession httpSession, int pid,
			String pos, String selectedCartItemKeyList) throws IOException {

		// 获取cookie中的区域id
		Cookie[] cookie = request.getCookies();
		int areaId = cartService.getAreaIdFromCookies(cookie);
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(pid);
		cartService.deleteCartByUserIdAndPid(cart);
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);
		String html = "";
		// 在购物车中删除产品
		if (CART_TYPE.equals(pos)) {
			Map<Integer, Boolean> overStockMap = new HashMap<Integer, Boolean>();

			html = cartService.createCartHtml(list, areaId, realPath, null,
					overStockMap);
			// 在购物车快照中删除
		} else {
			html = cartService.createCartSnapHtml(list, areaId, realPath);
		}

		int cartCount = cartService.getCartCountByUserId(userId);
		html = html + "<input type='hidden' id='productCount' value='"
				+ cartCount + "'/>";
		response.getWriter().print(html);
		return null;
	}

	/**
	 * 清空购物车
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/clear")
	public String clear(HttpServletResponse response, HttpSession httpSession,
			HttpServletRequest request, String pos) throws IOException {
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);

		cartService.deleteCartByUserId(userId);
		String result = "";
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		if (CART_TYPE.equals(pos)) {
			result = "<ul id=\"noBuy\"><li>购物车内暂时没有商品，<a href=\"" + realPath
					+ "/index.do\">去首页</a>挑选喜欢的商品</li></ul>";
		} else {
			result = "<div class='shoppingNone'>购物车中还没有商品，赶紧选购吧！</div>"
					+ "<div id='csProudctCount' style=' display:none;'>0</div>";
		}

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		return null;
	}

	/**
	 * 改变购物车产品的数量
	 * 
	 * @param response
	 * @param pmId
	 * @param buyCount
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/changepruductcount")
	public String changepruductcount(HttpServletResponse response,
			HttpSession httpSession, HttpServletRequest request, int pid,
			int buyCount, String selectedCartItemKeyList, int areaId)
			throws IOException {
		List<Integer> pidList = new ArrayList<Integer>();
		String[] pids = null;
		if (selectedCartItemKeyList != null
				&& !"_".equals(selectedCartItemKeyList)
				&& selectedCartItemKeyList.length() > 0) {
			pids = selectedCartItemKeyList.split(",");
		}

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		boolean overStock = false;
		// 查询产品的库存
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(pid);
		cart.setCount(buyCount);
		cartService.updateCount(cart);

		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		List<CartSnapVO> list = cartService.getCartSnapByUserId(userId);
		if (pids != null) {
			for (String productId : pids) {
				pidList.add(Integer.parseInt(productId));
			}
		}
		// 是否超过库存
		Map<Integer, Boolean> overStockMap = new HashMap<Integer, Boolean>();
		overStockMap.put(pid, overStock);
		String html = cartService.createCartHtml(list, areaId, realPath,
				pidList, overStockMap);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(html);
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/countCartProQuantity")
	@ResponseBody
	public Map<String, Object> countCartProQuantity(HttpSession httpSession) {
		Map<String, Object> map = new HashMap();
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		String cartQuantityName = "quantity";
		if (userId == null) {
			map.put(cartQuantityName, 0);
		} else {
			Integer count = cartService.getCartCountByUserId(userId);
			map.put(cartQuantityName, count);
		}
		map.put("state", "success");
		return map;
	}

}
