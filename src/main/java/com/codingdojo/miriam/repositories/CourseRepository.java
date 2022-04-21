package com.codingdojo.miriam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.miriam.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	//CRUD ------------------------------------------------------------------------------------
	
	//READ ALL
	List <Course> findAll();
	//READ ONE
	List<Course> findById(long id);
	//CREATE - UPDATE
	//Course save(Course newCourse);
}
