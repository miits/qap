package mioib.qap.utils;

import mioib.qap.model.QAPInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public static ArrayList<Integer> randomNeighbour(List<Integer> permutation) {
        final ArrayList<Integer> neighbour = new ArrayList<>(permutation);
        int i = ThreadLocalRandom.current().nextInt(neighbour.size());
        int j;
        do{
            j = ThreadLocalRandom.current().nextInt(neighbour.size());
        }while(j == i);
        Collections.swap(neighbour, i, j);
        return neighbour;
    }
}
