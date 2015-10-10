package com.runlion.shop.controller.home.product;

import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.lambdaj.group.Group;

import com.github.pagehelper.Page;
import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.CookieHanler;
import com.runlion.shop.common.util.PageHTMLHelper;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspFavorites;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsExample;
import com.runlion.shop.entity.BspProductskusRTM;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.entity.ProductLink;
import com.runlion.shop.entity.SkuInfo;
import com.runlion.shop.entity.common.ResponseData;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.vo.WebConfigVO;

//import static org.hamcrest.Matchers.*;

/**
 * 产品controller
 * */
@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	BspAreaManagerService bspAreaManagerService;

	// test
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController test] Start...");
		// test for service
		BspProducts p = bspProductsService.selectByPrimaryKey(54);
		System.out.println("BspProducts name: " + p.getName());

		// test for pagination
		BspProductsExample example = new BspProductsExample();
		List<BspProducts> list = bspProductsService.selectByExample(example, 1,
				10);
		System.out
				.println("bspProductsService.selectByExample: " + list.size());

		loggerinfo.info("==========[ProductController test] End...");
		return "home/product/list";
	}

	// 产品列表list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController load] Start...");
		loggerinfo.info("==========[ProductController load] End...");
		return "home/product/list";
	}

	// 单个条件查询
	@RequestMapping(value = "/query")
	public String query(@RequestParam("typeid") int typeid,
			@RequestParam("id") int id,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize,

			ModelMap m) throws Exception {

		List<BspProducts> list = bspProductsService.query(typeid, id,
				pageNumber, pageSize);
		// 产品列表
		m.put("productList", list);
		m.put("pageNum", ((Page<BspProducts>) list).getPageNum());
		m.put("pages", ((Page<BspProducts>) list).getPages());
		m.put("pageSize", ((Page<BspProducts>) list).getPageSize());
		m.put("total", ((Page<BspProducts>) list).getTotal());

		// 推荐产品列表
		List<BspProducts> rlist = bspProductsService.recommendedProducts();
		m.put("rProductList", rlist);

		return "home/product/list";
	}

	// 组合条件查询
	@RequestMapping(value = "/comboQuery")
	public String comboQuery(
			HttpServletRequest request,
			@RequestParam("path") String path,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "9") int pageSize,
			ModelMap m) throws Exception {
		// 从cookie取收货地址id
		String areaId = CookieHanler.getLastAreaFromCookie(request);
		List<ProductComboInfo> result = bspProductsService.generateProductLink(
				Integer.valueOf(areaId), path);
		m.put("productCombo", result);

		Integer brandId = -1;
		m.put("brandId", brandId); // 品牌id

		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] pathList = path.split("-");
		for (int i = 0; i < pathList.length; i++)
			map.put(pathList[i], Integer.parseInt(pathList[i]));
		List<BspProducts> list = bspProductsService.comboQuery(map,
				Integer.valueOf(areaId), pageNumber, pageSize);
		Page<BspProducts> page = (Page<BspProducts>) list;
		// 产品列表
		m.put("productList", list);
		long total = page.getTotal();

		// 推荐产品列表
		List<BspProducts> rlist = bspProductsService.recommendedProducts();
		m.put("rProductList", rlist);

		String newPath = "comboQuery.do?path=" + path + "&pageNumber=";
		m.put("pageDiv", StringHandler.generatePageDiv(total, pageSize,
				pageNumber, newPath));

		return "home/product/list";
	}

	/**
	 * 产品扩展属性筛选，以后备用
	 * */
	@RequestMapping(value = "/comboQueryzw")
	public String comboQueryzw(
			@RequestParam("path") String path,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,
			ModelMap m) throws Exception {

		List<ProductComboInfo> result = bspProductsService.updateLinkzw(path);

		Map map = new HashMap();
		String[] pathList = path.split("-");
		for (int i = 0; i < pathList.length; i++)
			map.put(pathList[i], Integer.parseInt(pathList[i]));

		List<BspProducts> list = bspProductsService.comboQuery(map, 1,
				pageNumber, pageSize);
		Page<BspProducts> page = (Page<BspProducts>) list;
		// 产品列表
		m.put("productList", list);
		long total = page.getTotal();

		/*
		 * 扩展属性
		 */

		if (path.length() == 0) {
			if (result.size() > 0) {
				StringBuilder attrRouteValue = new StringBuilder();
				for (int i = 0; i < result.size(); i++) {
					attrRouteValue.append("0-");
				}
				path = attrRouteValue.delete(attrRouteValue.length() - 1, 1)
						.toString();
			} else {
				path = "0";
			}
		}

		int order = 1;
		String startStr;
		String endStr;
		String centerStr;

		StringBuilder sb = new StringBuilder();

		for (ProductComboInfo item : result) {

			if (item.getProductlinklist().size() > 0) {
				startStr = order != 1 ? path.substring(0,
						StringHandler.IndexOf(path, order - 1) + 1) : "";
				endStr = order != result.size() ? path.substring(StringHandler
						.IndexOf(path, order)) : "";
				centerStr = StringHandler.TrimEnd(
						StringHandler.TrimStart(path, startStr), endStr);

				sb.append("<div class=\"selectItme\">");
				sb.append(StringHandler.formateString("<h3>{0}：</h3>",
						item.getAttrname()));
				sb.append("<ul>");
				sb.append("<li><a href=\"comboQueryzw.do?path=" + startStr
						+ "0" + endStr + "&pageNumber=1\"");
				if (centerStr.contains("0")) {
					sb.append(" class=\"hot\"");
				}
				sb.append(">不限</a></li>");

				for (ProductLink info : item.getProductlinklist()) {
					sb.append("<li><a href=\"comboQueryzw.do?path=" + startStr
							+ info.getAttrValue().getAttrvalueid() + endStr
							+ "&pageNumber=1\"");
					if (centerStr.contains(info.getAttrValue().getAttrvalueid()
							.toString())) {
						sb.append(" class=\"hot\"");
					}
					sb.append(StringHandler.formateString(">{0}</a></li>", info
							.getAttrValue().getAttrvalue()));
				}

				sb.append("<div class=\"clear\"></div>");
				sb.append("</ul>");
				sb.append("<div class=\"clear\"></div>");
				sb.append("</div>");
			}

			order++;
		}

		// 扩展属性
		m.put("productCombo", sb);

		String newPath = "comboQuery.do?path=" + path + "&pageNumber=";
		m.put("pageDiv", StringHandler.generatePageDiv(total, pageSize,
				pageNumber, newPath));

		return "home/product/list";
	}

	// 产品详细detail
	@SuppressWarnings("null")
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam(defaultValue = "0") Integer id,
			HttpServletRequest request, HttpServletResponse response, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[ProductController detail] Start..." + id);

		BspAdminuser bspAdminuser = (BspAdminuser) request.getSession()
				.getAttribute("adminuser");
		// 是否是管理员预览
		boolean isPreview = false;
		if (bspAdminuser != null)
			isPreview = true;

		String areaId = CookieHanler.getLastAreaFromCookie(request);
		BspProducts entity = bspProductsService.selectByPrimaryKeyState(id,
				bspAdminuser, Integer.valueOf(areaId));
		if (entity == null) {
			m.put("msg", "该信息不存在，请重新访问！");
			return "error/noSecurity";
		}

		// 网站配置的售后保障
		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		m.put("webinfor", configVo);

		// 产品计量单位
		BspUnit unit = bspProductsService.getUnit(entity.getWeightunitid());
		BspUnit QuantityUnit = bspProductsService.getUnit(entity
				.getQuantityunitid());

		Map<String, Object> productDetail = new HashMap<String, Object>();
		productDetail.put("entity", entity);
		productDetail.put("unit", unit);
		productDetail.put("QuantityUnit", QuantityUnit);

		// 产品价格
		List<BspProductsregionsprice> priceList = bspProductsService
				.getProductsregionsPrice(id, Integer.valueOf(areaId));

		BigDecimal productPrice = entity.getMarketprice();

		// 区域运费 & 装卸费
		List<BspProductsregionsprice> transPriceList = new ArrayList<BspProductsregionsprice>();
		List<BspProductsregionsprice> loadPriceList = new ArrayList<BspProductsregionsprice>();
		try {
			Iterator<BspProductsregionsprice> iterator = priceList.iterator();
			while (iterator.hasNext()) {
				BspProductsregionsprice p = iterator.next();
				if (p.getPricetype().equals("2")) {
					transPriceList.add(p);
					iterator.remove();
				} else if (p.getPricetype().equals("3")) {
					loadPriceList.add(p);
					iterator.remove();
				} else if (p.getPricetype().equals("1"))
					productPrice = p.getPrice();
			}
		} catch (Exception e) {
			// java.lang.IllegalStateException
			e.printStackTrace();
		}
		// 运费、装卸费作排序
		Collections.sort(transPriceList);
		Collections.sort(loadPriceList);
		productDetail.put("productPrice", priceList);

		if (transPriceList.size() == 0)
			transPriceList = bspProductsService.getRegionsTransPrice(Integer
					.valueOf(areaId));
		if (loadPriceList.size() == 0)
			loadPriceList = bspProductsService.getRegionsLoadPrice(Integer
					.valueOf(areaId));
		// 设置运费区间信息
		for (int i = 0; i < transPriceList.size(); i++) {
			BspProductsregionsprice p = transPriceList.get(i);
			if (i == 0 && transPriceList.size() > 1)
				p.setInfo(p.getBuyminquan()
						+ "吨~"
						+ Double.valueOf(transPriceList.get(i + 1)
								.getBuyminquan() - 1) + "吨: ");

			else if (i < transPriceList.size() - 1)
				p.setInfo(Double.valueOf(p.getBuyminquan() - 1)
						+ "吨~"
						+ Double.valueOf(transPriceList.get(i + 1)
								.getBuyminquan() - 1) + "吨: ");
			if (i == transPriceList.size() - 1)
				p.setInfo(p.getBuyminquan() + "吨及以上： ");
		}
		// 设置装卸费区间信息
		for (int i = 0; i < loadPriceList.size(); i++) {
			BspProductsregionsprice p = loadPriceList.get(i);
			if (i == 0 && loadPriceList.size() > 1)
				p.setInfo(p.getBuyminquan()
						+ "吨~"
						+ Double.valueOf(transPriceList.get(i + 1)
								.getBuyminquan() - 1) + "吨: ");
			else if (i < loadPriceList.size() - 1)
				p.setInfo(Double.valueOf(p.getBuyminquan() - 1) + "吨~"
						+ loadPriceList.get(i + 1).getBuyminquan() + "吨: ");
			if (i == loadPriceList.size() - 1)
				p.setInfo(p.getBuyminquan() + "吨及以上： ");
		}

		productDetail.put("transPriceList", transPriceList);
		productDetail.put("loadPriceList", loadPriceList);

		// 运费起步价
		BspProductsregions proRegions = regionsProductService
				.getProductsRegions(id, Integer.valueOf(areaId));
		BspProductsregions proExtRegion = bspAreaManagerService
				.getYesProidProregionsWithNotUp(Integer.valueOf(areaId), id);
		// proExtRegion=null;
		if (proExtRegion == null) {
			proExtRegion = bspAreaManagerService
					.getYesProidProregionsWithNotUp(Integer.valueOf(areaId), -1);
		}
		//
		if (proExtRegion != null) {
			List<BspProregionextprice> extpriceList = bspAreaManagerService
					.getBspProregionextpriceList(proExtRegion.getId());
			m.put("extpriceList", extpriceList);
		}
		//
		String carry = "0";
		if (proRegions != null) {
			carry = proRegions.getStartship().toString();
		}
		if (transPriceList.size() > 0) {
			int prid = transPriceList.get(0).getProductregionsid();
			BspProductsregions b = regionsProductService
					.getProductsRegionsById(prid);
			carry = b.getStartship().toString();
		}

		m.put("carry", carry);

		// 产品图片（多张）
		List<BspProductimages> pics = bspProductsService.getProductNormPics(id);
		productDetail.put("productPics", pics);

		// 产品属性
		List<BspAttributevalues> attrs = bspProductsService
				.getProductAttrByID(id);
		productDetail.put("attrs", attrs);

		// 价格不一致，前台提醒
		String[] temp = CookieHanler.getPriceFromCookie(request).split("-");
		String oldAreaId = temp[0];
		String oldPrice = temp[1];
		if (!oldPrice.equals(productPrice.toString())
				&& !oldAreaId.equals(areaId))
			m.put("isPriceChanged", 1);
		// 价格写入cookie
		CookieHanler.setPriceToCookie(productPrice.toString(), areaId, request,
				response);

		// 生成sku组
		List<SkuInfo> skuList = bspProductsService.genarateSku(
				Integer.valueOf(areaId), id, isPreview);
		productDetail.put("skuList", skuList);

		m.put("productDetail", productDetail);
		m.put("productId", id); // 产品id

		Integer brandId = null;
		Integer factoryId = null; // 产品属性未必有产地属性，sku里面则必然有
		List<BspProductskusRTM> factoryList = null;
		for (SkuInfo sku : skuList)
			if (sku.getAttrid() == Constant.brandId)

				for (BspProductskusRTM b : sku.getSkuList())
					if (b.getPid().equals(id)) {
						brandId = b.getAttrvalueid();
						factoryList = sku.getBrand_factory().get(brandId);
						break;
					}

		if (factoryList == null || factoryList.size() == 0)
			factoryId = null;
		else
			factoryId = factoryList.get(0).getAttrvalueid();

		m.put("brandId", brandId); // 品牌id
		m.put("factoryId", factoryId); // 产地id

		BspSaleaddress address_factory = null;

		BspSaleaddress address_store = bspProductsService.getProductAddress(
				Integer.valueOf(areaId), factoryId, 1);

		// 门店的工作时间
		if (address_store != null) {
			String worktime = "";
			List<BspWorktime> timeList = bspProductsService.getStoreWorkTime(
					address_store.getId(), 1);
			if (timeList == null || timeList.size() == 0)
				worktime = "";
			else
				worktime = timeList.get(0).getWtbegin() + "-"
						+ timeList.get(0).getWtend();
			address_store.setWorktime(worktime);
		}

		if (factoryId != null)
			address_factory = bspProductsService.getProductAddress(
					Integer.valueOf(areaId), factoryId, 2);

		// 工厂的工作时间
		if (address_factory != null) {
			String worktime = "";
			List<BspWorktime> timeList = bspProductsService.getStoreWorkTime(
					address_factory.getId(), 1);
			if (timeList == null || timeList.size() == 0)
				worktime = "";
			else
				worktime = timeList.get(0).getWtbegin() + "-"
						+ timeList.get(0).getWtend();
			address_factory.setWorktime(worktime);
		}

		productDetail.put("address_store", address_store);
		productDetail.put("address_factory", address_factory);

		m.put("skudiv", getBspProductskusRTMList(entity.getSkugid(), id));

		// 推荐产品列表
		List<BspProducts> rlist = bspProductsService.recommendedProducts();
		m.put("rProductList", rlist);

		loggerinfo.info("==========[ProductController detail] End..." + id);
		return "home/product/detail";
	}

	// 全文搜索
	@RequestMapping(value = "/search")
	public String search(HttpServletRequest request,
			@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "1") int pageNumber, ModelMap m)
			throws Exception {

		int pageSize = 10;

		String areaId = CookieHanler.getLastAreaFromCookie(request);
		List<ProductComboInfo> result = bspProductsService.generateProductLink(
				Integer.valueOf(areaId), "");
		m.put("productCombo", result);

		List<BspProducts> list = bspProductsService.search(
				Integer.valueOf(areaId), keyword, pageNumber, pageSize);
		// 产品列表
		m.put("productList", list);
		m.put("pageNum", ((Page<BspProducts>) list).getPageNum());
		m.put("pages", ((Page<BspProducts>) list).getPages());
		m.put("pageSize", ((Page<BspProducts>) list).getPageSize());
		m.put("total", ((Page<BspProducts>) list).getTotal());

		PageHTMLHelper p = new PageHTMLHelper();
		p.setTotal((Long) m.get("total"));
		p.setPageSize(pageSize);
		p.setIndex(pageNumber);
		p.setPath("search.do?keyword=" + keyword + "&pageNumber=");

		m.put("pageDiv", p.getPageHTML());

		// 推荐产品列表
		List<BspProducts> rlist = bspProductsService.recommendedProducts();
		m.put("rProductList", rlist);

		return "home/product/list";
	}

	// 收藏产品
	@ResponseBody
	@RequestMapping(value = "/addToFavorite", method = RequestMethod.GET)
	public ResponseData addToFavorite(HttpServletRequest request,
			@RequestParam(defaultValue = "0") Integer pid, ModelMap m)
			throws Exception {
		BspFavorites record = new BspFavorites();
		record.setPid(pid);
		Integer uid = (Integer) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_UID);
		if (uid == null)
			return new ResponseData("loginfirst", "用户未登陆，不能收藏产品！");
		record.setUid(uid);
		record.setState((byte) 0);
		Date addtime = new Date();
		record.setAddtime(addtime);
		int result = bspProductsService.addFavorites(record);
		if (result > 0)
			return new ResponseData("ok", "收藏产品成功！");
		else if (result == 0)
			return new ResponseData("failed", "产品已收藏，不能重复收藏！");
		else
			return new ResponseData("error", "收藏产品失败！");

	}

	public String getBspProductskusRTMList(int skuid, int pid) throws Exception {

		int attrLayer = 0;
		StringBuilder div = new StringBuilder();
		List<BspProductskusRTM> ProductSKUList = new ArrayList<BspProductskusRTM>();
		ProductSKUList = bspProductsService.getProductSkuList(skuid);

		List<BspProductskusRTM> currentProductSKUItemList = new ArrayList();

		// pid分组

		Group<BspProductskusRTM> group = group(ProductSKUList,
				by(on(BspProductskusRTM.class).getPid()));

		for (int i = 0; i < ProductSKUList.size(); i++) {
			BspProductskusRTM sku = ProductSKUList.get(i);
			if (sku.getPid() == pid)
				currentProductSKUItemList.add(sku);
		}

		//

		List<HashMap<Integer, BspProductskusRTM>> skuProductList = new ArrayList<HashMap<Integer, BspProductskusRTM>>();
		HashMap<Integer, List<BspProductskusRTM>> temp = new HashMap<Integer, List<BspProductskusRTM>>();

		// GroupBy pid分组
		// 1.初始化temp
		Set<Integer> pidSet = new HashSet<Integer>();
		for (int i = 0; i < ProductSKUList.size(); i++) {
			pidSet.add(ProductSKUList.get(i).getPid());
		}
		Iterator<Integer> it = pidSet.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			List<BspProductskusRTM> e = new ArrayList<BspProductskusRTM>();
			for (int j = 0; j < ProductSKUList.size(); j++) {
				BspProductskusRTM cell = ProductSKUList.get(j);
				if (cell.getPid() == i)
					e.add(cell);
			}
			temp.put(i, e);
		}

		temp.keySet();

		Iterator<Integer> iter = pidSet.iterator();
		while (iter.hasNext()) {
			Integer i = iter.next();
			List<BspProductskusRTM> info = temp.get(i);
			int attrValueIdKey = 1;
			HashMap<Integer, BspProductskusRTM> map = new HashMap<Integer, BspProductskusRTM>();
			for (BspProductskusRTM innerCell : info)
				attrValueIdKey *= innerCell.getAttrvalueid();
			map.put(attrValueIdKey, info.get(0));
			skuProductList.add(map);
		}

		List<BspProductskusRTM> attrList = new ArrayList<BspProductskusRTM>();
		for (BspProductskusRTM info : ProductSKUList) {
			boolean flag = true;
			for (BspProductskusRTM inner : attrList) {
				if (info.getAttrid() == inner.getAttrid())
					flag = false;
			}
			if (flag) {
				attrList.add(info);
			}
		}

		for (BspProductskusRTM info : attrList) {
			//

			div.append("<dl class=\"choose ");
			if (info.getAttrshowtype() == 1)
				div.append("chooseImg");
			div.append("\">");
			div.append("<dt>" + info.getAttrname() + "：</dt><dd>");

			List<BspProductskusRTM> attrValueList = new ArrayList<BspProductskusRTM>();
			for (BspProductskusRTM attrValue : ProductSKUList) {

				if (attrValue.getAttrid() == info.getAttrid()
						&& select(
								attrValueList,
								having(on(BspProductskusRTM.class)
										.getAttrvalueid(), Matchers
										.equalTo(info.getAttrvalueid())))
								.size() == 0) {

					attrValueList.add(attrValue);
				}
			}

			for (BspProductskusRTM attrValue : attrValueList) {
				int attrValueIdKey = 1;
				for (int i = 0; i < currentProductSKUItemList.size(); i++) {
					if (attrLayer != i) {
						attrValueIdKey *= currentProductSKUItemList.get(i)
								.getAttrvalueid();
					} else {
						attrValueIdKey *= attrValue.getAttrvalueid();
					}
				}

				HashMap<Integer, BspProductskusRTM> skuProduct = new HashMap<Integer, BspProductskusRTM>();
				for (HashMap<Integer, BspProductskusRTM> cell : skuProductList) {
					if (cell.get(attrValueIdKey) != null)
						skuProduct = cell;
				}

				if (skuProduct.get(attrValueIdKey) == null) {
					div.append(" <a href=\"javascript:;\" class=\"itme\"> ");

					if (attrValue.getIsinput() == 0) {
						div.append(attrValue.getAttrvalue());
					} else {
						div.append(attrValue.getInputvalue());
					}
					div.append(" </a>");
				} else {
					if (info.getAttrshowtype() == 0) {
						div.append(" <a href=\"?pid="
								+ skuProduct.get(attrValueIdKey).getPid()
								+ "\" class=\"itme ");
						if (skuProduct.get(attrValueIdKey).getPid() == pid) {
							div.append("hot");
						}
						div.append("\">");
						if (attrValue.getIsinput() == 0) {
							div.append(attrValue.getAttrvalue());
						} else {
							div.append(attrValue.getInputvalue());
						}
						div.append(" </a>");
					} else {
						div.append("  <a href=\"?pid="
								+ skuProduct.get(attrValueIdKey).getPid()
								+ " class=\"itme ");
						if (skuProduct.get(attrValueIdKey).getPid() == pid) {
							div.append("hot");
						}
						div.append("\">");
						div.append("<img src=\"/upload/product/show/thumb60_60/"
								+ skuProduct.get(attrValueIdKey).getShowimg()
								+ " width=\"25\" height=\"25\" /><i>");
						if (attrValue.getIsinput() == 0) {
							div.append(attrValue.getAttrvalue());
						} else {
							div.append(attrValue.getInputvalue());
						}
						div.append("</i></a>");
					}
				}
			}
			div.append("</dd><div class=\"clear\"></div></dl>");
			attrLayer++;

		}

		return div.toString();
	}

	// 收货地址改变，更新缓存中的菜单信息
	@ResponseBody
	@RequestMapping(value = "/refreshMenu", method = RequestMethod.GET)
	public ResponseData refreshMenu(HttpServletRequest request, ModelMap m) {
		try {
			// 从cookie取收货地址id
			String areaId = CookieHanler.getLastAreaFromCookie(request);
			List<ProductComboInfo> result = bspProductsService
					.generateProductLink(Integer.valueOf(areaId), "");
			EHCacheUtil.put("menuLink", result);
			return new ResponseData("ok", "区域改变，更新菜单成功！");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData("error", "区域改变，更新菜单失败！");
		}
		return null;
	}
}
