package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {
	
	@Autowired
	private UserService us;
	
	@Autowired
	private UserRepository urepo;
	
	@Test
	@Order(1)
	public void createUserTestAdmin()
	{
		User user = new User();
		user.setEmail("ravikumarr@gmail.com");
		user.setPassword("123456");
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
		user.setPassword("123456");
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
	public void findRoleType()
	{
		User u = urepo.findByRoleType("Admin").get(0);
		assertEquals("ADMIN",u.getRoles());
	}
	
	@Test
	@Order(5)
	public void testFindByEmail() {
		User u = urepo.findByEmail("google@gmail.com");
		assertTrue(u != null);
	}
}
