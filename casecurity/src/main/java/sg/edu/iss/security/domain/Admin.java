package sg.edu.iss.security.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User  {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long AdminId;
//	
	@OneToMany(mappedBy="admin", cascade = CascadeType.ALL)
	private List<Course> course;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Admin(long adminId, List<Course> courselist) {
//		super();
//		AdminId = adminId;
//		this.courselist = courselist;
//	}

//	public Admin(List<Course> courselist) {
//		super();
//		this.courselist = courselist;
//	}
//
//	public long getAdminId() {
//		return AdminId;
//	}
//
//	public void setAdminId(long adminId) {
//		AdminId = adminId;
//	}

//	public List<Course> getCourselist() {
//		return courselist;
//	}
//
//	public void setCourselist(List<Course> courselist) {
//		this.courselist = courselist;
//	}
//
//	@Override
//	public String toString() {
//		return "Admin [courselist=" + courselist + ", getFirstName()=" + getFirstName() + ", getLastName()="
//				+ getLastName() + "]";
//	} 

	
	
	
}
