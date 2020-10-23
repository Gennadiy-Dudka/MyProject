package com.guccigang6.servlets;

import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


import com.guccigang6.beans.ThreadBean;
import com.guccigang6.beans.UserAccount;
import com.guccigang6.utils.DBUtils;
import com.guccigang6.utils.InfoKeeper;

import java.time.*;
import java.time.format.*;

@WebServlet(urlPatterns= {"/threadCreate"})
public class ThreadCreateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserAccount user = InfoKeeper.getLoginedUser(session);
		if(user != null) {
			RequestDispatcher dis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/threadCreateView.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String theme = req.getParameter("theme");
		String value = req.getParameter("value");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		
		HttpSession session = req.getSession();
		UserAccount user = InfoKeeper.getLoginedUser(session);
		
		ThreadBean thread = new ThreadBean();
		thread.setCreationDate(date);
		thread.setModificationDate(date);
		thread.setTheme(theme);
		thread.setUserName(user.getUserName());
		thread.setValue(value);
		int threadId = -1;
		Connection conn = InfoKeeper.getConnection(req);
		try {
			threadId = DBUtils.insertThread(conn, thread);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		resp.sendRedirect(req.getContextPath()+"/threadPage?id="+threadId);
		
	}
	
	
}
