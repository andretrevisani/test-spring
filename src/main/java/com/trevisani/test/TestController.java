package com.trevisani.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevisani.bean.User;
import com.trevisani.dao.UserRepository;

@RestController
public class TestController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/create-fake-data")
	public String createFakeData() {
		userRepository.save(new User("joao", "123"));
		userRepository.save(new User("maria", "321"));
		
		return "fake data created!";
	}
}
