package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class EnrollmentInfo {
	
	private long enrollmentId;
	private String courseName;
	private LocalDate startDate;
	private Long studentId;
	private String StudentName;
	private float score;
	
	public EnrollmentInfo() {
		super();
	}
	
	public EnrollmentInfo(String courseName, LocalDate startDate, Long studentId, String studentName) {
		super();
		this.courseName = courseName;
		this.startDate = startDate;
		this.studentId = studentId;
		StudentName = studentName;
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
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
