package com.trevisani.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trevisani.bean.Task;
import com.trevisani.bean.User;
import com.trevisani.dao.TaskRepository;
import com.trevisani.dao.UserRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/new-task")
	public String home(Model model) {
		model.addAttribute("taskData", new Task());
		return "views/new_task";
	}
	
	@PostMapping("/create-task-action")
	public String createTaskAction(Task taskData, Model model, HttpSession session) {
		if(taskData.getName().length() < 5) {
			model.addAttribute("msg", "Task name deve ter no minimo 5 characteres");
			model.addAttribute("taskData", new Task());
			return "views/new_task";
		} else {
			User loggedUser = (User) session.getAttribute("loggedUser");
			taskData.setUser(userRepository.findOne(loggedUser.getId()));
			Task t = taskRepository.save(taskData);
			loggedUser.getTasks().add(t);
			return "redirect:/user-home";
		}
	}
	
}