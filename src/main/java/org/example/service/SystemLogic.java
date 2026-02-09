package org.example.service;

import org.example.Main;
import org.example.model.Evaluation;

import java.io.*;

public class SystemLogic {
    public static String FILE = "data/systems.json";
    public static void load() {
        try (Reader reader = new FileReader(FILE)) {
            Evaluation loaded = Main.gson.fromJson(reader, Evaluation.class);
            if (loaded != null) {
                Main.eval = loaded;
            }
            else Main.eval = new Evaluation();
        } catch (FileNotFoundException e) {
            System.out.println("No existing systems file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(FILE)) {
            Main.gson.toJson(Main.eval, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
