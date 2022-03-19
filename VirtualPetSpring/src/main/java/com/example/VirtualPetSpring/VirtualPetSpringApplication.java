package com.example.VirtualPetSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = {"com.example.VirtualPetSpring.Pet", "com.example.VirtualPetSpring.PetController"})
public class VirtualPetSpringApplication {
	PetController petControl;
	static Pet pet;
	static TimeHandler handler;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(VirtualPetSpringApplication.class, args);
		handler.update(pet);
		pet.setAngryPoints(5);


	}

	@Bean
	public TimeHandler timeHandler(){
		return handler = new TimeHandler();
	}

	@Bean
	public Pet pet(){
		return pet = new Pet("Schnuffi");
	}

	@Bean
	public PetController petController(){
		return petControl = new PetController(pet);
	}

}
