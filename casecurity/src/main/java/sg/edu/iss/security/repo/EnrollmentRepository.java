package sg.edu.iss.security.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.security.domain.Enrollment;


public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long>, JpaRepository<Enrollment,Long> {
	
	
	@Query("SELECT e FROM Enrollment e JOIN e.studentClass sc "
			+ "JOIN sc.course c "
			+ "where c.id=:id ")
	public Enrollment findEnrollmentByCourseId(@Param("id") Long id);

	@Query("SELECT e FROM Enrollment e JOIN e.studentClass sc "
			+"where sc.id=:id ")
	public List<Enrollment> findEnrollmentByStudentClassId(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("update Enrollment e set e.score =:score where e.id=:id")
	public void saveScore(float score, long id);
	
}
