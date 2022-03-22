package com.example.VirtualPetSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeHandlerController {
    @Autowired
    TimeHandler handler;
    @Autowired
    Pet pet;

    public TimeHandlerController(){
        this.handler = new TimeHandler();
    }

    @GetMapping("/start")
    public boolean setIsRunningTrue(){
        this.handler.setRunning(true);
        this.handler.update(pet);
        System.out.println("in controller");
        return true;
    }

    @GetMapping("/stopp")
    public void setIsRunningFalse(){
        this.handler.setRunning(false);
    }
}
