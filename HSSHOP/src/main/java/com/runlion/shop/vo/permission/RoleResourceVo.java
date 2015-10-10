package com.runlion.shop.vo.permission;

import java.util.List;

import com.runlion.shop.entity.SysResource;
import com.runlion.shop.entity.SysRole;

public class RoleResourceVo {

	private SysRole role;
	private List<SysResource> resourceList;

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public List<SysResource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<SysResource> resourceList) {
		this.resourceList = resourceList;
	}

}
