package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.User;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
	
	@Query("SELECT c FROM Course c WHERE c.name = ?1")
	public List<Course> findCoursesByName(String name);
	
	@Query("SELECT c FROM Course c WHERE c.type = ?1")
	public List<Course> findCoursesByType(String type);
}
