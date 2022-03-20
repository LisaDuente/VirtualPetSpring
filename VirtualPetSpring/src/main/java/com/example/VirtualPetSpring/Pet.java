package com.example.VirtualPetSpring;


import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;
@Component
public class Pet {

    public enum PetState{
        HUNGRY,
        SLEEPY,
        DIRTY,
        NORMAL,
        BORED,
        ANGRY
    }
    public enum PetAge{
        BABY,
        KID,
        ADULT,
        DEAD
    }
    private String name;
    private PetState state;
    private PetAge age;

    //might be a little boring for the kid and adult because it won't happen that much
    private int timeToUpdate;
    final int BABYTIME = 6;//0;
    final int KIDTIME = 12;
    final int ADULTTIME = 18;

    private int timeToEvolve;
    //after 20 minutes
    final int BABYEVOLVE = 20;
    //after 2h
    final int KIDEVOLVE = 24;
    //after 4h
    final int DIE = 24;

    private int hungryPoints;
    private int boredPoints;
    private int dirtyPoints;
    private int sleepPoints;
    private int angryPoints;
    private int deathCount;

    private int posX;
    private int posY;
    private BufferedImage pet;
    File petPath;

    public Pet (){

    }

    public Pet(String name){
        this.name = name;
        this.age = PetAge.BABY;
        this.state = PetState.HUNGRY;
        //1 minutes until next update in living()
        this.timeToUpdate = BABYTIME;
        this.timeToEvolve = 0;
        this.hungryPoints = 10;
        this.boredPoints = 10;
        this.dirtyPoints = 0;
        this.sleepPoints = 0;
        this.angryPoints = 0;
        this.deathCount = 0;
        this.petPath = new File("src/main/resources/Sprites/Test.png");
        try {
            this.pet = ImageIO.read(petPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posX = 100;
        this.posY = 200;
    }

    //add food?
    public void feed(){
        if(this.hungryPoints > 0 && this.hungryPoints <= 10 ){
            this.hungryPoints = this.hungryPoints - 2;
            //can't be less than 0
            if(this.hungryPoints < 0){
                this.hungryPoints = 0;
            }
        }
    }

    public void clean(){
        if(this.dirtyPoints > 0 && this.dirtyPoints <= 10 ){
            this.dirtyPoints--;
            //can't be less than 0
            if(this.dirtyPoints < 0){
                this.dirtyPoints = 0;
            }
        }
    }

    //add a toy?
    public void play(){
        if(this.boredPoints > 0 && this.boredPoints <= 10 ){
            this.boredPoints= this.boredPoints - 2;
            //can't be less than 0
            if(this.boredPoints < 0){
                this.boredPoints = 0;
            }
        }
    }

    public void sleep(){
        if(this.sleepPoints > 0 && this.sleepPoints<=10){
            this.sleepPoints--;
            if(this.sleepPoints < 0){
                this.sleepPoints = 0;
            }
        }
    }

    public void calm(){
        if(this.angryPoints > 0){
            this.angryPoints = 0;
        }
    }

    public void evolve(){
        switch(this.age){
            case BABY:
                System.out.println("Evolving");
                this.age = PetAge.KID;
                this.timeToUpdate = KIDTIME;
                break;
            case KID:
                System.out.println("Evolving");
                this.age = PetAge.ADULT;
                this.timeToUpdate = ADULTTIME;
                break;
            case ADULT:
                System.out.println("Evolving");
                this.age = PetAge.DEAD;
                break;
        }
    }
    //needs time in milliseconds
    public void live(){
        this.timeToUpdate--;
        //as long as the pet is not dead
        switch(this.age){
            case BABY:
                if(this.timeToUpdate <= 0){
                    //update state
                    chooseRandomState();
                    //update points
                    updatePoints();
                    //show state and points
                    System.out.println(this.state);
                    System.out.println(getAllPoints());
                    //one minute
                    this.timeToUpdate =  BABYTIME;
                    this.timeToEvolve++;
                    if( timeToEvolve > BABYEVOLVE){
                        evolve();
                        this.timeToEvolve = 0;
                    }
                }
                break;
            case KID:
                if(this.timeToUpdate <= 0){
                    chooseRandomState();
                    updatePoints();
                    //five minutes
                    this.timeToUpdate =  KIDTIME;
                    this.timeToEvolve++;
                    if(this.timeToEvolve > KIDEVOLVE){
                        evolve();
                        this.timeToEvolve = 0;
                    }
                }
                break;
            case ADULT:
                if(this.timeToUpdate <= 0){
                    chooseRandomState();
                    updatePoints();
                    //10 minutes
                    this.timeToUpdate =  ADULTTIME;
                    this.timeToEvolve++;
                    if(this.timeToEvolve > DIE){
                        evolve();
                        this.timeToEvolve = 0;
                    }
                }
                break;
            default:
                System.out.println("Error::Pet:live()");
        }
    }

    //check this
    public void chooseRandomState(){
        Random random = new Random();
        PetState[] states = {PetState.BORED,PetState.SLEEPY,PetState.ANGRY,PetState.DIRTY,PetState.HUNGRY};
        this.state = states[random.nextInt(states.length)];
    }

    //updates the points on corresponding point counters
    public void updatePoints(){
        if(this.deathCount >= 10){
            this.age = PetAge.DEAD;
        }else {
            switch (this.state) {
                case HUNGRY:
                    if (this.hungryPoints < 10) {
                        this.hungryPoints++;
                    }else if (this.hungryPoints > 10) {
                        this.hungryPoints = 10;
                    }else if (this.hungryPoints == 10){
                        deathCount++;
                    }
                    break;
                case DIRTY:
                    if (this.dirtyPoints < 10) {
                        this.dirtyPoints++;
                    }else if (this.dirtyPoints > 10) {
                        this.dirtyPoints = 10;
                    }
                    break;
                case ANGRY:
                    if (this.angryPoints < 10) {
                        this.angryPoints++;
                    }else if (this.angryPoints > 10) {
                        this.angryPoints = 10;
                    }
                    break;
                case BORED:
                    if (this.boredPoints < 10) {
                        this.boredPoints++;
                    }else if (this.boredPoints > 10) {
                        this.boredPoints = 10;
                    }
                    break;
                case SLEEPY:
                    if (this.sleepPoints < 10) {
                        this.sleepPoints++;
                    }else if (this.sleepPoints > 10) {
                        this.sleepPoints = 10;
                    }
                    break;
                case NORMAL:
                    break;

                default:
                    System.out.println("Error::Pet:updatePoints");
            }
        }
    }

    public String getAllPoints(){
        return
                "sleep: "+this.sleepPoints
                        +"\n hungry: "+this.hungryPoints
                        +"\n angry: "+this.angryPoints
                        +"\n bored: "+this.boredPoints
                        +"\n dirty: "+this.dirtyPoints
                        +"\n death: "+this.deathCount;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", age=" + age +
                ", timeToUpdate=" + timeToUpdate +
                ", BABYTIME=" + BABYTIME +
                ", KIDTIME=" + KIDTIME +
                ", ADULTTIME=" + ADULTTIME +
                ", timeToEvolve=" + timeToEvolve +
                ", BABYEVOLVE=" + BABYEVOLVE +
                ", KIDEVOLVE=" + KIDEVOLVE +
                ", DIE=" + DIE +
                ", hungryPoints=" + hungryPoints +
                ", boredPoints=" + boredPoints +
                ", dirtyPoints=" + dirtyPoints +
                ", sleepPoints=" + sleepPoints +
                ", angryPoints=" + angryPoints +
                ", deathCount=" + deathCount +
                '}';
    }

    public PetAge getAge() {
        return age;
    }

    public void setAge(PetAge age) {
        this.age = age;
    }

    public PetState getState() {
        return state;
    }

    public void setState(PetState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAngryPoints() {
        return angryPoints;
    }

    public void setAngryPoints(int angryPoints) {
        this.angryPoints = angryPoints;
    }

    public int getHungryPoints() {
        return hungryPoints;
    }

    public void setHungryPoints(int hungryPoints) {
        this.hungryPoints = hungryPoints;
    }

    public int getBoredPoints() {
        return boredPoints;
    }

    public void setBoredPoints(int boredPoints) {
        this.boredPoints = boredPoints;
    }

    public int getDirtyPoints() {
        return dirtyPoints;
    }

    public void setDirtyPoints(int dirtyPoints) {
        this.dirtyPoints = dirtyPoints;
    }

    public int getSleepPoints() {
        return sleepPoints;
    }

    public void setSleepPoints(int sleepPoints) {
        this.sleepPoints = sleepPoints;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BufferedImage getPet() {
        return pet;
    }
}
