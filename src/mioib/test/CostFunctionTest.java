package mioib.test;

import mioib.qap.utils.CostFunction;
import mioib.qap.utils.TestInstanceGenerator;
import mioib.qap.model.QAPInstance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CostFunctionTest {

    @Test
    void shouldReturnCorrectCostForBur26aInstance() {
        //given
        final double expectedCost = 5426670;
        final QAPInstance bur26a = TestInstanceGenerator.Bur26a();
        final List<Integer> solution = Arrays.asList(26, 15, 11, 7, 4, 12, 13, 2, 6, 18, 1, 5, 9, 21, 8, 14, 3, 20, 19, 25, 17, 10, 16, 24, 23, 22);
        //when
        final double cost = CostFunction.evaluate(bur26a, solution);
        //then
        assertEquals(expectedCost, cost);
    }

    @Test
    void shouldReturnCorrectCostForSimpleInstance() {
        //given
        final double expectedCost = 177;
        final QAPInstance simpleInstance = TestInstanceGenerator.simpleInstance();
        final List<Integer> solution = Arrays.asList(1, 2, 3);
        //when
        final double cost = CostFunction.evaluate(simpleInstance, solution);
        //then
        assertEquals(expectedCost, cost);
    }


    @Test
    @DisplayName("Should return correct cost for simpleInstance2 when not all locations assigned")
    void shouldReturnCorrectCostForSimpleInstance2WhenNotAllLocationsAssigned() {
        //given
        final double expectedCost = 4;
        final QAPInstance simpleInstance = TestInstanceGenerator.simpleInstance2();
        final List<Integer> solution = Arrays.asList(1, 0, 2);
        //when
        final double cost = CostFunction.evaluate(simpleInstance, solution);
        //then
        assertEquals(expectedCost, cost);
    }

    @Test
    @DisplayName("Should return correct cost for simpleInstance when not all locations assigned")
    void shouldReturnCorrectCostForSimpleInstanceWhenNotAllLocationsAssigned() {
        //given
        final double expectedCost = 44;
        final QAPInstance simpleInstance = TestInstanceGenerator.simpleInstance();
        final List<Integer> solution = Arrays.asList(1, 0, 2);
        //when
        final double cost = CostFunction.evaluate(simpleInstance, solution);
        //then
        assertEquals(expectedCost, cost);
    }


    @Test
    void shouldReturnCorrectCostForEveryPermutationInSimpleInstance2() {
        //given
        final double expectedCost1 = 14;
        final double expectedCost2 = 16;
        final double expectedCost3 = 18;
        final List<Integer> solution1 = Arrays.asList(1, 3, 2);
        final List<Integer> solution2 = Arrays.asList(2, 3, 1);
        final List<Integer> solution3 = Arrays.asList(1, 2, 3);
        final List<Integer> solution4 = Arrays.asList(3, 2, 1);
        final List<Integer> solution5 = Arrays.asList(2, 1, 3);
        final List<Integer> solution6 = Arrays.asList(3, 1, 2);
        final QAPInstance simpleInstance = TestInstanceGenerator.simpleInstance2();
        //when
        final double cost1 = CostFunction.evaluate(simpleInstance, solution1);
        final double cost2 = CostFunction.evaluate(simpleInstance, solution2);
        final double cost3 = CostFunction.evaluate(simpleInstance, solution3);
        final double cost4 = CostFunction.evaluate(simpleInstance, solution4);
        final double cost5 = CostFunction.evaluate(simpleInstance, solution5);
        final double cost6 = CostFunction.evaluate(simpleInstance, solution6);
        //then
        assertTrue(cost1 == expectedCost1 || cost2 == expectedCost1);
        assertTrue(cost3 == expectedCost2 || cost4 == expectedCost2);
        assertTrue(cost5 == expectedCost3 || cost6 == expectedCost3);
    }
}