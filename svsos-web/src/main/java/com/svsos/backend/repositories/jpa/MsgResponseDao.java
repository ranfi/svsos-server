package com.svsos.backend.repositories.jpa;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.svsos.backend.model.MsgResponse;

public interface MsgResponseDao extends PagingAndSortingRepository<MsgResponse, Integer>{
	
	public MsgResponse findMsgResponseByKeyword(String Keyword);
	
}
