package sg.edu.iss.security.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.UserRepository;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.StudentClassService;
import sg.edu.iss.security.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService uService;

	 
	@Autowired
	private StudentClassService scService;

	@Autowired
	private CourseService cService;

	@Autowired
	private EnrollmentService eService;
	
	@Autowired
	private UserRepository urepo;
	

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
	 
	

	@GetMapping("/courses/delete/{id}")
	public String deleteCourse(Model model, @PathVariable("id") Long id) {
		cService.delete(id);
		return "redirect:/courses";
	}
	@GetMapping("/admin/enrollment")
	//@ResponseBody
	public String showEnrollmentList(Model model, Principal p) {
		//how to regulate enrollment status
//		pending
//		confirmed
//		denied
		long adminId = urepo.findByEmail(p.getName()).getId();
		
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
		
		return "redirect:/admin/enrollment";
	}
	
	
	@RequestMapping(value = "/adminstudentClassList", method = RequestMethod.GET)
	//@ResponseBody
	public String ListAllStudentClass(Model model) {
		
		List<StudentClass> scList = scService.getAllStdCLass();
		List<StudentClassInfo> sciList = new ArrayList<>(scList.size());
		
		for(int i=0; i < scList.size(); i++) {
			sciList.add(new StudentClassInfo());
			sciList.get(i).setId(scList.get(i).getId());
			sciList.get(i).setCourseName(scList.get(i).getCourse().getName());
			sciList.get(i).setCourseId(scList.get(i).getCourse().getId());
			sciList.get(i).setStartdate(scList.get(i).getStartdate());
			sciList.get(i).setClassSize(scList.get(i).getClassSize());
			sciList.get(i).setEnrollmentSize(scList.get(i).getEnrollmentList().size());
			sciList.get(i).setLecturerName(scList.get(i).getLecturer().getFirstName()+scList.get(i).getLecturer().getLastName());
			sciList.get(i).setLecturerId(scList.get(i).getLecturer().getId());
		}
		
		model.addAttribute("studentclasses",sciList);
		//return sciList;
		return "adminall_studentclasses";
	}
	
	@RequestMapping(value = "/savestdclass", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("StdClass") StudentClass StdClass) {
		scService.save(StdClass);
		
		return "redirect:/adminstudentClassList";
	}
	
	@GetMapping("/adminstudentClassList/edit/{id}")
	public String showEditStudenClassForm(Model model,@PathVariable(name = "id") Long id) 
	{ 	  
	  StudentClass stdclass = scService.getStdClassByStdClassId(id); 
	  model.addAttribute("stdclass", stdclass);
	  
	  return "edit_stdclass"; }
	 
	
	@RequestMapping("/adminstudentClassList/delete/{id}")
	public String deleteStdClass(@PathVariable(name = "id") Long id) {
		scService.delete(id);
		
		return "redirect:/adminstudentClassList";

	}
//
}
