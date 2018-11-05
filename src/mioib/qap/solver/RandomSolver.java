package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;
import mioib.qap.utils.RandomPermutationGenerator;

import java.util.ArrayList;

public class RandomSolver implements Solver {

    private final QAPInstance instance;
    private final int durationMillis;
    private final RandomPermutationGenerator permutationGenerator;

    public RandomSolver(QAPInstance instance, int durationMillis) {
        this.instance = instance;
        this.durationMillis = durationMillis;
        this.permutationGenerator = new RandomPermutationGenerator();
    }

    public QAPSolution findSolution() {
        long solutionsChecked = 0;
        long stepsCount = 0;
        final long start = System.currentTimeMillis();
        double bestCost = Double.MAX_VALUE;
        ArrayList<Integer> bestSolution = null;
        while (System.currentTimeMillis() - start < durationMillis) {
            solutionsChecked++;
            stepsCount++;
            final ArrayList<Integer> nextSolution = permutationGenerator.generate(instance.getSize());
            final double nextSolutionCost = CostFunction.evaluate(instance, nextSolution);
            if (nextSolutionCost < bestCost) {
                bestCost = nextSolutionCost;
                bestSolution = nextSolution;
            }
        }
        return new QAPSolution(bestCost, bestSolution, this.durationMillis, solutionsChecked, stepsCount);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
