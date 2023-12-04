/* Student information for assignment:
 *
 * On OUR honor, RAHIK N SIKDER and WAJEEH A JAFRY,
 * this programming assignment is OUR own work
 * and WE have not provided this code to any other student.
 *
 * Number of slip days used:
 *
 * Student 1 (Student whose Canvas account is being used)
 * UTEID: rns2359
 * email address: sikder.rahik@utexas.edu
 * Grader name: Casey :)
 * Section number: 52679
 *
 * Student 2
 * UTEID: waj495
 * email address: wajeehjafry@utexas.edu
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

    // Counter for all failed tests
    // static var allows for tests to split over multiple methods
    private static int numTests;
    private static int numFailedTests;

    // run the tests
    public static void main(String[] args) {
        runStudentTests();
    }

    /**
     * STUDENT TESTS
     */

    private static void runStudentTests() {

        numTests = 0;
        numFailedTests = 0;
        System.out.println("\n*********************************************************\n");
        System.out.println("                      RECURSIVE TESTS                        ");
        System.out.println("\n*********************************************************\n");
        testBinary();
        System.out.println("\n*********************************************************\n");
        testReverseString();
        System.out.println("\n*********************************************************\n");
        testNextDouble();
        System.out.println("\n*********************************************************\n");
        testPhoneMnemonics();
        System.out.println("\n*********************************************************\n");
        testCanFlow();
        System.out.println("\n*********************************************************");
        System.out.println("                 ( May take a few seconds )                  ");
        testTeams();
        System.out.println("\n*********************************************************\n");
        testMazes();
        System.out.println("\n*********************************************************\n");

        printResults();
    }

    private static void testBinary() {
        int[] tests = { 0, 1, -1, 2, 23, 3834, -4232, 77777, -2398743, 22212222 };
        String[] results = { "0", "1", "-1", "10", "10111",
                "111011111010", "-1000010001000", "10010111111010001",
                "-1001001001101000010111", "1010100101110111001111110" };

        for (int i = 0; i < tests.length; i++) {
            String testA = Recursive.getBinary(tests[i]);
            printTestResult(testA, results[i], "BINARY TEST - " + (i + 1));
        }

    }

    private static void testReverseString() {
        String[] tests = { "hello", "world", "what", " ", "is ", "up, DSJH" };
        String[] results = { "olleh", "dlrow", "tahw", " ", " si", "HJSD ,pu" };

        for (int i = 0; i < tests.length; i++) {
            String testA = Recursive.revString(tests[i]);
            printTestResult(testA, results[i], "REV STRING TEST - " + (i + 1));
        }
    }

    private static void testNextDouble() {
        int[][] tests = {
                { 1, 2, 4, 8, 16, 0, 64, 128, 256 },
                { 2, 3, 4, 5, 4, 8, 1222, -5, 6 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                {},
                { 0 },
                { 0, 0 }
        };
        int[] results = {
                6,
                1,
                9,
                1,
                0,
                0,
                1
        };

        for (int i = 0; i < tests.length; i++) {
            int testA = Recursive.nextIsDouble(tests[i]);
            printTestResult(testA, results[i], "NEXT DOUBLE TEST - " + (i + 1));
        }

    }

    private static void testPhoneMnemonics() {

        ArrayList<String> testA = Recursive.listMnemonics("2");
        ArrayList<String> resultA = new ArrayList<>();
        resultA.add("A");
        resultA.add("B");
        resultA.add("C");

        Collections.sort(testA);
        Collections.sort(resultA);
        printTestResult(testA.toString(), resultA.toString(), "PHONE MNEMONICS TEST - 1");

        testA = Recursive.listMnemonics("85");
        resultA = new ArrayList<>();
        resultA.add("TJ");
        resultA.add("TK");
        resultA.add("TL");
        resultA.add("UJ");
        resultA.add("UK");
        resultA.add("UL");
        resultA.add("VJ");
        resultA.add("VK");
        resultA.add("VL");

        Collections.sort(testA);
        Collections.sort(resultA);
        printTestResult(testA.toString(), resultA.toString(), "PHONE MNEMONICS TEST - 2");

    }

    private static void testCanFlow() {
        int[][] map = { { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
        };

        printTestResult(true, Recursive.canFlowOffMap(map, 0, 0),
                "CAN FLOW OFF MAP TEST - 1");

        map = new int[][] { { 1, 0, 1, 1, 1, 1 },
                { 1, -2, 1, 1, 1, 1 },
                { 1, -3, 1, 1, 1, 1 },
                { -5, -4, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
        };

        printTestResult(true, Recursive.canFlowOffMap(map, 0, 1),
                "CAN FLOW OFF MAP TEST - 2");

    }

    private static void testTeams() {
        int[] players = { 17, 17, 3, 9, 12, 10, 8, 8, 8, 7, 3, 4, 30 };
        int testNum = Recursive.minDifference(4, players);

        printTestResult(testNum, 0, "FAIR TEAMS TEST - 1");

        players = new int[] { -8, 16, 5, 7, 6, 3, 4, 6, -10, 23, -1000, 1013 };
        testNum = Recursive.minDifference(5, players);

        printTestResult(testNum, 0, "FAIR TEAMS TEST - 2");
    }

    private static void testMazes() {
        char[][] maze = { { '$' }, { 'S' }, { 'Y' }, { 'G' }, { 'E' } };
        int testNum = Recursive.canEscapeMaze(maze);

        printTestResult(testNum, 2, "MAZES TEAMS TEST - 1");

        maze = new char[][] { { '*', '*', '*', '*', '*' },
                { '$', 'G', '*', '*', '*' },
                { '*', 'Y', 'Y', 'Y', 'S' },
                { 'G', '*', 'E', 'Y', '$' }
        };

        testNum = Recursive.canEscapeMaze(maze);
        printTestResult(testNum, 1, "MAZES TEAMS TEST - 2");

    }

    /**
     * Prints out the reuslts of the tests using the instance vars
     * numTexts and numFailed tests
     * 
     */
    private static void printResults() {
        // Formatting the allignment
        // Likely inefficient, but it's for small values and only computed once
        int numSpaces = Integer.toString(numTests).length() -
                Integer.toString(numFailedTests).length();
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) {
            spaces.append(" ");
        }

        System.out.print(
                ("NUMBER OF FAILED TESTS: -------------------------------------------------")
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