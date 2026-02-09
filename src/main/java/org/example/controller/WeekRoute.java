package org.example.controller;

import org.example.Main;
import org.example.service.WeekLogic;

import static spark.Spark.get;

public class WeekRoute {
    public static void registerRoutes() {
        // Return habits as JSON
        get("/week", (req, res) -> {
            WeekLogic.load();
            res.type("application/json");
            return Main.gson.toJson(Main.week);
        });
    }
}
