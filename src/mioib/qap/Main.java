package mioib.qap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RandomPermutationGenerator generator = new RandomPermutationGenerator();
        ArrayList<Integer> randomPermutation = generator.generate(26);
        System.out.println(randomPermutation.toString());
    }
}
