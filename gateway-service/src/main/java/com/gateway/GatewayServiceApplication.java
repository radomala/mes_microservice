package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication


public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

/*
	 @Bean
	  RouteLocator staticRoutes(RouteLocatorBuilder builder) {
		  return builder.routes() 
	     .route("r1", r ->r.path("/customers/**").uri("http://localhost:8081/")) 
	     .route("r2", r ->r.path("/products/**").uri("http://localhost:8082/"))
	     .build(); 
	  }
*/

	 @Bean
	  RouteLocator withnNameMicroServiceRoutes(RouteLocatorBuilder builder) {
		   return builder.routes()
		    .route("r1", r ->r.path("/customers/**").uri("lb://customer-service")) 
		    .route("r2", r ->r.path("/products/**").uri("lb://inventory-service"))
		    .build(); 
	}

	   //http://localhost:8888/CUSTOMER-SERVICE/customers
	 /*  @Bean
	   DiscoveryClientRouteDefinitionLocator dinamicRouters (ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
		   return new DiscoveryClientRouteDefinitionLocator (rdc, dlp);
	   }*/
	 

}
