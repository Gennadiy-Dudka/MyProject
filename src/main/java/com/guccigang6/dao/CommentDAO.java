package com.guccigang6.dao;

import com.guccigang6.beans.CommentBean;

public interface CommentDAO {
	void saveComment(CommentBean comment);
	CommentBean getComment(int id);
	void deleteComment(int id);
}
