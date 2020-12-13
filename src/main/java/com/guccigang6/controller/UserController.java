package com.guccigang6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guccigang6.beans.UserAccount;
import com.guccigang6.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signUp")
	public ModelAndView openSignUp() {
		ModelAndView mv = new ModelAndView("signUpView");
		mv.addObject("user", new UserAccount());
		return mv;
	}
	@PostMapping("/signUp")
	public ModelAndView signUp(@Valid @ModelAttribute("user") UserAccount user, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		userService.saveUser(user);
		HttpSession session = req.getSession();
		userService.storeLoginedUser(session, user);
		return mv;
	}
	@GetMapping("/logIn")
	public ModelAndView openLogIn() {
		ModelAndView mv = new ModelAndView("loginView");
		mv.addObject("user", new UserAccount());
		return mv;
	}
	@PostMapping("/logIn")
	public ModelAndView logIn(@Valid @ModelAttribute("user") UserAccount user, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		HttpSession session = req.getSession();
		System.out.println(user.getUserName() + user.getPassword());
		user = userService.getUser(user.getUserName(), user.getPassword());
		userService.storeLoginedUser(session, user);
		return mv;
	}
	@RequestMapping("/logOut")
	public ModelAndView logOut(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		HttpSession session = req.getSession();
		userService.storeLoginedUser(session, null);
		return mv;
	}
}
