package org.example.service;

import org.example.Main;

import java.io.*;

public class LevelLogic {

    public static String FILE = "data/levels.json";
    public static void load() {
        try (Reader reader = new FileReader(FILE)) {
            Main.level = Main.gson.fromJson(reader, Integer.class);
//            System.out.println("My level is " + Main.level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(FILE)) {
            Main.gson.toJson(Main.level, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
