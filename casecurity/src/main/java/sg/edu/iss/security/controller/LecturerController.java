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
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
import sg.edu.iss.security.repo.UserRepository;
import sg.edu.iss.security.service.EnrollmentService;
import sg.edu.iss.security.service.LecturerService;
import sg.edu.iss.security.service.StudentClassService;
import sg.edu.iss.security.service.StudentService;

@Controller
public class LecturerController {

	@Autowired
	private LecturerService lservice;
	
	@Autowired
	private EnrollmentService eservice;
	
	@Autowired
	private StudentClassService scservice;
	
	@Autowired
	private StudentService stdservice;
	
	@Autowired 
	private UserRepository urepo;
	
	@RequestMapping(value = "/studentClassList", method = RequestMethod.GET)
	public String ListStudentClassByLecture(Model model, Principal p) {
		//long id = urepo.findByEmail(p.getName()).getId();
		long id = 8;
		
		List<StudentClass> scList = scservice.getStdClassByLecturer(id);
		List<StudentClassInfo> sciList = new ArrayList<>(scList.size());
		
		for(int i=0; i < scList.size(); i++) {
			sciList.add(new StudentClassInfo());
			sciList.get(i).setCourseName(scList.get(i).getCourse().getName());
			sciList.get(i).setStartdate(scList.get(i).getStartdate());
			sciList.get(i).setClassSize(scList.get(i).getClassSize());
		}
		
		model.addAttribute("studentclasses",sciList);
		//return sciList;
		return "lecturer_studentclasses";
	}

	@RequestMapping(value = "/studentClassList/{id}", method = RequestMethod.GET)
	public String ListEnrollment(Model model, @PathVariable("id") Long scId) {
		List<Enrollment> eList = eservice.getByStudentClassId(scId);
		List<EnrollmentInfo> eiList = new ArrayList<>(eList.size());
		
		for(int i=0; i < eList.size(); i++) {
			eiList.add(new EnrollmentInfo());
			eiList.get(i).setEnrollmentId(eList.get(i).getId());
			eiList.get(i).setCourseName(eList.get(i).getStudentClass().getCourse().getName());
			eiList.get(i).setStartDate(eList.get(i).getStudentClass().getStartdate());
			eiList.get(i).setStudentId(eList.get(i).getStudent().getId());
			eiList.get(i).setStudentName(eList.get(i).getStudent().getFirstName()+eList.get(i).getStudent().getLastName());
			eiList.get(i).setScore(eList.get(i).getScore());
		}
		model.addAttribute("enrollments",eiList);
		//return eiList;
		return "lecturer_enrollment";
	}
	
	@RequestMapping(value ="editScore", method = RequestMethod.POST)
	@ResponseBody
	public float saveScore(@RequestParam("score") float score) {
		//@RequestParam("e_id") long eId
		long eId = 1;
		eservice.saveScore(score, eId);
		return score;
	}
}
