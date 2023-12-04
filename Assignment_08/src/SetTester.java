/*  Student information for assignment:
 *
 *  On MY honor, RAHIK N SIKDER, 
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  TA name: Casey, the Dedicated
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: 
 * 
 * CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * 
 *      It would be unwise to implement all three of the intersection, union, and difference 
 *      methods because it would lead to circular logic. 
 * 
 *      In order to implement any one of those methods, it relies on the calling of one of the 
 *      other two because AbstractSet cannot instantiate an instance of itself or the interface it 
 *      implements. 
 * 
 *      Similarly, AbstractSet cannot create instances of UnsortedSet or SortedSet by the 
 *      assignment guidelines.
 * 
 *      Therefore, implementing all three would result in a circular logic pattern that would not
 *      actually create an ISet object.
 * 
 * Experiments:
 * 
 * What do you think the order (Big O) of the two processText methods are for each kind of Set? 
 * Assume N = total number of words in a file and M = number of distinct words in the file.
 * M = the size of the set when finished.
 * 
 *      I think the processText methods for SortedSet and UnsortedSet was O(N*M). However, 
 *       internal implementation of add() and contains() contributed to a lower T(N) in the
 *       SortedSet that ultimately led to optimizations that can be seen the time difference
 *       between both sets.
 * 
 *      I think the order for the HashSet and TreeSet processText methods were O(N) and O(NlogN) 
 *       respectively.
 * 
 * What are the orders (Big O) of your add methods? What do you think the Big O of the HashSet 
 * and TreeSet add methods are?
 *      
 *      The SortedSet add is O(N) and the UnsortedSet add is also O(N)
 *      The HashSet add method is likely O(1) whereas the TreeSet add method is likely O(logN)
 * 
 * What are the differences between HashSet and TreeSet when printing out the contents of the Set?
 *  
 *      The major difference will be that the TreeSet will print out the contents of the Set
 *       in sorted order through an internal binary search tree whereas the HashSet will store 
 *       its elements in an unsorted order that is unknown until runtime.
 *      Additionally, 
 *      The HashSet will be able to print out the contents of the set in O(N) time since the
 *       retrival of a single element is done in O(1) time. The TreeSet, however, will print 
 *       out the contents of the set in O(NlogN) time as the retrival takes place in O(logN) 
 *       time
 * 
 * Sorted Set
 * File             Size (kb)   Total Words   Inc.  Unique Words   Inc.   Actual Time   Inc.
 * 
 * small_test.txt   110         20540         -     3747           -      0.026 sec.    -     
 * medium_test.txt  278         51888         2.5x  6663           1.8x   0.051 sec.    1.96x 
 * The_Trial.txt    470         86847         1.7x  9434           1.4x   0.060 sec.    1.18x
 * Pride_and_p.txt  712         125007        1.4x  13754          1.5x   0.075 sec.    1.15x
 * Tale_of_two_txt  791         138923        1.1x  19632          1.4x   0.093 sec.    1.24x
 * 
 * 
 * Unsorted Set
 * File             Size (kb)   Total Words   Inc.  Unique Words   Inc.   Actual Time   Inc.
 * 
 * small_test.txt   110         20540         -     3747           -      0.054 sec.    -     
 * medium_test.txt  278         51888         2.5x  6663           1.8x   0.117 sec.    2.17x
 * The_Trial.txt    470         86847         1.7x  9434           1.4x   0.253 sec.    2.16x
 * Pride_and_P.txt  712         125007        1.4x  13754          1.5x   0.520 sec.    2.06x
 * Tale_of_two_txt  791         138923        1.1x  19632          1.4x   1.167 sec.    2.24x
 * 
 * 
 * HashSet
 * File             Size (kb)   Total Words   Inc.  Unique Words   Inc.   Actual Time   Inc.
 * 
 * small_test.txt   110         20540         -     3747           -     0.014 sec.    -     
 * medium_test.txt  278         51888         2.5x  6663           1.8x  0.021 sec.    1.5x
 * The_Trial.txt    470         86847         1.7x  9434           1.4x  0.032 sec.    1.52x
 * Pride_and_P.txt  712         125007        1.4x  13754          1.5x  0.048 sec.    1.5x
 * Tale_of_Two_txt  791         138923        1.1x  19632          1.4x  0.048 sec.    1x
 * 
 * 
 * TreeSet
 * File             Size (kb)   Total Words   Inc.  Unique Words   Inc.   Actual Time   Inc.
 * 
 * small_test.txt   110         20540         -     3747           -      0.017 sec.    -     
 * medium_test.txt  278         51888         2.5x  6663           1.8x   0.025 sec.    1.47x
 * The_Trial.txt    470         86847         1.7x  9434           1.4x   0.034 sec.    1.36x
 * Pride_and_p.txt  712         125007        1.4x  13754          1.5x   0.042 sec.    1.23x
 * Tale_of_two_txt  791         138923        1.1x  19632          1.4x   0.047 sec.    1.12x
 * 
 */

public class SetTester {

    // Counter for all failed tests
    // static var allows for tests to split over multiple methods
    private static int numTests;
    private static int numFailedTests;
    private static final int NUM_TESTS_PER_METHOD = 10;

    public static void main(String[] args) {
        // runExperiments();
        runStudentTests();
    }

    private static void runStudentTests() {
        numTests = 0;
        numFailedTests = 0;
        System.out.println("\n*********************************************************\n");
        System.out.println("                     ABSTRACT SET TESTS                      ");
        System.out.println("\n*********************************************************\n");
        testAbstractAddAll();
        System.out.println("\n*********************************************************\n");
        testAbstractClear();
        System.out.println("\n*********************************************************\n");
        testAbstractContains();
        System.out.println("\n*********************************************************\n");
        testAbstractContainsAll();
        System.out.println("\n*********************************************************\n");
        testAbstractEquals();
        System.out.println("\n*********************************************************\n");
        testAbstractUnion();
        System.out.println("\n*********************************************************\n");
        System.out.println("                     UNSORTED SET TESTS                      ");
        System.out.println("\n*********************************************************\n");
        testUnsortedAdd();
        System.out.println("\n*********************************************************\n");
        testUnsortedSize();
        System.out.println("\n*********************************************************\n");
        testUnsortedRemove();
        System.out.println("\n*********************************************************\n");
        testUnsortedDifference();
        System.out.println("\n*********************************************************\n");
        testUnsortedIntersection();
        System.out.println("\n*********************************************************\n");
        System.out.println("                      SORTED SET TESTS                       ");
        System.out.println("\n*********************************************************\n");
        testSortedConstructor();
        System.out.println("\n*********************************************************\n");
        testSortedAdd();
        System.out.println("\n*********************************************************\n");
        testSortedSize();
        System.out.println("\n*********************************************************\n");
        testSortedRemove();
        System.out.println("\n*********************************************************\n");
        testSortedAddAll();
        System.out.println("\n*********************************************************\n");
        testSortedContains();
        System.out.println("\n*********************************************************\n");
        testSortedContainsAll();
        System.out.println("\n*********************************************************\n");
        testSortedEquals();
        System.out.println("\n*********************************************************\n");
        testSortedUnion();
        System.out.println("\n*********************************************************\n");
        testSortedDifference();
        System.out.println("\n*********************************************************\n");
        testSortedIntersection();
        System.out.println("\n*********************************************************\n");
        testSortedMin();
        System.out.println("\n*********************************************************\n");
        testSortedMax();
        System.out.println("\n*********************************************************\n");

        printResults();
    }

    private static final int NUM_LETTERS = 26;
    private static final int ASCII_NUM_UPPER = 65;

    private static ISet<String> generateSet(String type, int size) {

        ISet<String> output = null;

        if (type.equalsIgnoreCase("sorted")) {
            output = new SortedSet<>();
        } else {
            output = new UnsortedSet<>();
        }

        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });
            output.add(letter);
        }

        return output;
    }

    private static void testAbstractAddAll() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            testA = generateSet("unsorted", 20);
            testB = new UnsortedSet<>();

            testB.addAll(testA);
            printTestResult(testA, testB, "ADD ALL TEST - " + i);
        }
    }

    private static void testAbstractClear() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD / 2; i++) {
            testA = new UnsortedSet<>();
            testB = generateSet("unsorted", 20);

            testB.clear();
            printTestResult(testA, testB, "CLEAR TEST - " + i);
        }

        for (int i = NUM_TESTS_PER_METHOD / 2 + 1; i <= NUM_TESTS_PER_METHOD; i++) {
            testA = new UnsortedSet<>();
            testB = generateSet("sorted", 20);

            testB.clear();
            printTestResult(testA, testB, "CLEAR TEST - " + i);
        }

    }

    private static void testAbstractContains() {

        ISet<String> testA;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD / 2; i++) {
            testA = generateSet("unsorted", 20);
            testA.add("A");
            printTestResult(true, testA.contains("A"), "CONTAINS TEST - " + i);
        }
        for (int i = NUM_TESTS_PER_METHOD / 2 + 1; i <= NUM_TESTS_PER_METHOD; i++) {
            testA = generateSet("unsorted", 20);
            printTestResult(false, testA.contains("N/A"), "CONTAINS TEST - " + i);
        }
    }

    private static void testAbstractContainsAll() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new UnsortedSet<>();
            testB = new UnsortedSet<>();

            // Generating testA and testB such that testB is always a subset of testA
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                if ((int) (Math.random() * 2) == 1) {
                    testB.add(letter);
                }
            }
            printTestResult(true, testA.containsAll(testB), "CONTAINS ALL TEST - " + i);
            testB.add("###");
            printTestResult(false, testA.containsAll(testB),
                    "CONTAINS ALL TEST - " + (i + 1));
        }
    }

    private static void testAbstractEquals() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new UnsortedSet<>();
            testB = new UnsortedSet<>();

            // Generating testA and testB such that testA == testB
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testB.add(letter);
            }
            printTestResult(true, testA.equals(testB), "EQUALS TEST - " + i);
            testB.add("#");
            printTestResult(false, testA.equals(testB),
                    "EQUALS TEST - " + (i + 1));
        }
    }

    private static void testAbstractUnion() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new UnsortedSet<>();
            testB = new UnsortedSet<>();
            testC = new UnsortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testC.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                testC.add(letter);
            }
            printTestResult(testC, testA.union(testB), "UNION TEST - " + i);
            printTestResult(testC, testB.union(testA), "UNION TEST - " + (i + 1));
        }
    }

    private static void testUnsortedAdd() {

        UnsortedSet<String> testA = new UnsortedSet<>();

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            testA.add(letter);
            printTestResult(true, testA.contains(letter), "ADD TEST - " + i);
        }
    }

    private static void testUnsortedSize() {

        UnsortedSet<String> testA = new UnsortedSet<>();
        int size = 0;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            size += (testA.add(letter)) ? 1 : 0;
            printTestResult(size, testA.size(), "SIZE TEST - " + i);
        }
    }

    private static void testUnsortedRemove() {

        ISet<String> testA;
        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            testA = generateSet("unsorted", 20);

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            int oldSize = testA.size();
            boolean contains = testA.contains(letter);
            boolean removed = testA.remove(letter);
            // If the set contained the letter,
            // then removed should be true AND oldSize is different from current size
            printTestResult(contains, removed && oldSize != testA.size(), "REMOVE TEST - " + i);
        }
    }

    private static void testUnsortedDifference() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            // Less computation than using clear()
            testA = new UnsortedSet<>();
            testB = new UnsortedSet<>();
            testC = new UnsortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testC.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                if (testC.contains(letter)) {
                    testC.remove(letter);
                }
            }
            printTestResult(testC, testA.difference(testB), "DIFFERENCE TEST - " + i);
        }
    }

    private static void testUnsortedIntersection() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            // Less computation than using clear()
            testA = new UnsortedSet<>();
            testB = new UnsortedSet<>();
            testC = new UnsortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                if (testA.contains(letter)) {
                    testC.add(letter);
                }
            }
            printTestResult(testC, testA.intersection(testB), "INTERSECTION TEST - " + i);
        }
    }

    private static void testSortedConstructor() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            testA = generateSet("sorted", 12);
            testB = new SortedSet<>(testA);

            printTestResult(testA, testB, "CONSTRUCTOR TEST - " + i);
        }
    }

    private static void testSortedAdd() {

        SortedSet<String> testA = new SortedSet<>();

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            testA.add(letter);
            printTestResult(true, testA.contains(letter), "ADD TEST - " + i);

        }
    }

    private static void testSortedSize() {

        SortedSet<String> testA = new SortedSet<>();
        int size = 0;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            size += (testA.add(letter)) ? 1 : 0;
            printTestResult(size, testA.size(), "SIZE TEST - " + i);

        }
    }

    private static void testSortedRemove() {

        ISet<String> testA;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            testA = generateSet("sorted", 20);

            int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
            String letter = new String(new char[] { (char) rand });

            int oldSize = testA.size();
            boolean contains = testA.contains(letter);
            boolean removed = testA.remove(letter);
            // If the set contained the letter,
            // then removed should be true AND oldSize is different from current size
            printTestResult(contains, removed && oldSize != testA.size(), "REMOVE TEST - " + i);
        }
    }

    private static void testSortedAddAll() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {
            testA = generateSet("sorted", 20);
            testB = new UnsortedSet<>();

            testB.addAll(testA);
            printTestResult(testA, testB, "ADD ALL TEST - " + i);
        }
    }

    private static void testSortedContains() {

        ISet<String> testA;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD / 2; i++) {
            testA = generateSet("sorted", 20);
            testA.add("A");
            printTestResult(true, testA.contains("A"), "CONTAINS TEST - " + i);
        }
        for (int i = NUM_TESTS_PER_METHOD / 2 + 1; i <= NUM_TESTS_PER_METHOD; i++) {
            testA = generateSet("sorted", 20);
            printTestResult(false, testA.contains("N/A"), "CONTAINS TEST - " + i);
        }
    }

    private static void testSortedContainsAll() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new SortedSet<>();
            testB = ((int) (Math.random() * 2) == 1) ? new UnsortedSet<>() : new SortedSet<>();

            // Generating testA and testB such that testB is always a subset of testA
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                if ((int) (Math.random() * 2) == 1) {
                    testB.add(letter);
                }
            }
            printTestResult(true, testA.containsAll(testB), "CONTAINS ALL TEST - " + i);
            testB.add("###");
            printTestResult(false, testA.containsAll(testB),
                    "CONTAINS ALL TEST - " + (i + 1));
        }
    }

    private static void testSortedEquals() {

        ISet<String> testA;
        ISet<String> testB;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new SortedSet<>();
            testB = ((int) (Math.random() * 2) == 1) ? new UnsortedSet<>() : new SortedSet<>();

            // Generating testA and testB such that testA == testB
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testB.add(letter);
            }
            printTestResult(true, testA.equals(testB), "EQUALS TEST - " + i);
            testB.add("#");
            printTestResult(false, testA.equals(testB),
                    "EQUALS TEST - " + (i + 1));
        }
    }

    private static void testSortedUnion() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i += 2) {

            // Less computation than using clear()
            testA = new SortedSet<>();
            testB = ((int) (Math.random() * 2) == 1) ? new UnsortedSet<>() : new SortedSet<>();
            testC = new SortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testC.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                testC.add(letter);
            }
            printTestResult(testC, testA.union(testB), "UNION TEST - " + i);
            printTestResult(testC, testB.union(testA), "UNION TEST - " + (i + 1));
        }
    }

    private static void testSortedDifference() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            // Less computation than using clear()
            testA = new SortedSet<>();
            testB = ((int) (Math.random() * 2) == 1) ? new UnsortedSet<>() : new SortedSet<>();
            testC = new SortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
                testC.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                if (testC.contains(letter)) {
                    testC.remove(letter);
                }
            }
            printTestResult(testC, testA.difference(testB), "DIFFERENCE TEST - " + i);
        }
    }

    private static void testSortedIntersection() {

        ISet<String> testA;
        ISet<String> testB;
        ISet<String> testC;

        for (int i = 1; i <= NUM_TESTS_PER_METHOD; i++) {

            // Less computation than using clear()
            testA = new SortedSet<>();
            testB = ((int) (Math.random() * 2) == 1) ? new UnsortedSet<>() : new SortedSet<>();
            testC = new SortedSet<>();

            // Generating testA, testB, and testC such that testA union testB == testC
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testA.add(letter);
            }
            for (int j = 0; j < 10; j++) {
                int rand = (int) (Math.random() * NUM_LETTERS) + ASCII_NUM_UPPER;
                String letter = new String(new char[] { (char) rand });
                testB.add(letter);
                if (testA.contains(letter)) {
                    testC.add(letter);
                }
            }
            printTestResult(testC, testA.intersection(testB), "INTERSECTION TEST - " + i);
        }
    }

    private static void testSortedMin() {
        SortedSet<String> testA = new SortedSet<>();
        testA.add("A");
        testA.add("B");
        testA.add("C");
        testA.add("E");
        testA.add("H");
        testA.add("Q");
        testA.add("Z");

        printTestResult("A", testA.min(), "MIN TEST - 1");
    }

    private static void testSortedMax() {
        SortedSet<String> testA = new SortedSet<>();
        testA.add("A");
        testA.add("B");
        testA.add("C");
        testA.add("E");
        testA.add("H");
        testA.add("Q");
        testA.add("Z");

        printTestResult("Z", testA.max(), "MAX TEST - 1");
    }

    private static void runExperiments() {
        // CS314 Students. Uncomment this section when ready to
        // run your experiments
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to change look and feel");
        }
        Scanner sc = new Scanner(System.in);
        String response = "";
        do {
            largeTest();
            System.out.print("Another file? Enter y to do another file: ");
            response = sc.next();
        } while (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y"));
        sc.close();
    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
        sc.close();
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
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

    // Prints test results for two Strings
    private static void printTestResult(String data1, String data2, String testName) {
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