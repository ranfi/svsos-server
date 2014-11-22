package com.svsos.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
public class CommonService {

	@PersistenceContext
	private EntityManager entityManager;
    //查询当前时间
	public Timestamp getCurrentTime() {
		Query query = entityManager.createNativeQuery("select now() from dual");
		Timestamp currTime = (Timestamp) query.getSingleResult();
		return currTime;
	}
    
	//查询当前日期
//	public Date getCurrentDate() {
//		Query query = entityManager.createNativeQuery("select CURDATE() from dual");
//		Date currDate = (Date) query.getSingleResult();
//		return currDate;
//	}
}
