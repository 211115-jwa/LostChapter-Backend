package com.revature.lostchapterbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.lostchapterbackend.dao.OrderDAO;
import com.revature.lostchapterbackend.exceptions.CartNotFoundException;
import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Order;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderdao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}
	
	@Override
	@Transactional
	public Order getOrderById(int orderId) throws OrderDoesNotExist {
		try
		{
			Order order = orderdao.getById(orderId);
			return order;
		}catch(Exception e)
		{
			throw new OrderDoesNotExist("Order Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public List<Order> getAllOrdersByUser(int userId) throws UserNotFoundException{
		try
		{
			List<Order> orders = orderdao.findByCartUser(userId);
			return orders;
		}catch(Exception e)
		{
			throw new UserNotFoundException("User Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public Order getOrderByCartId(int cartId) throws CartNotFoundException{
		try
		{
			Order order = orderdao.findBycart(cartId);
			return order;
		}catch(Exception e)
		{
			throw new CartNotFoundException("Cart Id Not Found, Try Again!");
		}
	}

	@Override
	@Transactional
	public Order addOrder(Order order) {
		Order newOrder = orderdao.save(order);
		return newOrder;
	}



}
