package com.svsos.backend.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.WxUser;

public interface WxUserDao extends PagingAndSortingRepository<WxUser, Integer> {

	
	//@Query("select a from WxUser a where a.wxId = ?0")
	public WxUser findWxUserByWxId(String wxId);

}
