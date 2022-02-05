package com.revature.lostchapterbackend.controller;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.annotation.Customer;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.model.Checkout;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.CartService;
import com.revature.lostchapterbackend.service.TransactionKeeperService;
import com.revature.lostchapterbackend.utility.ValidateCheckoutUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class CheckoutController {

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private ValidateCheckoutUtil validateCheckoutUtil;

	@Autowired
	private TransactionKeeperService cs;

	@Autowired
	private CartService css;

	@Customer
	@PostMapping(path = "/user/checkout")
	public ResponseEntity<Object> payout(@RequestBody Checkout payout) {

		try {
			Checkout tk;
			// main way to get cartId
			User currentlyLoggedInUser = (User) req.getSession().getAttribute("currentUser");

			Cart c = css.getCartById(String.valueOf(currentlyLoggedInUser.getUserId()));
			validateCheckoutUtil.verifyCheckout(payout); // validates card information

			// removes all the spaces in the card number when user inputs spaces
			payout.setOrderNumber(payout.getOrderNumber().trim().replaceAll("\\s+", ""));

			// checks if card already exists
			Checkout getCardNumber = cs.findByCardNumber(payout.getOrderNumber());
			// if it doesn't ...
			if (getCardNumber == null) {
				payout.setCardBalance(10000);
				tk = cs.confirmCheckout(c, payout);
				return ResponseEntity.status(200).body(tk);
			} else {
				// if it exists...
				validateCheckoutUtil.verifyCardInfo(getCardNumber, payout);
				getCardNumber.setShippingAddress(payout.getShippingAddress());
				cs.saveCard(getCardNumber);
				tk = cs.confirmCheckout(c, getCardNumber);
				return ResponseEntity.status(200).body(tk);
			}

		} catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}

	}
	
	@Customer
	@GetMapping(path = "/order-confirmation/{transactionId}")
	public ResponseEntity<Object> getTransactionId(@PathVariable("transactionId") String transacId) {
		
		try {
			Checkout transaction = cs.getTransactionById(transacId);		
			return ResponseEntity.status(200).body(transaction);
		} catch (InvalidParameterException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
}
