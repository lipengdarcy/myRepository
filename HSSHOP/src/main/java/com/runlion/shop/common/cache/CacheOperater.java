package com.runlion.shop.common.cache;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CacheOperater {

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger loggererror = Logger.getLogger("ErrorLogger");
	private Logger loggerinfo = Logger.getLogger("InfoLogger");

	private static CacheOperater instance;

	public static CacheOperater getInstance() {
		if (null == instance) {
			instance = new CacheOperater();
		}
		return instance;
	}

	/***
	 * 启动时加载缓存的数据
	 * 
	 * @throws Exception
	 */
	public void initCacheDate() {
		loggerinfo.info("==========[load cache date] Start...");

		// systemEnums = this.systemEnumService.getSystemEnums();
		loggerinfo.info("==========[load cache date] End...");
	}

	public void clearCacheDate() {

	}

}
