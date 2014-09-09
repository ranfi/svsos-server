package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_reward_punishment")
public class RewardPunishment extends IdEntity<Long> {

	private static final long serialVersionUID = -8132814135336996308L;

	private Long masterUid;
	private Long servantUid;
	private Long relationshipId;
	private Integer category;
	private String content;
	private Timestamp createTime;

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

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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

}
