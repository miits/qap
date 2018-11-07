package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicSearchSolver2 implements Solver {

    private final QAPInstance instance;

    public HeuristicSearchSolver2(QAPInstance instance) {
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

        //select next by max facility weight and min locations cost with already taken
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
            double minDistance = Double.MAX_VALUE;
            Integer minLocation = -1;
            for (Integer otherLocation : restOfLocations) {
                int distanceCost = 0;
                for (int i = 0; i < result.length; i++) {
                    if (result[i] == 0) {
                        continue;
                    }
                    int takenLocation = i + 1;


                    distanceCost += instance.getDistanceWeight(otherLocation, takenLocation);
                    distanceCost += instance.getDistanceWeight(takenLocation, otherLocation);
                }
                if (distanceCost < minDistance) {
                    minDistance = distanceCost;
                    minLocation = otherLocation;
                }
            }
            result[minLocation - 1] = maxFac;
            stepsCount++;

            restOfLocations.remove(minLocation);
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
