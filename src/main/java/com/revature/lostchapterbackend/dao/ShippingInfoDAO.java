package com.revature.lostchapterbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.model.ShippingInformation;
@Repository
public interface ShippingInfoDAO extends JpaRepository<ShippingInformation, Integer> {
	public List<ShippingInformation> findShippingInformationByUser(int userId);
}
