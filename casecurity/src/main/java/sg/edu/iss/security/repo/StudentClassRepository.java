package sg.edu.iss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.StudentClass;

public interface StudentClassRepository extends PagingAndSortingRepository<StudentClass, Long>, JpaRepository<StudentClass,Long> {

}
