package com.revature.lostchapterbackend.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="book")
public class Book {
	//This is the second most important object in this project
	//Has the values of
		//String ISBN, bookName, synopsis, author, genre, publisher, bookImage
		//int year, quantityOnHand
		//boolean saleIsActive, featured
		//float bookPrice, saleDiscountRate, saleDiscountRate
	//Has the special methods of
		//hashCode: hashes all of the book information
		//equals: see if there is a matching book in the database 
		//toString: converts all of the books information into a string

	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@Column(name="isbn")
	private String ISBN;

	@Column(name="book_name")
	private String bookName;

	@Column(name="synopsis")
	private String synopsis;

	@Column(name="author")
	private String author;

	@Column(name="genre")
	private String genre;

	@Column(name="year")
	private int year;

	@Column(name="publisher")
	private String publisher;

	@Column(name="book_image")
	private String bookImage;
	
	@Column(name="sale_is_active")
	private boolean saleIsActive;
	@Column(name="featured")
	private boolean featured;
	@Column(name="book_price")
	private float bookPrice;
	@Column(name="sale_discount_rate")
	private float saleDiscountRate;
	@Column(name="quantity_on_hand")
	private int quantityOnHand;
	
	public Book(int bookId) {
		super();
		this.bookId = bookId;
	}
	public Book() {
		bookId = 0;
		ISBN = " ";
		bookName = " ";
		synopsis = "";
		author = "";
		genre = "unknown";
		year = 0;
		publisher = "";
		bookImage = "";
		saleIsActive=false;
		featured=false;
		bookPrice=0.0f;
		saleDiscountRate=0.0f;
		quantityOnHand=0;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getBookImage() {
		return bookImage;
	}


	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}


	public boolean isSaleIsActive() {
		return saleIsActive;
	}


	public void setSaleIsActive(boolean saleIsActive) {
		this.saleIsActive = saleIsActive;
	}


	public boolean isFeatured() {
		return featured;
	}


	public void setFeatured(boolean featured) {
		this.featured = featured;
	}


	public float getBookPrice() {
		return bookPrice;
	}


	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}


	public float getSaleDiscountRate() {
		return saleDiscountRate;
	}


	public void setSaleDiscountRate(float saleDiscountRate) {
		this.saleDiscountRate = saleDiscountRate;
	}


	public float getQuantityOnHand() {
		return quantityOnHand;
	}


	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ISBN, author, bookId, bookImage, bookName, bookPrice, featured, genre, publisher,
				quantityOnHand, saleDiscountRate, saleIsActive, synopsis, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(ISBN, other.ISBN) && Objects.equals(author, other.author) && bookId == other.bookId
				&& Objects.equals(bookImage, other.bookImage) && Objects.equals(bookName, other.bookName)
				&& Float.floatToIntBits(bookPrice) == Float.floatToIntBits(other.bookPrice)
				&& featured == other.featured && Objects.equals(genre, other.genre)
				&& Objects.equals(publisher, other.publisher)
				&& Float.floatToIntBits(quantityOnHand) == Float.floatToIntBits(other.quantityOnHand)
				&& Float.floatToIntBits(saleDiscountRate) == Float.floatToIntBits(other.saleDiscountRate)
				&& saleIsActive == other.saleIsActive && Objects.equals(synopsis, other.synopsis) && year == other.year;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", ISBN=" + ISBN + ", bookName=" + bookName + ", synopsis=" + synopsis
				+ ", author=" + author + ", genre=" + genre + ", year=" + year + ", publisher=" + publisher
				+ ", bookImage=" + bookImage + ", saleIsActive=" + saleIsActive + ", featured=" + featured
				+ ", bookPrice=" + bookPrice + ", saleDiscountRate=" + saleDiscountRate + ", quantityOnHand="
				+ quantityOnHand + "]";
	}

	
	
}


	