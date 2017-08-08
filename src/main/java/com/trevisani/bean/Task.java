package com.trevisani.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false) // habilita carregar do banco as tasks do usuário automaticamente
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Task() {
		
	}
	
	public Task(String name, User user) {
		this.name = name;
		this.user = user;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
