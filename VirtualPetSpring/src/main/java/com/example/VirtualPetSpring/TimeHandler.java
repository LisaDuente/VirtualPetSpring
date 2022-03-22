package com.example.VirtualPetSpring;

public class TimeHandler {
    private long last;
    private long accumulator;
    private long updateTime;
    public static final int FPS = 30;
    private boolean isRunning;


    public TimeHandler() {
        this.last = System.nanoTime();
        this.accumulator = 0L;
        this.updateTime = 1000000000L/TimeHandler.FPS;
        this.isRunning = false;


    }

    public void update(Pet pet) {
        pet.randomMovement();
        while (isRunning) {
            long now = System.nanoTime();
            long elapsed = now - this.last;
            this.last = now;
            this.accumulator += elapsed;

            while (this.accumulator >= this.updateTime) {
                this.accumulator -= this.updateTime;
                pet.live();
                pet.move();
            }
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}

