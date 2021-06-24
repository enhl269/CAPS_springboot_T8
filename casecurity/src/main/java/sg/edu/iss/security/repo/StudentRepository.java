package sg.edu.iss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;

public interface StudentRepository extends JpaRepository<User, Long>  {

	@Query("SELECT s FROM Student s where s.email=:email")
	public Student findStudentByEmail(@Param("email") String email);
}
