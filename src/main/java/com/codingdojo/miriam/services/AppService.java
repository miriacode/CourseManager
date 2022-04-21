package com.codingdojo.miriam.services;


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.miriam.models.Course;
import com.codingdojo.miriam.models.LoginUser;
import com.codingdojo.miriam.models.Student;
import com.codingdojo.miriam.models.User;
import com.codingdojo.miriam.repositories.CourseRepository;
import com.codingdojo.miriam.repositories.StudentRepository;
import com.codingdojo.miriam.repositories.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private UserRepository repository_user;
	
	@Autowired
	private CourseRepository repository_course;
	
	@Autowired
	private StudentRepository repository_student;
	
	//REGISTER---------------------------------------------------------------------------------------------------------------------------
	public User register(User newUser, BindingResult result) {
		
		String newEmail = newUser.getEmail();
		
		//Check if the email is already in the database
		if(repository_user.findByEmail(newEmail).isPresent()) {
			result.rejectValue("email", "Unique", "The email has been registered previously.");
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirm()) ) {
			result.rejectValue("confirm", "Matches", "Passwords don't match.");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			//Encrypting password
			String password_encrypt = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(password_encrypt);
			//Save User
			return repository_user.save(newUser);
		}
		
	}
	
	
	//LOGIN---------------------------------------------------------------------------------------------------------------------------
	public User login(LoginUser newUserLogingIn, BindingResult result) {
		
		if(result.hasErrors()) {
			return null;
		}
		
		//Search the email of that user
		Optional<User> possibleUser = repository_user.findByEmail(newUserLogingIn.getEmail());
		if(!possibleUser.isPresent()) {
			result.rejectValue("email", "Unique", "The provided email doesn't exist.");
			return null;
		}
		
		User user_login = possibleUser.get();
		
		//Comparing bcrypted password
		if(! BCrypt.checkpw(newUserLogingIn.getPassword(), user_login.getPassword()) ) {
			result.rejectValue("password", "Matches", "Invalid password.");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return user_login; 
		}
		
		
	}
	
	//DASHBOARD ------------------------------------------------------------------------------------------------------------------------
	
		//USER -- CREATE
		public User save_user(User updatedUser) {
			return repository_user.save(updatedUser);
		}
		
		//USER -- READ ONE
		public User find_user(Long id) {
			Optional<User> optionalUser = repository_user.findById(id);
			if(optionalUser.isPresent()) {
				return optionalUser.get();
			} else {
				return null;
			}
		}
		
		
		

		//COURSE -- CREATE & UPDATE
		public Course save_course(Course newCourse) {	
			return repository_course.save(newCourse);
		}
		
		//COURSE -- READ ONE
		public Course find_course(Long id) {
			Optional<Course> optionalCourse = repository_course.findById(id);
			if(optionalCourse.isPresent()) {
				return optionalCourse.get();
			} else {
				return null;
			}
		}
		
		//COURSE -- READ ALL
			public List<Course> find_all_courses() {
				return repository_course.findAll();
			}
		
		//COURSE - DELETE
		public void delete_course(Long id) {
			repository_course.deleteById(id);
		}
		
		
		
		
		//STUDENT -- READ ALL
		public List<Student> find_all_students() {
			return repository_student.findAll();
		}
		
		
		//STUDENT -- CREATE & UPDATE
		public Student save_student(Student newStudent) {	
			return repository_student.save(newStudent);
		}
				
		//STUDENT-- READ ONE
		public Student find_student(Long id) {
		Optional<Student> optionalStudent = repository_student.findById(id);
			if(optionalStudent.isPresent()) {
				return optionalStudent.get();
			} else {
				return null;
			}
		}
	
	
}
