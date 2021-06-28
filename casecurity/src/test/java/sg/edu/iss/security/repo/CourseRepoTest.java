package sg.edu.iss.security.repo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.service.CourseService;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepoTest {

	@Autowired
	private CourseService co;
	
	@Autowired
	private CourseRepository crepo;
	
	@Test
	@Order(1)
	public void testCourseCreation2()
	{
		//Name, Description, Type, List<LecturerCanTeach>, List<StudentClass>
		List<LecturerCanTeach> lecturerCanTeach1 =new ArrayList<LecturerCanTeach>();
		List<StudentClass> studentClass1 =new ArrayList<StudentClass>();
	
		Course course2 = new Course("Java","Java Basics","Long", lecturerCanTeach1,studentClass1, null);
		Course saved = crepo.save(course2);
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testFindCourseByType()
	{
		Course c1 = crepo.findCoursesByType("Long").get(0);
		assertEquals("Long",c1.getType());
	}
	
	@Test
	@Order(3)
	public void testFindCourseByName()
	{
		// given
		String name = "Java";
		// when
		List<Course> saved = crepo.findCoursesByName(name);
		// then
		assertTrue(saved.size() > 0);
	}
	
	@Test
	@Order(4)
	public void TestUpdateCourse() {
		// given
		String n = "Java";
		Course given = crepo.findCoursesByName(n).get(0);
		// when
		given.setName("Python");
		Course saved = crepo.save(given);
		// then
		assertNotNull(saved);
	}

	@Test
	@Order(5)
	public void testListCourses() {
		// given
		List<Course> list = new ArrayList<Course>();
		// when
		list = (List<Course>) crepo.findAll();
		//then
		assertTrue(list.size() > 0);
	}

	@Test
	@Order(6)
	public void testDeleteCourse() {
		
		// given
		String fn = "Python";
		// when
		Course selected = crepo.findCoursesByName(fn).get(0);
		crepo.delete(selected);
		// then
		assertTrue(crepo.findCoursesByName(fn).size() == 0);
	}
	
	
}
