package mioib.qap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TestInstanceGenerator instanceGenerator = new TestInstanceGenerator();
        QAPInstance instance = instanceGenerator.getQAPInstance();
        RandomPermutationGenerator permutationGenerator = new RandomPermutationGenerator();
        ArrayList<Integer> assignment = permutationGenerator.generate(instance.getSize());

    }
}
