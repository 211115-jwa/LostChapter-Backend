package com.revature.lostchapterbackend.model;

import java.time.LocalDateTime;
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
@Table(name="transaction_keeper")
public class Transaction {

	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	private double totalPrice;
	private LocalDateTime transactionDate;
	
	public Transaction() {
		super();
		this.transactionId = 0;
		this.order = new Order();
		this.totalPrice = 0.0;
		this.transactionDate = null;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", order=" + order + ", totalPrice=" + totalPrice
				+ ", transactionDate=" + transactionDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, totalPrice, transactionDate, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(order, other.order)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& Objects.equals(transactionDate, other.transactionDate) && transactionId == other.transactionId;
	}


	
}
