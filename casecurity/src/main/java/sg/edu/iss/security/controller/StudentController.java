package sg.edu.iss.security.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.CourseGrades;
import sg.edu.iss.security.domain.CourseViewModel;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.UserRepository;
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
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
    private JavaMailSender javaMailSender;

	//all courses taken and grades
		@RequestMapping(value = "/stdnotcourses", method = RequestMethod.GET)
		//@ResponseBody
		public String listCourses(Model model, Principal p){
			long id = urepo.findByEmail(p.getName()).getId();
			
			List<Course> allstdcourses = service.getAllCourse();
			List<CourseViewModel> a = new ArrayList<>(allstdcourses.size());
			List<Course> stdcourses = service.getCourseStudentTakes(id);
			List<CourseViewModel> b = new ArrayList<>(stdcourses.size());
			for(int i=0;i< stdcourses.size();i++)
			{
				a.add(new CourseViewModel());
				a.get(i).setId(stdcourses.get(i).getId());
				a.get(i).setName(stdcourses.get(i).getName());
				a.get(i).setDescription(stdcourses.get(i).getDescription());
				a.get(i).setType(stdcourses.get(i).getType());
				a.get(i).setCredits(stdcourses.get(i).getCredits());
			}
			
			for(int i=0;i< stdcourses.size();i++)
			{
				b.add(new CourseViewModel());
				b.get(i).setId(stdcourses.get(i).getId());
				b.get(i).setName(stdcourses.get(i).getName());
				b.get(i).setDescription(stdcourses.get(i).getDescription());
				b.get(i).setType(stdcourses.get(i).getType());
				b.get(i).setCredits(stdcourses.get(i).getCredits());
			}
			
			List<CourseViewModel> Course = new ArrayList<>(b);
			Course.removeAll(b);
			
			model.addAttribute("Course",Course);
			
			return "coursesnottakenstd";
		}
	
	//to create hidden enrollment
	@RequestMapping("/enroll/{course.id}")
	public String showNewEnrollmentForm(Model model, Principal p, @PathVariable("course.id") Long cid) {
		
		long id = urepo.findByEmail(p.getName()).getId();
		
		List<Course> allstdcourses = service.getAllCourse();
		List<CourseViewModel> a = new ArrayList<>(allstdcourses.size());
		List<Course> stdcourses = service.getCourseStudentTakes(id);
		List<CourseViewModel> b = new ArrayList<>(stdcourses.size());
		for(int i=0;i< stdcourses.size();i++)
		{
			a.add(new CourseViewModel());
			a.get(i).setId(stdcourses.get(i).getId());
			a.get(i).setName(stdcourses.get(i).getName());
			a.get(i).setDescription(stdcourses.get(i).getDescription());
			a.get(i).setType(stdcourses.get(i).getType());
			a.get(i).setCredits(stdcourses.get(i).getCredits());
		}
		
		for(int i=0;i< stdcourses.size();i++)
		{
			b.add(new CourseViewModel());
			b.get(i).setId(stdcourses.get(i).getId());
			b.get(i).setName(stdcourses.get(i).getName());
			b.get(i).setDescription(stdcourses.get(i).getDescription());
			b.get(i).setType(stdcourses.get(i).getType());
			b.get(i).setCredits(stdcourses.get(i).getCredits());
		}
		
		List<CourseViewModel> Course = new ArrayList<>(b);
		Course.removeAll(b);
		
		StudentClass sc = scservice.getStdClass(cid);
		
		String page = "";
		
		if(sc.getEnrollmentList().size()< sc.getClassSize())
		{
			Enrollment e= new Enrollment();
			Student s = stdservice.getStd(id);
			e.setStudent(s);
			e.setStudentClass(sc);
			model.addAttribute("Enrollment", e);
			
			page = "redirect:/saveenroll";
			
		} else {
			page = "class_full";
		}
		return page;
		
	}
	//to save hidden enrollment
	@RequestMapping(value = "/saveenroll", method = RequestMethod.POST)
	public String saveEnrollment(@ModelAttribute("Enrollment") Enrollment e) {
		eservice.save(e);
	
		return "redirect:/email";
	}
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String sendEmail(Principal p,@ModelAttribute("Enrollment") Enrollment e) {
		
		Long id = urepo.findByEmail(p.getName()).getId();
		Student s = stdservice.getStd(id);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(s.getEmail());

        msg.setSubject("Enrollment is Successful");
        msg.setText("Hello " + s.getFirstName() + " your enrollment in " + e.getStudentClass().getCourse().getName()+ "is successful.");

        javaMailSender.send(msg);
        
        return "redirect:/";

    }
	
	//all courses taken and grades
	@RequestMapping(value = "/courseno", method = RequestMethod.GET)
	public String ListCourseByStudent(Model model,Principal p){
		long id = urepo.findByEmail(p.getName()).getId();
		List<Course> stdcourses = service.getCourseStudentTakes(id);
		List<CourseGrades> a = new ArrayList<>(stdcourses.size());
		for(int i=0;i< stdcourses.size();i++)
		{
			a.add(new CourseGrades());
			a.get(i).setId(stdcourses.get(i).getId());
			a.get(i).setName(stdcourses.get(i).getName());
			a.get(i).setDescription(stdcourses.get(i).getDescription());
			a.get(i).setType(stdcourses.get(i).getType());
			a.get(i).setCredits(stdcourses.get(i).getCredits());
			
			Long courseid = stdcourses.get(i).getId();
			float x = eservice.getScore(courseid);
			a.get(i).setScore(x);
			a.get(i).setGrade(a.get(i).getScore());
		}
		
		float sum = 0;
		float mc =0;
		for(int i=0;i< stdcourses.size();i++)
		{
			mc+= a.get(i).getCredits();
			a.get(i).setPrelimScore(a.get(i).getScore());
			sum += a.get(i).getCredits() * a.get(i).getPrelimScore();;
		}
		
		model.addAttribute("Course",a);
		
		return "course_stdgrades";
	}

		
}
