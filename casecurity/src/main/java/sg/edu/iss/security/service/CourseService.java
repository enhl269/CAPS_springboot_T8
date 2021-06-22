package sg.edu.iss.security.service;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.repo.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository repo;
	
//	public List<Course> listAll() {
//		return repo.findAll(null);
//	}
	
	public void save(Course Course) {
		repo.save(Course);
	}
	
	public Course get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<Course> getPageCourse(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
