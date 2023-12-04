/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: rns2359
 *  email address: sikder.rahik@gmail.com
 *  TA name: Casey, the Considerate
 *  Number of slip days I am using: 0
 */

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Questions . txt
 * 
 * 1) For each value of N what is the minimum possible tree height, assuming N unique values are 
 *      inserted into the tree?
 * 
 *      The minimum possible tree height is ceil(log2(N)), where the tree is a complete binary tree.
 *      For each value look at the Experiment Results below.
 *  
 * 2) What are the average times for TreeSet? How do they compare to your BinarySearchTree?
 *      
 *      Look at Experiment Results below for times. They are very similar to the BinarySearchTree.
 * 
 * 3) How do these times compare to the times it took for you BinarySearchTree class when inserting
 *     integers in sorted order? What do you think is the cause for these differences?
 *  
 *      The TreeSet took way less time to to insert elements in sorted order than the BST.
 *      The reason for this is likely due to the TreeSet being balanced and thus maintaining a 
 *      O(logN) average add time for each element whereas the BST being unbalanced takes O(N) time
 *      for an add on a sorted element.
 * 
 */

/**
 * Experiment Data:
 * 
 * ***********************************************************************************************

    RANDOM - USING OUR BST

    N = 1000
    Height: 21      Size: 1000      Min Height: 10.0        Time: 2.8959570000000004E-4
    ---------------------------------------------------------------------------------------------
    N = 2000
    Height: 24      Size: 2000      Min Height: 11.0        Time: 2.1749159999999998E-4
    ---------------------------------------------------------------------------------------------
    N = 4000
    Height: 26      Size: 4000      Min Height: 12.0        Time: 4.405374E-4
    ---------------------------------------------------------------------------------------------
    N = 8000
    Height: 30      Size: 8000      Min Height: 13.0        Time: 8.329874000000001E-4
    ---------------------------------------------------------------------------------------------
    N = 16000
    Height: 32      Size: 16000     Min Height: 14.0        Time: 0.0017934459
    ---------------------------------------------------------------------------------------------
    N = 32000
    Height: 35      Size: 32000     Min Height: 15.0        Time: 0.0043000417
    ---------------------------------------------------------------------------------------------
    N = 64000
    Height: 38      Size: 63999     Min Height: 16.0        Time: 0.0106057333
    ---------------------------------------------------------------------------------------------
    N = 128000
    Height: 40      Size: 127998    Min Height: 17.0        Time: 0.024369525
    ---------------------------------------------------------------------------------------------
    N = 256000
    Height: 43      Size: 255992    Min Height: 18.0        Time: 0.0586993459
    ---------------------------------------------------------------------------------------------
    N = 512000
    Height: 46      Size: 511967    Min Height: 19.0        Time: 0.11397209999999999
    ---------------------------------------------------------------------------------------------
    N = 1024000
    Height: 49      Size: 1023876   Min Height: 20.0        Time: 0.36177200829999995
    ---------------------------------------------------------------------------------------------

    ***********************************************************************************************

    RANDOM - USING TREE SET

    N = 1000
            Size: 1000      Time: 4.811582E-4
    ---------------------------------------------------------------------------------------------
    N = 2000
            Size: 2000      Time: 3.19525E-4
    ---------------------------------------------------------------------------------------------
    N = 4000
            Size: 4000      Time: 5.046373E-4
    ---------------------------------------------------------------------------------------------
    N = 8000
            Size: 7999      Time: 9.956539E-4
    ---------------------------------------------------------------------------------------------
    N = 16000
            Size: 15999     Time: 0.0023481625999999998
    ---------------------------------------------------------------------------------------------
    N = 32000
            Size: 31999     Time: 0.0056318834
    ---------------------------------------------------------------------------------------------
    N = 64000
            Size: 63999     Time: 0.010167820900000001
    ---------------------------------------------------------------------------------------------
    N = 128000
            Size: 127998    Time: 0.02447145
    ---------------------------------------------------------------------------------------------
    N = 256000
            Size: 255993    Time: 0.05177281659999999
    ---------------------------------------------------------------------------------------------
    N = 512000
            Size: 511969    Time: 0.1165423292
    ---------------------------------------------------------------------------------------------
    N = 1024000
            Size: 1023873   Time: 0.33704147090000003
    ---------------------------------------------------------------------------------------------

    ***********************************************************************************************

    SORTED - USING OUR BST

    N = 1000
    Height: 999     Size: 1000      Time: 0.004077417
    ---------------------------------------------------------------------------------------------
    N = 2000
    Height: 1999    Size: 2000      Time: 0.0040585
    ---------------------------------------------------------------------------------------------
    N = 4000
    Height: 3999    Size: 4000      Time: 0.017763833
    ---------------------------------------------------------------------------------------------
    N = 8000
    Height: 7999    Size: 8000      Time: 0.062774584
    ---------------------------------------------------------------------------------------------
    N = 16000
    Height: 15999   Size: 16000     Time: 0.245972
    ---------------------------------------------------------------------------------------------
    N = 32000
    Height: 31999   Size: 32000     Time: 0.948180792
    ---------------------------------------------------------------------------------------------
    N = 64000
    Height: 63999   Size: 64000     Time: 3.70296225
    ---------------------------------------------------------------------------------------------
    N = 128000
    Height: 127999  Size: 128000    Time: 12.781012709
    ---------------------------------------------------------------------------------------------
    N = 256000
    Height: 255999  Size: 256000    Time: 53.32932725
    ---------------------------------------------------------------------------------------------
    N = 512000
    Height: 511999  Size: 512000    Time: 193.243595583
    ---------------------------------------------------------------------------------------------

    Okay I might have ran it instead of prediciting the time (only once, not average because I 
    realized that would take waaaaaaay too long). But my preditions would be based on a O(N^2) 
    timing as each add is O(N) running N times.

     N = 128_000 => Predited 14.811849 seconds
     N = 256_000 => Predicted 59.247396 seconds
     N = 512_000 => Predicted 236.989584 seconds

    ***********************************************************************************************

    SORTED - USING TREE SET

    N = 1000
            Size: 1000      Time: 7.208329999999999E-5
    ---------------------------------------------------------------------------------------------
    N = 2000
            Size: 2000      Time: 1.487501E-4
    ---------------------------------------------------------------------------------------------
    N = 4000
            Size: 4000      Time: 0.0016091125000000002
    ---------------------------------------------------------------------------------------------
    N = 8000
            Size: 8000      Time: 4.942376E-4
    ---------------------------------------------------------------------------------------------
    N = 16000
            Size: 16000     Time: 0.0011344251
    ---------------------------------------------------------------------------------------------
    N = 32000
            Size: 32000     Time: 0.0023685668
    ---------------------------------------------------------------------------------------------
    N = 64000
            Size: 64000     Time: 0.0043247584999999995
    ---------------------------------------------------------------------------------------------
    N = 128000
            Size: 128000    Time: 0.0099444041
    ---------------------------------------------------------------------------------------------
    N = 256000
            Size: 256000    Time: 0.021349095800000002
    ---------------------------------------------------------------------------------------------
    N = 512000
            Size: 512000    Time: 0.0470986959
    ---------------------------------------------------------------------------------------------

    ***********************************************************************************************
 */


/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * 
     * @param args Not used
     */
    public static void main(String[] args) {
        // runExperiments();
        runStudentTests();
    }

    private static void runExperiments() {

        // First Experiment
        System.out.println(
                "\n***********************************************************************************************\n");
        System.out.println("RANDOM - USING OUR BST");
        Stopwatch s = new Stopwatch();
        double[] experiment;
        System.out.println();
        for (int n = 1000; n <= 1_024_000; n *= 2) {
            experiment = runExp1(s, n, 10);
            System.out.println("N = " + n);
            System.out.print("Height: " + (int) experiment[1]);
            System.out.print("\tSize: " + (int) experiment[2]);
            System.out.print("\tMin Height: " + Math.ceil(Math.log(n) / Math.log(2)));
            System.out.println("\tTime: " + experiment[0]);
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
        }

        // Second Experiment
        System.out.println(
                "\n***********************************************************************************************\n");
        System.out.println("RANDOM - USING TREE SET");
        System.out.println();
        for (int n = 1000; n <= 1_024_000; n *= 2) {
            experiment = runExp2(s, n, 10);
            System.out.println("N = " + n);
            System.out.print("\tSize: " + (int) experiment[2]);
            System.out.println("\tTime: " + experiment[0]);
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
        }

        // Third Experiment
        System.out.println(
                "\n***********************************************************************************************\n");
        System.out.println("SORTED - USING OUR BST");
        System.out.println();
        for (int n = 1000; n <= 512_000; n *= 2) {
            experiment = runExp3(s, n, 10);
            System.out.println("N = " + n);
            System.out.print("Height: " + (int) experiment[1]);
            System.out.print("\tSize: " + (int) experiment[2]);
            System.out.println("\tTime: " + experiment[0]);
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
        }

        System.out.println();
        System.out.println();

        // Second Experiment
        System.out.println(
                "\n***********************************************************************************************\n");
        System.out.println("SORTED - USING TREE SET");
        System.out.println();
        for (int n = 1000; n <= 512_000; n *= 2) {
            experiment = runExp4(s, n, 10);
            System.out.println("N = " + n);
            System.out.print("\tSize: " + (int) experiment[2]);
            System.out.println("\tTime: " + experiment[0]);
            System.out.println(
                    "---------------------------------------------------------------------------------------------");
        }
        System.out.println(
                "\n***********************************************************************************************\n");

    }

    // 0 - time
    // 1 - height
    // 2 - size
    private static double[] runExp1(Stopwatch s, int n, int numLoops) {

        double[] avgs = new double[3];
        Random r = new Random();
        for (int run = 0; run < numLoops; run++) {
            // Copied from questions.txt
            BinarySearchTree<Integer> b = new BinarySearchTree<>();
            s.start();
            for (int i = 0; i < n; i++) {
                b.add(r.nextInt());
            }
            s.stop();
            avgs[0] += s.time();
            avgs[1] += b.height();
            avgs[2] += b.size();
        }

        for (int i = 0; i < avgs.length; i++) {
            avgs[i] /= numLoops;
        }

        return avgs;
    }

    // 0 - time
    // 1 - 0
    // 2 - size
    private static double[] runExp2(Stopwatch s, int n, int numLoops) {

        double[] avgs = new double[3];
        Random r = new Random();
        for (int run = 0; run < numLoops; run++) {
            // Copied from questions.txt
            TreeSet<Integer> b = new TreeSet<>();
            s.start();
            for (int i = 0; i < n; i++) {
                b.add(r.nextInt());
            }
            s.stop();
            avgs[0] += s.time();
            // Can't access Java TreeSet height
            avgs[2] += b.size();
        }

        for (int i = 0; i < avgs.length; i++) {
            avgs[i] /= numLoops;
        }

        return avgs;
    }

    // 0 - time
    // 1 - height
    // 2 - size
    private static double[] runExp3(Stopwatch s, int n, int numLoops) {

        double[] avgs = new double[3];
        for (int run = 0; run < numLoops; run++) {
            // Copied from questions.txt
            BinarySearchTree<Integer> b = new BinarySearchTree<>();
            s.start();
            for (int i = 0; i < n; i++) {
                b.iterativeAdd(i);
            }
            s.stop();
            avgs[0] += s.time();
            avgs[1] += n - 1;
            avgs[2] += n;
        }

        for (int i = 0; i < avgs.length; i++) {
            avgs[i] /= numLoops;
        }

        return avgs;
    }

    // 0 - time
    // 1 - 0
    // 2 - size
    private static double[] runExp4(Stopwatch s, int n, int numLoops) {

        double[] avgs = new double[3];
        for (int run = 0; run < numLoops; run++) {
            // Copied from questions.txt
            TreeSet<Integer> b = new TreeSet<>();
            s.start();
            for (int i = 0; i < n; i++) {
                b.add(i);
            }
            s.stop();
            avgs[0] += s.time();
            // Can't access Java TreeSet height
            avgs[2] += n;
        }

        for (int i = 0; i < avgs.length; i++) {
            avgs[i] /= numLoops;
        }

        return avgs;
    }

    // Counter for all failed tests
    // static var allows for tests to split over multiple methods
    private static int numTests;
    private static int numFailedTests;
    private static final int NUM_TESTS_PER_METHOD = 10;

    private static void runStudentTests() {
        numTests = 0;
        numFailedTests = 0;
        BinarySearchTree<String> testA = new BinarySearchTree<>();
        System.out.println("\n*********************************************************\n");
        System.out.println("                  BINARY SEARCH TREE TESTS                   ");
        System.out.println("\n*********************************************************\n");
        runConstructorTests();
        System.out.println("\n*********************************************************\n");
        runAddTests(testA);
        System.out.println("\n*********************************************************\n");
        runRemoveTests(testA);
        System.out.println("\n*********************************************************\n");
        runIsPresentTests(testA);
        System.out.println("\n*********************************************************\n");
        runSizeTests(testA);
        System.out.println("\n*********************************************************\n");
        runHeightTests(testA);
        System.out.println("\n*********************************************************\n");
        runGetAllTests(testA);
        System.out.println("\n*********************************************************\n");
        runMaxTests(testA);
        System.out.println("\n*********************************************************\n");
        runMinTests(testA);
        System.out.println("\n*********************************************************\n");
        runIterativeAddTests(testA);
        System.out.println("\n*********************************************************\n");
        runGetKthTest();
        System.out.println("\n*********************************************************\n");
        runGetAllLessThanTests(testA);
        System.out.println("\n*********************************************************\n");
        runGetAllGreaterThanTests(testA);
        System.out.println("\n*********************************************************\n");
        runNodesAtDepthTests(testA);
        System.out.println("\n*********************************************************\n");

        printResults();

    }

    private static void runConstructorTests() {
        BinarySearchTree<String> testA = new BinarySearchTree<>();

        printTestResult(-1, testA.height(), "DEFAULT CONSTRUCTOR TEST - 1");
        printTestResult(0, testA.size(), "DEFAULT CONSTRUCTOR TEST - 2");

    }

    private static void runAddTests(BinarySearchTree<String> testA) {

        testA.add("D");
        printTestResult(1, testA.size(), "ADD TEST - 1");

        testA.add("F");
        testA.add("D");
        testA.add("A");

        printTestResult(3, testA.size(), "ADD TEST - 2");

    }

    private static void runRemoveTests(BinarySearchTree<String> testA) {

        testA.remove("D");
        printTestResult(2, testA.size(), "REMOVE TEST - 1");

        printTestResult(true, testA.remove("F"), "REMOVE TEST - 2");

        printTestResult(false, testA.remove("D"), "REMOVE TEST - 3");

    }

    private static final int NUM_LETTERS = 26;
    private static final int ASCII_NUM_UPPER = 65;

    private static void runIsPresentTests(BinarySearchTree<String> testA) {
        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });
            testA.add(letter);
        }

        testA.remove("A");
        printTestResult(false, testA.isPresent("A"), "IS PRESENT TEST - 1");

        testA.add("A");
        printTestResult(true, testA.isPresent("A"), "IS PRESENT TEST - 2");
    }

    private static void runSizeTests(BinarySearchTree<String> testA) {
        testA = new BinarySearchTree<>();
        int numElements = 0;

        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });
            if (testA.add(letter)) {
                numElements++;
                printTestResult(numElements, testA.size(), "SIZE TEST - " + (numElements));
            }
        }
    }

    private static void runHeightTests(BinarySearchTree<String> testA) {
        testA = new BinarySearchTree<>();
        testA.add("C");
        printTestResult(0, testA.height(), "HEIGHT TEST - 1");

        testA.add("A");
        testA.add("D");
        printTestResult(1, testA.height(), "HEIGHT TEST - 2");

        testA.add("F");
        testA.add("G");
        printTestResult(3, testA.height(), "HEIGHT TEST - 3");

        testA.add("E");
        printTestResult(3, testA.height(), "HEIGHT TEST - 3");

    }

    private static void generateTreeAndSet1(BinarySearchTree<String> tree, List<String> list) {

        for (int i = 0; i < 20; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });
            if (tree.add(letter)) {
                list.add(letter);
            }
        }
        Collections.sort(list);
    }

    private static void runGetAllTests(BinarySearchTree<String> testA) {
        List<String> list = null;
        for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
            testA = new BinarySearchTree<>();
            list = new LinkedList<>();
            generateTreeAndSet1(testA, list);
            printTestResult(testA.getAll(), list, "GET ALL TEST - " + i);
        }
    }

    private static void runMaxTests(BinarySearchTree<String> testA) {
        List<String> list = null;
        for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
            testA = new BinarySearchTree<>();
            list = new LinkedList<>();
            generateTreeAndSet1(testA, list);
            printTestResult(testA.max(), list.get(list.size() - 1), "MAX TEST - " + i);
        }
    }

    private static void runMinTests(BinarySearchTree<String> testA) {
        List<String> list = null;
        for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
            testA = new BinarySearchTree<>();
            list = new LinkedList<>();
            generateTreeAndSet1(testA, list);
            printTestResult(testA.min(), list.get(0), "MIN TEST - " + i);
        }
    }

    private static void runIterativeAddTests(BinarySearchTree<String> testA) {

        testA = new BinarySearchTree<>();
        testA.iterativeAdd("D");
        printTestResult(1, testA.size(), "ITERATIVE ADD TEST - 1");

        testA.iterativeAdd("F");
        testA.iterativeAdd("D");
        testA.iterativeAdd("A");

        printTestResult(3, testA.size(), "ITERATIVE ADD TEST - 2");

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        HashSet<Integer> hs = new HashSet<>();
        Random r = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            int rand = r.nextInt();
            tree.iterativeAdd(rand);
            hs.add(rand);
        }
        printTestResult(tree.size(), hs.size(), "ITERATIVE ADD STRESS TEST");

    }

    private static void runGetKthTest() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 1_000; i++) {
            tree.add(i);
        }

        Integer rand = (int) (Math.random() * 1000);
        printTestResult(rand, tree.get(rand), "GET KTH TEST - 1");

        rand = (int) (Math.random() * 1000);
        printTestResult(rand, tree.get(rand), "GET KTH TEST - 2");

        rand = (int) (Math.random() * 1000);
        printTestResult(rand, tree.get(rand), "GET KTH TEST - 3");

        rand = (int) (Math.random() * 1000);
        printTestResult(rand, tree.get(rand), "GET KTH TEST - 4");

    }

    private static void generateTreeAndSet2(BinarySearchTree<String> tree, List<String> list,
            String cutoff) {

        for (int i = 0; i < 20; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            if (tree.add(letter) && letter.compareTo(cutoff) < 0) {
                list.add(letter);
            }
        }
        Collections.sort(list);
    }

    private static void runGetAllLessThanTests(BinarySearchTree<String> testA) {
        List<String> list = null;
        for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
            testA = new BinarySearchTree<>();
            list = new LinkedList<>();
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String cutoff = new String(new char[] { (char) rand });
            generateTreeAndSet2(testA, list, cutoff);
            printTestResult(
                    testA.getAllLessThan(cutoff), list, "GET ALL LESS THAN TEST - " + (i + 1));
        }
    }

    private static void generateTreeAndSet3(BinarySearchTree<String> tree, List<String> list,
            String cutoff) {

        for (int i = 0; i < 20; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            if (tree.add(letter) && letter.compareTo(cutoff) > 0) {
                list.add(letter);
            }
        }
        Collections.sort(list);
    }

    private static void runGetAllGreaterThanTests(BinarySearchTree<String> testA) {
        List<String> list = null;
        for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
            testA = new BinarySearchTree<>();
            list = new LinkedList<>();
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String cutoff = new String(new char[] { (char) rand });
            generateTreeAndSet3(testA, list, cutoff);
            printTestResult(
                    testA.getAllGreaterThan(cutoff), list,
                    "GET ALL GREATER THAN TEST - " + (i + 1));
        }
    }

    private static void runNodesAtDepthTests(BinarySearchTree<String> testA) {
        testA = new BinarySearchTree<>();
        testA.add("M");
        testA.add("F");

        testA.add("T");
        testA.add("S");
        testA.add("W");
        testA.add("R");
        testA.add("U");
        testA.add("Y");

        testA.add("D");
        testA.add("G");
        testA.add("A");
        testA.add("B");

        printTestResult(4, testA.numNodesAtDepth(2),
                "GET NODES AT DEPTH TEST - 1");
        printTestResult(4, testA.numNodesAtDepth(3),
                "GET NODES AT DEPTH TEST - 2");
        printTestResult(1, testA.numNodesAtDepth(4),
                "GET NODES AT DEPTH TEST - 3");
        printTestResult(0, testA.numNodesAtDepth(5),
                "GET NODES AT DEPTH TEST - 4");

    }

    /**
     * 
     * My overloaded methods for printing test results
     * 
     */

    private static void printResults() {
        // super cool :) formatting stuff that's probably inefficient but it really
        // doesn't
        // matter because these are small values
        int numSpaces = Integer.toString(numTests).length() -
                Integer.toString(numFailedTests).length();
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) {
            spaces.append(" ");
        }

        System.out.print(("NUMBER OF FAILED TESTS: -------------------------------------------------")
                .substring(0, 50));
        System.out.println(" " + spaces.toString() + numFailedTests);
        System.out.print(("NUMBER OF TESTS: -------------------------------------------------")
                .substring(0, 50));
        System.out.println(" " + numTests);
    }

    // Prints test results for two ints
    private static void printTestResult(int data1, int data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " FAILED";
        System.out.println(result);
        if (result.equals(" FAILED")) {
            System.out.println("Expected: " + data1);
            System.out.println("Output: " + data2);
            numFailedTests++;
        }
        numTests++;
    }

    // Prints test results for two booleans
    private static void printTestResult(boolean data1, boolean data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " FAILED";
        System.out.println(result);
        if (result.equals(" FAILED")) {
            System.out.println("Expected: " + data1);
            System.out.println("Output: " + data2);
            numFailedTests++;
        }
        numTests++;
    }

    // Prints test results for two Objects
    private static void printTestResult(Object data1, Object data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1.equals(data2)) ? " Passed" : " FAILED";
        System.out.println(result);
        if (result.equals(" FAILED")) {
            System.out.println("Expected: " + data1);
            System.out.println("Output: " + data2);
            numFailedTests++;
        }
        numTests++;
    }
}