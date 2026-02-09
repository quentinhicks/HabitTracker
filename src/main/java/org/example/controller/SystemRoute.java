package org.example.controller;

import org.example.Main;
import org.example.SystemsEval;
import org.example.service.SystemLogic;

import static spark.Spark.get;

public class SystemRoute {
    public static void registerRoutes() {

        get("/isEvaluated", (req, res) -> {
            res.type("application/json");
            System.out.println("SystemRoute: I'm being evaluated! to " +Main.eval.isEvaluated);
            return Main.gson.toJson(Main.eval.isEvaluated);
        });

        // Return systems as JSON
        get("/systems", (req, res) -> {
            SystemsEval.evaluate();
            SystemLogic.save();
            res.type("application/json");
            return Main.gson.toJson(Main.eval.Map);
        });

        // Get systems evaluation
        get("/eval", (req, res) -> {
            res.type("application/json");
            SystemLogic.save();
            SystemsEval.shouldLevelUp();
            return Main.gson.toJson(Main.eval.verdict);
        });
    }
}
