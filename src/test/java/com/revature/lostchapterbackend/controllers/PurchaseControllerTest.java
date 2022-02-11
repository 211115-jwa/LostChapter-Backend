//<<<<<<< HEAD:src/test/java/com/revature/lostchapterbackend/Purchaseintegrationtests/PurchaseIntegrationTests.java
package com.revature.lostchapterbackend.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.revature.lostchapterbackend.controller.PurchaseController;
import com.revature.lostchapterbackend.model.Book;
import com.revature.lostchapterbackend.model.Purchase;
import com.revature.lostchapterbackend.service.PurchaseService;


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
//import com.revature.lostchapterbackend.model.Purchase;
//import com.revature.lostchapterbackend.model.Genre;
//import com.revature.lostchapterbackend.model.User;
//
@SpringBootTest(classes=LostChapterBackendApplication.class)
public class PurchaseControllerTest {
	@MockBean
	private PurchaseService purchaseServ;
	
	//@MockBean
	//private  TransactionService transServ;
	
	@Autowired
	private static PurchaseController purchaseController;
	
	// this object basically represents a mock of the Spring Web architecture
	private static MockMvc mockMvc;
	
	// this is a Jackson object mapper for JSON marshalling
	// (turning objects to JSON strings and vice versa
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp () {
		// sets up the minimum architecture to test our controller
		mockMvc = MockMvcBuilders.standaloneSetup(PurchaseController.class).build();
	}
	
	@Test
	public void createPurchase() throws Exception {
		Purchase newPurchase = new Purchase();
		
		when(purchaseServ.createPurchase(newPurchase)).thenReturn(1);
		
		
		String jsonPurchase = objMapper.writeValueAsString(newPurchase);
		mockMvc.perform(post("/Purchase").content(jsonPurchase).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
		}
	
	@Test
	public void getPurchaseById ()throws Exception {
		when(purchaseServ.getPurchaseById(1)).thenReturn(new Purchase());
		
		mockMvc.perform(get("/Purchase/{PurchaseId}",1)).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getPurchaseByIdNotFound() throws Exception {
		when(purchaseServ.getPurchaseById(1)).thenReturn(null);
		mockMvc.perform(get("/Purchase/{PurchaseId}", 1)).andExpect(status().isNotFound()).andReturn();
	}
	
}
	
//	@Test
//	public void addBookToPurchase(@RequestBody Book bookToAdd, @PathVariable int userId) throws Exception {
//		Book newBook = new Book ();
//		doNothing().when(PurchaseServ).addBooksToPurchase(newBook, userId);
//		
//		
//		String jsonBook = objMapper.writeValueAsString(newBook);
//		
//		mockMvc.perform(post("/Purchase/add/{bookToBuyId}/{userId}").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isBadRequest()).andReturn();
//	}
	
//	@Test 
//	public void addBookToPurchaseNoUser(@RequestBody Book bookToAdd, @PathVariable int PurchaseId) throws Exception {
//		String jsonBook = objMapper.writeValueAsString(null);
//		mockMvc.perform(post("/Purchase/delete/{bookToBuyId}/{userId}").content(jsonBook).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isBadRequest()).andReturn();
//	}
//}
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//public class PurchaseIntegrationTests {
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
//		Purchase Purchase = new Purchase(user);
//		
//		session.persist(Purchase);
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
//	public void Purchase_test_adding_item_to_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Purchase expectedPurchase = new Purchase();
//
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//		
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_removing_item_from_Purchase_positive() throws Exception {
//
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchase expectedPurchase = new Purchase();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "1").session(session);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
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
//	public void Purchase_test_attempting_to_add_to_Purchase_item_out_of_stock_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "2").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Currently Out of Stock..."));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_to_Purchase_when_item_already_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Purchase expectedPurchase = new Purchase();
//
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_of_multiple_quantity_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		Purchase expectedPurchase = new Purchase();
//		
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_of_negative_quantity_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "-1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_get_Purchase_by_id_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/1/Purchase").session(session);
//		
//		Purchase expectedPurchase = new Purchase();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_get_Purchase_no_matching_id_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/4/Purchase").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("There is no Purchase with the id of 4"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_book_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchase expectedPurchase = new Purchase();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_book_not_in_Purchase_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookID", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_all_items_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchase expectedPurchase = new Purchase();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test 
//	public void Purchase_test_add_book_to_Purchase_user_id_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/a/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_add_book_to_Purchase_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "a").param("quantityToBuy", "1").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	}
//	
//	@Test
//	public void Purchase_test_add_book_to_Purchase_quantityToBuy_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "a").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	
//		
//	}
//	
//	@Test
//	public void Purchase_test_trying_to_add_book_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "5").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//				
//		
//	}
//	
//	
//	@Test
//	public void Purchase_test_trying_to_access_Purchase_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/5/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_trying_to_get_Purchase_Purchase_id_not_int_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/a/Purchase").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The Id entered must be an int."));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_PurchaseId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/a/Purchase").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Purchase id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "a").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Purchase id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_bookId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "6").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("Product not found on this Purchase"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_PurchaseId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/7/Purchase").param("bookId", "1").session(session);
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
//import com.revature.lostchapterbackend.model.Purchases;
//import com.revature.lostchapterbackend.model.Genre;
//import com.revature.lostchapterbackend.model.Users;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//public class PurchaseControllerTests {
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
//		Purchases Purchase = new Purchases(user);
//		
//		session.persist(Purchase);
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
//	public void Purchase_test_adding_item_to_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Purchases expectedPurchase = new Purchases();
//
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//
//		
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_removing_item_from_Purchase_positive() throws Exception {
//
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchases expectedPurchase = new Purchases();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "1").session(session);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
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
//	public void Purchase_test_attempting_to_add_to_Purchase_item_out_of_stock_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "2").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Currently Out of Stock..."));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_to_Purchase_when_item_already_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		Purchases expectedPurchase = new Purchases();
//
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_of_multiple_quantity_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		Purchases expectedPurchase = new Purchases();
//		
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		this.positiveBookToBuy.setQuantityToBuy(2);
//		bookToBuyList.add(positiveBookToBuy);
//		
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_adding_item_of_negative_quantity_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "-1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_get_Purchase_by_id_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/1/Purchase").session(session);
//		
//		Purchases expectedPurchase = new Purchases();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_get_Purchase_no_matching_id_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/4/Purchase").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("There is no Purchase with the id of 4"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_book_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchases expectedPurchase = new Purchases();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_book_not_in_Purchase_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookID", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_all_items_in_Purchase_positive() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "2").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200));
//		
//		Purchases expectedPurchase = new Purchases();
//		expectedPurchase.setUser(this.expectedUser);
//		expectedPurchase.setPurchaseId(1);
//		ArrayList<BookToBuy> bookToBuyList = new ArrayList<>();
//		expectedPurchase.setBooksToBuy(bookToBuyList);
//		
//		String expectedJson = mapper.writeValueAsString(expectedPurchase);
//		
//		builder = MockMvcRequestBuilders.delete("/users/1/Purchase").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.content().json(expectedJson));
//		
//	}
//	
//	@Test 
//	public void Purchase_test_add_book_to_Purchase_user_id_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/a/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_add_book_to_Purchase_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "a").param("quantityToBuy", "1").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	}
//	
//	@Test
//	public void Purchase_test_add_book_to_Purchase_quantityToBuy_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "1").param("quantityToBuy", "a").session(session);
//		
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("product id or quantity must be of type int!"));
//	
//		
//	}
//	
//	@Test
//	public void Purchase_test_trying_to_add_book_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/1/Purchase").param("bookId", "5").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Book doesn't exist"));
//				
//		
//	}
//	
//	
//	@Test
//	public void Purchase_test_trying_to_access_Purchase_that_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/5/Purchase").param("bookId", "1").param("quantityToBuy", "1").session(session);
//		
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_trying_to_get_Purchase_Purchase_id_not_int_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/a/Purchase").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("The Id entered must be an int."));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_PurchaseId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/a/Purchase").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Purchase id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_bookId_doesnt_match_pattern_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "a").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().string("Purchase id/product id must be of type int!"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_bookId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/1/Purchase").param("bookId", "6").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("Product not found on this Purchase"));
//		
//	}
//	
//	@Test
//	public void Purchase_test_delete_item_in_Purchase_PurchaseId_doesnt_exist_negative() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("currentUser", this.expectedUser);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/7/Purchase").param("bookId", "1").session(session);
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(404)).andExpect(MockMvcResultMatchers.content().string("No value present"));
//		
//	}
//	
//}
//>>>>>>> 299a339d32b047175d407fcf1c6fa7494a6941ea:src/test/java/com/revature/lostchapterbackend/controllers/PurchaseControllerTests.java
