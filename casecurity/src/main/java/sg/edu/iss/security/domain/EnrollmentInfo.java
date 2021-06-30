package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class EnrollmentInfo {

	private Long enrollmentId;
	private String courseName;
	private LocalDate startDate;
	private Long studentId;
	private String studentName;
	private String status;
	private Double credits;
	private float score;
	private String grade;
	private float prelimscore;
	private float cgpa;
	private long studentclassid;
	
	public EnrollmentInfo() {
		super();
	}
	
	public EnrollmentInfo(Long enrollmentId, String courseName, LocalDate startDate, Long studentId, String studentName,
			String status) {
		super();
		this.enrollmentId = enrollmentId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.studentId = studentId;
		this.studentName = studentName;
		this.status = status;
	}

	public EnrollmentInfo(Long enrollmentId, String courseName, LocalDate startDate, Long studentId, String studentName,
			String status, Double credits, float score, String grade, float prelimscore, float cgpa,
			long studentclassid) {
		super();
		this.enrollmentId = enrollmentId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.studentId = studentId;
		this.studentName = studentName;
		this.status = status;
		this.credits = credits;
		this.score = score;
		this.grade = grade;
		this.prelimscore = prelimscore;
		this.cgpa = cgpa;
		this.studentclassid = studentclassid;
	}


	public Long getEnrollmentId() {
		return enrollmentId;
	}


	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
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
		this.studentName = studentName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Double getCredits() {
		return credits;
	}


	public void setCredits(Double credits) {
		this.credits = credits;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}


	public float getCgpa() {
		return cgpa;
	}


	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}


	public long getStudentclassid() {
		return studentclassid;
	}


	public void setStudentclassid(long studentclassid) {
		this.studentclassid = studentclassid;
	}


	public float getPrelimScore() {
		return prelimscore;
	}
	public void setPrelimScore(float score) {
		float prelimscore = 0;

		if(score>= 80) {prelimscore=5f;}
		else if(score>=75) {prelimscore=4.5f;}
		else if(score>=70) {prelimscore=4f;}
		else if(score>=65) {prelimscore=3.5f;}
		else if(score>=60) {prelimscore=3f;}
		else if(score>=55) {prelimscore=2.5f;}
		else if(score>=50) {prelimscore=2f;}
		else if(score>=45) {prelimscore=1.5f;}
		else if(score>=40) {prelimscore=1f;}
		else {prelimscore=0f;}
		
		this.prelimscore = prelimscore;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(float score) {
		
		String grade = "";
		if(score>=85) {grade="A*";}
		else if(score>= 80) {grade= "A";}
		else if(score>=75) {grade= "A-";}
		else if(score>=70) {grade= "B+";}
		else if(score>=65) {grade= "B";}
		else if(score>=60) {grade= "B-";}
		else if(score>=55) {grade= "C*";}
		else if(score>=50) {grade= "C";}
		else if(score>=45) {grade= "D*";}
		else if(score>=40) {grade= "D";}
		else {grade= "F";}
		
		this.grade = grade;

	}
	
}

