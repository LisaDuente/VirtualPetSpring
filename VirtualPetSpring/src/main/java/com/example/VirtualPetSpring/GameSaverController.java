package com.example.VirtualPetSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSaverController {
    @Autowired
    GameSaverJson saver;
    @Autowired
    Pet pet;

    public GameSaverController(GameSaverJson saver, Pet pet){
        this.saver = saver;
        this.pet = pet;
    }

    @GetMapping("/save")
    public void save(){
        saver.save(pet);
    }

    @GetMapping("/load")
    public Pet load(){
        return this.pet = saver.load();
    }
}
