package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.*;
import mioib.qap.utils.TestInstanceGenerator;

public class Main {

    public static void main(String[] args) {
        QAPInstance instance = TestInstanceGenerator.Bur26a();
        final Solver solver = new SimulatedAnnealingSearchSolver(instance, 0.98, 54_500, 1000);
        final QAPSolution solution = solver.findSolution();
        System.out.println(solution.getCost());
    }
}
