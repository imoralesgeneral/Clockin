package com.uoc.clockin.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ClockinEmployeesConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockinEmployeesConfigServerApplication.class, args);
	}

}
