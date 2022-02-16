package com.revature.lostchapterbackend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.controller.BookController;
import com.revature.lostchapterbackend.controller.CreditCardInfoController;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.BookService;
import com.revature.lostchapterbackend.service.CreditCardInfoServ;

//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes=LostChapterBackendApplication.class)
public class CreditCardControllerTest {
	@MockBean
	private CreditCardInfoServ cardServ;
	
	@Autowired
	private CreditCardInfoController cardController;
	
	private static MockMvc mockMvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// this initializes the Spring Web/MVC architecture for just one controller
		// so that we can isolate and unit test it
		mockMvc = MockMvcBuilders.standaloneSetup(BookController.class).build();
	}
	
}