package com.svsos.backend.entity;

import java.io.Serializable;

public class MenuRolePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7880318594070507799L;
	private long roleId;
	private long menuId;
	
	public MenuRolePK(){
	}
	
	public MenuRolePK(long roleId, long menuId){
		this.menuId = menuId;
		this.roleId = roleId;
	}
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		
		if (obj == null){
			return false;
		}
		
		if(getClass() != obj.getClass()){
			return false;
		}
		MenuRolePK menuRole = (MenuRolePK)obj;
		if(this.getMenuId()==menuRole.getMenuId() && this.getRoleId()==menuRole.getRoleId()){
			return true;
		}
		return false;
	}
	
}
