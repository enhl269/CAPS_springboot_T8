package sg.edu.iss.security.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import sg.edu.iss.security.domain.StudentClass;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CasecurityApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentClassRepoTest {

  @Autowired
  private StudentClassRepository screpo;
  
  @Test
  @Order(1)
  public void TestFindById(){
    long id = 1;
    StudentClass sc = screpo.findById(id).get();
    assertEquals(id,sc.getId());
  }
  
  @Test
  @Order(2)
  public void TestFindbyLecturerId(){
	  long lecturer_id = 3;
	  StudentClass sc = screpo.findStudentClassByLecturerId(lecturer_id).get(0);
	  assertEquals(lecturer_id,sc.getLecturer().getId());
  }
  
}