package com.revature.lostchapterbackend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.OrderDoesNotExist;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Order;

import com.revature.lostchapterbackend.service.OrderService;



@RestController
@RequestMapping(path="/order")
@CrossOrigin("*")
public class OrderController {
	//This controller is used for the following
		//updateOrder PUT /update
		//addBookToOrder POST /add
		//getOrderByUser GET user/{userId}
		//getOrderById GET /{orderId}
		//deletePurchase DELETE /{orderId}

	private static OrderService orderServ;
	public OrderController() {
		super();
	}
	
	@Autowired
	public OrderController(OrderService orderServ) {
		this.orderServ=orderServ;
	}
	/*needs update*/
	@PutMapping(path = "/update") 
	public ResponseEntity<Object> updateOrder(@RequestBody Order newOrder) throws OrderDoesNotExist{
		//This methods responsibility it to add a new book to the Order collection if it doesnt exist already or to increase the quantity if it is present
		//This method uses the userID in order to find the users Order collection
		if (newOrder !=null) {
			orderServ.updateOrderBy(newOrder);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/add") 
	public ResponseEntity<Object> addBookToOrder(@RequestBody Order ccAdd){
		
		if (ccAdd !=null) {
			orderServ.addOrder(ccAdd);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@GetMapping(path = "user/{userId}") 
	public ResponseEntity<Object> getOrderByUser(@PathVariable int userId)throws UserNotFoundException{
		//gets the purchase by its PurchaseId
		
		List<Order> orders = orderServ.getAllOrdersByUser(userId);	
		if (orders != null)
			return ResponseEntity.ok(orders);
		else
			return ResponseEntity.notFound().build();
	}
	@GetMapping(path = "/{orderId}") 
	public ResponseEntity<Object> getOrderById(@PathVariable int orderId) throws OrderDoesNotExist{
		//gets the purchase by its PurchaseId
		
		Optional<Order>  cc = orderServ.getOrderById(orderId);	
		if (cc != null)
			return ResponseEntity.ok(cc);
		else
			return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping(path = "/{orderId}")
	public ResponseEntity<Void> deletePurchase(@RequestBody Order ccToDelete){
		//deletes the purchase by its PurchaseId
		if (ccToDelete !=null) {
			orderServ.deleteOrder(ccToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
