package com.svsos.backend.repositories.jpa;

import com.svsos.backend.model.MsgResponse;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MsgResponseDao extends PagingAndSortingRepository<MsgResponse, Integer>{
	
	public MsgResponse findMsgResponseByKeyword(String Keyword);
	
}
