package com.svsos.api.web;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Maps;

import com.svsos.api.common.model.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserApi extends WebBase {

	@GET
	public Response getUser(String s) {
		Map<String, Object> result = Maps.newHashMap();
		User user = new User();
		result.put("user", user);
		return Response.ok(result).build();
	}

	@GET
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUser(User user) {
		System.out.println(user);
		Map<String, String> result = Maps.newHashMap();
		result.put("status", "ok");
		return Response.ok(user).build();
	}

}
