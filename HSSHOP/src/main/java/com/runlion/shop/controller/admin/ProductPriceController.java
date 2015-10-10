package com.runlion.shop.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.Constant;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.region.RegionsPriceService;

/**
 * 产品价格 Controller
 * */
@Controller
@RequestMapping("/price")
public class ProductPriceController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	private String retFlag = "success";

	@Autowired
	private BspAreaManagerService bspAreaManagerService;
	
	@Autowired
	private RegionsPriceService regionsPriceService;
	
	

	@Autowired
	private BspProductsService bspProductsService;

	// 区域价格添加
	@ResponseBody
	@RequestMapping(params = "method=productAdd")
	public Map<String, Object> productAdd(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		int productId = integer("pid", request);
		loggerinfo
				.info("==========[AreaManagerController productAdd]---productId:"
						+ productId);
		String priceType = "1";// 区域价格
		int regionsId = integer("regions_id", request);
		boolean b = bspAreaManagerService.existsProductRegions(productId,
				regionsId);
		if (b) {
			result.put("state", "exists");
			return result;
		}
		String regionsName = str("regions_name", request);
		double price1 = dbl("price1", request);
		double price2 = dbl("price2", request);
		double price3 = dbl("price3", request);



		result.put("state", retFlag);
		return result;
	}

	// 区域价格修改
	@ResponseBody
	@RequestMapping(params = "method=productEdit")
	public Map<String, Object> productEdit(HttpServletRequest request,
			ModelMap m) {
		loggerinfo.info("==========[AreaManagerController productEdit] called");
		String priceType = "1";// 区域价格
		int regionsId = integer("regions_id", request);
		String regionsName = str("regions_name", request);
		double price1 = dbl("price1", request);
		// double price2 = dbl("price2", request);
		// double price3 = dbl("price3", request);
		int productId = integer("pid", request);
		int productsRegionsId = integer("id", request);
	
		// bspAreaManagerService.removeProductRegionsPrice(productsRegionsId,
		// regionsName);
		
		// if (price2 != -1.0) {
		// bspAreaManagerService.addProductsRegionsPrice(productsRegionsId,
		// "2", price2, priceType);
		// }
		// if (price3 != -1.0) {
		// bspAreaManagerService.addProductsRegionsPrice(productsRegionsId,
		// "3", price3, priceType);
		// }

		Map<String, Object> result = getResult();
		result.put("state", retFlag);
		return result;
	}

	@RequestMapping(params = "method=toProductEdit")
	public String toProductEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==========[AreaManagerController toProductEdit]---id:"
				+ id);

		List<Map<String, Object>> list = bspAreaManagerService
				.queryProductRegionsInfo(id, "");
		if (!CollectionUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				String type = str("type", map);
				String price = str("price", map);
				String pid = str("pid", map);
				if (pid.equals(""))
					pid = "-1";
				String pname = str("pname", map);
				m.put("price" + type, price);
				int saleAddressId = integer("saleaddressid", map);
				if (i == 0) {
					m.put("cid", getCid(map));
					m.put("saleAddressId", saleAddressId);
					m.put("pid", pid);
					m.put("pname", pname);
				}
			}
		}
		m.put("productRegionsId", id);

		return "admin/area/product-edit";
	}

	@RequestMapping(params = "method=toProductAdd")
	public String toProductAdd(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController toProductAdd] called");
		int productId = integer("pid", request);
		m.put("pid", productId);

		//m.put("product", bspAreaManagerService.queryProductById(productId));

		return "admin/area/product-add";
	}

	// 区域价格修改日志查询列表
	@RequestMapping(value = "/pricelogList")
	public String pricelogList(@RequestParam(defaultValue = "0") Integer id,
			HttpServletRequest request, ModelMap m) {
		int prid = integer("prid", request);
		String cid = request.getParameter("cid");
		int type = 2;

		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);
		Integer areaid = integer("areaid", request);
		int startNum = (pageNo - 1) * pageSize;
//		List<Map<String, Object>> shipList = bspAreaManagerService
//				.queryAreaProduct(type, prid, startNum, pageSize, areaid);
//		int cnt = bspAreaManagerService.countAreaProduct(prid, areaid);
		m.put("status", retFlag);
//		m.put("shipList", shipList);
//		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("prid", prid);
		m.put("type", type);
		m.put("areaid", areaid);
		m.put("cid", cid);
		return "admin/area/product-list";
	}

	// 区域价格
	@RequestMapping(value = "/saveProductList")
	@ResponseBody
	public StatusHtml saveProductList(HttpServletRequest request, ModelMap m) {
		String[] ids = request.getParameterValues("priceid");
		String[] prices = request.getParameterValues("pricename");
		String pageid = request.getParameter("pageid");

		List<BspProductsregionsprice> priceList = new ArrayList();
		if (ids != null && prices != null) {
			if (ids.length == prices.length) {
				for (int i = 0; i < ids.length; i++) {
					BspProductsregionsprice price = new BspProductsregionsprice();
					price.setId(Integer.valueOf(ids[i]));
					price.setPrice(new BigDecimal(prices[i]));
					priceList.add(price);
				}
			}
		}

		

		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		if (pageid != null && pageid.trim().length() > 0) {
			statusHtml.setNavTabId(pageid);
			statusHtml.setCallbackType("");
		} else {
			statusHtml.setNavTabId("");
		}

		return statusHtml;
	}

	@RequestMapping(params = "method=toBatchEdit")
	public String toBatchEdit(HttpServletRequest request, ModelMap m) {
		int pid = integer("pid", request);
		int batchType = integer("batchType", request);
		m.put("type", batchType);
		if (batchType == 4) {
			m.put("productId", pid);
			BspProducts b = bspProductsService.selectByPrimaryKey(pid);
			m.put("productName", b.getName());

			return "admin/area/batcheditByProduct";

		} else {
			return "admin/area/batcheditByRegion";
		}
	}

	// 选择区域，批量修改价格
	@ResponseBody
	@RequestMapping(params = "method=priceBatchEdit")
	public Map<String, Object> priceBatchEdit(HttpServletRequest request,
			ModelMap m) {
		int RegionsId = integer("RegionsId", request);
		// 取区域id的层级信息
		BspRegions b = bspProductsService.getBspRegions(RegionsId);
		int layer = b.getLayer();

		int productId = integer("productId", request);
		int type = integer("type", request);
		double price = dbl("price", request);
		String message = "";
		Map<String, Object> result = getResult();
		int count = 0;

		count = bspAreaManagerService.priceBatchEdit(type, price, layer,
				RegionsId, productId);

		if (count > 0) {
			result.put("state", "success");
			message = "批量更新成功！影响的记录数是：" + count;
		}

		else {
			result.put("state", "failed");
			message = "批量更新失败！";
		}

		result.put("message", message);

		return result;
	}

	public String calCid(BspRegions bspRegions, Integer regid) {
		Byte layer = bspRegions.getLayer();
		String cid = "";
		if (layer == 1) {
			cid = regid + "-0-0-0-0";
		} else if (layer == 2) {
			cid = bspRegions.getProvinceid() + "-" + regid + "-0-0-0";
		} else if (layer == 3) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + regid + "-0-0";
		} else if (layer == 4) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + bspRegions.getCountyid() + "-" + regid + "-0";
		} else if (layer == 5) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + bspRegions.getCountyid() + "-"
					+ bspRegions.getStreetid() + "-" + regid;
		}
		return cid;
	}

	// 区域价格删除
	@ResponseBody
	@RequestMapping(params = "method=priceDelete")
	public Map<String, Object> shipDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		//bspAreaManagerService.removeProductRegions(id);
		Map<String, Object> result = getResult();
		result.put("state", retFlag);
		return result;
	}

}
