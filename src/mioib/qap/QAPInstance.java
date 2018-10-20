package mioib.qap;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Vector<Vector<Double>> getFlowMatrix() {
        return flowMatrix;
    }

    public void setFlowMatrix(Vector<Vector<Double>> flowMatrix) {
        this.flowMatrix = flowMatrix;
    }

    public Vector<Vector<Double>> getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(Vector<Vector<Double>> distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}
