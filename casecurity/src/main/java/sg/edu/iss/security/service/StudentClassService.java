package sg.edu.iss.security.service;

import java.util.List;

import sg.edu.iss.security.domain.StudentClass;

public interface StudentClassService {

	StudentClass getStdClass(Long id);

	List<StudentClass> getStdClassByLecturer(Long id);

	List<StudentClass> getAllStdCLass();

	StudentClass getStdClassByStdClassId(Long id);

	void save(StudentClass stdclass);

	void delete(Long id);

}