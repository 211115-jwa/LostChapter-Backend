package com.revature.lostchapterbackend.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.revature.lostchapterbackend.model.CreditCardInfo;

@Repository
public interface CreditCardInfoDAO extends JpaRepository< CreditCardInfo, Integer>{

	public List<CreditCardInfo> findByUser(int userId);
}
