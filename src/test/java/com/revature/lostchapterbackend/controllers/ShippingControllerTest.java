package com.revature.lostchapterbackend.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.controller.ReviewController;
import com.revature.lostchapterbackend.controller.ShippingController;
import com.revature.lostchapterbackend.service.ShippingService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class ShippingControllerTest {
	
	@MockBean
	private ShippingService shipServ;
	
	@Autowired
	private ShippingController shipController;
	
	private static MockMvc mockMvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// this initializes the Spring Web/MVC architecture for just one controller
		// so that we can isolate and unit test it
		mockMvc = MockMvcBuilders.standaloneSetup(ReviewController.class).build();
	}
	

}
