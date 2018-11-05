package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.RandomSolver;
import mioib.qap.utils.TestInstanceGenerator;

public class Main {

    public static void main(String[] args) {
        QAPInstance instance = TestInstanceGenerator.Bur26a();
        final RandomSolver randomSolver = new RandomSolver(instance, 1000 * 10);
        final QAPSolution solution = randomSolver.findSolution();
        System.out.println(solution.getCost());
    }
}
