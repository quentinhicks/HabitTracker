package org.example;

import org.example.model.AddHab;
import org.example.model.Habit;

import java.util.TreeMap;

public class SystemsEval {

    public static byte[][] habitsArray;
    public static boolean[] addHabsArray;
    public static TreeMap<Integer, Object> habitMap;

    // Miscellaneous constants
    private final static int MAX_ID = 1000;
    private final static int NDAYS = 7;

    // Verdict tiers
    private final static int EXC = 3;
    private final static int MIN = 2;
    private final static int POOR = 1;

    // Habit tiers
    private final static int HAB_GOOD = 0;
    private final static int HAB_MIN = 1;

    public static void evaluate() {
        // create a button to press to evaluate and run
//        HabitTracker.load();
//        AddHabTracker.loadAddHabsFromFile();
        habitMap = new TreeMap<>();
        habitsArray = new byte[MAX_ID][NDAYS];
        for (Habit habit : Main.habits) {
            for (int i = 0; i < NDAYS; i++) habitsArray[habit.id][i] = habit.days[i];
            habitMap.put(habit.id, habit);
        }
        addHabsArray = new boolean[MAX_ID];
        for (AddHab addHab : Main.addHabs) {
            addHabsArray[addHab.id] = addHab.completed;
            habitMap.put(addHab.id, addHab);
        }

        // if the evaluation is a 3, then it is an exceptional score
        // if the evaluation is a 2, then it is the minimum score
        // if the evaluation is a 1, then it is failing.

        Main.eval.Map.put("BuJo", buJo());
        Main.eval.Map.put("Sleep", sleep());
        Main.eval.Map.put("Exercise", exercise());
        Main.eval.Map.put("Mental", therapy());
        Main.eval.Map.put("Academics", academics());
        Main.eval.Map.put("Career", career());

        Main.eval.isEvaluated = true;
    }

    public static void shouldLevelUp() {
        int sum = 0;
        double count = 0;
        for (int i : Main.eval.Map.values()) {
            if (i == 0) continue;
            count++;
            sum += i;
        }
        Main.eval.verdict = sum / count > (EXC + MIN)/2.0;
    }

    private static int[] count(byte[] array) {
        int[] counts = new int[2];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == HAB_GOOD) counts[HAB_GOOD]++;
            if (array[i] == HAB_MIN) counts[HAB_MIN]++;
        }
        return counts;
    }

    private static boolean evaluator(int qualGood, int qualMin, byte[] habit) {
        int[] counter = count(habit);
        boolean evaluation = counter[HAB_GOOD] >= qualGood && (counter[HAB_GOOD]
                + counter[HAB_MIN]) >= (qualGood + qualMin);
        return evaluation;
    }

    private static int state(int gQZ, int gQO, int mQZ, int mQO, int index) {
        if (!habitMap.containsKey(index)) return 0;
        // maximum qualification standards
        if (evaluator(gQZ, gQO, habitsArray[index])) return EXC;
        // minimum qualification standards
        else if (evaluator(mQZ, mQO, habitsArray[index])) return MIN;
        else return POOR;
    }

    private static int state(int index) {
        if (!habitMap.containsKey(index)) return 0;
        if (addHabsArray[index]) return EXC;
        return POOR;
    }

    public static int buJo() {
        // daily exec; good 6, 1; min 1, 6
        int exec = state(6, 1, 1, 6, 791);
        // plan abided; good 6, 1; min 1, 4
        // int abided = state(6, 1, 1, 4, 226);

        // return (exec + abided)/2;
        return exec;
    }

    public static int sleep() {
        // wake up at 8 am; good 7, 0; min 3, 4
        int cond1 = state(7, 0, 3, 4, 941);

        // go to bed at 11 pm; good 5, 2 or 6, 0; min 1, 4
        int cond2 = Math.max(state(5, 2, 1, 4, 541), state(6, 0, 1, 4, 541));

        return (Math.min(cond1, cond2));
    }

    public static int exercise() {
        // exercise; good 6, 0; min 3, 3
        return state(6, 0, 3, 3, 126);
    }

    public static int therapy() {
        // therapy writeups & medication
        return (((state(298) + state(670)) / 2));
    }

    public static int academics() {
        // academic intention; good 5, 2; min 2, 4
        return state(5, 2, 2, 4, 18);
    }

    public static int career() {
        // schedule CCD meeting;
        return state(289);
    }

}
