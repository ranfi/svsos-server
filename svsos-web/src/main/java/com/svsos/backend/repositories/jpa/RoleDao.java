/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.svsos.backend.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

}
