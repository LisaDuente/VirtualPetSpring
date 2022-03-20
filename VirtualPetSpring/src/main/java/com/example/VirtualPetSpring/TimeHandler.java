package com.example.VirtualPetSpring;

public class TimeHandler {
    private long last;
    private long accumulator;
    private long updateTime;


    public TimeHandler(){
        this.last = System.nanoTime();
        this.accumulator = 0L;
        this.updateTime = 1000000000L;

    }

    public void update(Pet pet){
        while(true){
            long now = System.nanoTime();
            long elapsed = now-this.last;
            this.last = now;
            this.accumulator += elapsed;

            while(this.accumulator>=this.updateTime){
                this.accumulator-= this.updateTime;
                pet.live();
                System.out.println("tik");
                doPetMovement(pet);
            }
        }
    }

    public void doPetMovement(Pet pet){
        if(pet.getPosX() < 400) {
            pet.setPosX(pet.getPosX() + 5);
        }
    }
}
