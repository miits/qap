package mioib.qap;

import mioib.qap.model.QAPInstance;

import java.util.ArrayList;

public class CostFunction {
    public static double evaluate(QAPInstance instance, ArrayList<Integer> assignments) {
        double cost = 0.0;
        for(int location = 0; location < assignments.size()-1; location++){
            int facility_ = assignments.get(location);
            instance.
        }

        return score;
    }
}
