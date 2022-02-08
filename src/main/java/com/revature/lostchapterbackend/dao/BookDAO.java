package com.revature.lostchapterbackend.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.Book;
@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
	public List<Book> findByGenre_Genre(String genre);
	//public List<Book> findBysaleIsActiveTrue();
	public List<Book> findByISBN(String ISBN);
	
}
