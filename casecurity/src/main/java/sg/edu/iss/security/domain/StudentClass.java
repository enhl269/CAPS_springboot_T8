package sg.edu.iss.security.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class StudentClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDate startdate;
	
	private int classSize;
	
	@ManyToOne
	private Course course;
	
	@OneToMany(mappedBy = "studentClass")
	private List<Enrollment> enrollmentList = new ArrayList<>();
	
	@ManyToOne
	private Lecturer lecturer;

	public StudentClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentClass(long id, LocalDate startdate, int classSize, Course course, List<Enrollment> enrollmentList,
			Lecturer lecturer) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.classSize = classSize;
		this.course = course;
		this.enrollmentList = enrollmentList;
		this.lecturer = lecturer;
	}

	public StudentClass(LocalDate startdate, int classSize, Course course, List<Enrollment> enrollmentList,
			Lecturer lecturer) {
		super();
		this.startdate = startdate;
		this.classSize = classSize;
		this.course = course;
		this.enrollmentList = enrollmentList;
		this.lecturer = lecturer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	@Override
	public String toString() {
		return "StudentClass [startdate=" + startdate + ", classSize=" + classSize + ", course=" + course
				+ ", enrollmentList=" + enrollmentList + ", lecturer=" + lecturer + "]";
	}
	
	
}
