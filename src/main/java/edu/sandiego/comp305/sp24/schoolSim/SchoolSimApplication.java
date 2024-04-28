package edu.sandiego.comp305.sp24.schoolSim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolSimApplication {
	private static final String CONFIG_FILE = "config.properties";

	public static void main(String[] args) {
		Config.initialize(CONFIG_FILE);

		SpringApplication.run(SchoolSimApplication.class, args);
	}

}
