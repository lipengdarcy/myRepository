package com.runlion.shop.controller.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductskusRTM;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.SkuInfo;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspNcenterinforService;
import com.runlion.shop.service.BspNewsService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.vo.SalerProInfor;
import com.runlion.shop.vo.SalerProSelParaVO;
import com.runlion.shop.vo.WebConfigVO;

//import static org.hamcrest.Matchers.*;

/**
 * 经销商产品controller
 * */
@Controller
@RequestMapping("/dealproduct")
public class SalerProductController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	// 经销商首页默认显示的条目数
	public static int MAX_INDEX_PROITEMS = 6;
	// 允销目录默认显示的条目数
	public static int MAX_SALER_PROITEMS = 10;
	// 产品详情页推荐商品默认的条目数
	public static int MAX_DETAIL_PROITEMS = 3;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	BspAreaManagerService bspAreaManagerService;

	@Autowired
	private BusinessService businessService;

	@Autowired
	BspNcenterinforService bspNcenterinforService;

	@Autowired
	private BspNewsService bspNewsService;

	// 产品列表list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController load] Start...");
		loggerinfo.info("==========[ProductController load] End...");
		return "home/product/list";
	}

	// 经销商首页产品目录
	@RequestMapping(value = "salerIndexProList")
	public ModelAndView salerIndexProList(HttpServletRequest request,
			HttpSession httpSession, SalerProSelParaVO paravo) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 获取永或的uid
		Integer uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		// 没有登录则跳转到登录页面
		if (uid == null) {
			mv.setViewName("redirect:/account/login.do");
			return mv;
		}
		// 获取用户角色
		int userType = 11;
		try {
			userType = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UserType);
		} catch (Exception ex) {

		}

		// 拥有工厂角色
		if (businessService.isFactoryUser(userType)) {
			mv.addObject("isFactoryUser", 1);
		}
		// 拥有经销商角色
		if (businessService.isStoreUser(userType)) {
			mv.addObject("isStoreUser", 1);
			// 经销商进货列表
			int salerid = businessService.getStoreId(uid,
					Constant.UserType_store);
			// 查理查询参数并查询允销目录
			this.handSalerSelParaVO(paravo);
			paravo.setPageSize(MAX_INDEX_PROITEMS);
			List<SalerProInfor> list = bspProductsService
					.selectSalerProInforlist(salerid, paravo);
			mv.addObject("productList", list);
		} else {
			mv.addObject("msg", "您不是经销商！");
		}

		// 获取去新闻信息列表
		List<Map<String, Object>> newsList1 = bspNewsService.queryNewsByType(1,
				1, 9);
		mv.addObject("newsList1", newsList1);
		List<Map<String, Object>> newsList2 = bspNewsService.queryNewsByType(2,
				1, 6);
		mv.addObject("newsList2", newsList2);
		//
		mv.addObject("parinfor", paravo);
		mv.addObject("userType", userType);
		mv.setViewName("business/index");

		return mv;
	}

	// 允销目录
	@RequestMapping(value = "salerProList")
	public ModelAndView salerProList(HttpServletRequest request,
			HttpSession httpSession, SalerProSelParaVO paravo) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 获取永或的uid
		Integer uid = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		// 没有登录则跳转到登录页面
		if (uid == null) {
			mv.setViewName("redirect:/account/login.do");
			return mv;
		}
		// 获取用户角色
		int userType = 11;
		try {
			userType = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UserType);
		} catch (Exception ex) {
		}
		// 拥有工厂角色
		if (businessService.isFactoryUser(userType)) {
			mv.addObject("isFactoryUser", 1);
		}
		// 拥有经销商角色
		if (businessService.isStoreUser(userType)) {
			mv.addObject("isStoreUser", 1);
			// 经销商进货列表
			int salerid = businessService.getStoreId(uid,
					Constant.UserType_store);
			// 查理查询参数并查询允销目录
			this.handSalerSelParaVO(paravo);
			List<SalerProInfor> list = bspProductsService
					.selectSalerProInforlist(salerid, paravo);
			mv.addObject("productList", list);
			// 分页
			long total = bspProductsService.countSalerProInforlist(salerid,
					paravo);
			// 查询参数需要带回
			String selWords = StringHandler.pramVoToKeyval(paravo,
					new ArrayList<String>(Arrays.asList("pageNumber")));
			String newPath = "salerProList.do?";
			if (selWords != null && !selWords.equals("")) {
				newPath += selWords + "&pageNumber=";
			} else {
				newPath += "&pageNumber=";
			}
			mv.addObject("pageDiv", StringHandler.generatePageDiv(total,
					paravo.getPageSize(), paravo.getPageNumber(), newPath));
		} else {
			mv.addObject("msg", "您不是经销商！");
		}
		mv.addObject("parinfor", paravo);
		mv.addObject("userType", userType);
		mv.setViewName("business/dealproduct/salerList");

		return mv;
	}

	// 产品详细detail
	@SuppressWarnings("null")
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView dealdetail(
			@RequestParam(defaultValue = "0") Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		loggerinfo.info("==========[ProductController detail] Start..." + id);
		ModelAndView mv = new ModelAndView();
		Integer uid = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);
		if (uid == null) {
			mv.setViewName("redirect:/account/login.do");
			return mv;
		}

		BspProducts entity = bspProductsService.selectByPrimaryKey(id);
		if (entity == null) {
			mv.addObject("msg", "该信息不存在，请重新访问！");
			mv.setViewName("error/noSecurity");
			return mv;
		}
		//
		// 查找用户关联的门店的信息
		BspSaleaddress salerinfor = businessService.getStoreInfor(uid,
				Constant.UserType_store);
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

		// 产品计量单位
		BspUnit unit = bspProductsService.getUnit(entity.getWeightunitid());
		BspUnit QuantityUnit = bspProductsService.getUnit(entity
				.getQuantityunitid());

		Map<String, Object> productDetail = new HashMap<String, Object>();
		productDetail.put("entity", entity);
		productDetail.put("unit", unit);
		productDetail.put("QuantityUnit", QuantityUnit);

		// 产品图片（多张）
		List<BspProductimages> pics = bspProductsService.getProductNormPics(id);
		productDetail.put("productPics", pics);

		// 产品属性
		List<BspAttributevalues> attrs = bspProductsService
				.getProductAttrByID(id);
		productDetail.put("attrs", attrs);

		int areaId = salerinfor.getRegionid();
		// 生成sku组
		List<SkuInfo> skuList = bspProductsService.genarateSku(areaId, id,
				false);
		productDetail.put("skuList", skuList);

		// 网站配置的售后保障
		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		mv.addObject("webinfor", configVo);

		mv.addObject("productDetail", productDetail);
		mv.addObject("productId", id); // 产品id

		Integer brandId = null;
		Integer factoryId = null; // 产品属性未必有产地属性，sku里面则必然有
		List<BspProductskusRTM> factoryList = null;
		for (SkuInfo sku : skuList) {
			if (sku.getAttrid() == Constant.brandId) {
				for (BspProductskusRTM b : sku.getSkuList()) {
					if (b.getPid().equals(id)) {
						brandId = b.getAttrvalueid();
						factoryList = sku.getBrand_factory().get(brandId);
						break;
					}

				}
			}
		}
		//
		if (factoryList == null || factoryList.size() == 0) {
			factoryId = null;
		} else {
			factoryId = factoryList.get(0).getAttrvalueid();
		}
		mv.addObject("brandId", brandId); // 品牌id
		mv.addObject("factoryId", factoryId); // 产地id

		// 获取推荐商品栏的商品信息
		int salerid = businessService.getStoreId(uid, Constant.UserType_store);
		SalerProSelParaVO paravo = new SalerProSelParaVO();
		this.handSalerSelParaVO(paravo);
		// 最多三条
		paravo.setPageSize(MAX_DETAIL_PROITEMS);
		List<SalerProInfor> list = bspProductsService.selectSalerProInforlist(
				salerid, paravo);
		mv.addObject("productList", list);

		loggerinfo.info("==========[ProductController detail] End..." + id);
		mv.setViewName("business/dealproduct/detail");
		return mv;
	}

	/**
	 * 处理允销目录的查询参数
	 * 
	 * @param paravo
	 * @return
	 */
	private Map handSalerSelParaVO(SalerProSelParaVO paravo) {
		Map<String, Object> rsinfor = new HashMap();
		Integer page = paravo.getPageNumber();
		Integer pagesize = paravo.getPageSize();
		if (page == null) {
			paravo.setPageNumber(1);
		}
		if (pagesize == null) {
			paravo.setPageNumber(MAX_SALER_PROITEMS);
		}
		return rsinfor;
	}

	private String voToKeyVal(SalerProSelParaVO paravo) {
		String keyVal = "";
		if (paravo.getNcpronum() != null) {
			keyVal += "ncpronum=" + paravo.getNcpronum();
		}
		if (paravo.getProname() != null) {
			if (!keyVal.equals("")) {
				keyVal += "&";
			}
			keyVal += "proname=" + paravo.getProname();
		}
		return keyVal;
	}

}
