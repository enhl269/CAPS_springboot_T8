package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.EnrollmentRepository;

@Service
public class EnrollmentServiceImp implements EnrollmentService {
	
	@Autowired
	private EnrollmentRepository erepo;
	
	@Override
	public float getScore(Long cid, Long sid) {
		
		Enrollment e = erepo.findEnrollmentByCourseIdandStdID(cid,sid);
		return e.getScore();
	}
	
	@Override
	public void save(Enrollment e) {
		erepo.save(e);
	}
	
	@Override
	public void saveScore(float score, long id) {
		erepo.saveScore(score, id);
	}
	
	@Override
	public void saveStatus(String status, long id) {
		erepo.saveStatus(status, id);
	}
	
	@Override
	public List<Enrollment> getByStudentClassId(long scId){
		return erepo.findEnrollmentByStudentClassId(scId);
	}

	
	@Override
	public Enrollment getEnrollmentById(long id) {
		return erepo.findById(id).get();
	}
	
	@Override
	public StudentClass getStudentClass(long id) {
		Enrollment e = erepo.findById(id).get();
		return e.getStudentClass();
	}
	
	@Override
	public List<Enrollment> getAllEnrollments(){
		return erepo.findAll();
	}
	
	@Override
	public List<Enrollment> getEnrollmentByStudentId(long id){
		return erepo.findEnrollmentByStudentId(id);
	}
	
	@Override
	public void delete(Long id) {
		erepo.deleteById(id);
	}
	
}
