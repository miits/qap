package mioib.qap.utils;

import mioib.qap.model.QAPInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CostFunction {

    public static double evaluate(QAPInstance instance, List<Integer> assignments) {
        double cost = 0.0;
        for (int facilityA = 1; facilityA <= assignments.size(); facilityA++) {
            for (int facilityB = 1; facilityB <= assignments.size(); facilityB++) {
                cost += calculateCost(instance, assignments, facilityA, facilityB);
            }
        }
        return cost;
    }

    public static double evaluate(QAPInstance instance, int[] assignments) {
        final List<Integer> assignmentList = Arrays.stream(assignments).boxed().collect(Collectors.toList());
        return CostFunction.evaluate(instance, assignmentList);
    }

    public static double evaluateDelta(QAPInstance instance, int[] assignments, int facilityAToSwitch, int facilityBToSwitch) {
        final List<Integer> assignmentList = Arrays.stream(assignments).boxed().collect(Collectors.toList());
        return CostFunction.evaluateDelta(instance, assignmentList, facilityAToSwitch, facilityBToSwitch);
    }

    public static double evaluateDelta(QAPInstance instance, List<Integer> assignments, int facilityAToSwitch, int facilityBToSwitch) {
        double originalCost = evaluate(instance, assignments);
        List<Integer> assignmentsAfterSwitch = switchFacilities(assignments, facilityAToSwitch, facilityBToSwitch);
        double cost = evaluate(instance, assignmentsAfterSwitch);
        double delta = cost - originalCost;
        return delta;
    }

    private static List<Integer> switchFacilities(List<Integer> assignments, int facilityAToSwitch, int facilityBToSwitch) {
        List<Integer> assignmentsAfterSwitch = new ArrayList<>();
        for (int facility: assignments) {
            assignmentsAfterSwitch.add(facility);
        }
        assignmentsAfterSwitch.set(facilityAToSwitch, assignments.get(facilityBToSwitch));
        assignmentsAfterSwitch.set(facilityBToSwitch, assignments.get(facilityAToSwitch));
        return assignmentsAfterSwitch;
    }

    private static double calculateCost(QAPInstance instance, List<Integer> assignments, int facilityA, int facilityB) {
        int locationA = assignments.indexOf(facilityA) + 1;
        int locationB = assignments.indexOf(facilityB) + 1;
        if (facilityA == 0 || facilityB == 0 || locationA == 0 || locationB == 0) {
            return 0.0;
        }
        final Double flowWeight = instance.getFlowWeight(facilityA, facilityB);
        final Double distanceWeight = instance.getDistanceWeight(locationA, locationB);
        return flowWeight * distanceWeight;
    }
}
