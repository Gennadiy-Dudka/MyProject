package com.guccigang6.servlets;

import java.io.IOException;
import javax.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.guccigang6.utils.InfoKeeper;

@WebServlet(urlPatterns= {"/logout"})
public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		InfoKeeper.storeLoginedUser(s, null);
		InfoKeeper.deleteUserCookie(resp);
		resp.sendRedirect(req.getContextPath()+"/home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
