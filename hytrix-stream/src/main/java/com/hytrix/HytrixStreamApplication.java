package com.hytrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HytrixStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(HytrixStreamApplication.class, args);
	}

}
