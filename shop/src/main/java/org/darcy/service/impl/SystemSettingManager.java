package org.darcy.service.impl;

import java.util.List;
import java.util.Map;

import org.darcy.eop.SystemSetting;
import org.darcy.framework.database.IDaoSupport;
import org.darcy.service.ISystemSettingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统配置实现类
 * @author Sylow
 * @version v2.0,2016年2月29日
 * @since v6.0
 */
@Service("systemSettingManager")
public class SystemSettingManager implements ISystemSettingManager {

	@Autowired
	private IDaoSupport daoSupport;
	
	@Override
	public SystemSetting getSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISystemSettingManager#getUrlConfig()
	 */
	@Override
	public List<Map> getUrlConfig() {
		return this.daoSupport.queryForList("SELECT * FROM es_url_server");
	}

}
