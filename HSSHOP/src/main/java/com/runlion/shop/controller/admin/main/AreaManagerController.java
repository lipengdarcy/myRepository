package com.runlion.shop.controller.admin.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.service.BspAreaManagerService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.BspRegionsService;
import com.runlion.shop.service.BspSaleaddressService;
import com.runlion.shop.service.BspUnitService;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsPriceService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.SalerAddressVO;
import com.runlion.shop.vo.SalerProInfor;
import com.runlion.shop.vo.SalerProSelParaVO;

/**
 * 区域controller
 * */
@Controller
@RequestMapping("/admin/area")
public class AreaManagerController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	private String retFlag = "success";

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private RegionsBrandService regionsBrandService;

	@Autowired
	private RegionsPriceService regionsPriceService;

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private BspUnitService unitServerice;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private BspRegionsService bspRegionsService;
	@Autowired
	private BspSaleaddressService bspSaleaddressService;

	// 工厂&门店修改
	@ResponseBody
	@RequestMapping(params = "method=saleaddressEdit")
	public Map<String, Object> saleaddressEdit(HttpServletRequest request,
			SalerAddressVO saleAddress, String delProids, ModelMap m) {
		Map<String, Object> result = getResult();

		String eid = saleAddress.getSaleAddress().getPkcorp();
		if (eid != null && !eid.equals("")) {
			boolean hasPkcorp = bspAreaManagerService.hasNcPkCorp(eid,
					saleAddress.getSaleAddress().getId());
			if (hasPkcorp) {
				result.put("state", "failed");
				result.put("msg", "NC企业编号已经存在");
				return result;
			}
		}

		result = bspSaleaddressService
				.updateSaleAddress(saleAddress, delProids);
		return result;
	}

	// 工厂&门店修改
	@ResponseBody
	@RequestMapping(params = "method=salerefregionsEdit")
	public Map<String, Object> salerefregionsEdit(HttpServletRequest request,
			ModelMap m) {
		int id = integer("id", request);
		String[] regionsIds = request.getParameterValues("lastName");
		String[] regionNames = request.getParameterValues("regions_name");
		loggerinfo.info("==[AreaManagerController saleaddressEdit]---id:" + id);

		Map<String, Object> result = getResult();

		bspAreaManagerService.updateSalerefregions(id, regionsIds, regionNames);
		result.put("state", retFlag);

		return result;
	}

	// 工厂、门店对应的覆盖区域列表
	@RequestMapping(params = "method=toSalerefregionsEdit")
	public String toSalerefregionsEdit(HttpServletRequest request, ModelMap m) {
		int saleid = integer("saleid", request);
		m.put("saleid", saleid);
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		m.put("s", bspAreaManagerService.querySaleaddressByid(saleid));
		m.put("placeList", bspAreaManagerService.queryPlaceList());

		List<BspProductsregions> list = regionsService.getSealAddrProRegions(
				saleid, pageNo, pageSize);
		// List<BspRegions> list = bspAreaManagerService.getSaleAddressRegions(
		// saleid, pageNo, pageSize);
		// Page<BspRegions> page = (Page<BspRegions>) list;
		Page<BspProductsregions> page = (Page<BspProductsregions>) list;
		if (list == null || list.size() == 0)
			return "admin/area/salerefregions-edit";

		// for (BspRegions b : list) {
		// b.setCid(this.calCid(b, b.getRegionid()));
		// }
		long cnt = page.getTotal();
		long cntPage = (cnt / 20) + ((cnt % 20) == 0 ? 0 : 1);
		// m.put("refregionList", list);
		m.put("refProRegionList", list);
		m.put("totalCount", page.getTotal());
		m.put("totalPage", cntPage);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);

		return "admin/area/salerefregions-edit";
	}

	// 工厂、门店对应的覆盖区域列表
	@RequestMapping(params = "method=delSealAddrRefRegionInfor")
	@ResponseBody
	public Map<String, Object> delSealAddrRefRegionInfor(
			HttpServletRequest request, ModelMap m) {
		int saleid = integer("saleid", request);
		int delId = integer("delid", request);
		Map<String, Object> rsInfor = new HashMap();
		bspAreaManagerService.delSaleAddrProRegionsRef(saleid, delId);
		rsInfor.put("status", "ok");
		return rsInfor;
	}

	@RequestMapping(params = "method=toSaleaddressEdit")
	public String toSaleaddressEdit(HttpServletRequest request, ModelMap m) {
		int saleid = integer("saleid", request);
		loggerinfo.info("[AreaManagerController toSaleaddressEdit]---prid:"
				+ " saleid:" + saleid);
		String title = request.getParameter("title");
		m.put("title", title);
		Map<String, Object> addMap = bspAreaManagerService
				.querySaleaddressByid(saleid);
		m.put("s", addMap);
		if (addMap.get("regionid") != null) {
			int regid = (Integer) addMap.get("regionid");
			m.put("cid", regionsService.calCid(regid));
		}
		// proList
		String ncnum = (String) addMap.get("pkcorp");
		if (ncnum != null && !ncnum.trim().equals("")) {
			// List<BspNcenterinfor> proList = bspSaleaddressService
			// .getProlistByNcnum(ncnum);
			SalerProSelParaVO paravo = new SalerProSelParaVO();
			paravo.setPageSize(100);
			List<SalerProInfor> proList = bspProductsService
					.selectSalerProInforlist(saleid, paravo);
			m.put("proList", proList);
		}

		m.put("placeList", bspAreaManagerService.queryPlaceList());
		m.put("wtlist", bspAreaManagerService.selWtListByPickpointId(saleid));

		return "admin/area/saleaddress-edit";
	}

	// 区域工厂/门店删除
	@ResponseBody
	@RequestMapping(params = "method=saleaddressDelete")
	public Map<String, Object> saleaddressDelete(HttpServletRequest request,
			ModelMap m) {
		int saleid = integer("saleid", request);
		loggerinfo.info("[AreaManagerController saleaddressDelete]---prid:"
				+ " saleid:" + saleid);
		bspAreaManagerService.removeSaleaddress(saleid);
		Map<String, Object> result = getResult();
		result.put("state", retFlag);
		return result;
	}

	// 区域工厂/门店添加
	@ResponseBody
	@RequestMapping(params = "method=saleaddressAdd")
	public Map<String, Object> saleaddressAdd(HttpServletRequest request,
			ModelMap m) {
		Map<String, Object> result = getResult();

		loggerinfo.info("==[AreaManagerController saleaddressAdd] called");
		// int regionsId = integer("lastName", request);// regions_id
		// String regionsName = str("regions_name", request);
		String type = str("type", request);
		String name = str("name", request);
		String address = str("address", request);
		String contacts = str("contacts", request);
		String tel = str("tel", request);
		String mobile = str("mobile", request);
		String workTime = str("work_time", request);
		String content = str("content", request);
		int placeid = integer("placeid", request);
		String ncpkcorp = request.getParameter("pkcorp").trim();
		int regionid = integer("regionid", request);
		String regionname = str("regionname", request);

		if (ncpkcorp != null && !ncpkcorp.equals("")) {
			boolean hasPkcorp = bspAreaManagerService.hasNcPkCorp(ncpkcorp,
					null);
			if (hasPkcorp) {
				result.put("state", "failed");
				result.put("msg", "NC企业编号已经存在");
				return result;
			}
		}

		List<BspWorktime> workTimeList = new ArrayList();
		int i = 0;
		while (true) {
			String wttype = request
					.getParameter("workTimeList" + i + ".wttype");
			String wtbegin = request.getParameter("workTimeList" + i
					+ ".wtbegin");
			String wtend = request.getParameter("workTimeList" + i + ".wtend");
			String wtweekbegin = request.getParameter("workTimeList" + i
					+ ".wtweekbegin");
			String wtweekend = request.getParameter("workTimeList" + i
					+ ".wtweekend");

			if (wtbegin == null || wtend == null || wtweekbegin == null
					|| wtweekend == null) {
				break;
			}
			Short iwttype = Short.valueOf(wttype);
			Short iwtweekbegin = Short.valueOf(wtweekbegin);
			Short iwtweekend = Short.valueOf(wtweekend);
			BspWorktime bwt = new BspWorktime();
			bwt.setWttype(iwttype);
			bwt.setWtbegin(wtbegin);
			bwt.setWtend(wtend);
			bwt.setWtweekbegin(iwtweekbegin);
			bwt.setWtweekend(iwtweekend);

			workTimeList.add(bwt);

			i++;
		}

		boolean hasSaved = false;
		if (type.equals("1")) {// 门店
			placeid = -1;
		}
		// else {// 工厂
		// hasSaved = bspAreaManagerService.hasRegionsPlaceOfZT(placeid,
		// regionsId);
		// }

		// if (hasSaved) {
		// result.put("state", "exists");
		// } else {

		BspSaleaddress u = new BspSaleaddress();
		u.setId(null);
		u.setName(name);
		u.setAddress(address);
		u.setTel(tel);
		u.setMobile(mobile);
		u.setContacts(contacts);
		u.setWorktime(workTime);
		u.setContent(content);
		u.setType(type);
		u.setPlaceid(placeid);
		u.setPkcorp(ncpkcorp);
		u.setRegionid(regionid);
		u.setRegionname(regionname);
		u.setOrderexpire(null);

		bspAreaManagerService.addSealAdressOnly(u, workTimeList);

		result.put("state", retFlag);
		// }

		return result;
	}

	@RequestMapping(params = "method=toSaleaddressAdd")
	public String toSaleaddressAdd(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController toSaleaddressAdd] called");
		String type = (String) request.getParameter("type");
		String title = (String) request.getParameter("title");
		m.put("type", type);
		m.put("title", title);
		m.put("placeList", bspAreaManagerService.queryPlaceList());
		return "admin/area/saleaddress-add";
	}

	// 区域厂家&门店管理
	@RequestMapping(value = "/saleaddress")
	public String saleaddress(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		String companyName = request.getParameter("companyName");
		String companyAddr = request.getParameter("companyAddr");
		String companyCont = request.getParameter("companyCont");
		String ncNum = request.getParameter("ncNum");

		List<Map<String, Object>> dataList = bspAreaManagerService
				.querySaleaddressLimit(startNum, pageSize, null, "",
						companyName, companyAddr, companyCont, ncNum);
		int cnt = bspAreaManagerService.countSaleaddress(null, "", companyName,
				companyAddr, companyCont, ncNum);
		// List<Map<String, Object>> dataList = bspAreaManagerService
		// .querySaleaddressLimit(startNum, pageSize, null, "");
		// int cnt = bspAreaManagerService.countSaleaddress(null, "");
		Map<String, Object> result = getResult();
		m.put("status", retFlag);
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("companyName", companyName);
		m.put("companyAddr", companyAddr);
		m.put("companyCont", companyCont);
		m.put("ncNum", ncNum);

		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/area/saleaddress-list";
	}

	// 区域厂家&门店管理
	@RequestMapping(value = "/factoryaddress")
	public String factoryaddress(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		String companyName = request.getParameter("companyName");
		String companyAddr = request.getParameter("companyAddr");
		String companyCont = request.getParameter("companyCont");
		String ncNum = request.getParameter("ncNum");

		List<Map<String, Object>> dataList = bspAreaManagerService
				.querySaleaddressLimit(startNum, pageSize, 2, "", companyName,
						companyAddr, companyCont, ncNum);
		int cnt = bspAreaManagerService.countSaleaddress(2, "", companyName,
				companyAddr, companyCont, ncNum);
		Map<String, Object> result = getResult();
		m.put("status", retFlag);
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("companyName", companyName);
		m.put("companyAddr", companyAddr);
		m.put("companyCont", companyCont);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/area/factoryaddress-list";
	}

	// 区域厂家&门店管理
	@RequestMapping(value = "/storeaddress")
	public String storeaddress(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		String companyName = request.getParameter("companyName");
		String companyAddr = request.getParameter("companyAddr");
		String companyCont = request.getParameter("companyCont");
		String ncNum = request.getParameter("ncNum");

		List<Map<String, Object>> dataList = bspAreaManagerService
				.querySaleaddressLimit(startNum, pageSize, 1, "", companyName,
						companyAddr, companyCont, ncNum);
		int cnt = bspAreaManagerService.countSaleaddress(1, "", companyName,
				companyAddr, companyCont, ncNum);

		Map<String, Object> result = getResult();
		m.put("status", retFlag);
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("companyName", companyName);
		m.put("companyAddr", companyAddr);
		m.put("companyCont", companyCont);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/area/storeaddress-list";
	}

	// 区域厂家管理
	@RequestMapping(value = "/factoryregions")
	public String factoryregions(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		String companyName = request.getParameter("companyName");
		String companyAddr = request.getParameter("companyAddr");
		String companyCont = request.getParameter("companyCont");
		String ncNum = request.getParameter("ncNum");

		List<Map<String, Object>> dataList = bspAreaManagerService
				.querySaleaddressLimit(startNum, pageSize, 2, "", companyName,
						companyAddr, companyCont, ncNum);
		int cnt = bspAreaManagerService.countSaleaddress(2, "", companyName,
				companyAddr, companyCont, ncNum);

		Map<String, Object> result = getResult();
		m.put("status", retFlag);
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("companyName", companyName);
		m.put("companyAddr", companyAddr);
		m.put("companyCont", companyCont);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/area/factoryregions-list";
	}

	// 区域门店管理
	@RequestMapping(value = "/storeregions")
	public String storeregions(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		String companyName = request.getParameter("companyName");
		String companyAddr = request.getParameter("companyAddr");
		String companyCont = request.getParameter("companyCont");
		String ncNum = request.getParameter("ncNum");

		List<Map<String, Object>> dataList = bspAreaManagerService
				.querySaleaddressLimit(startNum, pageSize, 1, "", companyName,
						companyAddr, companyCont, ncNum);
		int cnt = bspAreaManagerService.countSaleaddress(1, "", companyName,
				companyAddr, companyCont, ncNum);

		// List<Map<String, Object>> dataList = bspAreaManagerService
		// .querySaleaddressLimit(startNum, pageSize, 1, "");
		// int cnt = bspAreaManagerService.countSaleaddress(1, "");
		Map<String, Object> result = getResult();
		m.put("status", retFlag);
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("companyName", companyName);
		m.put("companyAddr", companyAddr);
		m.put("companyCont", companyCont);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/area/storeregions-list";
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

}
