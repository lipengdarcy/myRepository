package org.darcy.controller.admin.setting;

import java.util.List;

import org.darcy.app.base.core.model.Role;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.framework.action.GridController;
import org.darcy.framework.action.GridJsonResult;
import org.darcy.framework.action.JsonResult;
import org.darcy.framework.util.JsonResultUtil;
import org.darcy.service.auth.IAuthActionManager;
import org.darcy.service.auth.IRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 4.角色管理
 */
@Controller 
@Scope("prototype")
@RequestMapping("/core/admin/role")
public class RoleController extends GridController {
	@Autowired
	private IRoleManager roleManager;
	@Autowired
	private IAuthActionManager authActionManager;
	
		
	/**
	 * 跳转到角色列表
	 * @return 角色列表
	 */
	@RequestMapping(value="/list")
	public String list(){
		return "/core/admin/auth/rolelist";
	}
	
	/**
	 * 角色JSON列表
	 * @param roleList 角色列表
	 * @return 角色JSON列表
	 */
	@ResponseBody
	@RequestMapping(value="/list-json")
	public GridJsonResult listJson(){
		List roleList = roleManager.list();
		return JsonResultUtil.getGridJson(roleList);
	}
	
	
	/**
	 * 跳转到角色添加页面
	 * @param authList 权限点列表
	 * @return 角色添加页面
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(){
		ModelAndView view = new ModelAndView();
		view.addObject("authList",authActionManager.list());
		view.setViewName("/core/admin/auth/role_add");
		return view;
	}
	
	
	/**
	 * 跳转到角色修改页面
	 * @param authList 权限点列表
	 * @param isEdit 是否为修改
	 * @param role 角色信息，同时读取此角色权限
	 * @param roleid 角色Id
	 * @return 角色修改页面
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(int roleid){
		ModelAndView view = new ModelAndView();
		view.addObject("authList",authActionManager.list());
		view.addObject("isEdit",1);
		view.addObject("role",this.roleManager.get(roleid));
		view.setViewName("/core/admin/auth/role_edit");
		return view;
	}
	
	/**
	 * 角色保存添加
	 * @param role 角色
	 * @param acts 权限点列表
	 * @return 新增角色状态
	 */
	@ResponseBody
	@RequestMapping(value="/save-add")
	public JsonResult saveAdd(Role role,int[] acts){
		try {
			this.roleManager.add(role, acts);
			return JsonResultUtil.getSuccessJson("新增角色成功");
		} catch (Exception e) {
			logger.error("新增角色失败", e);
			return JsonResultUtil.getErrorJson("新增角色失败");

		}
	}
	
	
	/**
	 * 保存修改
	 * @param role 角色
	 * @param acts 权限点列表
	 * @return 修改角色状态
	 */
	@ResponseBody
	@RequestMapping(value="/save-edit")
	public JsonResult saveEdit(Role role,int[] acts){
		try {
			this.roleManager.edit(role, acts);
			return JsonResultUtil.getSuccessJson("角色修改成功");
		} catch (Exception e) {
			logger.error("角色修改失败", e);
			return JsonResultUtil.getErrorJson("角色修改失败");
		}		
	}
	
	/**
	 * 删除角色
	 * @param roleid 角色Id
	 * @return 删除角色状态
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public JsonResult delete(int roleid){
		
		if(EopSetting.IS_DEMO_SITE){
			if(roleid<=5){
				return JsonResultUtil.getErrorJson("抱歉，当前为演示站点，以不能修改这些示例数据，请下载安装包在本地体验这些功能！");
			}
		}
		
		try {
			this.roleManager.delete(roleid);
			return JsonResultUtil.getSuccessJson("角色删除成功");
		} catch (Exception e) {
			logger.error("角色删除失败", e);
			return JsonResultUtil.getErrorJson("角色删除失败");

		}		
	}
}
