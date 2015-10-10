package com.runlion.shop.controller.app.b2c;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.CookieHanler;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.entity.common.JsonResponseData;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.vo.app.b2c.B2cProductTypeVo;

@Controller
@RequestMapping("/b2capp/product")
public class AppProductController {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService bspProductsService;

	/**
	 * B2C产品分类接口， 品牌，强度，品种
	 * 
	 * @param
	 * @return JsonResponseData<B2cProductTypeVo> 返回类型
	 * @throws Exception
	 */
	@RequestMapping("productType")
	@ResponseBody
	public JsonResponseData<B2cProductTypeVo> productType() throws Exception {
		loggerinfo
				.info("==========[AppProductController productType] Begin...");

		B2cProductTypeVo B2cProductTypeVo = new B2cProductTypeVo();
		JsonResponseData<B2cProductTypeVo> data = new JsonResponseData<B2cProductTypeVo>();

		// 品牌
		List<BspAttributevalues> brandList = bspProductsService
				.getAttributesValue((short) Constant.brandId);
		// 强度
		List<BspAttributevalues> strengthList = bspProductsService
				.getAttributesValue((short) Constant.strengthId);
		// 品种
		List<BspAttributevalues> typeList = bspProductsService
				.getAttributesValue((short) Constant.typeId);

		B2cProductTypeVo.setBrandList(brandList);
		B2cProductTypeVo.setStrengthList(strengthList);
		B2cProductTypeVo.setTypeList(typeList);

		// 输出
		data.setResult(B2cProductTypeVo);
		data.setErrorMsg("");
		data.setErrorNo("");
		data.setSuccess(true);

		loggerinfo.info("==========[AppProductController productType] End...");
		return data;
	}

	// 产品列表
	/**
	 * B2C产品列表 排序方式：价格、销量、评价数
	 * 
	 * @param orderbyId
	 *            排序id 1：价格、2：销量、3：评价数
	 * @return JsonResponseData<B2cProductTypeVo> 返回类型
	 * @throws Exception
	 */
	@RequestMapping("productList")
	@ResponseBody
	public JsonResponseData<B2cProductTypeVo> productList(
			HttpServletRequest request,
			@RequestParam("path") String path,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "9") int pageSize,
			@RequestParam(required = false, value = "orderbyId", defaultValue = "0") int orderbyId)
			throws Exception {
		loggerinfo
				.info("==========[AppProductController productList] Begin...");

		B2cProductTypeVo B2cProductTypeVo = new B2cProductTypeVo();
		JsonResponseData<B2cProductTypeVo> data = new JsonResponseData<B2cProductTypeVo>();

		// 品牌
		List<BspAttributevalues> brandList = bspProductsService
				.getAttributesValue((short) Constant.brandId);
		// 强度
		List<BspAttributevalues> strengthList = bspProductsService
				.getAttributesValue((short) Constant.strengthId);
		// 品种
		List<BspAttributevalues> typeList = bspProductsService
				.getAttributesValue((short) Constant.typeId);

		B2cProductTypeVo.setBrandList(brandList);
		B2cProductTypeVo.setStrengthList(strengthList);
		B2cProductTypeVo.setTypeList(typeList);

		// 从cookie取收货地址id
		String areaId = CookieHanler.getLastAreaFromCookie(request);
		List<ProductComboInfo> result = bspProductsService.generateProductLink(
				Integer.valueOf(areaId), path);

		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] pathList = path.split("-");
		for (int i = 0; i < pathList.length; i++)
			map.put(pathList[i], Integer.parseInt(pathList[i]));
		List<BspProducts> list = bspProductsService.comboQuery(map,
				Integer.valueOf(areaId), pageNumber, pageSize);
		Page<BspProducts> page = (Page<BspProducts>) list;

		// 输出
		data.setResult(B2cProductTypeVo);
		data.setErrorMsg("");
		data.setErrorNo("");
		data.setSuccess(true);

		loggerinfo.info("==========[AppProductController productList] End...");
		return data;
	}
	// 产品列表-筛选
	// 产品评价
	// 产品详情
	// 产品详情-产品属性选择
	// 浏览记录

}
