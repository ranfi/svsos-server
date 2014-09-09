package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_request_servant")
public class RequestServant extends IdEntity<Long>{
	private static final long serialVersionUID = -812363123597771558L;
	
	private Long recruitId;
	private Long requestUid;
	private Timestamp requestTime;
	private Integer requestType;
	private String introduce;
	private Integer status;
	private Recruit recruit;
	
	public Long getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(Long recruitId) {
		this.recruitId = recruitId;
	}
	public Long getRequestUid() {
		return requestUid;
	}
	public void setRequestUid(Long requestUid) {
		this.requestUid = requestUid;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "recruitId", insertable = false, updatable = false)
	public Recruit getRecruit() {
		return recruit;
	}
	public void setRecruit(Recruit recruit) {
		this.recruit = recruit;
	}
	
	
}
