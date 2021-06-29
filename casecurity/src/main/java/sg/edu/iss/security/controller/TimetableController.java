package sg.edu.iss.security.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import sg.edu.iss.security.domain.Timetable;
import sg.edu.iss.security.repo.TimetableRepository;

@RestController
public class TimetableController {
	
    @Autowired
    TimetableRepository er;

   @RequestMapping("/api")
    @ResponseBody
    String home() {
        return "Welcome !";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Timetable> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate end) {
        return er.findBetween(start, end);
    }
    
  

}
