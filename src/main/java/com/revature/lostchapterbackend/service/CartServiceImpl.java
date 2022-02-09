package com.revature.lostchapterbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.lostchapterbackend.dao.BookToBuyDAO;
import com.revature.lostchapterbackend.dao.CartDAO;

import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.BookToBuy;
import com.revature.lostchapterbackend.model.Cart;
@Service
public class CartServiceImpl implements CartService{

	private CartDAO cartDao;
	private BookToBuyDAO bookToBuyDao;
	@Autowired
	public  CartServiceImpl(CartDAO cartDao,BookToBuyDAO bookToBuyDao) {
		this.cartDao=cartDao;
		this.bookToBuyDao=bookToBuyDao;
	}
	
	@Override
	@Transactional
	public void deleteCart(Cart cartToDelete) {
		cartDao.delete(cartToDelete);
		}

	@Override
	@Transactional
	public Cart getCartById(int id) {
		Optional<Cart> cart = cartDao.findById(id);
		if (cart.isPresent())
			return cart.get();
		else return null;
	}

	@Override
	@Transactional
	public void addBooksToCart(Book newbook, int userId) {
		Cart currentCart= cartDao.findByuser(userId);
		BookToBuy newBookToBuy = new BookToBuy();
		newBookToBuy.setBook(newbook);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		booksInCart.add(newBookToBuy);
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
	}

	@Override
	@Transactional
	public boolean checkBookInTheCart(Book book, int userId) {
		Cart currentCart= cartDao.findByuser(userId);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		Boolean bookExists=false;
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				 bookExists=true;
				}
		}
		return bookExists;
	}

	@Override
	@Transactional
	public Cart deleteBookInCart(Book book, int userId){
		Cart currentCart= cartDao.findByuser(userId);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		BookToBuy rmbookToBuy= new BookToBuy(); 
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				rmbookToBuy=booksInCart.get(i);
			}
		}
		
		booksInCart.remove(rmbookToBuy);
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
		return currentCart;
	}

	@Override
	@Transactional
	public Cart deleteAllBooksInCart(int userId) {
		Cart currentCart= cartDao.findByuser(userId);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		booksInCart.clear();
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
		return currentCart;
	}

	@Override
	@Transactional
	public void addBooksToCartNoUser(Book newbook, Cart currentCart) {
		BookToBuy newBookToBuy = new BookToBuy();
		newBookToBuy.setBook(newbook);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		booksInCart.add(newBookToBuy);
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
	}

	@Override
	@Transactional
	public boolean checkBookInTheCartNoUser(Book book, Cart currentCart) {
	
		List<BookToBuy> booksInCart= currentCart.getBooks();
		for(BookToBuy b:booksInCart) {
			if(book.equals(b))
				return true;
		}
		return false;
	}

	@Override
	@Transactional
	public Cart deleteBookInCartNoUser(Book book, Cart currentCart)  {
		
		
		List<BookToBuy> booksInCart= currentCart.getBooks();
		BookToBuy rmbookToBuy= new BookToBuy(); 
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				rmbookToBuy=booksInCart.get(i);
			}
		}
		
		booksInCart.remove(rmbookToBuy);
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
		return currentCart;
	}

	@Override
	@Transactional
	public Cart deleteAllBooksInCartNoUser(Cart currentCart) {
		List<BookToBuy> booksInCart= currentCart.getBooks();
		booksInCart.clear();
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
		return currentCart;
	}

	@Override
	@Transactional
	public void incrementQuantity(Book book, int userId) {
		Cart currentCart= cartDao.findByuser(userId);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				booksInCart.get(i).setQuantityToBuy(booksInCart.get(i).getQuantityToBuy()+1);
			}
		}
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
	}

	@Override
	@Transactional
	public void incrementQuantityNoUser(Book book, Cart currentCart) {
		List<BookToBuy> booksInCart= currentCart.getBooks();
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				booksInCart.get(i).setQuantityToBuy(booksInCart.get(i).getQuantityToBuy()+1);
			}
		}
		currentCart.setBooks(booksInCart);
		cartDao.save(currentCart);
		
	}

	@Override
	@Transactional
	public void decreaseQuantity(Book book, int userId) {
		Cart currentCart= cartDao.findByuser(userId);
		List<BookToBuy> booksInCart= currentCart.getBooks();
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				if(booksInCart.get(i).getQuantityToBuy()>1)
				{
				booksInCart.get(i).setQuantityToBuy(booksInCart.get(i).getQuantityToBuy()-1);
				currentCart.setBooks(booksInCart);
				cartDao.save(currentCart);
				}
				else
				{
					booksInCart.remove(booksInCart.get(i));
					currentCart.setBooks(booksInCart);
					cartDao.save(currentCart);
				}
			}
		}
		
	}

	@Override
	@Transactional
	public Cart createCart(Cart newCart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void decreaseQuantityNoUser(Book book, Cart currentCart) {
		List<BookToBuy> booksInCart= currentCart.getBooks();
		for(int i=0;i<booksInCart.size();i++) {
			if(booksInCart.get(i).getBook().getBookId()==book.getBookId()) {
				if(booksInCart.get(i).getQuantityToBuy()>1)
				{
				booksInCart.get(i).setQuantityToBuy(booksInCart.get(i).getQuantityToBuy()-1);
				currentCart.setBooks(booksInCart);
				cartDao.save(currentCart);
				}
				else
				{
					booksInCart.remove(booksInCart.get(i));
					currentCart.setBooks(booksInCart);
					cartDao.save(currentCart);
				}
			}
		}
		
	}




	
	
}
