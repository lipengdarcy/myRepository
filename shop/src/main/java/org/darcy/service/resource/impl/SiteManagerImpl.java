package org.darcy.service.resource.impl;

import java.util.Map;

import org.darcy.framework.util.ReflectionUtil;
import org.darcy.service.ISettingService;
import org.darcy.service.resource.ISiteManager;
import org.darcy.service.resource.model.EopSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 站点管理
 * 
 * @author kingapex 2010-5-9下午07:56:03
 */
@Service("siteManager")
public class SiteManagerImpl implements ISiteManager {
	
	
	@Autowired
	private  ISettingService settingService;
	
	
	/* (non-Javadoc)
	 * @see com.enation.eop.resource.ISiteManager#saveToDB()
	 */
	@Override
	public void saveToDB() {
		EopSite site  = EopSite.getInstance();
		Map map = ReflectionUtil.po2Map(site);
		Map<String,Map<String,String>> allSetting = settingService.getSetting();
		allSetting.put(EopSite.SITE_SETTING_KEY, map);//更新缓存
		this.settingService.save(EopSite.SITE_SETTING_KEY,map); //更新数据库
		
	} 
	
	
	
}
