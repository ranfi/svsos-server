package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_ms_relationship")
public class MsRelationship extends IdEntity<Long> {

	private static final long serialVersionUID = -2946919848832366883L;

	private Long masterUid;
	private Long servantUid;
	private Long validTime = 3 * 24 * 60 * 60 * 1000l;
	private Long recruitId;
	private Integer score = 0;
	private String comments;
	private Timestamp createTime;
	private Integer status;
	private Long msRenewalId;

	public Long getMsRenewalId() {
		return msRenewalId;
	}

	public void setMsRenewalId(Long msRenewalId) {
		this.msRenewalId = msRenewalId;
	}

	public Long getMasterUid() {
		return masterUid;
	}

	public void setMasterUid(Long masterUid) {
		this.masterUid = masterUid;
	}

	public Long getServantUid() {
		return servantUid;
	}

	public void setServantUid(Long servantUid) {
		this.servantUid = servantUid;
	}

	public Long getValidTime() {
		return validTime;
	}

	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(Long recruitId) {
		this.recruitId = recruitId;
	}

}
