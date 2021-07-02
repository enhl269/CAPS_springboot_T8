package sg.edu.iss.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.exception.CustomException;
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

	 
	@Autowired
	private StudentClassService scService;

	@Autowired
	private CourseService cService;

	@Autowired
	private EnrollmentService eService;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private LecturerService lService;
	

	@Autowired
	private StudentService stdService;
	
	@GetMapping("/adminview") 
	public String adminoverallview() {
		return "adminview"; 
	}
	
	@GetMapping("/lecturers") 
	public String listLecturers(Model model) {
		List<User> listLecturers = udcservice.findAllByType("LECTURER");
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
		if(a.isEmpty()) throw new CustomException("Lecturer has not been assigned to any classes");
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
		List<User> lecturers = uService.getLectures();
		List<LecturerCanTeach> lectCT = lService.findAllLCT(lct.getLecturer().getId());
		
		 for(int i = 0; i < lecturers.size(); i++) {
			 if(lecturers.get(i).getId().equals(lct.getLecturer().getId())) {
				 for(int j = 0; j < lectCT.size(); j++) {
					 if(lectCT.get(j).getLecturer().getId() == lct.getLecturer().getId() &&
							 lectCT.get(j).getCourse().getId() == lct.getCourse().getId()) {
						 lService.save(lct);
							return "redirect:/lecturers";
						 }
					 }
				  return "LectAssignError";		 
		}
			 }
		return "LectErrorPage";
	}
	
	@GetMapping("users/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("editUser", uService.get(id));
		return "editUser_form";
	}//
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam("id") Long id, @Valid User userDetail, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editUser_form";
		}
		User user = uService.get(id);
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setEmail(userDetail.getEmail());
		user.setContactNumber(userDetail.getContactNumber());
		uService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Long id) {
		uService.delete(id);
		return "redirect:/users";
	}
	
	@GetMapping("/students") 
	public String listStudents(Model model) {
		List<User> listStudents = udcservice.findAllByType("STUDENT");
		model.addAttribute("listUsers", listStudents); 
		return "students"; 
	}
	
	@GetMapping("/modifystudentenrollment/{id}")
	//@ResponseBody
	public String showStudentEnrollmentList(Model model,@PathVariable("id") Long id) {
		//how to regulate enrollment status
		//pending,confirmed,denied
		List<EnrollmentInfo> eiList = new ArrayList<>();
		List<Enrollment> eList = eService.getEnrollmentByStudentId(id);
		for(Enrollment e:eList) {
			eiList.add(new EnrollmentInfo(e.getId(),
										  e.getStudentClass().getCourse().getName(),
						                  e.getStudentClass().getStartdate(),
						                  e.getStudent().getId(),
						                  (e.getStudent().getFirstName() + " " + e.getStudent().getLastName()),
						                  e.getStatus()));
		}//
		List<String> status = new ArrayList<>();
		status.add("Reject");
		status.add("Confirm");
		model.addAttribute("listStatus",status);
		model.addAttribute("enrollments",eiList);
		return "admin_enrollmentList";
		
	}
	
	@GetMapping("/modifystudentenrollmentstdclass/{id}")
	public String showStudentEnrollmentListStdClass(Model model,@PathVariable("id") Long id) {
		List<EnrollmentInfo> eiList = new ArrayList<>();
		List<Enrollment> eList = eService.getByStudentClassId(id);
		for(Enrollment e:eList) {
			eiList.add(new EnrollmentInfo(e.getId(),
										  e.getStudentClass().getCourse().getName(),
						                  e.getStudentClass().getStartdate(),
						                  e.getStudent().getId(),
						                  (e.getStudent().getFirstName() + " " + e.getStudent().getLastName()),
						                  e.getStatus()));
		}
		List<String> status = new ArrayList<>();
		status.add("Reject");
		status.add("Confirm");
		model.addAttribute("listStatus",status);
		model.addAttribute("enrollments",eiList);
		return "admin_enrollmentList";
		
	}
	
	@GetMapping("/admin/enrollment")
	//@ResponseBody
	public String showEnrollmentList(Model model) {
		//how to regulate enrollment status
		//pending,confirmed,denied
		List<EnrollmentInfo> eiList = new ArrayList<>();
		List<Enrollment> eList = eService.getAllEnrollments();
		for(Enrollment e:eList) {
			eiList.add(new EnrollmentInfo(e.getId(),
										  e.getStudentClass().getCourse().getName(),
						                  e.getStudentClass().getStartdate(),
						                  e.getStudent().getId(),
						                  (e.getStudent().getFirstName() + " " + e.getStudent().getLastName()),
						                  e.getStatus()));
		}
		List<String> status = new ArrayList<>();
		status.add("Reject");
		status.add("Confirm");
		model.addAttribute("listStatus",status);
		model.addAttribute("enrollments",eiList);
		
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
		
		List<User> lecturers = uService.getLectures();
		List<LecturerCanTeach> lectCT = lService.findAllLCT(StdClass.getLecturer().getId());
		List<StudentClass> stdClassByDate = scService.getAllStdCLass();
		 for(int i = 0; i < lecturers.size(); i++) {
			 if(lecturers.get(i).getId().equals(StdClass.getLecturer().getId())) {
				 for(int j = 0; j < lectCT.size(); j++) {
					 if(lectCT.get(j).getLecturer().getId() == StdClass.getLecturer().getId() &&
							 lectCT.get(j).getCourse().getId() == StdClass.getCourse().getId()) {
						 for(int k = 0; k < stdClassByDate.size(); k++) {
							 if(stdClassByDate.get(k).getStartdate().equals(StdClass.getStartdate()) &&
									 stdClassByDate.get(k).getCourse().getId()==StdClass.getCourse().getId() &&
									 stdClassByDate.get(k).getLecturer().getId()==StdClass.getLecturer().getId()) {
								 return "class_duplicate";
						 }
						 
						 }
						 scService.save(StdClass); return
								 "redirect:/adminstudentClassList";
						 
						 }return "LectAssignError";		
					 }
		}//
			 }
		return "LectErrorPage";
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
	
	//all courses taken and grades
	@RequestMapping(value = "/adminenrollstdnotcourses/{studentid}", method = RequestMethod.GET)
	public String listCourses(Model model, @PathVariable("studentid") Long stdid){
		
		long id = stdid;
		List<Course> allstdcourses = cService.getAllCourse();
		List<Course> stdcourses = cService.getCourseStudentTakes(id);
		List<CourseViewModel> a = new ArrayList<>(allstdcourses.size());
		List<CourseViewModel> b = new ArrayList<>(stdcourses.size());
		for(int i=0;i< stdcourses.size();i++)
		{
			b.add(new CourseViewModel());
			b.get(i).setId(stdcourses.get(i).getId());
			b.get(i).setName(stdcourses.get(i).getName());
			b.get(i).setDescription(stdcourses.get(i).getDescription());
			b.get(i).setType(stdcourses.get(i).getType());
			b.get(i).setCredits(stdcourses.get(i).getCredits());
			b.get(i).setStdId(id);
		}
		
		for(int i=0;i< allstdcourses.size();i++)
		{
			a.add(new CourseViewModel());
			a.get(i).setId(allstdcourses.get(i).getId());
			a.get(i).setName(allstdcourses.get(i).getName());
			a.get(i).setDescription(allstdcourses.get(i).getDescription());
			a.get(i).setType(allstdcourses.get(i).getType());
			a.get(i).setCredits(allstdcourses.get(i).getCredits());
			a.get(i).setStdId(id);
		}
		
		List<CourseViewModel> Course = new ArrayList<>(a);
//		
//		for(int i=0;i< Course.size();i++)
//		{
//			for(int j=0;j<b.size();j++)
//			{
//				if(Course.get(i).getId()==b.get(j).getId())
//				{
//					Course.remove(i);
//				}
//			}
//		}
		
		Course.removeAll(b);
		model.addAttribute("Course",Course);
		
		return "adminenrollstudent";
	}
	
	//to create and save hidden enrollment
	@RequestMapping("/adminenroll/{course.id}/{student.id}")
	public String AdminEnrollStudentForm(Model model, @PathVariable("course.id") Long cid,@PathVariable("student.id") Long stdid) {
		
		long id = stdid;

		StudentClass sc = scService.getStdClass(cid);
		
		String page = "";
		Student s = stdService.getStd(id);
		
		  if(sc.getEnrollmentList().size()< sc.getClassSize()) 
		  { 
			  Enrollment e= new Enrollment("Pending",s,sc);
			  eService.save(e);
			  
		  page = "redirect:/students";
		  
		 } else { page = "class_full"; } 
		  
		  return page;
	}
	
	@RequestMapping("/admin/enrollment/delete/{id}")
	public String deleteStdEnrollment(@PathVariable(name = "id") Long id) {
		eService.delete(id);
		
		return "redirect:/admin/enrollment";

	}
	
}
