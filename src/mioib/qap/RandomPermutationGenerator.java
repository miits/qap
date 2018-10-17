package mioib.qap;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPermutationGenerator {

    public ArrayList<Integer> generate(int len) {
        ArrayList<Integer> permutation = new ArrayList<>();
        for(int i = 0; i < len; i++) {
            permutation.add(i);
        }
        for(int i = 0; i < len - 1; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, len - i);
            int randomElement = permutation.get(randomIndex);
            int currentLastIndex = len - i - 1;
            permutation.set(randomIndex, permutation.get(currentLastIndex));
            permutation.set(currentLastIndex, randomElement);
        }
        return permutation;
    }
}
