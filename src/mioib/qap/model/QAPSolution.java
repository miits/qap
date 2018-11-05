package mioib.qap.model;

import java.util.ArrayList;
import java.util.List;

public class QAPSolution {
    private final double cost;
    private final List<Integer> assignment;

    public QAPSolution(double cost, List<Integer> assignment) {
        this.cost = cost;
        this.assignment = assignment;
    }

    public double getCost() {
        return cost;
    }

    public List<Integer> getAssignment() {
        return assignment;
    }
}
