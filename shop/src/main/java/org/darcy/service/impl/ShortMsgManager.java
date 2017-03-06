package org.darcy.service.impl;

import java.util.List;

import org.darcy.framework.database.IDaoSupport;
import org.darcy.service.IShortMsgManager;
import org.darcy.service.auth.IAdminUserManager;
import org.darcy.service.resource.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enation.app.base.core.model.ShortMsg;
import com.enation.app.base.core.plugin.shortmsg.ShortMsgPluginBundle;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.DateUtil;

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
