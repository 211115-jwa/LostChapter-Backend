package com.revature.lostchapterbackend.model;

import java.util.Objects;

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
@Table(name="book_to_buy")
public class BookToBuy {

	@Id
	@Column(name="book_to_buy_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="quantity_to_buy")
	private int quantityToBuy;

	public BookToBuy() {
		this.bookId=0;
		this.book=null;
		this.quantityToBuy=1;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantityToBuy() {
		return quantityToBuy;
	}

	public void setQuantityToBuy(int quantityToBuy) {
		this.quantityToBuy = quantityToBuy;
	}

	@Override
	public String toString() {
		return "BookToBuy [bookId=" + bookId + ", book=" + book + ", quantityToBuy=" + quantityToBuy + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, bookId, quantityToBuy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookToBuy other = (BookToBuy) obj;
		return Objects.equals(book, other.book) && bookId == other.bookId && quantityToBuy == other.quantityToBuy;
	}

	

	
}
