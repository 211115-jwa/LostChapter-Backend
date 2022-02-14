package com.revature.lostchapterbackend.controllers;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import com.revature.lostchapterbackend.model.Users;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.DirtiesContext.ClassMode;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.revature.lostchapterbackend.dto.AddOrUpdateBookDTO;
//import com.revature.lostchapterbackend.model.Book;
//import com.revature.lostchapterbackend.model.Genre;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.controller.BookController;

import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.BookService;

//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes=LostChapterBackendApplication.class)
public class BookControllerTest {
	@MockBean
	private BookService bookServ;
	
	@Autowired
	private BookController bookController;
	
	private static MockMvc mockMvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// this initializes the Spring Web/MVC architecture for just one controller
		// so that we can isolate and unit test it
		mockMvc = MockMvcBuilders.standaloneSetup(BookController.class).build();
	}
	
	@Test
	public void getAvailableBooks() throws Exception {
		when(bookServ.getAllBooks()).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/book"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();

	}
	
	@Test
	public void getFeaturedBooks() throws Exception {
		when(bookServ.getFeaturedBooks()).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/book"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();

	}
	
	@Test
	public void getBookByIdIfExists() throws Exception {
		when(bookServ.getBookById(1)).thenReturn(new Book());
			
		mockMvc.perform(get("/book/{bookId}", 1)).andExpect(status().isOk()).andReturn();
		}
		
	@Test
	public void getPetByIdDoesNotExist() throws Exception {
		when(bookServ.getBookById(1)).thenReturn(null);
			
		mockMvc.perform(get("/book/{bookId}", 1)).andExpect(status().isNotFound()).andReturn();
		
		}
	
	@Test
	public void getBookByGenre () throws Exception {
		when(bookServ.getBookByGenre("fiction")).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/book/genre/{name}", "fiction"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();
		}
	
	@Test
	public void getBookByKeyword() throws Exception {
		when(bookServ.getByKeyWord("key")).thenReturn(Collections.emptyList());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
		
		mockMvc.perform(get("/book/search/{key}", "key"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();
		}
	
	@Test
	public void getBookBySale() throws Exception {
	when(bookServ.getBooksBySale()).thenReturn(Collections.emptyList());
	
	String jsonSet = objMapper.writeValueAsString(Collections.emptyList());
	
	mockMvc.perform(get("/book/books/sales"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json(jsonSet))
		.andReturn();
	
	}
		
	@Test
	public void addNewBookSuccessfully() throws Exception {
		Book newBook = new Book();
		when(bookServ.addBook(newBook)).thenReturn(1);
		
		String jsonBook = objMapper.writeValueAsString(newBook);
		
		mockMvc.perform(post("/book").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();
	}
	
	@Test
	public void addNewBookWithoutBook() throws Exception {
		String jsonBook = objMapper.writeValueAsString(null);
		
		mockMvc.perform(post("/book").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andReturn();
	}
	
}		

	
	

//
//	@Autowired
//	private MockMvc mvc; // send fake http requests to the server
//
//	@Autowired
//	private EntityManagerFactory emf;
//
//	@Autowired
//	private ObjectMapper mapper; // translate JSON objects
//
//	private Genre g;
//	private Genre g2;
//	private Users admin;
//
//	@BeforeEach
//	public void setUp() {
//		EntityManager em = emf.createEntityManager();
//		Session session = em.unwrap(Session.class);
//		Transaction tx = session.beginTransaction();
//
//		g = new Genre();
//		g.setGenre("Fiction");
//		em.persist(g);
//		g2 = new Genre();
//		g2.setGenre("NonFiction");
//		em.persist(g2);
//		LocalDate dt = LocalDate.parse("2000-11-01");
//		// Arrange
//		Book actualBook = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		em.persist(actualBook);
//
//		Book actualBook2 = new Book("2122232425", "bookName2", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99,10.99, "");
//		em.persist(actualBook2);
//
//		Book actualBook3 = new Book("91011121314", "bookName3", "synopsis",
//				"author", g2, 1, 1996, "edition", "publisher",
//				false, 0.99, 10.99, "");
//		em.persist(actualBook3);
//
//		admin = new Users("test123",
//				"5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8",
//				"testfn","testln","test123@gmail.com",dt,
//				"address123","Admin");
//		em.persist(admin);
//
//		tx.commit();
//		session.close();
//
//	}
//
//	@Test
//	public void testGetAllBook_positive() throws Exception {
//
//
//		// Act and Assert
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
//
//		//fix genre here also
//		Book expectedBook = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		expectedBook.setBookId(1);
//		Book expectedBook2 = new Book("2122232425", "bookName2", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99,10.99, ""); //book id 1
//		expectedBook2.setBookId(2);
//		Book expectedBook3 = new Book("91011121314", "bookName3", "synopsis",
//				"author", g2, 1, 1996, "edition", "publisher",
//				false, 0.99, 10.99, ""); //book id 1
//		expectedBook3.setBookId(3);
//
//		List<Book> expectedBooks = new ArrayList<>();
//		expectedBooks.add(expectedBook);
//		expectedBooks.add(expectedBook2);
//		expectedBooks.add(expectedBook3);
//
//		String expectedJson = mapper.writeValueAsString(expectedBooks);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//	}
//	
//	@Test
//	public void testGetBookById_positive() throws Exception {
//
//		// Not sure how best to send id of 1
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/1");
//
//		//fix genre here also
//		Book expectedBook = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		expectedBook.setBookId(1);
//
//		String expectedJson = mapper.writeValueAsString(expectedBook);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//	}
//
//	@Test
//	public void testGetAllBooksByGenre_positive() throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/genre/1");
//
//		Book expectedBook1 = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		expectedBook1.setBookId(1);
//		Book expectedBook2 = new Book("2122232425", "bookName2", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99,10.99, "");
//		expectedBook2.setBookId(2);
//
//		List<Book> expectedBooks = new ArrayList<>();
//		expectedBooks.add(expectedBook1);
//		expectedBooks.add(expectedBook2);
//
//		String expectedJson = mapper.writeValueAsString(expectedBooks);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//	}
//
//	@Test
//	public void testGetBooksByKeyWord_positive() throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/search/bookName");
//
//		Book expectedBook1 = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		expectedBook1.setBookId(1);
//		Book expectedBook2 = new Book("2122232425", "bookName2", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99,10.99, "");
//		expectedBook2.setBookId(2);
//		Book expectedBook3 = new Book("91011121314", "bookName3", "synopsis",
//				"author", g2, 1, 1996, "edition", "publisher",
//				false, 0.99, 10.99, "");
//		expectedBook3.setBookId(3);
//
//		List<Book> expectedBooks = new ArrayList<>();
//		expectedBooks.add(expectedBook1);
//		expectedBooks.add(expectedBook2);
//		expectedBooks.add(expectedBook3);
//
//		String expectedJson = mapper.writeValueAsString(expectedBooks);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//	}
//
//	@Test
//	public void testGetBooksBySales_positive() throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/sales");
//
//		Book expectedBook1 = new Book("1234567879", "bookName", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//		expectedBook1.setBookId(1);
//		Book expectedBook2 = new Book("2122232425", "bookName2", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.99,10.99, "");
//		expectedBook2.setBookId(2);
//
//		List<Book> expectedBooks = new ArrayList<>();
//		expectedBooks.add(expectedBook1);
//		expectedBooks.add(expectedBook2);
//
//		String expectedJson = mapper.writeValueAsString(expectedBooks);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//	}
//
//	@Test
//	public void testAddBook_positive() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"author", 1, 1, 1996, "edition",
//				"publisher", true,
//				0.90, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		Book expectedBook1 = new Book("2425262728", "bookName4", "synopsis",
//				"author", g, 1, 1996, "edition",
//				"publisher", true,
//				0.90, 10.99, "image");
//		expectedBook1.setBookId(4);
//
//
//		String expectedJson = mapper.writeValueAsString(expectedBook1);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(201))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//	}
//
//	@Test
//	public void testAddBookISBNIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("", "bookName4", "synopsis",
//				"author", 1, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("ISBN cannot be blank."));
//
//	}
//
//	@Test
//	public void testAddBookButBookNameIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "", "synopsis",
//				"author", 1, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Book name cannot be blank."));
//
//	}
//
//	@Test
//	public void testAddBookSynopsisIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "",
//				"author", 1, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Synopsis cannot be blank."));
//
//	}
//
//	@Test
//	public void testAddBookAuthorIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"", 1, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Author cannot be blank."));
//
//	}
//
//	@Test
//	public void testAddBookGenreIsZero_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"author", 0, 1, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "image");
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Genre cannot be blank."));
//
//	}
//
////	@Test
////	public void testAddBookQuantityIsZero_negative() throws Exception {
////
////		AddOrUpdateBookDTO actualBook = new AddBookDTO("2425262728", "bookName4", "synopsis",
////				"author", 1, 0, 1996, "edition",
////				"publisher", true,
////				0.99, 10.99, "image");
////		String jsonToSend = mapper.writeValueAsString(actualBook);
////
////		MockHttpSession session1 = new MockHttpSession();
////
////		session1.setAttribute("currentUser", admin);
////
////		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
////				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
////
////		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
////				.andExpect(MockMvcResultMatchers.content().string("Quantity cannot be blank."));
////
////	}
//
//	@Test
//	public void testAddBookEditionIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"author", 1, 0, 1996, "",
//				"publisher", true,
//				0.99, 10.99, "image");
//
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Edition cannot be blank."));
//	}
//
//	@Test
//	public void testAddBookPublisherIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"author", 1, 0, 1996, "edition",
//				"", true,
//				0.99, 10.99, "image");
//
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Publisher cannot be blank."));
//	}
//
//	@Test
//	public void testAddBookImageIsEmpty_negative() throws Exception {
//
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2425262728", "bookName4", "synopsis",
//				"author", 1, 0, 1996, "edition",
//				"publisher", true,
//				0.99, 10.99, "");
//
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//
//		MockHttpSession session1 = new MockHttpSession();
//
//		session1.setAttribute("currentUser", admin);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session1)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//				.andExpect(MockMvcResultMatchers.content().string("Image cannot be blank."));
//	}
//	
//	@Test
//	public void book_controller_test_get_book_by_id_bookNotFoundException_negative() throws Exception {
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/-1");
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//		
//	}
//	
//	@Test
//	public void book_controller_test_add_book_genre_not_found_exception_negative() throws Exception {
//		
//		AddOrUpdateBookDTO bookToAdd = new AddOrUpdateBookDTO("12345", "bookname5", "synopsis", "author", 12345, 5, 1996, "edition", "publisher", true, 0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(bookToAdd);
//		
//		MockHttpSession session = new MockHttpSession(); 
//		
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//		.andExpect(MockMvcResultMatchers.content().string("Genre doesn't exist"));
//		
//	}
//	
//	@Test
//	public void book_controller_test_add_book_ISNBAlreadyExistsException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO bookToAdd = new AddOrUpdateBookDTO("1234567879", "bookname5", "synopsis", "author", 1, 5, 1996, "edition", "publisher", true, 0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(bookToAdd);
//		
//		MockHttpSession session = new MockHttpSession(); 
//		
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/books").session(session)
//				.content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
//		.andExpect(MockMvcResultMatchers.content().string("ISBN already used for another book"));
//		
//	}
//	
//	@Test
//	public void book_controller_successfully_updating_book_positive() throws Exception {
//		
//		AddOrUpdateBookDTO bookToSend = new AddOrUpdateBookDTO("000001", "bookName", "synopsis",
//				"author", 1, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(bookToSend);
//		
//		Book actualBook = new Book("000001", "bookName", "synopsis",
//				"author", g, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		actualBook.setBookId(1);
//		
//		String expectedJson = mapper.writeValueAsString(actualBook);
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/1").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void book_controller_update_book_BookNotFoundException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("000001", "bookName", "synopsis",
//				"author", 1, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//		
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/20").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//		
//	}
//	
//	@Test
//	public void book_controller_update_book_InvalidParamterException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("000001", "bookName", "synopsis",
//				"author", 1, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//		
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/a").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Id must be in Int format"));
//		
//	}
//	
//	@Test
//	public void book_controller_update_book_GenreNotFoundException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("000001", "bookName", "synopsis",
//				"author", 20, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//		
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/1").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Genre doesn't exist"));
//		
//	}
//	
//	@Test
//	public void book_controller_update_book_SaleDiscountRateException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("000001", "bookName", "synopsis",
//				"author", 1, 5, 1996, "edition",
//				"publisher", true,
//				1, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//		
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/1").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Sale discount rate must be more than 0.0 and less than or equal to 0.90"));
//		
//	}
//	
//	@Test
//	public void book_controller_update_book_ISNBAlreadyExistsException_negative() throws Exception {
//		
//		AddOrUpdateBookDTO actualBook = new AddOrUpdateBookDTO("2122232425", "bookName", "synopsis",
//				"author", 1, 5, 1996, "edition",
//				"publisher", true,
//				0.5, 10.99, "image");
//		
//		String jsonToSend = mapper.writeValueAsString(actualBook);
//		
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", admin);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/books/1").session(session).content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("ISBN already used for another book"));
//		
//	}
//	
//	@Test
//	public void book_controller_get_book_by_id_InvalidParamterException_negative() throws Exception {	//Yall didnt catch this exception. Thrown in service layer, but not caught in controller layer.
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/a"); 
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The Id entered must be an int."));
//		
//	}
//	
//	@Test
//	public void book_controller_get_books_by_genre_id_InvalidParamterException_negative() throws Exception { //Same thing here
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/genre/a");
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The genreId entered must be an int."));
//		
//	}
//	
//	
//	
//
//
//}
