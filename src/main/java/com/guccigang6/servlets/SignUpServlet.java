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

@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
		dis.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = (String)req.getParameter("userName");
		String password = (String)req.getParameter("password");
		HttpSession session = req.getSession();
		UserAccount user = new UserAccount();
		user.setUserName(userName);
		user.setPassword(password);
		Connection conn = InfoKeeper.getConnection(req);
		try {
			DBUtils.insertUser(conn, userName, password);
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServletException();
		}
		InfoKeeper.storeLoginedUser(session, user);
		InfoKeeper.storeUserCookie(resp, user);
		
		resp.sendRedirect(req.getContextPath()+"/home");
	}
	
}
