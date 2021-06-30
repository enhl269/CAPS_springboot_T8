package sg.edu.iss.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.StudentRepository;
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository srepo; 
	
	public Student getStd(Long id)
	{
		return (Student) srepo.findById(id).get();
	}

	public Page<User> getPageStudent(Pageable pageable) {
		return srepo.findAll(pageable);
	}

}
