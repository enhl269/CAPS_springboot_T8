package sg.edu.iss.security;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.CourseRepository;
import sg.edu.iss.security.repo.EnrollmentRepository;
import sg.edu.iss.security.repo.LecturerCanTeachRepository;
import sg.edu.iss.security.repo.LecturerRepository;
import sg.edu.iss.security.repo.StudentClassRepository;
import sg.edu.iss.security.repo.StudentRepository;
import sg.edu.iss.security.service.UserService;






@SpringBootApplication
public class CasecurityApplication {
	
	@Autowired 
	private CourseRepository crepo;
	
	@Autowired
	private UserService us;

	
	@Autowired
	private StudentRepository srepo;
	
	@Autowired
	private LecturerRepository lrepo;
	
	@Autowired
	private StudentClassRepository screpo;
	
	@Autowired 
	private LecturerCanTeachRepository lctrepo;
	
	@Autowired
	private EnrollmentRepository erepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CasecurityApplication.class, args);
		
		
	}
	

	@Bean
	CommandLineRunner runner() {
		return args ->{
			

			//Creating Admin
			User Admin = new User();
			Admin.setEmail("ravikumar@gmail.com");
			Admin.setPassword("123456");
			Admin.setFirstName("Ravi");
			Admin.setLastName("Kumar");
			Admin.setRoles(Role.ADMIN.toString());
			us.registerDefaultUser(Admin);
			
			User Admin2 = new User();
			Admin2.setEmail("wankey@gmail.com");
			Admin2.setPassword("123456");
			Admin2.setFirstName("Wankey");
			Admin2.setLastName("Teo");
			Admin2.setRoles(Role.ADMIN.toString());
			us.registerDefaultUser(Admin2);
			
			//Creating Student
			User stu = new User();
			stu.setEmail("google@gmail.com");
			stu.setPassword("123456");
			stu.setFirstName("Larry");
			stu.setLastName("Page");
			stu.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu);
			
			User stu2 = new User();
			stu2.setEmail("zhangran@gmail.com");
			stu2.setPassword("123456");
			stu2.setFirstName("Zhang");
			stu2.setLastName("Ran");
			stu2.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu2);
			
			User stu3 = new User();
			stu3.setEmail("johnson@gmail.com");
			stu3.setPassword("123456");
			stu3.setFirstName("Johnson");
			stu3.setLastName("Leow");
			stu3.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu3);
			
			User stu4 = new User();
			stu4.setEmail("noel@gmail.com");
			stu4.setPassword("123456");
			stu4.setFirstName("Noel");
			stu4.setLastName("Wai");
			stu4.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu4);
			
			
			//Creating Lecturer 
			User lect = new User();
			lect.setEmail("suriya@gmail.com");
			lect.setPassword("123456");
			lect.setFirstName("Suriya");
			lect.setLastName("Priya");
			lect.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect);
			
			User lect2 = new User();
			lect2.setEmail("issntt@gmail.com");
			lect2.setPassword("123456");
			lect2.setFirstName("Nguyen");
			lect2.setLastName("Tri Tin");
			lect2.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect2);
			
			User lect3 = new User();
			lect3.setEmail("esther@gmail.com");
			lect3.setPassword("123456");
			lect3.setFirstName("Esther");
			lect3.setLastName("Tan");
			lect3.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect3);
			
			LocalDate dateStartSem1 = LocalDate.of(2021, 02, 01);
			
			Student s = srepo.findStudentByEmail("google@gmail.com");
			Lecturer l = lrepo.findLecturerByEmail("suriya@gmail.com");	
			Lecturer l2 = lrepo.findLecturerByEmail("issntt@gmail.com");
			Lecturer l3 = lrepo.findLecturerByEmail("esther@gmail.com");
			
			List<Course> course = new ArrayList<>();
			course.add(new Course("C#","learn C#","OOP",8.0));
			course.add(new Course("Java","learn Java","OOP",8.0));
			course.add(new Course("Software Analysis","System Design","Design",4.0));
			course.add(new Course("Enterprise Solution","Enterprise Design","Design",4.0));
			course.add(new Course("Python","learn Python","Python",6.0));
			crepo.saveAll(course);
			
			
			StudentClass sc = new StudentClass(dateStartSem1, 30, course.get(0),l2);
			screpo.save(sc);
			StudentClass sc2 = new StudentClass(dateStartSem1, 30, course.get(1),l);
			screpo.save(sc2);
			StudentClass sc3 = new StudentClass(dateStartSem1, 30, course.get(4),l3);
			screpo.save(sc3);
			
			LecturerCanTeach LCT = new LecturerCanTeach(l, course.get(1));
			lctrepo.save(LCT);
			LecturerCanTeach LCT2 = new LecturerCanTeach(l2, course.get(0));
			lctrepo.save(LCT2);
			
			LecturerCanTeach LCT3 = new LecturerCanTeach(l3, course.get(4));
			lctrepo.save(LCT3);
			
			Enrollment er = new Enrollment(50f,"Pass",s,sc);
			erepo.save(er);
			
			Enrollment er2 = new Enrollment(50f,"Pass",srepo.findStudentByEmail("zhangran@gmail.com"),sc2);
			erepo.save(er2);
			
			Enrollment er3 = new Enrollment(50f,"Pass",srepo.findStudentByEmail("johnson@gmail.com"),sc3);
			erepo.save(er3);

		};
	}


}
