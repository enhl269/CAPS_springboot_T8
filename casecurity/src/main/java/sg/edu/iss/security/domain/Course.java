package sg.edu.iss.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private Double credits;
	 

	public Double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	@OneToMany(mappedBy="course")
	private List<LecturerCanTeach> lecturerCanTeach = new ArrayList<>();
	
	
	
	@OneToMany(mappedBy="course")
	private List<StudentClass> studentClass = new ArrayList<>();

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(long id, String name, String description, String type, List<LecturerCanTeach> lecturerCanTeach,
			List<StudentClass> studentClass, Double credits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.lecturerCanTeach = lecturerCanTeach;
		this.credits = credits;
		this.studentClass = studentClass;
	}

	public Course(String name, String description, String type, List<LecturerCanTeach> lecturerCanTeach,
			List<StudentClass> studentClass, Double credits) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.lecturerCanTeach = lecturerCanTeach;
		this.credits = credits;
		this.studentClass = studentClass;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<LecturerCanTeach> getLecturerCanTeach() {
		return lecturerCanTeach;
	}

	public void setLecturerCanTeach(List<LecturerCanTeach> lecturerCanTeach) {
		this.lecturerCanTeach = lecturerCanTeach;
	}


	public List<StudentClass> getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(List<StudentClass> studentClass) {
		this.studentClass = studentClass;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", description=" + description + ", type=" + type + ", lecturerCanTeach="
				+ lecturerCanTeach + ", studentClass=" + studentClass + "]";
	}
	
	
	
}
