package sg.edu.iss.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.CourseRepository;
import sg.edu.iss.security.repo.EnrollmentRepository;
import sg.edu.iss.security.repo.StudentClassRepository;
import sg.edu.iss.security.repo.StudentRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository repo;
	
	@Autowired
	private EnrollmentRepository erepo;
	
	@Autowired 
	private StudentRepository srepo;
	
	@Autowired 
	private StudentClassRepository screpo;
	
//	public List<Course> listAll() {
//		return repo.findAll(null);
//	}
	
	
	public void save(Course Course) {
		repo.save(Course);
	}
	
	public Course get(Long id) {
		return repo.findById(id).get();
	}
	
	/*
	 * public void update(Long id, Course course) { for(int i = 0; i<
	 * courses.size(); i++) { Course c = courses.get(i); if(c.getId() == id) {
	 * courses.set(i, course); return; } } }
	 */
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<Course> getPageCourse(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public List<Course> getCourseStudentTakes(Long id) {
		return repo.findCourseByStudentId(id);
	}
	
	public List<Course> getAllCourse() {
		return repo.findAll();
	}
	
	
		
		
}
	

