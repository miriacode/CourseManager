package com.codingdojo.miriam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.miriam.models.Course;
import com.codingdojo.miriam.models.Student;
import com.codingdojo.miriam.models.User;
import com.codingdojo.miriam.services.AppService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/new")
	//@RequestMapping(value="/new", method=RequestMethod.GET)
	public String new_course(@ModelAttribute("course") Course course, HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		return "new_course.jsp";
		
	}
	
	@PostMapping("/create")
	//@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create_course(@Valid @ModelAttribute("course") Course course, 
								 BindingResult result,
								 HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		if(result.hasErrors()) {
			return "new_course.jsp";
		} else {
			
			Course newCourse = servicio.save_course(course);//Guardando el proyecto
			
			//Many to One
			User myUser = servicio.find_user(currentUser.getId()); //ID de usuario en sesión 
			myUser.getCourses().add(newCourse);//Añade el nuevo curso a la lista de cursos del nuevo usuario
			servicio.save_user(myUser); //Lo actualiza 
			
			return "redirect:/dashboard";
			
		}
	}
	
	
	//UPDATE 
	@GetMapping("/edit/{id}")
	public String edit_course(@PathVariable("id") Long id,
							   @ModelAttribute("course") Course course,
							   HttpSession session,
							   Model model) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		Course course_edit = servicio.find_course(id);
		model.addAttribute("course", course_edit);
		
		
		return "edit_course.jsp";
	}
	
	
	//UPDATE
	@PutMapping("/update")
	//@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String update_course(@Valid @ModelAttribute("course") Course courseForm,
								 BindingResult result) {
		
		if(result.hasErrors()) {
			return "edit_course.jsp";
		} else {
			//SUS
			//Recuperar
			//Course thisCourse = servicio.find_course(courseForm.getId());
			//courseForm.setUsers(thisCourse.getUsers());
			
			
			servicio.save_course(courseForm);

			return "redirect:/dashboard";
		}
		
	}
	
//	
//	//JOIN PROJECT
//	@GetMapping("/join/{project_id}")
//	public String join_project(@PathVariable("project_id") Long project_id, HttpSession session) {
//		/*REVISAMOS SESION*/
//		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
//		
//		if(currentUser == null) {
//			return "redirect:/";
//		}
//		/*REVISAMOS SESION*/
//		
//		servicio.save_project_user(currentUser.getId(), project_id);
//		
//		return "redirect:/dashboard";
//	}
//	
//	
//	//LEAVE PROJECT
//	@GetMapping("/leave/{project_id}")
//	public String leave_project(@PathVariable("project_id") Long project_id, HttpSession session) {
//		/*REVISAMOS SESION*/
//		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
//		
//		if(currentUser == null) {
//			return "redirect:/";
//		}
//		/*REVISAMOS SESION*/
//		
//		servicio.remove_project_user(currentUser.getId(), project_id);
//		
//		return "redirect:/dashboard";
//	}
//	
//	
	//READ ONE
	@GetMapping("/{course_id}")
	public String show_project(@PathVariable("course_id") Long course_id, HttpSession session, Model model,  
			                  @ModelAttribute("student") Student student) {
		
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		Course thisCourse = servicio.find_course(course_id);
		model.addAttribute("course", thisCourse);
		
		//OJOOOOOOOOOOO
		//model.addAttribute("student", new Student()); 
		
		return "show_course.jsp";		
	}
	
	
	//DELETE
	@DeleteMapping("/delete/{course_id}")
	public String delete_project(@PathVariable("course_id") Long course_id, HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		servicio.delete_course(course_id);
		
		return "redirect:/dashboard";
	}
	
	
}
	