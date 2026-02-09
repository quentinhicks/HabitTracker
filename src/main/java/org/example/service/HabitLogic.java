package org.example.service;

import org.example.Main;
import org.example.model.Habit;

import java.io.*;
import java.util.Arrays;

public class HabitLogic {

    public static String FILE = "data/habits.json";
    public static void load() {
        try (Reader reader = new FileReader(FILE)) {
            Habit[] loaded = Main.gson.fromJson(reader, Habit[].class);

            if (loaded != null) {
                Main.habits.clear();
                Main.habits.addAll(Arrays.asList(loaded));

                // Ensure all have 7 days
                for (Habit h : Main.habits) {
                    System.out.println(h.name);
                    if (h.days == null || h.days.length != 7) {
                        h.days = new byte[7];
                    }
                }
            }
            System.out.println("Loaded " + Main.habits.size() + " habits from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing habits file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(FILE)) {
            Main.gson.toJson(Main.habits, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
