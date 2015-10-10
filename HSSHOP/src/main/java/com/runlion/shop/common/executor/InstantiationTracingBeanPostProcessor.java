package com.runlion.shop.common.executor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.runlion.shop.common.util.WebConfigHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.entity.BspJobLog;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.entity.unionpay.UnionPayParam;
import com.runlion.shop.service.BspProductsService;
import com.runlion.shop.service.PriceUpdateJobService;
import com.runlion.shop.vo.WebConfigVO;
import com.unionpay.acp.sdk.SDKConfig;

public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private BspProductsService bspProductsService;
	@Autowired
	private PriceUpdateJobService priceUpdateJobService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		initMenuLink();
		initWebInforConfig(event);

		// 初始化：价格更新任务到缓存
		initPriceUpdateJobList();

		// 初始化：加载银联支付的属性文件
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		// 初始化：加载支付回调的属性文件
		initUnionPayProperties(event);
	}

	public void initPriceUpdateJobList() {
		List<BspJobLog> jobList = priceUpdateJobService.getJobList();
		if (jobList == null)
			return;
		EHCacheUtil.initCacheManager();
		EHCacheUtil.put("jobList", jobList);
	}

	public void initMenuLink() {
		List<ProductComboInfo> menuLink = null;
		List<ProductComboInfo> brandList = null;
		List<ProductComboInfo> factoryList = null;

		try {
			menuLink = bspProductsService.generateProductLink(0, "");
			// factoryList = bspProductsService.getFactoryListByAreaId(id);
		} catch (Exception e) {

		}
		if (menuLink == null)
			return;

		// 初始化--必须
		EHCacheUtil.initCacheManager();
		EHCacheUtil.put("menuLink", menuLink);
		// 区域品牌关联信息
		EHCacheUtil.put("brandList", brandList);
		// 区域品牌产地关联信息
		EHCacheUtil.put("factoryList", factoryList);
	}

	public void initWebInforConfig(ContextRefreshedEvent event) {
		WebConfigVO configVo = new WebConfigVO();
		String path = "";
		try {
			path = event.getApplicationContext()
					.getResource("/WEB-INF/classes/config/shop.config")
					.getFile().getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String path = request.getSession().getServletContext()
		// .getRealPath("/WEB-INF/classes/config/shop.config");

		WebConfigHandler.getConfigInfor(configVo, path);

		// 初始化--必须
		EHCacheUtil.initCacheManager();
		EHCacheUtil.put("webConfig", configVo);
	}

	public void initUnionPayProperties(ContextRefreshedEvent event) {
		String path = "";
		try {
			path = event
					.getApplicationContext()
					.getResource(
							"/WEB-INF/classes/unionpay/unionpay.properties")
					.getFile().getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 装载配置文件的通知地址
		Properties prop = new Properties();
		String merId = "", frontUrl = "", backUrl = "";
		try {
			prop.load(new FileInputStream(path));
			frontUrl = prop.getProperty("frontReceiveUrl");
			backUrl = prop.getProperty("backReceiveUrl");
			merId = prop.getProperty("merId");
		} catch (IOException e) {
			e.printStackTrace();
		}
		UnionPayParam param = new UnionPayParam();
		param.setMerId(merId);
		param.setFrontUrl(frontUrl);
		param.setBackUrl(backUrl);
		EHCacheUtil.initCacheManager();
		EHCacheUtil.put("UnionPayParam", param);

	}
}