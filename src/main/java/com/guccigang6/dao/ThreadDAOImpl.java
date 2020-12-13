package com.guccigang6.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.ThreadBean;

@Repository
public class ThreadDAOImpl implements ThreadDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ThreadBean> getThreads() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ThreadBean order by creationDate desc");
		return q.getResultList();
	}

	@Override
	public void saveThread(ThreadBean thread) {
		Session session = sessionFactory.getCurrentSession();
		session.save(thread);
	}

	@Override
	public ThreadBean getThread(int id) {
		Session session = sessionFactory.getCurrentSession();
		ThreadBean thread = session.get(ThreadBean.class, id);
		return thread;
	}

	@Override
	public void deleteThread(int id) {
		Session session = sessionFactory.getCurrentSession();
		ThreadBean thread = session.get(ThreadBean.class, id);
		session.delete(thread);
	}

}
