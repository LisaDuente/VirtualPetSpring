package com.example.VirtualPetSpring;

public class TimeHandler {
    private long last;
    private long accumulator;
    private long updateTime;
    public static final int FPS = 30;


    public TimeHandler() {
        this.last = System.nanoTime();
        this.accumulator = 0L;
        this.updateTime = 1000000000L/TimeHandler.FPS;


    }

    public void update(Pet pet) {
        pet.randomMovement();
        while (true) {
            long now = System.nanoTime();
            long elapsed = now - this.last;
            this.last = now;
            this.accumulator += elapsed;

            while (this.accumulator >= this.updateTime) {
                this.accumulator -= this.updateTime;
                pet.live();
                pet.move();
                //System.out.println("tik");
            }
        }
    }

}

