package org.example.service;

import org.example.Main;

import java.io.*;

public class WeekLogic {

    public static String FILE = "data/week.json";
    public static void load() {
        try (Reader reader = new FileReader(FILE)) {
            Main.week = Main.gson.fromJson(reader, Integer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(FILE)) {
            Main.gson.toJson(Main.week, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
