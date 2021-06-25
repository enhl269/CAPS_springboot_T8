package sg.edu.iss.security.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.security.domain.Course;
import sg.edu.iss.security.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	private CourseService service;


	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourses(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		final int currentPage = page.orElse(1);
		final int pageSize = size.orElse(5);

		Page<Course> coursePage = service.getPageCourse(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("coursePage", coursePage);

		int totalPages = coursePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "page_course_final";
	}

	@RequestMapping("/new")
	public String showNewCourseForm(Model model) {
		Course Course= new Course();
		model.addAttribute("Course", Course);
		
		return "new_Course";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("Course") Course Course) {
		service.save(Course);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditCourseForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_course");
		
		Course course = service.get(id);
		mav.addObject("course", course);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteCourset(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}


