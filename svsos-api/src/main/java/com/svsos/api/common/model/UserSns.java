package com.svsos.api.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_user_sns")
public class UserSns implements Serializable{
	
	private static final long serialVersionUID = -3614416737982475016L;
	
	private Long uid;
	private Long snsUid;
	private String accessToken;
	private Timestamp updateTime;
	private Timestamp createTime;
	
	@Id
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getSnsUid() {
		return snsUid;
	}
	public void setSnsUid(Long snsUid) {
		this.snsUid = snsUid;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
