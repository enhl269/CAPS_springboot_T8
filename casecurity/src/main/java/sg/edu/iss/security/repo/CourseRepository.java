package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Course;



public interface CourseRepository extends PagingAndSortingRepository<Course, Long>, JpaRepository<Course,Long>{
	
	@Query("SELECT c FROM Course c JOIN c.studentClass sc "
			+ "JOIN sc.enrollmentList el JOIN el.student stu "
			+ "where stu.email=:email")
	public List<Course> findCourseByStudentEmail(@Param("email") String email);
	
	@Query("SELECT c FROM Course c WHERE c.name = ?1")
	public List<Course> findCoursesByName(String name);
	
	@Query("SELECT c FROM Course c WHERE c.type = ?1")
	public List<Course> findCoursesByType(String type);
	
	@Query("SELECT c FROM Course c JOIN c.studentClass sc "
			+ "JOIN sc.enrollmentList el JOIN el.student stu "
			+ "where stu.id=:id")
	public List<Course> findCourseByStudentId(@Param("id") Long id);
	
	@Query("SELECT c FROM Course c JOIN c.lecturerCanTeach lct "
			+ "JOIN lct.lecturer lec  "
			+ "where lec.id=:id")
	public List<Course> findCoursesTaughtByLecturerId(@Param("id") Long id);
}
