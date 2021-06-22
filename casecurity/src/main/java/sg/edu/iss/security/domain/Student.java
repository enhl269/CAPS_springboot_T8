package sg.edu.iss.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Student")
public class Student extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)	
//	private Long StudentId;
//	
	private float cGPA;
	
	private int contactNumber;
	
	@OneToMany(mappedBy = "student")
	private List<Enrollment> enrollmentList = new ArrayList<>();

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Student(Long studentId, float cGPA, int contactNumber, List<Enrollment> enrollmentList) {
//		super();
////		StudentId = studentId;
//		this.cGPA = cGPA;
//		this.contactNumber = contactNumber;
//		this.enrollmentList = enrollmentList;
//	}

	public Student(float cGPA, int contactNumber, List<Enrollment> enrollmentList) {
		super();
		this.cGPA = cGPA;
		this.contactNumber = contactNumber;
		this.enrollmentList = enrollmentList;
	}
	
	

//	public Long getStudentId() {
//		return StudentId;
//	}
//
//	public void setStudentId(Long studentId) {
//		StudentId = studentId;
//	}

	public float getcGPA() {
		return cGPA;
	}

	public void setcGPA(float cGPA) {
		this.cGPA = cGPA;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	@Override
	public String toString() {
		return "Student [cGPA=" + cGPA + ", contactNumber=" + contactNumber + ", enrollmentList=" + enrollmentList
				+ ", getEmail()=" + getEmail() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + "]";
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + ((StudentId == null) ? 0 : StudentId.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		if (StudentId == null) {
//			if (other.StudentId != null)
//				return false;
//		} else if (!StudentId.equals(other.StudentId))
//			return false;
//		return true;
//	}

	
	
	
	
}
