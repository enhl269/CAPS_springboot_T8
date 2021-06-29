package sg.edu.iss.security.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timetable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String text;
	
	LocalDate start;
	
	LocalDate end;

	String color;


	public Timetable(String text, LocalDate start, LocalDate end, String color) {
		super();
		this.text = text;
		this.start = start;
		this.end = end;
		this.color = color;
	}



	public Timetable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public String getColor() { return color; }

	public void setColor(String color) { this.color = color; }

}

