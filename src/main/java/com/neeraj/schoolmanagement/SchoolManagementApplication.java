package com.neeraj.schoolmanagement;

import com.neeraj.schoolmanagement.domain.Globals;
import com.neeraj.schoolmanagement.domain.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

}
