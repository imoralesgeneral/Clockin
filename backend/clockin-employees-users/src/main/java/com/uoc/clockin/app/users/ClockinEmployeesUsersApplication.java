package com.uoc.clockin.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.uoc.clockin.app.commons.models.entity"})
public class ClockinEmployeesUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockinEmployeesUsersApplication.class, args);
	}

}
