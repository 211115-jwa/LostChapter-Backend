package com.revature.lostchapterbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.TransactionNotFound;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.model.Transaction;
import com.revature.lostchapterbackend.service.CartService;
import com.revature.lostchapterbackend.service.TransactionService;

@RestController
@RequestMapping(path="/transactions")
@CrossOrigin(origins="http://localhost:4200/")
public class TransactionController {

	private static TransactionService transService;
	private static CartService cartService;
	
	public TransactionController () {super();}
	
	@Autowired
	public TransactionController(TransactionService transService, CartService cartServ) {
		this.transService=transService;
		this.cartService=cartService;
	}
	
	@GetMapping(path="/{currentOrderId}")
	public ResponseEntity<Cart> getPurchasedCart(@PathVariable int currentOrderId) throws OrderDoesNotExist{
		Cart cart = transService.getPurchasedCart(currentOrderId);
		if(cart != null) {
			return ResponseEntity.ok(cart);
		}
		else
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(path="/{transactionId}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int transactionId) throws TransactionNotFound {
		Transaction trans = transService.getTransactionById(transactionId);
		if(trans != null) {
			return ResponseEntity.ok(trans);
		}
		else
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(path="/{userId}")
	public ResponseEntity<List<Transaction>> getTransactionByUser(@PathVariable int userId) throws UserNotFoundException{
		List<Transaction> trans = transService.getTransactionByUser(userId);
		if(trans != null) {
			return ResponseEntity.ok(trans);
		}
		else
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(path="/{orderId}")
	public ResponseEntity<Transaction> getTransactionByOrderId(@PathVariable int orderId) throws OrderDoesNotExist {
		Transaction trans = transService.getTransactionByOrderId(orderId);
		if(trans != null) {
			return ResponseEntity.ok(trans);
		}
		else
		return ResponseEntity.notFound().build();
		
	}
	
}
