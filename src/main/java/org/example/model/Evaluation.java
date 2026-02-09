package org.example.model;

import java.util.TreeMap;

public class Evaluation {
    public boolean isEvaluated;
    public TreeMap<String, Integer> Map;
    public boolean verdict;

    public Evaluation() {
        isEvaluated = false;
        Map = new TreeMap<>();
        verdict = false;
    }
}
