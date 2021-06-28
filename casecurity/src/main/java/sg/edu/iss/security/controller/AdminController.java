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
import sg.edu.iss.security.domain.CourseViewModel;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.UserRepository;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.LecturerService;
import sg.edu.iss.security.service.StudentClassService;
import sg.edu.iss.security.service.StudentService;
import sg.edu.iss.security.service.UserDetailsCustomService;
import sg.edu.iss.security.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private UserDetailsCustomService udcservice;

	@Autowired private StudentService sService;
	 
	 @Autowired private LecturerService lService;
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

	
	@GetMapping("/lecturers") 
	public String listLecturers(Model model) {
		List<User> listLecturers = udcservice.findAllLecturers("LECTURER");
		model.addAttribute("listUsers", listLecturers); 
		return "lecturers"; 
	}
	@GetMapping("/modifycourseallocation/{id}") 
	public String ModifyLecturerCourseAllocation(Model model, @PathVariable("id") Long id)
	{
		List<LecturerCanTeach> lct = lService.findAllLCT(id);
		List<CourseViewModel> a = new ArrayList<>(lct.size());
		for(int i=0;i< lct.size();i++)
		{
			a.add(new CourseViewModel());
			a.get(i).setId(lct.get(i).getCourse().getId());
			a.get(i).setName(lct.get(i).getCourse().getName());
			a.get(i).setDescription(lct.get(i).getCourse().getDescription());
			a.get(i).setType(lct.get(i).getCourse().getType());
			a.get(i).setCredits(lct.get(i).getCourse().getCredits());
			a.get(i).setLecturerCTId(lct.get(i).getId());
			a.get(i).setLecId(id);
		}
		
		List<CourseViewModel> Course = new ArrayList<>(a);
		model.addAttribute("Course",Course);
		
		return "coursestaught";
	}
	
	@GetMapping("/modifycourseallocation/delete/{lectct.id}/{lecid}")
	public String deleteCourseAllocation(Model model, @PathVariable("lectct.id") Long lctid,@PathVariable("lecid") Long lecid) {
		lService.delete(lctid);
		return "redirect:/modifycourseallocation/"+lecid;
	}
	
	@RequestMapping("/assignlecturer/{id}")
	public String assignNewLecturertocourse(Model model,@PathVariable("id")Long id) {
		
		LecturerCanTeach lct = new LecturerCanTeach();
		lct.setCourse(cService.get(id));
		model.addAttribute("lct", lct);
		return "new_lctassigned";
	}
	
	@RequestMapping(value = "/savelctassignment", method = RequestMethod.POST)
	public String saveLCTAssignment(@ModelAttribute("lct") LecturerCanTeach lct) {
		lService.save(lct);
		
		return "redirect:/lecturers";
	}
	
	
	@GetMapping("users/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("editUser", uService.get(id));
		return "editUser_form";
	}//
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
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
}


