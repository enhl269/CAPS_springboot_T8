package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.StudentClassRepository;

@Service
public class StudentClassService {
	
	@Autowired
	private StudentClassRepository screpo;
	
	public StudentClass getStdClass(Long id)
	{
		return screpo.findStudentClassByCourseId(id).get(0);
	}
	
	public List<StudentClass> getStdClassByLecturer(Long id)
	{
		return screpo.findStudentClassByLecturerId(id);
	}

}
