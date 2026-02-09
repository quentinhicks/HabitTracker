package org.example;

import org.example.model.Habit;

import java.util.LinkedList;

public class MistakeJournal {
    public static void MistakeLog() {
    }

    // Iterate through the mistakes on each habit's day
    public static int[][] mistakeIterate(int day) {
        LinkedList<Integer> mistakesFail = new LinkedList<Integer>();
        LinkedList<Integer> mistakesSub = new LinkedList<Integer>();// mistakes[0] is suboptimal (1), mistakes[1] is fail (2)
        for (Habit h : Main.habits) {
            if (h.days[day] == 1) {
                mistakesSub.add(h.id);
            }
            if (h.days[day] == 2) {
                mistakesFail.add(h.id);
            }
        }
        Integer[][] mistakeArray = new Integer[2][];
        mistakeArray[0] = mistakesSub.toArray(new Integer[0]);
        mistakeArray[1] = mistakesSub.toArray(new Integer[0]);
        int[][] mistakes = new int[2][];
        mistakes[0] = new int[mistakesSub.size()];
        mistakes[1] = new int[mistakesFail.size()];

        for (int i = 0; i < mistakesSub.size(); i++) {
            mistakes[0][i] = mistakeArray[0][i];
        }
        for (int i = 0; i < mistakesFail.size(); i++) {
            mistakes[1][i] = mistakeArray[1][i];
        }
        return mistakes;
    }

    // Iterate through the mistakes on each habit's day
    public static String mistakeParse(int day) {
        LinkedList<Integer> mistakesFail = new LinkedList<Integer>();
        LinkedList<Integer> mistakesSub = new LinkedList<Integer>();// mistakes[0] is suboptimal (1), mistakes[1] is fail (2)
        for (Habit h : Main.habits) {
            if (h.days[day] == 1) {
                mistakesSub.add(h.id);
            }
            if (h.days[day] == 2) {
                mistakesFail.add(h.id);
            }
        }
        Integer[][] mistakeArray = new Integer[2][];
        mistakeArray[0] = mistakesSub.toArray(new Integer[0]);
        mistakeArray[1] = mistakesSub.toArray(new Integer[0]);
        int[][] mistakes = new int[2][];
        mistakes[0] = new int[mistakesSub.size()];
        mistakes[1] = new int[mistakesFail.size()];
        String phrase = "";
        for (int i = 0; i < mistakesSub.size(); i++) {
            mistakes[0][i] = mistakeArray[0][i];
            phrase += mistakes[0][i];
            if (mistakesFail.isEmpty() && i == mistakesSub.size() - 1) {
                continue;
            }
            phrase += ", ";

        }
        for (int i = 0; i < mistakesFail.size(); i++) {
            mistakes[1][i] = mistakeArray[1][i];
            phrase += mistakes[1][i];
            if (i ==  mistakesFail.size() - 1) {
                continue;
            }
            phrase += ", ";
        }
        return phrase;
    }

}
