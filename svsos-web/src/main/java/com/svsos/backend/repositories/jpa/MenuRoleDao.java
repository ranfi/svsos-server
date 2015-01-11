package com.svsos.backend.repositories.jpa;


import com.svsos.backend.entity.MenuRole;
import com.svsos.backend.entity.MenuRolePK;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MenuRoleDao extends PagingAndSortingRepository<MenuRole, MenuRolePK>{
}
