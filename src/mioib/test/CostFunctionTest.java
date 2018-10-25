package mioib.test;

import mioib.qap.CostFunction;
import mioib.qap.TestInstanceGenerator;
import mioib.qap.model.QAPInstance;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}