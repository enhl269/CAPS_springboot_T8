package sg.edu.iss.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.iss.security.domain.User;

//to change to User class to test it out whether it still persist to the database or not
//kiv
public interface UserRepository extends JpaRepository<User, Long>,PagingAndSortingRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	@Query("select u from User u where u.roles = ?1")
	public List<User> findByRoleType(String role);
	
	
	
	
}
