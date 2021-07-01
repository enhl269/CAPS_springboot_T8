package sg.edu.iss.security.domain;

public class CourseViewModel {
	
	private long id;
	private String name;
	private String description;
	private String type;
	private Double credits;
	private long lecturerctid;
	private long lecid;
	private long stdid;
	
	
	public CourseViewModel() {
		super();
	}

	public CourseViewModel(long id, String name, String description, String type, Double credits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.credits = credits;
	}

	public Double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getStdId() {
		return stdid;
	}

	public void setStdId(long stdid) {
		this.stdid = stdid;
	}
	
	public long getLecturerCTId() {
		return lecturerctid;
	}

	public void setLecturerCTId(long lecturerctid) {
		this.lecturerctid = lecturerctid;
	}
	
	public long getLecId() {
		return lecid;
	}

	public void setLecId(long lecid) {
		this.lecid = lecid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseViewModel other = (CourseViewModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
