package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import com.runlion.shop.vo.permission.PermissionUserVO;

public interface CusUsersPermissionMapper {

	List<PermissionUserVO> selectApplayInfor(Map<String, Object> par);
}
