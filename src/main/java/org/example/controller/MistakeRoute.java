package org.example.controller;

import org.example.Main;
import org.example.MistakeJournal;
import org.example.model.Mistake;

import static spark.Spark.post;

public class MistakeRoute {
    public static void registerRoutes() {
        // Get mistakes from a given day
        post("/mistakes", (req, res) -> {
            int day = Main.gson.fromJson(req.body(), Integer.class);
            Mistake newMistake = new Mistake(day);
            int[][] mistakes = MistakeJournal.mistakeIterate(day);
            String phrase = MistakeJournal.mistakeParse(day);
            newMistake.suboptimalHabits = mistakes[0];
            newMistake.failedHabits = mistakes[1];
            newMistake.habitsParsed = phrase;
            res.type("application/json");
            return phrase;
        });
    }
}
