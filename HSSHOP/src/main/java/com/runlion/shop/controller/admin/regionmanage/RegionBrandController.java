package com.runlion.shop.controller.admin.regionmanage;

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
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;

/**
 * 区域品牌管理
 * */
@Controller
@RequestMapping("/regionbrand")
public class RegionBrandController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private RegionsBrandService regionsBrandService;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private BspProductsService bspProductsService;

	// 区域品牌列表
	@RequestMapping(value = "/brandList")
	public String brandList(HttpServletRequest request, ModelMap m) {
		String cid = request.getParameter("cid");
		Integer areaid = integer("areaid", request);
		Integer brandId = integer("brandId", request);

		List<Map<String, Object>> mapObj = bspAreaManagerService
				.queryBrandList();

		m.put("proregionbrand", mapObj);

		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspAreaManagerService
				.queryProductRegionsBrand(startNum, pageSize, areaid, brandId);
		int cnt = bspAreaManagerService.countProductRegionsBrand(areaid,
				brandId);

		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("areaid", areaid);
		m.put("cid", cid);
		m.put("brandId", brandId);
		return "admin/region/brand/brand-list";
	}

	// 区域品牌添加
	@ResponseBody
	@RequestMapping(value = "/brandAdd")
	public Map<String, Object> brandAdd(HttpServletRequest request, ModelMap m) {
		int brandId = integer("brand_id", request);
		int regionsId = integer("regions_id", request);
		String regionsName = str("regions_name", request);
		Map<String, Object> result = getResult();
		int productsRegionsId = regionsProductService.getProductsRegionsId(-1,
				regionsId);
		int regionBrandId = regionsBrandService.getProductsRegionsBrandId(
				brandId, productsRegionsId);
		if (regionBrandId > 0) {
			result.put("state", "exists");
		} else {
			// 插入区域产品之前，先判断是否已存在

			if (productsRegionsId <= 0) {
				productsRegionsId = regionsProductService
						.generateNewProductRegionId();
				regionsProductService.addProductsRegions(productsRegionsId, -1,
						regionsId, regionsName, null, null);

			}

			regionsBrandService.addProductRegionsBrand(brandId,
					productsRegionsId);
			result.put("state", "success");
		}

		return result;
	}

	// 区域品牌删除
	@ResponseBody
	@RequestMapping(value = "/brandDelete")
	public StatusHtml brandDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		regionsBrandService.deleteProductRegionsBrand(id);
		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setNavTabId(Constant.NavTabId_region_brand);
		statusHtml.setMessage("删除成功！");
		return statusHtml;
	}

	// 区域品牌编辑
	@ResponseBody
	@RequestMapping(value = "/brandEdit")
	public Map<String, Object> brandEdit(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("==========[AreaManagerController brandAdd] called");
		int id = integer("id", request);
		int brandId = integer("brand_id", request);
		int regionsId = integer("regions_id", request);
		String regionsName = str("regions_name", request);
		Map<String, Object> result = getResult();
		int productsRegionsId = -1;
		if (bspAreaManagerService.hasProductRegionsBrandWithId(id, brandId,
				regionsId)) {
			result.put("state", "exists");
		} else {
			// 插入区域产品之前，先判断是否已存在
			productsRegionsId = regionsProductService.getProductsRegionsId(-1,
					regionsId);
			if (productsRegionsId <= 0) {
				productsRegionsId = regionsProductService
						.generateNewProductRegionId();
				regionsProductService.addProductsRegions(productsRegionsId,
						null, regionsId, regionsName, null, null);

			}
			bspAreaManagerService.upProductRegionsBrandBy(id, brandId,
					productsRegionsId);
			result.put("state", "success");
		}

		return result;
	}

	@RequestMapping(params = "method=toBrandAdd")
	public String toBrandAdd(ModelMap m) {
		loggerinfo.info("==========[AreaManagerController toBrandAdd] called");
		m.put("brandList", bspAreaManagerService.queryBrandList());

		return "admin/region/brand/brand-add";
	}

	@RequestMapping(params = "method=toBrandEdit")
	public String toBrandEdit(ModelMap m, Integer id) {
		loggerinfo.info("==========[AreaManagerController toBrandAdd] called");
		m.put("brandList", bspAreaManagerService.queryBrandList());
		//
		Map<String, Object> mapObj = bspAreaManagerService
				.selProRegionsBrand(id);
		m.put("proregionbrand", mapObj);
		Integer proRegionId = (Integer) mapObj.get("productregionsId");
		BspProductsregions proregion = regionsProductService
				.getProductsRegionsById(proRegionId);
		if (proregion != null) {
			Integer regid = proregion.getRegionsid();

			BspRegions bspRegions = regionsService.getResionsById(regid);
			String cid = regionsService.calCid(bspRegions, regid);
			m.put("cid", cid);
		}

		return "admin/region/brand/brand-edit";
	}

	// 区域品牌异步查询
	@ResponseBody
	@RequestMapping(value = "/getBrandByRegionsId")
	public List<Map<String, Object>> getBrandByRegionsId(
			HttpServletRequest request, ModelMap m) {
		int regionsId = integer("regions_id", request);
		loggerinfo.info("==[AreaManagerController areaBrand]---regionsId:"
				+ regionsId);
		List<Map<String, Object>> list = bspAreaManagerService
				.queryBrandListByRegionsid(regionsId);
		return list;
	}

	// 区域品牌批量删除
	@ResponseBody
	@RequestMapping(value = "/brandListDelete")
	public StatusHtml brandDelete(HttpServletRequest request, ModelMap m,
			String ids) {

		loggerinfo.info("==[AreaManagerController brandListDelete]---ids:"
				+ ids);
		if (ids != null) {
			String[] idsArr = ids.split(",");
			for (int i = 0; i < idsArr.length; i++) {
				String id = idsArr[i];
				String[] idPro = id.split("_");
				if (idPro.length > 1) {
					regionsBrandService.deleteProductRegionsBrand(Integer
							.valueOf(idPro[0]));
				}

			}
		}

		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setNavTabId(Constant.NavTabId_region_brand);
		statusHtml.setMessage("删除成功！");
		return statusHtml;
	}

}
