package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;

public class HeuristicSearchSolver {

    private final QAPInstance instance;

    public HeuristicSearchSolver(QAPInstance instance) {
        this.instance = instance;
    }

    public QAPSolution findSolution() {
        throw new RuntimeException("TODO");
    }

}
