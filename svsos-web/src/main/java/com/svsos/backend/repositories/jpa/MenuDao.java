package com.svsos.backend.repositories.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.entity.Menu;

public interface MenuDao extends PagingAndSortingRepository<Menu, Integer> {

    public List<Menu> findByPidAndIsLeafNodeNot(Integer pid, Integer isLeafNode, Sort sort);

    public List<Menu> findByPid(Integer pid, Sort sort);

    @Query(value = "select * from ss_menu t1 where t1.pid=?1 and t1.id in (select t2.menu_id from ss_menu_role t2 where t2.role_id in(?2))", nativeQuery = true)
    public List<Menu> findByUserRoles(long pid, String roleIds);

}
