package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.repo.EnrollmentRepository;
@Service
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
	
	public void saveScore(float score, long id) {
		erepo.saveScore(score, id);
	}
	
	public List<Enrollment> getByStudentClassId(long scId){
		return erepo.findEnrollmentByStudentClassId(scId);
	}

}
