package sg.edu.iss.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.LecturerCanTeachRepository;
import sg.edu.iss.security.repo.LecturerRepository;
import sg.edu.iss.security.repo.UserRepository;

@Service
public class LecturerServiceImp implements LecturerService {
	
	
	@Autowired
	private LecturerRepository lrepo; 
	
	@Autowired 
	private LecturerCanTeachRepository lctrepo;
	
	@Autowired
	private UserRepository urepo; 
	
	@Override
	public List<User> findAllLecturers(String role)
	{
		return urepo.findByRoleType(role);
		
	}
	
	@Override
	public List<LecturerCanTeach> findAllLCT(long id)
	{
		return lctrepo.findLecturerCanTeachByLecturerId(id);
	}
	
	@Override
	public void delete(Long id) {
		lctrepo.deleteById(id);
	}
	
	@Override
	public void save(LecturerCanTeach lct) {
		lctrepo.save(lct);
	}

}