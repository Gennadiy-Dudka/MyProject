package com.guccigang6.filters;

import java.sql.Connection;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.guccigang6.conn.*;
import com.guccigang6.utils.*;

@WebFilter(filterName="jdbcFilter", urlPatterns= {"/*"})
public class JDBCFilter implements Filter{

	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	private boolean needJDBC(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		if(pathInfo != null) {
			urlPattern = servletPath + "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		System.out.println(urlPattern);
		for(ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			System.out.println(mappings.toString());
			if(mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if(needJDBC(req)) {
			Connection conn = null;
			try {
				conn = ConnectionUtils.getConnection();
				conn.setAutoCommit(false);
				InfoKeeper.storeConnection(request, conn);
				chain.doFilter(request, response);
				conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				ConnectionUtils.rollbackQuietly(conn);
				throw new ServletException();
			}finally {
				ConnectionUtils.closeQuietly(conn);
			}
		} else {
			chain.doFilter(request, response);
		}
		
	}

}
