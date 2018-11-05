package mioib.qap.model;

import java.util.List;

public class QAPSolution {
    private final double cost;
    private final List<Integer> assignment;
    private final long timeMillis;
    private final long stepsCount;
    private final long solutionsChecked;

    public QAPSolution(double cost, List<Integer> assignment, long timeMillis, long solutionsChecked, long stepsCount) {
        this.cost = cost;
        this.assignment = assignment;
        this.timeMillis = timeMillis;
        this.solutionsChecked = solutionsChecked;
        this.stepsCount = stepsCount;
    }

    public double getCost() {
        return cost;
    }

    public List<Integer> getAssignment() {
        return assignment;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public long getStepsCount() {
        return stepsCount;
    }

    public long getSolutionsChecked() {
        return solutionsChecked;
    }

    public String statistics() {
        return "QAPSolution{" +
                "cost=" + cost +
                ", timeMillis=" + timeMillis +
                ", stepsCount=" + stepsCount +
                ", solutionsChecked=" + solutionsChecked +
                '}';
    }
}
