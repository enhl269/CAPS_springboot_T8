package sg.edu.iss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.Enrollment;


public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long>, JpaRepository<Enrollment,Long> {
	
}
