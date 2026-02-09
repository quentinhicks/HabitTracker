package org.example.controller;

import org.example.Main;
import org.example.model.Habit;
import org.example.service.HabitLogic;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class HabitRoute {
    public static void registerRoutes() {
        // Return habits as JSON
        get("/habits", (req, res) -> {
            res.type("application/json");
            return Main.gson.toJson(Main.habits);
        });

        // Add habit, Update a day's completion for a habit
        post("/updateHabitDay", (req, res) -> {
            Map<String, Object> data = Main.gson.fromJson(req.body(), Map.class);
            int habitIndex = ((Double) data.get("habitIndex")).intValue();
            int dayIndex = ((Double) data.get("dayIndex")).intValue();
            byte state = ((Double) data.get("state")).byteValue();
            if (habitIndex >= 0 && habitIndex < Main.habits.size() &&
                    dayIndex >= 0 && dayIndex < 7) {
                Main.habits.get(habitIndex).days[dayIndex] = state;
                HabitLogic.save();
            }
            res.type("application/json");
            return Main.gson.toJson(Main.habits);
        });

        post("/addHabit", (req, res) -> {
            Habit habit = Main.gson.fromJson(req.body(), Habit.class);
            Main.habits.add(habit);
            HabitLogic.save();
            res.type("application/json");
            return "OK";
        });
    }
}
