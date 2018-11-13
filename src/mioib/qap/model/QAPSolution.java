package mioib.qap.model;

import java.util.Arrays;
import java.util.List;

public class QAPSolution {
    private final double cost;
    private final double firstAssignmentCost;
    private final List<Integer> assignment;
    private final List<Integer> firstAssignment;
    private final long timeMillis;
    private final long stepsCount;
    private final long solutionsChecked;

    public QAPSolution(double cost, double firstAssignmentCost, List<Integer> assignment, List<Integer> firstAssignment, long timeMillis, long solutionsChecked, long stepsCount) {
        this.cost = cost;
        this.firstAssignmentCost = firstAssignmentCost;
        this.assignment = assignment;
        this.firstAssignment = firstAssignment;
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


    public String getTimeMillisString() {
        return String.valueOf(timeMillis);
    }

    public String getStepsCountString() {
        return String.valueOf(stepsCount);
    }

    public String getSolutionsCheckedString() {
        return String.valueOf(solutionsChecked);
    }

    public String getCostString() {
        return String.valueOf(cost);
    }

    public String getSolutionString() {
        return "'" + Arrays.toString(assignment.toArray()) + "'";
    }

    public String getFirstAssignmentCost() {
        return String.valueOf(firstAssignmentCost);
    }

    public String getFirstAssignmentString() {
        return "'" + Arrays.toString(firstAssignment.toArray()) + "'";
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
