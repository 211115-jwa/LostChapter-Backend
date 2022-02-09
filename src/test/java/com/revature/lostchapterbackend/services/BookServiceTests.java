package com.revature.lostchapterbackend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.dao.BookDAO;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Genre;
import com.revature.lostchapterbackend.service.BookService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class BookServiceTests {
	@MockBean
	private BookDAO bookDao;
	@Autowired
	private BookService bookServ;
	
	private static List<Book> mockBooks;
	
	private static Genre mockGenreHorror;
	
	private static Genre mockGenreAction;
	
	@BeforeAll
	public static void mockBookSetUp() {
			mockBooks = new ArrayList<>();
			
			mockGenreHorror = new Genre();
			mockGenreHorror.setGenre("Horror");
			mockGenreHorror.setId(1);
			
			mockGenreAction = new Genre();
			mockGenreAction.setGenre("Action");
			mockGenreAction.setId(2);
		
		for (int i=1; i<=5; i++) {
			Book book = new Book();
			book.setBookId(i);
			if (i<3)
				book.setGenre(mockGenreHorror);
				book.setBookName("To Kill a Mocking Bird");
				book.setISBN("123456789");
			if  (i>3)
				book.setGenre(mockGenreAction);
				book.setBookName("Fahrenheit 451");
				book.setISBN("987654321");
			mockBooks.add(book);
		}
	}
	
	@Test
	public void getAllBooks() {
		when(bookDao.findAll()).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getAllBooks();
		
		assertEquals(mockBooks, actualBooks);
	}
	
	@Test
	public void getBookByIdExists() {
		Book book = new Book();
		book.setBookId(0);
		
		when(bookDao.findById(1)).thenReturn(Optional.of(book));
		
		Book actualBook = bookServ.getBookById(1);
		assertEquals(book, actualBook);
	}
	
	@Test
	public void getBookByIdDoesNotExist() {
		when(bookDao.findById(1)).thenReturn(Optional.empty());
		
		Book actualBook = bookServ.getBookById(1);
		assertNull(actualBook);
	}
	
	@Test
	public void getBooksByGenreExists() {
		String genre = "Horror";
		
		when(bookDao.findByGenre_Genre("Horror")).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getBookByGenre(genre);
		boolean onlyHorror = true;
		for (Book book : actualBooks) {
			if (!book.getGenreId().equals(1))
				onlyHorror = false;
		}
		assertTrue(onlyHorror);
	}

	
	@Test
	public void getBooksByGenreDoesNotExist() {
		String genre = "Mystery";
		
		when(bookDao.findByGenre_Genre("Mystery")).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getBookByGenre(genre);
		assertTrue(actualBooks.isEmpty());
	}
	
	@Test
	public void getBooksByISBNExists() {
		String ISBN = "123456789";
		
		when(bookDao.findByISBN("123456789")).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getByISBN(ISBN);
		boolean onlyISBN = true;
		for (Book book : actualBooks) {
			if (!book.getISBN().equals("123456789"))
				onlyISBN = false;
		}
		assertTrue(onlyISBN);
	}

	
	@Test
	public void getBooksByISBNDoesNotExist() {
		String ISBN = "1";
		
		when(bookDao.findByISBN("1")).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getByISBN(ISBN);
		assertTrue(actualBooks.isEmpty());
	}
	
	@Test
	public void getBooksByKeyWordExists() {
		String keyWord = "Kill";
		
		when(bookDao.findAll()).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getByKeyWord(keyWord);
		boolean onlyKeyWord = true;
		for (Book book : actualBooks) {
			if (!book.getBookName().toLowerCase().contains(keyWord.toLowerCase()))
				onlyKeyWord = false;
		}
		assertTrue(onlyKeyWord);
	}
	
	@Test
	public void getBooksByKeyWordDoesNotExist() {
		String keyWord = "bubble gum";
		
		when(bookDao.findAll()).thenReturn(mockBooks);
		
		List<Book> actualBooks = bookServ.getByKeyWord(keyWord);
		assertTrue(actualBooks.isEmpty());
	}
	
	
//	Feature still a stretch?
//	@Test
//	public void getBooksByFeaturedExists() {
//		
//	}
//	
//	@Test
//	public void getBooksByFeaturedDoesNotExist() {
//
//	}
	
	@Test
	public void addBookSuccessfully() {
		Book newBook = new Book();
		Book mockBook = new Book();
		mockBook.setBookId(69);
		
		when(bookDao.save(newBook)).thenReturn(mockBook);
		
		int newId = bookServ.addBook(newBook);
		
		assertNotEquals(0, newId);
	}
	
	@Test
	public void addBookUnSuccessfully() {
		Book book = new Book();
		
		when(bookDao.save(book)).thenReturn(book);
		
		int newId = bookServ.addBook(book);
		
		assertEquals(0,newId);
	}
	
	@Test
	public void updateBookSuccessfully() {
		Book editedBook = new Book();
		editedBook.setBookId(1);
		editedBook.setBookName("My Last Book");
		
		when(bookDao.findById(2)).thenReturn(Optional.of(editedBook));
		when(bookDao.save(Mockito.any(Book.class))).thenReturn(editedBook);
		
		Book actualBook = bookServ.updateBook(editedBook);
		
		assertEquals(editedBook, actualBook);

	}
	
	@Test
	public void updateBookUnsuccessfully() {
		when(bookDao.findById(2)).thenReturn(Optional.empty());
		
		Book editedBook = new Book();
		editedBook.setBookId(2);
		editedBook.setBookName("My First Book");
		
		Book actualBook = bookServ.updateBook(editedBook);
		
		assertNull(actualBook);
		verify(bookDao, times(0)).save(Mockito.any(Book.class));
	}
	
}