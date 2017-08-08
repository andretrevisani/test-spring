package com.trevisani.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trevisani.bean.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findUserByLoginAndPassword(String login, String password);
}
