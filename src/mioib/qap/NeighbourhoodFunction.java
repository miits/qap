package mioib.qap;

import mioib.qap.model.QAPInstance;

import java.util.ArrayList;
import java.util.List;

public class NeighbourhoodFunction {

    public static ArrayList<ArrayList<Integer>> getNeighbourhood(QAPInstance instance, List<Integer> permutation) {
        ArrayList<ArrayList<Integer>> neighbourhood = new ArrayList<>();
        for (int i = 0; i < instance.getSize(); i++) {
            int swapElement = permutation.get(i);
            for (int j = i + 1; j < instance.getSize(); j++) {
                ArrayList<Integer> neighbour = new ArrayList<>(permutation);
                neighbour.set(i, permutation.get(j));
                neighbour.set(j, swapElement);
                neighbourhood.add(neighbour);
            }
        }
        return neighbourhood;
    }
}
