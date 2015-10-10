package com.runlion.shop.controller.admin.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.entity.BspAttributegroups;
import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspCategories;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAttributevaluesService;
import com.runlion.shop.service.BspCategoriesService;
import com.runlion.shop.vo.AttrValsVO;
import com.runlion.shop.vo.CategoryParentVo;

/**
 * @Description 产品目录 控制器
 * @author lwt
 * @date 2015/07/05 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/category")
public class CategoriesController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	BspCategoriesService bspCategoriesService;
	@Autowired
	BspAttributevaluesService bspAttributevaluesService;

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		String keywords = request.getParameter("keywords");
		String method = request.getMethod();

		if (keywords == null) {
			keywords = "";
		} else {
			if ("GET".equalsIgnoreCase(method)) {
				keywords = keywords;
			}
		}
		m.put("keywords", keywords);

		List<BspCategories> categoryList = bspCategoriesService
				.selectByKeyWord(keywords);
		request.setAttribute("categoryList", categoryList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/category/list";
	}

	@RequestMapping(params = "method=getCateTree", method = RequestMethod.GET)
	public String getCateTree(HttpServletRequest request, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/category/catetree";
	}

	@RequestMapping(params = "method=getCateAttrVals")
	public String getCateAttrVals(HttpServletRequest request,
			@RequestParam("cateid") Short cateid) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		List<AttrValsVO> attrValList = bspCategoriesService
				.selAttrValsVOList(cateid);
		request.setAttribute("attrValList", attrValList);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/category/cateAttrVals";
	}

	@RequestMapping(params = "method=getSKUCateAttrVals")
	public String getSKUCateAttrVals(HttpServletRequest request,
			@RequestParam("cateid") Short cateid) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		List<AttrValsVO> attrValList = bspCategoriesService
				.selAttrValsVOList(cateid);
		request.setAttribute("attrValList", attrValList);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/category/cateSKUAttrVals";
	}

	@RequestMapping(params = "method=getCateTreeJson")
	@ResponseBody
	public List<BspCategories> getCateTreeJson(HttpServletRequest request,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		List<BspCategories> categoryList = bspCategoriesService
				.selectByKeyWord("");
		// request.setAttribute("categoryList", categoryList);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return categoryList;
	}

	@RequestMapping(params = "method=toUpdate")
	public String toUpdate(HttpServletRequest request,
			@RequestParam("cateid") Short id, ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		m.put("topMenu", "编辑");
		m.put("getpost", "method=update");// actionInfo

		List<CategoryParentVo> cateParents = bspCategoriesService
				.getCateParentVos();

		request.setAttribute("cateParents", cateParents);

		BspCategories cateModel = bspCategoriesService.selectByPrimaryKey(id);

		request.setAttribute("props", cateModel);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/category/update";
	}

	@RequestMapping(params = "method=update", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml update(@ModelAttribute("bspCategories") BspCategories u,
	// @ModelAttribute("oldParId") Short oldParId,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		String oldParIdStr = (String) request.getParameter("oldParId");
		Short oldParId = 0;
		if (oldParIdStr != null) {
			oldParId = Short.parseShort(oldParIdStr);
		}
		boolean isOk = bspCategoriesService.updateByPrimaryKeySelective(u,
				oldParId);

		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page8");
			statusHtml.setCallbackType("closeCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toAdd")
	public String toAdd(HttpServletRequest request, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		m.put("topMenu", "添加");
		m.put("getpost", "method=add");

		List<CategoryParentVo> cateParents = bspCategoriesService
				.getCateParentVos();

		request.setAttribute("cateParents", cateParents);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/category/add";
	}

	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml add(@ModelAttribute("bspCategories") BspCategories u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		boolean isOk = bspCategoriesService.addBspCategories(u);
		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page8");
			statusHtml.setCallbackType("closeCurrent");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=delete", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml delete(@RequestParam("cateid") Short id) throws Exception {
		loggerinfo.info("==========[NewsController delete] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspCategoriesService.deleteByPrimaryKey(id)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page8");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController delete] End...");
		return statusHtml;
	}

	// @InitBinder
	// protected void initBinder(HttpServletRequest request,
	// ServletRequestDataBinder binder) throws Exception {
	// // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// CustomDateEditor dateEditor = new CustomDateEditor(format, true);
	// binder.registerCustomEditor(Date.class, dateEditor);
	// super.initBinder(request, binder);
	// }
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 忽略字段绑定异常
		// binder.setIgnoreInvalidFields(true);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "insertTime",
				new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class, "modifyTime",
				new CustomDateEditor(format, true));
	}

	// 属性分组
	@RequestMapping(params = "method=attributegrouplist")
	public String attributegrouplist(HttpServletRequest request,
			@RequestParam("cateid") Short cateid,
			@RequestParam("catename") String catename, ModelMap m)
			throws Exception {
		loggerinfo
				.info("==========[NewsController attributegrouplist] Start...");

		List<BspAttributegroups> attributegrouplist = bspCategoriesService
				.getAttributegrouplist(cateid);
		request.setAttribute("attrValList", attributegrouplist);
		request.setAttribute("cateid", cateid);
		request.setAttribute("catename", catename);

		loggerinfo.info("==========[NewsController attributegrouplist] End...");
		return "admin/category/attrGroupList";
	}

	// 属性列表
	@RequestMapping(params = "method=attributelist")
	public String attributelist(HttpServletRequest request,
			@RequestParam("cateid") Short cateid,
			@RequestParam("catename") String catename, ModelMap m)
			throws Exception {
		loggerinfo
				.info("==========[NewsController attributegrouplist] Start...");

		List attributelist = bspCategoriesService.getAttributelist(cateid);
		request.setAttribute("attrValList", attributelist);
		request.setAttribute("cateid", cateid);
		request.setAttribute("catename", catename);

		loggerinfo.info("==========[NewsController attributegrouplist] End...");
		return "admin/category/attrList";
	}

	@RequestMapping(params = "method=toAddAttrGroup")
	public String toAddAttrGroup(HttpServletRequest request,
			@RequestParam("catename") String catename, Short cateid, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAddAttrGroup] Start...");

		request.setAttribute("catename", catename);
		request.setAttribute("cateid", cateid);
		loggerinfo.info("==========[NewsController toAddAttrGroup] End...");
		return "admin/category/attrGroupUpdate";
	}

	@RequestMapping(params = "method=updateAttrGroup", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml updateAttrGroup(HttpServletRequest request,
			Short attrgroupid, Short cateid, String name, int displayorder)
			throws Exception {
		loggerinfo.info("==========[NewsController toAddAttrGroup] Start...");
		StatusHtml statusHtml = new StatusHtml();
		BspAttributegroups groupModel = bspCategoriesService
				.getAttributegroup(attrgroupid);
		BspAttributegroups bg = new BspAttributegroups();
		bg.setAttrgroupid(attrgroupid);
		bg.setName(name);
		bg.setDisplayorder(displayorder);
		bg.setCateid(cateid);
		try {
			if (null != groupModel) {
				int record = bspCategoriesService.updateAttrGroup(bg);
				if (record > 0) {
					statusHtml.setStatusCode("200");
					statusHtml.setNavTabId("pageedit");
					statusHtml.setMessage("操作成功");
				}
			} else {
				bspCategoriesService.saveAttrGroup(bg);
				statusHtml.setStatusCode("200");
				statusHtml.setNavTabId("pageedit");
				statusHtml.setMessage("操作成功");
			}
		} catch (Exception e) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败");
			loggererror.error(e.toString());
		}
		loggerinfo.info("==========[NewsController toAddAttrGroup] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=updateAttr", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml updateAttr(HttpServletRequest request, Short attrgroupid,
			Short attrid, byte isfilter, Short cateid, String name,
			int displayorder, int skuorder) throws Exception {
		loggerinfo.info("==========[NewsController updateAttr] Start...");
		StatusHtml statusHtml = new StatusHtml();
		// BspAttributes groupModel = bspCategoriesService
		// .getAttribute(attrgroupid);
		BspAttributes ba = new BspAttributes();

		ba.setAttrgroupid(attrgroupid);
		ba.setName(name);
		ba.setDisplayorder(displayorder);
		ba.setCateid(cateid);
		ba.setShowtype((byte) 0);
		ba.setIsfilter(isfilter);
		ba.setSkuorder(skuorder);
		try {
			if (null != attrid) {
				ba.setAttrid(attrid);
				int record = bspCategoriesService.updateAttr(ba);
				if (record > 0) {
					statusHtml.setStatusCode("200");
					statusHtml.setNavTabId("pageattredit");
					statusHtml.setMessage("操作成功");
				}
			} else {
				bspCategoriesService.saveAttrGroup(ba);
				statusHtml.setStatusCode("200");
				statusHtml.setNavTabId("pageattredit");
				statusHtml.setMessage("操作成功");
			}
		} catch (Exception e) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败");
			loggererror.error(e.toString());
		}
		loggerinfo.info("==========[NewsController updateAttr] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toUpdateAttrGroup")
	public String toUpdateAttrGroup(HttpServletRequest request,
			@RequestParam("attrgroupid") Short id,
			@RequestParam("catename") String catename, Short cateid, ModelMap m)
			throws Exception {
		loggerinfo
				.info("==========[NewsController toUpdateAttrGroup] Start...");

		BspAttributegroups groupModel = bspCategoriesService
				.getAttributegroup(id);

		request.setAttribute("props", groupModel);
		request.setAttribute("catename", catename);
		request.setAttribute("cateid", cateid);
		loggerinfo.info("==========[NewsController toUpdateAttrGroup] End...");
		return "admin/category/attrGroupUpdate";
	}

	@RequestMapping(params = "method=toUpdateAttr")
	public String toUpdateAttr(HttpServletRequest request,
			@RequestParam("attrgroupid") Short id,
			@RequestParam("catename") String catename, String name,
			Short cateid, ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toUpdateAttr] Start...");

		BspAttributes groupModel = bspCategoriesService.getAttribute(id);

		request.setAttribute("props", groupModel);
		request.setAttribute("catename", catename);
		request.setAttribute("name", name);
		request.setAttribute("cateid", cateid);
		loggerinfo.info("==========[NewsController toUpdateAttr] End...");
		return "admin/category/attrUpdate";
	}

	@RequestMapping(params = "method=deleteAttributeGroup", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml deleteAttributeGroup(@RequestParam("cateid") Short id)
			throws Exception {
		loggerinfo
				.info("==========[NewsController deleteAttributeGroup] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspCategoriesService.deleteAttributegroup(id) > 0) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("pageedit");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo
				.info("==========[NewsController deleteAttributeGroup] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=deleteAttribute")
	@ResponseBody
	public StatusHtml deleteAttribute(@RequestParam("attrid") Short id)
			throws Exception {
		loggerinfo.info("==========[NewsController deleteAttribute] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspCategoriesService.deleteAttribute(id) > 0) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("pageattredit");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController deleteAttribute] End...");
		return statusHtml;
	}

	// 属性值列表
	@RequestMapping(params = "method=attrValList")
	public String attrValList(@RequestParam("attrid") Short attrid,
			@RequestParam("attrname") String attrName, ModelMap m)
			throws Exception {
		loggerinfo
				.info("==========[NewsController attributegrouplist] Start...");
		List<BspAttributevalues> attrValList = bspCategoriesService
				.getBspAttributevaluesByAttrid(attrid);
		m.put("attrValList", attrValList);
		m.put("attrid", attrid);
		m.put("attrname", attrName);

		loggerinfo.info("==========[NewsController attributegrouplist] End...");
		return "admin/category/attrValList";
	}

	// 属性值添加模板
	@RequestMapping(params = "method=toAddAttrVal")
	public String toAddAttrVal(@RequestParam("attrid") Short attrid,
			@RequestParam("attrname") String attrName, ModelMap m)
			throws Exception {
		loggerinfo
				.info("==========[NewsController attributegrouplist] Start...");

		m.put("attrid", attrid);
		m.put("attrname", attrName);

		loggerinfo.info("==========[NewsController attributegrouplist] End...");
		return "admin/category/addAttrValTmp";
	}

	@RequestMapping(params = "method=addAttrVal", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml addAttrVal(BspAttributevalues u, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController deleteAttribute] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspAttributevaluesService.saveAttrVal(u)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController deleteAttribute] End...");
		return statusHtml;
	}

	// 属性值添加模板
	@RequestMapping(params = "method=toEditAttrVal")
	public String toEditAttrVal(@RequestParam("attrvalid") Integer attrvalid,
			ModelMap m) throws Exception {
		loggerinfo
				.info("==========[NewsController attributegrouplist] Start...");

		m.put("attrvalid", attrvalid);
		BspAttributevalues attrVal = bspAttributevaluesService
				.getBspAttributevaluesById(attrvalid);
		m.put("attrVal", attrVal);

		loggerinfo.info("==========[NewsController attributegrouplist] End...");
		return "admin/category/editAttrValTmp";
	}

	@RequestMapping(params = "method=editAttrVal", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml editAttrVal(BspAttributevalues u, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController deleteAttribute] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspAttributevaluesService.uptBspAttributevalues(u)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("pageattrval");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController deleteAttribute] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=delAttrVal")
	@ResponseBody
	public StatusHtml delAttrVal(@RequestParam("attrid") Integer attrid,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController deleteAttribute] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspAttributevaluesService.delAttrVal(attrid)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("pageattrval");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController deleteAttribute] End...");
		return statusHtml;
	}

}
