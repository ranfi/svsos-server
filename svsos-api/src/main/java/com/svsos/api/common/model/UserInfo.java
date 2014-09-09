package com.svsos.api.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "lx_user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1710983869271877975L;

	private Long uid;
	private String avatarId;
	private String avatarUrl;
	private String bgimageId;
	private Integer score = 0;
	private Integer flowersNum = 0;
	private Integer punishNum = 0;
	private Integer visitNum = 0;
	private Integer consumeFlowersNum = 0;
	private Integer credit = 10;
	private Integer msCount = 0;
	private String photoIds;
	private String accessToken;
	private Timestamp createTime;
	private Timestamp updateTime;


	@Id
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getBgimageId() {
		return bgimageId;
	}

	public void setBgimageId(String bgimageId) {
		this.bgimageId = bgimageId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getFlowersNum() {
		return flowersNum;
	}

	public void setFlowersNum(Integer flowersNum) {
		this.flowersNum = flowersNum;
	}

	public Integer getPunishNum() {
		return punishNum;
	}

	public void setPunishNum(Integer punishNum) {
		this.punishNum = punishNum;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getConsumeFlowersNum() {
		return consumeFlowersNum;
	}

	public void setConsumeFlowersNum(Integer consumeFlowersNum) {
		this.consumeFlowersNum = consumeFlowersNum;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getPhotoIds() {
		return photoIds;
	}

	public void setPhotoIds(String photoIds) {
		this.photoIds = photoIds;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getMsCount() {
		return msCount;
	}

	public void setMsCount(Integer msCount) {
		this.msCount = msCount;
	}

}
