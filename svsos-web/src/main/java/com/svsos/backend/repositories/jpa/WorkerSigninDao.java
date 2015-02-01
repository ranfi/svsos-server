package com.svsos.backend.repositories.jpa;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.WorkerSignin;

public interface WorkerSigninDao extends PagingAndSortingRepository<WorkerSignin, Integer> {

	@Query(value = "select * from ss_sp_worker_signin t where t.worker_id=?1 and t.signin_date= DATE_FORMAT(NOW(),'%Y-%m-%d')", nativeQuery = true)
	public List<WorkerSignin> findWorkerSigninForCurrByWorkId(Integer id);

	@Query(value = "select * from ss_sp_worker_signin t where t.worker_id=?1 ORDER BY t.signin_date DESC", nativeQuery = true)
	public List<WorkerSignin> findWorkerSigninByWorkId(Integer id);

	@Query(value = "from WorkerSignin where workerId = ?1 and signinDate = ?2")
	public WorkerSignin findWorkerSigninByWorkIdAndSignindate(Integer id, Date signinDate);

}
