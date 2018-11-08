package mioib.qap.utils;

import mioib.qap.model.QAPInstance;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TestInstanceGenerator {


    public static QAPInstance fromFile(String url, List<Integer> optimalAssignment, String name) {
        try (Scanner scanner = new Scanner(new URL(url).openStream())) {
            final int size = Integer.parseInt(scanner.nextLine().trim());
            scanner.nextLine();
            Vector<Vector<Double>> distanceMatrix = new Vector<>();
            for (int i = 0; i < size; i++) {
                distanceMatrix.add(strToVector(scanner.nextLine()));
            }

            scanner.nextLine();
            Vector<Vector<Double>> flowMatrix = new Vector<>();
            for (int i = 0; i < size; i++) {
                flowMatrix.add(strToVector(scanner.nextLine()));
            }
            return new QAPInstance(name, size, flowMatrix, distanceMatrix, optimalAssignment);

        } catch (Exception e) {
            throw new RuntimeException(url, e);
        }
    }


    public static List<QAPInstance> selectedInstances() {
        final ArrayList<QAPInstance> instances = new ArrayList<>();
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/tai12a.dat", Arrays.asList(8, 1, 6, 2, 11, 10, 3, 5, 9, 7, 12, 4), "tail2a"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/tai15a.dat", Arrays.asList(5, 10, 4, 13, 2, 9, 1, 11, 12, 14, 7, 15, 3, 8, 6), "tail5a"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/tai17a.dat", Arrays.asList(12, 2, 6, 7, 4, 8, 14, 5, 11, 3, 16, 13, 17, 9, 1, 10, 15), "tail7a"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/tai20a.dat", Arrays.asList(10, 9, 12, 20, 19, 3, 14, 6, 17, 11, 5, 7, 15, 16, 18, 2, 4, 8, 13, 1), "tail20a"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26b.dat", Arrays.asList(17, 11, 26, 7, 4, 14, 6, 22, 23, 18, 5, 9, 1, 21, 8, 12, 3, 19, 20, 15, 10, 25, 24, 16, 13, 2), "bur26b"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26e.dat", Arrays.asList(14, 4, 13, 7, 16, 25, 26, 17, 1, 15, 12, 20, 18, 19, 3, 8, 21, 9, 5, 24, 6, 10, 22, 2, 23, 11), "bur26e"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26g.dat", Arrays.asList(22, 11, 2, 23, 13, 25, 24, 8, 1, 21, 20, 4, 7, 18, 12, 15, 9, 19, 5, 26, 16, 6, 14, 3, 17, 10), "bur26g"));
        instances.add(fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/nug28.dat", Arrays.asList(18, 21, 9, 1, 28, 20, 11, 3, 13, 12, 10, 19, 14, 22, 15, 2, 25, 16, 4, 23, 7, 17, 24, 26, 5, 27, 8, 6), "nug28"));
        return instances;
    }


    /**
     * http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26a.dat
     */
    public static QAPInstance Bur26a() {
        int size = 26;
        Vector<Vector<Double>> distanceMatrix = new Vector<>();
        distanceMatrix.add(strToVector("53 66 66 66 66 53 53 53 53 53 73 53 53 53 66 53 53 53 53 85 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 53 66 66 66 53 53 53 53 53 53 73 53 53 66 53 53 53 53 73 85 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 53 66 66 53 53 53 53 53 53 53 73 53 66 53 53 53 53 73 73 85 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 53 66 53 53 53 53 53 53 53 53 73 73 53 53 53 53 73 73 73 85 85 53 53"));
        distanceMatrix.add(strToVector("66 66 66 66 53 53 53 53 53 53 53 53 53 53 73 53 53 53 53 73 73 73 85 85 53 53"));
        distanceMatrix.add(strToVector("53 53 53 53 53 53 66 66 66 66 53 53 53 53 53 73 73 53 53 53 53 53 53 53 85 85"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 53 66 66 66 53 53 53 53 53 73 73 53 53 53 53 53 53 53 85 85"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 53 66 66 53 53 53 53 53 66 53 73 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 53 66 53 53 53 53 53 66 53 53 73 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 66 53 53 53 53 53 53 66 53 53 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("66 66 66 66 66 53 53 53 53 53 53 53 53 53 66 53 53 53 53 73 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 66 66 53 53 53 53 53 53 53 53 53 66 53 53 53 53 73 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 66 66 53 53 53 53 53 53 53 53 53 66 53 53 53 53 73 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 66 66 53 53 53 53 53 53 53 53 53 66 53 53 53 53 73 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 66 66 53 53 53 53 53 53 53 53 66 53 53 53 53 53 73 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 66 66 53 53 53 53 53 53 66 53 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 66 66 53 53 53 53 53 66 53 53 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 66 66 53 53 53 53 53 66 53 53 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("53 53 53 53 53 66 66 66 66 66 53 53 53 53 53 66 53 53 53 53 53 53 53 53 73 73"));
        distanceMatrix.add(strToVector("85 66 66 66 66 53 53 53 53 53 66 53 53 53 66 53 53 53 53 53 73 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 85 66 66 66 53 53 53 53 53 53 66 53 53 66 53 53 53 53 73 53 73 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 85 66 66 53 53 53 53 53 53 53 66 53 66 53 53 53 53 73 73 53 73 73 53 53"));
        distanceMatrix.add(strToVector("66 66 66 85 85 53 53 53 53 53 53 53 53 66 66 53 53 53 53 73 73 73 53 66 53 53"));
        distanceMatrix.add(strToVector("66 66 66 85 85 53 53 53 53 53 53 53 53 66 66 53 53 53 53 73 73 73 66 53 53 53"));
        distanceMatrix.add(strToVector("53 53 53 53 53 85 85 66 66 66 53 53 53 53 53 66 66 53 53 53 53 53 53 53 53 66"));
        distanceMatrix.add(strToVector("53 53 53 53 53 85 85 66 66 66 53 53 53 53 53 66 66 53 53 53 53 53 53 53 66 53"));

        Vector<Vector<Double>> flowMatrix = new Vector<>();
        flowMatrix.add(strToVector(" 47 348  316   74   12 181  338  309   35  3  84 714 367 1153   7  71 0  687  432  507 975 38   6  8  7  15"));
        flowMatrix.add(strToVector("175   9    0    4 1300  12   41   18  183  6   3 102   2    7  84   0 0  150   84   54 148  2  13  0  1  11"));
        flowMatrix.add(strToVector(" 19   0    6    9   12   0    1 3100    3  1 209   9   3    1  22   0 0    4    2    3   1  0   2  0  0   0"));
        flowMatrix.add(strToVector("575  10    5    3 2729  10   10    6 1186  0   4  48  46   30 103  11 0  102   36   34 160  2  14  0  3   1"));
        flowMatrix.add(strToVector(" 56 265  165  249   45 142  391  398 2329  4 132 747 479 4754  32  51 3 4501 1311  512 326 26 111 43  6  68"));
        flowMatrix.add(strToVector("190   3    0   10  313 112   31    4   91  2   3  76   2   16 104   5 1  163   30  257  45  2   1  0  1  16"));
        flowMatrix.add(strToVector("187   6    0    4 1889   9    7    2  138  3  33 126   7   41  39   0 0  198  199  149 100  2   1  0  5  10"));
        flowMatrix.add(strToVector("626  11    3    5 1232  12    5    4  207  2  26 230 125  204 132   1 0  596  113  596 107  5  67  0  4  12"));
        flowMatrix.add(strToVector("107  50 1029  149 2067  53  471  157    3  2 151 370 292 2100 220  18 3  250 1038 1008  25 65   2  4  0  42"));
        flowMatrix.add(strToVector("171   0    0    0  125   0    0    0    0  0   0   0   0    1  12   0 0    0    0    1  28  0   0  0  0   0"));
        flowMatrix.add(strToVector("286   4    2    0  297  11   13    7   53  0   4 118   1   11 247   4 0  113   32  218 116  1   4  0  1   3"));
        flowMatrix.add(strToVector("473  80   23   99  802  49   64   13  787  2  33 639  27   43 126  11 0    9  239  332 186 12   1  0 28  21"));
        flowMatrix.add(strToVector("378  41    3    2  567   9    5    5  581  0   5  23 244    4 101  80 0    1   44   86 101  0   6  0  0   0"));
        flowMatrix.add(strToVector("407  56   36 1458 1176 112 1045   69  534  9 171  59  57  394 184  13 0   21  496  701 194 24  36  1  5 159"));
        flowMatrix.add(strToVector(" 16  68  196  111    6  60   56  119    7  7  35 344 193  769  13  73 0  590  157  114  26 17  48  3  7 122"));
        flowMatrix.add(strToVector("188   0    0    5  111  43    0   25  107  0   2  75   2    1 146  56 0  391   13   42  44  0   0  0  0   0"));
        flowMatrix.add(strToVector("  0   0    0    0    1   0    0    0    0  0   0   0   0    0   0   0 0    1    0    0  16  0   0  0  0   0"));
        flowMatrix.add(strToVector("539 180   75  426 1251 119  211  129  548 12 212 190 122  229 367  41 0   89  443  601 314 20  77  2  3  94"));
        flowMatrix.add(strToVector("272  84 1132   18  941  46   77   51  664 10  72  47  50    9 372 278 0   62  359 1432  92 42  46  0 19  18"));
        flowMatrix.add(strToVector("381  36    5    6 2158  29   38   85  595  3  20 160  21   24 139   5 1  302  419  335 197  8  98  0  8 248"));
        flowMatrix.add(strToVector(" 35  64  213   35  144 386   94   26   10  1  38  62 270 1624   1  56 0  426  463  284   2 13   9  4  0  12"));
        flowMatrix.add(strToVector(" 22   1    0    1  523   0    0    0   97  0   0   0   0    0 525   1 0    1    0    2   1  0   4  0  0   0"));
        flowMatrix.add(strToVector("298   1    0    1  591   0    0    2  480 14   0   1   1    5 153   0 1    0    4    0  67  0   0  0  0   0"));
        flowMatrix.add(strToVector("  5   0    1    0    6   0    0    0   15  0   1   0   0    0   2  14 0    0    0    7   3  0   0  0  1   1"));
        flowMatrix.add(strToVector("  0   1    1    2   10   0    1    1    1  0   4   0  30    8   3   9 0    3   13    1   0  0   1  1  0   0"));
        flowMatrix.add(strToVector(" 37   1    1    2  400   6   10    2  177  1   5  15   3    8  19   6 0    1    7   78 529  4 101  0  1   2"));

        return new QAPInstance("bur26a", size, flowMatrix, distanceMatrix, Arrays.asList(26, 15, 11, 7, 4, 12, 13, 2, 6, 18, 1, 5, 9, 21, 8, 14, 3, 20, 19, 25, 17, 10, 16, 24, 23, 22));
    }

    public static QAPInstance simpleInstance() {
        int size = 3;
        Vector<Vector<Double>> flowMatrix = new Vector<>();
        flowMatrix.add(strToVector("1 2 3"));
        flowMatrix.add(strToVector("4 5 6"));
        flowMatrix.add(strToVector("7 8 9"));

        Vector<Vector<Double>> distanceMatrix = new Vector<>();
        distanceMatrix.add(strToVector("7 8 9"));
        distanceMatrix.add(strToVector("4 5 6"));
        distanceMatrix.add(strToVector("1 2 3"));

        return new QAPInstance("simpleInstance", size, flowMatrix, distanceMatrix, null);
    }

    public static QAPInstance simpleInstance2() {
        int size = 3;
        Vector<Vector<Double>> flowMatrix = new Vector<>();
        flowMatrix.add(strToVector("0 1 2"));
        flowMatrix.add(strToVector("1 0 3"));
        flowMatrix.add(strToVector("2 3 0"));

        Vector<Vector<Double>> distanceMatrix = new Vector<>();
        distanceMatrix.add(strToVector("0 1 2"));
        distanceMatrix.add(strToVector("1 0 1"));
        distanceMatrix.add(strToVector("2 1 0"));

        return new QAPInstance("simpleInstance2", size, flowMatrix, distanceMatrix, null);
    }

    private static Vector<Double> strToVector(String str) {
        final String[] tokenize = str.trim().replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(tokenize).map(Double::parseDouble).collect(Collectors.toCollection(Vector::new));
    }

}
