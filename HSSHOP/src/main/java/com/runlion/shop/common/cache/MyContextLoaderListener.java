package com.runlion.shop.common.cache;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @Description TODO
 * @author 赵威
 * @date 2014-9-23 下午4:03:34
 * @version V1.0
 */
@Component
public class MyContextLoaderListener extends ContextLoaderListener {

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger loggererror = Logger.getLogger("ErrorLogger");
	private Logger loggerinfo = Logger.getLogger("InfoLogger");

	/**
	 * @description 重写ContextLoaderListener的contextInitialized方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		loggerinfo.info("==========[ContextLoaderListener] Start...");
		super.contextInitialized(event);

		CacheOperater.getInstance().initCacheDate();
		System.out
				.println("------MyContextLoaderListener.getCurrentWebApplicationContext()---------"
						+ MyContextLoaderListener
								.getCurrentWebApplicationContext());
		loggerinfo.info("==========[ContextLoaderListener] End...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		closeWebApplicationContext(event.getServletContext());
		CacheOperater.getInstance().initCacheDate();

	}

}
