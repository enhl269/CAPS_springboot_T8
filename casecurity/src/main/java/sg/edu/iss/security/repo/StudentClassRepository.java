package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.StudentClass;

public interface StudentClassRepository extends PagingAndSortingRepository<StudentClass, Long>, JpaRepository<StudentClass,Long> {
	
	@Query("SELECT sc FROM StudentClass sc JOIN sc.course c "
			+ "where c.id=:id")
	public List<StudentClass> findStudentClassByCourseId(@Param("id") Long id);
	
	@Query("SELECT sc FROM StudentClass sc JOIN sc.lecturer l "
			+ "where l.id=:id")
	public List<StudentClass> findStudentClassByLecturerId(@Param("id") Long id);

}
