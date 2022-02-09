package com.revature.lostchapterbackend.notfunctional;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.lostchapterbackend.dao.UserDAO;

@Profile("UserService-test")
@Configuration
public class UserDAOTestConfiguration {
	@Bean
	@Primary
	public UserDAO ud() {
		return Mockito.mock(UserDAO.class);
	}
}
