package com.javaguidesl.springboot;

import com.javaguidesl.springboot.controller.ServerStatusController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TutorialApplicationTests {

		private MockMvc mockMvc;

		@InjectMocks
		private ServerStatusController serverStatusController;

		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
			mockMvc = MockMvcBuilders.standaloneSetup(serverStatusController).build();
		}

		@Test
		public void testServerStatus() throws Exception {
			mockMvc.perform(get("/api/v1/server/status/")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
}