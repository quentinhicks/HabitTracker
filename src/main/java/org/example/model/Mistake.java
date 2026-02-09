package org.example.model;

public class Mistake {
    public int day;
    public int[] failedHabits;
    public int[] suboptimalHabits;
    public String habitsParsed;
    public String mistakeMade;
    public String actionableLesson;

    public Mistake(int day) {
        this.day = day;
    }
}
