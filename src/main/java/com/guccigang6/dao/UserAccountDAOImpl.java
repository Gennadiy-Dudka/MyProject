package com.guccigang6.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.UserAccount;

@Repository
public class UserAccountDAOImpl implements UserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserAccount> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserAccount> q = session.createQuery("from UserAccount", UserAccount.class);
		return q.list();
	}

	@Override
	public void saveUser(UserAccount user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public UserAccount getUser(String userName) {
		Session session = sessionFactory.getCurrentSession();
		UserAccount user = session.get(UserAccount.class, userName);
		return user;
	}

	@Override
	public void deleteUser(String userName) {
		Session session = sessionFactory.getCurrentSession();
		UserAccount user = session.get(UserAccount.class, userName);
		session.remove(user);
	}

	@Override
	public UserAccount getUser(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserAccount> q = session.createQuery("from UserAccount where userName = :username and password = :password", UserAccount.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		return q.uniqueResult();
	}

}
