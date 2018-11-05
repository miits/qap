package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;

import java.util.Arrays;
import java.util.List;
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

        while (restOfFacilities.size() > 1) {
            double maxFlow = -1;
            int maxFacA = -1;
            int maxFacB = -1;
            final Integer facA = restOfFacilities.get(0);
            restOfFacilities.remove(0);
            for (Integer facB : restOfFacilities) {
                final Double flowWeightAB = instance.getFlowWeight(facA, facB);
                final Double flowWeightBA = instance.getFlowWeight(facB, facA);
                if (flowWeightAB + flowWeightBA > maxFlow) {
                    maxFlow = flowWeightAB + flowWeightBA;
                    maxFacA = facA;
                    maxFacB = facB;
                }
            }

            double minDistance = Double.MAX_VALUE;
            int minLocA = -1;
            int minLocB = -1;
            for (Integer locA : restOfLocations) {
                for (Integer locB : restOfLocations) {
                    if (locA.equals(locB)) {
                        continue;
                    }

                    final Double distanceWeightAB = instance.getDistanceWeight(locA, locB);
                    final Double distanceWeightBA = instance.getDistanceWeight(locB, locA);
                    if (distanceWeightAB + distanceWeightBA < minDistance) {
                        minDistance = distanceWeightAB + distanceWeightBA;
                        minLocA = locA;
                        minLocB = locB;
                    }
                }
            }
            result[minLocA - 1] = maxFacA;
            stepsCount++;
            result[minLocB - 1] = maxFacB;
            stepsCount++;

            restOfLocations.remove(Integer.valueOf(minLocA));
            restOfLocations.remove(Integer.valueOf(minLocB));
            restOfFacilities.remove(Integer.valueOf(maxFacB));
        }

        if (restOfFacilities.size() == 1) {
            result[restOfLocations.get(0) - 1] = restOfFacilities.get(0);
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
