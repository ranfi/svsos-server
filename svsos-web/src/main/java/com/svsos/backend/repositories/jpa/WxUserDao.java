package com.svsos.backend.repositories.jpa;

import com.svsos.backend.model.WxUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WxUserDao extends PagingAndSortingRepository<WxUser, Integer> {

	
	//@Query("select a from WxUser a where a.wxId = ?0")
	public WxUser findWxUserByWxId(String wxId);

}
