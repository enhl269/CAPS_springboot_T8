package sg.edu.iss.security.domain;

import javax.persistence.Transient;

public class CourseGrades {
	private long id;
	private String name;
	private String description;
	private String type;
	private Double credits;
	private float score;
	private String grade;
	
	private float prelimscore;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	

}
