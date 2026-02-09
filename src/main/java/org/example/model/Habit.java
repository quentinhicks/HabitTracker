package org.example.model;

// Habit class
public class Habit {

    public String name;
    public String opDef = "";
    public byte[] days = new byte[] {3, 3, 3, 3, 3, 3, 3};
    public double xp = 0;
    public int id = (int) (Math.random() * 1000);

    Habit() {                 // Gson-friendly no-args ctor
        this.days = new byte[] {3, 3, 3, 3, 3, 3, 3};
        this.opDef = "";
    }

    Habit(String name) {
        this.name = name;
        this.opDef = "";
        this.days = new byte[] {3, 3, 3, 3, 3, 3, 3};
        this.xp = 0;
    }
}
