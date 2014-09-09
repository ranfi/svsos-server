package com.svsos.application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class ApiApplication extends ResourceConfig {

	public ApiApplication() {

		// 扫描api接口
		packages("com.svsos.api.web");

		// 加载Resource
		register(RequestContextFilter.class);
		// register(CustomObjectMapperProvider.class);
		register(LoggingFilter.class);
		register(JacksonFeature.class);
		register(MultiPartFeature.class);

	}
}
