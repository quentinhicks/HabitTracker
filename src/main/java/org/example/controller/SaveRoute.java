package org.example.controller;

import org.example.Main;
import org.example.SaveLogic;
import org.example.service.WeekLogic;

import static spark.Spark.post;

public class SaveRoute {
    public static void registerRoutes() {
        // Save json file to a week folder
        post("/save", (req, res) -> {
            int weekNum = Main.gson.fromJson(req.body(), Integer.class);
            WeekLogic.save();
            SaveLogic.saveFile(weekNum);

            res.type("application/json");
            return "OK";
        });

        // Load json files from the week folder
        post("/load", (req, res) -> {
            int weekNum = Main.gson.fromJson(req.body(), Integer.class);
            Main.week = weekNum;
            SaveLogic.loadFile(weekNum);
            res.type("application/json");
            return "OK";
        });
    }
}
