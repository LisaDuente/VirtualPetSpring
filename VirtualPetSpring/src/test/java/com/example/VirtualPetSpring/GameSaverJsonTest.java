package com.example.VirtualPetSpring;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameSaverJsonTest {
    GameSaverJson jSave = new GameSaverJson();
    Gson gson = new Gson();



    @Test
    void save() {
        //input
        Pet pet = new Pet("Emilia");
        String json = gson.toJson(pet);
        //when
        jSave.save(pet);
        //result
        File file = new File("src/main/resources/Save.json");
        try {
            Scanner scanner = new Scanner(file);
            String result = scanner.nextLine();
            assertEquals(json,result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void load() {
        //input
        Pet pet = new Pet("Emma");
        //when
        jSave.save(pet);
        pet.setName("Amanda");
        pet = jSave.load();
        //result
        assertEquals("Emma",pet.getName());
    }
}