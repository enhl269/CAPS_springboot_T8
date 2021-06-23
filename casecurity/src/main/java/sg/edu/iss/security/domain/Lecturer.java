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
@DiscriminatorValue("Lecture")
public class Lecturer extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long LectureId;
//	
	@OneToMany(mappedBy="lecturer")
	private List<LecturerCanTeach> lecturerCanTeach = new ArrayList<>();
	
	@OneToMany(mappedBy="lecturer")
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
//
//	public Lecturer(Long lectureId, List<LecturerCanTeach> lecturerCanTeach, List<StudentClass> studentClass) {
//		super();
//		LectureId = lectureId;
//		this.lecturerCanTeach = lecturerCanTeach;
//		this.studentClass = studentClass;
//	}

	public Lecturer(List<LecturerCanTeach> lecturerCanTeach, List<StudentClass> studentClass) {
		super();
		this.lecturerCanTeach = lecturerCanTeach;
		this.studentClass = studentClass;
	}

//	public Long getLectureId() {
//		return LectureId;
//	}
//
//	public void setLectureId(Long lectureId) {
//		LectureId = lectureId;
//	}

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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + ((LectureId == null) ? 0 : LectureId.hashCode());
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
//		Lecturer other = (Lecturer) obj;
//		if (LectureId == null) {
//			if (other.LectureId != null)
//				return false;
//		} else if (!LectureId.equals(other.LectureId))
//			return false;
//		return true;
//	}
//
//	
	
}
