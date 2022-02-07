package com.revature.lostchapterbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.TransactionNotFound;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.model.Transaction;


@Service
public interface TransactionService {
	public Cart getPurchacedCart(int currentOrderId) throws OrderDoesNotExist;
	public Transaction getTransactionById(int transactionId) throws TransactionNotFound;
	public List<Transaction> getTransactionByUser(int userId) throws UserNotFoundException;
	public Transaction getTransactionByOrderId(int orderId) throws OrderDoesNotExist;
	 
}
