package com.svsos.backend.repositories.jpa;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.entity.MenuRole;
import com.svsos.backend.entity.MenuRolePK;

public interface MenuRoleDao extends PagingAndSortingRepository<MenuRole, MenuRolePK>{
}
