package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class StudentClassInfo {

	
	private long id;
	private String courseName;
	private LocalDate startdate;
	private int classSize;
	private int enrollmentsize;
	private String lecturername;
	
	private long courseid;
	private long lecturerid;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getLecturerName() {
		return lecturername;
	}
	public void setLecturerName(String lecturername) {
		this.lecturername = lecturername;
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
	
	public long getCourseId() {
		return courseid;
	}

	public void setCourseId(long courseid) {
		this.courseid = courseid;
	}
	
	public long getLecturerId() {
		return lecturerid;
	}

	public void setLecturerId(long lecturerid) {
		this.lecturerid = lecturerid;
	}

}
