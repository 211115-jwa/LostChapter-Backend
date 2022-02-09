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
public class CreditCardInfo {
	
	@Id
	@Column(name="cc_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccInfoId;
	private String nameOnCard;
	private long cardNumber;
	private int cvv;
	private int expirationMonth;
	private int expirationYear;
	private int billingZip;
	private String creditCardType;
	
	@OneToOne
	@JoinColumn(name="shipping_info_id")
	private ShippingInformation shippingInfo;
	
	
	public CreditCardInfo() {
		this.ccInfoId = 0;
		this.nameOnCard = "Jane Doe";
		this.cardNumber = 0000000000000000;
		this.cvv = 123;
		this.expirationMonth = 01;
		this.expirationYear = 25;
		this.billingZip = 12345;
		this.creditCardType = "visa";
		this.shippingInfo = new ShippingInformation();
	}


	public int getCcInfoId() {
		return ccInfoId;
	}


	public void setCcInfoId(int ccInfoId) {
		this.ccInfoId = ccInfoId;
	}


	public String getNameOnCard() {
		return nameOnCard;
	}


	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}


	public long getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}


	public int getCvv() {
		return cvv;
	}


	public void setCvv(int cvv) {
		this.cvv = cvv;
	}


	public int getExpirationMonth() {
		return expirationMonth;
	}


	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}


	public int getExpirationYear() {
		return expirationYear;
	}


	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}


	public int getBillingZip() {
		return billingZip;
	}


	public void setBillingZip(int billingZip) {
		this.billingZip = billingZip;
	}


	public String getCreditCardType() {
		return creditCardType;
	}


	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}


	public ShippingInformation getShippingInfo() {
		return shippingInfo;
	}


	public void setShippingInfo(ShippingInformation shippingInfo) {
		this.shippingInfo = shippingInfo;
	}


	@Override
	public String toString() {
		return "CreditCardInfo [ccInfoId=" + ccInfoId + ", nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber
				+ ", cvv=" + cvv + ", expirationMonth=" + expirationMonth + ", expirationYear=" + expirationYear
				+ ", billingZip=" + billingZip + ", creditCardType=" + creditCardType + ", shippingInfo=" + shippingInfo
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(billingZip, cardNumber, ccInfoId, creditCardType, cvv, expirationMonth, expirationYear,
				nameOnCard, shippingInfo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCardInfo other = (CreditCardInfo) obj;
		return billingZip == other.billingZip && cardNumber == other.cardNumber && ccInfoId == other.ccInfoId
				&& Objects.equals(creditCardType, other.creditCardType) && cvv == other.cvv
				&& expirationMonth == other.expirationMonth && expirationYear == other.expirationYear
				&& Objects.equals(nameOnCard, other.nameOnCard) && Objects.equals(shippingInfo, other.shippingInfo);
	}
	

	

}