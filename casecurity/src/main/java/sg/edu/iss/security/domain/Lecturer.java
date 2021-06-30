package sg.edu.iss.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Lecture")
public class Lecturer extends User {

	@OneToMany(mappedBy="lecturer", cascade = CascadeType.ALL)
	private List<LecturerCanTeach> lecturerCanTeach = new ArrayList<>();
	
	@OneToMany(mappedBy="lecturer", cascade = CascadeType.ALL)
	private List<StudentClass> studentClass = new ArrayList<>();

	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(List<LecturerCanTeach> lecturerCanTeach) {
		super();
		this.lecturerCanTeach = lecturerCanTeach;
	}

	public Lecturer(User user)
	{
		super();
	}

	public Lecturer(List<LecturerCanTeach> lecturerCanTeach, List<StudentClass> studentClass) {
		super();
		this.lecturerCanTeach = lecturerCanTeach;
		this.studentClass = studentClass;
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
		return "Lecturer [lecturerCanTeach=" + lecturerCanTeach + ", studentClass=" + studentClass + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + "]";
	}

	
}
