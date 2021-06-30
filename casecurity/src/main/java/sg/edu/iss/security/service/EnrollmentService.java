package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.StudentClass;

public interface EnrollmentService {

	float getScore(Long cid, Long sid);

	void save(Enrollment e);

	void saveScore(float score, long id);

	void saveStatus(String status, long id);

	List<Enrollment> getByStudentClassId(long scId);

	Enrollment getEnrollmentById(long id);

	StudentClass getStudentClass(long id);

	List<Enrollment> getAllEnrollments();

	List<Enrollment> getEnrollmentByStudentId(long id);

	void delete(Long id);
	
	Page<Enrollment> getPageEnrollment(Pageable pageable);

}
