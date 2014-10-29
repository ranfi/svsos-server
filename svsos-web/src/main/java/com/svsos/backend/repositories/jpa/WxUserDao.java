package com.svsos.backend.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.WxUser;

public interface WxUserDao extends PagingAndSortingRepository<WxUser, Integer> {

}
