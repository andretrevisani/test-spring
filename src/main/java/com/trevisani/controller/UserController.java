package com.trevisani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trevisani.bean.Task;
import com.trevisani.bean.User;
import com.trevisani.dao.TaskRepository;
import com.trevisani.dao.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
	public String home(Model model) {
		model.addAttribute("userData", new User());
		return "views/register";
	}
	
	@PostMapping("/register-action")
	public String loginAction(@ModelAttribute User userData, Model model) {
		if(userData.getLogin().length() == 0) {
			model.addAttribute("msg", "Login não pode ser nulo");
			model.addAttribute("userData", new User());
			return "views/register";
		} else if(userData.getPassword().length() < 5) {
			model.addAttribute("msg", "Password deve ter ao menos 5 letras");
			model.addAttribute("userData", new User());
			return "views/register";
		} else {
			userRepository.save(userData); // Tudo OK, salva o usuário
			return "redirect:/"; // redireciona o cara para a home ("/")
		}
	}
}