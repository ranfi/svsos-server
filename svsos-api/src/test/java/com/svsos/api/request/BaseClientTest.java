package com.svsos.api.request;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public abstract class BaseClientTest {

	protected <T> T get(String url, Class<T> cls) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(url);
		Builder request = webTarget.request(MediaType.APPLICATION_JSON);
		return request.get().readEntity(cls);
	}

	protected <T> T post(String url, Object data, Class<T> cls) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(url);System.out.println(MediaType.APPLICATION_JSON);
		Builder request = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = request.post(Entity.entity(data, MediaType.APPLICATION_JSON));
		return response.readEntity(cls);
	}
}
