package com.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

// ***************************ENTITY**********************************************************************
@Entity
class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String mail;

	public Customer(Long id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "customer [id=" + id + ", name=" + name + ", mail=" + mail + "]";
	}

	public Customer() {
	}
}

// ***************************REPOSITORY**********************************************************************
@RepositoryRestResource
interface CustomerRepository extends JpaRepository<Customer, Long> {

}

// ***************************CONTROLLER**********************************************************************

/*
@RestController
class restcontrol {

	@HystrixCommand (fallbackMethod = "fallbackHello")
	@GetMapping ("/hello")
	public String hello () {
		return "hello word";
	}

	String fallbackHello () {
		return "heloo initiated";
	}

}


 */

// ***************************MAIN**********************************************************************

@SpringBootApplication
@EnableCircuitBreaker
@EnableKafka
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {

		return args -> {

			customerRepository.save(new Customer(null, "ord hp hjh", "test"));
			customerRepository.save(new Customer(null, "imprimante", "test"));
			customerRepository.save(new Customer(null, "xerox", "test"));

			customerRepository.findAll().forEach(System.out::println);
		};

	}

}
