package com.guccigang6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guccigang6.beans.CommentBean;
import com.guccigang6.dao.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDao;
	
	@Override
	@Transactional
	public void saveComment(CommentBean comment) {
		commentDao.saveComment(comment);

	}

}
