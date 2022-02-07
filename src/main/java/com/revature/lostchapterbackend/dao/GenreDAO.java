package com.revature.lostchapterbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.Genre;
@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {

}
