package com.revature.lostchapterbackend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.lostchapterbackend.LostChapterBackendApplication;

import com.revature.lostchapterbackend.controller.TransactionController;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.service.TransactionService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class TransactionControllerTest {

	@MockBean
	private  TransactionService transServ;
	
	@Autowired
	private static TransactionController transController;
	private static MockMvc mockMvc;
	
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp () {
		// sets up the minimum architecture to test our controller
		mockMvc = MockMvcBuilders.standaloneSetup(TransactionController.class).build();
	}
	
	@Test
	public void getPurchasedCart(@PathVariable int currentOrderId) throws Exception {
		when(transServ.getPurchasedCart(currentOrderId)).thenReturn(new Cart());
		
		mockMvc.perform(get("/transactions/{currentOrderId}",1)).andExpect(status().isOk()).andReturn();
		
	}
	
		
}
	
	

