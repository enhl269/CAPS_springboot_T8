package sg.edu.iss.security.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler  {

//
//	@ExceptionHandler(value=TemplateProcessingException.class)
//	public String handler() {
//		return "support";
//	}
//	
	@ExceptionHandler(value=CustomException.class)
	public ModelAndView handler2(Exception e) {
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e);
	    mav.setViewName("ProcessErrors");
		return mav;
	}
	
	@Override
		protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
			// TODO Auto-generated method stub
	
		return new ResponseEntity<Object>("Please return to home page", HttpStatus.BAD_REQUEST);
		}

}
