package com.svsos.api.context;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class CustomObjectMapperProvider implements ContextResolver<ObjectMapper> {
	final ObjectMapper defaultObjectMapper;

	public CustomObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {

		return defaultObjectMapper;
	}

	public static ObjectMapper createCombinedObjectMapper() {
		return new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)
				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
				.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT);

		return result;
	}

	public static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

		return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
	}

}
