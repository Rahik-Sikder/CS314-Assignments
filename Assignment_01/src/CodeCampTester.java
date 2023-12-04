
// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment: 
 * Name: RAHIK N SIKDER
 * email address: sikder.rahik@utexas.edu
 * UTEID: rns2359
 * Section 5 digit ID: 52610
 * Grader name: 
 * Number of slip days used on this assignment:
 */

/*
 * Experiment 1:
 * 
 * Num People: 182
 * Average Pairs: 45
 * 
 *  Run with 
 *      CodeCamp.runExperiment1();
 * 
 * Experiment 2:
 *      
 *      It takes at least 23 people for there to be at least a 50% chance of two people having a shared
 *      birthday.
 *      
 *  Run with 
 *      CodeCamp.runExperiment1(); 
 * 
 *      Num people: 2, number of experiments with one or more shared birthday: 150,     percentage: 0.30
        Num people: 3, number of experiments with one or more shared birthday: 434,     percentage: 0.87
        Num people: 4, number of experiments with one or more shared birthday: 807,     percentage: 1.61
        Num people: 5, number of experiments with one or more shared birthday: 1363,    percentage: 2.73
        Num people: 6, number of experiments with one or more shared birthday: 2004,    percentage: 4.01
        Num people: 7, number of experiments with one or more shared birthday: 2906,    percentage: 5.81
        Num people: 8, number of experiments with one or more shared birthday: 3665,    percentage: 7.33
        Num people: 9, number of experiments with one or more shared birthday: 4789,    percentage: 9.58
        Num people: 10, number of experiments with one or more shared birthday: 5703,   percentage: 11.41
        Num people: 11, number of experiments with one or more shared birthday: 6998,   percentage: 14.00
        Num people: 12, number of experiments with one or more shared birthday: 8321,   percentage: 16.64
        Num people: 13, number of experiments with one or more shared birthday: 9765,   percentage: 19.53
        Num people: 14, number of experiments with one or more shared birthday: 11374,  percentage: 22.75
        Num people: 15, number of experiments with one or more shared birthday: 12854,  percentage: 25.71
        Num people: 16, number of experiments with one or more shared birthday: 14233,  percentage: 28.47
        Num people: 17, number of experiments with one or more shared birthday: 15692,  percentage: 31.38
        Num people: 18, number of experiments with one or more shared birthday: 17149,  percentage: 34.30
        Num people: 19, number of experiments with one or more shared birthday: 18908,  percentage: 37.82
        Num people: 20, number of experiments with one or more shared birthday: 20508,  percentage: 41.02
        Num people: 21, number of experiments with one or more shared birthday: 22296,  percentage: 44.59
        Num people: 22, number of experiments with one or more shared birthday: 23846,  percentage: 47.69
    --> Num people: 23, number of experiments with one or more shared birthday: 25521,  percentage: 51.04 <--
        Num people: 24, number of experiments with one or more shared birthday: 26812,  percentage: 53.62
        Num people: 25, number of experiments with one or more shared birthday: 28495,  percentage: 56.99
        Num people: 26, number of experiments with one or more shared birthday: 29977,  percentage: 59.95
        Num people: 27, number of experiments with one or more shared birthday: 31261,  percentage: 62.52
        Num people: 28, number of experiments with one or more shared birthday: 32579,  percentage: 65.16
        Num people: 29, number of experiments with one or more shared birthday: 34075,  percentage: 68.15
        Num people: 30, number of experiments with one or more shared birthday: 35374,  percentage: 70.75
        Num people: 31, number of experiments with one or more shared birthday: 36649,  percentage: 73.30
        Num people: 32, number of experiments with one or more shared birthday: 37575,  percentage: 75.15
        Num people: 33, number of experiments with one or more shared birthday: 38774,  percentage: 77.55
        Num people: 34, number of experiments with one or more shared birthday: 39780,  percentage: 79.56
        Num people: 35, number of experiments with one or more shared birthday: 40726,  percentage: 81.45
        Num people: 36, number of experiments with one or more shared birthday: 41710,  percentage: 83.42
        Num people: 37, number of experiments with one or more shared birthday: 42352,  percentage: 84.70
        Num people: 38, number of experiments with one or more shared birthday: 43130,  percentage: 86.26
        Num people: 39, number of experiments with one or more shared birthday: 44037,  percentage: 88.07
        Num people: 40, number of experiments with one or more shared birthday: 44463,  percentage: 88.93
        Num people: 41, number of experiments with one or more shared birthday: 45144,  percentage: 90.29
        Num people: 42, number of experiments with one or more shared birthday: 45626,  percentage: 91.25
        Num people: 43, number of experiments with one or more shared birthday: 46186,  percentage: 92.37
        Num people: 44, number of experiments with one or more shared birthday: 46603,  percentage: 93.21
        Num people: 45, number of experiments with one or more shared birthday: 47085,  percentage: 94.17
        Num people: 46, number of experiments with one or more shared birthday: 47384,  percentage: 94.77
        Num people: 47, number of experiments with one or more shared birthday: 47770,  percentage: 95.54
        Num people: 48, number of experiments with one or more shared birthday: 48034,  percentage: 96.07
        Num people: 49, number of experiments with one or more shared birthday: 48285,  percentage: 96.57
        Num people: 50, number of experiments with one or more shared birthday: 48565,  percentage: 97.13
        Num people: 51, number of experiments with one or more shared birthday: 48696,  percentage: 97.39
        Num people: 52, number of experiments with one or more shared birthday: 48929,  percentage: 97.86
        Num people: 53, number of experiments with one or more shared birthday: 49080,  percentage: 98.16
        Num people: 54, number of experiments with one or more shared birthday: 49165,  percentage: 98.33
        Num people: 55, number of experiments with one or more shared birthday: 49333,  percentage: 98.67
        Num people: 56, number of experiments with one or more shared birthday: 49422,  percentage: 98.84
        Num people: 57, number of experiments with one or more shared birthday: 49541,  percentage: 99.08
        Num people: 58, number of experiments with one or more shared birthday: 49593,  percentage: 99.19
        Num people: 59, number of experiments with one or more shared birthday: 49640,  percentage: 99.28
        Num people: 60, number of experiments with one or more shared birthday: 49729,  percentage: 99.46
        Num people: 61, number of experiments with one or more shared birthday: 49736,  percentage: 99.47
        Num people: 62, number of experiments with one or more shared birthday: 49790,  percentage: 99.58
        Num people: 63, number of experiments with one or more shared birthday: 49836,  percentage: 99.67
        Num people: 64, number of experiments with one or more shared birthday: 49850,  percentage: 99.70
        Num people: 65, number of experiments with one or more shared birthday: 49880,  percentage: 99.76
        Num people: 66, number of experiments with one or more shared birthday: 49920,  percentage: 99.84
        Num people: 67, number of experiments with one or more shared birthday: 49921,  percentage: 99.84
        Num people: 68, number of experiments with one or more shared birthday: 49943,  percentage: 99.89
        Num people: 69, number of experiments with one or more shared birthday: 49944,  percentage: 99.89
        Num people: 70, number of experiments with one or more shared birthday: 49960,  percentage: 99.92
        Num people: 71, number of experiments with one or more shared birthday: 49964,  percentage: 99.93
        Num people: 72, number of experiments with one or more shared birthday: 49974,  percentage: 99.95
        Num people: 73, number of experiments with one or more shared birthday: 49987,  percentage: 99.97
        Num people: 74, number of experiments with one or more shared birthday: 49981,  percentage: 99.96
        Num people: 75, number of experiments with one or more shared birthday: 49987,  percentage: 99.97
        Num people: 76, number of experiments with one or more shared birthday: 49987,  percentage: 99.97
        Num people: 77, number of experiments with one or more shared birthday: 49988,  percentage: 99.98
        Num people: 78, number of experiments with one or more shared birthday: 49994,  percentage: 99.99
        Num people: 79, number of experiments with one or more shared birthday: 49991,  percentage: 99.98
        Num people: 80, number of experiments with one or more shared birthday: 49998,  percentage: 100.00
        Num people: 81, number of experiments with one or more shared birthday: 49998,  percentage: 100.00
        Num people: 82, number of experiments with one or more shared birthday: 49997,  percentage: 99.99
        Num people: 83, number of experiments with one or more shared birthday: 49998,  percentage: 100.00
        Num people: 84, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 85, number of experiments with one or more shared birthday: 49999,  percentage: 100.00
        Num people: 86, number of experiments with one or more shared birthday: 49999,  percentage: 100.00
        Num people: 87, number of experiments with one or more shared birthday: 49999,  percentage: 100.00
        Num people: 88, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 89, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 90, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 91, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 92, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 93, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 94, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 95, number of experiments with one or more shared birthday: 49999,  percentage: 100.00
        Num people: 96, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 97, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 98, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 99, number of experiments with one or more shared birthday: 50000,  percentage: 100.00
        Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00
 * 
 */

public class CodeCampTester {

    public static void main(String[] args) {
        final String newline = System.getProperty("line.separator");
        int expected;
        int actual;

        /**
         * HAMMING DISTANCE TESTS
         */

        // Hamming Test 1
        int[] h1 = new int[100];
        int[] h2 = new int[100];
        expected = 0; // No substitution needed
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Hamming Test 1: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 1, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        }

        // Hamming Test 2
        h1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        h2 = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        expected = 10; // Every element needs a substitution
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Hamming Test 2: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 2, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        }

        // Hamming Test 3
        h1 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        h2 = new int[] { 0, 0, 1, 0, 2, 3, 0, 0, 4, 0 };
        ;
        expected = 4; // 4 elements need a substitution
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Hamming Test 3: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 3, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 3, hamming distance");
        }

        /**
         * IS PERMUTATION TESTS
         */

        // Perm Test 1
        int[] a = { 17, 8, 15 };
        int[] b = { 15, 17, 8 };
        boolean expectedBool = true; // a and b are a permuation
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Perm Test 1: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 1, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 1, isPermutation");
        }

        // Perm Test 2
        a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        b = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        expectedBool = false; // a and b are NOT permutations of each other
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Perm Test 2: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 2, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 2, isPermutation");
        }

        // Perm Test 3
        a = new int[10000];
        b = new int[20000];
        expectedBool = false; // the empty arrays are NOT permutations of each other due to size diff
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Perm Test 3: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 3, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 3, isPermutation");
        }

        /**
         * MOST VOWELS TESTS
         */

        // Most Vowels Test 1
        String[] arrayOfStrings = { "aaaaaaaaaaaaa", "aieuoaieuoaieuoaieuoaieuoaieuo" };
        int expectedResult = 1; // The second element has the most vowels
        int actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Most Vowels Test 1: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 1, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 1, mostVowels");
        }

        // Most Vowels Test 2
        arrayOfStrings = new String[] { "Which ", "word", "has", "the", "most", "vooooooooooowels?" };
        expectedResult = 5;
        actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Most Vowels Test 2: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 2, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 2, mostVowels");
        }

        // Most Vowels Test 3
        arrayOfStrings = new String[1000];
        arrayOfStrings[999] = "";
        expectedResult = 999;
        actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Most Vowels Test 3: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 3, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 3, mostVowels");
        }

        /**
         * SHARED BIRTHDAY TESTS
         */

        int pairs;

        // Shared Birthdays Test 1
        pairs = CodeCamp.sharedBirthdays(125, 100);
        System.out.println(newline + "Shared Birthdays Test 1: expected: "
                + "a value of 1 or more, actual result: " + pairs);
        // 1 more person than days, therefore at least 25 shared bDays
        if (pairs >= 25) {
            System.out.println("passed test 1, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 1, shared birthdays");
        }

        // Shared Birthdays Test 2
        pairs = CodeCamp.sharedBirthdays(387, 1);
        System.out.println(newline + "Shared Birthdays Test 2: expected: 74691"
                + ", actual result: " + pairs);
        // The pairs should be equal to the Summation of (n-1) from 1 to 387 which is
        // 74691
        if (pairs == 74691) {
            System.out.println("passed test 2, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 2, shared birthdays");
        }

        /**
         * QUEENS ARE SAFE TESTS
         */

        // Queens Safe Test 1
        char[][] board = {
                { 'q', '.', '.' },
                { '.', '.', '.' },
                { '.', 'q', '.' } };

        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Queens Safe Test 1: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 1, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 1, queensAreSafe");
        }

        // Queens Safe Test 2
        board = new char[][] {
                { 'q', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', 'q', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', 'q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'q', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', 'q' },
                { '.', '.', '.', '.', 'q', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        };

        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Queens Safe Test 2: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 2, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 2, queensAreSafe");
        }

        /**
         * MOST VALUABLE PLOT TESTS
         */

        // Most Val Plot Test 1
        int[][] city = {
                { 1, 0, 0, -1, 1 },
                { 0, 2, 0, 10, 4 },
                { 0, 0, 1, -9, -3 },
                { 0, -9, 8, 9, 3 },
                { 1, 0, -1, 1, 0 },
        };
        expected = 23;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Most Val Plot Test 1: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 1, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 1, getValueOfMostValuablePlot");
        }

        // Most Val Plot Test 2
        city = new int[][] {
                { 0, 0, 0, -1, 1 },
                { 0, 2, 0, 10, -4 },
                { -24, 0, 1, -9, -3 },
                { -9, 9, -8, 9, 3 },
                { 24, 0, -24, 1, 0 },
        };
        expected = 24;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Most Val Plot Test 2: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 2, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 2, getValueOfMostValuablePlot");
        }

    } // end of main method
}