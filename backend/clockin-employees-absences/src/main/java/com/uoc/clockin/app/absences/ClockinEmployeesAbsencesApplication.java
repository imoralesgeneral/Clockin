package com.uoc.clockin.app.absences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.uoc.clockin.app.commons.models.entity"})
public class ClockinEmployeesAbsencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockinEmployeesAbsencesApplication.class, args);
	}

}
