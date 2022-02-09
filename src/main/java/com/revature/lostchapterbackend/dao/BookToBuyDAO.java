package com.revature.lostchapterbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.BookToBuy;
@Repository
public interface BookToBuyDAO extends JpaRepository<BookToBuy, Integer>{

}
