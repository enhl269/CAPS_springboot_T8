package sg.edu.iss.security.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import sg.edu.iss.security.domain.Timetable;

public interface TimetableRepository extends CrudRepository<Timetable, Long> {

	@Query("from Timetable e where not(e.end < :from or e.start > :to)")
	public List<Timetable> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDate start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDate end);
}
