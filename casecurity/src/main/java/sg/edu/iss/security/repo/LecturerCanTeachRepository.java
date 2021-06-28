package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.LecturerCanTeach;
import sg.edu.iss.security.domain.StudentClass;

public interface LecturerCanTeachRepository extends PagingAndSortingRepository<LecturerCanTeach, Long>, JpaRepository<LecturerCanTeach, Long>{

	@Query("SELECT lct FROM LecturerCanTeach lct JOIN lct.lecturer lect "
			+"where lect.id=:id ")
	public List<LecturerCanTeach> findLecturerCanTeachByLecturerId(@Param("id") Long id);
}

