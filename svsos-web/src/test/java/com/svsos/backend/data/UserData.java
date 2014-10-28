/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.svsos.backend.data;

import java.util.Random;

import com.svsos.backend.entity.Role;
import com.svsos.backend.entity.User;

/**
 * 用户测试数据生成.
 * 
 * @author calvin
 */
public class UserData {

	public static User randomUser() {
		String userName = String.valueOf(new Random(100000000l));

		User user = new User();
		user.setLoginName(userName);
		user.setName(userName);
		user.setPlainPassword("123456");
		user.setEmail(userName + "@springside.org.cn");

		return user;
	}

	public static User randomUserWithAdminRole() {
		User user = UserData.randomUser();
		Role adminRole = UserData.adminRole();
		user.getRoleList().add(adminRole);
		return user;
	}

	public static Role adminRole() {
		Role role = new Role();
		role.setId(1L);
		role.setName("Admin");

		return role;
	}
}
