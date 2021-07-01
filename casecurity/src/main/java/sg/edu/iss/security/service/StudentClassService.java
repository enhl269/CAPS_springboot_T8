package sg.edu.iss.security.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.security.domain.StudentClass;

public interface StudentClassService {

	StudentClass getStdClass(Long id);

	List<StudentClass> getStdClassByLecturer(Long id);

	List<StudentClass> getAllStdCLass();

	StudentClass getStdClassByStdClassId(Long id);

	void save(StudentClass stdclass);

	void delete(Long id);
	
	Page<StudentClass> getPageStudentClass(Pageable pageable);

	List<StudentClass> getStdClassByCourseId(long id);

	List<StudentClass> findStudentClassByStartDate(LocalDate date);

}
