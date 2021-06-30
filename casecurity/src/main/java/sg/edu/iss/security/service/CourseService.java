package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.security.domain.Course;

public interface CourseService {

	void save(Course Course);

	Course get(Long id);

	void delete(Long id);

	Page<Course> getPageCourse(Pageable pageable);

	List<Course> getCourseStudentTakes(Long id);

	List<Course> getAllCourse();

	List<Course> getAllCourseTaughtByLectuer(long id);

}