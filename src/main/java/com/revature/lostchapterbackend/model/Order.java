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
@Table(name="ordr")
public class Order {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@OneToOne
	@JoinColumn(name="cc_info_id")
	private CreditCardInfo creditCard;
	
	@OneToOne
	@JoinColumn(name="cart_id")
	private Cart cart;

	public Order() {

		this.orderId = 0;
		this.creditCard = new CreditCardInfo();
		this.cart = new Cart();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public CreditCardInfo getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardInfo creditCard) {
		this.creditCard = creditCard;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", creditCard=" + creditCard + ", cart=" + cart + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cart, creditCard, orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(creditCard, other.creditCard)
				&& orderId == other.orderId;
	}


	


}
