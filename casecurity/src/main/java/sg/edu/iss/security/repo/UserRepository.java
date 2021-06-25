package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;

//to change to User class to test it out whether it still persist to the database or not
//kiv
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	@Query("select u from User u where u.roles = ?1")
	public List<User> findByRoleType(String role);
	
	/*
	 * @Query("select u from User u where u.roles = Student") public List<Student>
	 * findStudent();
	 * 
	 * @Query("select u from User u where u.roles = Lecturer") public List<Lecturer>
	 * findLecturer();
	 */
	
	
	
	
	
}
