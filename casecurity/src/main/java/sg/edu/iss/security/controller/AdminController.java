package sg.edu.iss.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.LecturerService;
import sg.edu.iss.security.service.StudentService;
import sg.edu.iss.security.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService uService;

	/*
	 * @Autowired private StudentService sService;
	 * 
	 * @Autowired private LecturerService lService;
	 */

	@Autowired
	private CourseService cService;

	@Autowired
	private EnrollmentService eService;

	/*
	 * @GetMapping("/users") public String listUsers(Model model) { List<User>
	 * listUsers = uService.listAll(); model.addAttribute("listUsers", listUsers);
	 * return "users"; }
	 */

	/*
	 * @GetMapping("/students") public String listStudents(Model model) {
	 * List<Student> listStudents = uService.listStudent();
	 * model.addAttribute("listStudents", listStudents); return "students"; }
	 */

	/*
	 * @GetMapping("/lecturers") public String listLecturers(Model model) {
	 * List<Lecturer> listLecturers = uService.listLecturer();
	 * model.addAttribute("listLecturers", listLecturers); return "lecturers"; }
	 */
	@GetMapping("users/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("editUser", uService.get(id));
		return "editUser_form";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Long id) {
		User user = uService.get(id);
		uService.delete(id);
		return "forward:/users";
	}

	@GetMapping("/courses/create")
	public String showNewCourseForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);

		return "new_course";
	}

	
	 @GetMapping("/courses/edit/{id}")
	 public String showCourseForm(Model model, @PathVariable("id") Long id) {
		 model.addAttribute("editCourse", cService.get(id));
		 return "editCourse_form"; }

	@GetMapping("/courses/delete/{id}")
	public String deleteCourse(Model model, @PathVariable("id") Long id) {
		Course course = cService.get(id);
		cService.delete(id);
		return "forward:/courses";
	}

}
