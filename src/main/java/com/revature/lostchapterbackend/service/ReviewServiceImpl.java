package com.revature.lostchapterbackend.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.dao.BookDAO;
import com.revature.lostchapterbackend.dao.ReviewDAO;
import com.revature.lostchapterbackend.exceptions.BookNotFoundException;
import com.revature.lostchapterbackend.exceptions.ReviewNotFoundException;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	private Logger logger = LoggerFactory.getLogger(ReviewService.class);

	private ReviewDAO revDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDAO revDao) {
		this.revDao = revDao;
	}

	@Override
	@Transactional
	public List<Review> getAllReviews() {
		logger.info("ReviewService.getAllReviews() invoked.");

		return revDao.findAll();
	}

	@Override
	@Transactional
	public Review getReviewById(int id) throws ReviewNotFoundException {
		logger.info("ReviewService.getReviewById() invoked.");
		Optional<Review> review = revDao.findById(id);
		if (review.isPresent())
			return review.get();
		else return null;
	}

	@Override
	@Transactional
	public int addReview(Review newReview) throws InvalidParameterException {
		logger.info("ReviewService.addReview() invoked.");
		Review review = revDao.save(newReview);
		if(review != null)
		return review.getReviewId();
		else return 0;

	}

	@Override
	@Transactional
	public Review updateReview(Review reviewToUpdate) throws ReviewNotFoundException, InvalidParameterException {
		logger.info("ReviewService.updateReview() invoked.");
		Optional<Review> ReviewFromDatabase = revDao.findById(reviewToUpdate.getReviewId());
		if (ReviewFromDatabase.isPresent()) {
			revDao.save(reviewToUpdate);
			return revDao.findById(reviewToUpdate.getReviewId()).get();
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<Review> getReviewsByBook(int bookId) throws BookNotFoundException {
		try
		{
			List<Review> reviews = revDao.findReviewByBook(bookId);
			return reviews;
		}catch(Exception e)
		{
			throw new BookNotFoundException("Book Id Not Found, Try Again", e);
		}
	}

}