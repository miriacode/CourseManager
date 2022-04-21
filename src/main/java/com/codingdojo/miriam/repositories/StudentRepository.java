package com.codingdojo.miriam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.miriam.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	
	//CRUD ------------------------------------------------------------------------------------
	
		//READ ALL
		List <Student> findAll();
		//READ ONE
		List<Student> findById(long id);
	
}
