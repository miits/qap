package mioib.qap.model;

import java.util.Objects;
import java.util.Vector;

public class QAPInstance {
    private int size;
    private Vector<Vector<Double>> flowMatrix;
    private Vector<Vector<Double>> distanceMatrix;

    public QAPInstance(int size, Vector<Vector<Double>> flowMatrix, Vector<Vector<Double>> distanceMatrix) {
        this.size = size;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    /**
     * facilities in matrix have facility-1 index (eq facility 26 is in 25 row)
     */
    public Double getFlowWeight(int facilityA, int facilityB) {
        return flowMatrix.get(facilityA-1).get(facilityB-1);
    }

    /**
     * locations in matrix have location-1 index (eq location 26 is in 25 row)
     */
    public Double getDistanceWeight(int locationA, int locationB) {
        return distanceMatrix.get(locationA-1).get(locationB-1);
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
