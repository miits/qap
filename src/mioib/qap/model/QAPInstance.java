package mioib.qap.model;

import mioib.qap.utils.CostFunction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class QAPInstance {
    private final String name;
    private final int size;
    private final Vector<Vector<Double>> flowMatrix;
    private final Vector<Vector<Double>> distanceMatrix;
    private final List<Integer> optimalAssignment;

    public QAPInstance(String name, int size, Vector<Vector<Double>> flowMatrix, Vector<Vector<Double>> distanceMatrix, List<Integer> optimalAssignment) {
        this.name = name;
        this.size = size;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
        this.optimalAssignment = optimalAssignment;
    }

    public String getOptimalSolutionCostString() {
        return String.valueOf(CostFunction.evaluate(this, optimalAssignment));
    }

    public String getName() {
        return name;
    }

    /**
     * facilities in matrix have facility-1 index (eq facility 26 is in 25 row)
     */
    public Double getFlowWeight(int facilityA, int facilityB) {
        return flowMatrix.get(facilityA - 1).get(facilityB - 1);
    }

    /**
     * locations in matrix have location-1 index (eq location 26 is in 25 row)
     */
    public Double getDistanceWeight(int locationA, int locationB) {
        return distanceMatrix.get(locationA - 1).get(locationB - 1);
    }

    public String getOptimalSolutionString() {
        return "'" + Arrays.toString(optimalAssignment.toArray()) + "'";
    }


    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QAPInstance that = (QAPInstance) o;
        return size == that.size &&
                Objects.equals(flowMatrix, that.flowMatrix) &&
                Objects.equals(distanceMatrix, that.distanceMatrix);
    }

    @Override
    public int hashCode() {

        return Objects.hash(size, flowMatrix, distanceMatrix);
    }
}
