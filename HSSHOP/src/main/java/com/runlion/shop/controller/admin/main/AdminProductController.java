package com.runlion.shop.controller.admin.main;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.uploadfile.FileUpload;
import com.runlion.shop.entity.BspCategories;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspProductattributes;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductskus;
import com.runlion.shop.entity.BspProductstocks;
import com.runlion.shop.entity.BspSkugroup;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspCategoriesService;
import com.runlion.shop.service.BspNcenterinforService;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.BspSkugroupService;
import com.runlion.shop.service.BspUnitService;
import com.runlion.shop.service.ProductimagesService;
import com.runlion.shop.service.product.BspProductSkuService;
import com.runlion.shop.vo.ProductAttrVO;
import com.runlion.shop.vo.ProductSkuVO;
import com.runlion.shop.vo.SkuShowVO;
import com.runlion.shop.vo.product.BspNcenterinforList;
import com.runlion.shop.vo.product.SelAllProlistResVO;
import com.runlion.shop.vo.product.SelAllProlistVO;
import com.runlion.shop.vo.product.SelStoreAndNcProinfor;
import com.runlion.shop.vo.product.SelStoreAndNcProinforRes;
import com.runlion.shop.vo.product.StoreAndNcProinfor;

/**
 * @Description NewsController控制器
 * @author 赵威
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/adminproduct")
public class AdminProductController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService bspProductsService;
	@Autowired
	private BspProductSkuService bspProductSkuService;

	@Autowired
	private BspCategoriesService bspCategoriesService;
	@Autowired
	private ProductimagesService productimagesService;
	@Autowired
	private BspUnitService unitServerice;
	@Autowired
	BspSkugroupService bspSkugroupService;
	@Autowired
	BspNcenterinforService bspNcenterinforService;

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String keyWord = request.getParameter("keywords");
		String method = request.getMethod();

		if (keyWord == null) {
			keyWord = "";
		}

		m.put("keywords", keyWord);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminproduct/list";
	}

	/**
	 * 在售商品列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=skugProList")
	public String skugProList(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String pageNumberStr = request.getParameter("pageNum");
		String numPerPageStr = request.getParameter("numPerPage");
		String keywords = request.getParameter("keywords");
		String skugid = request.getParameter("skugid");
		String status = request.getParameter("status");

		if (keywords == null) {
			keywords = "";
		}
		m.put("keywords", keywords);
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(pageNumberStr);
		} catch (Exception e) {
			pageNumber = 1;
		}

		int numPerPage = 20;
		try {
			numPerPage = Integer.parseInt(numPerPageStr);
		} catch (Exception e) {
			numPerPage = 20;
		}

		m.put("pageNum", pageNumber);

		m.put("numPerPage", numPerPage);
		int totalCount = bspProductsService.countSkugProductList(keywords,
				skugid, status);
		m.put("totalCount", totalCount);
		m.put("skugid", skugid);
		m.put("status", status);

		// List<BspProducts> proList = bspProductsService
		// .selOnProductList((String) m.get("keywords"));

		List<BspProducts> proList = new ArrayList();
		try {
			proList = bspProductsService.selSkugProductList(keywords, skugid,
					status, pageNumber, numPerPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.put("actionlist", proList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminproduct/skugProList";
	}

	/**
	 * 在售商品列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=onList")
	public String onList(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String pageNumberStr = request.getParameter("pageNum");
		String numPerPageStr = request.getParameter("numPerPage");
		String keywords = request.getParameter("keywords");

		if (keywords == null) {
			keywords = "";
		}
		m.put("keywords", keywords);
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(pageNumberStr);
		} catch (Exception e) {
			pageNumber = 1;
		}

		int numPerPage = 20;
		try {
			numPerPage = Integer.parseInt(numPerPageStr);
		} catch (Exception e) {
			numPerPage = 20;
		}

		m.put("pageNum", pageNumber);

		m.put("numPerPage", numPerPage);
		int totalCount = bspProductsService.selOnProductListCount(keywords);
		m.put("totalCount", totalCount);

		// List<BspProducts> proList = bspProductsService
		// .selOnProductList((String) m.get("keywords"));

		List<BspProducts> proList = new ArrayList();
		try {
			proList = bspProductsService.selOnProductList(keywords, pageNumber,
					numPerPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.put("actionlist", proList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminproduct/onList";
	}

	/**
	 * sku 组列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=skugroupList")
	public String skugroupList(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String pageNumberStr = request.getParameter("pageNum");
		String numPerPageStr = request.getParameter("numPerPage");
		String keywords = request.getParameter("keywords");
		String method = request.getMethod();

		if (keywords == null) {
			keywords = "";
		}
		m.put("keywords", keywords);
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(pageNumberStr);
		} catch (Exception e) {
			pageNumber = 1;
		}

		int numPerPage = 20;
		try {
			numPerPage = Integer.parseInt(numPerPageStr);
		} catch (Exception e) {
			numPerPage = 20;
		}

		m.put("pageNum", pageNumber);

		m.put("numPerPage", numPerPage);
		int totalCount = bspProductsService.selSkugroupListCount(keywords);
		m.put("totalCount", totalCount);

		// List<BspProducts> proList = bspProductsService
		// .selOnProductList((String) m.get("keywords"));

		List<BspSkugroup> skugList = new ArrayList();
		try {
			skugList = bspProductsService.selSkugroupList(keywords, pageNumber,
					numPerPage);
			List<Integer> cateIds = new ArrayList();
			for (int i = 0; i < skugList.size(); i++) {
				cateIds.add(skugList.get(i).getCateid());
			}
			Map<Short, String> idNameMap = bspCategoriesService
					.getCateIdNameMap(cateIds);
			for (int i = 0; i < skugList.size(); i++) {
				Short tsid = skugList.get(i).getCateid().shortValue();
				String name = idNameMap.get(tsid);
				skugList.get(i).setCateName(name);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.put("actionlist", skugList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminproduct/skugroupList";
	}

	/**
	 * 下架商品列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=downList")
	public String downList(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String pageNumberStr = request.getParameter("pageNum");
		String numPerPageStr = request.getParameter("numPerPage");
		String keywords = request.getParameter("keywords");
		String method = request.getMethod();

		if (keywords == null) {
			keywords = "";
		}
		m.put("keywords", keywords);
		int pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(pageNumberStr);
		} catch (Exception e) {
			pageNumber = 1;
		}

		int numPerPage = 20;
		try {
			numPerPage = Integer.parseInt(numPerPageStr);
		} catch (Exception e) {
			numPerPage = 20;
		}

		m.put("pageNum", pageNumber);

		m.put("numPerPage", numPerPage);
		int totalCount = bspProductsService.selDownProductListCount(keywords);
		m.put("totalCount", totalCount);
		// List<BspProducts> proList = bspProductsService
		// .selOnProductList((String) m.get("keywords"));

		List<BspProducts> proList = new ArrayList();
		try {
			proList = bspProductsService.selDownProductList(keywords,
					pageNumber, numPerPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.put("actionlist", proList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminproduct/downList";
	}

	@RequestMapping(params = "method=toAdd")
	public String toAdd(ModelMap m, String pageid) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		m.put("topMenu", "添加商品信息");
		m.put("getpost", "method=add");
		List<BspUnit> unitList = unitServerice.searchUnit("");
		m.put("unitlist", unitList);

		List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		m.put("weightUnitList", weightUnitList);

		m.put("pageid", pageid);
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/add";
	}

	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml add(ProductAttrVO u, String pageid,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		StatusHtml statusHtml = new StatusHtml();
		boolean isOk = true;
		String ncpronum = u.getProduct().getNcpronum().trim();
		// 检查NC编号是否重复
		if (ncpronum != null && !ncpronum.equals("")) {
			if (bspProductsService.hasNcPronum(ncpronum, null)) {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("物料的NC编号已经存在");
				isOk = false;
			}
		}
		if (isOk) {
			// 检查产品目录号是否存在
			if (u.getProduct().getCateid() <= 0) {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作错误,你必须选择物料种类信息");
			} else {
				isOk = bspProductsService.saveProductWithAttr(u);

				if (isOk) {
					statusHtml.setStatusCode("200");
					statusHtml.setMessage("操作成功");
					if (pageid != null && pageid.trim().length() > 0) {
						statusHtml.setNavTabId(pageid);
						statusHtml.setCallbackType("closeCurrent");
					} else {
						statusHtml.setNavTabId("");
					}

				} else {
					statusHtml.setStatusCode("300");
					statusHtml.setMessage("操作错误");
				}
			}
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toAddSKU")
	public String toAddSKU(ModelMap m, String pageid) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		m.put("topMenu", "添加SKU");
		m.put("getpost", "method=addSKU");
		List<BspUnit> unitList = unitServerice.searchUnit("");
		m.put("unitlist", unitList);

		List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		m.put("weightUnitList", weightUnitList);

		m.put("pageid", pageid);
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/addSKU";
	}

	@RequestMapping(params = "method=addSKU", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml addSKU(ProductSkuVO u, String pageid,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		String[] skuNames = (String[]) request
				.getParameterValues("skuListName");

		StatusHtml statusHtml = new StatusHtml();

		if (u.getProduct().getCateid() <= 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,你必须选择物料种类信息");
		} else if (u.getSkuList().size() == 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,生成SKU时必须添加属性");
		} else {
			boolean isOk = true;
			String errMsg = "操作错误";

			int sameNameCount = 0;

			String name = u.getSkuGroup().getSkugname();

			// if (name.trim().equals("")) {
			// isOk = false;
			// errMsg = "sku的组名称不能为空";
			// } else {
			// sameNameCount = bspProductsService.countSkugname(name.trim());
			// if (sameNameCount > 0) {
			// isOk = false;
			// errMsg = "sku的组名称重复";
			// } else {
			isOk = bspProductsService.saveSrcProductSkuVO(u, skuNames);
			// }
			// }

			if (isOk) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				if (pageid != null && pageid.trim().length() > 0) {
					statusHtml.setNavTabId(pageid);
					statusHtml.setCallbackType("closeCurrent");
				} else {
					statusHtml.setNavTabId("");
				}

			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage(errMsg);
			}
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=delete", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml delete(@RequestParam("pid") Integer id, String pageid)
			throws Exception {
		loggerinfo.info("==========[NewsController delete] Start...");
		boolean isOk = bspProductsService.delProductByPid(id);
		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			if (pageid != null && pageid.trim().length() > 0) {
				statusHtml.setNavTabId(pageid);
			} else {
				statusHtml.setNavTabId("page11");
			}
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController delete] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toUpdate")
	public String toUpdate(@RequestParam("pid") Integer id, String pageid,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=update");// actionInfo
		List<BspUnit> unitList = unitServerice.searchUnit("");
		m.put("unitlist", unitList);

		List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		m.put("weightUnitList", weightUnitList);

		BspProducts product = bspProductsService.selProductByPid(id);
		m.put("product", product);

		BspProductstocks productstocks = bspProductsService
				.selProductstocksByPid(id);
		m.put("productstocks", productstocks);

		BspCategories bspCategories = bspCategoriesService
				.selectByPrimaryKey(product.getCateid());

		m.put("bspCategories", bspCategories);

		m.put("pageid", pageid);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/update";
	}

	@RequestMapping(params = "method=update", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml update(ProductAttrVO u, String pageid,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		StatusHtml statusHtml = new StatusHtml();
		String ncpronum = u.getProduct().getNcpronum().trim();
		boolean isOk = true;
		// 检查NC编号是否存在
		if (ncpronum != null && !ncpronum.equals("")) {
			// 将去除前后空格的nc编号存进对象
			u.getProduct().setNcpronum(ncpronum);
			if (bspProductsService.hasNcPronum(ncpronum, u.getProduct()
					.getPid())) {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("物料的NC编号已经存在");
				isOk = false;
			}
		}
		if (isOk) {
			isOk = bspProductsService.upProductWithAttr(u);

			if (isOk) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				if (pageid != null && pageid.trim().length() > 0) {
					statusHtml.setNavTabId(pageid);
				} else {
					statusHtml.setNavTabId("page11");
				}
				statusHtml.setCallbackType("closeCurrent");
			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作错误");
			}
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toUpdateSkugroup")
	public String toUpdateSkugroup(@RequestParam("skugid") Integer id,
			String pageid, ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=updateSkugroup");// actionInfo
		List<BspUnit> unitList = unitServerice.searchUnit("");
		m.put("unitlist", unitList);

		List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		m.put("weightUnitList", weightUnitList);

		// BspProducts product = bspProductsService.selProductByPid(id);
		// m.put("product", product);
		//
		// BspProductstocks productstocks = bspProductsService
		// .selProductstocksByPid(id);
		// m.put("productstocks", productstocks);

		BspSkugroup skugroup = bspProductsService.selSkugroupByKeyid(id);
		m.put("skuGroup", skugroup);

		BspCategories bspCategories = bspCategoriesService
				.selectByPrimaryKey(skugroup.getCateid().shortValue());

		m.put("bspCategories", bspCategories);

		m.put("pageid", pageid);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/skugroupEdit";
	}

	// skugroup编辑
	@RequestMapping(params = "method=updateSkugroup", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml updateSkugroup(ProductSkuVO u, String pageid,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("sku编辑 Start...");
		String[] skuNames = (String[]) request
				.getParameterValues("skuListName");

		StatusHtml statusHtml = new StatusHtml();

		if (u.getProduct().getCateid() <= 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,你必须选择物料种类信息");
		} else if (u.getSkuList().size() == 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,生成SKU时必须添加属性");
		} else {
			boolean isOk = true;
			String errMsg = "操作错误";

			int sameNameCount = 0;
			String name = u.getSkuGroup().getSkugname();

			// if (name.trim().equals("")) {
			// isOk = false;
			// errMsg = "sku的组名称不能为空";
			// } else {
			// sameNameCount = bspProductsService.countSkugname(name.trim());
			// if (sameNameCount > 0) {
			// isOk = false;
			// errMsg = "sku的组名称重复";
			// } else {
			isOk = bspProductsService.upSrcProductSkuVO(u, skuNames);
			// }
			// }

			if (isOk) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				if (pageid != null && pageid.trim().length() > 0) {
					statusHtml.setNavTabId(pageid);
					statusHtml.setCallbackType("closeCurrent");
				} else {
					statusHtml.setNavTabId("");
				}

			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage(errMsg);
			}
		}

		loggerinfo.info("sku编辑 End...");
		return statusHtml;
	}

	// skugroup编辑
	@RequestMapping(params = "method=delSkugroup")
	@ResponseBody
	public StatusHtml updateSkugroup(Integer skugid, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("sku编辑 Start...");

		boolean isOk = true;
		String errMsg = "操作错误";

		isOk = bspProductsService.delSkugroupById(skugid);

		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("");
		statusHtml.setCallbackType("");

		loggerinfo.info("sku编辑 End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=getProAttrList")
	@ResponseBody
	public List<BspProductattributes> getProAttrList(Integer pid)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		List<BspProductattributes> proAttrList = bspProductsService
				.selBspProductattributesByPId(pid);
		loggerinfo.info("==========[NewsController add] End...");
		return proAttrList;
	}

	@RequestMapping(params = "method=getProSkuAttrList")
	@ResponseBody
	public List<BspProductskus> getProSkuAttrList(
			@RequestParam("skugid") Integer skugid) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		List<BspProductskus> proSkuAttrList = bspProductSkuService
				.getProductSkuListByGroup(skugid);
		loggerinfo.info("==========[NewsController add] End...");
		return proSkuAttrList;
	}

	@RequestMapping(params = "method=getSkuShowPage")
	public String getSkuShowPage(@RequestParam("pid") Integer id, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");

		List<SkuShowVO> skuShowVOList = bspProductsService.getSkuShowVOList(id);
		m.put("skuShowVOList", skuShowVOList);
		if (skuShowVOList == null || skuShowVOList.isEmpty()) {
			m.put("isEmptySku", "true");
		}

		BspProducts b = bspProductsService.selectByPrimaryKey(id);
		List<BspProductskus> skuList = bspProductSkuService
				.getProductSkuListByGroup(b.getSkugid());
		m.put("skuList", skuList);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/skuShowPage";
	}

	@RequestMapping(params = "method=dlimgurl")
	public void dlimgurl(@RequestParam("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			response.setContentType("image/jpeg; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName,
					request, null)));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	//
	@RequestMapping(params = "method=toProductImg")
	public String toProductImg(@RequestParam("pid") Integer id, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=update");// actionInfo

		List<BspProductimages> productPics = bspProductsService
				.getAllProductPics(id);

		m.put("productPics", productPics);
		m.put("productid", id);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/productImg";
	}

	//
	@RequestMapping(params = "method=addProductImg")
	@ResponseBody
	public StatusHtml addProductImg(BspProductimages u, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		StatusHtml statusHtml = new StatusHtml();

		if (StringHandler.isEmpty(u.getShowimg())) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败，请选择上传图片！");
		} else {
			boolean isOk = productimagesService.saveProductimage(u);
			if (isOk) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				statusHtml.setNavTabId("");
				statusHtml.setCallbackType("");
			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作失败");
			}
		}
		loggerinfo.info("==========[NewsController toAdd] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=addProductImgTmp")
	public String addProductImgTmp(@RequestParam("pid") Integer id, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */

		m.put("productid", id);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminproduct/addProductImgTmp";
	}

	@RequestMapping(params = "method=deleteImg")
	@ResponseBody
	public StatusHtml deleteImg(@RequestParam("pimgid") Integer id)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		StatusHtml statusHtml = new StatusHtml();

		boolean isOk = productimagesService.delProductimagesById(id);
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("productImgList");
			statusHtml.setCallbackType("");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败");
		}

		loggerinfo.info("==========[NewsController toAdd] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=setMainImg")
	@ResponseBody
	public StatusHtml setMainImg(@RequestParam("pimgid") Integer id,
			@RequestParam("pid") Integer pid, Byte type,
			@RequestParam("showimg") String imgName) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		StatusHtml statusHtml = new StatusHtml();

		boolean isOk = productimagesService.setMainImg(id, pid, type, imgName);

		// 更新产品的图片字段
		// BspProducts b = bspProductsService.selectByPrimaryKey(id);
		// b.setShowimg(bspProductsService.getProductMainPic(id).getShowimg());
		// bspProductsService.updateProductByPrimaryKey(b);
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("productImgList");
			statusHtml.setCallbackType("");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败");
		}

		loggerinfo.info("==========[NewsController toAdd] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=changeProductState")
	@ResponseBody
	public StatusHtml changeProductState(@RequestParam("state") Integer id,
			@RequestParam("pid") Integer pid) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		StatusHtml statusHtml = new StatusHtml();

		boolean isOk = bspProductsService.changeProductState(id, pid);
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			if (id == 1) {
				statusHtml.setNavTabId("page11");
			} else {
				statusHtml.setNavTabId("page12");
			}

			statusHtml.setCallbackType("");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作失败");
		}

		loggerinfo.info("==========[NewsController toAdd] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toEditSKU")
	public String toEditSKU(@RequestParam("pid") Integer id, ModelMap m)
			throws Exception {
		m.put("topMenu", "编辑SKU");
		m.put("getpost", "method=editSKU");
		// //////////////
		List<BspUnit> unitList = unitServerice.searchUnit("");
		m.put("unitlist", unitList);

		List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		m.put("weightUnitList", weightUnitList);

		BspProducts product = bspProductsService.selProductByPid(id);
		m.put("product", product);

		Integer skugId = product.getSkugid();
		BspSkugroup bspSkugroup = bspSkugroupService.selectByPrimaryKey(skugId);
		m.put("skuGroup", bspSkugroup);

		BspProductstocks productstocks = bspProductsService
				.selProductstocksByPid(id);
		m.put("productstocks", productstocks);

		BspCategories bspCategories = bspCategoriesService
				.selectByPrimaryKey(product.getCateid());

		m.put("bspCategories", bspCategories);

		// m.put("pageid", pageid);

		// ////////
		// List<BspUnit> unitList = unitServerice.searchUnit("");
		// m.put("unitlist", unitList);
		//
		// List<BspUnit> weightUnitList = unitServerice.selUnitsByType(1);
		// m.put("weightUnitList", weightUnitList);

		return "admin/adminproduct/editSKU";
	}

	// sku编辑
	@RequestMapping(params = "method=editSKU", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml editSKU(ProductSkuVO u, String pageid,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("sku编辑 Start...");
		String[] skuNames = (String[]) request
				.getParameterValues("skuListName");

		StatusHtml statusHtml = new StatusHtml();

		if (u.getProduct().getCateid() <= 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,你必须选择物料种类信息");
		} else if (u.getSkuList().size() == 0) {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误,生成SKU时必须添加属性");
		} else {
			boolean isOk = bspProductsService.upSrcProductSkuVO(u, skuNames);
			if (isOk) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				if (pageid != null && pageid.trim().length() > 0) {
					statusHtml.setNavTabId(pageid);
					statusHtml.setCallbackType("closeCurrent");
				} else {
					statusHtml.setNavTabId("");
				}

			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作错误");
			}
		}

		loggerinfo.info("sku编辑 End...");
		return statusHtml;
	}

	/**
	 * 在售商品列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=allProList")
	@ResponseBody
	public SelAllProlistResVO allProList(ModelMap m,
			HttpServletRequest request, SelAllProlistVO selVo) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		List<BspProducts> prolist = bspProductsService.getAllProList(selVo);
		int count = bspProductsService.countAllProList(selVo);
		SelAllProlistResVO rsvo = new SelAllProlistResVO();
		rsvo.setProlist(prolist);
		rsvo.setTotalnum(count);
		rsvo.setCurpage(selVo.getPageNum());
		rsvo.setPagenum(selVo.getNumPerPage());
		int totalpage = count / selVo.getNumPerPage();
		if (count % selVo.getNumPerPage() > 0) {
			totalpage += 1;
			rsvo.setTotalPage(totalpage);
		}

		loggerinfo.info("==========[NewsController load] End...");
		return rsvo;
	}

	// StoreRefProManager.jsp
	@RequestMapping(params = "method=toStoreRefProManager")
	public String toStoreRefProManager(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/area/StoreRefProManager";
	}

	/**
	 * 在售商品列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storeAndNcProinforList")
	public String storeAndNcProinforList(ModelMap m,
			HttpServletRequest request, SelStoreAndNcProinfor selVo)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		if (selVo == null) {
			selVo = new SelStoreAndNcProinfor();

		} else {
			if (selVo.getStoreKeyWord() == null) {
				selVo.setStoreKeyWord("");
			}
			if (selVo.getFacKeyWord() == null) {
				selVo.setFacKeyWord("");
			}
			if (selVo.getProKeyWord() == null) {
				selVo.setProKeyWord("");
			}
			if (selVo.getNumPerPage() == null) {
				selVo.setNumPerPage(20);
			}
			if (selVo.getPageNum() == null) {
				selVo.setPageNum(1);
			}
		}
		List<StoreAndNcProinfor> sanList = bspProductsService
				.getStoreAndNcProinforList(selVo);
		int count = bspProductsService.countStoreAndNcProinforList(selVo);
		SelStoreAndNcProinforRes rsvo = new SelStoreAndNcProinforRes();
		rsvo.setResList(sanList);
		rsvo.setTotalnum(count);
		if (selVo != null) {
			rsvo.setCurpage(selVo.getPageNum());
			rsvo.setPagenum(selVo.getNumPerPage());
			int totalpage = count / selVo.getNumPerPage();
			if (count % selVo.getNumPerPage() > 0) {
				totalpage += 1;
				rsvo.setTotalPage(totalpage);
			}
			m.put("selVo", selVo);
		}

		m.put("rsvo", rsvo);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/area/StoreRefProManager";
	}

	/**
	 * 保存允销目录列表
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=saveNcenterinforList")
	@ResponseBody
	public StatusHtml storeAndNcProinforList(ModelMap m,
			HttpServletRequest request, BspNcenterinforList ncproList)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		StatusHtml statusHtml = new StatusHtml();
		boolean isok = bspNcenterinforService.upNcenterinforList(ncproList
				.getNcproList());
		if (isok) {
			statusHtml.setStatusCode("200");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("更新失败");
		}

		loggerinfo.info("==========[NewsController load] End...");
		return statusHtml;
	}

	/**
	 * 删除给定id的允销目录的相关信息
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=delNcProinforById")
	@ResponseBody
	public StatusHtml delNcProinforById(ModelMap m, HttpServletRequest request,
			Integer id) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		StatusHtml statusHtml = new StatusHtml();
		boolean isok = bspNcenterinforService.delNcenterinforById(id);
		if (isok) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page48");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("删除失败");
		}

		loggerinfo.info("==========[NewsController load] End...");
		return statusHtml;
	}

	/**
	 * 只更新产品信息本身
	 * 
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=updateProOnly")
	@ResponseBody
	public StatusHtml updateProOnly(BspProducts proInfor) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		StatusHtml statusHtml = new StatusHtml();
		// 检查NC编号是否存在
		boolean isOk = true;
		String ncpronum = proInfor.getNcpronum().trim();
		if (ncpronum != null && !ncpronum.equals("")) {
			proInfor.setNcpronum(ncpronum);
			if (bspProductsService.hasNcPronum(ncpronum, proInfor.getPid())) {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("物料的NC编号已经存在");
				isOk = false;
			}
		}
		if (isOk) {
			int rsi = bspProductsService.updateProductByPrimaryKey(proInfor);
			if (rsi > 0) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作失败");
			}
		}

		loggerinfo.info("==========[NewsController load] End...");
		return statusHtml;
	}

	// StoreRefProManager.jsp
	@RequestMapping(params = "method=toAddNcProinfor")
	public String toAddNcProinfor(ModelMap m, HttpServletRequest request)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		m.put("getpost", "method=addNcProinfor");
		loggerinfo.info("==========[NewsController load] End...");
		return "admin/area/addNcProinfor";
	}

	// StoreRefProManager.jsp
	@RequestMapping(params = "method=addNcProinfor")
	@ResponseBody
	public StatusHtml addNcProinfor(ModelMap m, BspNcenterinfor ncPro)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		boolean isOk = bspNcenterinforService.addNewNcenterinfor(ncPro);

		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("数据保存成功");
			statusHtml.setNavTabId("page48");
			statusHtml.setCallbackType("closeCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("数据保存失败");
		}
		loggerinfo.info("==========[NewsController load] End...");
		return statusHtml;
	}

	// StoreRefProManager.jsp
	@RequestMapping(params = "method=toUpNcProinfor")
	public String toUpNcProinfor(ModelMap m, Integer id) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		BspNcenterinfor ncenter = bspNcenterinforService
				.getNcenterinforById(id);
		m.put("getpost", "method=upNcProinfor");
		m.put("ncenter", ncenter);
		loggerinfo.info("==========[NewsController load] End...");
		return "admin/area/upNcProinfor";
	}

	// StoreRefProManager.jsp
	@RequestMapping(params = "method=upNcProinfor")
	@ResponseBody
	public StatusHtml upNcProinfor(ModelMap m, BspNcenterinfor ncPro)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		boolean isOk = bspNcenterinforService.upNcenterinfor(ncPro);

		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("数据修改成功");
			statusHtml.setNavTabId("page48");
			statusHtml.setCallbackType("closeCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("数据修改失败");
		}
		loggerinfo.info("==========[NewsController load] End...");
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
		// 子表行数限制的更改，spring mvc默认为256
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);

	}
}
