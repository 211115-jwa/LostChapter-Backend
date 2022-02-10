package com.revature.lostchapterbackend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Collections;

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
import com.revature.lostchapterbackend.controller.ReviewController;
import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.service.BookService;
import com.revature.lostchapterbackend.service.ReviewService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class ReviewControllerTest {

	@MockBean
	private BookService bookServ;
	
	@MockBean
	private ReviewService reviewServ;
	
	@Autowired
	private ReviewController reviewController;
	
	private static MockMvc mockMvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// this initializes the Spring Web/MVC architecture for just one controller
		// so that we can isolate and unit test it
		mockMvc = MockMvcBuilders.standaloneSetup(ReviewController.class).build();
	}
	
	@Test
	public void getAllReviews() throws Exception {
		when(reviewServ.getAllReviews()).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/reviews"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();
		
	}
	
	@Test
	public void getReviewById () throws Exception {
		when(reviewServ.getReviewById("id")).thenReturn(new Review());
		
		mockMvc.perform(get("/reviews/{reviewId}", "id")).andExpect(status().isOk()).andReturn();
		
	}
	
//	@Test
//	public void postNewReview () throws Exception {
//		Review newReview = new Review();
//		when(reviewServ.addReview(newReview)).thenReturn(new Review());
//		
//		String jsonPet = objMapper.writeValueAsString(newReview);
//		DateFormat jsonDate = objMapper.getDateFormat();
//		
//		mockMvc.perform(post("/reviews").content(jsonPet).contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isCreated())
//			.andReturn();
//
//		
//	}
	
//	@Test
//	public void updateReviewById () throws Exception {
//		Review reviewToEdit = new Review();
//		reviewToEdit.setReviewId(1);;
//		
//		when(reviewServ.updateReview(reviewToEdit, "1"));
//		String jsonReview = objMapper.writeValueAsString(reviewToEdit);
//		mockMvc.perform(put("/reviews/{reviewId}", 1).content(jsonReview).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().json(jsonReview))
//				.andReturn();
//	}
	
	@Test
	public void getAllReviewsForBook () throws Exception {
		when(reviewServ.getReviewsByBook(bookServ.getBookById(1))).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/reviews/book/{bookId}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();
		
	}
}
	

