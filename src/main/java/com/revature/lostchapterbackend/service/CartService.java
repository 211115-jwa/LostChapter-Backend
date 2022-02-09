package com.revature.lostchapterbackend.service;

import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Cart;
/*Front End Implementations notes
 * If the user is not logged in they will not be able to use the cart feature.
 * However, to get around this we can add a new cart to the table without a user. 
 * the cart id must be stored in the front end and compared as a global variable whenever doing login checks
 * The cart id essentially becomes the identifier of the non-logged in person
 * when the person logs in the cart should compared to the current cart then combined 
 * Last delete the cart that was used when user was not logged in
 * Same goes for registering
 * But instead of deleting the cart if they decide to register we set the current carts' user to the newly created user 
 * We should set a timer and if the timer runs out when the user is not logged in then the cart gets deleted
 * */


/* To do :
 *  Verify there is inventory of product when adding to cart.
 *  When checking out verifying once again there is enough inventory as some may have checkedout before
 * */

@Service
public interface CartService {
	
	public Cart createCart(Cart newCart);	
	
/* Returns cart by id using spring boot repo*/
public Cart getCartById(int Id);

/*deletes cart*/
public void deleteCart(Cart cartToDelete);

/*Using the login token we find the user's cart
 *Then we add the book to the list of books in cart
 *Spring boot adds a new entry to the booktobuy table
 */
public void addBooksToCart(Book newbook, int userId);

/*Using the login token we find the user's cart
 * Then we check the carts list for the book
 * if book is found we return true else false
 * */
public boolean checkBookInTheCart(Book book, int userId);

/*Using the login token we find the user's cart
 *Then we delete the book from the list 
 *Spring cascades to delete the booktobuy entry
 */
public Cart deleteBookInCart(Book book, int userId) ;

/*Using the login token we find the user's cart
 *Then we delete the book from the list 
 *Spring cascades to delete the booktobuy entry
 */
public Cart deleteAllBooksInCart(int userId);

/*If no user is logged in methods 
 * */
public void addBooksToCartNoUser(Book newbook, Cart currentCart);

/*we check the carts list for the book
 * if book is found we return true else false
 * */
public boolean checkBookInTheCartNoUser(Book book, Cart currentCart);

/*we delete the book from the list 
 *Spring cascades to delete the booktobuy entry
 */
public Cart deleteBookInCartNoUser(Book book, Cart currentCart) ;

/* we delete the book from the list 
 *Spring cascades to delete the booktobuy entry
 */
public Cart deleteAllBooksInCartNoUser(Cart currentCart);
/*
 * increments book quantity 
 * */
public void incrementQuantity(Book book, int userId);

/*
 *  increments book quantity 
 * */
public void incrementQuantityNoUser(Book book, Cart currentCart);

/*
 * decreases quantity
 * */
public void decreaseQuantity(Book book, int userId);

/*
 * decreases quantity
 * */
public void decreaseQuantityNoUser(Book book, Cart currentCart);



}
