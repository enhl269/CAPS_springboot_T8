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

}
