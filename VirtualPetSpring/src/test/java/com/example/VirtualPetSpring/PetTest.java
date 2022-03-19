package com.example.VirtualPetSpring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    Pet pet = new Pet("Lara");
    Food apple = new Food("Apple", 2,1.00f);
/*
    @org.junit.jupiter.api.Test
    void feed() {
        //input
        pet.setHungryPoints(10);

        //when
        pet.feed(apple);

        //result
        assertEquals(8,pet.getHungryPoints());
    }
    @org.junit.jupiter.api.Test
    void feed2() {
        //input
        pet.setHungryPoints(1);

        //when
        pet.feed(apple);

        //result
        assertEquals(0, pet.getHungryPoints());
    }

    @org.junit.jupiter.api.Test
    void feed3() {
        //input
        pet.setHungryPoints(0);

        //when
        pet.feed(apple);

        //result
        assertEquals(0, pet.getHungryPoints());
    }

 */

    @org.junit.jupiter.api.Test
    void calm() {
        //input
        pet.setAngryPoints(10);

        //when
        pet.calm();

        //result
        assertEquals(0, pet.getAngryPoints());
    }

    @org.junit.jupiter.api.Test
    void evolve() {
        //input
        pet.setAge(Pet.PetAge.BABY);

        //when
        pet.evolve();

        //result
        assertEquals(Pet.PetAge.KID, pet.getAge());
    }

    @org.junit.jupiter.api.Test
    void evolve2() {
        //input
        pet.setAge(Pet.PetAge.KID);

        //when
        pet.evolve();

        //result
        assertEquals(Pet.PetAge.ADULT, pet.getAge());
    }

    @org.junit.jupiter.api.Test
    void evolve3() {
        //input
        pet.setAge(Pet.PetAge.ADULT);

        //when
        pet.evolve();

        //result
        assertEquals(Pet.PetAge.DEAD, pet.getAge());
    }

    @org.junit.jupiter.api.Test
    void evolve4() {
        //input
        pet.setAge(Pet.PetAge.DEAD);

        //when
        pet.evolve();

        //result
        assertEquals(Pet.PetAge.DEAD, pet.getAge());
    }

    @Test
    void updatePointsHungry(){
        //input
        pet.setState(Pet.PetState.HUNGRY);
        pet.setHungryPoints(0);

        //when
        pet.updatePoints();

        //result
        assertEquals(1,pet.getHungryPoints());
    }

    @Test
    void updatePointsHungry2(){
        //input
        pet.setState(Pet.PetState.HUNGRY);
        pet.setHungryPoints(10);

        //when
        pet.updatePoints();

        //result
        assertEquals(10,pet.getHungryPoints());
        assertEquals(1, pet.getDeathCount());
    }

    @Test
    void updatePointsHungryDeath(){
        //input
        pet.setState(Pet.PetState.HUNGRY);
        pet.setHungryPoints(2);
        pet.setDeathCount(10);

        //when
        pet.updatePoints();

        //result
        assertEquals(Pet.PetAge.DEAD,pet.getAge());
        assertEquals(2, pet.getHungryPoints());
    }

    @Test
    void updatePointsAngry(){
        //input
        pet.setState(Pet.PetState.ANGRY);
        pet.setAngryPoints(0);

        //when
        pet.updatePoints();

        //result
        assertEquals(1,pet.getAngryPoints());
    }

    @Test
    void updatePointsSleepy(){
        //input
        pet.setState(Pet.PetState.SLEEPY);
        pet.setSleepPoints(0);

        //when
        pet.updatePoints();

        //result
        assertEquals(1,pet.getSleepPoints());
    }

    @Test
    void updatePointsBored(){
        //input
        pet.setState(Pet.PetState.BORED);
        pet.setBoredPoints(0);

        //when
        pet.updatePoints();

        //result
        assertEquals(1,pet.getBoredPoints());
    }

    @Test
    void updatePointsBored2(){
        //input
        pet.setState(Pet.PetState.BORED);
        pet.setBoredPoints(11);

        //when
        pet.updatePoints();

        //result
        assertEquals(10,pet.getBoredPoints());
    }


}