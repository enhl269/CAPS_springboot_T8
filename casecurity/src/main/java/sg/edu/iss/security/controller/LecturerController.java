package sg.edu.iss.security.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.security.domain.Enrollment;
import sg.edu.iss.security.domain.EnrollmentInfo;
import sg.edu.iss.security.domain.Lecturer;
import sg.edu.iss.security.domain.StudentClass;
import sg.edu.iss.security.domain.StudentClassInfo;
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
	
	@RequestMapping(value = "/studentClassList", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentClassInfo> ListStudentClassByLecture(Model model, HttpSession session) {
		//Long id = (Long) session.getAttribute("usession");
		long id = 7;
		
		List<StudentClass> scList = scservice.getStdClassByLecturer(id);
		List<StudentClassInfo> sciList = new ArrayList<>(scList.size());
		
		for(int i=0; i < scList.size(); i++) {
			sciList.add(new StudentClassInfo());
			sciList.get(i).setCourseName(scList.get(i).getCourse().getName());
			sciList.get(i).setStartdate(scList.get(i).getStartdate());
			sciList.get(i).setClassSize(scList.get(i).getClassSize());
		}
		
		//model.addAttribute("studentClass",scList);
		return sciList;
	}

	@RequestMapping(value = "/studentClassList/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<EnrollmentInfo> ListEnrollment(Model model, HttpSession session, @PathVariable("id") Long scId) {
		List<Enrollment> eList = eservice.getByStudentClassId(scId);
		List<EnrollmentInfo> eiList = new ArrayList<>(eList.size());
		
		for(int i=0; i < eList.size(); i++) {
			eiList.add(new EnrollmentInfo());
			eiList.get(i).setCourseName(eList.get(i).getStudentClass().getCourse().getName());
			eiList.get(i).setStartDate(eList.get(i).getStudentClass().getStartdate());
			eiList.get(i).setStudentId(eList.get(i).getStudent().getId());
			eiList.get(i).setStudentName(eList.get(i).getStudent().getFirstName()+eList.get(i).getStudent().getLastName());
		}
		//model.addAttribute("studentClass",scList);
		return eiList;
	}
	
	@RequestMapping(value = "/studentClassList/enrollment/{e_id}/{score}", method = RequestMethod.GET)
	public void saveScore(@PathVariable("e_id") long eId, @PathVariable("score") float score) {
		eservice.saveScore(score, eId);
	}
}
