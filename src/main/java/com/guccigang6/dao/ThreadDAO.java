package com.guccigang6.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.ThreadBean;

@Repository
public interface ThreadDAO extends CrudRepository<ThreadBean, Integer>{
}
