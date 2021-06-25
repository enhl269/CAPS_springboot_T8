package sg.edu.iss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.LecturerCanTeach;

public interface LecturerCanTeachRepository extends PagingAndSortingRepository<LecturerCanTeach, Long>, JpaRepository<LecturerCanTeach, Long>{

}
