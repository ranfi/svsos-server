package com.svsos.backend.repositories.jpa;

import com.svsos.backend.model.WorkUser;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface WorkUserDao extends PagingAndSortingRepository<WorkUser, Integer> {

	public WorkUser findWorkUserByAccount(String account);

}
