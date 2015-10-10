package com.runlion.shop.controller.admin.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.Constant;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspUserApplyMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.dao.CusUsersPermissionMapper;
import com.runlion.shop.dao.SysResourceMapper;
import com.runlion.shop.dao.SysRoleMapper;
import com.runlion.shop.dao.SysRoleResourceMapper;
import com.runlion.shop.dao.SysUserRoleMapper;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspUserApply;
import com.runlion.shop.entity.BspUserApplyExample;
import com.runlion.shop.entity.BspUsersPermissionWithBLOBs;
import com.runlion.shop.entity.SysResource;
import com.runlion.shop.entity.SysResourceExample;
import com.runlion.shop.entity.SysRole;
import com.runlion.shop.entity.SysRoleExample;
import com.runlion.shop.entity.SysRoleResource;
import com.runlion.shop.entity.SysRoleResourceExample;
import com.runlion.shop.entity.SysUserRole;
import com.runlion.shop.entity.common.ResponseData;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.vo.permission.PermissionUserVO;
import com.runlion.shop.vo.permission.RoleResourceVo;

//用户权限、角色相关
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BusinessService businessService;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;
	@Autowired
	private CusUsersPermissionMapper cusUsersPermissionMapper;

	@Autowired
	private BspUserApplyMapper bspUserApplyMapper;

	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper; // NC

	@Autowired
	private SysResourceMapper sysResourceMapper;// 权限

	@Autowired
	private SysRoleMapper sysRoleMapper;// 角色

	@Autowired
	private SysRoleResourceMapper sysRoleResourceMapper;// 角色权限

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;// 用户角色

	// 获取所有的权限
	@RequestMapping("/resourcelist")
	public String resourcelist(ModelMap m) {
		SysResourceExample e = new SysResourceExample();
		List<SysResource> list = sysResourceMapper.selectByExample(e);
		m.put("resourcelist", list);
		return "admin/user/resourcelist";
	}

	// 获取所有的角色
	@RequestMapping("/rolelist")
	public String rolelist(ModelMap m) {
		SysRoleExample e = new SysRoleExample();
		List<SysRole> list = sysRoleMapper.selectByExample(e);
		m.put("rolelist", list);
		return "admin/user/rolelist";
	}

	// 角色新增页面
	@RequestMapping("/roleaddPage")
	public String roleaddPage(ModelMap m) {
		return "admin/user/roleadd";
	}

	// 角色新增
	@ResponseBody
	@RequestMapping("/roleadd")
	public ResponseData roleadd(SysRole record, ModelMap m) {
		int result = sysRoleMapper.insertSelective(record);
		if (result > 0)
			return new ResponseData("success", "角色新增成功！");
		else
			return new ResponseData("error", "角色新增失败！");
	}

	// 角色编辑页面
	@RequestMapping("/roleeditPage")
	public String roleeditPage(Long id, ModelMap m) {
		SysRole role = sysRoleMapper.selectByPrimaryKey(id);
		m.put("role", role);

		List<SysResource> SysResourceList = sysResourceMapper
				.selectByExample(new SysResourceExample());
		m.put("SysResourceList", SysResourceList);

		SysRoleResourceExample e = new SysRoleResourceExample();
		e.createCriteria().andRoleidEqualTo(id);
		List<SysRoleResource> SysRoleResourceList = sysRoleResourceMapper
				.selectByExample(e);
		m.put("SysRoleResourceList", SysRoleResourceList);

		RoleResourceVo vo = new RoleResourceVo();
		vo.setRole(role);

		List<SysResource> resourceList = new ArrayList<SysResource>();
		for (SysRoleResource out : SysRoleResourceList)
			for (SysResource in : SysResourceList)
				if (out.getResourceid().equals(in.getId()))
					resourceList.add(in);
		vo.setResourceList(resourceList);

		m.put("RoleResource", vo);

		return "admin/user/roleedit";
	}

	// 角色编辑
	@ResponseBody
	@RequestMapping("/roleedit")
	public ResponseData roleedit(SysRole record, String resourceList, ModelMap m) {

		// int r1 = sysRoleMapper.updateByPrimaryKeySelective(RoleResource
		// .getRole());
		//
		// int result = 0;
		// if (r1 > 0) {
		// for (SysResource r : RoleResource.getResourceList())
		// result += sysRoleResourceMapper
		// .insertSelective(new SysRoleResource(RoleResource
		// .getRole().getId(), r.getId()));
		// if (result == RoleResource.getResourceList().size())
		// return new ResponseData("success", "角色赋予权限成功！");
		// else
		// return new ResponseData("error", "角色赋予权限失败！");
		// }
		//
		// return new ResponseData("error", "角色编辑失败！");

		Long roleid = record.getId();
		int r1 = sysRoleMapper.updateByPrimaryKeySelective(record);
		String[] list = resourceList.split(",");

		int result = 0;
		if (r1 > 0) {
			for (String rid : list)
				result += sysRoleResourceMapper
						.insertSelective(new SysRoleResource(roleid, Long
								.valueOf(rid)));
			if (result == list.length)
				return new ResponseData("success", "角色赋予权限成功！");
			else
				return new ResponseData("error", "角色赋予权限失败！");
		}

		return new ResponseData("error", "角色编辑失败！");
	}

	// 角色删除
	@ResponseBody
	@RequestMapping("/roledelete")
	public ResponseData roledelete(Long id, ModelMap m) {
		int result = sysRoleMapper.deleteByPrimaryKey(id);
		if (result > 0)
			return new ResponseData("success", "角色删除成功！");
		else
			return new ResponseData("error", "角色删除失败！");
	}

	// 角色赋予权限
	@ResponseBody
	@RequestMapping("/addPermissionToRole")
	public ResponseData addPermissionToRole(Long roleid,
			@RequestParam("resourceList[]") List<Long> resourceList, ModelMap m) {
		int result = 0;
		for (Long rid : resourceList)
			result += sysRoleResourceMapper
					.insertSelective(new SysRoleResource(roleid, rid));
		if (result == resourceList.size())
			return new ResponseData("success", "角色赋予权限成功！");
		else
			return new ResponseData("error", "角色赋予权限失败！");
	}

	// 用户赋予角色
	@ResponseBody
	@RequestMapping("/addRoleToUser")
	public ResponseData addRoleToUser(Long userid,
			@RequestParam("roleList[]") List<Long> roleList, ModelMap m) {
		int result = 0;
		for (Long rid : roleList)
			result += sysUserRoleMapper.insertSelective(new SysUserRole(userid,
					rid));
		if (result == roleList.size())
			return new ResponseData("success", "用户赋予角色成功！");
		else
			return new ResponseData("error", "用户赋予角色失败！");
	}

	// 申请列表 经销商/工厂用户申请
	@RequestMapping("/applylist")
	public String applylist(
			@RequestParam(required = false, defaultValue = "-1", value = "applyStatus") Integer applyStatus,
			ModelMap m) {
		Map<String, Object> map = new HashMap();
		if (!applyStatus.equals(-1)) {
			map.put("applyStatus", applyStatus);
		}

		List<PermissionUserVO> list = cusUsersPermissionMapper
				.selectApplayInfor(map);
		m.put("applyList", list);
		return "admin/user/applylist";
	}

	// 查看申请工厂用户的信息
	@RequestMapping(value = "applyUserInfo")
	public String applyUserInfo(Integer id, ModelMap m) {
		BspUsersPermissionWithBLOBs p = bspUsersPermissionMapper
				.selectByPrimaryKey(id);
		if (p == null)
			return "admin/user/applyUserInfo";
		String type = "";
		if (Constant.UserType_factory == Integer.valueOf(p.getRoleid()))
			type = "2";
		if (Constant.UserType_store == Integer.valueOf(p.getRoleid()))
			type = "1";

		BspUserApplyExample e = new BspUserApplyExample();
		e.createCriteria().andUidEqualTo(p.getUid()).andTypeEqualTo(type);
		List<BspUserApply> list = bspUserApplyMapper.selectByExample(e);
		if (list == null || list.size() == 0)
			return "admin/user/applyUserInfo";
		m.put("apply", list.get(0));
		m.put("id", id);
		m.put("status", p);
		// NC客户编号、门店ID
		if (p.getStoreid() != null) {
			BspSaleaddress address = bspSaleaddressMapper
					.selectByPrimaryKey(Integer.valueOf(p.getStoreid()));
			if (address == null) {
				m.put("NC", "");
			} else {
				m.put("NC", address.getPkcorp());
			}
		}

		return "admin/user/applyUserInfo";
	}

	// 审核通过经销商/工厂用户申请
	@ResponseBody
	@RequestMapping(value = "appproveStoreUserApply")
	public ResponseData appproveStoreUserApply(
			@RequestParam(required = false, defaultValue = "1", value = "id") Integer id,
			@RequestParam(required = false, defaultValue = "1", value = "clientNo") String clientNo,
			ModelMap m) {
		// NC接口查询客户信息 to do 入参：clientNo

		int result = businessService.appproveStoreUserApply(id, clientNo);
		if (result > 0)
			return new ResponseData("success", "审核通过！");
		else
			return new ResponseData("error", "审核失败！");
	}

	// 审核驳回经销商/工厂用户申请
	@ResponseBody
	@RequestMapping(value = "rejectStoreUserApply")
	public ResponseData rejectStoreUserApply(
			@RequestParam(required = false, defaultValue = "1", value = "id") Integer id,
			ModelMap m) {
		int result = businessService.rejectStoreUserApply(id);
		if (result > 0)
			return new ResponseData("success", "审核驳回！");
		else
			return new ResponseData("error", "审核失败！");
	}

	// 删除经销商/工厂用户申请
	@ResponseBody
	@RequestMapping(value = "deleteStoreUserApply")
	public ResponseData deleteStoreUserApply(
			@RequestParam(required = false, defaultValue = "1", value = "id") Integer id,
			ModelMap m) {
		int result = businessService.deleteStoreUserApply(id);
		if (result > 0)
			return new ResponseData("success", "删除成功！");
		else
			return new ResponseData("error", "删除失败！");
	}

}
