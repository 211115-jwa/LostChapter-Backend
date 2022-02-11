package com.revature.lostchapterbackend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.exceptions.BookNotFoundException;
import com.revature.lostchapterbackend.exceptions.InvalidParameterException;
import com.revature.lostchapterbackend.exceptions.ReviewNotFoundException;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.service.ReviewService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class ReviewServiceTest {
	@MockBean
	private Review reviewDao;
	@Autowired
	private ReviewService reviewServ;
	
	private static List<Review> mockReviews;
	
	@BeforeAll
	public static void mockReviewSetUp() {
			mockReviews = new ArrayList<>();
			Book book1 = new Book();
			book1.setBookId(1);
			Book book2 = new Book();
			book1.setBookId(1);
		
		for (int i=1; i<=5; i++) {
			Review review = new Review();
			review.setReviewId(i);
			if (i<3)
				review.setBook(book1);
			if  (i>3)
				review.setBook(book2);
			mockReviews.add(review);
		}
	}
	
	
//	@Test 
//	public void getAllReviews() {
//		when(reviewDao.findAll()).thenReturn(mockReviews);
//		
//		List<Review> actualReviews = reviewServ.getAllReviews();
//		
//		assertEquals(mockReviews, actualReviews);
//	}
//	
//	@Test 
//	public void getReviewById() throws ReviewNotFoundException {
//		Review review = new Review();
//		review.setReviewId(1);
//		
//		when(reviewDao.findById(1)).thenReturn(Optional.of(review));
//		
//		Book actualReview = reviewServ.getReviewById(1);
//		assertEquals(review, actualReview);
//	}
	
	@Test 
	public void getReviewByIdDoesNotExist() throws ReviewNotFoundException {
		
	}
	
	@Test 
	public void getReviewsByBookExists() throws BookNotFoundException  {
		
	}
	
	@Test 
	public void getReviewsByBookDoesNotExist() throws BookNotFoundException  {
		
	}
	
	@Test 
	public void addReviewSuccessfully() throws InvalidParameterException {
		
	}
	
	@Test 
	public void addReviewUnsuccessfully() throws InvalidParameterException {
		
	}
	
	@Test 
	public void updateReviewExists() throws ReviewNotFoundException, InvalidParameterException {
		
	}
	
	@Test 
	public void updateReviewDoesNotExist() throws ReviewNotFoundException, InvalidParameterException {
		
	}
	

}
