package sg.edu.iss.security.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
