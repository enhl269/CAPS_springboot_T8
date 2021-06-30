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

	@Modifying
	@Transactional
	@Query("update Enrollment e set e.status =:status where e.id=:id")
	public void saveStatus(String status, long id);
	
	@Query("SELECT e FROM Enrollment e JOIN e.student s "
			+ "where s.id=:id ")
	public List<Enrollment> findEnrollmentByStudentId(@Param("id") Long id);
	
	@Query("SELECT e FROM Enrollment e JOIN e.studentClass sc "
			+ "JOIN sc.course c "
			+ "JOIN e.student s "
			+ "where c.id=:cid "
			+ "AND s.id=:sid")
	public Enrollment findEnrollmentByCourseIdandStdID(@Param("cid") Long cid,@Param("sid") Long sid);
	
	
	
}
