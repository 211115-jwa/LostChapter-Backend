//<<<<<<< HEAD:src/test/java/com/revature/lostchapterbackend/cartintegrationtests/CartIntegrationTests.java
package com.revature.lostchapterbackend.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.controller.CartController;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Cart;
import com.revature.lostchapterbackend.service.CartService;
import com.revature.lostchapterbackend.service.TransactionService;

//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.servlet.http.HttpServletRequest;
//
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
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.lostchapterbackend.dto.LoginDto;
//import com.revature.lostchapterbackend.model.Book;
//import com.revature.lostchapterbackend.model.BookToBuy;
//import com.revature.lostchapterbackend.model.Cart;
//import com.revature.lostchapterbackend.model.Genre;
//import com.revature.lostchapterbackend.model.User;
//
@SpringBootTest(classes=LostChapterBackendApplication.class)
public class CartControllerTest {
	@MockBean
	private CartService cartServ;
	
	@MockBean
	private  TransactionService transServ;
	
	@Autowired
	private static CartController cartController;
	
	// this object basically represents a mock of the Spring Web architecture
	private static MockMvc mockMvc;
	
	// this is a Jackson object mapper for JSON marshalling
	// (turning objects to JSON strings and vice versa
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp () {
		// sets up the minimum architecture to test our controller
		mockMvc = MockMvcBuilders.standaloneSetup(CartController.class).build();
	}
	
	@Test
	public void getCartById ()throws Exception {
		when(cartServ.getCartById(1)).thenReturn(new Cart());
		
		mockMvc.perform(get("/cart/{cartId}",1)).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getCartByIdNotFound() throws Exception {
		when(cartServ.getCartById(1)).thenReturn(null);
		mockMvc.perform(get("/cart/{cartId}", 1)).andExpect(status().isNotFound()).andReturn();
	}
	
	@Test
	public void addBookToCart(@RequestBody Book bookToAdd, @PathVariable int userId) throws Exception {
		Book newBook = new Book ();
		doNothing().when(cartServ).addBooksToCart(newBook, userId);
		
		
		String jsonBook = objMapper.writeValueAsString(newBook);
		
		mockMvc.perform(post("/cart/add/{bookToBuyId}/{userId}").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test 
	public void addBookToCartNoUser(@RequestBody Book bookToAdd, @PathVariable int cartId) throws Exception {
		String jsonBook = objMapper.writeValueAsString(null);
		mockMvc.perform(post("/cart/delete/{bookToBuyId}/{userId}").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andReturn();
	}
}
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//public class CartIntegrationTests {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired 
//	private EntityManagerFactory emf;
//	
//	@Autowired
//	private ObjectMapper mapper; 
//	
//	private User expectedUser; 
//	private BookToBuy positiveBookToBuy; 
//	private BookToBuy negativeBookToBuy; 
//	
//	@BeforeEach
//	public void setup() {
//		
//		EntityManager em = emf.createEntityManager();
//		Session session = em.unwrap(Session.class);
//		Transaction tx =session.beginTransaction();
//		
//		Book positiveBook = new Book();	//Id should be 1
//		Book noStockBook = new Book();	//Id should be 2
//		Genre genre = new Genre();	//Id should be 1
//		
//		genre.setGenre("test");
//		
//		session.persist(genre);		
//			
//		
//		
//		positiveBook.setISBN("test");
//		positiveBook.setBookName("test");
//		positiveBook.setSynopsis("test");
//		positiveBook.setAuthor("test");
//		positiveBook.setGenre(genre);
//		positiveBook.setQuantity(100);
//		positiveBook.setYear(2000);
//		positiveBook.setEdition("test");
//		positiveBook.setPublisher("test");
//		positiveBook.setSaleIsActive(false);
//		positiveBook.setSaleDiscountRate(0);
//		positiveBook.setBookPrice(0);
//		positiveBook.setBookImage("test");
//		
//		session.persist(positiveBook);
//		
//		noStockBook.setISBN("test");
//		noStockBook.setBookName("test");
//		noStockBook.setSynopsis("test");
//		noStockBook.setAuthor("test");
//		noStockBook.setGenre(genre);
//		noStockBook.setQuantity(0);
//		noStockBook.setYear(2000);
//		noStockBook.setEdition("test");
//		noStockBook.setPublisher("test");
//		noStockBook.setSaleIsActive(false);
//		noStockBook.setSaleDiscountRate(0);
//		noStockBook.setBookPrice(0);
//		noStockBook.setBookImage("test");
//		
//		session.persist(noStockBook);
//		
//		LocalDate dt = LocalDate.parse("2018-11-01");
//		User user = new User();	//Id should be 1
//		user.setUsername("test");
//		user.setPassword("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"); //its "password"
//		user.setFirstName("test");
//		user.setLastName("test");
////		user.setAge(25);
//		user.setEmail("test");
//		user.setBirthday(dt);
//		user.setAddress("test");
//		user.setRole("Customer");
//		
//		session.persist(user);
//		
//		Cart cart = new Cart(user);
//		
//		session.persist(cart);
//		
//		
//		tx.commit();
//		
//		session.close();
//		
//		this.expectedUser = new User();
//		
//		this.expectedUser.setUserId(1);
//		this.expectedUser.setUsername("test");
//		this.expectedUser.setPassword("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"); //its "password"
//		this.expectedUser.setFirstName("test");
//		this.expectedUser.setLastName("test");
//		
//		this.expectedUser.setEmail("test");
//		this.expectedUser.setBirthday(dt);
//		this.expectedUser.setAddress("test");
//		this.expectedUser.setRole("Customer");
//		
//		this.positiveBookToBuy = new BookToBuy();
//		this.negativeBookToBuy = new BookToBuy();
//		
//		this.positiveBookToBuy.setBooks(positiveBook);
//		this.positiveBookToBuy.setId(1);
//		this.positiveBookToBuy.setQuantityToBuy(1);
//		
//		this.negativeBookToBuy.setBooks(noStockBook);
//		this.negativeBookToBuy.setId(2);
//		this.negativeBookToBuy.setQuantityToBuy(1);
//		
//		
//		
//	}
//
//	@Test
//	public void cart_test_adding_item_to_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Cart expectedCart = new Cart();
//
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//		
//		
//		
//	}
//	
//	@Test
//	public void cart_test_removing_item_from_cart_positive() throws Exception {
//
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Cart expectedCart = new Cart();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "1").session(session);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		System.out.println(expectedJson);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	
//	@Test
//	public void cart_test_attempting_to_add_to_cart_item_out_of_stock_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "2").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Currently Out of Stock..."));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_to_cart_when_item_already_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Cart expectedCart = new Cart();
//
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_of_multiple_quantity_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		Cart expectedCart = new Cart();
//		
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_of_negative_quantity_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "-1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_get_cart_by_id_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/1/cart").session(session);
//		
//		Cart expectedCart = new Cart();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_get_cart_no_matching_id_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/4/cart").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("There is no cart with the id of 4"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_book_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Cart expectedCart = new Cart();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_book_not_in_cart_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookID", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_delete_all_items_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Cart expectedCart = new Cart();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test 
//	public void cart_test_add_book_to_cart_user_id_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/a/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_add_book_to_cart_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "a").param("quantityToBuy", "1").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	}
//	
//	@Test
//	public void cart_test_add_book_to_cart_quantityToBuy_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "a").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	
//		
//	}
//	
//	@Test
//	public void cart_test_trying_to_add_book_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "5").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//				
//		
//	}
//	
//	
//	@Test
//	public void cart_test_trying_to_access_cart_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/5/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//	@Test
//	public void cart_test_trying_to_get_cart_cart_id_not_int_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/a/cart").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The Id entered must be an int."));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_cartId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/a/cart").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("cart id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "a").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("cart id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_bookId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "6").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("Product not found on this cart"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_cartId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/7/cart").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//}
//=======
//package com.revature.lostchapterbackend.controllers;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.servlet.http.HttpServletRequest;
//
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
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.lostchapterbackend.dto.LoginDto;
//import com.revature.lostchapterbackend.model.Book;
//import com.revature.lostchapterbackend.model.BookToBuy;
//import com.revature.lostchapterbackend.model.Carts;
//import com.revature.lostchapterbackend.model.Genre;
//import com.revature.lostchapterbackend.model.Users;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//public class CartControllerTests {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired 
//	private EntityManagerFactory emf;
//	
//	@Autowired
//	private ObjectMapper mapper; 
//	
//	private Users expectedUser; 
//	private BookToBuy positiveBookToBuy; 
//	private BookToBuy negativeBookToBuy; 
//	
//	@BeforeEach
//	public void setup() {
//		
//		EntityManager em = emf.createEntityManager();
//		Session session = em.unwrap(Session.class);
//		Transaction tx =session.beginTransaction();
//		
//		Book positiveBook = new Book();	//Id should be 1
//		Book noStockBook = new Book();	//Id should be 2
//		Genre genre = new Genre();	//Id should be 1
//		
//		genre.setGenre("test");
//		
//		session.persist(genre);		
//			
//		
//		
//		positiveBook.setISBN("test");
//		positiveBook.setBookName("test");
//		positiveBook.setSynopsis("test");
//		positiveBook.setAuthor("test");
//		positiveBook.setGenre(genre);
//		positiveBook.setQuantity(100);
//		positiveBook.setYear(2000);
//		positiveBook.setEdition("test");
//		positiveBook.setPublisher("test");
//		positiveBook.setSaleIsActive(false);
//		positiveBook.setSaleDiscountRate(0);
//		positiveBook.setBookPrice(0);
//		positiveBook.setBookImage("test");
//		
//		session.persist(positiveBook);
//		
//		noStockBook.setISBN("test");
//		noStockBook.setBookName("test");
//		noStockBook.setSynopsis("test");
//		noStockBook.setAuthor("test");
//		noStockBook.setGenre(genre);
//		noStockBook.setQuantity(0);
//		noStockBook.setYear(2000);
//		noStockBook.setEdition("test");
//		noStockBook.setPublisher("test");
//		noStockBook.setSaleIsActive(false);
//		noStockBook.setSaleDiscountRate(0);
//		noStockBook.setBookPrice(0);
//		noStockBook.setBookImage("test");
//		
//		session.persist(noStockBook);
//		
//		LocalDate dt = LocalDate.parse("2018-11-01");
//		Users user = new Users();	//Id should be 1
//		user.setUsername("test");
//		user.setPassword("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"); //its "password"
//		user.setFirstName("test");
//		user.setLastName("test");
////		user.setAge(25);
//		user.setEmail("test");
//		user.setBirthday(dt);
//		user.setAddress("test");
//		user.setRole("Customer");
//		
//		session.persist(user);
//		
//		Carts cart = new Carts(user);
//		
//		session.persist(cart);
//		
//		
//		tx.commit();
//		
//		session.close();
//		
//		this.expectedUser = new Users();
//		
//		this.expectedUser.setId(1);
//		this.expectedUser.setUsername("test");
//		this.expectedUser.setPassword("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"); //its "password"
//		this.expectedUser.setFirstName("test");
//		this.expectedUser.setLastName("test");
//		
//		this.expectedUser.setEmail("test");
//		this.expectedUser.setBirthday(dt);
//		this.expectedUser.setAddress("test");
//		this.expectedUser.setRole("Customer");
//		
//		this.positiveBookToBuy = new BookToBuy();
//		this.negativeBookToBuy = new BookToBuy();
//		
//		this.positiveBookToBuy.setBooks(positiveBook);
//		this.positiveBookToBuy.setId(1);
//		this.positiveBookToBuy.setQuantityToBuy(1);
//		
//		this.negativeBookToBuy.setBooks(noStockBook);
//		this.negativeBookToBuy.setId(2);
//		this.negativeBookToBuy.setQuantityToBuy(1);
//		
//		
//		
//	}
//
//	@Test
//	public void cart_test_adding_item_to_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Carts expectedCart = new Carts();
//
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//		
//		
//		
//	}
//	
//	@Test
//	public void cart_test_removing_item_from_cart_positive() throws Exception {
//
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Carts expectedCart = new Carts();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "1").session(session);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		System.out.println(expectedJson);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	
//	@Test
//	public void cart_test_attempting_to_add_to_cart_item_out_of_stock_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "2").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Currently Out of Stock..."));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_to_cart_when_item_already_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Carts expectedCart = new Carts();
//
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_of_multiple_quantity_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		Carts expectedCart = new Carts();
//		
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_adding_item_of_negative_quantity_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "-1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_get_cart_by_id_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/1/cart").session(session);
//		
//		Carts expectedCart = new Carts();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_get_cart_no_matching_id_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/4/cart").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("There is no cart with the id of 4"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_book_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Carts expectedCart = new Carts();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_book_not_in_cart_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookID", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		
//	}
//	
//	@Test
//	public void cart_test_delete_all_items_in_cart_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Carts expectedCart = new Carts();
//		expectedCart.setUser(this.expectedUser);
//		expectedCart.setCartId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedCart.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedCart);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/cart").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test 
//	public void cart_test_add_book_to_cart_user_id_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/a/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_add_book_to_cart_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "a").param("quantityToBuy", "1").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	}
//	
//	@Test
//	public void cart_test_add_book_to_cart_quantityToBuy_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "1").param("quantityToBuy", "a").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	
//		
//	}
//	
//	@Test
//	public void cart_test_trying_to_add_book_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/cart").param("bookId", "5").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//				
//		
//	}
//	
//	
//	@Test
//	public void cart_test_trying_to_access_cart_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/5/cart").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//	@Test
//	public void cart_test_trying_to_get_cart_cart_id_not_int_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/a/cart").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The Id entered must be an int."));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_cartId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/a/cart").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("cart id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "a").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("cart id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_bookId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/cart").param("bookId", "6").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("Product not found on this cart"));
//		
//	}
//	
//	@Test
//	public void cart_test_delete_item_in_cart_cartId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/7/cart").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//}
//>>>>>>> 299a339d32b047175d407fcf1c6fa7494a6941ea:src/test/java/com/revature/lostchapterbackend/controllers/CartControllerTests.java
