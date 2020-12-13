package com.guccigang6.dao;

import java.util.List;

import com.guccigang6.beans.ThreadBean;

public interface ThreadDAO {
	List<ThreadBean> getThreads();
	void saveThread(ThreadBean thread);
	ThreadBean getThread(int id);
	void deleteThread(int id);
}
