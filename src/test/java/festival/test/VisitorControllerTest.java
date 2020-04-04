package festival.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.project.Controller.VisitorController;

public class VisitorControllerTest {

	
	@InjectMocks
	private VisitorController helloWorldController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();
	}

	@Test
	public void testVisitorLogout() throws Exception {
		this.mockMvc.perform(get("/visitor/logOut.html")).andExpect(status().isOk());
	}


}
