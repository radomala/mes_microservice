package com.invetory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.kafka.annotation.EnableKafka;


// ***************************ENTITY**********************************************************************
@Entity
class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	public Product(Long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Product() {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
}

// ***************************REPOSITORY**********************************************************************
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long> {
	
}

// ***************************MAIN**********************************************************************
@SpringBootApplication
@EnableKafka
public class InvetoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvetoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product(null, "ord hp hjh", 7666));
			productRepository.save(new Product(null, "imprimante", 7666));
			productRepository.save(new Product(null, "xerox", 1000));
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
	
	

}
