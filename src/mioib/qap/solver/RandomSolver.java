package mioib.qap.solver;

import mioib.qap.CostFunction;
import mioib.qap.RandomPermutationGenerator;
import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;

import java.util.ArrayList;

public class RandomSolver {

    private final QAPInstance instance;
    private final int durationMillis;
    private final RandomPermutationGenerator permutationGenerator;

    public RandomSolver(QAPInstance instance, int durationMillis) {
        this.instance = instance;
        this.durationMillis = durationMillis;
        this.permutationGenerator = new RandomPermutationGenerator();
    }

    public QAPSolution findSolution() {
        final long start = System.currentTimeMillis();
        double bestCost = 999999999;
        ArrayList<Integer> bestSolution = null;
        while (System.currentTimeMillis() - start < durationMillis) {
            final ArrayList<Integer> nextSolution = permutationGenerator.generate(instance.getSize());
            final double nextSolutionCost = CostFunction.evaluate(instance, nextSolution);
            if (nextSolutionCost < bestCost) {
                bestCost = nextSolutionCost;
                bestSolution = nextSolution;
            }
        }
        return new QAPSolution(bestCost, bestSolution);
    }
}
