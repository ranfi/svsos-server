package com.svsos.backend.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.MsgSubscribe;

public interface MsgSubscribeDao extends PagingAndSortingRepository<MsgSubscribe, Integer> {

	public MsgSubscribe findMsgSubscribeByStatus(Integer status);

}
