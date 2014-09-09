package com.svsos.api.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lx_punishment_tag")
public class PunishmentTag extends IdEntity<Integer> {

	private static final long serialVersionUID = -3671597512527755005L;

	private String title;
	private Integer type;
	private String tag;
	private Long validTime;
	private Integer isFree;
	private Integer isShow;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getValidTime() {
		return validTime;
	}

	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

}
