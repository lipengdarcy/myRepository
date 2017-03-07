package org.darcy.service;

import java.util.List;

import org.darcy.app.base.core.model.ShortMsg;



/**
 * 短消息管理
 * @author kingapex
 *
 */
public interface IShortMsgManager {
 
	
	/**
	 * 读取所有未读的消息 
	 * @return
	 */
	public List<ShortMsg> listNotReadMessage();
	
	 
	
	
}
