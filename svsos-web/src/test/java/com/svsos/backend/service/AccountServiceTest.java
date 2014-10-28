/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.svsos.backend.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.svsos.backend.common.exception.ServiceException;
import com.svsos.backend.entity.User;
import com.svsos.backend.repositories.jpa.UserDao;
import com.svsos.backend.security.ShiroTestUtils;
import com.svsos.backend.service.AccountService;
import com.svsos.backend.service.ShiroDbRealm.ShiroUser;

public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private UserDao mockUserDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject(new ShiroUser("foo", "Foo"));
	}

	@After
	public void tearDown() {
		ShiroTestUtils.clearSubject();
	}

	@Test
	public void saveUser() {
		User admin = new User();
		admin.setId(1L);

		User user = new User();
		user.setId(2L);
		user.setName("ranfi");
		user.setPlainPassword("123");

		// 正常保存用户.
		accountService.saveUser(user);

		// 保存超级管理用户抛出异常.
		try {
			accountService.saveUser(admin);
		} catch (ServiceException e) {
			// expected exception
		}
		Mockito.verify(mockUserDao, Mockito.never()).delete(1L);
	}
}
