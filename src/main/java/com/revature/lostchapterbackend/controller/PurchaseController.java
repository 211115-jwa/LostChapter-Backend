package com.revature.lostchapterbackend.controller;
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
import com.revature.lostchapterbackend.model.Purchase;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.PurchaseService;

@RestController
@RequestMapping(path="/Purchase")
@CrossOrigin("*")
public class PurchaseController {

	
	//static for testing
	private static PurchaseService purchaseServ;
	
	
	public PurchaseController() {
		super();
		}
	//field injection
	@Autowired
	public PurchaseController(PurchaseService purchaseServ) {
		this.purchaseServ= purchaseServ;
	}
	
	@PostMapping
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase newPurchase)  {
		
		if (newPurchase !=null) {
			purchaseServ.createPurchase(newPurchase);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}

//	@PostMapping(path = "/add/{userId}") 
//	public ResponseEntity<Object> addBookToPurchase(@RequestBody Book bookId, @PathVariable(value="userId") int userId){
//		if (bookId !=null&&userId!=0) {
//			if(PurchaseServ.checkBookInThePurchase(bookId, userId)) {
//				PurchaseServ.incrementQuantity(bookId, userId);
//			}
//			else {
//			PurchaseServ.addBooksToPurchase(bookId, userId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			}
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	
//	@PutMapping(path = "/add/{bookToBuyId}/{PurchaseId}") 
//	public ResponseEntity<Object> addBookToPurchaseNoUser(@RequestBody Book bookToAdd, @PathVariable(value="PurchaseId") int PurchaseId){
//		if (bookToAdd !=null) {
//			PurchaseServ.addBooksToPurchaseNoUser(bookToAdd, PurchaseServ.getPurchaseById(PurchaseId));
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	@PostMapping(path = "/delete/{bookToBuyId}/{userId}") 
//	public ResponseEntity<Object> deleteBookToPurchase(@RequestBody Book bookToDelete, @PathVariable int userId){
//		if (bookToDelete !=null&&userId!=0) {
//			PurchaseServ.deleteBookInPurchase(bookToDelete, userId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
	
//	@PostMapping(path = "/delete/{bookToBuyId}/{PurchaseId}") 
//	public ResponseEntity<Object> deleteBookToPurchaseNoUser(@RequestBody Book bookToAdd, @PathVariable int PurchaseId){
//		if (bookToAdd !=null) {
//			PurchaseServ.deleteBookInPurchaseNoUser(bookToAdd, PurchaseServ.getPurchaseById(PurchaseId));
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
//	@PostMapping(path = "/add/quantity/{bookToBuyId}/{userId}") 
//	public ResponseEntity<Object> addQuantity(@RequestBody Book bookToAdd, @PathVariable int userId){
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
//		if (bookToAdd !=null) {
//			
//			PurchaseServ.decreaseQuantity(bookToAdd, PurchaseId);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//			
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//	}
	
	@GetMapping(path = "/{PurchaseId}") 
	public ResponseEntity<Object> getPurchaseById(@PathVariable int PurchaseId) {
		Purchase Purchase = purchaseServ.getPurchaseById(PurchaseId);
		if (Purchase != null)
			return ResponseEntity.ok(Purchase);
		else
			return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping(path = "/{PurchaseId}")
	public ResponseEntity<Void> deletePurchase(@RequestBody Purchase PurchaseToDelete){
		if (PurchaseToDelete !=null) {
			purchaseServ.deletePurchase(PurchaseToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
}
