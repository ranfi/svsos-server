package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_ms_task")
public class MsTask extends IdEntity<Long>{

	private static final long serialVersionUID = 1036476649199545409L;
	
	private Long publishUid;
	private Integer type;
	private String title;
	private String contentType;
	private String content;
	private Double lng_baidu;
	private Double lat_baidu;
	private Double lng_google;
	private Double lat_google;
	private String address;
	private Timestamp createTime;
	private Long acceptValidTime;
	private Timestamp taskTime;
	private Long taskTimeLimit;
	private Integer taskStatus;
	
	
	public Double getLng_baidu() {
		return lng_baidu;
	}
	public void setLng_baidu(Double lng_baidu) {
		this.lng_baidu = lng_baidu;
	}
	public Double getLat_baidu() {
		return lat_baidu;
	}
	public void setLat_baidu(Double lat_baidu) {
		this.lat_baidu = lat_baidu;
	}
	public Double getLng_google() {
		return lng_google;
	}
	public void setLng_google(Double lng_google) {
		this.lng_google = lng_google;
	}
	public Double getLat_google() {
		return lat_google;
	}
	public void setLat_google(Double lat_google) {
		this.lat_google = lat_google;
	}
	
	public Long getPublishUid() {
		return publishUid;
	}
	public void setPublishUid(Long publishUid) {
		this.publishUid = publishUid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getAcceptValidTime() {
		return acceptValidTime;
	}
	public void setAcceptValidTime(Long acceptValidTime) {
		this.acceptValidTime = acceptValidTime;
	}
	public Timestamp getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(Timestamp taskTime) {
		this.taskTime = taskTime;
	}
	public Long getTaskTimeLimit() {
		return taskTimeLimit;
	}
	public void setTaskTimeLimit(Long taskTimeLimit) {
		this.taskTimeLimit = taskTimeLimit;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	

}
