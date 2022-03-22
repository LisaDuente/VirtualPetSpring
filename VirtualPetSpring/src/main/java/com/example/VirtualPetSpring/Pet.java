package com.example.VirtualPetSpring;


import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class Pet {

//ENUMS
    public enum MoveState{
        IDLE,
        WALKING,
        PLAYING,
        SLEEPING,
        CHEERING,
        DEAD
    }

    public enum PointState {
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
//BASICS
    private String name;
    private PointState state;
    private PetAge age;
    private MoveState moveState;

//UPDATE TIMES
    private int timeToUpdate;
    //we have 30 FPS and want an update after 1 minute, 2 minutes, 3 minutes
    final int BABYTIME = 60*TimeHandler.FPS;
    final int KIDTIME = 120*TimeHandler.FPS;
    final int ADULTTIME = 180*TimeHandler.FPS;

//EVOLVING TIMES
    private int timeToEvolve;
    final int BABYEVOLVE = 20;
    final int KIDEVOLVE = 24;
    final int DIE = 24;

//POINTS
    private int hungryPoints;
    private int boredPoints;
    private int dirtyPoints;
    private int sleepPoints;
    private int angryPoints;
    private int deathCount;

//POSITION ON SCREEN
    private int posX;
    private int posY;
    private boolean walkingRight;
    private final int SPR_SIZE_X = 48;
    private final int SPR_SIZE_Y = 48;

//ANIMATION SPEEDS / FRAMES
    private final int WALK_SPEED = 5;
    private final int WALK_FRAMES = 4;
    private final int IDLE_SPEED = 5;
    private final int IDLE_FRAMES = 4;
    private final int DEAD_SPEED = 6;
    private final int DEAD_FRAMES = 4;

//STATE LISTS
    private final MoveState[] MOVE_STATES = {MoveState.WALKING,MoveState.IDLE};


    public Pet (){

    }

    public Pet(String name){
        //BASICS
        this.name = name;
        this.age = PetAge.BABY;
        this.state = PointState.HUNGRY;
        this.moveState = MoveState.WALKING;
        //TIMES
        this.timeToUpdate = BABYTIME;
        this.timeToEvolve = 0;
        //POINTS
        this.hungryPoints = 10;
        this.boredPoints = 10;
        this.dirtyPoints = 0;
        this.sleepPoints = 0;
        this.angryPoints = 0;
        this.deathCount = 0;
        //POSITION
        this.posX = 100;
        this.posY = 200;
        this.walkingRight = false;
    }

//MOVE METHODS

    public void move(){
        switch(this.moveState){
            case WALKING:
                if(!this.walkingRight){
                    if(this.posX <= (400- SPR_SIZE_X)) {
                        this.posX += 1;
                    }else{
                        this.walkingRight = true;
                    }
                }else{
                    if(this.posX >= 100){
                        this.posX -=1;
                    }else{
                        this.walkingRight = false;
                    }
                }
                break;
            case IDLE:
                break;

        }

    }

//ANIMATION METHODS

    public int chooseWalkingSprite(){
        return (this.timeToUpdate/WALK_SPEED)% WALK_FRAMES;
    }
    public int chooseIdleSprite(){
        return (this.timeToUpdate/IDLE_SPEED)% IDLE_FRAMES;
    }
    public int chooseDeadSprite(){
        return (this.timeToUpdate/DEAD_SPEED)% DEAD_FRAMES;
    }
    public void randomMovement() {
        new Thread(() -> {
            while(true){
                if(this.age != PetAge.DEAD){
                    Random random = new Random();
                    this.moveState = this.MOVE_STATES[random.nextInt(this.MOVE_STATES.length)];
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    this.moveState = MoveState.DEAD;
                    break;
                }
            }
        }).start();

    }

//CARING METHODS
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
            this.sleepPoints = 0;
        }
    }

    public void calm(){
        if(this.angryPoints > 0){
            this.angryPoints = 0;
        }
    }

//LIVING AND EVOLVING
    public void evolve(){
        switch (this.age) {
            case BABY -> {
                System.out.println("Evolving");
                this.age = PetAge.KID;
                this.timeToUpdate = KIDTIME;
            }
            case KID -> {
                System.out.println("Evolving");
                this.age = PetAge.ADULT;
                this.timeToUpdate = ADULTTIME;
            }
            case ADULT -> {
                System.out.println("Evolving");
                this.age = PetAge.DEAD;
            }
        }
    }

    public void live(){
        this.timeToUpdate--;
        switch(this.age){
            case BABY:
                if(this.timeToUpdate <= 0){
                    chooseRandomState();
                    updatePoints();
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

    public void chooseRandomState(){
        Random random = new Random();
        PointState[] states = {PointState.BORED, PointState.SLEEPY, PointState.ANGRY, PointState.DIRTY, PointState.HUNGRY};
        this.state = states[random.nextInt(states.length)];
    }

    //updates the points on corresponding point counters
    public void updatePoints(){
        if(this.age != PetAge.DEAD){
            switch (this.state) {
                case HUNGRY:
                    this.hungryPoints++;
                    if (this.hungryPoints >= 10) {
                        this.hungryPoints = 10;
                        this.deathCount++;
                        if(this.deathCount >= 10) {
                            this.age = PetAge.DEAD;
                        }
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

//GETTER AND SETTER

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

    public PointState getState() {
        return state;
    }

    public void setState(PointState state) {
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

    public boolean isWalkingRight() {
        return walkingRight;
    }

    public MoveState getMoveState() {
        return moveState;
    }
}
