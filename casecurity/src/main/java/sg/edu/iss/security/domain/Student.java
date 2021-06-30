package sg.edu.iss.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Student")
public class Student extends User {

	private float cGPA;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Enrollment> enrollmentList = new ArrayList<>();

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Student(float cGPA) {
		super();
		this.cGPA = cGPA;
		
	}


	public Student(float cGPA, int contactNumber, List<Enrollment> enrollmentList) {
		super();
		this.cGPA = cGPA;
		this.enrollmentList = enrollmentList;
	}
	
	

	public float getcGPA() {
		return cGPA;
	}

	public void setcGPA(float cGPA) {
		this.cGPA = cGPA;
	}


	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	@Override
	public String toString() {
		return "Student [cGPA=" + cGPA + ", enrollmentList=" + enrollmentList
				+ ", getEmail()=" + getEmail() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + "]";
	}
	
}

