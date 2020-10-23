package com.guccigang6.servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.guccigang6.utils.*;
import com.guccigang6.beans.*;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public HomeServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = InfoKeeper.getConnection(request);
		 
        List<ThreadBean> list = null;
        try {
            list = DBUtils.getThreadList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("threadList", list);
		RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dis.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request,response);
	}
}
