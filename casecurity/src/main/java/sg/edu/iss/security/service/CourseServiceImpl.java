package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.repo.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository repo;
	
//	public List<Course> listAll() {
//		return repo.findAll(null);
//	}
	
	@Override
	public void save(Course Course) {
		repo.save(Course);
	}
	
	@Override
	public Course get(Long id) {
		return repo.findById(id).get();
	}
	
	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Page<Course> getPageCourse(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public List<Course> getCourseStudentTakes(Long id) {
		return repo.findCourseByStudentId(id);
	}
	
	@Override
	public List<Course> getAllCourse() {
		return repo.findAll();
	}
	@Override
	public List<Course> getAllCourseTaughtByLectuer(long id){
		return repo.findCoursesTaughtByLecturerId(id);
	}
}
	

