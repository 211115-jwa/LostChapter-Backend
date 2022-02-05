package com.revature.lostchapterbackend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.BookNotFoundException;
import com.revature.lostchapterbackend.exceptions.ReviewNotFoundException;
import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.service.BookService;
import com.revature.lostchapterbackend.service.ReviewService;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ReviewController {
	private Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BookService bookService;

	@GetMapping(path = "/reviews")
	public List<Review> getAllReviews() {
		logger.info("ReviewController.getAllReviews() invoked.");
		List<Review> allReviews = reviewService.getAllReviews();
		return allReviews;
	}

	@GetMapping(path = "/reviews/{reviewId}")
	public ResponseEntity<Object> getReviewById(@PathVariable String reviewId) 
			throws ReviewNotFoundException {
		logger.info("ReviewController.getReviewById() invoked.");
		try {
			Review review = reviewService.getReviewById(reviewId);
			return ResponseEntity.ok(review);
		} catch (ReviewNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(path = "/reviews")
	public ResponseEntity<Object> postNewReview(@RequestBody Review newReview) {
		logger.info("ReviewController.postNewReview() invoked.");

		if (newReview != null) {
			newReview.setSentAt(LocalDateTime.now());
			reviewService.addReview(newReview);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping(path = "/reviews/{reviewId}")
	public ResponseEntity<Object> updateReviewById(@PathVariable String reviewId, @RequestBody Review updatedReview)
			throws ReviewNotFoundException {
		logger.info("ReviewController.updateReviewById() invoked.");

		try {
			reviewService.updateReview(updatedReview, reviewId);
			return ResponseEntity.ok(updatedReview);
		} catch (ReviewNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/reviews/book/{bookId}")
	public ResponseEntity<Object> getAllReviewsForBook(@PathVariable String bookId) 
			throws BookNotFoundException {
		logger.info("ReviewController.getAllReviewsForBook() invoked.");
		try {
			List<Review> reviewList = reviewService.getReviewsByBook(bookService.getBookById(bookId));
			return ResponseEntity.ok(reviewList);
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
