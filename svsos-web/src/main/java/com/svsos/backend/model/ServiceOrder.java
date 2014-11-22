package com.svsos.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "ss_service_order")
public class ServiceOrder implements Serializable{

	private static final long serialVersionUID = -7729563846450985291L;
	private Integer id;
	private String orderLsh;
	private Integer serviceType;
	private String productBrand;
	private Integer productCategory;
	private String productModel;
	private String productSn;
	private String name;
	private String tel;
	private Double lng;
	private Double lat;		
	private String address;
	private Integer acceptUid;
	private Integer acceptWorkerId;
	private Date startTime;
	private Date endTime;
	private Integer createUid;
	private Timestamp createTime;
	private Integer payStatus;
	private Integer orderChannel;
	private Integer status;
	private String remark;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderLsh() {
		return orderLsh;
	}
	public void setOrderLsh(String orderLsh) {
		this.orderLsh = orderLsh;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public Integer getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(Integer productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getProductSn() {
		return productSn;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAcceptUid() {
		return acceptUid;
	}
	public void setAcceptUid(Integer acceptUid) {
		this.acceptUid = acceptUid;
	}
	public Integer getAcceptWorkerId() {
		return acceptWorkerId;
	}
	public void setAcceptWorkerId(Integer acceptWorkerId) {
		this.acceptWorkerId = acceptWorkerId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getOrderChannel() {
		return orderChannel;
	}
	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
