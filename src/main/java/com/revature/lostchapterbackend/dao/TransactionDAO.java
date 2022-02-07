package com.revature.lostchapterbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.model.Transaction;
@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>{

	public Cart findByOrderCart(int orderId);
	public List<Transaction> findByOrderCartUser(int userId);
	public Transaction findByOrder(int orderId);
}
