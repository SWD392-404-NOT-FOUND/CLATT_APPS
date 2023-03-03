package container.code;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@OpenAPIDefinition
public class ClattAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClattAppsApplication.class, args);
	}

}
