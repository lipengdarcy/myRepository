package com.runlion.shop.controller.admin.regionmanage;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.Constant;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspRegionpriecedefunits;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.BspRegionsService;
import com.runlion.shop.service.BspUnitService;
import com.runlion.shop.service.PriceUpdateJobService;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsPriceService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.RegionPriceVO;

/**
 * 区域价格管理（产品价格、区域运费、区域装卸费）
 * */
@Controller
@RequestMapping("/regionprice")
public class RegionPriceController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private RegionsBrandService regionsBrandService;

	@Autowired
	private RegionsPriceService regionsPriceService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private PriceUpdateJobService priceUpdateJobService;

	@Autowired
	private BspRegionsService bspRegionsService;

	@Autowired
	private BspUnitService bspUnitService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	// ////////////////////////////////////////--以下是区域价格--////////////////////////////////////////////////////////

	// 区域价格列表
	@RequestMapping(value = "/priceList")
	public String priceList(HttpServletRequest request, ModelMap m) {
		int prid = integer("prid", request);
		int pid = integer("pid", request);
		Integer areaid = integer("areaid", request);
		String productName = request.getParameter("productName");
		if (productName == null) {
			productName = "";
		}
		int type = integer("type", request);
		String cid = request.getParameter("cid");
		if (type < 0)
			type = 2;

		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> shipList = regionsPriceService
				.getProductRegionPrice(type, prid, pid,
						Constant.priceType_product, startNum, pageSize, areaid,
						productName);
		int cnt = regionsPriceService.getProductRegionPriceCount(type, prid,
				pid, Constant.priceType_product, areaid, productName);
		m.put("status", "success");
		m.put("shipList", shipList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("prid", prid);
		m.put("pid", pid);
		m.put("type", type);
		m.put("areaid", areaid);
		m.put("cid", cid);
		m.put("productName", productName);

		return "admin/region/price/price-list";
	}

	// 区域价格添加
	@ResponseBody
	@RequestMapping(value = "priceAdd")
	public Map<String, Object> priceAdd(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		int productId = integer("pid", request);
		int regionsId = integer("regions_id", request);
		double price = dbl("price", request);
		String regionsName = str("regions_name", request);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date planeffecttime = null;
		// 是否立即生效
		int effectnow = integer("effectnow", request);
		try {
			planeffecttime = sdf.parse(str("planeffecttime", request));
		} catch (ParseException e) {
			planeffecttime = null;
		}

		int prid = regionsProductService.getProductsRegionsId(productId,
				regionsId);
		if (prid < 0) {
			prid = regionsProductService.generateNewProductRegionId();
			regionsProductService.addProductsRegions(prid, productId,
					regionsId, regionsName);
		}

		int priceId = regionsPriceService.getProductsRegionsPriceId(
				String.valueOf(Constant.priceType_product), price, prid);
		if (priceId > 0) {
			result.put("state", "exists");
			return result;
		}

		regionsPriceService.addProductsRegionsPrice(prid, price,
				String.valueOf(Constant.priceType_product), planeffecttime,
				effectnow);

		result.put("state", "success");
		return result;
	}

	// 区域价格、运费、装卸费删除
	@ResponseBody
	@RequestMapping(value = "priceDelete")
	public StatusHtml priceDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		int pid = integer("pid", request);
		int priceType = integer("priceType", request);
		loggerinfo.info("==========[AreaManagerController shipDelete]---id:"
				+ id);
		// 根据产品id批量删除价格
		if (id < 0)
			regionsPriceService.deletePriceByProductId(priceType, pid);
		else
			// 单价格删除
			regionsPriceService.deleteProductRegionsPrice(id);

		StatusHtml statusHtml = new StatusHtml();
		switch (priceType) {
		case 1:
			statusHtml.setNavTabId(Constant.NavTabId_region_price); // 产品价格
			break;
		case 2:
			statusHtml.setNavTabId(Constant.NavTabId_region_ship); // 运费
			break;
		case 3:
			statusHtml.setNavTabId(Constant.NavTabId_region_load); // 装卸费
			break;
		default:
			break;
		}

		return statusHtml;
	}

	// 区域价格、运费、装卸费 批量删除价格
	@ResponseBody
	@RequestMapping(value = "priceBatchDelete")
	public StatusHtml priceBatchDelete(String ids, String navTabId) {
		StatusHtml statusHtml = new StatusHtml();
		try {
			regionsPriceService.priceBatchDelete(ids);
			statusHtml.setNavTabId(navTabId);
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("删除失败");
		}

		return statusHtml;
	}

	// 区域价格修改
	@ResponseBody
	@RequestMapping(value = "priceEdit")
	public Map<String, Object> priceEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		double price = dbl("price", request);

		Date planeffecttime = null;
		// 是否立即生效
		int effectnow = integer("effectnow", request);
		try {
			planeffecttime = sdf.parse(str("planeffecttime", request));
		} catch (ParseException e) {
			planeffecttime = new Date();
		}

		regionsPriceService.updateProductsRegionsPrice(id,
				BigDecimal.valueOf(price), planeffecttime, effectnow);
		Map<String, Object> result = getResult();
		return result;
	}

	// 区域价格批量修改
	@RequestMapping(value = "/priceBatchEdit")
	@ResponseBody
	public StatusHtml priceBatchEdit(HttpServletRequest request, ModelMap m) {
		String[] ids = request.getParameterValues("priceid");
		String[] prices = request.getParameterValues("pricename");
		String pageid = request.getParameter("pageid");

		Date planeffecttime = null;
		// 是否立即生效
		int effectnow = integer("effectnow", request);
		if (effectnow < 0)
			effectnow = 1; // 默认是立即生效
		try {
			planeffecttime = sdf.parse(str("planeffecttime", request));
		} catch (ParseException e) {
			planeffecttime = new Date();
		}

		List<BspProductsregionsprice> priceList = new ArrayList();
		if (ids != null && prices != null) {
			if (ids.length == prices.length) {
				for (int i = 0; i < ids.length; i++) {
					BspProductsregionsprice price = new BspProductsregionsprice(
							Integer.valueOf(ids[i]), Double.valueOf(prices[i]));
					priceList.add(price);
				}
			}
		}
		regionsPriceService.updateProductRegionPriceList(priceList,
				planeffecttime, effectnow);

		StatusHtml statusHtml = new StatusHtml();
		if (pageid != null && pageid.trim().length() > 0) {
			statusHtml.setNavTabId(pageid);
			statusHtml.setCallbackType("");
		} else {
			statusHtml.setNavTabId("");
		}

		return statusHtml;
	}

	// 获取添加区域价格编辑页面
	@RequestMapping(value = "toPriceEdit")
	public String toPriceEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		BspProductsregionsprice price = regionsPriceService
				.getProductsregionspriceById(id);
		BspProductsregions regionproduct = regionsProductService
				.getProductsRegionsById(price.getProductregionsid());
		BspProducts product = bspProductsService
				.selectByPrimaryKey(regionproduct.getProductid());
		// 1.产品名称
		m.put("productName", product.getName());
		// 2.区域名称
		m.put("regionName", regionproduct.getRegionsName());
		// 3.价格
		m.put("price", price.getPrice());
		m.put("id", id);

		if (regionproduct != null) {
			Integer regid = regionproduct.getRegionsid();
			BspRegions bspRegions = regionsService.getResionsById(regid);
			String cid = regionsService.calCid(bspRegions, regid);
			m.put("cid", cid);
		}
		return "admin/region/price/price-edit";
	}

	// 获取添加区域价格新增页面
	@RequestMapping(value = "toPriceAdd")
	public String toPriceAdd(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController toProductAdd] called");
		int productId = integer("pid", request);
		m.put("pid", productId);
		m.put("product", bspProductsService.selectByPrimaryKey(productId));
		return "admin/region/price/price-add";
	}

	// ////////////////////////////////////////--以下是运费--////////////////////////////////////////////////////////

	// 区域运费列表
	@RequestMapping(value = "/shipList")
	public String shipList(HttpServletRequest request, ModelMap m) {
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);
		int prid = integer("prid", request);
		int pid = integer("pid", request);
		int type = integer("type", request);
		if (type < 0)
			type = 3;
		int startNum = (pageNo - 1) * pageSize;
		Integer areaid = integer("areaid", request);
		String cid = request.getParameter("cid");
		int priceType = Constant.priceType_ship;// 区域运费
		List<Map<String, Object>> shipList = regionsPriceService
				.getProductRegionPrice(type, prid, pid, priceType, startNum,
						pageSize, areaid, null);
		int cnt = regionsPriceService.getProductRegionPriceCount(type, prid,
				pid, priceType, areaid, null);
		m.put("status", "success");
		m.put("shipList", shipList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("prid", prid);
		m.put("pid", pid);
		m.put("type", type);
		m.put("areaid", areaid);
		m.put("cid", cid);
		return "admin/region/price/ship-list";
	}

	// 区域运费添加
	@ResponseBody
	@RequestMapping(value = "shipAdd")
	public Map<String, Object> shipAdd(HttpServletRequest request, ModelMap m,
			RegionPriceVO priceVo) {
		loggerinfo.info("==========[AreaManagerController shipAdd] called");
		String priceType = String.valueOf(Constant.priceType_ship);// 区域运费
		if (priceVo.getStarthand() == null)
			priceVo.setStarthand(BigDecimal.valueOf(0));
		boolean isOk = regionsPriceService.updateProductsRegionsPrice(priceVo,
				priceType);

		// lipeng 同时插入区域的下级
		Integer regionsId = priceVo.getRegionId();
		Integer productId = priceVo.getPid();
		if (productId == null)
			productId = -1;// 不与产品挂钩，纯区域运费
		List<BspRegions> list = regionsService
				.getResionsChildrenById(regionsId);
		for (BspRegions b : list) {
			// 判断是否已存在
			int prid = regionsProductService.getProductsRegionsId(productId,
					b.getRegionid());
			for (BspProductsregionsprice p : priceVo.getPriceList()) {
				double price = p.getPrice().doubleValue();
				int priceId = regionsPriceService.getProductsRegionsPriceId(
						priceType, price, prid);
				if (priceId < 0) {
					RegionPriceVO cloneVo = priceVo.clone();
					cloneVo.setRegionId(b.getRegionid());
					String regionName = regionsService.getResionsFullNameById(b
							.getRegionid());
					cloneVo.setRegionName(regionName);
					regionsPriceService.updateProductsRegionsPrice(cloneVo,
							priceType);
				}
			}

		}

		Map<String, Object> result = getResult();
		if (!isOk) {
			result.put("state", "failed");
		}
		return result;
	}

	// 区域运费修改
	@ResponseBody
	@RequestMapping(value = "/shipEdit")
	public Map<String, Object> shipEdit(HttpServletRequest request, ModelMap m,
			RegionPriceVO priceVo) {
		String priceType = String.valueOf(Constant.priceType_ship);// 区域运费
		if (priceVo.getStarthand() == null)
			priceVo.setStarthand(BigDecimal.valueOf(0));

		boolean isOk = regionsPriceService.updateProductsRegionsPrice(priceVo,
				priceType);
		Map<String, Object> result = getResult();
		if (!isOk) {
			result.put("state", "failed");
		}
		return result;
	}

	@RequestMapping(value = "toShipEdit")
	public String toShipEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		int prid = integer("prid", request);

		List<BspUnit> unitList = bspUnitService.searchUnit("");
		m.put("unitlist", unitList);

		BspProductsregions proRegionsInfor = bspAreaManagerService
				.selBspProductsregions(prid);
		m.put("proRegion", proRegionsInfor);

		Integer regid = proRegionsInfor.getRegionsid();

		List<BspRegionpriecedefunits> defUnitList = bspAreaManagerService
				.getPRDefUnits(prid, String.valueOf(Constant.priceType_ship));
		m.put("defUnitList", defUnitList);

		BspRegions bspRegions = regionsService.getResionsById(regid);
		String cid = regionsService.calCid(bspRegions, regid);
		m.put("cid", cid);

		List<BspProregionextprice> exgList = regionsPriceService
				.getProregionExtPriceList(prid);
		m.put("extPrice", exgList);

		if (proRegionsInfor.getProductid() > 0) {
			BspProducts product = bspProductsService
					.selectByPrimaryKey(proRegionsInfor.getProductid());
			m.put("product", product);
			m.put("prounit", bspAreaManagerService.queryUnitByid(product
					.getQuantityunitid()));
		}

		List<BspProductsregionsprice> priceList = bspAreaManagerService
				.proRegionPriceList(prid,
						String.valueOf(Constant.priceType_ship));
		m.put("priceList", priceList);

		m.put("productRegionsId", prid);

		return "admin/region/price/ship-edit";
	}

	@RequestMapping(value = "toShipAdd")
	public String toShipAdd(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("==========[AreaManagerController toShipAdd] called");
		m.put("saleAddress", bspAreaManagerService.querySaleaddress());
		int pid = integer("pid", request);
		int prid = integer("prid", request);
		m.put("prid", prid);
		m.put("pid", pid);

		List<BspUnit> unitList = bspUnitService.searchUnit("");
		m.put("unitlist", unitList);

		if (pid > 0) {
			BspProducts product = bspProductsService.selectByPrimaryKey(pid);
			m.put("product", product);
			m.put("prounit", bspAreaManagerService.queryUnitByid(product
					.getQuantityunitid()));
		}

		return "admin/region/price/ship-add";
	}

	// ////////////////////////////////////////--以下是装卸费--////////////////////////////////////////////////////////

	// 区域装卸费列表
	@RequestMapping(value = "/handList")
	public String handList(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("装卸费列表");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);
		int prid = integer("prid", request);
		int pid = integer("pid", request);
		int type = integer("type", request);
		if (type < 0)
			type = 3;
		int startNum = (pageNo - 1) * pageSize;
		Integer areaid = integer("areaid", request);
		String cid = request.getParameter("cid");

		int priceType = Constant.priceType_load;// 装卸费
		List<Map<String, Object>> shipList = regionsPriceService
				.getProductRegionPrice(type, prid, pid, priceType, startNum,
						pageSize, areaid, null);
		int cnt = regionsPriceService.getProductRegionPriceCount(type, prid,
				pid, priceType, areaid, null);
		m.put("status", "success");
		m.put("shipList", shipList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("prid", prid);
		m.put("pid", pid);
		m.put("type", type);
		m.put("areaid", areaid);
		m.put("cid", cid);
		return "admin/region/price/hand-list";
	}

	// 区域装卸费添加
	@ResponseBody
	@RequestMapping(value = "handAdd")
	public Map<String, Object> handAdd(HttpServletRequest request, ModelMap m,
			RegionPriceVO priceVo) {
		loggerinfo.info("==========[AreaManagerController handAdd] called");
		String priceType = String.valueOf(Constant.priceType_load);// 区域装卸费

		if (priceVo.getStartship() == null)
			priceVo.setStartship(BigDecimal.valueOf(0));

		boolean isOk = regionsPriceService.updateProductsRegionsPrice(priceVo,
				priceType);
		Map<String, Object> result = getResult();
		if (!isOk) {
			result.put("state", "failed");
		}

		// lipeng 同时插入区域的下级
		Integer regionsId = priceVo.getRegionId();
		Integer productId = priceVo.getPid();
		if (productId == null)
			productId = -1;// 不与产品挂钩，纯区域运费
		List<BspRegions> list = regionsService
				.getResionsChildrenById(regionsId);
		for (BspRegions b : list) {
			// 判断是否已存在
			int prid = regionsProductService.getProductsRegionsId(productId,
					b.getRegionid());
			for (BspProductsregionsprice p : priceVo.getPriceList()) {
				double price = p.getPrice().doubleValue();

				int priceId = regionsPriceService.getProductsRegionsPriceId(
						priceType, price, prid);
				if (priceId < 0) {
					RegionPriceVO cloneVo = priceVo.clone();
					cloneVo.setRegionId(b.getRegionid());
					String regionName = regionsService.getResionsFullNameById(b
							.getRegionid());
					cloneVo.setRegionName(regionName);
					regionsPriceService.updateProductsRegionsPrice(cloneVo,
							priceType);
				}
			}

		}

		return result;
	}

	// 区域装卸费修改
	@ResponseBody
	@RequestMapping(value = "handEdit")
	public Map<String, Object> handEdit(HttpServletRequest request, ModelMap m,
			RegionPriceVO priceVo) {
		String priceType = String.valueOf(Constant.priceType_load);// 区域装卸费
		if (priceVo.getStartship() == null)
			priceVo.setStartship(BigDecimal.valueOf(0));
		boolean isOk = regionsPriceService.updateProductsRegionsPrice(priceVo,
				priceType);
		Map<String, Object> result = getResult();
		if (!isOk) {
			result.put("state", "failed");
		}
		return result;

	}

	@RequestMapping(value = "toHandEdit")
	public String toHandEdit(HttpServletRequest request, ModelMap m) {
		// int id = integer("id", request);
		int prid = integer("prid", request);
		String priceType = String.valueOf(Constant.priceType_load);// 区域装卸费

		List<BspUnit> unitList = bspUnitService.searchUnit("");
		m.put("unitlist", unitList);

		BspProductsregions proRegionsInfor = bspAreaManagerService
				.selBspProductsregions(prid);
		m.put("proRegion", proRegionsInfor);

		Integer regid = proRegionsInfor.getRegionsid();

		List<BspRegionpriecedefunits> defUnitList = bspAreaManagerService
				.getPRDefUnits(prid, priceType);
		m.put("defUnitList", defUnitList);

		BspRegions bspRegions = regionsService.getResionsById(regid);
		String cid = regionsService.calCid(bspRegions, regid);

		m.put("cid", cid);

		if (proRegionsInfor.getProductid() > 0) {
			BspProducts product = bspProductsService
					.selectByPrimaryKey(proRegionsInfor.getProductid());

			m.put("product", product);

			m.put("prounit", bspAreaManagerService.queryUnitByid(product
					.getQuantityunitid()));
		}

		List<BspProductsregionsprice> priceList = bspAreaManagerService
				.proRegionPriceList(prid, priceType);
		m.put("priceList", priceList);
		m.put("productRegionsId", prid);
		return "admin/region/price/hand-edit";
	}

	@RequestMapping(value = "toHandAdd")
	public String toHandAdd(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("==========[AreaManagerController toHandAdd] called");
		List<BspUnit> unitList = bspUnitService.searchUnit("");
		m.put("unitlist", unitList);

		int pid = integer("pid", request);
		if (pid > 0) {
			BspProducts product = bspProductsService.selectByPrimaryKey(pid);
			m.put("product", product);
			m.put("prounit", bspAreaManagerService.queryUnitByid(product
					.getQuantityunitid()));
		}
		return "admin/region/price/hand-add";
	}

}
