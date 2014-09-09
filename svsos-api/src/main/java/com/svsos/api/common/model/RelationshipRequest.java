package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_relationship_request")
public class RelationshipRequest extends IdEntity<Long>{

	private static final long serialVersionUID = -6153057213553638777L;
	
	private Long requestUid;
	private Long goalUid;
	private Integer type;
	private String info;
	private Integer status;
	private Timestamp requestTime;
	private Timestamp responseTime;
	private String responseContent;
	
	public Long getRequestUid() {
		return requestUid;
	}
	public void setRequestUid(Long requestUid) {
		this.requestUid = requestUid;
	}
	public Long getGoalUid() {
		return goalUid;
	}
	public void setGoalUid(Long goalUid) {
		this.goalUid = goalUid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public Timestamp getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
	
	
}
