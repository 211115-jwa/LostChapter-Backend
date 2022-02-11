package com.revature.lostchapterbackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.service.BookService;
import com.revature.lostchapterbackend.service.OrderService;


@RestController
@RequestMapping(path="/order")
@CrossOrigin("*")
public class OrderController {

	private static OrderService orderServ;
	public OrderController() {
		super();
	}
	
	@Autowired
	public OrderController(OrderService orderServ) {
		this.orderServ=orderServ;
	}
}
