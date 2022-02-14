package com.revature.lostchapterbackend.controller;
import java.util.List;

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
import com.revature.lostchapterbackend.model.Order;
import com.revature.lostchapterbackend.model.Purchase;




import com.revature.lostchapterbackend.service.PurchaseService;

@RestController
@RequestMapping(path="/Purchase")
@CrossOrigin("*")
public class PurchaseController {
	//This controller is used for the following
		//add books to purchase database row POST /add/{userId}
		//add books to purchase PUT /add/{bookToBuyId}/{PurchaseId}
		//delete book by title from purchase by first finding the user by userId POST /delete/{bookToBuyId}/{userId}
		//delete book by title from purchase by first finding the purchase by purchaseID POST /delete/{bookToBuyId}/{PurchaseId}
		//increase quantity of books to purchase by first finding the user by userId POST /add/quantity/{bookToBuyId}/{userId}
		//increase quantity of books to purchase by first finding the purchase by purchaseID POST /add/quantity/{bookToBuyId}/{PurchaseId}
		//decrease quantity of books to purchase by first finding the user by userId POST POST /decrease/quantity/{bookToBuyId}/{userId}
		//decrease quantity of books to purchase by first finding the purchase by purchaseID POST /decrease/quantity/{bookToBuyId}/{PurchaseId}
		//get purchase by its id GET /{PurchaseId}
		//delete purchase by its id DELETE /{PurchaseId}
	private static PurchaseService PurchaseServ;
	
	
	public PurchaseController() {
		super();
		}
	//field injection
	@Autowired
	public PurchaseController(PurchaseService PurchaseServ) {
		this.PurchaseServ= PurchaseServ;
	}
	/*get all purchases by user*/
	@GetMapping(path = "user/{userId}") 
	public ResponseEntity<Object> getPurchaseByUser(@PathVariable int userId){
		//gets the purchase by its PurchaseId
		
		List<Purchase> purchase = PurchaseServ.getPurchaseByuserId(userId);	
		if (purchase != null)
			return ResponseEntity.ok(purchase);
		else
			return ResponseEntity.notFound().build();
	}
	/*update purchase */
	@PutMapping(path = "/adjust") 
	public ResponseEntity<Object> updatePurchase(@RequestBody Purchase newPurchase){
		//This methods responsibility it to add a new book to the purchase collection if it doesnt exist already or to increase the quantity if it is present
		//This method uses the userID in order to find the users Purchase collection
		if (newPurchase !=null&& newPurchase.getQuantityToBuy()!=0) {
			PurchaseServ.upDatePurchase(newPurchase);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		else if (newPurchase !=null&& newPurchase.getQuantityToBuy()==0) {
		PurchaseServ.deletePurchase(newPurchase);	
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	/*create new purchase*/
	@PostMapping(path = "/add") 
	public ResponseEntity<Object> addBookToPurchase(@RequestBody Purchase bookToAdd){
		
		if (bookToAdd !=null) {
			PurchaseServ.createPurchase(bookToAdd);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
//	@PostMapping(path = "/delete/{bookToBuyId}/{userId}") 
//	public ResponseEntity<Object> deleteBookToPurchase(@RequestBody Book bookToDelete, @PathVariable int userId){
		//This methods responsibility it to delete a new book from the purchase collection
		//This method uses the userId in order to find the users Purchase collection
//		if (bookToDelete !=null&&userId!=0) {
//			PurchaseServ.deleteBookInPurchase(bookToDelete, userId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
	
//	@PostMapping(path = "/delete/{bookToBuyId}/{PurchaseId}") 
//	public ResponseEntity<Object> deleteBookToPurchaseNoUser(@RequestBody Book bookToAdd, @PathVariable int PurchaseId){
		//This methods responsibility it to delete a new book from the purchase collection
		//This method uses the PurchaseId in order to find the users Purchase collection
//		if (bookToAdd !=null) {
//			PurchaseServ.deleteBookInPurchaseNoUser(bookToAdd, PurchaseServ.getPurchaseById(PurchaseId));
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	@PostMapping(path = "/add/quantity/{bookToBuyId}/{userId}") 
//	public ResponseEntity<Object> addQuantity(@RequestBody Book bookToAdd, @PathVariable int userId){
		//This methods responsibility increase the quantity of the book by 1
		//This method uses the userId in order to find the users Purchase collection
//		if (bookToAdd !=null&&userId!=0) {
//			
//			PurchaseServ.incrementQuantity(bookToAdd, userId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
	
//	@PostMapping(path = "/add/quantity/{bookToBuyId}/{PurchaseId}") 
//	public ResponseEntity<Object> addQuantityNoUser(@RequestBody Book bookToAdd, @PathVariable int PurchaseId){
		//This methods responsibility increase the quantity of the book by 1
		//This method uses the PurchaseId in order to find the users Purchase collection
//		if (bookToAdd !=null) {
//			
//			PurchaseServ.incrementQuantity(bookToAdd, PurchaseId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	
//	@PostMapping(path = "/decrease/quantity/{bookToBuyId}/{userId}") 
//	public ResponseEntity<Object> decreaseQuantity(@RequestBody Book bookToAdd, @PathVariable int userId){
		//This methods responsibility decrease the quantity of the book by 1
		//This method uses the userId in order to find the users Purchase collection
//		if (bookToAdd !=null&&userId!=0) {
//			
//			PurchaseServ.decreaseQuantity(bookToAdd, userId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	
//	@PostMapping(path = "/decrease/quantity/{bookToBuyId}/{PurchaseId}") 
//	public ResponseEntity<Object> decreaseQuantityNoUser(@RequestBody Book bookToAdd, @PathVariable int PurchaseId){
		//This methods responsibility decrease the quantity of the book by 1
		//This method uses the PurchaseId in order to find the users Purchase collection
//		if (bookToAdd !=null) {
//			
//			PurchaseServ.decreaseQuantity(bookToAdd, PurchaseId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
	/*get  purchase by id*/
	@GetMapping(path = "/{PurchaseId}") 
	public ResponseEntity<Object> getPurchaseById(@PathVariable int PurchaseId) {
		//gets the purchase by its PurchaseId
		Purchase Purchase = PurchaseServ.getPurchaseById(PurchaseId);
		if (Purchase != null)
			return ResponseEntity.ok(Purchase);
		else
			return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping(path = "/{PurchaseId}")
	public ResponseEntity<Void> deletePurchase(@RequestBody Purchase PurchaseToDelete){
		//deletes the purchase by its PurchaseId
		if (PurchaseToDelete !=null) {
			PurchaseServ.deletePurchase(PurchaseToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
}
