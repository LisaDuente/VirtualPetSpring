package com.example.VirtualPetSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtualPetSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualPetSpringApplication.class, args);
		Pet pet = new Pet("Schnuffi");
		TimeHandler handler = new TimeHandler();
		//handler.update(pet);
		GameSaverJson jSaver = new GameSaverJson();
		jSaver.save(pet);
		pet.setName("Linda");
		System.out.println(pet.toString());
		pet = jSaver.load();
		System.out.println(pet.getName());
	}

}
