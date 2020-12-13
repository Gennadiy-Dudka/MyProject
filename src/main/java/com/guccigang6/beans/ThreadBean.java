package com.guccigang6.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="thread")
public class ThreadBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="thread_id")
	private int threadId;
	
	@ManyToOne
	@JoinColumn(name="user_name")
	private UserAccount user;
	
	@Column(name="value")
	private String value;
	
	@Column(name="theme")
	private String theme;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	
	@OneToMany(mappedBy = "thread", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<CommentBean> comments;
	
	public ThreadBean() {}
	
	public String getFormatDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return creationDate.format(formatter);
	}
	
	public UserAccount getUser() {
		return user;
	}
	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Set<CommentBean> getComments() {
		return comments;
	}

	public void setComments(Set<CommentBean> comments) {
		this.comments = comments;
	}
	
}
