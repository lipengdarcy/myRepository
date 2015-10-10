package com.runlion.shop.controller.app.b2c;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsWithPics;
import com.runlion.shop.entity.app.b2c.B2cIndexAdEntity;
import com.runlion.shop.entity.app.b2c.B2cIndexBrandEntity;
import com.runlion.shop.entity.common.JsonResponseData;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.vo.app.b2c.B2cIndexVo;

@Controller
@RequestMapping("/b2capp/api")
public class B2cAppIndexController {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService bspProductsService;

	/**
	 * B2C首页接口
	 * 
	 * @param app
	 * @return JsonResponseData<List<BspNews>> 返回类型
	 * @throws Exception
	 */
	@RequestMapping(params = "method=toIndex")
	@ResponseBody
	public JsonResponseData<B2cIndexVo> appIndex() throws Exception {
		loggerinfo.info("==========[B2cAppIndexController load] Start...");

		B2cIndexVo b2cIndexVo = new B2cIndexVo();
		JsonResponseData<B2cIndexVo> app = new JsonResponseData<B2cIndexVo>();

		// 广告开始
		List<B2cIndexAdEntity> list = new ArrayList<B2cIndexAdEntity>();
		B2cIndexAdEntity entity = new B2cIndexAdEntity();
		entity.setId(1);
		entity.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");
		entity.setUrl("http://www.baidu.com");

		B2cIndexAdEntity entity1 = new B2cIndexAdEntity();
		entity1.setId(2);
		entity1.setImg("http://f.hiphotos.baidu.com/image/pic/item/a5c27d1ed21b0ef4764fce63d9c451da81cb3e63.jpg");
		entity1.setUrl("http://www.baidu.com");

		B2cIndexAdEntity entity2 = new B2cIndexAdEntity();
		entity2.setId(3);
		entity2.setImg("http://d.hiphotos.baidu.com/image/pic/item/a8ec8a13632762d0648506e4a2ec08fa513dc636.jpg");
		entity2.setUrl("http://www.baidu.com");

		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		// 广告结束

		// 品牌推荐开始
		List<B2cIndexBrandEntity> brandlist = new ArrayList<B2cIndexBrandEntity>();
		B2cIndexBrandEntity brandentity = new B2cIndexBrandEntity();
		brandentity.setId(1);
		brandentity.setName("品牌1");
		brandentity.setUrl("http://www.baidu.com");
		brandentity
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");

		B2cIndexBrandEntity brandentity1 = new B2cIndexBrandEntity();
		brandentity1.setId(2);
		brandentity1.setName("品牌2");
		brandentity1.setUrl("http://www.baidu.com");
		brandentity1
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/a5c27d1ed21b0ef4764fce63d9c451da81cb3e63.jpg");

		B2cIndexBrandEntity brandentity2 = new B2cIndexBrandEntity();
		brandentity2.setId(3);
		brandentity2.setName("品牌3");
		brandentity2.setUrl("http://www.baidu.com");
		brandentity2
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");

		B2cIndexBrandEntity brandentity3 = new B2cIndexBrandEntity();
		brandentity3.setId(4);
		brandentity3.setName("品牌4");
		brandentity3.setUrl("http://www.baidu.com");
		brandentity3
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");

		B2cIndexBrandEntity brandentity4 = new B2cIndexBrandEntity();
		brandentity4.setId(5);
		brandentity4.setName("品牌5");
		brandentity4.setUrl("http://www.baidu.com");
		brandentity4
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");

		B2cIndexBrandEntity brandentity5 = new B2cIndexBrandEntity();
		brandentity5.setId(6);
		brandentity5.setName("品牌6");
		brandentity5.setUrl("http://www.baidu.com");
		brandentity5
				.setImg("http://f.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d602b673f6040828381e30fd67.jpg");

		brandlist.add(brandentity);
		brandlist.add(brandentity1);
		brandlist.add(brandentity2);
		brandlist.add(brandentity3);
		brandlist.add(brandentity4);
		brandlist.add(brandentity5);
		// 品牌推荐结束

		// 推荐产品开始
		List<BspProductsWithPics> hotlist = new ArrayList<BspProductsWithPics>();
		List<BspProducts> plist = bspProductsService.hotProducts(2);
		// 增加产品的小图
		for (BspProducts b : plist) {
			List<BspProductimages> picList = bspProductsService
					.getIndexProductPics(b.getPid(), (byte) 3);
			List<BspProductimages> productPicList = new ArrayList<BspProductimages>();
			for (BspProductimages bspProductimages : picList) {
				BspProductimages p = new BspProductimages();
				p.setPid(bspProductimages.getPid());
				p.setPimgid(bspProductimages.getPimgid());
				p.setDisplayorder(bspProductimages.getDisplayorder());
				p.setIsmain(bspProductimages.getIsmain());
				p.setShowimg("http://www.hongshids.com:8080/upload/product/show/source/"
						+ bspProductimages.getShowimg());
				productPicList.add(p);
			}

			BspProductsWithPics p = new BspProductsWithPics();
			p.setPicList(productPicList);
			p.setProduct(b);
			hotlist.add(p);

		}
		// 推荐产品结束

		// 输出
		b2cIndexVo.setB2cIndexAdEntity(list);
		b2cIndexVo.setB2cIndexBrandEntity(brandlist);
		b2cIndexVo.setBspProductsWithPics(hotlist);
		// 输出
		app.setResult(b2cIndexVo);
		app.setErrorMsg("");
		app.setErrorNo("");
		app.setSuccess(true);

		loggerinfo.info("==========[B2cAppIndexController load] End...");
		return app;
	}

}
