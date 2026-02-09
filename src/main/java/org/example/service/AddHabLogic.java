package org.example.service;

import org.example.Main;
import org.example.model.AddHab;

import java.io.*;
import java.util.Arrays;

public class AddHabLogic {

    public static final String FILE = "data/addhabs.json";
    public static void load() {
        try (Reader reader = new FileReader(FILE)) {
            AddHab[] loaded = Main.gson.fromJson(reader, AddHab[].class);
            if (loaded != null) {
                Main.addHabs.clear();
                Main.addHabs.addAll(Arrays.asList(loaded));
            }
            System.out.println("Loaded " + Main.addHabs.size() + " habits from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing habits file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void save() {
        try (Writer writer = new FileWriter(FILE)) {
            Main.gson.toJson(Main.addHabs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
