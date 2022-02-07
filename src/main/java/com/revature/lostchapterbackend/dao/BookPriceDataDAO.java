package com.revature.lostchapterbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.BookPriceData;

@Repository
public interface BookPriceDataDAO extends JpaRepository<BookPriceData, Integer>{

}
