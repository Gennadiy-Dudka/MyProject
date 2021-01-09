package com.guccigang6.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.CommentBean;

@Repository
public interface CommentDAO extends CrudRepository<CommentBean, Integer>{
}
