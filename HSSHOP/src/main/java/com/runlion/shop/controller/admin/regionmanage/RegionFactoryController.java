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
import com.runlion.shop.entity.BspProductsregionsbrand;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsProductService;

/**
 * 区域产地管理
 * */
@Controller
@RequestMapping("/regionfactory")
public class RegionFactoryController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private RegionsBrandService regionsBrandService;

	@Autowired
	private BspProductsService bspProductsService;

	// 品牌产地列表 lipeng
	@RequestMapping(value = "/factoryList")
	public String factoryList(HttpServletRequest request, ModelMap m) {
		String cid = request.getParameter("cid");
		Integer areaid = integer("areaid", request);

		Integer brandId = integer("brandId", request);
		Integer placeId = integer("placeId", request);

		List<Map<String, Object>> mapObj = bspAreaManagerService
				.queryBrandList();
		List<Map<String, Object>> placeObj = bspAreaManagerService
				.queryPlaceList();

		m.put("proregionbrand", mapObj);
		m.put("placelist", placeObj);

		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;

		List<Map<String, Object>> dataList = bspAreaManagerService
				.getBrandPlace(startNum, pageSize, brandId, placeId);
		int cnt = bspAreaManagerService.countBrandPlace(brandId, placeId);

		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("areaid", areaid);
		m.put("cid", cid);
		m.put("brandId", brandId);
		m.put("placeId", placeId);
		return "admin/region/factory/factory-list";
	}

	// 品牌产地添加（批量）
	@ResponseBody
	@RequestMapping(value = "/factoryAdd")
	public Map<String, Object> factoryAdd(HttpServletRequest request, ModelMap m) {

		Integer placeId = integer("place_id", request);
		Integer brandId = integer("brand_id", request);
		Map<String, Object> result = getResult();

		// List<BspProductsregionsbrand> list = regionsBrandService
		// .getRegionsIdList(brandId);
		// for (BspProductsregionsbrand b : list) {
		// int id = regionsBrandService.getProductsRegionsBrandPlaceId(
		// brandId, b.getProductregionsid(), placeId);
		// if (id < 0)
		// regionsBrandService.addProductRegionsBrandPlace(brandId,
		// b.getProductregionsid(), placeId);
		// }

		// 获取区域品牌
		List<BspProductsregionsbrand> list = regionsBrandService
				.getRegionsIdList(brandId);
		if (list == null || list.size() == 0) {
			result.put("state", "failed");
			return result;
		}

		int deleteCount = regionsBrandService.deleteRegionPlace(brandId,
				placeId);
		int count = regionsBrandService.addRegionPlace(brandId, placeId);

		result.put("state", "success");
		result.put("deleteCount", deleteCount);
		result.put("count", count);

		return result;
	}

	// 品牌产地删除
	@ResponseBody
	@RequestMapping(value = "/factoryDelete")
	public StatusHtml factoryDelete(HttpServletRequest request, ModelMap m) {
		Integer placeId = integer("place_id", request);
		Integer brandId = integer("brand_id", request);
		int count = regionsBrandService.deleteRegionPlace(brandId, placeId);

		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setNavTabId(Constant.NavTabId_brand_factory);
		statusHtml.setMessage("删除成功！ 删除数量：" + count);
		return statusHtml;
	}

	// 品牌产地编辑
	@ResponseBody
	@RequestMapping(value = "/factoryEdit")
	public Map<String, Object> factoryEdit(HttpServletRequest request,
			ModelMap m) {
		loggerinfo.info("=====[AreaManagerController placeAdd] called");

		Integer placeId = integer("place_id", request);
		Integer brandId = integer("brand_id", request);

		Integer oldplaceId = integer("oldplaceId", request);
		Integer oldbrandId = integer("oldbrandId", request);

		Map<String, Object> result = getResult();
		// 获取区域品牌
		List<BspProductsregionsbrand> list = regionsBrandService
				.getRegionsIdList(brandId);
		if (list == null || list.size() == 0) {
			result.put("state", "failed");
			return result;
		}
		int deleteCount = regionsBrandService.deleteRegionPlace(oldbrandId,
				oldplaceId);
		regionsBrandService.deleteRegionPlace(brandId, placeId);
		int count = regionsBrandService.addRegionPlace(brandId, placeId);

		result.put("state", "success");
		result.put("deleteCount", deleteCount);
		result.put("count", count);

		return result;
	}

	// 区域产地列表
	@RequestMapping(value = "/RegionfactoryList")
	public String RegionfactoryList(HttpServletRequest request, ModelMap m) {
		String cid = request.getParameter("cid");
		Integer areaid = integer("areaid", request);
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspAreaManagerService
				.queryProductRegionsBrandPlace(areaid, startNum, pageSize);
		int cnt = bspAreaManagerService.countProductRegionsBrandPlace(areaid);

		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("areaid", areaid);
		m.put("cid", cid);
		return "admin/region/factory/regionfactory-list";
	}

	// 区域产地添加
	@ResponseBody
	@RequestMapping(value = "/RegionfactoryAdd")
	public Map<String, Object> RegionfactoryAdd(HttpServletRequest request,
			ModelMap m) {
		loggerinfo.info("=====[AreaManagerController placeAdd] called");
		Integer placeId = integer("place_id", request);
		Integer brandId = integer("brand_id", request);
		Integer regionsId = integer("regions_id", request);
		String regionsName = str("regions_name", request);
		Map<String, Object> result = getResult();
		int productsRegionsId = regionsProductService.getProductsRegionsId(-1,
				regionsId);
		int regionFactoryId = regionsBrandService
				.getProductsRegionsBrandPlaceId(brandId, productsRegionsId,
						placeId);

		if (regionFactoryId > 0) {
			result.put("state", "exists");
		} else {
			if (regionsId != -1) {

				// 插入区域产品之前，先判断是否已存在
				if (productsRegionsId <= 0) {
					productsRegionsId = regionsProductService
							.generateNewProductRegionId();
					regionsProductService.addProductsRegions(productsRegionsId,
							null, regionsId, regionsName, null, null);

				}
			}
			regionsBrandService.addProductRegionsBrandPlace(brandId,
					productsRegionsId, placeId);
			result.put("state", "success");
		}

		return result;
	}

	// 区域产地删除
	@ResponseBody
	@RequestMapping(value = "/RegionfactoryDelete")
	public StatusHtml RegionfactoryDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		int productregionsid = integer("prid", request);
		loggerinfo.info("==[AreaManagerController placeDelete]---id:" + id
				+ ", prid:" + productregionsid);
		bspAreaManagerService.removeProductRegionsBrandPlace(id,
				productregionsid);
		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setNavTabId(Constant.NavTabId_region_factory);
		statusHtml.setMessage("删除成功！");
		return statusHtml;
	}

	// 区域产地编辑
	@ResponseBody
	@RequestMapping(value = "/RegionfactoryEdit")
	public Map<String, Object> RegionfactoryEdit(HttpServletRequest request,
			ModelMap m) {
		loggerinfo.info("=====[AreaManagerController placeAdd] called");
		Integer id = integer("id", request);
		Integer placeId = integer("place_id", request);
		Integer brandId = integer("brand_id", request);
		Integer regionsId = integer("regions_id", request);
		String regionsName = str("regions_name", request);
		Map<String, Object> result = getResult();
		int productsRegionsId = -1;
		if (bspAreaManagerService.hasProductRegionsBrandPlaceWithId(id,
				brandId, regionsId, placeId)) {
			result.put("state", "exists");
		} else {
			if (regionsId != -1) {
				productsRegionsId = regionsProductService.getProductsRegionsId(
						-1, regionsId);
				// 插入区域产品之前，先判断是否已存在
				if (productsRegionsId <= 0) {
					productsRegionsId = regionsProductService
							.generateNewProductRegionId();
					regionsProductService.addProductsRegions(productsRegionsId,
							null, regionsId, regionsName, null, null);
				}
			}

			bspAreaManagerService.upProductRegionsBrandPlace(id, brandId,
					productsRegionsId, placeId);
			result.put("state", "success");
		}

		return result;
	}

	// 获取添加品牌产地添加页面
	@RequestMapping(value = "/toFactoryAdd")
	public String toFactoryAdd(ModelMap m) {
		loggerinfo.info("==========[AreaManagerController toPlaceAdd] called");
		m.put("brandList", bspAreaManagerService.queryBrandList());
		m.put("placeList", bspAreaManagerService.queryPlaceList());

		return "admin/region/factory/factory-add";
	}

	// 获取添加品牌产地编辑页面
	@RequestMapping(value = "/toFactoryEdit")
	public String toFactoryEdit(ModelMap m, HttpServletRequest request) {
		Integer place_id = integer("place_id", request);
		Integer brand_id = integer("brand_id", request);

		m.put("brandList", bspAreaManagerService.queryBrandList());
		m.put("placeList", bspAreaManagerService.queryPlaceList());

		m.put("brand_id", brand_id);
		m.put("place_id", place_id);

		return "admin/region/factory/factory-edit";
	}

	// 获取添加区域产地编辑页面
	@RequestMapping(value = "/toRegionFactoryEdit")
	public String toRegionFactoryEdit(ModelMap m, Integer id) {
		loggerinfo.info("==========[AreaManagerController toPlaceAdd] called");
		m.put("brandList", bspAreaManagerService.queryBrandList());
		m.put("placeList", bspAreaManagerService.queryPlaceList());

		Map mapObj = bspAreaManagerService.selBrandPlaceById(id);
		m.put("brandplace", mapObj);

		return "admin/region/factory/regionfactory-edit";
	}

	// 区域品牌异步查询
	@ResponseBody
	@RequestMapping(params = "method=getBrandByRegionsId")
	public List<Map<String, Object>> getBrandByRegionsId(
			HttpServletRequest request, ModelMap m) {
		int regionsId = integer("regions_id", request);
		loggerinfo.info("==[AreaManagerController areaBrand]---regionsId:"
				+ regionsId);
		List<Map<String, Object>> list = bspAreaManagerService
				.queryBrandListByRegionsid(regionsId);
		return list;
	}

	// 品牌产地批量删除
	@ResponseBody
	@RequestMapping(value = "/factoryListDelete")
	public StatusHtml factoryListDelete(HttpServletRequest request, ModelMap m,
			String ids) {

		int count = 0;
		if (ids != null) {
			String[] idsArr = ids.split(",");
			for (int i = 0; i < idsArr.length; i++) {
				String id = idsArr[i];
				String[] idPro = id.split("_");
				if (idPro.length > 1) {
					count += regionsBrandService.deleteRegionPlace(
							Integer.valueOf(idPro[1]),
							Integer.valueOf(idPro[0]));
				}

			}
		}
		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setNavTabId(Constant.NavTabId_brand_factory);
		// statusHtml.setMessage("删除成功！ 删除数量：" + count);
		statusHtml.setMessage("删除成功！");
		return statusHtml;
	}

}
