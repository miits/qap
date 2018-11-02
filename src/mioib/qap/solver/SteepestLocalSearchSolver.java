package mioib.qap.solver;

import mioib.qap.CostFunction;
import mioib.qap.NeighbourhoodFunction;
import mioib.qap.RandomPermutationGenerator;
import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;

import java.util.ArrayList;

public class SteepestLocalSearchSolver {

    private final QAPInstance instance;

    public SteepestLocalSearchSolver(QAPInstance instance) { this.instance = instance; }

    public QAPSolution findSolution() {
        ArrayList<Integer> best = new RandomPermutationGenerator().generate(this.instance.getSize());
        ArrayList<Integer> previousBest = new ArrayList<>(best);
        double bestScore = CostFunction.evaluate(this.instance, best);
        boolean stop = false;
        while (!stop) {
            stop = true;
            for (ArrayList<Integer> n : NeighbourhoodFunction.getNeighbourhood(this.instance, best)) {
                if (n.equals(previousBest)) continue;
                double nScore = CostFunction.evaluate(this.instance, n);
                if (nScore < bestScore) {
                    previousBest.clear();
                    previousBest.addAll(best);
                    best = n;
                    bestScore = nScore;
                    stop = false;
                }
            }
        }
        return new QAPSolution(bestScore, best);
    }
}
