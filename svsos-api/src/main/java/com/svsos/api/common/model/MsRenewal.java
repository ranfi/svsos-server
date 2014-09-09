package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
@Table(name = "lx_ms_renewal")
public class MsRenewal extends IdEntity<Long> {
	private static final long serialVersionUID = -7190589179479564628L;
	
	private Long msRelationshipId;
	private Long requestUid;
	private Integer status;
	private String content;
	private Timestamp requestTime;
	private MsRelationship msRelationship;
	
	
	public Long getMsRelationshipId() {
		return msRelationshipId;
	}
	public void setMsRelationshipId(Long msRelationshipId) {
		this.msRelationshipId = msRelationshipId;
	}
	public Long getRequestUid() {
		return requestUid;
	}
	public void setRequestUid(Long requestUid) {
		this.requestUid = requestUid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "msRelationshipId", insertable = false, updatable = false)
	@JsonIgnore
	public MsRelationship getMsRelationship() {
		return msRelationship;
	}
	public void setMsRelationship(MsRelationship msRelationship) {
		this.msRelationship = msRelationship;
	}
	
}
