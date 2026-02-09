package org.example.controller;

import org.example.Main;
import org.example.service.LevelLogic;

import static spark.Spark.get;
import static spark.Spark.post;

public class LevelRoute {
    public static void registerRoutes() {
        // Increase level
        post("/increaseLevel", (req, res) -> {
            Main.level++;
            LevelLogic.save();
            res.type("application/json");
            return Main.gson.toJson((int) Main.level);
        });

        // Return habits as JSON
        get("/levels", (req, res) -> {
            LevelLogic.load();
            res.type("application/json");
//            System.out.println("I'm running + my level is " + Main.level);
            return Main.gson.toJson(Main.level);
        });
    }
}
