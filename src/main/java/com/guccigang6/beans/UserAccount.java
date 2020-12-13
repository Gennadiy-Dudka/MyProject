package com.guccigang6.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class UserAccount {
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<ThreadBean> threads;
	
	@OneToMany(mappedBy="user")
	private List<CommentBean> comments;
	
	public List<ThreadBean> getThreads() {
		return threads;
	}

	public void setThreads(List<ThreadBean> threads) {
		this.threads = threads;
	}

	public List<CommentBean> getComments() {
		return comments;
	}

	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}

	public UserAccount() {}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
