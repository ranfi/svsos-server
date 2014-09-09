package com.svsos.api.common.service;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommonService {

	@PersistenceContext
	private EntityManager entityManager;

	public Timestamp getCurrentTime() {
		Query query = entityManager.createNativeQuery("select now() from dual");
		Timestamp currTime = (Timestamp) query.getSingleResult();
		return currTime;
	}

}
