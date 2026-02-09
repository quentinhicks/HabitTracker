package org.example;

import org.example.model.AddHab;
import org.example.model.Evaluation;
import org.example.model.Habit;
import org.example.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class SaveLogic {
    public static void saveFile(int weekNum) {
        Path source = Paths.get("data");
        Path target = Paths.get("history/" + weekNum);

        Main.week = weekNum;
        WeekLogic.save();

        try {
            Files.createDirectories(target);

            try (Stream<Path> stream = Files.list(source)) {
                stream.filter(path -> path.toString().endsWith(".json")).forEach(path -> {
                    Path targetPath = target.resolve(path.getFileName());
                    try {
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            System.out.println("All JSON files copied!");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        resetFields();
    }

    public static void resetFields() {
        HabitLogic.load();
        for (Habit h : Main.habits) {
            for (int i = 0; i < h.days.length; i++) {
                h.days[i] = 3;
            }
        }
        HabitLogic.save();

        SystemLogic.load();
        Main.eval = new Evaluation();
        SystemLogic.save();

        AddHabLogic.load();
        for (AddHab h :  Main.addHabs) {
            h.completed = false;
        }
        AddHabLogic.save();

    }

    public static void loadFile(int weekNum) {
        Path target = Paths.get("data");
        Path source = Paths.get("history/" + weekNum);

        try {
            Files.createDirectories(target);

            try (Stream<Path> stream = Files.list(source)) {
                stream.filter(path -> path.toString().endsWith(".json")).forEach(path -> {
                    Path targetPath = target.resolve(path.getFileName());
                    try {
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            Main.week = weekNum;
            WeekLogic.save();
            System.out.println("All JSON files loaded!");
            HabitLogic.load();
            AddHabLogic.load();
            WeekLogic.load();
            LevelLogic.load();
            SystemLogic.load();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
