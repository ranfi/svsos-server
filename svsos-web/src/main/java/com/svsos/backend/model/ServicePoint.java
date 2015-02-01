package com.svsos.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ss_service_point")
public class ServicePoint implements Serializable {

	private static final long serialVersionUID = 5789302879016989710L;

	private Integer spId;
	private String spName;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private String contact;
	private String phone;
	private String qq;
	private String address;
	private Integer employeeNum;
	private String intro;
	private Integer score;
	private Integer comments;
	private BigDecimal lngBaidu;
	private BigDecimal latBaidu;
	private String productCids;
	private String coverAreaIds;
	private String serviceBrandIds;
	private String tag;
	private String remark;
	private Timestamp createTime;
	private Integer isVerify;
	private Integer status;
	private Integer syncSpId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sp_id")
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	@Column(name = "sp_name")
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "district_id")
	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	@Column(name = "contact")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "employee_num")
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "comments")
	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	@Column(name = "lng_baidu")
	public BigDecimal getLngBaidu() {
		return lngBaidu;
	}

	public void setLngBaidu(BigDecimal lngBaidu) {
		this.lngBaidu = lngBaidu;
	}

	@Column(name = "lat_baidu")
	public BigDecimal getLatBaidu() {
		return latBaidu;
	}

	public void setLatBaidu(BigDecimal latBaidu) {
		this.latBaidu = latBaidu;
	}

	@Column(name = "product_cids")
	public String getProductCids() {
		return productCids;
	}

	public void setProductCids(String productCids) {
		this.productCids = productCids;
	}

	@Column(name = "cover_area_ids")
	public String getCoverAreaIds() {
		return coverAreaIds;
	}

	public void setCoverAreaIds(String coverAreaIds) {
		this.coverAreaIds = coverAreaIds;
	}

	@Column(name = "service_brand_ids")
	public String getServiceBrandIds() {
		return serviceBrandIds;
	}

	public void setServiceBrandIds(String serviceBrandIds) {
		this.serviceBrandIds = serviceBrandIds;
	}

	@Column(name = "tag")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_verify")
	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "sync_sp_id")
	public Integer getSyncSpId() {
		return syncSpId;
	}

	public void setSyncSpId(Integer syncSpId) {
		this.syncSpId = syncSpId;
	}

}
