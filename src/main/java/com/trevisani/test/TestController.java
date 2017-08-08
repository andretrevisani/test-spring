package com.trevisani.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevisani.bean.Task;
import com.trevisani.bean.User;
import com.trevisani.dao.TaskRepository;
import com.trevisani.dao.UserRepository;

@RestController
public class TestController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/create-fake-data")
	public String createFakeData() {
		User joaoUser = userRepository.save(new User("joao", "123"));
		User mariaUser = userRepository.save(new User("maria", "321"));
		
		Task t1 = taskRepository.save(new Task("task um do joao", joaoUser));
		Task t2 = taskRepository.save(new Task("task dois do joao", joaoUser));
		
		Task t3 = taskRepository.save(new Task("task um da maria", mariaUser));
		Task t4 = taskRepository.save(new Task("task dois da maria", mariaUser));
		
		return "fake data created!";
	}
}
