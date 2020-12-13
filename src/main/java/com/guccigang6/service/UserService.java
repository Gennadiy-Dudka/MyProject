package com.guccigang6.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.guccigang6.beans.UserAccount;

public interface UserService {
	List<UserAccount> getUsers();
	void saveUser(UserAccount user);
	void storeLoginedUser(HttpSession session, UserAccount user);
	UserAccount getLoginedUser(HttpSession session);
	UserAccount getUser(String username);
	UserAccount getUser(String username, String password);
	void deleteUser(String username);
}
