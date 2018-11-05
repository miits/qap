package mioib.qap.solver;

import mioib.qap.utils.CostFunction;
import mioib.qap.utils.NeighbourhoodFunction;
import mioib.qap.utils.RandomPermutationGenerator;
import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealingSearchSolver {

    private final QAPInstance instance;
    private final double alpha;
    private final double initTemp;
    private final int kSteps;

    public SimulatedAnnealingSearchSolver(QAPInstance instance, double alpha, double initTemp, int kSteps) {
        this.alpha = alpha;
        this.instance = instance;
        this.initTemp = initTemp;
        this.kSteps = kSteps;
    }

    public QAPSolution findSolution() {
        ArrayList<Integer> currentState = new RandomPermutationGenerator().generate(this.instance.getSize());
        double currentCost = CostFunction.evaluate(instance, currentState);
        double t = initTemp;
        for (int k = 0; k < kSteps; k++) {
            final ArrayList<Integer> neighbour = NeighbourhoodFunction.randomNeighbour(currentState);
            final double neighbourCost = CostFunction.evaluate(instance, neighbour);
            if (neighbourCost <= currentCost) {
                currentState = neighbour;
                currentCost = neighbourCost;
            } else {
                if (p(currentCost, neighbourCost, t) > randomChance()) {
                    currentState = neighbour;
                    currentCost = neighbourCost;
                }
            }
            t = decreaseTemp(t);
        }
        return new QAPSolution(currentCost, currentState);
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

}
