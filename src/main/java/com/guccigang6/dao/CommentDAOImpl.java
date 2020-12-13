package com.guccigang6.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.CommentBean;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveComment(CommentBean comment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
	}
	
	@Override
	public CommentBean getComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		CommentBean comment = session.get(CommentBean.class, id);
		return comment;
	}

	@Override
	public void deleteComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		CommentBean comment = session.load(CommentBean.class, id);
		session.delete(comment);
	}
}
