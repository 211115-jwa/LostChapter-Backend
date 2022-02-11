package com.revature.lostchapterbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.dao.OrderDAO;
import com.revature.lostchapterbackend.exceptions.CartNotFoundException;
import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderdao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}
	
	@Override
	@Transactional
	public Order getOrderById(int orderId){
			Optional<Order> order = orderdao.findById(orderId);
			if (order.isPresent())
				return order.get();
			else return null;
	}

	@Override
	@Transactional
	public List<Order> getAllOrdersByUser(int userId) throws UserNotFoundException{
		try
		{
			List<Order> orders = orderdao.findByUser(userId);
			return orders;
		}catch(Exception e)
		{
			throw new UserNotFoundException("User Id Not Found, Try Again!");
		}
	}

//	@Override
//	@Transactional
//	public Order getOrderByCartId(int cartId) throws CartNotFoundException{
//		try
//		{
//			Order order = orderdao.findBycart(cartId);
//			return order;
//		}catch(Exception e)
//		{
//			throw new CartNotFoundException("Cart Id Not Found, Try Again!");
//		}
//	}

	@Override
	@Transactional
	public int addOrder(Order newOrder) {
		Order order = orderdao.save(newOrder);
		if(order != null)
		return order.getOrderId();
		else return 0;
	}



}
