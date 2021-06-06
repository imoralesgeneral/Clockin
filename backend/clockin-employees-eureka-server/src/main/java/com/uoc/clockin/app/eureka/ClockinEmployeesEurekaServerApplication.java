package com.uoc.clockin.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ClockinEmployeesEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockinEmployeesEurekaServerApplication.class, args);
	}

}
