package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.model.QAPSolution;
import mioib.qap.solver.*;
import mioib.qap.utils.CSVUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BenchmarkRunner {

    private static final int N_RUNS = 10;
    private final QAPInstance instance;
    private final List<String> HEADERS = Arrays.asList("timeMillis", "stepsCount", "solutionsChecked", "cost", "solution", "optimalSolutionCost", "optimalSolution");
    private final String workDir;

    public BenchmarkRunner(QAPInstance instance, String workDir) {
        this.instance = instance;
        this.workDir = workDir;

        final File file = new File(workDir);
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new RuntimeException("Unable to create dir");
            }
        }
    }

    public void runBenchmark() throws IOException {
        long steepestTimeSum = 0;
        //steepest
        String csvFile = workDir + "/steepest.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, HEADERS);
        for (int i = 0; i < N_RUNS; i++) {
            final Solver solver = new SteepestLocalSearchSolver(instance);
            final QAPSolution solution = solver.findSolution();
            steepestTimeSum += solution.getTimeMillis();
            final List<String> line = createLineFromSolution(solution);
            CSVUtils.writeLine(writer, line);
        }
        double avgSteepestTimeMillis = steepestTimeSum / N_RUNS;
        writer.close();

        //greedy
        long greedyTimeSum = 0;
        csvFile = workDir + "/greedy.csv";
        writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, HEADERS);
        for (int i = 0; i < N_RUNS; i++) {
            final Solver solver = new GreedyLocalSearchSolver(instance);
            final QAPSolution solution = solver.findSolution();
            final List<String> line = createLineFromSolution(solution);
            CSVUtils.writeLine(writer, line);
        }
        double avgGreedyTimeMillis = greedyTimeSum / N_RUNS;
        writer.close();


        //random
        int randomDurationMillis = (int) (avgGreedyTimeMillis + avgSteepestTimeMillis) / 2;
        csvFile = workDir + "/random.csv";
        writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, HEADERS);
        for (int i = 0; i < N_RUNS; i++) {
            final Solver solver = new RandomSolver(instance, randomDurationMillis);
            final QAPSolution solution = solver.findSolution();
            final List<String> line = createLineFromSolution(solution);
            CSVUtils.writeLine(writer, line);
        }
        writer.close();

        //heuristic
        csvFile = workDir + "/heuristic.csv";
        writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, HEADERS);
        for (int i = 0; i < N_RUNS; i++) {
            final Solver solver = new HeuristicSearchSolver(instance);
            final QAPSolution solution = solver.findSolution();
            final List<String> line = createLineFromSolution(solution);
            CSVUtils.writeLine(writer, line);
        }
        writer.close();
    }

    private List<String> createLineFromSolution(QAPSolution solution) {
        final ArrayList<String> line = new ArrayList<>();
        line.add(solution.getTimeMillisString());
        line.add(solution.getStepsCountString());
        line.add(solution.getSolutionsCheckedString());
        line.add(solution.getCostString());
        line.add(solution.getSolutionString());
        line.add(instance.getOptimalSolutionCostString());
        line.add(instance.getOptimalSolutionString());
        return line;
    }


}
