package sg.edu.iss.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.StudentClassService;
import sg.edu.iss.security.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private CourseService service;
	
	@Autowired
	private EnrollmentService eservice;
	
	@Autowired
	private StudentClassService scservice;
	
	@Autowired
	private StudentService stdservice;
	
	//all courses not taken
	@RequestMapping(value = "/stdnotcourses", method = RequestMethod.GET)
	public String listCourses(Model model, HttpSession session) {
		
		Long id = (Long) session.getAttribute("usession");
		List<Course> allcourses = service.getAllCourse();
		List<Course> stdcourses = service.getCourseStudentTakes(id);
		
		List<Course> Course = new ArrayList<>(allcourses);
		Course.removeAll(stdcourses);
		
		model.addAttribute("Course",Course);

		return "coursesnottakenstd";
	}
	//to create hidden enrollment
	@RequestMapping("/enroll/{course.id}")
	public String showNewEnrollmentForm(Model model, HttpSession session, @PathVariable("course.id") Long cid) {
		
		Long id = (Long) session.getAttribute("usession");
		List<Course> allcourses = service.getAllCourse();
		List<Course> stdcourses = service.getCourseStudentTakes(id);
		List<Course> Course = new ArrayList<>(allcourses);
		Course.removeAll(stdcourses);
		
		Enrollment e= new Enrollment();
		Student s = stdservice.getStd(id);
		e.setStudent(s);
		
		StudentClass sc = scservice.getStdClass(cid);
		e.setStudentClass(sc);
		model.addAttribute("Enrollment", e);
		return "redirect:/saveenroll";
	}
	//to save hidden enrollment
	@RequestMapping(value = "/saveenroll", method = RequestMethod.POST)
	public String saveEnrollment(@ModelAttribute("Enrollment") Enrollment e) {
		eservice.save(e);
	
		return "redirect:/";
	}
	
	//all courses taken and grades
		@RequestMapping(value = "/stdcourses", method = RequestMethod.GET)
		public String listGradedCourses(Model model, HttpSession session) {
			
			Long id = (Long) session.getAttribute("usession");
			List<Course> stdcourses = service.getCourseStudentTakes(id);
			
			for(Course c : stdcourses)
			{
				Long courseid = c.getId();
				float x = eservice.getScore(courseid);
				c.setScore(x);
			}
			List<Course> Course = new ArrayList<>(stdcourses);
			model.addAttribute("Course",Course);

			return "course_stdgrades";
		}

}
