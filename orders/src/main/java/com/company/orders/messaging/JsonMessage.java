package com.company.orders.messaging;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
public class JsonMessage {

	private MessageType type;
	private Object body;
	@JsonIgnore
	private ObjectMapper mapper;

	public JsonMessage(MessageType type, Object body) {
		this.type = type;
		this.body = body;
		this.mapper = new ObjectMapper();
		this.mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}

	@Override
	public String toString() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
