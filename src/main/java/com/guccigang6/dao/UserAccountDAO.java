package com.guccigang6.dao;

import java.util.List;

import com.guccigang6.beans.UserAccount;

public interface UserAccountDAO {
	List<UserAccount> getUsers();
	void saveUser(UserAccount user);
	UserAccount getUser(String username);
	void deleteUser(String username);
	UserAccount getUser(String username, String password);
}
