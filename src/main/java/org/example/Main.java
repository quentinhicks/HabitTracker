package org.example;

import com.google.gson.Gson;
import org.example.controller.*;
import org.example.model.AddHab;
import org.example.model.Evaluation;
import org.example.model.Habit;
import org.example.service.*;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Main {
    // Level information
    public static Integer level;
    public static Integer week;
    public static List<Habit> habits = new ArrayList<>();
    public static List<AddHab> addHabs = new ArrayList<>();
    public static Evaluation eval = new Evaluation();

    // Json object to read from json file and convert to Java
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        port(4567);

        // Serve static files from resources/public
        staticFiles.location("/public");

        HabitLogic.load();
        LevelLogic.load();
        AddHabLogic.load();
        WeekLogic.load();
        SystemLogic.load();

        HabitRoute.registerRoutes();
        AddHabRoute.registerRoutes();
        LevelRoute.registerRoutes();
        MistakeRoute.registerRoutes();
        SaveRoute.registerRoutes();
        SystemRoute.registerRoutes();
        WeekRoute.registerRoutes();
    }
}
