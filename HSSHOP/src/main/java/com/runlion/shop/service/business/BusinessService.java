package com.runlion.shop.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.common.Constant;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspUserApplyMapper;
import com.runlion.shop.dao.BspUserdetailsMapper;
import com.runlion.shop.dao.BspUsersMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspSaleaddressExample;
import com.runlion.shop.entity.BspUserApply;
import com.runlion.shop.entity.BspUsersPermission;
import com.runlion.shop.entity.BspUsersPermissionExample;
import com.runlion.shop.entity.BspUsersPermissionWithBLOBs;

@Service
public class BusinessService {

	@Autowired
	private BspUsersMapper bspUsersMapper;

	@Autowired
	private BspUserdetailsMapper bspUserdetailsMapper;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;
	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper;

	@Autowired
	private BspUserApplyMapper bspUserApplyMapper;

	// 判断用户类型 1：普通用户，2：门店用户，3：工厂用户
	/**
	 * 一个用户多种角色，第一个数字表示角色数量，后面的的数字表示角色id 如： 11：一种角色，普通用户 。213：两种角色，普通用户、工厂用户。
	 * 3123：三种角色，普通用户、门店用户、工厂用户
	 * */
	public int getUserType(int uid) {
		int roleid = 11;// 默认就是一种角色，即普通用户
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(uid)
				.andApplystatusEqualTo(Constant.ApplyStatus_approved);
		List<BspUsersPermission> list = bspUsersPermissionMapper
				.selectByExample(e);
		// 1：普通用户
		if (list == null || list.size() == 0)
			return roleid;
		roleid = (int) Math.pow(10, list.size()) * list.size();
		for (int i = 0; i < list.size(); i++) {
			BspUsersPermission b = list.get(i);
			int id = Integer.valueOf(b.getRoleid());
			roleid += id * (int) Math.pow(10, i);
		}
		return roleid;
	}

	// 判断是否是工厂用户
	public boolean isFactoryUser(int roleid) {
		// 一种角色，普通用户
		if (roleid < 100)
			return false;
		// 三种角色，普通用户、门店用户、工厂用户
		if (roleid > 1000)
			return true;
		// 两种角色
		roleid = roleid % 100;
		int a = roleid / 10;
		int b = roleid % 10;
		if (a == Constant.UserType_factory || b == Constant.UserType_factory)
			return true;

		return false;
	}

	// 判断是否是经销商用户
	public boolean isStoreUser(int roleid) {
		// 一种角色，普通用户
		if (roleid < 100)
			return false;
		// 三种角色，普通用户、门店用户、工厂用户
		if (roleid > 1000)
			return true;
		// 两种角色
		roleid = roleid % 100;
		int a = roleid / 10;
		int b = roleid % 10;
		if (a == Constant.UserType_store || b == Constant.UserType_store)
			return true;

		return false;
	}

	// 获取工厂门店id
	public int getStoreId(int uid, int roleid) {
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(uid)
				.andRoleidEqualTo(String.valueOf(roleid));
		List<BspUsersPermission> list = bspUsersPermissionMapper
				.selectByExample(e);
		if (list == null || list.size() == 0
				|| list.get(0).getStoreid() == null)
			return -1;
		return Integer.valueOf(list.get(0).getStoreid());

	}

	// 获取关联的工厂门店信息
	public BspSaleaddress getStoreInfor(int uid, int roleid) {
		int id = this.getStoreId(uid, roleid);
		return bspSaleaddressMapper.selectByPrimaryKey(id);
	}

	// 判断用户是否已经申请过
	public boolean isApplyedStoreUser(int uid, int userType) {
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(uid)
				.andRoleidEqualTo(String.valueOf(userType));
		List<BspUsersPermission> list = bspUsersPermissionMapper
				.selectByExample(e);
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	// 申请为经销商/工厂用户
	@Transactional
	public int applyStoreUser(int uid, int userType, BspUserApply apply) {
		// step 1: 插入申请记录
		bspUserApplyMapper.insertSelective(apply);

		// step 2： 检查BspUsersPermission是否存在普通用户的记录，不存在则先增加普通用户记录
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(uid)
				.andRoleidEqualTo(String.valueOf(Constant.UserType_client));
		List<BspUsersPermission> list = bspUsersPermissionMapper
				.selectByExample(e);
		if (list == null || list.size() == 0) {
			BspUsersPermissionWithBLOBs record = new BspUsersPermissionWithBLOBs();
			record.setUid(uid);
			record.setRoleid(String.valueOf(Constant.UserType_client));
			record.setApplystatus(Constant.ApplyStatus_approved);
			bspUsersPermissionMapper.insertSelective(record);
		}

		// step 3： 插入BspUsersPermission申请工厂或者门店的记录
		BspUsersPermissionWithBLOBs record = new BspUsersPermissionWithBLOBs();
		record.setUid(uid);
		record.setRoleid(String.valueOf(Constant.UserType_store));
		if (userType == Constant.UserType_store)
			record.setRoleid(String.valueOf(Constant.UserType_store));
		else if (userType == Constant.UserType_factory)
			record.setRoleid(String.valueOf(Constant.UserType_factory));
		else
			record.setRoleid(String.valueOf(Constant.UserType_client));

		record.setApplystatus(Constant.ApplyStatus_applying);
		int r = bspUsersPermissionMapper.insertSelective(record);
		return r;
	}

	// 审核通过经销商/工厂用户申请
	@Transactional
	public int appproveStoreUserApply(int id, String clientNo) {
		int storeid = 0;
		// 更新审批状态&关联门店id
		BspSaleaddressExample e = new BspSaleaddressExample();
		e.createCriteria().andPkcorpEqualTo(clientNo);
		List<BspSaleaddress> list = bspSaleaddressMapper.selectByExample(e);
		if (list != null && list.size() > 0)
			storeid = list.get(0).getId();

		BspUsersPermissionWithBLOBs record = bspUsersPermissionMapper
				.selectByPrimaryKey(id);
		record.setApplystatus(Constant.ApplyStatus_approved);
		record.setStoreid(String.valueOf(storeid));
		int r1 = bspUsersPermissionMapper.updateByPrimaryKeySelective(record);
		return r1;
	}

	// 审核驳回经销商/工厂用户申请
	public int rejectStoreUserApply(int id) {
		BspUsersPermissionWithBLOBs record = bspUsersPermissionMapper
				.selectByPrimaryKey(id);
		record.setApplystatus(Constant.ApplyStatus_rejected);
		return bspUsersPermissionMapper.updateByPrimaryKeySelective(record);
	}

	// 删除经销商/工厂用户申请
	public int deleteStoreUserApply(int id) {
		return bspUsersPermissionMapper.deleteByPrimaryKey(id);
	}

	// 根据用户id获取该用户在nc系统中的客户编号
	public String getUserNcNumber(int uid) {
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(uid)
				.andApplystatusEqualTo(Constant.ApplyStatus_approved);
		List<BspUsersPermission> list = bspUsersPermissionMapper
				.selectByExample(e);
		// 1：普通用户没有nc编号
		if (list == null || list.size() == 0)
			return "";

		for (int i = 0; i < list.size(); i++) {
			BspUsersPermission b = list.get(i);
			int id = Integer.valueOf(b.getRoleid());
			if (id == Constant.UserType_store) {
				// 针对经销商用户
				BspSaleaddress address = bspSaleaddressMapper
						.selectByPrimaryKey(Integer.valueOf(b.getStoreid()));
				return address.getPkcorp();
			}

		}
		return "";
	}

}
