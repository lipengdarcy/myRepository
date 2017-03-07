package org.darcy.service.resource.impl;

import java.util.List;
import java.util.Map;

import org.darcy.eop.sdk.context.EopContext;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.eop.sdk.database.BaseSupport;
import org.darcy.framework.database.IDaoSupport;
import org.darcy.framework.util.FileUtil;
import org.darcy.service.ISettingService;
import org.darcy.service.resource.ISiteManager;
import org.darcy.service.resource.IThemeManager;
import org.darcy.service.resource.model.EopSite;
import org.darcy.service.resource.model.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 前台主题管理
 * @author kingapex
 * @version v2.0
 * 2016年2月17日下午9:20:38
 * @since v6.0
 */
@Service("themeManager")
public class ThemeManagerImpl  implements IThemeManager {
	
	@Autowired
	private IDaoSupport<Theme> daoSupport;
	
	@Autowired
	private  ISettingService settingService;
	
	public void clean() {
		daoSupport.execute("truncate table  es_theme");
	}

	public Theme getTheme(Integer themeid) {
		return daoSupport.queryForObject("select * from es_theme where id=?", Theme.class, themeid);
	}

	public List<Theme> list() {
			return daoSupport.queryForList("select * from es_theme where siteid = 0", Theme.class);
		 
	}

	/*
	 * 取得主站的theme列表 (non-Javadoc)
	 * 
	 * @see com.enation.eop.core.resource.IThemeManager#getMainThemeList()
	 */
	public List<Theme> list(int siteid) {
		return daoSupport.queryForList("select * from es_theme where siteid = 0", Theme.class);
	}

	public void addBlank(Theme theme) {
		try {

			daoSupport.insert("es_theme", theme);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建主题出错");
		}
	}

	public Integer add(Theme theme, boolean isCommon) {
		try {
			//4.0开始不再copy目录 
		//	this.copy(theme, isCommon);
			daoSupport.insert("es_theme", theme);
			return daoSupport.getLastId("theme");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装主题出错");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void changetheme(int themeid) {
		 Theme theme = this.getTheme(themeid);
			Map map =  this.settingService.getSetting(EopSite.SITE_SETTING_KEY);
			map.put("themeid",""+ themeid);
			map.put("themepath", theme.getPath());
			this.settingService.save(EopSite.SITE_SETTING_KEY, map);
			EopSite.reload();
		 
	}

 
	
 
}
