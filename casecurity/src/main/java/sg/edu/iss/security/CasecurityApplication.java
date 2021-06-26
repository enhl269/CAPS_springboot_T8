package sg.edu.iss.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.security.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




@SpringBootApplication
public class CasecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasecurityApplication.class, args);
		
		
	}
	
	
	

 
	
	/*
	 * 
	 * @Autowired private JavaMailSender javaMailSender;
	 * 
	 * void sendEmail() {
	 * 
	 * SimpleMailMessage msg = new SimpleMailMessage();
	 * msg.setTo("esther.neohneohgmail.com");
	 * 
	 * msg.setSubject("Testing from Spring Boot");
	 * msg.setText("Hello World \n Spring Boot Email");
	 * 
	 * javaMailSender.send(msg);
	 * 
	 * }
	 * 
	 * @Autowired private CourseRepository crepo;
	 * 
	 */
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			     
		};
	}
	
	

}
