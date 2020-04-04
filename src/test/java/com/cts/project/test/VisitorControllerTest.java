package com.cts.project.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.project.Controller.VisitorController;

public class VisitorControllerTest {

	@InjectMocks
	private VisitorController visitorController;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(visitorController).build();
	
	}

	@Test
		public void logOutTest() throws Exception {
			this.mockMvc.perform(get("/visitor/logOut.html")).andExpect(status().isOk());
		
	}

}
