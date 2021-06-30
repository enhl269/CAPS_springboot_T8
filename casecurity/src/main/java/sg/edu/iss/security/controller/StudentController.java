package sg.edu.iss.security.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
//	@Autowired
//    private JavaMailSender javaMailSender;

	//all courses taken and grades
	@RequestMapping(value = "/stdnotcourses", method = RequestMethod.GET)
	public String listCourses(Model model, Principal p,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
		final int currentPage = page.orElse(1);
		final int pageSize = size.orElse(5);

		Page<Course> coursePage = service.getPageCourse(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("coursePage", coursePage);

		int totalPages = coursePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
			long id = urepo.findByEmail(p.getName()).getId();
			
			//List<Course> allstdcourses = service.getAllCourse();
			List<Course> stdcourses = service.getCourseStudentTakes(id);

			//List<CourseViewModel> a = new ArrayList<>(allstdcourses.size());
			List<CourseViewModel> b = new ArrayList<>(stdcourses.size());
			
			List<CourseViewModel> c = new ArrayList<>();
			List<StudentClass> studentClasses = scservice.getAllStdCLass();
			
			for(int i=0;i< stdcourses.size();i++)
			{
				b.add(new CourseViewModel());
				b.get(i).setId(stdcourses.get(i).getId());
				b.get(i).setName(stdcourses.get(i).getName());
				b.get(i).setDescription(stdcourses.get(i).getDescription());
				b.get(i).setType(stdcourses.get(i).getType());
				b.get(i).setCredits(stdcourses.get(i).getCredits());
			}
			
//			for(int i=0;i< allstdcourses.size();i++)
//			{
//				a.add(new CourseViewModel());
//				a.get(i).setId(allstdcourses.get(i).getId());
//				a.get(i).setName(allstdcourses.get(i).getName());
//				a.get(i).setDescription(allstdcourses.get(i).getDescription());
//				a.get(i).setType(allstdcourses.get(i).getType());
//				a.get(i).setCredits(allstdcourses.get(i).getCredits());
//			}
			
			for(StudentClass sc:studentClasses) {
				Course cStClass = service.get(sc.getCourse().getId());
				c.add(new CourseViewModel(cStClass.getId(), 
										  cStClass.getName(), 
										  cStClass.getDescription(),
										  cStClass.getType(),
										  cStClass.getCredits()));
			}
			List<CourseViewModel> Course = new ArrayList<>(c);
			
//			for(int i=0;i< Course.size();i++)
//			{
//				for(int j=0;j<b.size();j++)
//				{
//					if(Course.get(i).getId()==b.get(j).getId())
//					{ 
//						Course.remove(i);
//					}
//				}
//			}
			Course.removeAll(b);
			//Course.removeAll(c);
			
			model.addAttribute("Course",Course);
			
			return "coursesnottakenstd";
		}

	//to create and save hidden enrollment
	@RequestMapping("/enroll/{course.id}")
	public String showNewEnrollmentForm(Model model, Principal p, @PathVariable("course.id") Long cid) {
		
		long id = urepo.findByEmail(p.getName()).getId();

		StudentClass sc = scservice.getStdClass(cid);
		
		String page = "";
		Student s = stdservice.getStd(id);
		
		  if(sc.getEnrollmentList().size()< sc.getClassSize()) 
		  { 
			  Enrollment e= new Enrollment("Pending",s,sc);
			  eservice.save(e);
			  
			  final String username = "estherneohl269@gmail.com";
		        final String password = "FILLINYOURSELF";

		        Properties prop = new Properties();
		        prop.put("mail.smtp.host", "smtp.gmail.com");
		        prop.put("mail.smtp.port", "587");
		        prop.put("mail.smtp.auth", "true");
		        prop.put("mail.smtp.starttls.enable", "true"); //TLS
		        
		        Session session = Session.getInstance(prop,
		                new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(username, password);
		                    }
		                });

		        try {

		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress("testing@gmail.com"));
		            message.setRecipients(
		                    Message.RecipientType.TO,
		                    InternetAddress.parse("sizheng89@gmail.com,e0696698@u.nus.edu")
		            );
		            message.setSubject("You have been enrolled into class");
		            message.setText("Dear Student,"
		                    + "\n\n You have been enrolled into course!");

		            Transport.send(message);

		            System.out.println("Done");

		        } catch (MessagingException f) {
		            f.printStackTrace();
		        }	  
		  
		  page = "SuccessfulEnroll";
		  
		 } else { page = "class_full"; } 
		  
		  return page;
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
			float x = eservice.getScore(courseid,id);
			a.get(i).setScore(x);
			a.get(i).setGrade(a.get(i).getScore());
		}
		
		float sum = 0;
		float mc =0;
		for(int i=0;i< stdcourses.size();i++)
		{
			mc+= a.get(i).getCredits();
			a.get(i).setPrelimScore(a.get(i).getScore());
			sum += a.get(i).getCredits() * a.get(i).getPrelimScore();
		}
		float x = sum/mc;
		
		model.addAttribute("Course",a);
		model.addAttribute("cgpa",x);
		
		return "course_stdgrades";
	}

}

