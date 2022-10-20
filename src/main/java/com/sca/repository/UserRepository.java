package com.sca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sca.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByFirstname(String firstname);
	List<User> findByEmail(String email);
	
    public User save(User user);
	
	

}
