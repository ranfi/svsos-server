package com.svsos.api.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.svsos.api.common.model.User;
import com.svsos.api.common.model.UserInfo;

public class RequestDTO implements Serializable {

	private static final long serialVersionUID = 3467214898131190924L;
	
	private String appVersion;
	private String phoneModel;
	private String platformType;
	private String accessToken;
	private String command;
	private String serviceProvider;
	private String networkType;
	private UserInfo userInfo;
	private User user;
	
	private final Map<String, Object> param = new HashMap<>();

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
