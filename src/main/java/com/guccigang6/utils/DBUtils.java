package com.guccigang6.utils;

import java.sql.*;
import java.util.*;

import com.guccigang6.beans.*;

public class DBUtils {
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException{
		String sqlQuery = "Select a.User_Name, a.Password from user_account a"
				+" where a.User_Name = ? and a.password = ?";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			return user;
		}
		return null;
	}
	
	public static UserAccount findUser(Connection conn, String userName) throws SQLException{
		String sqlQuery = "Select a.User_Name, a.Password from user_account a"
				+" where a.User_Name = ?";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String password = rs.getString("Password");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			return user;
		}
		return null;
	}
	
	public static void insertUser(Connection conn, String userName, String password) throws SQLException{
		String sqlQuery = "INSERT INTO user_account(user_name, password) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.executeUpdate();
	}
	
	public static void insertComment(Connection conn, CommentBean comment) throws SQLException{
		String sqlQuery = "INSERT INTO comment(value, thread_id, user_name, creation_date) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, comment.getValue());
		ps.setInt(2, comment.getThreadId());
		ps.setString(3, comment.getUserName());
		ps.setString(4, comment.getCreationDate());
		ps.executeUpdate();
	}
	
	public static int insertThread(Connection conn, ThreadBean thread) throws SQLException{
		String sqlQuery = "INSERT INTO thread(theme, value, user_name, modification_date, creation_date) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, thread.getTheme());
		ps.setString(2, thread.getValue());
		ps.setString(3, thread.getUserName());
		ps.setString(4, thread.getModificationDate());
		ps.setString(5, thread.getCreationDate());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int threadId = -1;
		if(rs.next()) {
			threadId = rs.getInt(1);
		}
		return threadId;
		
	}
	
	public static List<ThreadBean> getThreadList(Connection conn) throws SQLException{
		String sqlQuery = "Select thread_id, theme, user_name, value, creation_date, modification_date"
				+ " from thread order by creation_date desc";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ResultSet rs = ps.executeQuery();
		List<ThreadBean> list = new ArrayList<>();
		while(rs.next()) {
			int threadId = rs.getInt("thread_id");
			String username = rs.getString("user_name");
			String value = rs.getString("value");
			String theme = rs.getString("theme");
			String creationDate = rs.getString("creation_date");
			String modificationDate = rs.getString("modification_date");
			ThreadBean thread = new ThreadBean();
			thread.setThreadId(threadId);
			thread.setValue(value);
			thread.setTheme(theme);
			thread.setCreationDate(creationDate);
			thread.setModificationDate(modificationDate);
			thread.setUserName(username);
			list.add(thread);
		}
		return list;
	}
	
	public static List<CommentBean> getCommentList(Connection conn, int threadId) throws SQLException{
		String sqlQuery = "Select thread_id, user_name, value, creation_date"
				+ " from comment where thread_id = ? order by creation_date desc";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setInt(1, threadId);
		ResultSet rs = ps.executeQuery();
		List<CommentBean> list = new ArrayList<>();
		while(rs.next()) {
			String username = rs.getString("user_name");
			String value = rs.getString("value");
			String creationDate = rs.getString("creation_date");
			CommentBean comment = new CommentBean();
			comment.setThreadId(threadId);
			comment.setValue(value);
			comment.setCreationDate(creationDate);
			comment.setUserName(username);
			list.add(comment);
		}
		return list;
	}
	
	public static ThreadBean getThread(Connection conn, int id) throws SQLException{
		ThreadBean thread = new ThreadBean();
		String sqlQuery = "Select thread_id, user_name, value, theme, creation_date, modification_date"
				+ " from thread where thread_id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int threadId = rs.getInt("thread_id");
			String username = rs.getString("user_name");
			String value = rs.getString("value");
			String theme = rs.getString("theme");
			String creationDate = rs.getString("creation_date");
			String modificationDate = rs.getString("modification_date");
			thread.setThreadId(threadId);
			thread.setValue(value);
			thread.setTheme(theme);
			thread.setCreationDate(creationDate);
			thread.setModificationDate(modificationDate);
			thread.setUserName(username);
		}else {
			return null;
		}
		return thread;
	}
}
