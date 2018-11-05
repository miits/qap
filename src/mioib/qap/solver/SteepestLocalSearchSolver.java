package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;
import mioib.qap.utils.NeighbourhoodFunction;
import mioib.qap.utils.RandomPermutationGenerator;

import java.util.ArrayList;

public class SteepestLocalSearchSolver implements Solver {

    private final QAPInstance instance;

    public SteepestLocalSearchSolver(QAPInstance instance) {
        this.instance = instance;
    }

    public QAPSolution findSolution() {
        final long start = System.currentTimeMillis();
        long solutionsChecked = 0;
        long stepsCount = 0;
        ArrayList<Integer> best = new RandomPermutationGenerator().generate(this.instance.getSize());
        ArrayList<Integer> previousBest = new ArrayList<>(best);
        double bestScore = CostFunction.evaluate(this.instance, best);
        boolean stop = false;
        while (!stop) {
            stop = true;
            stepsCount++;
            for (ArrayList<Integer> n : NeighbourhoodFunction.getNeighbourhood(this.instance, best)) {
                if (n.equals(previousBest)) continue;
                double nScore = CostFunction.evaluate(this.instance, n);
                solutionsChecked++;
                if (nScore < bestScore) {
                    previousBest.clear();
                    previousBest.addAll(best);
                    best = n;
                    bestScore = nScore;
                    stop = false;
                }
            }
        }
        final long totalTimeMillis = System.currentTimeMillis() - start;
        return new QAPSolution(bestScore, best, totalTimeMillis, solutionsChecked, stepsCount);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
