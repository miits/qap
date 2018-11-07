package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.*;
import mioib.qap.utils.TestInstanceGenerator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        QAPInstance instance = TestInstanceGenerator.Bur26a();
        final ArrayList<Solver> solvers = new ArrayList<>();
        solvers.add(new RandomSolver(instance, 2 * 1000));
        solvers.add(new SteepestLocalSearchSolver(instance));
        solvers.add(new GreedyLocalSearchSolver(instance));
        solvers.add(new HeuristicSearchSolver(instance));
        solvers.add(new HeuristicSearchSolver2(instance));
        solvers.add(new HeuristicSearchSolver3(instance));
        for (Solver solver : solvers) {
            final QAPSolution solution = solver.findSolution();
            System.out.println(String.format("%s - %s", solver.getName(), solution.statistics()));
        }
    }
}
