package com.svsos.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "ss_menu_role")
@IdClass(MenuRolePK.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MenuRole {
	private long roleId;
	private long menuId;
	
	@Id
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Id
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	
}
