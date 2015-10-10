package com.runlion.shop.vo.permission;

import com.runlion.shop.entity.BspUsersPermissionWithBLOBs;

public class PermissionUserVO extends BspUsersPermissionWithBLOBs {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
