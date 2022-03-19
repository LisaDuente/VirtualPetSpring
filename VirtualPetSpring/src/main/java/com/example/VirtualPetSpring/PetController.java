package com.example.VirtualPetSpring;

import org.springframework.web.bind.annotation.GetMapping;

public class PetController {
    Pet pet;

    public PetController(Pet pet) {
        this.pet = pet;
    }

    @GetMapping("/feed")
    public void feed(Food food) {
        pet.feed(food);
    }

    @GetMapping("/sleep")
    public void sleep() {
        pet.sleep();
    }

    @GetMapping("/calm")
    public void calm() {
        pet.calm();
    }

    @GetMapping("/play")
    public void play(Toy toy) {
        pet.play(toy);
    }

    @GetMapping("/clean")
    public void clean() {
        pet.clean();
    }

    @GetMapping("/getAge")
    public Pet.PetAge getAge() {
        return pet.getAge();
    }

    @GetMapping("/name")
    public String getName() {
        return pet.getName();
    }

    @GetMapping("/getHungry")
    public int getHungryPoints() {
        return pet.getHungryPoints();
    }

    @GetMapping("/getAngry")
    public int getAngryPoints() {
        return pet.getAngryPoints();
    }

    @GetMapping("/getSleep")
    public int getSleepPoints() {
        return pet.getSleepPoints();
    }

    @GetMapping("/getBored")
    public int getBoredPoints(){
        return pet.getBoredPoints();
    }

    @GetMapping("/getDirty")
    public int getDirtyPoints(){
        return pet.getDirtyPoints();
    }

    @GetMapping("/getDeathPoints")
    public int getDeathPoints(){
        return pet.getDeathCount();
    }
}
