package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.repo.StudentClassRepository;

@Service
public class StudentClassServiceImp implements StudentClassService {
	
	@Autowired
	private StudentClassRepository screpo;
	
	@Override
	public StudentClass getStdClass(Long id)
	{
		//need a change to get current class
		return screpo.findStudentClassByCourseId(id).get(0);
	}
	
	@Override
	public List<StudentClass> getStdClassByLecturer(Long id)
	{
		return screpo.findStudentClassByLecturerId(id);
	}
	
	@Override
	public List<StudentClass> getAllStdCLass()
	{
		return screpo.findAll();
	}
	
	@Override
	public StudentClass getStdClassByStdClassId(Long id)
	{
		return screpo.getById(id);
	}
	
	@Override
	public void save(StudentClass stdclass) {
		screpo.save(stdclass);
	}
	
	@Override
	public void delete(Long id) {
		screpo.deleteById(id);
	}

}
