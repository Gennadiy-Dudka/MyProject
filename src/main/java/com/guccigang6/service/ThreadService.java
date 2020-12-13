package com.guccigang6.service;

import java.util.List;
import java.util.Set;

import com.guccigang6.beans.CommentBean;
import com.guccigang6.beans.ThreadBean;
import com.guccigang6.beans.UserAccount;

public interface ThreadService {
	Set<CommentBean> getComments(int threadId);
	List<ThreadBean> getThreads();
	void saveThread(ThreadBean thread, UserAccount user);
	ThreadBean getThread(int id);
	void saveComment(CommentBean comment, int id, UserAccount user);
}
