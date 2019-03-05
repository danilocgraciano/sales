package com.company.order.base;

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

	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext context;
	
	private boolean initialized;

	protected MockMvc mvc;

	public BaseTest(String uri) {
		this.uri = uri;
	}

	protected String getUri() {
		return uri;
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

	protected String getIdFromLocation(MvcResult result) {

		String location = result.getResponse().getHeader("Location");
		String[] arr = location.split("/");
		return arr[arr.length - 1];

	}

}
