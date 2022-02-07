package com.revature.lostchapterbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.Order;
@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{

	public Order findBycart(int cartId);
	public List<Order> findByCartUser(int userId);

}
