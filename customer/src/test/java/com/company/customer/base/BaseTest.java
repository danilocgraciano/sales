package com.company.customer.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {

	private String uri;

	private static String id;

	private ObjectMapper mapper;

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;

	private boolean initialized;

	public BaseTest(String uri) {
		this.uri = uri;
	}

	protected String getUri() {
		return uri;
	}

	protected String getId() {
		return id;
	}

	protected String getUriId() {
		return getUri() + "/" + getId();
	}

	public String asJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Before
	public void setup() {

		if (this.initialized)
			return;

		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		this.mapper = new ObjectMapper();
		this.initialized = true;
	}

	protected void readIdFromLocation(MvcResult result) {

		String location = result.getResponse().getHeader("Location");
		String[] arr = location.split("/");
		id = arr[arr.length - 1];

	}

}
