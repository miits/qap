package mioib.test;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.GreedyLocalSearchSolver;
import mioib.qap.solver.HeuristicSearchSolver;
import mioib.qap.utils.TestInstanceGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeuristicSearchSolverTest {

    @Test
    void shouldFindOptimalSolutionForSimpleInstance2() {
        //given
        final QAPInstance instance = TestInstanceGenerator.simpleInstance2();
        final double optimalCost = 14;
        final ArrayList<Integer> optimalAssignment1 = new ArrayList<>(Arrays.asList(1, 3, 2));
        final ArrayList<Integer> optimalAssignment2 = new ArrayList<>(Arrays.asList(2, 3, 1));
        final HeuristicSearchSolver solver = new HeuristicSearchSolver(instance);
        //when
        final QAPSolution solution = solver.findSolution();
        //then
        assertEquals(solution.getCost(), optimalCost);
        assertTrue(solution.getAssignment().equals(optimalAssignment1) || solution.getAssignment().equals(optimalAssignment2));
    }
}