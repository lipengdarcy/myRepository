package com.enation.eop.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.darcy.framework.context.spring.SpringContextHolder;
import org.darcy.service.framework.component.IComponentManager;
import org.darcy.service.resource.ISiteManager;
import org.darcy.service.resource.model.EopSite;

import com.enation.eop.SystemSetting;
import com.enation.eop.sdk.context.EopSetting;

/**
 * EopLinstener 负责初始化站点缓存
 * @author kingapex
 * 2010-7-18下午04:01:16
 */
public class EopContextLoaderListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent event) {

	}
	
	public void contextInitialized(ServletContextEvent event) {
	 
	 
		if( EopSetting.INSTALL_LOCK.toUpperCase().equals("YES") ){
			EopSite.reload(); //重新加载站点设置
			SystemSetting.load();
			IComponentManager componentManager = SpringContextHolder.getBean("componentManager");
			componentManager.startComponents(); //启动组件
		}
		
	}
	
	  

}
