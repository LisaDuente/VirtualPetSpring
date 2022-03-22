package com.example.VirtualPetSpring;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameSaverJson {
    private Gson gson;
    private File save;

    public GameSaverJson(){
        this.gson = new Gson();
        this.save = new File("src/main/resources/Save.json");
    }

    public void save(Pet pet){
        String jsonString = gson.toJson(pet);
        try {
            PrintWriter writer = new PrintWriter(this.save);
            writer.println(jsonString);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Pet load(){
        Pet pet = new Pet();
        try {
            Scanner scanner = new Scanner(this.save);
            pet = gson.fromJson(scanner.nextLine(),Pet.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pet;
    }
}
