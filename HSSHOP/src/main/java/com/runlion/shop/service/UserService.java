package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspUserdetailsMapper;
import com.runlion.shop.dao.BspUserranksMapper;
import com.runlion.shop.dao.BspUsersMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.entity.BspUserdetails;
import com.runlion.shop.entity.BspUserdetailsExample;
import com.runlion.shop.entity.BspUserranks;
import com.runlion.shop.entity.BspUsers;

@Service
public class UserService {
	@Autowired
	private BspUserranksMapper userranksMapper;
	@Autowired
	private BspUsersMapper usersMapper;
	@Autowired
	private BspUserdetailsMapper userdetailsMapper;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;

	public Map<String, Object> queryUuidParams(String uuid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UUID", uuid);
		return usersMapper.selectUuidParams(par);
	}

	public void addUuidParams(String uuid, String methodName, String paramStr) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UUID", uuid);
		par.put("METHOD_NAME", methodName);
		par.put("PARAM_STR", paramStr);
		usersMapper.insertUuidParams(par);
	}

	public void changePwd(int uid, String password) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UID", uid);
		par.put("PASSWORD", password);
		usersMapper.updateUserPwd(par);
	}

	public Map<String, Object> queryUserByLoginname(String userName) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("USER_NAME", userName);
		return usersMapper.selectUserByLoginname(par);
	}

	public void addUserDetails(int uid, String ip) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UID", uid);
		par.put("IP", ip);
		usersMapper.insertUserDetails(par);
	}

	public int getUid() {
		Integer c = usersMapper.getUserMaxId();
		return c == null ? 1 : c + 1;
	}

	public void addUser(int uid, String userName, String password, String mobile) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UID", uid);
		par.put("USER_NAME", userName);
		par.put("PASSWORD", password);
		par.put("MOBILE", mobile);
		usersMapper.insertUser(par);
	}

	public boolean hasSameUsername(String userName) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("USER_NAME", userName);
		int r = usersMapper.hasSameUsername(par);
		return r > 0;
	}

	public Map<String, Object> authUser(String userName, String password) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("USER_NAME", userName);
		par.put("PASSWORD", password);
		return usersMapper.selectUserEffect(par);
	}

	/**
	 * 查询用户
	 * 
	 * @param userId
	 * @return
	 */
	public BspUsers getUsersById(int userId) {
		return usersMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 用户等级
	 * 
	 * @param userrid
	 * @return
	 */
	public BspUserranks getUserranks(int userrId) {
		return userranksMapper.selectByPrimaryKey((short) userrId);

	}

	/**
	 * 查询用户的详细信息
	 * 
	 * @param userId
	 * @return
	 */
	public BspUserdetails getUserdetails(int userId) {
		BspUserdetailsExample ude = new BspUserdetailsExample();
		ude.or().andUidEqualTo(userId);
		List<BspUserdetails> list = userdetailsMapper.selectByExample(ude);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public int saveUpUserInfor(BspUsers userInfor) {
		return usersMapper.updateByPrimaryKeySelective(userInfor);
	}
}
