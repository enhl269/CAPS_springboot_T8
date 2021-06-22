//package sg.edu.iss.security.repo;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import sg.edu.iss.security.CasecurityApplication;
//import sg.edu.iss.security.domain.Role;
//import sg.edu.iss.security.domain.User;
//import sg.edu.iss.security.domain.Users;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = CasecurityApplication.class)
//@TestMethodOrder(OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UserRepoTest {
//	
//	@Autowired
//	private UserRepository urepo;
//	
////	@Test
////	@Order(1)
////	public void createUserTest()
////	{
////		Users user = new Users();
////		user.setEmail("ravikumar@gmail.com");
////		user.setPassword("ravi2020");
////		user.setFirstName("Ravi");
////		user.setLastName("Kumar");
////		user.setRoles(Role.ADMIN.toString());
////		
////		Users saved = urepo.save(user);
////		assertNotNull(saved);
////	}
//}
