package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicSearchSolver implements Solver {

    private final QAPInstance instance;

    public HeuristicSearchSolver(QAPInstance instance) {
        this.instance = instance;
    }

    public QAPSolution findSolution() {
        final long start = System.currentTimeMillis();
        long solutionsChecked = 0;
        long stepsCount = 0;


        final int[] result = new int[instance.getSize()];
        final List<Integer> restOfLocations = IntStream
                .range(1, instance.getSize() + 1)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> restOfFacilities = IntStream
                .range(1, instance.getSize() + 1)
                .boxed()
                .collect(Collectors.toList());


        //draw start facility and his location
        final Integer randomStartFacility = restOfFacilities.get(ThreadLocalRandom.current().nextInt(instance.getSize()));
        restOfFacilities.remove(randomStartFacility);
        final Integer randomStartLocation = restOfLocations.get(ThreadLocalRandom.current().nextInt(instance.getSize()));
        restOfLocations.remove(randomStartLocation);

        result[randomStartLocation - 1] = randomStartFacility;
        stepsCount++;

        while (restOfFacilities.size() > 1) {
            double maxFlow = -1;
            Integer maxFac = -1;
            for (Integer otherFacility : restOfFacilities) {
                int flowCost = 0;
                for (int takenFacility : result) {
                    if (takenFacility == 0) {
                        continue;
                    }
                    flowCost += instance.getFlowWeight(otherFacility, takenFacility);
                    flowCost += instance.getFlowWeight(takenFacility, otherFacility);
                }
                if (flowCost > maxFlow) {
                    maxFlow = flowCost;
                    maxFac = otherFacility;
                }
            }
            double minCost = Double.MAX_VALUE;
            Integer bestLocation = -1;
            for (Integer emptyLocation : restOfLocations) {
                final int[] tmpResult = result.clone();
                tmpResult[emptyLocation - 1] = maxFac;
                final double tmpCost = CostFunction.evaluate(instance, tmpResult);
                if (tmpCost < minCost) {
                    bestLocation = emptyLocation;
                    minCost = tmpCost;
                }
            }
            result[bestLocation - 1] = maxFac;
            stepsCount++;

            restOfLocations.remove(bestLocation);
            restOfFacilities.remove(maxFac);
        }

        if (restOfFacilities.size() == 1) {
            result[restOfLocations.get(0) - 1] = restOfFacilities.get(0);
            stepsCount++;
        }


        final List<Integer> assignment = Arrays.stream(result).boxed().collect(Collectors.toList());
        final double cost = CostFunction.evaluate(instance, assignment);
        final long totalTimeMillis = System.currentTimeMillis() - start;
        return new QAPSolution(cost, assignment, totalTimeMillis, 1, stepsCount);

    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
