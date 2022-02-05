package com.revature.lostchapterbackend.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.revature.lostchapterbackend.dao.BookDAO;
import com.revature.lostchapterbackend.model.Book;



public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(BookService.class);
	private BookDAO bookDao;
	

	@Autowired
	public BookServiceImpl(BookDAO bookDao) {
		this.bookDao = bookDao;
	}

	

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		List<Book> books = bookDao.findAll();
		return books;
	}



	@Override
	@Transactional
	public Book getBookById(int bookId) {
		Optional<Book> book = bookDao.findById(bookId);
		if (book.isPresent())
			return book.get();
		else return null;
	}



	@Override
	@Transactional
	public int addBook(Book newBook) {
		Book book = bookDao.save(newBook);
		if(book != null)
		return book.getBookId();
		else return 0;
	}



	@Override
	@Transactional
	public Book updateBook(Book bookToUpdate) {
		Optional<Book> BookFromDatabase = bookDao.findById(bookToUpdate.getBookId());
		if (BookFromDatabase.isPresent()) {
			bookDao.save(bookToUpdate);
			return bookDao.findById(bookToUpdate.getBookId()).get();
		}
		return null;
	}


		
		@Override
		@Transactional
		public List<Book> getBookByGenre(int Id) {
			return bookDao.findBygenre(Id);
		}
		

		@Override
		@Transactional
		public List<Book> getByISBN(String ISBN){
			return bookDao.findByISBN(ISBN);
		}
//		

	
	
		
		
//	public List<Book> getFeaturedBooks() {
//		logger.info("BookService.getFeaturedBooks() invoked.");
//
//		Page<Book> bookPage = bd.findAll(PageRequest.of(0, 15, Sort.by("quantity").descending()));
//		List<Book> b = null;
//		if (bookPage.hasContent()) {
//			System.out.println(bookPage.getContent());
//			b = bookPage.getContent();
//		}
//
//		return b;
//	}
//
//	public Book getBookById(String id) throws BookNotFoundException {
//		logger.info("BookService.getBookById() invoked.");
//
//		try {
//			int bookId = Integer.parseInt(id);
//			if (!bd.findById(bookId).isPresent()) {
//				throw new BookNotFoundException("Book doesn't exist");
//			}
//			return bd.findById(bookId).get();
//		} catch (NumberFormatException e) {
//			throw new InvalidParameterException("The Id entered must be an int.");
//
//		}
//
//	}
//
//	public List<Book> getBooksByGenreId(String genreId) {
//		logger.info("BookService.getBooksByGenreId() invoked.");
//
//		try {
//			int gId = Integer.parseInt(genreId);
//			return bd.getByGenreId(gId);
//		} catch (NumberFormatException e) {
//			throw new InvalidParameterException("The genreId entered must be an int.");
//		}
//
//	}
//
//	public List<Book> getBooksByKeyword(String keyword) {
//		logger.info("BookService.getBooksByKeyword() invoked.");
//
//		return bd.findBybookNameIgnoreCaseContaining(keyword);
//	}
//
//	public List<Book> getBooksBySale() {
//		logger.info("BookService.getBooksBySale() invoked.");
//
//		return bd.findBysaleIsActiveTrue();
//	}
//
//	public Book addBook(AddOrUpdateBookDTO dto) throws GenreNotFoundException, ISBNAlreadyExists,
//			InvalidParameterException, SynopsisInputException, SaleDiscountRateException {
//
//		logger.info("BookService.addBook() invoked.");
//
//		vBUtil.validateBookInput(dto);
//		System.out.println("service syso:" +dto.getYear());
//
//		/*-
//		 *  check if isbn already exist
//		 */
//		logger.info("check if ISBN already exist");
//
//		if (bd.findByISBN(dto.getISBN()).isPresent()) {
//			throw new ISBNAlreadyExists("ISBN already used for another book");
//		}
//
//		Genre getGenre = gd.findById(dto.getGenre()).get();
//		Book addedBook = new Book(dto.getISBN(), dto.getBookName(), dto.getSynopsis(), dto.getAuthor(), getGenre,
//				dto.getQuantity(), dto.getYear(), dto.getEdition(), dto.getPublisher(), dto.isSaleIsActive(),
//				dto.getSaleDiscountRate(), dto.getBookPrice(), dto.getBookImage());
//
//		return bd.saveAndFlush(addedBook);
//
//	}
//
//	public Book updateBook(AddOrUpdateBookDTO dto, String id) throws BookNotFoundException, InvalidParameterException,
//			GenreNotFoundException, ISBNAlreadyExists, SynopsisInputException, SaleDiscountRateException {
//
//		logger.info("BookService.updateBook() invoked.");
//
//		try {
//			int bookId = Integer.parseInt(id);
//
//			if (!bd.findById(bookId).isPresent()) {
//
//				throw new BookNotFoundException("Book doesn't exist");
//			}
//
//			logger.debug("bd.findById(bookId).get() {}", bd.findById(bookId).get());
//
//			String bookIsbn = bd.findById(bookId).get().getISBN();
//
//			logger.debug("bd.findById(bookId).get().getISBN() {}", bd.findById(bookId).get().getISBN());
//			logger.debug("bookIsbn {}", bookIsbn);
//			logger.debug("dto.getISBN() {}", dto.getISBN());
//
//			if (bookIsbn.equals(dto.getISBN())) {
//
//				logger.debug("bookIsbn 1 {}", bookIsbn);
//				logger.debug("dto.getISBN 1 () {}", dto.getISBN());
//
//				vBUtil.validateBookInput(dto);
//
//				Book bookToUpdate = bd.findById(bookId).get();
//
//				Genre getGenre = gd.findById(dto.getGenre()).get();
//
//				bookToUpdate.setISBN(dto.getISBN());
//				bookToUpdate.setBookName(dto.getBookName());
//				bookToUpdate.setSynopsis(dto.getSynopsis());
//				bookToUpdate.setAuthor(dto.getAuthor());
//				bookToUpdate.setGenre(getGenre);
//				bookToUpdate.setQuantity(dto.getQuantity());
//				bookToUpdate.setYear(dto.getYear());
//				bookToUpdate.setEdition(dto.getEdition());
//				bookToUpdate.setPublisher(dto.getPublisher());
//				bookToUpdate.setSaleIsActive(dto.isSaleIsActive());
//				bookToUpdate.setSaleDiscountRate(dto.getSaleDiscountRate());
//				bookToUpdate.setBookPrice(dto.getBookPrice());
//				bookToUpdate.setBookImage(dto.getBookImage());
//
//				return bd.saveAndFlush(bookToUpdate);
//
//			} else {
//
//				if (bd.findByISBN(dto.getISBN()).isPresent()) {
//					throw new ISBNAlreadyExists("ISBN already used for another book");
//
//				} else {
//
//					vBUtil.validateBookInput(dto);
//
//					Book bookToUpdate = bd.findById(bookId).get();
//
//					Genre getGenre = gd.findById(dto.getGenre()).get();
//
//					bookToUpdate.setISBN(dto.getISBN());
//					bookToUpdate.setBookName(dto.getBookName());
//					bookToUpdate.setSynopsis(dto.getSynopsis());
//					bookToUpdate.setAuthor(dto.getAuthor());
//					bookToUpdate.setGenre(getGenre);
//					bookToUpdate.setQuantity(dto.getQuantity());
//					bookToUpdate.setYear(dto.getYear());
//					bookToUpdate.setEdition(dto.getEdition());
//					bookToUpdate.setPublisher(dto.getPublisher());
//					bookToUpdate.setSaleIsActive(dto.isSaleIsActive());
//					bookToUpdate.setSaleDiscountRate(dto.getSaleDiscountRate());
//					bookToUpdate.setBookPrice(dto.getBookPrice());
//					bookToUpdate.setBookImage(dto.getBookImage());
//
//					return bd.saveAndFlush(bookToUpdate);
//				}
//			}
//
//		} catch (NumberFormatException e) {
//			throw new InvalidParameterException("Id must be in Int format");
//		}
//	}

}
