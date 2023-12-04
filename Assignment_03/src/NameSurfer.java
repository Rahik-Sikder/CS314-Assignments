/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, RAHIK N SIKDER, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * 
 * UTEID: rns2359
 * email address: sikder.rahik@utexas.edu 
 * Grader name: Casey, the Amazing
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class NameSurfer {

    // CS314 students, explain your menu option 7 here:

    /**
     * My menu 7 option is startedInDecade(int decade).
     * The method takes in a a decade in full decade form and
     * returns the names that first got their ranking on that
     * year, or in other words, started in that decade.
     * 
     * The method rounds all inputs up to the nearest decade.
     * If the client enters in 1984, the method will return results
     * for 1990 because no real determination can be made whether
     * the names for 1980 occured before or after 1984.
     * 
     */

    // CS314 students, Explain your interesting search / trend here:

    /**
     * Clair / Claire / Clare
     * 
     * All three of these names are the same but each have a different spelling.
     * And each one has very different ranking distribution.
     * 
     * Clair was most popular during the early 20th century and became
     * unranked from 1970 onwards.
     * 
     * Claire was always more popular than Clair and had peaks in the
     * 1920s-30s and its highest peak being 2000.
     * 
     * Clare is very strange, consistently less popular than Clair before 1960, yet
     * does not ever become unranked except for one decade (1970).
     * 
     * From this I can conclude that Claire has been the standard and classic
     * way to spell the name, and while other variations do occur they are no
     * where near as consistent as Claire. Clare does have a resurgence from
     * 1980 to 2000 but is still several hundred ranks below Claire. Clair has
     * been out of fashion (top 1000) since 1970.
     * 
     */

    // CS314 students, add test code for NameRecord class here:

    /**
     * Runs test cases for all of the methods in the NameRecord class
     */
    public static void nameRecordTests() {

        String nameDataA = "Aaron 131 147 193 187 199 250 237 230 178 52 34 34 41 55 53";
        String[] nameDataArrayA = nameDataA.split("\\s+");

        String nameDataB = "Maren 0 0 0 0 0 0 0 0 0 0 946 0 0 0 883";
        String[] nameDataArrayB = nameDataB.split("\\s+");

        NameRecord testA = new NameRecord(nameDataArrayA, 1870, 15);
        NameRecord testB = new NameRecord(nameDataArrayB, 1870, 15);

        System.out.println("*********************************************************");
        System.out.println("                    NAME RECORD TESTS                    ");
        System.out.println("*********************************************************");
        /**
         * BASE DECADE TEST
         */
        printTestResult(testA.getBaseDecade(), 1870, "Get Base Decade - Test 1 ");
        printTestResult(testB.getBaseDecade(), 1870, "Get Base Decade - Test 2 ");
        System.out.println("*********************************************************");

        /**
         * NUM DECADES TEST
         */
        printTestResult(testA.getNumDecades(), 15, "Get Num Decades - Test 1 ");
        printTestResult(testB.getNumDecades(), 15, "Get Num Decades - Test 2 ");
        System.out.println("*********************************************************");

        /**
         * GET RANK TEST
         */
        printTestResult(testA.getRank(14), 53, "Get Rank - Test 1");
        printTestResult(testB.getRank(10), 946, "Get Rank - Test 2");
        printTestResult(testA.getRank(0), 131, "Get Rank - Test 3");
        printTestResult(testB.getRank(0), 0, "Get Rank - Test 4");
        System.out.println("*********************************************************");

        /**
         * GET BEST DECADE TEST
         */
        printTestResult(testA.getBestDecade(), 1970, "Get Best Decade - Test 1");
        printTestResult(testB.getBestDecade(), 2010, "Get Best Decade - Test 2");
        System.out.println("*********************************************************");

        /**
         * NUM TIMES RANKED TESTS
         */
        printTestResult(testA.numTimesRanked(), 15, "Num Times Ranked - Test 1");
        printTestResult(testB.numTimesRanked(), 2, "Num Times Ranked  - Test 2");
        System.out.println("*********************************************************");

        /**
         * IS ALWAYS RANKED TESTS
         */
        printTestResult(testA.isAlwaysRanked(), true, "Is Always Ranked - Test 1");
        printTestResult(testB.isAlwaysRanked(), false, "Is Always Ranked  - Test 2");
        System.out.println("*********************************************************");

        /**
         * IS ONLY RANKED ONCE TESTS
         */
        String[] nameDataC = "Legacy 0 0 0 0 0 0 0 0 0 0 0 0 0 0 999".split("\\s+");
        NameRecord testC = new NameRecord(nameDataC, 1870, 15);
        printTestResult(testA.isOnlyRankedOnce(), false, "Is Only Ranked Once - Test 1");
        printTestResult(testB.isOnlyRankedOnce(), false, "Is Only Ranked Once - Test 2");
        printTestResult(testC.isOnlyRankedOnce(), true, "Is Only Ranked Once - Test 3");
        System.out.println("*********************************************************");

        /**
         * IS ALWAYS IMPROVING TESTS
         */
        nameDataC = "Legacy 20 19 18 17 15 14 12 10 9 8 7 6 5 4 1".split("\\s+");
        testC = new NameRecord(nameDataC, 1870, 15);
        String[] nameDataD = "Legacy 20 19 18 17 15 14 12 10 9 8 7 6 5 4 0".split("\\s+");
        NameRecord testD = new NameRecord(nameDataD, 1870, 15);
        printTestResult(testA.isAlwaysImproving(), false, "Is Always Improving - Test 1");
        printTestResult(testB.isAlwaysImproving(), false, "Is Always Improving - Test 2");
        printTestResult(testC.isAlwaysImproving(), true, "Is Always Improving - Test 3");
        printTestResult(testD.isAlwaysImproving(), false, "Is Always Improving - Test 4");
        System.out.println("*********************************************************");

        /**
         * IS ALWAYS DECLINING TESTS
         */
        nameDataC = "Legacy 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15".split("\\s+");
        testC = new NameRecord(nameDataC, 1870, 15);
        nameDataD = "Legacy 1 2 3 4 5 6 7 8 9 10 11 12 13 14 0".split("\\s+");
        testD = new NameRecord(nameDataD, 1870, 15);
        printTestResult(testA.isAlwaysDeclining(), false, "Is Always Declining - Test 1");
        printTestResult(testB.isAlwaysDeclining(), false, "Is Always Declining - Test 2");
        printTestResult(testC.isAlwaysDeclining(), true, "Is Always Declining - Test 3");
        printTestResult(testD.isAlwaysDeclining(), true, "Is Always Declining - Test 4");
        System.out.println("*********************************************************");

        /**
         * STARTED IN DECADE TEST
         */
        nameDataC = "Legacy 0 0 0 0 5 0 0 0 0 10 11 12 13 14 15".split("\\s+");
        testC = new NameRecord(nameDataC, 1870, 15);
        printTestResult(testA.startedInDecade(4), false, "Started in Decade - Test 1");
        printTestResult(testB.startedInDecade(10), true, "Started in Decade - Test 2");
        printTestResult(testC.startedInDecade(9), false, "Started in Decade - Test 3");
        printTestResult(testC.startedInDecade(4), true, "Started in Decade - Test 4");
        System.out.println("*********************************************************");

        /**
         * TO STRING TESTS
         */
        String stringA = "Aaron\n"
                + "1870: 131\n"
                + "1880: 147\n"
                + "1890: 193\n"
                + "1900: 187\n"
                + "1910: 199\n"
                + "1920: 250\n"
                + "1930: 237\n"
                + "1940: 230\n"
                + "1950: 178\n"
                + "1960: 52\n"
                + "1970: 34\n"
                + "1980: 34\n"
                + "1990: 41\n"
                + "2000: 55\n"
                + "2010: 53\n";

        String stringB = "Maren\n"
                + "1870: 0\n"
                + "1880: 0\n"
                + "1890: 0\n"
                + "1900: 0\n"
                + "1910: 0\n"
                + "1920: 0\n"
                + "1930: 0\n"
                + "1940: 0\n"
                + "1950: 0\n"
                + "1960: 0\n"
                + "1970: 946\n"
                + "1980: 0\n"
                + "1990: 0\n"
                + "2000: 0\n"
                + "2010: 883\n";

        String stringC = "Legacy\n"
                + "1870: 0\n"
                + "1880: 0\n"
                + "1890: 0\n"
                + "1900: 0\n"
                + "1910: 5\n"
                + "1920: 0\n"
                + "1930: 0\n"
                + "1940: 0\n"
                + "1950: 0\n"
                + "1960: 10\n"
                + "1970: 11\n"
                + "1980: 12\n"
                + "1990: 13\n"
                + "2000: 14\n"
                + "2010: 15\n";

        printTestResult(testA.toString(), stringA, "To String - Test 1");
        printTestResult(testB.toString(), stringB, "To String - Test 2");
        printTestResult(testC.toString(), stringC, "To String - Test 3");
        System.out.println("*********************************************************");

        /**
         * COMPARE TO TESTS
         */
        nameDataC = "Legacy 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15".split("\\s+");
        testC = new NameRecord(nameDataC, 1870, 15);
        nameDataD = "Aaron 131 147 193 187 199 250 237 230 178 52 34 34 41 55 5".split("\\s+");
        testD = new NameRecord(nameDataD, 1870, 15);
        // Aaron comes before Maren
        printTestResult(testA.compareTo(testB) < 0, true, "Compare To - Test 1");
        // Maren comes after Legacy
        printTestResult(testB.compareTo(testC) > 0, true, "Compare To - Test 2");
        // Legacy comes after Aaron
        printTestResult(testC.compareTo(testD) > 0, true, "Compare To - Test 3");
        // Aaron is equal to Aaron
        printTestResult(testD.compareTo(testA) == 0, true, "Compare To - Test 4");
        System.out.println("*********************************************************");

    }

    // main method. Driver for the whole program
    public static void main(String[] args) {

        // nameRecordTests();

        // Alter name of file to try different data sources.
        final String NAME_FILE = "names.txt";
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /**
     * Ask user for options to perform on the given Names object.
     * Creates a Scanner connected to System.in.
     * 
     * <br>
     * pre: namesDatabase != null
     * <br>
     * 
     * @param namesDatabase
     */
    private static void runOptions(Names namesDatabase) {

        if (namesDatabase == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "namesDatabase != null");
        }

        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if (menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                startedInDecade(namesDatabase, keyboard);
            }
        } while (menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /**
     * Create a Scanner and return connected to a File with the given name.
     * 
     * <br>
     * pre: fileName != null
     * <br>
     * 
     * @param fileName pointing towards a txt file containing name data
     * @return a Scanner connected to the file or null if the File does not
     *         exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file "
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /**
     * Display the names that have appeared in every decade.
     * 
     * <br>
     * pre: namesDatabase != null
     * <br>
     * 
     * @param namesDatabase contains a Names object
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        ArrayList<String> alwaysAppear = namesDatabase.rankedEveryDecade();
        System.out.println("--------------------------------------------------");
        System.out.println(alwaysAppear.size() + " names appear in every decade. The names are: ");
        for (String curName : alwaysAppear) {
            System.out.println(curName);
        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Display the names that have appeared in only one decade.
     * 
     * <br>
     * pre: namesDatabase != null
     * <br>
     * 
     * @param namesDatabase is not null
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> onlyOnce = namesDatabase.rankedOnlyOneDecade();
        System.out.println("--------------------------------------------------");
        System.out.println(onlyOnce.size() + " names appear in only one decade. The names are: ");
        for (String curName : onlyOnce) {
            System.out.println(curName);
        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Display the names that have gotten more popular
     * in each successive decade.
     * 
     * <br>
     * pre: namesDatabase != null
     * <br>
     * 
     * @param namesDatabase is not null
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> alwaysPopular = namesDatabase.alwaysMorePopular();
        System.out.println("--------------------------------------------------");
        System.out.println(alwaysPopular.size() + " names are more popular. The names are: ");
        for (String curName : alwaysPopular) {
            System.out.println(curName);
        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Display the names that have gotten less popular
     * in each successive decade.
     * 
     * <br>
     * pre: namesDatabase != null
     * <br>
     * 
     * @param namesDatabase is not null
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> alwaysDeclining = namesDatabase.alwaysLessPopular();
        System.out.println("--------------------------------------------------");
        System.out.println(alwaysDeclining.size() + " names are less popular. The names are: ");
        for (String curName : alwaysDeclining) {
            System.out.println(curName);
        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Display the data for one name or state that name has never been ranked.
     * 
     * <br>
     * pre: namesDatabase != null, keyboard != null and is connected to System.in
     * <br>
     * 
     * @param namesDatabase is not null
     * @param keyboard is not null and connected to System.in
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.println("--------------------------------------------------");
        System.out.print("Enter a name: ");
        String search = keyboard.nextLine();
        System.out.println(); // For formatting
        NameRecord result = namesDatabase.getName(search);
        if (result == null) {
            System.out.println(search + " does not occur in any decade.");
        } else {
            System.out.println(result);
        }

        System.out.println("--------------------------------------------------");
    }

    /**
     * Display all names that contain a substring from the user
     * and the decade they were most popular.
     * 
     * <br>
     * pre: namesDatabase != null, keyboard != null and is connected to System.in
     * <br>
     * 
     * @param namesDatabase is not null
     * @param keyboard is not null and connected to System.in
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.println("--------------------------------------------------");
        System.out.print("Enter a name: ");
        String search = keyboard.nextLine();
        ArrayList<NameRecord> results = namesDatabase.getMatches(search);
        System.out.println(); // For formatting
        System.out.println("There are " + results.size() + " matches for " + search + "\n");
        System.out.println("The matches with their highest ranking decade are:");
        for (NameRecord nameRecord : results) {
            System.out.println(nameRecord.getName() + " " + nameRecord.getBestDecade());
        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Display all names that first occured in ranking on the decade given
     * by the user
     * 
     * <br>
     * pre: namesDatabase != null, keyboard != null and is connected to System.in
     * <br>
     * 
     * @param namesDatabase is not null
     * @param keyboard is not null and connected to System.in
     */
    private static void startedInDecade(Names namesDatabase, Scanner keyboard) {
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.println("--------------------------------------------------");
        int search = getInt(keyboard, "Enter a decade (will be rounded up to the nearest decade): ");
        keyboard.nextLine();
        ArrayList<NameRecord> results = namesDatabase.startedInDecade(search);
        System.out.println(); // For formatting
        int roundedUpSearch = (search + 9) / 10 * 10;
        System.out.println("There are " + results.size() + " names that started during " + roundedUpSearch + "\n");
        System.out.println("These names are:");
        for (NameRecord nameRecord : results) {
            System.out.println(nameRecord.getName());
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Get choice from the user
     * 
     * <br>
     * pre: keyboard != null and is connected to System.in
     * <br>
     * 
     * @param keyboard is not null
     * @return an int that is >= MenuChoices.SEARCH.ordinal()
     *         and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1 || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /**
     * Ensure an int is entered from the keyboard.
     * 
     * <br>
     * pre: s != null and is connected to System
     * <br>
     * 
     * @param s
     * @param prompt
     * @return the next int entered by the User
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to display all names that started appearing "
                + "on a specific decade.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE,
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }

    /**
     * THE FOLLOWING ARE FOR NAMERECORD TESTS
     */

    // Prints test results for two Strings
    private static void printTestResult(String data1, String data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1.equals(data2)) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // Prints test results for two ints
    private static void printTestResult(int data1, int data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " Failed";
        System.out.println(result);
    }

    // Prints test results for two booleans
    private static void printTestResult(boolean data1, boolean data2, String testName) {
        System.out.print((testName + " -------------------------------------------------")
                .substring(0, 50));
        String result = (data1 == data2) ? " Passed" : " Failed";
        System.out.println(result);
    }
}