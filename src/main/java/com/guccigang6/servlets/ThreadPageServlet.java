package com.guccigang6.servlets;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.guccigang6.beans.*;
import com.guccigang6.utils.DBUtils;
import com.guccigang6.utils.InfoKeeper;

@WebServlet(urlPatterns= {"/threadPage"})
public class ThreadPageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = InfoKeeper.getConnection(req);
		int threadId = Integer.parseInt(req.getParameter("id"));
		
		ThreadBean thread = null;
		List<CommentBean> commentList = null;
		try {
			thread = DBUtils.getThread(conn, threadId);
			commentList = DBUtils.getCommentList(conn, threadId);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		req.setAttribute("thread", thread);
		req.setAttribute("comments", commentList);
		RequestDispatcher dis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/threadPageView.jsp");
		dis.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commentValue = req.getParameter("value");
		int threadId = Integer.parseInt(req.getParameter("id"));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		
		HttpSession session = req.getSession();
		UserAccount user = InfoKeeper.getLoginedUser(session);
		
		CommentBean comment = new CommentBean();
		comment.setValue(commentValue);
		comment.setUserName(user.getUserName());
		comment.setThreadId(threadId);
		comment.setCreationDate(date);
		Connection conn = InfoKeeper.getConnection(req);
		try {
			DBUtils.insertComment(conn, comment);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		resp.sendRedirect(req.getContextPath()+"/threadPage?id="+threadId);
	}
	
}
