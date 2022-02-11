package com.revature.lostchapterbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.lostchapterbackend.LostChapterBackendApplication;
import com.revature.lostchapterbackend.dao.ReviewDAO;
import com.revature.lostchapterbackend.dao.ShippingInfoDAO;
import com.revature.lostchapterbackend.model.Review;
import com.revature.lostchapterbackend.service.ReviewService;
import com.revature.lostchapterbackend.service.ShippingService;

@SpringBootTest(classes=LostChapterBackendApplication.class)
public class ShippingServiceTest {
	
	@MockBean
	private ShippingInfoDAO shipDao;
	@Autowired
	private ShippingService shipServ;

}
