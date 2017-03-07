package org.darcy.app.base.core.plugin.user;

import org.darcy.service.resource.model.AdminUser;


/**
 * 管理员登录事件
 * @author kingapex
 *
 */
public interface IAdminUserLoginEvent {
	
	public void onLogin(AdminUser user);
	
}
