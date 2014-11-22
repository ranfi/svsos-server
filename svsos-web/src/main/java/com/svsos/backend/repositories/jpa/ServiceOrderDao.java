package com.svsos.backend.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.svsos.backend.model.ServiceOrder;

public interface ServiceOrderDao extends PagingAndSortingRepository<ServiceOrder, Integer>{
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.create_time >= DATE_FORMAT(NOW(),'%Y-%m-%d') ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndCurrentDate(Integer id);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.create_time >= DATE_FORMAT(DATE_SUB(now(),INTERVAL WEEKDAY(now()) day),'%Y-%m-%d') ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndCurrentWeek(Integer id);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.create_time >= DATE_FORMAT(DATE_SUB( DATE_SUB( NOW( ) , INTERVAL DAYOFMONTH( NOW( ) ) -1 DAY ) , INTERVAL 0 MONTH ),'%Y-%m-%d') ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndCurrentMonth(Integer id);

	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and Year(t1.create_time) >= year( curdate( )) ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndCurrenYear(Integer id);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.`status`='3' ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndStatusForAcp(Integer id);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.order_lsh=?2", nativeQuery = true)
	public ServiceOrder findServiceOrderByOrderLsh(Integer id ,String orderLsh);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.`status`='2' ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndStatusForNew(Integer id);
	
	@Query(value = "select * from ss_service_order t1 where t1.accept_worker_id=?1 and t1.`status`>='2' ORDER BY t1.create_time desc", nativeQuery = true)
	public List <ServiceOrder> findServiceOrderByacceptWorkerIdAndStatusForAll(Integer id);
}
