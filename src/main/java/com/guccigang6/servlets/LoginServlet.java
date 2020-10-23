package com.guccigang6.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.guccigang6.beans.UserAccount;
import com.guccigang6.utils.DBUtils;
import com.guccigang6.utils.InfoKeeper;

@WebServlet(urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		dis.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;
		
		if(userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required userName and password";
		}else {
			Connection conn = InfoKeeper.getConnection(req);
			try {
				user = DBUtils.findUser(conn, userName, password);
				if(user==null) {
					hasError = true;
					errorString = "User Name or Password invalid";
				}
			} catch(SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		if(hasError) {
			user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			req.setAttribute("errorString", errorString);
			req.setAttribute("user", user);
			RequestDispatcher dis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dis.forward(req, resp);
		}else {
			HttpSession session = req.getSession();
			InfoKeeper.storeLoginedUser(session, user);
			InfoKeeper.storeUserCookie(resp, user);
			resp.sendRedirect(req.getContextPath()+"/home");
		}
	}
}
