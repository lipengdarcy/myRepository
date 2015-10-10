package com.runlion.shop.controller.home.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.CookieHanler;
import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.JspToHtml;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.common.util.uploadfile.FileUpload;
import com.runlion.shop.common.util.uploadfile.UploadFilePathEnum;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrderproductsExample;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspRegionsExample;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.BspRegionsService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.WebConfigVO;

/**
 * 工具控制器，返回json格式数据
 *
 * @2015年7月2日 by linyj
 * @2015年9月28日 zhaowei 增加市级区域
 */
@Controller
@RequestMapping("/tool")
public class ToolController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	private String retFlag = "success";

	@Autowired
	private BspRegionsService bspRegionsService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private BspOrdersMapper bspOrdersMapper;

	@Autowired
	private BspOrderproductsMapper bspOrderproductsMapper;

	public ToolController() {
		loggerinfo.info("==========ToolController() ...");

	}

	private Map<String, Object> getResult() {
		return new HashMap<String, Object>();
	}

	// 获取省级列表
	@ResponseBody
	@RequestMapping(value = "/city")
	public Map<String, Object> city(String provinceId) {
		loggerinfo.info("==========[ToolController city] Start... provinceId="
				+ provinceId);
		List<BspRegions> list = null;
		if (StringUtils.isNotEmpty(provinceId)) {
			int parentid = Integer.parseInt(provinceId);
			BspRegions region = bspRegionsService.queryRegionByid(parentid);
			if (region != null && region.getLayer() == 1) {
				list = new ArrayList<BspRegions>();
				BspRegions jh = new BspRegions();
				jh.setRegionid(212131);
				jh.setName("金华市");
				jh.setLayer((byte) 2);
				jh.setProvinceid(192224);
				jh.setCityid(0);
				jh.setCountyid(0);
				jh.setStreetid(0);
				list.add(jh);
				BspRegions qz = new BspRegions();
				qz.setRegionid(217443);
				qz.setName("衢州市");
				qz.setLayer((byte) 2);
				qz.setProvinceid(192224);
				qz.setCityid(0);
				qz.setCountyid(0);
				qz.setStreetid(0);
				list.add(qz);
			} else {
				list = bspRegionsService.queryCity(parentid);
			}
		}
		List<Map<String, Object>> regionList = getRegionList(list);
		Map<String, Object> result = getResult();
		result.put("content", regionList);
		result.put("state", retFlag);
		loggerinfo.info("==========[ToolController city] End...");
		return result;
	}

	// 获取省级列表
	@ResponseBody
	@RequestMapping(value = "/province")
	public Map<String, Object> province() {
		loggerinfo.info("==========[ToolController province] Start...");
		// List<BspRegions> list = bspRegionsService.queryProvince();
		List<BspRegions> list = new ArrayList<BspRegions>();
		BspRegions zj = new BspRegions();
		zj.setRegionid(192224);
		zj.setName("浙江省");
		zj.setLayer((byte) 1);
		zj.setProvinceid(0);
		zj.setCityid(0);
		zj.setCountyid(0);
		zj.setStreetid(0);
		list.add(zj);

		List<Map<String, Object>> regionList = getRegionList(list);
		Map<String, Object> result = getResult();
		result.put("content", regionList);
		result.put("state", retFlag);
		loggerinfo.info("==========[ToolController province] End...");
		return result;
	}

	/**
	 * TODO(处理返回行政区域集合) 2015/8/8 17：54 剔除市辖区下级没有数据的情况
	 * 
	 * @param list
	 * @return List<Map<String,Object>> 返回类型
	 */
	private List<Map<String, Object>> getRegionList(List<BspRegions> list) {
		List<Map<String, Object>> regionList = new ArrayList<Map<String, Object>>();
		if (!CollectionUtils.isEmpty(list)) {
			for (BspRegions br : list) {
				int regioncityidcitystaut = 0;// 市辖区下级没有行政区域状态 0默认有 1没有
				if (StringUtils.trim(br.getName()).equals("市辖区")) {
					BspRegionsExample e = new BspRegionsExample();
					e.createCriteria().andParentidEqualTo(br.getRegionid());
					int regioncityidcount = bspRegionsService.countByExample(e);
					if (regioncityidcount == 0) {
						regioncityidcitystaut = 1;
					}
				}
				if (regioncityidcitystaut == 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", br.getRegionid() + "");
					map.put("name", StringUtils.trim(br.getName()));
					map.put("layer", br.getLayer() + "");
					map.put("ProvinceId", br.getProvinceid() + "");
					map.put("CityId", br.getCityid() + "");
					map.put("CountyId", br.getCountyid() + "");
					map.put("StreetId", br.getStreetid() + "");
					regionList.add(map);
				}
			}
		}
		return regionList;
	}

	// 上方菜单链接
	@RequestMapping(value = "/topMenu")
	public String topMenuLink(ModelMap m) {

		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);
		@SuppressWarnings("unchecked")
		List<ProductComboInfo> menuLink = (List<ProductComboInfo>) EHCacheUtil
				.get("menuLink");
		m.put("menuLink", menuLink);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includes/topMenu";
	}

	// 新的上方菜单链接
	@RequestMapping(value = "/newTopMenu")
	public String newTopMenuLink(ModelMap m) {

		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);
		@SuppressWarnings("unchecked")
		List<ProductComboInfo> menuLink = (List<ProductComboInfo>) EHCacheUtil
				.get("menuLink");
		m.put("menuLink", menuLink);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includesnew/topMenu";
	}

	// 新的幻灯片页面链接
	@RequestMapping(value = "/newBanner")
	public String newBanner(ModelMap m) {

		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includesnew/banner";
	}

	// 新的服务信息列表的链接
	@RequestMapping(value = "/newService")
	public String newService(ModelMap m) {

		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includesnew/serviceList";
	}

	// 新的合作伙伴的链接
	@RequestMapping(value = "/newPartner")
	public String newPartner(ModelMap m) {

		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includesnew/partner";
	}

	// 左侧菜单链接
	@RequestMapping(value = "/leftMenu")
	public String leftMenuLink(ModelMap m) {
		loggerinfo.info("==========[ToolController menuLink] Start...");
		// 从cookie取收货地址id
		// String areaId = CookieHanler.getLastAreaFromCookie(request);

		@SuppressWarnings("unchecked")
		List<ProductComboInfo> menuLink = (List<ProductComboInfo>) EHCacheUtil
				.get("menuLink");
		m.put("menuLink", menuLink);

		loggerinfo.info("==========[ToolController menuLink] End...");
		return "home/includes/leftMenu";
	}

	// 网站配置信息(包括： 产品的售后保障信息等)
	@RequestMapping(value = "/webinfo")
	public String SiteInfo(@RequestParam(defaultValue = "0") Integer id,
			ModelMap m) {
		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		m.put("webinfor", configVo);
		return "home/includes/leftMenu";
	}

	// 订单列表点击商品生成快照
	@RequestMapping(value = "/snapshot")
	public String snapshot(@RequestParam(defaultValue = "0") Integer oid,
			@RequestParam(defaultValue = "0") Integer pid,
			HttpServletRequest request, ModelMap m) {

		BspOrders bo = bspOrdersMapper.selectByPrimaryKey(oid); // 订单
		BspOrderproducts p = null;
		BspOrderproductsExample e = new BspOrderproductsExample();
		e.createCriteria().andPidEqualTo(pid).andOidEqualTo(oid);
		List<BspOrderproducts> list = bspOrderproductsMapper.selectByExample(e);
		if (list != null && list.size() > 0)
			p = list.get(0);
		// begin 如果产品下架不存在，则不生成
		BspAdminuser bspAdminuser = (BspAdminuser) request.getSession()
				.getAttribute("adminuser");
		String areaId = CookieHanler.getLastAreaFromCookie(request);
		BspProducts entity = bspProductsService.selectByPrimaryKeyState(pid,
				bspAdminuser, Integer.valueOf(areaId));
		if (entity == null) {
			return null;
		}
		// end

		String allarea = "0-0-0-0-0-0";
		BspRegions b = regionsService.getResionsById(bo.getRegionid());
		if (b != null)
			allarea = b.getParentid() + "-" + b.getCityid() + "-"
					+ b.getCountyid() + "-" + b.getStreetid() + "-"
					+ b.getRegionid() + "-0";

		String cookie = "JSESSIONID=A57F6493C5D456A0B6487A3AD31B6F1F; allarea="
				+ allarea + "; a2404_pages=5; a2404_times=1 ;lastarea="
				+ Integer.toString(bo.getRegionid());
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		String urlStr = realPath + "/product/detail.do?id=" + p.getPid();

		// 路径
		UploadFilePathEnum orderState = EnumsUtil.valueOf(
				UploadFilePathEnum.class, (byte)9);
		String source = FileUpload.getUploadFilePath(orderState);

		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ source + p.getUrl();
		File f = new File(filePath);
		if (!f.exists()) {
			loggerinfo.info("==========[" + urlStr + "]-[" + filePath + "]-["
					+ p.getPid() + "]-[" + realPath + "]-[" + cookie + "] ...");
			JspToHtml.JspToHtmlByURL(urlStr, filePath, p.getPid(), realPath,
					cookie);
		}

		m.put("url", p.getUrl());

		return "/home/center/snapshot";
	}
}
