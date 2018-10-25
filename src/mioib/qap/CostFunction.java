package mioib.qap;

import mioib.qap.model.QAPInstance;

import java.util.ArrayList;
import java.util.List;

public class CostFunction {

    public static double evaluate(QAPInstance instance, List<Integer> assignments) {
        double cost = 0.0;
        for (int facilityA = 1; facilityA <= assignments.size(); facilityA++) {
            for (int facilityB = 1; facilityB <= assignments.size(); facilityB++) {
                int locationA = assignments.indexOf(facilityA) + 1;
                int locationB = assignments.indexOf(facilityB) + 1;
                try {
                    cost += instance.getFlowWeight(facilityA, facilityB) * instance.getDistanceWeight(locationA, locationB);
                }catch (Exception e){
                   throw e;
                }
            }
        }
        return cost;
    }
}