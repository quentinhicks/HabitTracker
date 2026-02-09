package org.example.model;

// Habit class
public class AddHab {
    public String name;
    public String opDef;
    public boolean completed;
    public double xp = 0;
    public int id = (int) (Math.random() * 1000);

    AddHab(String name) {
        this.name = name;
        this.opDef = "";
        this.completed = false;
        this.xp = 0;
    }
}
