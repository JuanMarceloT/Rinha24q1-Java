package com.example.rinha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RinhaApplication {

	public static void main(String[] args) {
		for (String envName : System.getenv().keySet()) {
            System.out.println(envName + ": " + System.getenv(envName));
        }
		SpringApplication.run(RinhaApplication.class, args);
	}

}
