package org.example.controller;

import org.example.Main;
import org.example.model.AddHab;
import org.example.service.AddHabLogic;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class AddHabRoute {
    public static void registerRoutes() {
        // Return habits as JSON
        get("/addHabs", (req, res) -> {
            res.type("application/json");
            return Main.gson.toJson(Main.addHabs);
        });

        // Add habit, Update a day's completion for a habit
        post("/updateAddHabDay", (req, res) -> {
            Map<String, Object> data = Main.gson.fromJson(req.body(), Map.class);
            int addHabIndex = ((Double) data.get("addHabIndex")).intValue();
            boolean state = (Boolean) data.get("completed");
            Main.addHabs.get(addHabIndex).completed = state;
            AddHabLogic.save();
            res.type("application/json");
            return Main.gson.toJson(Main.addHabs);
        });

        post("/addAddHab", (req, res) -> {
            AddHab habit = Main.gson.fromJson(req.body(), AddHab.class);
            habit.id = (int) (Math.random() * 1000);
            habit.opDef = "";
            Main.addHabs.add(habit);
            AddHabLogic.save();
            res.type("application/json");
            return "OK";
        });
    }
}
