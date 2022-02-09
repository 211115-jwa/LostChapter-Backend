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

import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Cart;




import com.revature.lostchapterbackend.service.CartService;



@RestController
@RequestMapping(path="/cart")
@CrossOrigin("*")
public class CartController {

	//static for testing
	private static CartService cartServ;
	
	
	public CartController() {
		super();
		}
	//field injection
	@Autowired
	public CartController(CartService cartServ) {
		this.cartServ= cartServ;
	}
	
	

	@PostMapping(path = "/add/{userId}") 
	public ResponseEntity<Object> addBookToCart(@RequestBody Book bookId, @PathVariable(value="userId") int userId){
		if (bookId !=null&&userId!=0) {
			if(cartServ.checkBookInTheCart(bookId, userId)) {
				cartServ.incrementQuantity(bookId, userId);
			}
			else {
			cartServ.addBooksToCart(bookId, userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(path = "/add/{bookToBuyId}/{cartId}") 
	public ResponseEntity<Object> addBookToCartNoUser(@RequestBody Book bookToAdd, @PathVariable(value="cartId") int cartId){
		if (bookToAdd !=null) {
			cartServ.addBooksToCartNoUser(bookToAdd, cartServ.getCartById(cartId));
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PostMapping(path = "/delete/{bookToBuyId}/{userId}") 
	public ResponseEntity<Object> deleteBookToCart(@RequestBody Book bookToDelete, @PathVariable int userId){
		if (bookToDelete !=null&&userId!=0) {
			cartServ.deleteBookInCart(bookToDelete, userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/delete/{bookToBuyId}/{cartId}") 
	public ResponseEntity<Object> deleteBookToCartNoUser(@RequestBody Book bookToAdd, @PathVariable int cartId){
		if (bookToAdd !=null) {
			cartServ.deleteBookInCartNoUser(bookToAdd, cartServ.getCartById(cartId));
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PostMapping(path = "/add/quantity/{bookToBuyId}/{userId}") 
	public ResponseEntity<Object> addQuantity(@RequestBody Book bookToAdd, @PathVariable int userId){
		if (bookToAdd !=null&&userId!=0) {
			
			cartServ.incrementQuantity(bookToAdd, userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/add/quantity/{bookToBuyId}/{cartId}") 
	public ResponseEntity<Object> addQuantityNoUser(@RequestBody Book bookToAdd, @PathVariable int cartId){
		if (bookToAdd !=null) {
			
			cartServ.incrementQuantity(bookToAdd, cartId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/decrease/quantity/{bookToBuyId}/{userId}") 
	public ResponseEntity<Object> decreaseQuantity(@RequestBody Book bookToAdd, @PathVariable int userId){
		if (bookToAdd !=null&&userId!=0) {
			
			cartServ.decreaseQuantity(bookToAdd, userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping(path = "/decrease/quantity/{bookToBuyId}/{cartId}") 
	public ResponseEntity<Object> decreaseQuantityNoUser(@RequestBody Book bookToAdd, @PathVariable int cartId){
		if (bookToAdd !=null) {
			
			cartServ.decreaseQuantity(bookToAdd, cartId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping(path = "/{cartId}") 
	public ResponseEntity<Object> getCartById(@PathVariable int cartId) {
		Cart cart = cartServ.getCartById(cartId);
		if (cart != null)
			return ResponseEntity.ok(cart);
		else
			return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping(path = "/{cartId}")
	public ResponseEntity<Void> deleteCart(@RequestBody Cart cartToDelete){
		if (cartToDelete !=null) {
			cartServ.deleteCart(cartToDelete);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
}
