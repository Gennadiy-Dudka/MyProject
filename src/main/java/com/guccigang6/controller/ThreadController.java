package com.guccigang6.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guccigang6.beans.CommentBean;
import com.guccigang6.beans.ThreadBean;
import com.guccigang6.beans.UserAccount;
import com.guccigang6.service.ThreadService;
import com.guccigang6.service.UserService;

@Controller
@RequestMapping("/thread")
public class ThreadController {
	@Autowired
	private ThreadService threadService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ModelAndView openThreadInfo(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("threadPageView");
		HttpSession session = req.getSession();
		ThreadBean thread = threadService.getThread(id);
		List<CommentBean> comments = thread.getComments()
				.stream().sorted((CommentBean c1, CommentBean c2) -> c2.getCreationDate().compareTo(c1.getCreationDate()))
				.collect(Collectors.toList());
		mv.addObject("user", userService.getLoginedUser(session));
		mv.addObject("thread", thread);
		mv.addObject("comments", comments);
		mv.addObject("comment", new CommentBean());
		return mv;
	}
	@GetMapping("/save")
	public ModelAndView openThreadCreation(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		UserAccount user = userService.getLoginedUser(session);
		if(user == null) {
			mv.setViewName("redirect:/logIn");
			return mv;
		}
		mv.addObject("user", userService.getLoginedUser(session));
		mv.addObject("thread", new ThreadBean());
		mv.setViewName("threadCreateView");
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView saveThread(@Valid @ModelAttribute("thread") ThreadBean thread, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		UserAccount user = userService.getLoginedUser(session);
		if(user == null) {
			mv.setViewName("redirect:/logIn");
			return mv;
		}
		threadService.saveThread(thread, user);
		mv.setViewName("redirect:/thread/" + thread.getThreadId());
		return mv;
	}
	
	@PostMapping("/saveComment/{id}")
	public ModelAndView saveComment(@Valid @ModelAttribute("comment")CommentBean comment, @PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = req.getSession();
		UserAccount user = userService.getLoginedUser(session);
		if(user == null) {
			mv.setViewName("redirect:/logIn");
			return mv;
		}
		threadService.saveComment(comment, id, user);
		mv.setViewName("redirect:/thread/" + id);
		return mv;
	}
}
