package container.code;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClattAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClattAppsApplication.class, args);
	}

	@Bean
	public void run() {
		System.out.println("Hello");
	}
}
