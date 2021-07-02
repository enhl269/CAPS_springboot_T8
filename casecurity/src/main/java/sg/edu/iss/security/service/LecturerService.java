package sg.edu.iss.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.User;

public interface LecturerService {

	List<User> findAllLecturers(String role);

	List<LecturerCanTeach> findAllLCT(long id);

	void delete(Long id);

	void save(LecturerCanTeach lct);
	
	 Page<Course> getPageLecture(Pageable pageable);

	List<LecturerCanTeach> getAllLCT();

}

