package com.uoc.clockin.app.dailyentries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.uoc.clockin.app.commons.models.entity"})
public class ClockinEmployeesDailyentriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockinEmployeesDailyentriesApplication.class, args);
	}

}
