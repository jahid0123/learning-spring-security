package com.jmjbrothers.learn_spring_security.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmjbrothers.learn_spring_security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByUsername(String username);

}
