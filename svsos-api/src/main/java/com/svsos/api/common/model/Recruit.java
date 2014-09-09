package com.svsos.api.common.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "lx_recruit")
public class Recruit extends IdEntity<Long>{

	private static final long serialVersionUID = -6940348109788051971L;

	private Long masterUid;
	private String info;
	private Integer status = 0;
	private Integer servantCount = 0;
	private Integer requestCount = 0;
	private Integer scanCount = 0;
	private String picId;
	private Long validTime = 24*60*60*1000l;
	private Timestamp publishTime;
	
	
	public Long getMasterUid() {
		return masterUid;
	}
	public void setMasterUid(Long masterUid) {
		this.masterUid = masterUid;
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
	public Integer getServantCount() {
		return servantCount;
	}
	public void setServantCount(Integer servantCount) {
		this.servantCount = servantCount;
	}
	public Integer getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}
	public Integer getScanCount() {
		return scanCount;
	}
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public Long getValidTime() {
		return validTime;
	}
	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	
}
