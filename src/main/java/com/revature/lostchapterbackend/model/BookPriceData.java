package com.revature.lostchapterbackend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class BookPriceData {

	@Id
	@Column(name="book_price_data_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookPriceDataId;
	private boolean saleIsActive;
	private double saleDiscountRate;
	private double bookPrice;
	private int quantityOnHand;
	private boolean featured;
	
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;

	public BookPriceData() {
		super();
		this.bookPriceDataId = 0;
		this.saleIsActive = false;
		this.saleDiscountRate = 0.0;
		this.bookPrice = 10.0;
		this.quantityOnHand = 1;
		this.featured = false;
		this.book = new Book();
	}

	public int getBookPriceDataId() {
		return bookPriceDataId;
	}

	public void setBookPriceDataId(int bookPriceDataId) {
		this.bookPriceDataId = bookPriceDataId;
	}

	public boolean isSaleIsActive() {
		return saleIsActive;
	}

	public void setSaleIsActive(boolean saleIsActive) {
		this.saleIsActive = saleIsActive;
	}

	public double getSaleDiscountRate() {
		return saleDiscountRate;
	}

	public void setSaleDiscountRate(double saleDiscountRate) {
		this.saleDiscountRate = saleDiscountRate;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getQuantityOnHand() {
		return quantityOnHand;
	}

	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookPriceData [bookPriceDataId=" + bookPriceDataId + ", saleIsActive=" + saleIsActive
				+ ", saleDiscountRate=" + saleDiscountRate + ", bookPrice=" + bookPrice + ", quantityOnHand="
				+ quantityOnHand + ", featured=" + featured + ", book=" + book + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, bookPrice, bookPriceDataId, featured, quantityOnHand, saleDiscountRate, saleIsActive);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookPriceData other = (BookPriceData) obj;
		return Objects.equals(book, other.book)
				&& Double.doubleToLongBits(bookPrice) == Double.doubleToLongBits(other.bookPrice)
				&& bookPriceDataId == other.bookPriceDataId && featured == other.featured
				&& quantityOnHand == other.quantityOnHand
				&& Double.doubleToLongBits(saleDiscountRate) == Double.doubleToLongBits(other.saleDiscountRate)
				&& saleIsActive == other.saleIsActive;
	}

	
	
}



