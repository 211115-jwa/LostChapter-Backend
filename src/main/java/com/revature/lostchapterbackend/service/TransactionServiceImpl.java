package com.revature.lostchapterbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.lostchapterbackend.dao.TransactionDAO;
import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.TransactionNotFound;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.model.Transaction;

public class TransactionServiceImpl implements TransactionService {
	
	private TransactionDAO transactiondao;
	
	@Autowired
	public TransactionServiceImpl(TransactionDAO transactiondao) {
		this.transactiondao = transactiondao;
	}

	@Override
	@Transactional
	public Cart getPurchasedCart(int currentOrderId) throws OrderDoesNotExist{
		try
		{
			Cart theCart = transactiondao.findByOrderCart(currentOrderId);
			return theCart;
		}catch(Exception e)
		{
			throw new OrderDoesNotExist("Order Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public Transaction getTransactionById(int transactionId) throws TransactionNotFound{
		try
		{
			Transaction transaction = transactiondao.getById(transactionId);
			return transaction;
		}catch(Exception e)
		{
			throw new TransactionNotFound("Transaction Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public List<Transaction> getTransactionByUser(int userId) throws UserNotFoundException{

		try
		{
			List<Transaction> transactions = transactiondao.findByOrderCartUser(userId);
			return transactions;
		}catch(Exception e)
		{
			throw new  UserNotFoundException("User Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public Transaction getTransactionByOrderId(int orderId) throws OrderDoesNotExist{
		try
		{
			Transaction transaction = transactiondao.findByOrder(orderId);
			return transaction;
		}catch(Exception e)
		{
			throw new OrderDoesNotExist("Order Id Not Found, Try Again!");
		}
	}

}
