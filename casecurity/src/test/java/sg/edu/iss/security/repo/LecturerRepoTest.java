package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.security.CasecurityApplication;

import sg.edu.iss.security.domain.Lecturer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class LecturerRepoTest {
	//
	@Autowired
	LecturerRepository lrepo;
	
	@Test
	@Order(1)
	public void testFindLecturerByEmail() {
		String email = "suriya@gmail.com";
		Lecturer saved = lrepo.findLecturerByEmail(email);
		assertTrue(saved != null);
	}
	
	@Test
	@Order(2)
	public void testFindLecturerByRole() {
		String role = "LECTURER";
		List<Lecturer> saved = lrepo.findLecturerByRole(role);
		assertTrue(saved.size() >= 0);
	}
	

}
