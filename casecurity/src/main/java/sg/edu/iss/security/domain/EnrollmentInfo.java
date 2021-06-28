package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class EnrollmentInfo {

	private String courseName;
	private LocalDate startDate;
	private Long studentId;
	private String studentName;
	private String status;
	
	public EnrollmentInfo() {
		super();
	}
	
	public EnrollmentInfo(String courseName, LocalDate startDate, Long studentId, String studentName, String status) {
		super();
		this.courseName = courseName;
		this.startDate = startDate;
		this.studentId = studentId;
		this.studentName = studentName;
		this.status = status;
	}



	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		studentName = studentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
