package com.trevisani.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trevisani.bean.Task;
import com.trevisani.bean.User;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	public List<Task> findByUser(User user);

}