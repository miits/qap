package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.RandomSolver;

public class Main {

    public static void main(String[] args) {
        TestInstanceGenerator instanceGenerator = new TestInstanceGenerator();
        QAPInstance instance = instanceGenerator.getQAPInstance();
        final RandomSolver randomSolver = new RandomSolver(instance, 1000 * 3);
        final QAPSolution solution = randomSolver.findSolution();
        System.out.println(solution.getCost());
    }
}
