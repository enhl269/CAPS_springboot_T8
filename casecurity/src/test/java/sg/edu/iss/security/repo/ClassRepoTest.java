package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.security.CasecurityApplication;
import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassRepoTest {
	
	@Autowired 
	private CourseRepository crepo;
	
	@Autowired
	private UserService us;
		
	@Autowired
	private UserRepository urepo;
	
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
	
	@Test
	@Order(1)
	public void createUserTestAdmin()
	{
		User user = new User();
		user.setEmail("ravikumarr@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Ravi");
		user.setLastName("Kumar");
		user.setRoles(Role.ADMIN.toString());
		us.registerDefaultUser(user);
		
		User saved = urepo.findByEmail("ravikumarr@gmail.com");
		
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void createUserTestStudent()
	{
		User user = new User();
		user.setEmail("googlee@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Larry");
		user.setLastName("PAge");
		user.setRoles(Role.STUDENT.toString());
		us.registerDefaultUser(user);
		
		User saved = urepo.findByEmail("googlee@gmail.com");
		
		assertEquals("STUDENT",saved.getRoles());
		
	}
	
	@Test
	@Order(3)
	public void createUserTestLecturer()
	{
		User user = new User();
		user.setEmail("yahoo@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Steve");
		user.setLastName("Jobs");
		user.setRoles(Role.LECTURER.toString());
		us.registerDefaultUser(user);
		
		User saved = urepo.findByEmail("yahoo@gmail.com");
		
		assertEquals("LECTURER",saved.getRoles());
	}
	
	@Test
	@Order(4)
	public void findStudent()
	{
		Student s = srepo.findStudentByEmail("google@gmail.com");
		assertNotNull(s);
	}
	
	@Test
	@Order(4)
	public void createCourse() {
		
		LocalDate dateStart = LocalDate.of(2019, 12, 31);
		Student s = srepo.findStudentByEmail("google@gmail.com");
		Lecturer l = lrepo.findLecturerByEmail("yahoo@gmail.com");	
		Course c = new Course("C#","learn C#","OOP",8.0);
		crepo.save(c);
		StudentClass sc = new StudentClass(dateStart, 30, c, l);
		screpo.save(sc);
		LecturerCanTeach LCT = new LecturerCanTeach(l, c);
		lctrepo.save(LCT);
		Enrollment er = new Enrollment(50f,"Pass",s,sc);
		erepo.save(er);
		
		Course cfind = crepo.findCourseByStudentEmail("google@gmail.com").get(0);
		assertNotNull(cfind);
		
   }
	
	@Test
	@Order(5)
	public void createCourse1() {
		
		LocalDate dateStart = LocalDate.of(2019, 12, 31);
		Student s = srepo.findStudentByEmail("google@gmail.com");
		Lecturer l = lrepo.findLecturerByEmail("yahoo@gmail.com");	
		Course c = crepo.getById(3L);
		StudentClass sc = new StudentClass(dateStart, 30, c, l);
		screpo.save(sc);
		LecturerCanTeach LCT = new LecturerCanTeach(l, c);
		lctrepo.save(LCT);
		Enrollment er = new Enrollment(50f,"Pass",s,sc);
		erepo.save(er);
		
		Course cfind = crepo.findCourseByStudentEmail("google@gmail.com").get(0);
		assertNotNull(cfind);
		
   }
	@Test
	@Order(6)
	public void testFindStudentClassByCourseId() {
		long id = 3;
		StudentClass saved = screpo.findStudentClassByCourseId(id).get(0);
		assertTrue(!saved.equals(null));
	}
	
	@Test
	@Order(7)
	public void testFindStudentClassByLecturerId() {
		long id = 12;
		StudentClass saved = screpo.findStudentClassByLecturerId(id).get(0);
		assertTrue(!saved.equals(null));
	}
	}

