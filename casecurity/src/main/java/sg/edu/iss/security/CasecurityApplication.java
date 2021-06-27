package sg.edu.iss.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.security.repo.UserRepository;




@SpringBootApplication
public class CasecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasecurityApplication.class, args);
		
		
	}
	
	
	@Autowired
	UserRepository urepo;

 
	
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
			
			System.out.println(urepo.findByEmail("yahoo@gmail.com").getId());
			     
		};
	}
	
	

}
