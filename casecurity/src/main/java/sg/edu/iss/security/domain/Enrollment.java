package sg.edu.iss.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private float score;
	
	private String status;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private StudentClass studentClass;

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enrollment(long id, float score, String status, Student student, StudentClass studentClass) {
		super();
		this.id = id;
		this.score = score;
		this.status = status;
		this.student = student;
		this.studentClass = studentClass;
	}

	public Enrollment(float score, String status, Student student, StudentClass studentClass) {
		super();
		this.score = score;
		this.status = status;
		this.student = student;
		this.studentClass = studentClass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentClass getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}

	@Override
	public String toString() {
		return "Enrollment [score=" + score + ", status=" + status + ", student=" + student + ", studentClass="
				+ studentClass + "]";
	}
	
	
	
}
