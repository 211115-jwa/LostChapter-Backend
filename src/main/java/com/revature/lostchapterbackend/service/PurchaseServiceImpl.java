package com.revature.lostchapterbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.lostchapterbackend.dao.PurchaseDAO;
import com.revature.lostchapterbackend.model.Book;

import com.revature.lostchapterbackend.model.Purchase;
@Service
public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDAO PurchaseDao;
	@Autowired
	public  PurchaseServiceImpl(PurchaseDAO PurchaseDao) {
		this.PurchaseDao=PurchaseDao;
	}
	
	@Override
	@Transactional
	public void deletePurchase(Purchase PurchaseToDelete) {
		PurchaseDao.delete(PurchaseToDelete);
		}
	
	@Override
	@Transactional
	public Purchase getPurchaseById(int id) {
		Optional<Purchase> Purchase = PurchaseDao.findById(id);
		if (Purchase.isPresent())
			return Purchase.get();
		else return null;
	}
//
//	@Override
//	@Transactional
//	public void addBooksToPurchase(Book newbook, int userId) {
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		BookToBuy newBookToBuy = new BookToBuy();
//		newBookToBuy.setBook(newbook);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		booksInPurchase.add(newBookToBuy);
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//	}
//
//	@Override
//	@Transactional
//	public boolean checkBookInThePurchase(Book book, int userId) {
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		Boolean bookExists=false;
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				 bookExists=true;
//				}
//		}
//		return bookExists;
//	}
//
//	@Override
//	@Transactional
//	public Purchase deleteBookInPurchase(Book book, int userId){
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		BookToBuy rmbookToBuy= new BookToBuy(); 
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				rmbookToBuy=booksInPurchase.get(i);
//			}
//		}
//		
//		booksInPurchase.remove(rmbookToBuy);
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//		return currentPurchase;
//	}
//
//	@Override
//	@Transactional
//	public Purchase deleteAllBooksInPurchase(int userId) {
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		booksInPurchase.clear();
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//		return currentPurchase;
//	}
//
//	@Override
//	@Transactional
//	public void addBooksToPurchaseNoUser(Book newbook, Purchase currentPurchase) {
//		BookToBuy newBookToBuy = new BookToBuy();
//		newBookToBuy.setBook(newbook);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		booksInPurchase.add(newBookToBuy);
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//	}
//
//	@Override
//	@Transactional
//	public boolean checkBookInThePurchaseNoUser(Book book, Purchase currentPurchase) {
//	
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		for(BookToBuy b:booksInPurchase) {
//			if(book.equals(b))
//				return true;
//		}
//		return false;
//	}
//
//	@Override
//	@Transactional
//	public Purchase deleteBookInPurchaseNoUser(Book book, Purchase currentPurchase)  {
//		
//		
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		BookToBuy rmbookToBuy= new BookToBuy(); 
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				rmbookToBuy=booksInPurchase.get(i);
//			}
//		}
//		
//		booksInPurchase.remove(rmbookToBuy);
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//		return currentPurchase;
//	}
//
//	@Override
//	@Transactional
//	public Purchase deleteAllBooksInPurchaseNoUser(Purchase currentPurchase) {
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		booksInPurchase.clear();
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//		return currentPurchase;
//	}
//
//	@Override
//	@Transactional
//	public void incrementQuantity(Book book, int userId) {
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				booksInPurchase.get(i).setQuantityToBuy(booksInPurchase.get(i).getQuantityToBuy()+1);
//			}
//		}
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//	}
//
//	@Override
//	@Transactional
//	public void incrementQuantityNoUser(Book book, Purchase currentPurchase) {
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				booksInPurchase.get(i).setQuantityToBuy(booksInPurchase.get(i).getQuantityToBuy()+1);
//			}
//		}
//		currentPurchase.setBooks(booksInPurchase);
//		PurchaseDao.save(currentPurchase);
//		
//	}
//
//	@Override
//	@Transactional
//	public void decreaseQuantity(Book book, int userId) {
//		Purchase currentPurchase= PurchaseDao.findByuser(userId);
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				if(booksInPurchase.get(i).getQuantityToBuy()>1)
//				{
//				booksInPurchase.get(i).setQuantityToBuy(booksInPurchase.get(i).getQuantityToBuy()-1);
//				currentPurchase.setBooks(booksInPurchase);
//				PurchaseDao.save(currentPurchase);
//				}
//				else
//				{
//					booksInPurchase.remove(booksInPurchase.get(i));
//					currentPurchase.setBooks(booksInPurchase);
//					PurchaseDao.save(currentPurchase);
//				}
//			}
//		}
//		
//	}
//
	@Override
	@Transactional
	public int createPurchase(Purchase newPurchase) {
		Purchase purchase = PurchaseDao.save(newPurchase);
		if(purchase != null)
		return purchase.getPurchaseId();
		else return 0;
	}
//
//	@Override
//	@Transactional
//	public void decreaseQuantityNoUser(Book book, Purchase currentPurchase) {
//		List<BookToBuy> booksInPurchase= currentPurchase.getBooks();
//		for(int i=0;i<booksInPurchase.size();i++) {
//			if(booksInPurchase.get(i).getBook().getBookId()==book.getBookId()) {
//				if(booksInPurchase.get(i).getQuantityToBuy()>1)
//				{
//				booksInPurchase.get(i).setQuantityToBuy(booksInPurchase.get(i).getQuantityToBuy()-1);
//				currentPurchase.setBooks(booksInPurchase);
//				PurchaseDao.save(currentPurchase);
//				}
//				else
//				{
//					booksInPurchase.remove(booksInPurchase.get(i));
//					currentPurchase.setBooks(booksInPurchase);
//					PurchaseDao.save(currentPurchase);
//				}
//			}
//		}
//		
//	}




	
	
}
