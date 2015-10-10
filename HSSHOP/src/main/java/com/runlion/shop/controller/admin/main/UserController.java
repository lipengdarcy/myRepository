package com.runlion.shop.controller.admin.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.dao.BspUserApplyMapper;
import com.runlion.shop.dao.BspUserdetailsMapper;
import com.runlion.shop.dao.BspUserranksMapper;
import com.runlion.shop.dao.BspUsersMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.dao.SysResourceMapper;
import com.runlion.shop.dao.SysRoleMapper;
import com.runlion.shop.dao.SysRoleResourceMapper;
import com.runlion.shop.dao.SysUserRoleMapper;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspUserdetails;
import com.runlion.shop.entity.BspUserdetailsExample;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.BspUsersExample;
import com.runlion.shop.entity.SysResource;
import com.runlion.shop.entity.SysResourceExample;
import com.runlion.shop.entity.SysRole;
import com.runlion.shop.entity.SysRoleExample;
import com.runlion.shop.entity.SysRoleResource;
import com.runlion.shop.entity.SysRoleResourceExample;
import com.runlion.shop.entity.SysUserRole;
import com.runlion.shop.entity.common.ResponseData;
import com.runlion.shop.vo.permission.RoleResourceVo;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspUsersMapper bum;

	@Autowired
	private BspUserranksMapper burm;

	@Autowired
	private BspUserdetailsMapper budm;

	@Autowired
	private BspRegionsMapper brm;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;

	@Autowired
	private BspUserApplyMapper bspUserApplyMapper;

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

	/**
	 * 用户列表
	 * */
	@RequestMapping("/userlist")
	public ModelAndView userlist(HttpServletRequest request) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		ModelAndView mv = new ModelAndView();
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		// String priceType = "1";// 区域价格
		BspUsersExample bue = new BspUsersExample();
		int cnt = bum.countByExample(bue);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", pageSize);
		List<Map<String, Object>> userlist = bum.selectByExamplePrice(par);
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < userlist.size(); i++) {
			int uid = (Integer) userlist.get(i).get("uid");
			BspUserdetails bud = budm.selectByPrimaryKey(uid);
			if (bud != null) {
				if (bud.getLastvisittime() != null) {
					userlist.get(i).put("lastvisittime",
							formate.format(bud.getLastvisittime()));
				} else {
					userlist.get(i).put("lastvisittime", null);
				}
				if (bud.getRegistertime() != null) {
					userlist.get(i).put("registertime",
							formate.format(bud.getRegistertime()));
				} else {
					userlist.get(i).put("registertime", null);
				}
			} else {
				userlist.get(i).put("lastvisittime", null);
				userlist.get(i).put("registertime", null);
			}
		}

		mv.setViewName("admin/user/userlist");
		mv.addObject("status", "success");
		mv.addObject("userlist", userlist);
		mv.addObject("totalCount", cnt);
		mv.addObject("currentPage", pageNo);
		mv.addObject("numPerPage", pageSize);
		mv.addObject("formate", formate);
		// m.put("status", "success");
		// m.put("userlist", userlist);
		// m.put("totalCount", cnt);
		// m.put("currentPage", pageNo);
		// m.put("numPerPage", pageSize);
		// m.put("formate", formate);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return mv;
	}

	/**
	 * 用户详情（去用户编辑页面）
	 * */
	@RequestMapping("/userinfo")
	public ModelAndView userinfo(int uid) {
		loggerinfo.info("==========[CenterController userinfo] Start...");
		ModelAndView mv = new ModelAndView();
		BspUsers bu = bum.selectByPrimaryKey(uid);
		BspUserdetails bud = budm.selectByPrimaryKey(uid);
		BspRegions br = null;
		String cid = "";
		if (bud != null) {
			if (bud.getRegionid() != 0) {
				br = brm.selectByPrimaryKey(bud.getRegionid());
				cid = getCid(br, bud.getRegionid().toString());
			}
		}
		Short userrid = bu.getUserrid().shortValue();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		mv.setViewName("admin/user/userinfo");
		mv.addObject("userinfo", bu);
		mv.addObject("userdetails", bud);
		mv.addObject("userrid", userrid);
		mv.addObject("formate", formate);
		mv.addObject("cid", cid);
		loggerinfo.info("==========[CenterController userinfo] End...");
		return mv;

		// return "admin/user/userinfo";
	}

	/**
	 * 用户修改
	 * */
	@RequestMapping("/useredit")
	@ResponseBody
	public Map<String, String> useredit(Integer uid, String email,
			String mobile, String nickName, String password, Integer userRid,
			String realName, Integer regionId, Byte gender, String idCard,
			String bid, String address, String bio) throws Exception {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> result = new HashMap<String, String>();
		BspUsers bu = new BspUsers();
		bu.setEmail(email);
		bu.setMobile(mobile);
		bu.setNickname(nickName);
		if (!password.trim().equals("")) {
			bu.setPassword(EncryptUtil.encrypt(password));
		}
		bu.setUserrid(userRid);
		BspUsersExample bue = new BspUsersExample();
		bue.or().andUidEqualTo(uid);
		bum.updateByExampleSelective(bu, bue);
		BspUserdetails budlist = budm.selectByPrimaryKey(uid);
		BspUserdetails bud = new BspUserdetails();
		bud.setRealname(realName);
		bud.setRegionid(regionId);
		bud.setGender(gender);
		bud.setIdcard(idCard);
		bud.setBday(formate.parse(bid));
		bud.setAddress(address);
		bud.setBio(bio);
		if (budlist != null) {
			BspUserdetailsExample bude = new BspUserdetailsExample();
			bude.or().andUidEqualTo(uid);
			budm.updateByExampleSelective(bud, bude);
		} else {
			bud.setUid(uid);
			budm.insertSelective(bud);
		}
		result.put("state", "success");
		return result;
	}

	/**
	 * 用户删除
	 * */
	@RequestMapping("/userdel")
	@ResponseBody
	public Map<String, Object> userdel(Integer uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		bum.deleteByPrimaryKey(uid);
		budm.deleteByPrimaryKey(uid);
		BspUsersExample bue = new BspUsersExample();
		int cnt = bum.countByExample(bue);
		result.put("state", "success");
		result.put("content", cnt);
		return result;
	}

	/**
	 * 新增用户（去新增用户界面）
	 * */
	@RequestMapping("/useradd")
	public String useradd() {
		return "admin/user/useradd";
	}

	/**
	 * 新增用户保存
	 * */
	@RequestMapping("/usersave")
	@ResponseBody
	public Map<String, String> usersave(String username, String email,
			String mobile, String nickName, String password, Integer userRid,
			String realName, Integer regionId, Byte gender, String idCard,
			String bid, String address, String bio) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		BspUsersExample buefind = new BspUsersExample();
		buefind.or().andUsernameEqualTo(username);
		List<BspUsers> bufindlist = bum.selectByExample(buefind);
		if (bufindlist.size() < 1) {
			try {
				BspUsers bu = new BspUsers();
				bu.setUsername(username);
				bu.setEmail(email);
				bu.setMobile(mobile);
				bu.setNickname(nickName);
				if (password.trim().equals("")) {
					bu.setPassword(EncryptUtil.encrypt(password));
				}
				bu.setUserrid(userRid);

				BspUserdetails bud = new BspUserdetails();
				if (bid != null) {
					bud.setBday(formate.parse(bid));
				}

				bum.insertSelective(bu);

				BspUsersExample bue = new BspUsersExample();
				bue.or().andUsernameEqualTo(username);
				List<BspUsers> buslist = bum.selectByExample(bue);

				bud.setUid(buslist.get(0).getUid());
				bud.setRealname(realName);
				bud.setRegionid(regionId);
				bud.setGender(gender);
				bud.setIdcard(idCard);
				bud.setAddress(address);
				bud.setBio(bio);
				budm.insertSelective(bud);
			} catch (ParseException e) {
				loggerinfo.info("时间异常：" + e);
				result.put("state", "error");
				result.put("content", "请填写正确的生日格式！");
			}

			result.put("state", "success");
		} else {
			result.put("state", "error");
			result.put("content", "用户名已存在！");
		}
		return result;
	}

	/**
	 * 用户等级 ranklist
	 * */
	@RequestMapping("/ranklist")
	public String ranklist(HttpServletRequest request, ModelMap m) {
		loggerinfo
				.info("==========[AreaManagerController saleaddress] Start...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		// int startNum = (pageNo - 1) * pageSize;
		// String priceType = "1";// 区域价格
		int cnt = 0;
		BspUsersExample bue = new BspUsersExample();
		List<BspUsers> userlist = bum.selectByExample(bue);
		m.put("status", "success");
		m.put("userlist", userlist);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return "admin/user/userlist";
	}
}
