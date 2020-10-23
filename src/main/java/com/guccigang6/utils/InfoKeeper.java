package com.guccigang6.utils;

import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.http.*;

import com.guccigang6.beans.*;

public class InfoKeeper {
	public static final String ATTR_CONN = "Attribute_For_Connection";
	public static final String ATTR_USER_NAME = "Attribute_For_User_Name";
	public static final String ATTR_LOGINED_USER = "Attribute_For_Logined_User";
	
	public static void storeConnection(ServletRequest request, Connection c) {
		request.setAttribute(ATTR_CONN, c);
	}
	
	public static Connection getConnection(ServletRequest request) {
		return (Connection)request.getAttribute(ATTR_CONN);
	}
	
	public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}
	
	public static UserAccount getLoginedUser(HttpSession session) {
		UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
		return loginedUser;
	}
	
	public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
		Cookie cookieUserName = new Cookie(ATTR_USER_NAME,user.getUserName());
		cookieUserName.setMaxAge(24*60*60);
		response.addCookie(cookieUserName);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(ATTR_USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATTR_USER_NAME, null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
}
