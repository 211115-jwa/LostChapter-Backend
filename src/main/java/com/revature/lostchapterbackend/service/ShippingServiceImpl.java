package com.revature.lostchapterbackend.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.lostchapterbackend.dao.ShippingInfoDAO;
import com.revature.lostchapterbackend.exceptions.BookNotFoundException;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.model.ShippingInformation;

@Service
public class ShippingServiceImpl implements ShippingService {
	private Logger logger = LoggerFactory.getLogger(ShippingService.class);

	private ShippingInfoDAO shipDao;
	
	@Autowired
	public ShippingServiceImpl(ShippingInfoDAO shipDao) {
		this.shipDao = shipDao;
	}

	@Override
	@Transactional
	public List<ShippingInformation> getAllShippingInfos() {
		return shipDao.findAll();
	}

	@Override
	@Transactional
	public ShippingInformation getShippingInformationById(int id) {
		Optional<ShippingInformation> ship = shipDao.findById(id);
		if (ship.isPresent())
			return ship.get();
		else return null;
	}

	@Override
	@Transactional
	public int addShippingInformation(ShippingInformation newShippingInformation) {
		ShippingInformation ship = shipDao.save(newShippingInformation);
		if(ship != null)
		return ship.getShippingInfoId();
		else return 0;
	}

	@Override
	@Transactional
	public List<ShippingInformation> getShippingInformationByUser(int UserId) {
			List<ShippingInformation> ship = shipDao.findShippingInformationByUser(UserId);
			return ship;
	}

}