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
			Admin.setContactNumber("96325472");
			Admin.setRoles(Role.ADMIN.toString());
			us.registerDefaultUser(Admin);
			
			User Admin2 = new User();
			Admin2.setEmail("wankey@gmail.com");
			Admin2.setPassword("123456");
			Admin2.setFirstName("Wankey");
			Admin2.setLastName("Teo");
			Admin2.setContactNumber("96396872");
			Admin2.setRoles(Role.ADMIN.toString());
			us.registerDefaultUser(Admin2);
			
			//Creating Student
			User stu = new User();
			stu.setEmail("google@gmail.com");
			stu.setPassword("123456");
			stu.setFirstName("Larry");
			stu.setLastName("Page");
			stu.setContactNumber("86325472");
			stu.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu);
			
			User stu2 = new User();
			stu2.setEmail("zhangran@gmail.com");
			stu2.setPassword("123456");
			stu2.setFirstName("Zhang");
			stu2.setLastName("Ran");
			stu2.setContactNumber("86396872");
			stu2.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu2);
			
			User stu3 = new User();
			stu3.setEmail("johnson@gmail.com");
			stu3.setPassword("123456");
			stu3.setFirstName("Johnson");
			stu3.setLastName("Leow");
			stu3.setContactNumber("96081872");
			stu3.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu3);
			
			User stu4 = new User();
			stu4.setEmail("noel@gmail.com");
			stu4.setPassword("123456");
			stu4.setFirstName("Noel");
			stu4.setLastName("Wai");
			stu4.setContactNumber("86080072");
			stu4.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu4);
			
			User stu5 = new User();
			stu5.setEmail("xinye@gmail.com");
			stu5.setPassword("123456");
			stu5.setFirstName("Xinye");
			stu5.setLastName("Li");
			stu5.setContactNumber("86092072");
			stu5.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu5);
			
			User stu6 = new User();
			stu6.setEmail("estherNeoh@gmail.com");
			stu6.setPassword("123456");
			stu6.setFirstName("Esther");
			stu6.setLastName("Neoh");
			stu6.setContactNumber("85092091");
			stu6.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu6);
			
			User stu7 = new User();
			stu7.setEmail("sizheng@gmail.com");
			stu7.setPassword("123456");
			stu7.setFirstName("Si Zheng");
			stu7.setLastName("Tan");
			stu7.setContactNumber("95092081");
			stu7.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu7);
			
			User stu8 = new User();
			stu8.setEmail("khant@gmail.com");
			stu8.setPassword("123456");
			stu8.setFirstName("Nyer Maung");
			stu8.setLastName("Khant");
			stu8.setContactNumber("95882081");
			stu8.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu8);
			
			User stu9 = new User();
			stu9.setEmail("zhangyu@gmail.com");
			stu9.setPassword("123456");
			stu9.setFirstName("Zhang");
			stu9.setLastName("Yu");
			stu9.setContactNumber("85882081");
			stu9.setRoles(Role.STUDENT.toString());
			us.registerDefaultUser(stu9);
			
			
			//Creating Lecturer 
			User lect = new User();
			lect.setEmail("suriya@gmail.com");
			lect.setPassword("123456");
			lect.setFirstName("Suriya");
			lect.setLastName("Priya");
			lect.setContactNumber("85082046");
			lect.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect);
			
			User lect2 = new User();
			lect2.setEmail("issntt@gmail.com");
			lect2.setPassword("123456");
			lect2.setFirstName("Nguyen");
			lect2.setLastName("Tri Tin");
			lect2.setContactNumber("95089046");
			lect2.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect2);
			
			User lect3 = new User();
			lect3.setEmail("esther@gmail.com");
			lect3.setPassword("123456");
			lect3.setFirstName("Esther");
			lect3.setLastName("Tan");
			lect3.setContactNumber("85089146");
			lect3.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect3);
			
			User lect4 = new User();
			lect4.setEmail("Liufan@gmail.com");
			lect4.setPassword("123456");
			lect4.setFirstName("Liu");
			lect4.setLastName("Fan");
			lect4.setContactNumber("95349146");
			lect4.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect4);
			
			User lect5 = new User();
			lect5.setEmail("cherwah@gmail.com");
			lect5.setPassword("123456");
			lect5.setFirstName("Cher Wah");
			lect5.setLastName("Tan");
			lect5.setContactNumber("98348846");
			lect5.setRoles(Role.LECTURER.toString());
			us.registerDefaultUser(lect5);
			
			LocalDate dateStartSem1 = LocalDate.of(2021, 02, 01);
			LocalDate dateStartSem2 = LocalDate.of(2021, 06, 01);
			
			List<Student> s = srepo.findAllStudents();
			List<Lecturer> l = lrepo.findAllLecturer();
				
			List<Course> course = new ArrayList<>();
			course.add(new Course("C#","learn C#","OOP",8.0));
			course.add(new Course("Java","learn Java","OOP",8.0));
			course.add(new Course("Software Analysis","System Design","Design",4.0));
			course.add(new Course("Enterprise Solution","Enterprise Design","Design",4.0));
			course.add(new Course("Python","learn Python","Python",6.0));
			course.add(new Course("Machine Learning","learn ML","Python",4.0));
			course.add(new Course("Springboot","learn SP","Java",5.0));
			course.add(new Course("React JS","learn React","JavaScript",5.0));
			course.add(new Course("Ruby","learn Ruby","Ruby",4.0));
			course.add(new Course("Kotlin","learn Kotlin","Android",4.0));
			crepo.saveAll(course);
			
			StudentClass sc = new StudentClass(dateStartSem1, 30, course.get(0), l.get(1));
			screpo.save(sc);
			StudentClass sc1 = new StudentClass(dateStartSem1, 30, course.get(1), l.get(0));
			screpo.save(sc1);
			StudentClass sc2 = new StudentClass(dateStartSem1, 30, course.get(4), l.get(2));
			screpo.save(sc2);
			StudentClass sc3 = new StudentClass(dateStartSem1, 30, course.get(2), l.get(2));
			screpo.save(sc3);
			StudentClass sc4 = new StudentClass(dateStartSem1, 30, course.get(3), l.get(0));
			screpo.save(sc4);
			StudentClass sc5 = new StudentClass(dateStartSem2, 2, course.get(5), l.get(1));
			screpo.save(sc5);
			StudentClass sc6 = new StudentClass(dateStartSem2, 5, course.get(6), l.get(3));
			screpo.save(sc6);
			StudentClass sc7 = new StudentClass(dateStartSem2, 30, course.get(7), l.get(4));
			screpo.save(sc7);
			StudentClass sc8 = new StudentClass(dateStartSem2, 30, course.get(8), l.get(3));
			screpo.save(sc8);
			StudentClass sc9 = new StudentClass(dateStartSem2, 30, course.get(9), l.get(0));
			screpo.save(sc9);

			LecturerCanTeach LCT = new LecturerCanTeach(l.get(1), course.get(0));
			lctrepo.save(LCT);
			LecturerCanTeach LCT3 = new LecturerCanTeach(l.get(0), course.get(3));
			lctrepo.save(LCT3);
			LecturerCanTeach LCT4 = new LecturerCanTeach(l.get(2), course.get(2));
			lctrepo.save(LCT4);
			LecturerCanTeach LCT2 = new LecturerCanTeach(l.get(1), course.get(0));
			lctrepo.save(LCT2);
			LecturerCanTeach LCT5 = new LecturerCanTeach(l.get(2), course.get(4));
			lctrepo.save(LCT5);
			LecturerCanTeach LCT6 = new LecturerCanTeach(l.get(1), course.get(5));
			lctrepo.save(LCT6);
			LecturerCanTeach LCT7 = new LecturerCanTeach(l.get(3), course.get(6));
			lctrepo.save(LCT7);
			
			List<Enrollment> enrol = new ArrayList<>();
			
			enrol.add(new Enrollment(50f, "Pass", s.get(0), sc));		
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("zhangran@gmail.com"), sc));		
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("johnson@gmail.com"), sc));	
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("estherNeoh@gmail.com"), sc));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("sizheng@gmail.com"), sc));
			
			
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("zhangran@gmail.com"), sc2));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("noel@gmail.com"), sc2));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("johnson@gmail.com"), sc2));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("estherNeoh@gmail.com"), sc2));
			
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("johnson@gmail.com"), sc3));		
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("google@gmail.com"), sc3));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("noel@gmail.com"), sc3));	
			
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("johnson@gmail.com"), sc5));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("khant@gmail.com"), sc5));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("zhangyu@gmail.com"), sc5));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("xinye@gmail.com"), sc5));
			
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("sizheng@gmail.com"), sc6));	
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("zhangran@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("noel@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("johnson@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("estherNeoh@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("khant@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("zhangyu@gmail.com"), sc6));
			enrol.add(new Enrollment(50f, "Pass", srepo.findStudentByEmail("xinye@gmail.com"), sc6));
			
			erepo.saveAll(enrol);

		};
	}


}
