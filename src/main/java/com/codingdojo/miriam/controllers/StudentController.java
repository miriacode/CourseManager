package com.codingdojo.miriam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.codingdojo.miriam.models.Course;
import com.codingdojo.miriam.models.Student;
import com.codingdojo.miriam.models.User;
import com.codingdojo.miriam.services.AppService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private AppService servicio;
	
	
	@PostMapping("/create")
	//@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create_student(@Valid @ModelAttribute("student") Student newStudent, 
								 BindingResult result,
								 HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session"); //Usuario en sesión
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		if(result.hasErrors()) {
			//return "redirect:/projects/show/"+newStudent.getCourse().getId();
			return "redirect:/dashboard";
		} else {
			
			servicio.save_student(newStudent);//Guardando el proyecto
			
			//Many to One
			
			Course currentCourse = servicio.find_course(newStudent.getCourse().getId()); //ID de usuario en sesión 
			currentCourse.getStudents().add(newStudent);//Añade el nuevo curso a la lista de cursos del nuevo usuario
			servicio.save_course(currentCourse); //Lo actualiza 
			
			//return "redirect:/projects/show/"+currentCourse.getId();
			return "redirect:/dashboard";
			
		}
	}
	
	
	
}
	