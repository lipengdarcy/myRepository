package com.runlion.shop.controller.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.runlion.shop.common.util.CookieHanler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsWithPics;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.service.BspNewsService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.CartService;

/**
 * 注册登录相关controller
 * */
@Controller
@RequestMapping("/index")
@SessionAttributes("productAttributes")
public class IndexController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService bspProductsService;
	@Autowired
	private CartService cartService;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private BspNewsService bspNewsService;

	@RequestMapping
	public String load(ModelMap m, HttpSession httpSession,
			HttpServletRequest request) throws Exception {

		loggerinfo.info("==========[HomeController load] Start...");

		// 从cookie取收货地址id
		String areaId = CookieHanler.getLastAreaFromCookie(request);
		m.put("areaId", areaId);

		List<ProductComboInfo> menuLink = (List<ProductComboInfo>) EHCacheUtil
				.get("menuLink");
		m.put("menuLink", menuLink);

		List<ProductComboInfo> result = bspProductsService.generateProductLink(
				Integer.valueOf(areaId), "");
		m.put("productCombo", result);

		Map<String, Object> map = bspProductsService.getProductAttributes();
		m.put("productAttributes", map);

		List<BspProductsWithPics> list = new ArrayList<BspProductsWithPics>();
		List<BspProducts> plist = bspProductsService.hotProducts(4);
		// 增加产品的大图和小图
		int pici = 0;
		for (BspProducts b : plist) {
			List<BspProductimages> picList = bspProductsService
					.getIndexProductPics(b.getPid(), (byte) 2);
			if (pici > 0) {
				picList = bspProductsService.getIndexProductPics(b.getPid(),
						(byte) 3);
			}
			BspProductsWithPics p = new BspProductsWithPics();
			p.setPicList(picList);
			p.setProduct(b);
			list.add(p);
			pici++;
		}
		m.put("hotProducts", list);

		List<BspProductsWithPics> list2 = new ArrayList<BspProductsWithPics>();
		List<BspProducts> plist2 = bspProductsService.newestProducts();
		// 增加产品的大图和小图
		pici = 0;
		for (BspProducts b : plist2) {
			List<BspProductimages> picList = bspProductsService
					.getIndexProductPics(b.getPid(), (byte) 2);
			if (pici > 0) {
				picList = bspProductsService.getIndexProductPics(b.getPid(),
						(byte) 3);
			}
			BspProductsWithPics p = new BspProductsWithPics();
			p.setPicList(picList);
			p.setProduct(b);
			list2.add(p);
			pici++;
		}
		m.put("newestProducts", list2);

		List<Map<String, Object>> newsList1 = bspNewsService.queryNewsByType(1,
				1, 9);
		m.put("newsList1", newsList1);

		List<Map<String, Object>> newsList2 = bspNewsService.queryNewsByType(2,
				1, 6);
		m.put("newsList2", newsList2);

		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		userId = userId == null ? 0 : userId;
		int cartCount = cartService.getCartCountByUserId(userId);
		httpSession.setAttribute("cartCount", cartCount);

		loggerinfo.info("==========[HomeController load] End...");
		return "home/index";
	}
}
