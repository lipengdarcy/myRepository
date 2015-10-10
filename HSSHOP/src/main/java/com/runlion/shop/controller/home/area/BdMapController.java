package com.runlion.shop.controller.home.area;

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
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.CartService;
import com.runlion.shop.vo.CartSnapVO;

/**
 * 购物车相关controller
 * */
@Controller
@RequestMapping("/area")
public class BdMapController {

	@Autowired
	private CartService cartService;
	@Autowired
	private BspAreaManagerService bspAreaManagerService;

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
	@RequestMapping("/bdmap")
	public String bdmap(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		// 获取cookie中的区域id
		Cookie[] cookie = request.getCookies();
		int areaId = cartService.getAreaIdFromCookies(cookie);

		List<BspSaleaddress> bsaList = new ArrayList();

		if (areaId != 0) {
			bsaList = bspAreaManagerService.selSaleaddressByAreaid(areaId);
		} else {
			bsaList = bspAreaManagerService.selSaleaddressByAreaid(214785);
		}

		request.setAttribute("saleadressList", bsaList);

		return "home/area/bdmap";
	}

}
