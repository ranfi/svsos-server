package com.svsos.backend.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.WorkUser;

public interface WorkUserDao extends PagingAndSortingRepository<WorkUser, Integer> {

	public WorkUser findWorkUserByAccount(String account);

}
