package org.darcy.controller.admin.setting;

import java.util.List;

import org.darcy.app.base.core.plugin.user.AdminUserPluginBundle;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.framework.action.GridController;
import org.darcy.framework.action.GridJsonResult;
import org.darcy.framework.action.JsonResult;
import org.darcy.framework.util.JsonResultUtil;
import org.darcy.service.auth.IAdminUserManager;
import org.darcy.service.auth.IPermissionManager;
import org.darcy.service.auth.IRoleManager;
import org.darcy.service.resource.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 3.站点管理员管理
 */
@Controller 
@Scope("prototype")
@RequestMapping("/core/admin/userAdmin")
public class UserAdminController extends GridController {
	@Autowired
	private AdminUserPluginBundle adminUserPluginBundle;
	@Autowired
	private IAdminUserManager adminUserManager;
	@Autowired
	private IRoleManager roleManager;
	@Autowired
	private IPermissionManager permissionManager;
	
	/***
	 * 跳转至站点管理员列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list() {
		return "/core/admin/user/useradmin";
	}
	
	/**
	 * 获取站点管理员JSON列表
	 * @param userList 管理员列表
	 * @return 站点管理员JSON列表
	 */
	@ResponseBody
	@RequestMapping(value="/list-json")
	public GridJsonResult listJson(){
		List userList= this.adminUserManager.list();
		return JsonResultUtil.getGridJson(userList);
	}

	/***
	 * 跳转添加管理员
	 * @param roleList 角色列表
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView add() throws Exception {
		ModelAndView view = new ModelAndView();
		view.addObject("roleList",roleManager.list());
		view.setViewName("/core/admin/user/addUserAdmin");
			return view;
	}
	
	/**
	 * 新增管理员
	 * @param adminUser 管理员
	 * @param roleids 选择角色列表
	 * @return 新增状态
	 */
	@ResponseBody
	@RequestMapping(value="/add-save")
	public JsonResult addSave(AdminUser adminUser,int[] roleids) throws Exception {
		try{
			//判断是否管理员重名
			boolean flag = this.adminUserManager.is_exist(adminUser.getUsername());
			if(flag){
				return JsonResultUtil.getSuccessJson("用户名已存在!");
			}else{
				adminUser.setRoleids(roleids);
				adminUserManager.add(adminUser);
				return JsonResultUtil.getSuccessJson("新增管理员成功");
			}
		 } catch (RuntimeException e) {
			 e.printStackTrace();
			    logger.error("新增管理员失败", e);
				return JsonResultUtil.getErrorJson("新增管理员失败");
		 }	

	}

	/**
	 * 跳转至管理员修改页面
	 * @param id 管理员Id
	 * @param roleList 角色列表
	 * @param userRoles 管理员角色
	 * @param adminUser 管理员
	 * @param htmlList 修改html
	 * @return 
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Integer id,AdminUser adminUser) throws Exception {
		ModelAndView view = new ModelAndView();
		view.addObject("roleList",roleManager.list());
		view.addObject("userRoles",permissionManager.getUserRoles(id));
		view.addObject("adminUser",adminUserManager.get(id));
		view.addObject("htmlList",adminUserPluginBundle.getInputHtml(adminUser));
		view.setViewName("/core/admin/user/editUserAdmin");
		return view;
		
	}

	/***
	 * 管理员修改
	 * @param updatePwd 是否修改密码
	 * @param adminUser 管理员
	 * @param newPassword 修改后密码
	 * @param roleids 管理员角色列表
	 * @return 修改状态
	 */
	@ResponseBody
	@RequestMapping(value="/edit-save")
	public JsonResult editSave(String updatePwd,AdminUser adminUser,String newPassword,int[] roleids) throws Exception {
		
		//判断是否为演示站点
		if(EopSetting.IS_DEMO_SITE){
			return JsonResultUtil.getErrorJson("抱歉，当前为演示站点，以不能修改这些示例数据，请下载安装包在本地体验这些功能！");
		}
		
		try {
			//判断是否修改密码
			if(updatePwd!=null){
				adminUser.setPassword(newPassword);
			}
			adminUser.setRoleids(roleids);
			this.adminUserManager.edit(adminUser);
			return JsonResultUtil.getSuccessJson("修改管理员成功");

		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e,e.fillInStackTrace());
			return JsonResultUtil.getErrorJson("修改管理员失败");
		}
	}
	

	/**
	 * 删除管理员
	 * @param id 管理员Id
	 * @return 修改状态
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public JsonResult delete(Integer id) throws Exception {
		
		//判断是否为演示站点
		if(EopSetting.IS_DEMO_SITE){
			return JsonResultUtil.getErrorJson("抱歉，当前为演示站点，以不能修改这些示例数据，请下载安装包在本地体验这些功能！");
		}
		
		try {
			this.adminUserManager.delete(id);
			return JsonResultUtil.getSuccessJson("管理员删除成功");
		} catch (RuntimeException e) {
			logger.error("管理员删除失败", e);
			return JsonResultUtil.getErrorJson("管理员删除失败");
		}
	}

	/**
	 * 跳转至修改密码页面
	 * @return
	 */
	/*public String editPassword() throws Exception {
		return "editPassword";
	}*/
	
	
}
