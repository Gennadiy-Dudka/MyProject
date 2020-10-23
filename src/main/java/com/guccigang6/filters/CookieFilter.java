package com.guccigang6.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.guccigang6.beans.UserAccount;
import com.guccigang6.utils.DBUtils;
import com.guccigang6.utils.InfoKeeper;

import javax.servlet.annotation.*;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter{
	public CookieFilter() {}
	public void init(FilterConfig fConfig) throws ServletException{}
	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		UserAccount userInSession = InfoKeeper.getLoginedUser(session);
		if(userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}
		Connection conn = InfoKeeper.getConnection(request);
		
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if(checked == null && conn != null) {
			String userName = InfoKeeper.getUserNameInCookie(req);
			try {
				UserAccount user = DBUtils.findUser(conn, userName);
				InfoKeeper.storeLoginedUser(session, user);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		chain.doFilter(request, response);
	}
}
