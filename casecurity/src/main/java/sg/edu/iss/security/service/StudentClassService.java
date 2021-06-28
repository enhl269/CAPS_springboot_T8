package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.StudentClassRepository;

@Service
public class StudentClassService {
	
	@Autowired
	private StudentClassRepository screpo;
	
	public StudentClass getStdClass(Long id)
	{
		//need a change to get current class
		return screpo.findStudentClassByCourseId(id).get(0);
	}
	
	public List<StudentClass> getStdClassByLecturer(Long id)
	{
		return screpo.findStudentClassByLecturerId(id);
	}
	
	public List<StudentClass> getAllStdCLass()
	{
		return screpo.findAll();
	}
	
	public StudentClass getStdClassByStdClassId(Long id)
	{
		return screpo.getById(id);
	}
	
	public void save(StudentClass stdclass) {
		screpo.save(stdclass);
	}
	
	public void delete(Long id) {
		screpo.deleteById(id);
	}

}
