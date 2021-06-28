package sg.edu.iss.security.domain;

import java.time.LocalDate;

public class EnrollmentInfo {
	
	private long enrollmentId;
	private String courseName;
	private LocalDate startDate;
	private Long studentId;
	private String StudentName;
	private Double credits;
	private float score;
	private String grade;
	private float prelimscore;
	private float cgpa;
	private long studentclassid;
	
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
	
	public float getCGPA() {
		return cgpa;
	}

	public void setCGPA(float cgpa) {
		this.cgpa = cgpa;
	}
	
	public long getStudentClassId() {
		return studentclassid;
	}

	public void setStudentClassId(long studentclassid) {
		this.studentclassid = studentclassid;
	}

}
