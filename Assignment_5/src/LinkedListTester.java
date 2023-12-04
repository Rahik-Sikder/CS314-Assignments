/*  Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Rahik N Sikder
 *  email address: sikder.rahik@utexas.edu
 *  UTEID: rns2359
 *  Section 5 digit ID: 52679
 *  Grader name: Casey, the Remarkable 
 *  Number of slip days used on this assignment: 2
 */

/* Experiment results. CS314 students, place your experiment
 *  results here:
 * 
 * Explainations:
 * 
 * General: Since each test performed any given method N number of 
 *          times and added up those time, to find the avg time per
 *          method, the total time was divided by N. Otherwise, all
 *          the timings are off by a magnitude of N due to being
 *          added up and not averaged. 
 * 
 * -> Adding at end
 *      - Similar avg timings for both. 
 *      - Both run in O(1) time because as the input N doubles, the
 *          average time per add didn't increase by a set factor,
 *          sometimes even decreasing from the previous N.
 * 
 * -> Adding at the front
 *      - The LinkedList was much faster than the ArrayList at adding
 *          from the front.
 *      - The average time for a single add operation LinkedList did
 *          not increase for each doubling of N, suggesting it ran in
 *          O(1) time
 *      - The average time for the ArrayList add method at the front
 *          doubled for every time N doubled, suggesting it ran in 
 *          O(N) time
 * 
 * -> Removing from the front
 *      - The LinkedList was much faster than the ArrayList at removing
 *          from the front.
 *      - The average time for a single remove from front operation by
 *          the LinkedList did not increase for each doubling of N, 
 *          suggesting it ran in O(1) time
 *      - The average time for the ArrayList remove method at the front
 *          doubled for every time N doubled, suggesting it ran in 
 *          O(N) time
 * 
 * -> Getting random
 *      - The ArrayList was must faster on average on getting a random  
 *          element from the list than the LinkedList
 *      - The average time for getting a random element in the LinkedList
 *          doubled every time N doubled, suggesting the method ran in
 *          O(N) time.
 *      - The average time for getting a random element in the ArrayList
 *          stayed constant every time N doubled, suggesting the method 
 *          ran in O(1) time.  
 * 
 * -> Getting all w/ iterator
 *      - Both iterators had methods running in constant time
 *      - The order of the iterator's methods for both ArrayList and
 *          LinkedList ran in constant time as N doubled.
 *      - The time for getting all N elements using the iterator doubled
 *          as N doubled, showing that it is O(N) to retrieve _all_ the 
 *          elements from either given list using an iterator
 * 
 * -> Getting all w/ get
 *      - The get method in ArrayList ran in constant time whereas the
 *          LinkedList was much slower and doubled for every doubling of N.
 *      - This reinforces the conclusions made about the Get random method made
 *          earlier.
 *      - The time for getting _all_ N elements using the Get method doubled
 *          for the ArrayList as N doubled suggesting that it is O(N) 
 *          to retrieve _all_ the elements from ArrayList using get()
 *      - The time for getting _all_ N elements using the Get method increased
 *          by a factor of 4 the LinkedList as N doubled suggesting that it 
 *          is O(N^2) to retrieve _all_ the elements from LinkedList using get()
 * 
 * Results: 
 *  
 *
 * Number of times test run: 100
 * Adding at end: ArrayList
 * N = 30000, 		total time:  0.0148, 		avg time: 0.00000049
 * N = 60000, 		total time:  0.0279, 		avg time: 0.00000047
 * N = 120000, 		total time:  0.0803, 		avg time: 0.00000067
 * N = 240000, 		total time:  0.2437, 		avg time: 0.00000102
 * N = 480000, 		total time:  0.5028, 		avg time: 0.00000105
 *
 *
 * Number of times test run: 100
 * Adding at end: LinkedList
 * N = 30000, 		total time:  0.0146, 		avg time: 0.00000049
 * N = 60000, 		total time:  0.0286, 		avg time: 0.00000048
 * N = 120000, 		total time:  0.0557, 		avg time: 0.00000046
 * N = 240000, 		total time:  0.1822, 		avg time: 0.00000076
 * N = 480000, 		total time:  0.3012, 		avg time: 0.00000063
 *
 *
 * Number of times test run: 100
 * Adding at front: ArrayList
 * N = 2000, 		total time:  0.0171, 		avg time: 0.00000854
 * N = 4000, 		total time:  0.0624, 		avg time: 0.00001560
 * N = 8000, 		total time:  0.2455, 		avg time: 0.00003069
 * N = 16000, 		total time:  0.9768, 		avg time: 0.00006105
 * N = 32000, 		total time:  3.9280, 		avg time: 0.00012275
 *
 *
 * Number of times test run: 100
 * Adding at front: LinkedList
 * N = 10000, 		total time:  0.0059, 		avg time: 0.00000059
 * N = 20000, 		total time:  0.0181, 		avg time: 0.00000091
 * N = 40000, 		total time:  0.0208, 		avg time: 0.00000052
 * N = 80000, 		total time:  0.0407, 		avg time: 0.00000051
 * N = 160000, 		total time:  0.0803, 		avg time: 0.00000050
 *
 *
 * Number of times test run: 100
 * Removing from front: ArrayList
 * N = 2000, 		total time:  0.0163, 		avg time: 0.00000813
 * N = 4000, 		total time:  0.0619, 		avg time: 0.00001547
 * N = 8000, 		total time:  0.2438, 		avg time: 0.00003047
 * N = 16000, 		total time:  0.9742, 		avg time: 0.00006089
 * N = 32000, 		total time:  3.9066, 		avg time: 0.00012208
 *
 *
 * Number of times test run: 100
 * removing from front: LinkedList
 * N = 5000, 		total time:  0.0028, 		avg time: 0.00000056
 * N = 10000, 		total time:  0.0050, 		avg time: 0.00000050
 * N = 20000, 		total time:  0.0101, 		avg time: 0.00000050
 * N = 40000, 		total time:  0.0195, 		avg time: 0.00000049
 * N = 80000, 		total time:  0.0403, 		avg time: 0.00000050
 *
 *
 * Number of times test run: 100
 * Getting random: ArrayList
 * N = 10000, 		total time:  0.0093, 		avg time: 0.00000093
 * N = 20000, 		total time:  0.0179, 		avg time: 0.00000090
 * N = 40000, 		total time:  0.0363, 		avg time: 0.00000091
 * N = 80000, 		total time:  0.0786, 		avg time: 0.00000098
 * N = 160000, 		total time:  0.1419, 		avg time: 0.00000089
 *
 *
 * Number of times test run: 100
 * Getting random: LinkedList
 * N = 1000, 		total time:  0.0370, 		avg time: 0.00003702
 * N = 2000, 		total time:  0.1580, 		avg time: 0.00007900
 * N = 4000, 		total time:  0.6629, 		avg time: 0.00016574
 * N = 8000, 		total time:  2.7286, 		avg time: 0.00034107
 * N = 16000, 		total time: 11.0504, 		avg time: 0.00069065
 *
 *
 * Number of times test run: 100
 * Getting all using iterator: ArrayList
 * N = 50000, 		total time:  0.0037, 		avg time: 0.00000007
 * N = 100000, 		total time:  0.0054, 		avg time: 0.00000005
 * N = 200000, 		total time:  0.0106, 		avg time: 0.00000005
 * N = 400000, 		total time:  0.0217, 		avg time: 0.00000005
 * N = 800000, 		total time:  0.0365, 		avg time: 0.00000005
 *
 *
 * Number of times test run: 100
 * Getting all using iterator: LinkedList
 * N = 50000, 		total time:  0.0134, 		avg time: 0.00000027
 * N = 100000, 		total time:  0.0253, 		avg time: 0.00000025
 * N = 200000, 		total time:  0.0452, 		avg time: 0.00000023
 * N = 400000, 		total time:  0.0911, 		avg time: 0.00000023
 * N = 800000, 		total time:  0.1800, 		avg time: 0.00000022
 *
 *
 * Number of times test run: 100
 * Getting all using get method: ArrayList
 * N = 100000, 		total time:  0.0048, 		avg time: 0.00000005
 * N = 200000, 		total time:  0.0086, 		avg time: 0.00000004
 * N = 400000, 		total time:  0.0198, 		avg time: 0.00000005
 * N = 800000, 		total time:  0.0360, 		avg time: 0.00000004
 * N = 1600000, 	total time:  0.0764, 		avg time: 0.00000005
 * !! The tabbing was actually off for the last N in the output !!
 *
 * Number of times test run: 100
 * Getting all using get method: LinkedList
 * N = 1000, 		total time:  0.0357, 		avg time: 0.00003569
 * N = 2000, 		total time:  0.1556, 		avg time: 0.00007780
 * N = 4000, 		total time:  0.6498, 		avg time: 0.00016244
 * N = 8000, 		total time:  2.6879, 		avg time: 0.00033599
 * N = 16000, 		total time: 11.0185, 		avg time: 0.00068866
 *
 *  
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class LinkedListTester {

    // Counter for all failed tests
    // static var allows for tests to split over multiple methods
    private static int numTests;
    private static int numFailedTests;

    public static void main(String[] args) {

        // CS314 students. Add your tests here:

        runStudentTests();

        // CS314 Students:
        // uncomment the following line to run tests comparing
        // your LinkedList class to the java ArrayList class.
        // comparison();
    }

    private static void runStudentTests() {

        // Didn't make scream case
        final LinkedList<String> TEST_A = new LinkedList<>();
        final ArrayList<String> TEST_B = new ArrayList<>();
        numFailedTests = 0;

        System.out.println("\n*********************************************************\n");
        System.out.println("                    LINKED LIST TESTS                    ");
        System.out.println("\n*********************************************************\n");
        testGet(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testSet(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testSize(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testAdd(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testAddFirst(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testAddLast(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testInsert(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testRemove(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testRemoveFirst(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testRemoveLast(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testRemoveRange(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testRemoveElement(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testGetSublist(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testToString(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testEquals(TEST_A);
        System.out.println("\n*********************************************************\n");
        testIndexOf(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testIndexOfPos(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");
        testIterator(TEST_A, TEST_B);
        System.out.println("\n*********************************************************\n");

        printResults();

    }

    private static final int NUM_DOUBLINGS_OF_N = 5;
    private static final int NUM_REPEATS_OF_TEST = 100;

    // A method to be run to compare the LinkedList you are completing and the Java
    // ArrayList class
    private static void comparison() {
        Stopwatch s = new Stopwatch();

        int initialN = 30000;
        addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 10000;
        addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 5000;
        removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 10000;
        getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 50000;
        getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
        getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 100000;
        getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

    }

    // These pairs of methods illustrate a failure to use polymorphism
    // If the students had implemented the Java list interface there
    // could be a single method. Also we could use function objects to
    // reduce the awful repitition of code.
    private static void addEndArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: ArrayList", totalTimes, initialN);
    }

    private static void showResults(String title, double[] times, int initialN) {
        System.out.println(" *");
        System.out.println(" * Number of times test run: " + NUM_REPEATS_OF_TEST);
        System.out.println(" * " + title);
        for (double time : times) {
            System.out.print(" * N = " + initialN + ", \t\ttotal time: ");
            System.out.printf("%7.4f", time);
            System.out.print(", \t\tavg time: ");
            System.out.printf("%7.8f\n", time / initialN);
            initialN *= 2;
        }
        System.out.println(" *");
    }

    private static void addEndLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LinkedList<Integer> studentList = new LinkedList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: LinkedList", totalTimes, initialN);
    }

    private static void addFrontArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    javaList.add(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: ArrayList", totalTimes, initialN);
    }

    private static void addFrontLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LinkedList<Integer> studentList = new LinkedList<>();
                s.start();
                for (int j = 0; j < n; j++) {
                    studentList.insert(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: LinkedList", totalTimes, initialN);
    }

    private static void removeFrontArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<String> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++)
                    javaList.add(j + "");
                s.start();
                while (!javaList.isEmpty()) {
                    javaList.remove(0);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Removing from front: ArrayList", totalTimes, initialN);
    }

    private static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LinkedList<String> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j + "");
                }
                s.start();
                while (studentList.size() != 0) {
                    studentList.removeFirst();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("removing from front: LinkedList", totalTimes, initialN);
    }

    private static void getRandomArrayList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.start();
                for (int j = 0; j < n; j++) {
                    total += javaList.get(r.nextInt(javaList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: ArrayList", totalTimes, initialN);
    }

    private static void getRandomLinkedList(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            Random r = new Random();
            for (int i = 0; i < numTests; i++) {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                int total = 0;
                s.start();
                for (int j = 0; j < n; j++) {
                    total += studentList.get(r.nextInt(studentList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: LinkedList", totalTimes, initialN);
    }

    private static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests) {

        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                Iterator<Integer> it = javaList.iterator();
                s.start();
                int total = 0;
                while (it.hasNext()) {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
    }

    private static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                Iterator<Integer> it = studentList.iterator();
                s.start();
                int total = 0;
                while (it.hasNext()) {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
    }

    private static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    javaList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < javaList.size(); j++) {
                    x += javaList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: ArrayList", totalTimes, initialN);
    }

    private static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for (int i = 0; i < numTests; i++) {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    studentList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < studentList.size(); j++) {
                    x += studentList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: LinkedList", totalTimes, initialN);
    }

    /**
     * METHODS FOR STUDENT TEST
     */

    private static final int MAX_LENGTH = 25;
    private static final int NUM_TESTS_PER_METHOD = 30;

    private static void testGet(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            int randIndex = (int) (Math.random() * testA.size());
            printTestResult(testA.get(randIndex), testB.get(randIndex), "GET TEST - " + i);
        }

    }

    private static void testSet(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            int randIndex = (int) (Math.random() * testA.size());
            testA.set(randIndex, "NEW SET VALUE");
            testB.set(randIndex, "NEW SET VALUE");
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "SET TEST - " + i);
        }
    }

    private static void testSize(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            printTestResult(testA.size(), testB.size(), "SIZE TEST - " + i);
        }
    }

    private static void testAdd(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            testA.add("NEW ADD VALUE");
            testB.add("NEW ADD VALUE");
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "ADD TEST - " + i);
        }
    }

    private static void testAddFirst(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            testA.addFirst("NEW ADD VALUE");
            testB.add(0, "NEW ADD VALUE");
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "ADD FIRST TEST - " + i);
        }
    }

    private static void testAddLast(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both lists
            testA.addLast("NEW ADD VALUE");
            testB.add("NEW ADD VALUE");
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "ADD LAST TEST - " + i);
        }
    }

    private static void testInsert(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            // insert can be called at pos = size()
            int randIndex = (int) (Math.random() * (testA.size() + 1));
            testA.insert(randIndex, "NEW INSERT VALUE");
            testB.add(randIndex, "NEW INSERT VALUE");
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "INSERT TEST - " + i);
        }
    }

    private static void testRemove(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            int randIndex = (int) (Math.random() * testA.size());
            testA.remove(randIndex);
            testB.remove(randIndex);
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "REMOVE TEST - " + i);
        }
    }

    private static void testRemoveFirst(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            testA.removeFirst();
            testB.remove(0);
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "REMOVE FIRST TEST - " + i);
        }
    }

    private static void testRemoveLast(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            testA.removeLast();
            testB.remove(testB.size() - 1);
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "REMOVE LAST TEST - " + i);
        }
    }

    private static void testRemoveRange(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            int stopIndex = (int) (Math.random() * (testA.size() + 1));
            int startIndex = (int) (Math.random() * (stopIndex + 1));
            testA.removeRange(startIndex, stopIndex);
            testB.subList(startIndex, stopIndex).clear();
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "REMOVE RANGE TEST - " + i);
        }
    }

    private static void testRemoveElement(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            int randIndex = (int) (Math.random() * testA.size());
            String element = testB.get(randIndex);
            testA.remove(element);
            testB.remove(element);
            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "REMOVE ELEMENT TEST - " + i);
        }
    }

    private static void testGetSublist(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            int stopIndex = (int) (Math.random() * (testA.size() + 1));
            int startIndex = (int) (Math.random() * (stopIndex + 1));
            String[] results = listsToString(testA.getSubList(startIndex, stopIndex),
                    testB.subList(startIndex, stopIndex));
            printTestResult(results[0], results[1], "GET SUBLIST TEST - " + i);
        }
    }

    private static void testToString(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            printTestResult(testA.toString(), testB.toString(), "TO STRING TEST - " + i);
        }
    }

    private static void testEquals(LinkedList<String> testA) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset list
            testA = newResetedTestList(testA);

            // Create another LinkedList
            LinkedList<String> testB = new LinkedList<>();

            // Get iterator from testA
            Iterator<String> iterator = testA.iterator();

            while (iterator.hasNext()) {
                testB.add(iterator.next());
            }

            // Perform same action on both
            printTestResult(testA.equals(testB), true, "EQUALS TEST - " + i);
        }
    }

    private static void testIndexOf(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);
            // Perform same action on both
            int randIndex = (int) (Math.random() * testA.size() * .5);
            String element = (randIndex < testA.size()) ? testB.get(randIndex) : "NOT IN LIST";
            int index = testB.indexOf(element);
            printTestResult(testA.indexOf(element), index, "INDEX OF TEST - " + i);

        }
    }

    private static void testIndexOfPos(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            // Make some of the elements in testA repeat
            testA.makeEmpty();
            final int REPEAT_NUM = 5;
            int random = (int) (Math.random() * MAX_LENGTH) + 1;
            for (int j = 0; j < random; j++) {
                testA.add("Item " + ((int) (Math.random() * REPEAT_NUM)));
            }

            // init testB
            testB = linkedListToArrayList(testA);

            // Perform same action on both
            int elementIndex = (int) (Math.random() * testA.size() * .5); // Random element
            int randStartIndex = (int) (Math.random() * testA.size()); // Random starting pos
            String element = (elementIndex < testA.size()) ? testB.get(elementIndex) : "NOT IN LIST";

            // Sublist to deal with repeat elements before ranStartIndex
            List<String> subListB = testB.subList(randStartIndex, testB.size());

            int index = randStartIndex + subListB.indexOf(element);
            if (subListB.indexOf(element) == -1) {
                index = -1;
            }

            printTestResult(testA.indexOf(element, randStartIndex), index, "INDEX OF POS TEST - " + i);

        }
    }

    private static void testIterator(LinkedList<String> testA, ArrayList<String> testB) {
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            // Reset lists
            testA = newResetedTestList(testA);
            testB = linkedListToArrayList(testA);

            // Perform same action on both
            Iterator<String> iterA = testA.iterator();
            Iterator<String> iterB = testB.iterator();

            final int REMOVE_PERCENTAGE = 50;

            while (iterA.hasNext()) {
                iterA.next();
                iterB.next();

                if ((int) (Math.random() * 100) >= REMOVE_PERCENTAGE) {
                    iterA.remove();
                    iterB.remove();
                }
            }

            String[] results = listsToString(testA, testB);
            printTestResult(results[0], results[1], "ITERATOR TEST - " + i);

        }
    }

    private static LinkedList<String> newResetedTestList(LinkedList<String> a) {
        a.makeEmpty();
        int random = (int) (Math.random() * MAX_LENGTH) + 1;
        for (int j = 0; j < random; j++) {
            a.add("Item " + (j));
        }
        return a;
    }

    private static ArrayList<String> linkedListToArrayList(LinkedList<String> testList) {
        ArrayList<String> result = new ArrayList<>();
        Iterator<String> s = testList.iterator();
        while (s.hasNext()) {
            result.add(s.next());
        }
        return result;
    }

    private static String[] listsToString(IList<String> listA, List<String> listB) {
        String[] result = new String[2];

        StringBuilder aListString = new StringBuilder("[");

        // Exact same as the LinkedList toString implementation

        // But also need to be able to test the toString method
        // as per project requirements, and calling toString for
        // everything kind of defeats the purpose of that

        // Instantiate an iterator
        Iterator<String> iterator = listA.iterator();

        // Iterate through elements
        if (iterator.hasNext()) {
            aListString.append(iterator.next());
        }

        while (iterator.hasNext()) {
            aListString.append(", ").append(iterator.next());
        }
        aListString.append("]");

        result[0] = aListString.toString();
        result[1] = listB.toString(); // Can assume Java List toString works
        return result;
    }

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

    /**
     * 
     * My overloaded methods for printing test results
     * 
     */

    // Prints test results for two Strings
    private static void printTestResult(String data1, String data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1.equals(data2)) ? " Passed" : " FAILED";
        System.out.println(result);
        if (result.equals(" FAILED")) {
            System.out.println("Expected: " + data2);
            System.out.println("Output: " + data1);
            numFailedTests++;
        }
        numTests++;
    }

    // Prints test results for two ints
    private static void printTestResult(int data1, int data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " FAILED";
        System.out.println(result);
        if (result.equals(" FAILED")) {
            System.out.println("Expected: " + data2);
            System.out.println("Output: " + data1);
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
            System.out.println("Expected: " + data2);
            System.out.println("Output: " + data1);
            numFailedTests++;
        }
        numTests++;
    }

}