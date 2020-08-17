package demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EBank2k20Application {

	public static void main(String[] args) {
		SpringApplication.run(EBank2k20Application.class, args);
	}

}
