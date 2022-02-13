package com.revature.lostchapterbackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.ShippingInformation;
import com.revature.lostchapterbackend.service.BookService;

import com.revature.lostchapterbackend.service.ShippingService;


@RestController
@RequestMapping(path="/shipping")
@CrossOrigin("*")
public class ShippingController {

	private static ShippingService shippingServ;
	public ShippingController() {
		super();
	}
	
	@Autowired
	public ShippingController(ShippingService shippingServ) {
		this.shippingServ=shippingServ;
	}
	/*Update existing ship info*/
	@PutMapping(path = "/update") 
	public ResponseEntity<Object> updateshipping(@RequestBody ShippingInformation newshipping){
		//This methods responsibility it to add a new book to the shipping collection if it doesnt exist already or to increase the quantity if it is present
		//This method uses the userID in shipping to find the users shipping collection
		if (newshipping !=null) {
			shippingServ.upDateShippingInformation(newshipping);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	/*add ship info*/
	@PostMapping(path = "/add") 
	public ResponseEntity<Object> addBookToshipping(@RequestBody ShippingInformation shipAdd){
		
		if (shipAdd !=null) {
			shippingServ.addShippingInformation(shipAdd);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	/*get all users ship info*/
	@GetMapping(path = "user/{userId}") 
	public ResponseEntity<Object> getshippingByUser(@PathVariable int userId)throws UserNotFoundException{
		//gets the purchase by its PurchaseId
		
		List<ShippingInformation> shippings = shippingServ.getShippingInformationByUser(userId);	
		if (shippings != null)
			return ResponseEntity.ok(shippings);
		else
			return ResponseEntity.notFound().build();
	}
	/*Get ship info by id*/
	@GetMapping(path = "/{shippingId}") 
	public ResponseEntity<Object> getshippingById(@PathVariable int shippingId){
		//gets the purchase by its PurchaseId
		
		ShippingInformation ship = shippingServ.getShippingInformationById(shippingId);	
		if (ship != null)
			return ResponseEntity.ok(ship);
		else
			return ResponseEntity.notFound().build();
	}
	/*delete existing ship info*/
	
	@DeleteMapping(path = "/{shippingId}")
	public ResponseEntity<Void> deletePurchase(@RequestBody ShippingInformation shipToDelete){
		//deletes the purchase by its PurchaseId
		if (shipToDelete !=null) {
			shippingServ.deleteShipping(shipToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}