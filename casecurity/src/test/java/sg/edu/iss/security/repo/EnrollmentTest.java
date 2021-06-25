package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.security.CasecurityApplication;
import sg.edu.iss.security.domain.Enrollment;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class EnrollmentTest {
	@Autowired
	private EnrollmentRepository erepo;
	
	@Test
	public void testFindEnrollmentByLecturer(){
		long sc_id = 1;
		Enrollment e = erepo.findEnrollmentByStudentClassId(sc_id).get(0);
		assertEquals(sc_id,e.getStudentClass().getId());
	  }
	
	@Test
	public void testSaveScore() {
		long e_id =2;
		float score = 75;
		erepo.saveScore(score, e_id);
		Enrollment e = erepo.findById(e_id).get();
		assertEquals(score,e.getScore());
	}
}
