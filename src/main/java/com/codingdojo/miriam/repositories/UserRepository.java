package com.codingdojo.miriam.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.miriam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	//For LOGIN - REGISTER
	Optional<User> findByEmail(String email);
	
}
