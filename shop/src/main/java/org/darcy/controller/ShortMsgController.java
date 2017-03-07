package org.darcy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.darcy.app.base.core.model.ShortMsg;
import org.darcy.framework.action.JsonResult;
import org.darcy.framework.util.JsonResultUtil;
import org.darcy.service.IShortMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短消息action
 * @author kingapex
 * @author Kanon 2015-10-14 version 1.1 添加注释
 *
 */
@Controller 
@RequestMapping("/core/admin/short-msg")
public class ShortMsgController  {
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	
	@Autowired
	private IShortMsgManager shortMsgManager ;
 
	
	
	/**
	 * 读取所有未读消息
	 * @param msgList 未读的消息列表
	 * @return 未读的消息列表
	 */
	@ResponseBody
	@RequestMapping(value="/list-new")
	public JsonResult listNew(){
		try{
			List<ShortMsg> msgList = this.shortMsgManager.listNotReadMessage();
			return JsonResultUtil.getObjectJson(msgList);
		}catch(Exception e ){
			logger.error("获取消息出错", e);
			return JsonResultUtil.getErrorJson("获取消息出错");
		}
	}

	 
}
