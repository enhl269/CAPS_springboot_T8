package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class StudentClassInfo {
	
	private long id;
	private String courseName;
	private LocalDate startdate;
	private int classSize;
	private int enrollmentsize;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public LocalDate getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}
	public int getClassSize() {
		return classSize;
	}
	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}
	
	public int getEnrollmentSize() {
		return enrollmentsize;
	}
	public void setEnrollmentSize(int enrollmentsize) {
		this.enrollmentsize = enrollmentsize;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
