package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class StudentClassInfo {
	private String courseName;
	private LocalDate startdate;
	private int classSize;
	
	public StudentClassInfo(String courseName, LocalDate startdate, int classSize) {
		super();
		this.courseName = courseName;
		this.startdate = startdate;
		this.classSize = classSize;
	}
	public StudentClassInfo() {
		super();
	}
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
	
	
}
