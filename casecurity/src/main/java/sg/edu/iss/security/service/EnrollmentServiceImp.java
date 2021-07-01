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
	public String getCGPAByStudent(Long sid) {
		//sid means studentId
		List<Enrollment> eList = erepo.findEnrollmentByStudentId(sid);
		float sum = 0;
		float mc =0;
		for(Enrollment e: eList) {
			float score = e.getScore();
			if(score>=0) {
				double credits = e.getStudentClass().getCourse().getCredits();
				float prelimscore;
				if(score>= 80) {prelimscore=5f;}
				else if(score>=75) {prelimscore=4.5f;}
				else if(score>=70) {prelimscore=4f;}
				else if(score>=65) {prelimscore=3.5f;}
				else if(score>=60) {prelimscore=3f;}
				else if(score>=55) {prelimscore=2.5f;}
				else if(score>=50) {prelimscore=2f;}
				else if(score>=45) {prelimscore=1.5f;}
				else if(score>=40) {prelimscore=1f;}
				else {prelimscore=0f;}
				sum += credits * prelimscore;
				mc += credits;
			}
		}
		float cgpa = sum/mc;	
		return String.valueOf(cgpa);
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
	
	@Override
	public String getStatusByCourseAndSt(long courseId, long stId) {
		Enrollment e = erepo.findEnrollmentByCourseIdandStdID(courseId,stId);
		return e.getStatus();
		
	}
	
}
