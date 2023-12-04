import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: rns2359
 *  email address: sikder.rahik@gmail.com
 *  TA name: Casey, the Delightful
 *  Number of slip days I am using:
 */

public class AnagramFinderTester {

    // Counter for all failed tests
    // static var allows for tests to split over multiple methods
    private static int numTests;
    private static int numFailedTests;

    /**
     * main method that executes tests.
     * 
     * @param args Not used.
     */
    public static void main(String[] args) {

        cs314StudentTestsForLetterInventory();

    }

    private static void cs314StudentTestsForLetterInventory() {
        numTests = 0;
        numFailedTests = 0;
        System.out.println("\n*********************************************************\n");
        System.out.println("                   LETTER INVENTORY TESTS                    ");
        System.out.println("\n*********************************************************\n");
        testConstructor();
        System.out.println("\n*********************************************************\n");
        testGet();
        System.out.println("\n*********************************************************\n");
        testSize();
        System.out.println("\n*********************************************************\n");
        testIsEmpty();
        System.out.println("\n*********************************************************\n");
        testToString();
        System.out.println("\n*********************************************************\n");
        testAdd();
        System.out.println("\n*********************************************************\n");
        testSubtract();
        System.out.println("\n*********************************************************\n");
        testAdd();
        System.out.println("\n*********************************************************\n");

        printResults();

    }

    private static void testConstructor() {
        LetterInventory testA = new LetterInventory("Wello Horld");
        LetterInventory testB = new LetterInventory("We$llo$# ++,Horl.>d");

        printTestResult(testA.toString(), "dehllloorw", "CONSTRUCTOR TEST - 1");
        printTestResult(testB.toString(), "dehllloorw", "CONSTRUCTOR TEST - 2");
        printTestResult(testA.get('l'), 3, "CONSTRUCTOR TEST - 3");
        printTestResult(testB.size(), 10, "CONSTRUCTOR TEST - 4");

    }

    private static void testGet() {
        LetterInventory testA = new LetterInventory("Wello Horld");

        printTestResult(testA.get('h'), 1, "GET TEST - 1");
        printTestResult(testA.get('Z'), 0, "GET TEST - 2");

    }

    private static void testSize() {
        LetterInventory testA = new LetterInventory("Wello Horld");
        LetterInventory testB = new LetterInventory("Something or another");

        printTestResult(testA.size(), 10, "SIZE TEST - 1");
        printTestResult(testB.size(), 18, "SIZE TEST - 2");

    }

    private static void testIsEmpty() {
        LetterInventory testA = new LetterInventory("Wello Horld");
        LetterInventory testB = new LetterInventory("");

        printTestResult(testA.isEmpty(), false, "IS EMPTY TEST - 1");
        printTestResult(testB.isEmpty(), true, "IS EMPTY TEST - 2");

    }

    private static void testToString() {
        LetterInventory testA = new LetterInventory("Something");
        LetterInventory testB = new LetterInventory("Another");

        printTestResult(testA.toString(), "eghimnost", "TO STRING TEST - 1");
        printTestResult(testB.toString(), "aehnort", "TO STRING TEST - 2");
    }

    private static void testAdd() {
        LetterInventory testA = new LetterInventory("Something");
        LetterInventory testB = new LetterInventory("Another");
        LetterInventory testC = new LetterInventory("aeeghhimnnoorstt");

        
        printTestResult(testA.add(testB), testC, "ADD TEST - 1");
        printTestResult(testB.add(testA), testC, "ADD TEST - 2");
    }

     private static void testSubtract() {
        LetterInventory testA = new LetterInventory("Something");
        LetterInventory testB = new LetterInventory("Another");
        LetterInventory testC = new LetterInventory("aeeghhimnnoorstt");

        
        printTestResult(testC.subtract(testB), testA, "SUBTRACT TEST - 1");
        printTestResult(testC.subtract(testA), testB, "SUBTRACT TEST - 2");
    }

    private static void testEquals() {
        LetterInventory testA = new LetterInventory("Something");
        LetterInventory testB = new LetterInventory("Another");
        LetterInventory testC = new LetterInventory("SO$$$mE*thiNg");

        
        printTestResult(testC.equals(testB), false, "SUBTRACT TEST - 1");
        printTestResult(testC.equals(testA), true, "SUBTRACT TEST - 2");
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

    // Prints test results for two Objects
    private static void printTestResult(Object data1, Object data2, String testName) {
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


}