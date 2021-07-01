package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.exception.CustomException;
import sg.edu.iss.security.repo.EnrollmentRepository;

@Service
public class EnrollmentServiceImp implements EnrollmentService {
	
	@Autowired
	private EnrollmentRepository erepo;
	
	@Override
	public float getScore(Long cid, Long sid) {
		if(cid==null || sid ==null) throw new CustomException("Values is not valid");
		Enrollment e = erepo.findEnrollmentByCourseIdandStdID(cid,sid);
		return e.getScore();
	}
	
	@Override
	public void save(Enrollment e) {
		erepo.save(e);
	}
	
	@Override
	public void saveScore(float score, long id) {
		if(score==0) throw new CustomException("score is not valid");
		erepo.saveScore(score, id);
	}
	
	@Override
	public void saveStatus(String status, long id) {
		if(status.isEmpty()) throw new CustomException("Status is not valid");
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
		if(e==null) throw new CustomException("Enrollment Class is not available");
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
	
	@Override
	public Page<Enrollment> getPageEnrollment(Pageable pageable) {
		return erepo.findAll(pageable);
	}
}
