package com.enation.app.base.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enation.app.base.core.model.ShortMsg;
import com.enation.app.base.core.plugin.shortmsg.ShortMsgPluginBundle;
import com.enation.app.base.core.service.IShortMsgManager;
import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.eop.resource.model.AdminUser;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.DateUtil;
import com.enation.framework.database.IDaoSupport;

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
