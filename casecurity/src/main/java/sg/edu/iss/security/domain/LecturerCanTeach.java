package sg.edu.iss.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LecturerCanTeach {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Lecturer lecturer;
	
	@ManyToOne
	private Course course;

	public LecturerCanTeach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LecturerCanTeach(long id, Lecturer lecturer, Course course) {
		super();
		this.id = id;
		this.lecturer = lecturer;
		this.course = course;
	}

	public LecturerCanTeach(Lecturer lecturer, Course course) {
		super();
		this.lecturer = lecturer;
		this.course = course;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "LecturerCanTeach [lecturer=" + lecturer + ", course=" + course + "]";
	}
	


}

