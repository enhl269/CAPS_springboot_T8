package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.security.CasecurityApplication;

import sg.edu.iss.security.domain.Timetable;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TimetableRepoTest {

	@Autowired
	TimetableRepository ttbrepo;
	
	@Test
	public void testFindBetween() {
		LocalDate start = LocalDate.of(2021, 02, 01);
		LocalDate end = LocalDate.of(2021, 03, 01);
		List<Timetable> ttb = ttbrepo.findBetween(start, end);
		assertTrue(ttb.size() >= 0);
	}
	
}
