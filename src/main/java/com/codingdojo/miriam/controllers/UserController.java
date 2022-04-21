package com.codingdojo.miriam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.miriam.models.Course;
import com.codingdojo.miriam.models.LoginUser;
import com.codingdojo.miriam.models.User;
import com.codingdojo.miriam.services.AppService;

@Controller
public class UserController {
	
	@Autowired
	private AppService servicio;
	
	
	//LOGIN-REGISTER-PAGE ----------------------------------------------------------------------------------------------------------------
	@GetMapping("/")
	public String index(@ModelAttribute("newUser") User newUser,
						@ModelAttribute("newUserLogingIn") LoginUser newUserLogingIn) {
		/*model.addAttribute("nuevoUsuario", new User());
		model.addAttribute("nuevoLogin", new LoginUser());*/
		
		return "index.jsp";
	}
	
	//REGISTER ----------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
						   BindingResult result, Model model, HttpSession session) {
		
		servicio.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newUserLogingIn", new LoginUser());
			return "index.jsp";
		}
		
		session.setAttribute("user_session", newUser);
		return "redirect:/dashboard";
		
	}
	
	//LOG IN ----------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/login")
	public String login(@Valid@ModelAttribute("newUserLogingIn") LoginUser newUserLogingIn,
						BindingResult result, Model model, HttpSession session) {
							
		User user = servicio.login(newUserLogingIn, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("user_session", user);
		return "redirect:/dashboard";
							
	}
	
	//LOG OUT ---------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_session");
		return "redirect:/";
	}
	
	
	
	//DASHBOARD -------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model, @ModelAttribute("event") Course course) {
		
		//Checking the session
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		User myUser = servicio.find_user(currentUser.getId());
		List<Course> allCourses = servicio.find_all_courses();
		model.addAttribute("user", myUser);
		model.addAttribute("courses", allCourses);
		
		
		return "dashboard.jsp";
	}
	
}
