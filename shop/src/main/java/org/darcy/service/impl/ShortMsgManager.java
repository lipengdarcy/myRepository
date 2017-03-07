package org.darcy.service.impl;

import java.util.List;

import org.darcy.app.base.core.model.ShortMsg;
import org.darcy.app.base.core.plugin.shortmsg.ShortMsgPluginBundle;
import org.darcy.eop.sdk.database.BaseSupport;
import org.darcy.eop.sdk.utils.DateUtil;
import org.darcy.framework.database.IDaoSupport;
import org.darcy.service.IShortMsgManager;
import org.darcy.service.auth.IAdminUserManager;
import org.darcy.service.resource.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短消息管理
 * @author kingapex
 *
 */
@Service
public class ShortMsgManager   implements IShortMsgManager {

	@Autowired
	private ShortMsgPluginBundle shortMsgPluginBundle;
	
	@Autowired
	protected IDaoSupport<ShortMsg> daoSupport;
	
	
	@Override
	public List<ShortMsg> listNotReadMessage() {
		return shortMsgPluginBundle.getMessageList();
	}

	public ShortMsgPluginBundle getShortMsgPluginBundle() {
		return shortMsgPluginBundle;
	}

	public void setShortMsgPluginBundle(ShortMsgPluginBundle shortMsgPluginBundle) {
		this.shortMsgPluginBundle = shortMsgPluginBundle;
	}

	
}
