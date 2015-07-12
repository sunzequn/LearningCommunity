package com.lst.lc.web.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	public UserController() {
		super();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "frontend/user/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "frontend/user/register";
	}

	@RequestMapping(value = "/login/{email}", method = RequestMethod.POST)
	public String login(@PathVariable String email, String password, Model model){
		User user = userDao.validateUser(email, password);
		return "frontend/index";
	}
}
