package sg.edu.iss.security.controller;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.UserRepository;
import sg.edu.iss.security.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	UserService us;
	
	@Autowired
	UserRepository urepo;
	

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		List<String> roles = new ArrayList<>();
		for(Role role:Role.values())
			roles.add(role.toString());
		
		model.addAttribute("listRoles", roles);
		
		
		model.addAttribute("user", new User());
		
		
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(@ModelAttribute("user") User user, Model model) {
		User userFromDb = us.getUserByEmail(user.getEmail());
		if(userFromDb != null)
			return "email-duplicate";
		us.registerDefaultUser(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = us.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
// example of using the getSessionUserId to get the current user id 	
//	public String userSession(Model model, Principal p) {
//		model.addAttribute("username", us.getSessionUserId(p));
//		return "users";
//	}
	
	
//	@RequestMapping(path = "/login")
//	public String login(Model model, HttpSession session) {
//		User u = new User();
//		model.addAttribute("user", u);
//		session.setAttribute("usession", u.getId());
//		return "login";
//	}
//	
	@GetMapping("/landing_page")
	public String log(Model model, Principal p) {
		String role= urepo.findByEmail(p.getName()).getRoles();
		if(role.equalsIgnoreCase("STUDENT"))
			return "redirect:/courseno";
		else if(role.equalsIgnoreCase("LECTURER"))
			return "redirect:/studentClassList";
		else if(role.equalsIgnoreCase("ADMIN"))
			return "redirect:/adminview";
		else
			return "/index";
	}
	
	@GetMapping("/NotAuth")
	public String NotAuth()
	{
		return "NotAuth";
	}
	
//
//	@GetMapping("/landing_page")
//	@ResponseBody
//	public String landing(Model model, Principal p) {
//		
//		
//		
//		String page = "You will receive an email acknowledging that you have enrolled in this course";
//		return page;
//	}	

}
