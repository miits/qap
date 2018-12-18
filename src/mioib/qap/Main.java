package mioib.qap;

import mioib.qap.model.QAPInstance;
import mioib.qap.utils.TestInstanceGenerator;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        final List<QAPInstance> instances = TestInstanceGenerator.selectedInstances();
        for (QAPInstance instance : instances) {
            final long start = System.currentTimeMillis();
            System.out.println("Instance: " + instance.getName());
            final BenchmarkRunner benchmarkRunner = new BenchmarkRunner(instance, "result");
            benchmarkRunner.runBenchmark();
            System.out.println("Total time: " + (System.currentTimeMillis() - start) + " [ms]");
        }
    }
}
