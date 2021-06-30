package sg.edu.iss.security.repo;


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


import sg.edu.iss.security.domain.LecturerCanTeach;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TestLecturerCanTeachRepo {
	
	@Autowired
	LecturerCanTeachRepository lctrepo;
	
	@Test
	@Order(1)
	public void testFindById() {
		long id = 1;
		LecturerCanTeach lct = lctrepo.findById(id).get();
		assertTrue(!lct.equals(null));
	}

	@Test
	@Order(2)
	public void testFindAll() {
		List<LecturerCanTeach>lctAll = lctrepo.findAll();
		assertTrue(lctAll.size()>0);
	}
	
	@Test
	@Order(3)
	public void testFindLecturerCanTeachByLecturerId() {
		long id = 12;
		List<LecturerCanTeach> lct = lctrepo.findLecturerCanTeachByLecturerId(id);
		assertTrue(lct.size() > 0);
	}
}
