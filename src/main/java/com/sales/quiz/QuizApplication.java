package com.sales.quiz;

import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.model.Client;
import com.sales.quiz.repository.ClientRepository;
import com.sales.quiz.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ClientRepository clientRepository) {
		return args -> {
			Stream.of("Majed", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				Client client = new Client(name, name.toLowerCase() + "@domain.com",name,"+96176451917");
				clientRepository.save(client);
			});
			clientRepository.findAll().forEach(System.out::println);
		};
	}


	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			productService.add(new ProductDTO(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(4L, "Icecream", 5.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(5L, "Beer", 3.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(6L, "Phone", 500.00, "http://placehold.it/200x100"));
			productService.add(new ProductDTO(7L, "Watch", 30.00, "http://placehold.it/200x100"));
		};
	}
}
