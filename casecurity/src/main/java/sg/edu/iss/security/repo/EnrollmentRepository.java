package sg.edu.iss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;


public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long>, JpaRepository<Enrollment,Long> {
	
	
	@Query("SELECT e FROM Enrollment e JOIN e.studentClass sc "
			+ "JOIN sc.course c "
			+ "where c.id=:id ")
	public Enrollment findEnrollmentByCourseId(@Param("id") Long id);
	
}
