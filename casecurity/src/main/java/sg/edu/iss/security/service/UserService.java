package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sg.edu.iss.security.domain.Admin;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.Role;
import sg.edu.iss.security.domain.Student;
import sg.edu.iss.security.domain.User;
import sg.edu.iss.security.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	public void registerDefaultUser(User user) {
		//Role roleUser = roleRepo.findByName("User");
		//user.setRoles(Role.ADMIN.toString());
//		switch(user.getRoles()):
//			//smth smth
		switch(user.getRoles()) {
		case "ADMIN":
			Admin admin = new Admin();
			admin.setEmail(user.getEmail());
			admin.setFirstName(user.getFirstName());
			admin.setId(user.getId());
			admin.setLastName(user.getLastName());
			admin.setPassword(user.getPassword());
			admin.setRoles(user.getRoles());	
			encodePassword(admin);
			userRepo.save(admin);
			break;
		case "STUDENT":
			Student student = new Student();
			student.setEmail(user.getEmail());
			student.setFirstName(user.getFirstName());
			student.setId(user.getId());
			student.setLastName(user.getLastName());
			student.setPassword(user.getPassword());
			student.setRoles(user.getRoles());	
			encodePassword(student);
			userRepo.save(student);
			break;
		case "LECTURER":
			Lecturer lecturer = new Lecturer();
			lecturer.setEmail(user.getEmail());
			lecturer.setFirstName(user.getFirstName());
			lecturer.setId(user.getId());
			lecturer.setLastName(user.getLastName());
			lecturer.setPassword(user.getPassword());
			lecturer.setRoles(user.getRoles());			
			encodePassword(lecturer);
			userRepo.save(lecturer);
			break;
		}
//		encodePassword(user);
//		userRepo.save(user);
	}
	
	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<String> listRoles() {
		List<String> roles = null;
		for(Role role : Role.values())
			roles.add(role.toString());
		
		return roles;
	}
	
	public <T extends User> void save(T user) {
		encodePassword(user);		
		userRepo.save(user);
	}
	//pass string password value here
	private <T extends User> void encodePassword(T user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
	

}

