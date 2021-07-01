package sg.edu.iss.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.repo.StudentRepository;
@Service
public class StudentServiceImp implements StudentService {
	
	@Autowired
	private StudentRepository srepo; 
	
	@Override
	public Student getStd(Long id)
	{
		return (Student) srepo.findById(id).get();
	}

}
