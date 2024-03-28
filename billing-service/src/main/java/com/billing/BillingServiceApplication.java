package com.billing;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Data;


@Data
class Customer {
	Long id;
	String name;
	String mail;
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
	public Customer(Long id, String name, String mail) {

		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	
	
	
}
/*
 *Comment appeller autre microservice.
 *exemple : on besoin d'un objet de microservice CUSTOMER-SERVICE
 *    On utulise : LIBRAIRIE FEIGN (declarative, rest client)
 *    1 : activer feign client @EnableFeignClients
 *    2 : create meme objet que l'object q on veut recuperer de microservice customer
 *    2 : create interface et indiquer le microservice a appeler
 *    3 : @GetMapping ("/customers/{id}") : url
 *    
 *    ==> CUSTOMER-SERVICE/customers/{id}
 *
 **/
@FeignClient (name = "CUSTOMER-SERVICE")
interface CustomerService {
	@GetMapping ("/customers/{id}")
	Customer findCustomerById (@PathVariable (name = "id") Long id);
	
	
	@GetMapping ("/customers")
	public PagedModel<Customer> findAllCustomers (); 
}


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerService customerService) {

		return args -> {
			System.out.println("************debut**************");
			Customer customer  = customerService.findCustomerById(1L);
			
			System.out.println("*************CUSTOMER *************");
			System.out.println(customer.getId());
			System.out.println(customer.getMail());
			System.out.println(customer.getName());
			
			System.out.println("************ LIST CUSTOMERS**************");
			PagedModel <Customer> customers  = customerService.findAllCustomers();
			
			customers.getContent().forEach(p -> {
				System.out.println(p.getId());
				System.out.println(p.getMail());
				System.out.println(p.getName());
			});
			
			

			
		};
	}

}
