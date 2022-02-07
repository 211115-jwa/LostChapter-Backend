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

	@ManyToOne
	@JoinColumn(name="genre_id")
	private Genre genre;

	@Column(name="year")
	private int year;

	@Column(name="publisher")
	private String publisher;

	@Column(name="book_image")
	private String bookImage;

	public Book() {
		bookId = 0;
		ISBN = " ";
		bookName = " ";
		synopsis = "";
		author = "";
		genre = new Genre();
		year = 0;
		publisher = "";
		bookImage = "";
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

	public Genre getGenreId() {
		return genre;
	}

	public void setGenre(Genre genre) {
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

	@Override
	public int hashCode() {
		return Objects.hash(ISBN, author, bookId, bookImage, bookName, genre, publisher, synopsis, year);
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
				&& genre == other.genre && Objects.equals(publisher, other.publisher)
				&& Objects.equals(synopsis, other.synopsis) && year == other.year;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", ISBN=" + ISBN + ", bookName=" + bookName + ", synopsis=" + synopsis
				+ ", author=" + author + ", genreId=" + genre + ", year=" + year + ", publisher=" + publisher
				+ ", bookImage=" + bookImage + "]";
	}
	
}


	