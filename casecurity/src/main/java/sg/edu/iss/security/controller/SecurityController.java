package sg.edu.iss.security.controller;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import javax.servlet.http.HttpSession;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	UserService us;
		

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		List<String> roles = new ArrayList<>();
		for(Role role:Role.values())
			roles.add(role.toString());
		
		model.addAttribute("listRoles", roles);

		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@GetMapping("/addedit")
	public String showAddEditForm(Model model) {
		
		List<String> roles = new ArrayList<>();
		for(Role role:Role.values())
			roles.add(role.toString());
		
		model.addAttribute("listRoles", roles);

		model.addAttribute("user", new User());
		
		return "addedit_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		User userFromDb = us.getUserByEmail(user.getEmail());
		if(userFromDb != null)
			return "email-duplicate";
		us.registerDefaultUser(user);
		
		return "register_success";
	}
	
	@PostMapping("/process_addedit")
	public String processUserEdit(@ModelAttribute("user")  User user, Model model) {
		
		User userFromDb = us.getUserByEmail(user.getEmail());
		if(userFromDb != null)
			return "email-duplicate";
		us.registerDefaultUser(user);
		
		return "redirect:/users";
	}

	@GetMapping("/users")
	public String listUsers(Model model,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
		
		final int currentPage = page.orElse(1);//current page 
		final int pageSize = size.orElse(10);//this page have how many data 
//orElse,this is optional attribute. 
		Page<User> userPage = us.getPageUser(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("userPage", userPage);
//addAttribute(name,value), name is the name of attribute 
		int totalPages = userPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		List<User> listUsers = us.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
// example of using the getSessionUserId to get the current user id 	
//	public String userSession(Model model, Principal p) {
//		model.addAttribute("username", us.getSessionUserId(p));
//		return "users";
//	}
	
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		User u = new User();
		model.addAttribute("user", u);
		session.setAttribute("usession", u.getId());
		return "login";
	}
	
	@GetMapping("/landing_page")
	public String log(Model model, Principal p) {
		String role= us.getUserByEmail(p.getName()).getRoles();
		if(role.equalsIgnoreCase("STUDENT"))
			return "redirect:/courseno";
		else if(role.equalsIgnoreCase("LECTURER"))
			return "redirect:/studentClassList";
		else if(role.equalsIgnoreCase("ADMIN"))
			return "redirect:/users";
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
