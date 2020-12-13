package com.guccigang6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guccigang6.service.ThreadService;
import com.guccigang6.service.UserService;

@Controller
public class HomeController {
	@Autowired
	ThreadService threadService;
	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public ModelAndView openHome(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("homeView");
		HttpSession session = req.getSession();
		mv.addObject("user", userService.getLoginedUser(session));
		mv.addObject("threadList", threadService.getThreads());
		return mv;
	}
}
