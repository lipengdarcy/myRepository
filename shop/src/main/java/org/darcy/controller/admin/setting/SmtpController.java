package org.darcy.controller.admin.setting;

import java.util.List;

import org.darcy.app.base.core.model.Smtp;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.framework.action.GridController;
import org.darcy.framework.action.GridJsonResult;
import org.darcy.framework.action.JsonResult;
import org.darcy.framework.util.JsonResultUtil;
import org.darcy.framework.util.StringUtil;
import org.darcy.service.ISmtpManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 2.smtp设置
 */

@Controller 
@RequestMapping("/core/admin/smtp")
public class SmtpController extends GridController  {
	
	@Autowired
	private ISmtpManager smtpManager;
	
	/**
	 * 跳转至添加SMTP页面
	 * @param isedit 是否为修改 0为添加，1为修改
	 * @return 添加SMTP页面
	 */
	@RequestMapping(value="/add")
	public String add(){
		return "/core/admin/smtp/add";
	}

	/**
	 * 跳转至修改SMTP页面
	 * @param isedit 是否为修改 0为添加，1为修改
	 * @param smtpId SMTPId
	 * @param smtp SMTP
	 * @return 修改SMTP页面
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Integer smtpId){
		ModelAndView view=new ModelAndView();
		view.addObject("smtp", this.smtpManager.get(smtpId));
		view.setViewName("/core/admin/smtp/edit");
		
		return view;

	}
	
	/**
	 * 保存添加
	 * @param smtp SMTP
	 */
	@ResponseBody
	@RequestMapping(value="/save-add")
	public JsonResult saveAdd(Smtp smtp){
		
		//判断是否为演示站点
		if(EopSetting.IS_DEMO_SITE){
			return JsonResultUtil.getErrorJson(EopSetting.DEMO_SITE_TIP);
		}
		try{
			this.smtpManager.add(smtp);
			return JsonResultUtil.getSuccessJson("smtp添加成功");
		}catch(RuntimeException e){
			logger.error("smtp添加失败",e);
			return JsonResultUtil.getErrorJson("smtp添加失败");
		}
	}
	
	/**
	 * 保存修改
	 * @param smtp SMTP
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save-edit")
	public JsonResult saveEdit(Smtp smtp){
		//是否为演示站点
		if(EopSetting.IS_DEMO_SITE){
			return JsonResultUtil.getErrorJson(EopSetting.DEMO_SITE_TIP);
		}
		try{
			//判断smtp密码是否为空
			if( StringUtil.isEmpty(smtp.getPassword()) ) {
				smtp.setPassword(this.smtpManager.get(smtp.getId()).getPassword()) ;
			}
			this.smtpManager.edit(smtp);
			return JsonResultUtil.getSuccessJson("smtp修改成功");
		}catch(RuntimeException e){
			logger.error("smtp修改失败", e);
			return JsonResultUtil.getErrorJson("smtp修改失败");
		}
	}
	  
	/**
	 * 跳转至smtp列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(){
		return "/core/admin/smtp/list";
	}
	
	/**
	 * 获取smtp列表JSON
	 * @author LiFenLong
	 * @param smtpList smtp列表
	 * @return smtp列表JSON
	 */
	@ResponseBody  
	@RequestMapping(value="/smtp-listJson")
	public GridJsonResult listJson(){
		 List smtpList = this.smtpManager.list();	
		 return JsonResultUtil.getGridJson(smtpList);
	}
	
	/**
	 * 删除SMTP
	 * @param id SMTPId
	 * @return
	 */
	@ResponseBody  
	@RequestMapping(value="/delete")
	public JsonResult delete(Integer[] id){
		//判断是否演示站点
		if(EopSetting.IS_DEMO_SITE){
			return JsonResultUtil.getErrorJson(EopSetting.DEMO_SITE_TIP);
		}
		try{
			this.smtpManager.delete(id);
			return JsonResultUtil.getSuccessJson("smtp删除成功");
		}catch(RuntimeException e){
			this.logger.error("smtp删除失败", e);
			return JsonResultUtil.getErrorJson("smtp删除失败");
		}
	}
}
