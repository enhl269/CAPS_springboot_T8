package sg.edu.iss.security.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.domain.CourseGrades;
import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
import sg.edu.iss.security.repo.UserRepository;
import sg.edu.iss.security.service.CourseService;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.StudentClassService;

@Controller
public class LecturerController {
	
	//@Autowired
	//private LecturerService lservice;
	
	@Autowired
	private EnrollmentService eservice;
	
	@Autowired
	private StudentClassService scservice;
	
	@Autowired 
	private UserRepository urepo;
	
	@Autowired
	private CourseService cservice;
	
	@RequestMapping("/studentClassList")
	//@ResponseBody
	public String ListStudentClassByLecture(Model model, Principal p) {
		long id = urepo.findByEmail(p.getName()).getId();
		//long id = 3;
		
		List<StudentClass> scList = scservice.getStdClassByLecturer(id);
		List<StudentClassInfo> sciList = new ArrayList<>(scList.size());
		
		for(int i=0; i < scList.size(); i++) {
			sciList.add(new StudentClassInfo());
			sciList.get(i).setId(scList.get(i).getId());
			sciList.get(i).setCourseName(scList.get(i).getCourse().getName());
			sciList.get(i).setStartdate(scList.get(i).getStartdate());
			sciList.get(i).setClassSize(scList.get(i).getClassSize());
			sciList.get(i).setEnrollmentSize(scList.get(i).getEnrollmentList().size());
		}
		
		model.addAttribute("studentclasses",sciList);
		//return sciList;
		return "lecturer_studentclasses";
	}

	
	  @RequestMapping(value = "/studentClassList/{id}", method = RequestMethod.GET)
	  //@ResponseBody
	  public String ListEnrollment(Model model, @PathVariable("id") Long scId) {
		  List<Enrollment> eList = eservice.getByStudentClassId(scId);
		  List<EnrollmentInfo> eiList = new ArrayList<>(eList.size());
		  
		  float sum = 0;
		  float mc =0;
		  
		  for(int i=0; i < eList.size(); i++) 
		  { 
			  eiList.add(new EnrollmentInfo());
			  eiList.get(i).setStudentclassid(scId);
			  eiList.get(i).setEnrollmentId(eList.get(i).getId());
			  eiList.get(i).setCourseName(eList.get(i).getStudentClass().getCourse().getName());
			  eiList.get(i).setCredits(eList.get(i).getStudentClass().getCourse().getCredits());
			  eiList.get(i).setStartDate(eList.get(i).getStudentClass().getStartdate());
			  eiList.get(i).setStudentId(eList.get(i).getStudent().getId());
			  eiList.get(i).setStudentName(eList.get(i).getStudent().getFirstName()+eList.get(i).getStudent().getLastName());
			  eiList.get(i).setScore(eList.get(i).getScore()); 
			  eiList.get(i).setGrade(eList.get(i).getScore());
			  eiList.get(i).setPrelimScore(eList.get(i).getScore());
			  
			  Long stdid = eiList.get(i).getStudentId();
			  List<Course> stdcourses = cservice.getCourseStudentTakes(eiList.get(i).getStudentId());
				List<CourseGrades> a = new ArrayList<>(stdcourses.size());
				for(int j=0;j< stdcourses.size();j++)
				{
					a.add(new CourseGrades());
					a.get(j).setId(stdcourses.get(j).getId());
					a.get(j).setName(stdcourses.get(j).getName());
					a.get(j).setDescription(stdcourses.get(j).getDescription());
					a.get(j).setType(stdcourses.get(j).getType());
					a.get(j).setCredits(stdcourses.get(j).getCredits());
					
					Long courseid = stdcourses.get(j).getId();
					float x = eservice.getScore(courseid,stdid);
					a.get(j).setScore(x);
					a.get(j).setGrade(a.get(j).getScore());
					
					mc+= a.get(j).getCredits();
					a.get(j).setPrelimScore(a.get(j).getScore());
					sum += a.get(j).getCredits() * a.get(j).getPrelimScore();
				}
				
				float cgpa = sum/mc;
				
				eiList.get(i).setCgpa(cgpa);
			  
			  }
		  model.addAttribute("enrollments",eiList); 
		  
		  model.addAttribute("requestparam",new EnrollmentInfo()); 
		  //return eiList; 
		  return "lecturer_enrollment"; 
		  }
		  
	  @RequestMapping(value ="/editScore/{studentclassid}/{id}", method = RequestMethod.POST)
	  //@ResponseBody
	  public String saveScore(@PathVariable("id") Long eId, @RequestParam("attempt1") float score,@PathVariable("studentclassid") Long scId) {
			eservice.saveScore(score, eId);
			
			return "redirect:/studentClassList/"+scId;
		}
	 
}
	 
