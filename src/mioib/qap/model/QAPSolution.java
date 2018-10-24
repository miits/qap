package mioib.qap.model;

import java.util.ArrayList;

public class QAPSolution {
    private final double cost;
    private final ArrayList<Integer> assignment;

    public QAPSolution(double cost, ArrayList<Integer> assignment) {
        this.cost = cost;
        this.assignment = assignment;
    }

    public double getCost() {
        return cost;
    }
}
