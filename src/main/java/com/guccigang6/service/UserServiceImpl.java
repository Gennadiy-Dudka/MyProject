package com.guccigang6.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guccigang6.beans.UserAccount;
import com.guccigang6.dao.UserAccountDAO;
import com.guccigang6.utils.InfoKeeper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserAccountDAO userDao;
	
	@Override
	@Transactional
	public List<UserAccount> getUsers() {
		return (List<UserAccount>) userDao.findAll();
	}
	
	@Override
	public void storeLoginedUser(HttpSession session, UserAccount user) {
		InfoKeeper.storeLoginedUser(session, user);
	}
	
	@Override
	public UserAccount getLoginedUser(HttpSession session) {
		return InfoKeeper.getLoginedUser(session);
	}

	@Override
	@Transactional
	public void saveUser(UserAccount user) {
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserAccount getUser(String username) {
		return userDao.findById(username).orElse(null);
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDao.deleteById(username);
	}
	
	@Override
	@Transactional
	public UserAccount getUser(String username, String password) {
		return userDao.findByUserNameAndPassword(username, password).orElse(null);
	}

}
