package com.example.soap_1;

import com.example.soap_1.model.GSC;
import com.example.soap_1.service.GSCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.util.List;

@SpringBootApplication
public class Soap1Application {

	public static void main(String[] args) {
		SpringApplication.run(Soap1Application.class, args);
	}

}
