package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Lecturer;

public interface LecturerRepository extends PagingAndSortingRepository<Course, Long>, JpaRepository<Course,Long> {
	@Query("SELECT s FROM Lecturer s where s.email=:email")
	public Lecturer findLecturerByEmail(@Param("email") String email);
	
	
	@Query("SELECT l FROM Lecturer l where l.roles = :roles")
	public List<Lecturer> findLecturerByRole(@Param("roles") String roles);
	
	@Query("SELECT s FROM Lecturer s")
	public List<Lecturer> findAllLecturer();

}

