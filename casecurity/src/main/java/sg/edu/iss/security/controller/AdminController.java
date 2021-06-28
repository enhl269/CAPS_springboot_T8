package sg.edu.iss.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.security.domain.Course;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.StudentClassService;
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
	
	@Autowired
	private StudentClassService scService;

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
	}//
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam("id") Long id, User userDetail) {
		User user = uService.get(id);
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setEmail(userDetail.getEmail());
		uService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Long id) {
		uService.delete(id);
		return "redirect:/users";
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
	 
	 
		/*
		 * @RequestMapping(value = "/editCourse", method = RequestMethod.POST) public
		 * String updateCourse(@RequestParam("id") Long id, Course courseDetail) {
		 * 
		 * Course course = cService.get(id); course.setName(courseDetail.getName());
		 * course.setType(courseDetail.getType());
		 * course.setDescription(courseDetail.getDescription()); cService.save(course);
		 * 
		 * return "redirect:/courses"; }
		 */

	@GetMapping("/courses/delete/{id}")
	public String deleteCourse(Model model, @PathVariable("id") Long id) {
		cService.delete(id);
		return "redirect:/courses";
	}
	@GetMapping("/admin/enrollment/{adminId}")
	//@ResponseBody
	public String showEnrollmentList(Model model,@PathVariable("adminId") Long adminId) {
		//how to regulate enrollment status
//		pending
//		confirmed
//		denied
		List<EnrollmentInfo> eiList = new ArrayList<>();
		List<Course> cList =cService.getAllCourseByAdminId(adminId);
		
		for(Course course:cList) {
			StudentClass sc = scService.getStdClass(course.getId());
			List<Enrollment> eList = eService.getByStudentClassId(sc.getId());
			for(Enrollment e:eList) {
				eiList.add(
					new EnrollmentInfo(
							e.getId(),
							e.getStudentClass().getCourse().getName(),
							e.getStudentClass().getStartdate(),
							e.getStudent().getId(),
							e.getStudent().getFirstName(),
							e.getStatus()));
			}
		}
		model.addAttribute("enrollments",eiList);
		//return eiList;
		return "admin_enrollmentList";
		
	}
	
	@PostMapping("/admin/enrollment/{id}")
	public String updateStatus(@RequestParam("status") String status, @PathVariable("id") Long id) {
		eService.saveStatus(status, id);

		Long adminId = eService.getStudentClass(id).getCourse().getAdmin().getId();
		String redirectString = "redirect:/admin/enrollment/"+adminId;
		
		return redirectString;
	}
	
//
}
