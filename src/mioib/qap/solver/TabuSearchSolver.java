package mioib.qap.solver;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.utils.CostFunction;
import mioib.qap.utils.NeighbourhoodFunction;
import mioib.qap.utils.RandomPermutationGenerator;

import java.util.*;

public class TabuSearchSolver implements Solver {

    private final QAPInstance instance;
    private final int tabuLength;
    private final int kCandidates;
    private final int P;

    public TabuSearchSolver(QAPInstance instance, int P) {
        this.instance = instance;
        this.tabuLength = instance.getSize() / 4;
        this.kCandidates = instance.getSize() / 10 == 0? 1 : instance.getSize() / 10;
        this.P = P;
    }

    public QAPSolution findSolution() {
        List<Integer> firstAssignment = null;
        double firstAssignmentCost = -1;
        final long start = System.currentTimeMillis();
        long solutionsChecked = 0;
        long stepsCount = 0;
        long stepsWithoutImprovement = 0;
        ArrayList<Integer> currentState = new RandomPermutationGenerator().generate(this.instance.getSize());
        double currentCost = CostFunction.evaluate(instance, currentState);
        LinkedList<Candidate> tabu = new LinkedList<>();
        while (stepsWithoutImprovement < P) {
            stepsCount++;
            final ArrayList<ArrayList<Integer>> neighbours = NeighbourhoodFunction.getNeighbourhood(instance, currentState);
            ArrayList<Candidate> candidates = new ArrayList<>(neighbours.size());
            for (ArrayList<Integer> neighbour : neighbours) {
                candidates.add(new Candidate(CostFunction.evaluate(instance, neighbour), neighbour));
            }
            candidates.sort(Comparator.comparingDouble(value -> value.score));
            final List<Candidate> topKCandidates = candidates.subList(0, kCandidates);
            for (Candidate topKCandidate : topKCandidates) {
                if (!tabu.contains(topKCandidate) && topKCandidate.score < currentCost) {
                    if (firstAssignment == null) {
                        firstAssignmentCost = topKCandidate.score;
                        firstAssignment = topKCandidate.solution;
                    }
                    currentState = topKCandidate.solution;
                    currentCost = topKCandidate.score;
                    solutionsChecked++;
                    stepsWithoutImprovement = 0;
                } else {
                    stepsWithoutImprovement++;
                }
            }
            for (Candidate topKCandidate : topKCandidates) {
                if (tabu.size() > tabuLength) {
                    tabu.addLast(topKCandidate);
                    tabu.removeFirst();
                } else {
                    tabu.addLast(topKCandidate);
                }
            }
        }
        final long totalTimeMillis = System.currentTimeMillis() - start;
        return new QAPSolution(currentCost, firstAssignmentCost, currentState, firstAssignment, totalTimeMillis, solutionsChecked, stepsCount);
    }


    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    private class Candidate {

        private double score;
        private ArrayList<Integer> solution;

        Candidate(double score, ArrayList<Integer> solution) {
            this.score = score;
            this.solution = solution;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Candidate candidate = (Candidate) o;
            return Objects.equals(solution, candidate.solution);
        }

        @Override
        public int hashCode() {

            return Objects.hash(solution);
        }
    }
}
