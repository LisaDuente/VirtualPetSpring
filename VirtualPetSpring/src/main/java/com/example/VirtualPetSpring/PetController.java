package com.example.VirtualPetSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;


@RestController
public class PetController {
    @Autowired
    Pet pet;

    public PetController(Pet pet) {
        this.pet = pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @GetMapping("/test")
    public String test(){
     return "test";
    }

    @GetMapping("/feed")
    public void feed() {
        pet.feed();
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
    public void play() {
        pet.play();
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

    @GetMapping("/getAllPoints")
    public String getAllPoints(){
        return pet.getAllPoints();
    }

    @GetMapping("/getX")
    public int getPosX(){
        return pet.getPosX();
    }

    @GetMapping("/getY")
    public int getPosY(){
        return pet.getPosY();
    }

    @GetMapping("/isWalkingRight")
    public boolean isWalkingRight(){
        return pet.isWalkingRight();
    }

    @GetMapping("/getMoveState")
    public Pet.MoveState getMoveState(){
       return pet.getMoveState();
    }

    @GetMapping("/chooseWalkingSprite")
    public int getWalkingSprite(){
        return pet.chooseWalkingSprite();
    }

    @GetMapping("/chooseIdleSprite")
    public int getIdleSprite(){
        return pet.chooseIdleSprite();
    }

    @GetMapping("/chooseDeadSprite")
    public int getBackSprite(){
        return pet.chooseDeadSprite();
    }
}
