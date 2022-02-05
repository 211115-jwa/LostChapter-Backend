package com.revature.lostchapterbackend.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.revature.lostchapterbackend.model.Users;
import com.revature.lostchapterbackend.model.Book;


@Entity
@Table
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;

	@Column(nullable = false)
	private Book book;
	
	@Column(nullable = false)
	private Users user;
	
	private String reviewTitle;
	private String reviewText;
	//rating names to be set on the front end based on book genre
	//For Fiction, ratingOne is Plot
	//For Non-Fiction, ratingOne is ???
	@Column(nullable = false)
	private int ratingOne;
	
	//For Fiction, ratingTwo is Pacing
	//For Non-Fiction, ratingTwo is ???
	@Column(nullable = false)
	private int ratingTwo;
	
	//For Fiction, ratingThree is Prose
	//For Non-Fiction, ratingThree is ???
	@Column(nullable = false)
	private int ratingThree;

	private LocalDateTime sentAt;
	
	public Review() {
		super();
	}
	
	public Review(int reviewId, Book book, Users user, String reviewTitle, String reviewText, int ratingOne,
			int ratingTwo, int ratingThree, LocalDateTime sentAt) {
		super();
		this.reviewId = reviewId;
		this.book = book;
		this.user = user;
		this.reviewTitle = reviewTitle;
		this.reviewText = reviewText;
		this.ratingOne = ratingOne;
		this.ratingTwo = ratingTwo;
		this.ratingThree = ratingThree;
		this.sentAt = sentAt;
	}
	
	public double getOverallRating() {
		return (ratingOne + ratingTwo + ratingThree)/3;
		
	}

	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getRatingOne() {
		return ratingOne;
	}
	public void setRatingOne(int ratingOne) {
		this.ratingOne = ratingOne;
	}
	public int getRatingTwo() {
		return ratingTwo;
	}
	public void setRatingTwo(int ratingTwo) {
		this.ratingTwo = ratingTwo;
	}
	public int getRatingThree() {
		return ratingThree;
	}
	public void setRatingThree(int ratingThree) {
		this.ratingThree = ratingThree;
	}
	public LocalDateTime getSentAt() {
		return sentAt;
	}
	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(book, ratingOne, ratingThree, ratingTwo, reviewId, reviewText, reviewTitle, sentAt, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(book, other.book) && ratingOne == other.ratingOne && ratingThree == other.ratingThree
				&& ratingTwo == other.ratingTwo && reviewId == other.reviewId
				&& Objects.equals(reviewText, other.reviewText) && Objects.equals(reviewTitle, other.reviewTitle)
				&& Objects.equals(sentAt, other.sentAt) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", book=" + book + ", user=" + user + ", reviewTitle=" + reviewTitle
				+ ", reviewText=" + reviewText + ", ratingOne=" + ratingOne + ", ratingTwo=" + ratingTwo
				+ ", ratingThree=" + ratingThree + ", sentAt=" + sentAt + "]";
	}
	
		
}
