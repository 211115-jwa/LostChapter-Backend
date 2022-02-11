package com.revature.lostchapterbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.exceptions.CartNotFoundException;
import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Order;

@Service
public interface OrderService {
	
	public Order getOrderById(int orderId);
	public List<Order> getAllOrdersByUser(int userId) throws UserNotFoundException;
	public int addOrder(Order newOrder);
//	public Order getOrderByBook(Book book);  stretch goal

}
