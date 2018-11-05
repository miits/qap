package mioib.qap.solver;

import mioib.qap.utils.CostFunction;
import mioib.qap.utils.NeighbourhoodFunction;
import mioib.qap.utils.RandomPermutationGenerator;
import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;

import java.util.ArrayList;

public class GreedyLocalSearchSolver implements Solver{

    private final QAPInstance instance;

    public GreedyLocalSearchSolver(QAPInstance instance) {
        this.instance = instance;
    }

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
                    break;
                }
            }
        }
        return new QAPSolution(bestScore, best);
    }
}
