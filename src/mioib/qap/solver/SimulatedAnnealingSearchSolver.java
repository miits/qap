package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;
import mioib.qap.utils.NeighbourhoodFunction;
import mioib.qap.utils.RandomPermutationGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealingSearchSolver implements Solver {

    private final QAPInstance instance;
    private final double alpha;
    private final double initTemp;
    private final int L;
    private final int P;

    public SimulatedAnnealingSearchSolver(QAPInstance instance, double alpha, double initTemp, int L, int P) {
        this.alpha = alpha;
        this.instance = instance;
        this.initTemp = initTemp;
        this.L = L;
        this.P = P;
    }

    public QAPSolution findSolution() {
        List<Integer> firstAssignment = null;
        double firstAssignmentCost = -1;
        final long start = System.currentTimeMillis();
        long solutionsChecked = 0;
        long stepsCount = 0;
        long stepsWithoutImprovement = 0;
        ArrayList<Integer> currentState = new RandomPermutationGenerator().generate(this.instance.getSize());
        double currentCost = CostFunction.evaluate(instance, currentState);
        double t = initTemp;
        while (t > 0.01 && stepsWithoutImprovement < P * L) {
            for (int k = 0; k < L; k++) {
                final ArrayList<Integer> neighbour = NeighbourhoodFunction.randomNeighbour(currentState);
                final double neighbourCost = CostFunction.evaluate(instance, neighbour);
                solutionsChecked++;
                if (neighbourCost <= currentCost) {
                    if (firstAssignment == null) {
                        firstAssignmentCost = neighbourCost;
                        firstAssignment = neighbour;
                    }
                    currentState = neighbour;
                    currentCost = neighbourCost;
                    stepsCount++;
                    stepsWithoutImprovement = 0;
                } else {
                    stepsWithoutImprovement++;
                    if (p(currentCost, neighbourCost, t) > randomChance()) {
                        currentState = neighbour;
                        currentCost = neighbourCost;
                        stepsCount++;
                    }
                }
            }
            t = decreaseTemp(t);
        }
        final long totalTimeMillis = System.currentTimeMillis() - start;
        return new QAPSolution(currentCost, firstAssignmentCost, currentState, firstAssignment, totalTimeMillis, solutionsChecked, stepsCount);
    }

    private double randomChance() {
        return ThreadLocalRandom.current().nextDouble(1.0);
    }


    private double p(double costOld, double costNew, double actualTemp) {
        return Math.exp(-(costNew - costOld) / actualTemp);
    }

    private double decreaseTemp(double actualTemp) {
        return actualTemp * alpha;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
