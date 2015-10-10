package com.runlion.shop.controller.admin.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.service.BspRegionsService;
import com.runlion.shop.vo.ItemAreaInfor;

/**
 * @Description NewsController控制器
 * @author 赵威
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/areamanager")
public class AreaPlaceManagerController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspRegionsService bspRegionsService;

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[AreaPlaceManagerController load] Start...");
		return "admin/areamanager/areaManager";
	}

	/**
	 * 添加新的区域条目到数据库
	 * 
	 * @param parid
	 *            新添加的区域条目的父级id，没有父级ID为0
	 * @param itemName
	 *            新添加的区域条目的名字
	 * @return 是否添加成功的信息
	 * @throws Exception
	 */
	@RequestMapping(params = "method=addNewItem")
	@ResponseBody
	public Map addNewItem(Integer parid, String[] areaname) throws Exception {
		Map<String, Object> rsMap = new HashMap();
		List<BspRegions> bspRegionsList = null;
		String errinfor = "";
		try {
			bspRegionsList = bspRegionsService.saveNewItem(parid, areaname);
		} catch (Exception ex) {
			errinfor = ex.getMessage();
		}
		rsMap.put("method", "add");
		if (bspRegionsList != null && !bspRegionsList.isEmpty()) {//
			rsMap.put("status", "success");
			rsMap.put("msg", bspRegionsList);
		} else {
			rsMap.put("status", "failed");
			rsMap.put("msg", errinfor);
		}

		return rsMap;
	}

	/**
	 * 编辑已存在的区域的名称
	 * 
	 * @param id
	 *            被编辑的区域的id
	 * @param itemName
	 *            编辑后的区域的名称
	 * @return 是否添加成功的信息
	 * @throws Exception
	 */
	@RequestMapping(params = "method=editItem")
	@ResponseBody
	public Map editItem(Integer id, String areaname) throws Exception {
		Map<String, Object> rsMap = new HashMap();
		String errinfor = "";
		ItemAreaInfor bspRegions = null;
		try {
			bspRegions = bspRegionsService.editItem(id, areaname);
		} catch (Exception ex) {
			errinfor = ex.getMessage();
		}
		rsMap.put("method", "edit");
		if (bspRegions != null) {//
			rsMap.put("status", "success");
			rsMap.put("msg", bspRegions);
		} else {
			rsMap.put("status", "failed");
			rsMap.put("msg", errinfor);
		}

		return rsMap;
	}

	/**
	 * 删除某个区域条目
	 * 
	 * @param id
	 *            要被删除的区域条目的id
	 * @return 是否添加成功的信息
	 * @throws Exception
	 */
	@RequestMapping(params = "method=delItem")
	@ResponseBody
	public Map delItem(String ids) throws Exception {
		Map<String, Object> rsMap = new HashMap();
		String errinfor = "";
		int rsi = 0;
		try {
			rsi = bspRegionsService.delSelAreas(ids);
		} catch (Exception ex) {
			errinfor = ex.getMessage();
		}
		rsMap.put("method", "del");
		if (rsi > 0) {//
			rsMap.put("status", "success");
			rsMap.put("msg", rsi);
		} else {
			rsMap.put("status", "failed");
			rsMap.put("msg", errinfor);
		}

		return rsMap;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 忽略字段绑定异常
		// binder.setIgnoreInvalidFields(true);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "insertTime",
				new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class, "modifyTime",
				new CustomDateEditor(format, true));
		// 子表行数限制的更改，spring mvc默认为256
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);

	}
}
