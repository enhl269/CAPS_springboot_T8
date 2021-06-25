package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.StudentRepository;
import sg.edu.iss.security.repo.UserRepository;
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository srepo; 
	
	@Autowired
	private UserRepository urepo;
	
	public Student getStd(Long id)
	{
		return (Student) srepo.findById(id).get();
	}

	public List<User> getAllStudent() {
		return urepo.findAll();
	}
}
