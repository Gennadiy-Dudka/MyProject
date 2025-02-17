package com.guccigang6.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guccigang6.beans.CommentBean;
import com.guccigang6.beans.ThreadBean;
import com.guccigang6.beans.UserAccount;
import com.guccigang6.dao.CommentDAO;
import com.guccigang6.dao.ThreadDAO;

@Service
public class ThreadServiceImpl implements ThreadService{
	
	@Autowired
	ThreadDAO threadDao;
	
	@Autowired
	CommentDAO commentDao;

	@Override
	@Transactional
	public Set<CommentBean> getComments(int threadId) {
		ThreadBean thread = threadDao.findById(threadId).orElse(null);
		return thread.getComments();
	}

	@Override
	@Transactional
	public List<ThreadBean> getThreads() {
		return (List<ThreadBean>) threadDao.findAll();
	}

	
	@Override
	@Transactional
	public void saveThread(ThreadBean thread, UserAccount user) {
		thread.setUser(user);
		thread.setCreationDate(LocalDateTime.now());
		threadDao.save(thread);
	}
	
	@Override
	@Transactional
	public ThreadBean getThread(int id) {
		return threadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveComment(CommentBean comment, int id, UserAccount user) {
		ThreadBean thread = threadDao.findById(id).orElse(null);
		comment.setThread(thread);
		comment.setUser(user);
		comment.setCreationDate(LocalDateTime.now());
		commentDao.save(comment);
	}
}
