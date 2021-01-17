package com.guccigang6.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guccigang6.beans.UserAccount;

@Repository
public interface UserAccountDAO extends CrudRepository<UserAccount, String>{
	
	Optional<UserAccount> findByUserNameAndPassword(String username, String password);
}
