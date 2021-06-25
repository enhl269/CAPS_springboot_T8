package sg.edu.iss.security.service;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.repo.EnrollmentRepository;

public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepository erepo;
	
	public float getScore(Long id) {
		
		Enrollment e = erepo.findEnrollmentByCourseId(id);
		return e.getScore();
	}
	
	public void save(Enrollment e) {
		erepo.save(e);
	}

}
