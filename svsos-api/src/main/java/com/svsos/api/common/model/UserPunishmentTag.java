package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lx_user_punishment_tag")
public class UserPunishmentTag extends IdEntity<Integer> {

	private static final long serialVersionUID = 5956582654909496931L;

	private Long uid;
	private Integer tagId;
	private Integer type;
	private Timestamp createTime;
	private Long validTime;
	private Timestamp expiredTime;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getValidTime() {
		return validTime;
	}

	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}

	public Timestamp getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}

}
