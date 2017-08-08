package com.trevisani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trevisani.bean.User;
import com.trevisani.dao.UserRepository;

@Controller
@SessionAttributes("loggedUser") // cada pessoa logada vai ter seu user salvo na session, só precisamos adicionar na Model o usuário logado
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("loginData", new User()); // Adiciona um new User, para que a view possa popular os dados de login e password para o loginAction
		return "views/home";
	}
	
	@PostMapping("/loginAction")
	public String loginAction(@ModelAttribute User loginData, Model model) {
		List<User> userList = userRepository.findUserByLoginAndPassword(loginData.getLogin(), loginData.getPassword());
		if(userList.size() == 0) {
			// erro no login, usuário e senha não existem no banco, retorna para a home
			model.addAttribute("loginData", new User()); // Adiciona um new User, para que a view possa popular os dados de login e password para o loginAction
			model.addAttribute("msg", "Erro ao logar. Usuário e senha incorretos.");
			return "views/home";
		} else {
			// Tudo OK, adicionamos na sessão do usuário, o User dele (que veio do banco)
			// Para isso, adicionamos na Model o User que veio do banco, com o nome de "loggedUser", que é o nome do parâmetro que demos ali em cima, na criação da classe (SessionAttribute)
			// Dessa forma, sempre que fizermos model.getAttribute("loggedUser") vai vir o usuário da pessoa que logou
			model.addAttribute("loggedUser", userList.get(0));
			return "views/user_home";
		}
		
	}
}