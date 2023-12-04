/*  Student information for assignment:
 *
 *  On OUR honor, RAHIK N SIKDER and WAJEEH A JAFRY,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  Grader name: Casey :)
 *  Section number: 52679
 *
 *  Student 2
 *  UTEID: waj495
 *  email address: wajeehjafry@utexas.edu
 *
 */

//imports

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     * 
     * pre: n != Integer.MIN_VALUE
     * post: Returns a String that represents N in binary.
     * 
     * All chars in returned String are '1's or '0's.
     * Most significant digit is at position 0
     * 
     * @param n the base 10 int to convert to base 2
     * @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }

        // Base Case:
        // Integer is either 1 or 0
        if (n == 1 || n == 0) {
            return "" + n % 2;
        }

        // Integer is negative
        // Add negative sign to front
        // Make recursive call on n
        if (n < 0) {
            n = n * -1;
            return "-" + getBinary(n);
        }

        // Append last digit
        return "" + getBinary(n / 2) + n % 2;

    }

    /**
     * Problem 2: reverse a String recursively.
     * 
     * pre: stringToRev != null
     * post: returns a String that is the reverse of stringToRev
     * 
     * @param stringToRev the String to reverse.
     * @return a String with the characters in stringToRev
     *         in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        // Special Case:
        // String is empty
        if (stringToRev.length() == 0) {
            return "";
        }

        // Base Case 1:
        // String is length 1
        if (stringToRev.length() == 1) {
            return String.valueOf(stringToRev.charAt(0));
        }

        // Append first char of string to back
        // Make recursive call
        return "" + revString(stringToRev.substring(1))
                + String.valueOf(stringToRev.charAt(0));

    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * 
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * 
     * @param data The array to search.
     * @return The number of elements in data that
     *         are followed immediately by a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        return nextisDoubleHelper(data, 0);

    }

    /**
     * Helper method for nextIsDouble
     * 
     * @param data
     * @param index
     * @return
     */
    private static int nextisDoubleHelper(int[] data, int index) {

        // Base Case:
        // On the last element of data
        if (index >= data.length - 1) {
            return 0;
        }

        // Find if the next element is the cur element's double
        if (data[index + 1] == data[index] * 2) {
            return 1 + nextisDoubleHelper(data, index + 1);
        }

        // Not double
        return nextisDoubleHelper(data, index + 1);

    }

    /**
     * Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     * pre: number != null, number.length() > 0,
     * all characters in number are digits<br>
     * post: see tips section of assignment handout
     * 
     * @param number The number to find mnemonics for
     * @return An ArrayList<String> with all possible mnemonics
     *         for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null || number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
        recursiveMnemonics(results, "", number);
        return results;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics, 
                                            String mnemonicSoFar, String digitsLeft) {

        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
            mnemonicSoFar = "";
        } else {
            String value = digitLetters(digitsLeft.charAt(0));
            for (int i = 0; i < value.length(); i++) {
                recursiveMnemonics(mnemonics, mnemonicSoFar + value.charAt(i),
                        digitsLeft.substring(1));
            }
        }
    }

    /*
     * Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = { "0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

    }
    // used by method digitLetters

    /*
     * helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /**
     * helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     * 
     * @param s 
     * @return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * 
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {

        // Sets up the canvas
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }


    /**
     * Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * 
     * @param g The Graphics object to use to fill rectangles
     * 
     * @param size the size of the current square
     * 
     * @param limit the smallest allowable size of squares
     * 
     * @param x the x coordinate of the upper left corner of the current square
     * 
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {

        // if the length of the square is less than or equal to the limit value do
        // nothing
        if (size > limit) {
            // the length of the square must be greater than the limit value) divide the
            // square into a 3 by 3 grid which will contain 9 sub squares. "Cut out" the
            // middle square
            // and then create a Sierpenski carpet in the 8 remaining sub squares

            int newSize = size / 3;

            // Draw the cutout at x = newSize and newSize / 3
            g.fillRect((int) x + newSize, (int) y + newSize, newSize, newSize);

            // Recursively draw out the rest of the carpet

            // Top Left
            drawSquares(g, newSize, limit, x, y);

            // Middle Left
            drawSquares(g, newSize, limit, x, y + newSize);

            // Bottom Left
            drawSquares(g, newSize, limit, x, y + newSize * 2);

            // Top Center
            drawSquares(g, newSize, limit, x + newSize, y);

            // Bottom Center
            drawSquares(g, newSize, limit, x + newSize, y + newSize * 2);

            // Top Right
            drawSquares(g, newSize, limit, x + newSize * 2, y);

            // Middle Right
            drawSquares(g, newSize, limit, x + newSize * 2, y + newSize);

            // Bottom Right
            drawSquares(g, newSize, limit, x + newSize * 2, y + newSize * 2);

        }
    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>
     * pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>
     * post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * 
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     *         specified by row, column can reach the edge of the map, false
     *         otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {

        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }

        // First case prior can't equal itself
        return flowsOffHelper(map, map[row][col] + 1, row, col); // Change as necessary.
    }

    /**
     * Helper method for canFlowOffMap that carries out the recursive call
     * 
     * @param map
     * @param checked
     * @param row
     * @param col
     * @return
     */
    private static boolean flowsOffHelper(int[][] map, int prior, int row, int col) {

        // Base case: flows off and so we return
        // Not sure why Mike said we shouldn't have to call this
        if (!inbounds(row, col, map)) {
            return true;
        }

        // If this is greater than prior return false
        if (map[row][col] >= prior) {
            return false;
        }

        // Check above
        if (flowsOffHelper(map, map[row][col], row - 1, col)) {
            return true;
        }

        // Check right
        if (flowsOffHelper(map, map[row][col], row, col + 1)) {
            return true;
        }

        // Check below
        if (flowsOffHelper(map, map[row][col], row + 1, col)) {
            return true;
        }

        // Check left
        if (flowsOffHelper(map, map[row][col], row, col - 1)) {
            return true;
        }

        return false;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br>
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br>
     * post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * 
     * @param numTeams  the number of teams to form.
     *                  Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     *         with the maximum total ability and the team with the minimum total
     *         ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {

        // Check preconditions
        if(numTeams < 2 || abilities == null || abilities.length < numTeams){
            throw new IllegalArgumentException("Failed precondition: "
                    + "numTeams >= 2, abilities != null, abilities.length >= numTeams."); 
        }

        // First create an ArrayList with all the possible team
        Team[] teams = new Team[numTeams];

        /*
         * A Team object stores 2 values:
         * - int totalAbility
         * - boolean atLeastOnePerson
         *      - The reason for this value is the case where a player
         *          is so bad at the game that he/she has an ability
         *          of 0. In that case you can't really check 
         *          totalAbilty == 0 to see if the team is empty.
         * 
         * The reason for a Team instead of a 2d array as everyone
         * seem to be suggesting is: I think it looks cleaner.
         * 
         * It's an array of arrays or an array of Objects
         * Not a large difference in effciency
         * 
         * I actually tested it using a 2d array where the first index
         * is the totalAbility and the second index is the numPlayers
         * 
         * Avg time for Object Array: 23.8s
         * Avg time for Array of Arrays: 25.9s
         * 
         * Writing all this because a TA told me this was too much and
         * also inefficient compared to an array. I thought that it 
         * should be the same, if not slightly better and decided to
         * test it.  
         * 
         */

        for (int i = 0; i < numTeams; i++) {
            teams[i] = new Team();
        }

        return minDiffHelper(teams, abilities, 0);
    }


    /**
     * Helper method for minDifference
     * 
     * @param teams
     * @param abilities
     * @param index
     * @return
     */
    private static int minDiffHelper(Team[] teams, int[] abilities, int index) {

        // Base Case:
        // When index is past abilities
        if (index >= abilities.length) {

            int highest = Integer.MIN_VALUE;
            int lowest = Integer.MAX_VALUE;

            for (Team team : teams) {

                // If a team has 0 players, return max
                if (!team.getAtLeastOnePerson()) {
                    return Integer.MAX_VALUE;
                }

                highest = Math.max(highest, team.getTotalAbility());
                lowest = Math.min(lowest, team.getTotalAbility());
            }

            // If all the teams have at least one person then we want to find the
            // difference between the highest and lowest values.
            int difference = highest - lowest;

            return difference;
        }

        // Store curPlayer and worst possible value
        int curPlayer = abilities[index];
        int minDiff = Integer.MAX_VALUE;

        // Iterate through all possible teams the current player
        // can be placed on
        for (int i = 0; i < teams.length; i++) {

            Team possibleTeam = teams[i];

            // Check to see if team is empty. If it is empty, then
            // make it not empty and make isFirstPlayer == true

            // isFirstPlayer needs to be stored, so we can take
            // the curPlayer out of the team at the end of the
            // loop iteration
            boolean isFirstPlayer = !possibleTeam.getAtLeastOnePerson();
            if (isFirstPlayer) {
                possibleTeam.setAtLeastOnePerson(true);
            }

            // Put the curPlayer into the team
            possibleTeam.addPlayer(curPlayer);

            // Recursive call and compare with min
            int bestMin = minDiffHelper(teams, abilities, index + 1);
            if (bestMin < minDiff) {
                minDiff = bestMin;
            }

            // Take the player off of the team
            possibleTeam.removePlayer(curPlayer);

            // Update atLeastOnePerson if need be
            if (isFirstPlayer) {
                possibleTeam.setAtLeastOnePerson(false);
            }
        }

        return minDiff;

    }


    /**
     * Problem 8: Maze solver.
     * <br>
     * pre: board != null
     * <br>
     * pre: board is a rectangular matrix
     * <br>
     * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>
     * pre: there is a single 'S' character present
     * <br>
     * post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * 
     * @param rawMaze represents the maze we want to escape.
     *                rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {

        // Check preconditions
        if (rawMaze == null || !isRectangular(rawMaze) || !hasCorrectChars(rawMaze)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }

        // Check if an end even exists
        if (!isEnd(rawMaze)) {
            return 0;
        }

        char[][] maze = new char[rawMaze.length][rawMaze[0].length];

        for (int i = 0; i < rawMaze.length; i++) {
            for (int j = 0; j < rawMaze[0].length; j++) {
                maze[i][j] = rawMaze[i][j];
            }
        }

        int[] start = startPoint(maze);
        int totalCoins = totalCoins(maze);

        return mazeHelper(maze, 0, totalCoins, start[0], start[1]);
    }


    /**
     * Finds the start point
     * 
     * @param maze
     * @return
     */
    private static int[] startPoint(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * Finds if the end point exists
     * 
     * @param maze
     * @return
     */
    private static boolean isEnd(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'E') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Counts up all the coins
     * 
     * @param maze
     * @return
     */
    private static int totalCoins(char[][] maze) {
        int coins = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == '$') {
                    coins++;
                }
            }
        }
        return coins;
    }

    /**
     * The primary helper method for canEscapeMaze
     * 
     * @param maze
     * @param coins
     * @param totalCoins
     * @param row
     * @param col
     * @return
     */
    private static int mazeHelper(char[][] maze, int coins, int totalCoins, int row, int col) {

        // Check if point exists in maze (Could also be considered a Base Case)
        // If not return 0;
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return 0;
        }

        // Store the current point
        char curPoint = maze[row][col];

        // Base Case 1:
        // We have reached the end
        if (curPoint == 'E') {
            // If we have collected the max coins possible then we return 2
            // If not then 1
            return (coins == totalCoins) ? 2 : 1;
        }

        // Base Case 2:
        // We reach an *
        if (curPoint == '*') {
            return 0;
        }

        // Now we mutate the maze depending on it's cell value
        switch (curPoint) {
            // If the curPoint is start, we turn it into green
            case 'S':
                maze[row][col] = 'G';
                break;

            // If the curPoint is a coin then we want to collect it
            case '$':
                coins++;
                maze[row][col] = 'G';
                break;

            // If the curPoint is green, we turn it into an yellow
            case 'G':
                maze[row][col] = 'Y';
                break;

            // If the curPoint is yellow, we turn it into an *
            case 'Y':
                maze[row][col] = '*';
                break;
        }

        // Movement:
        // We want to move through every direction and see if 1 or 2 is possible
        return makeMove(maze, curPoint, coins, totalCoins, row, col);
    }

    /**
     * Recursively moves up, down, left, and right. Whenever a 2 is found, the result is returned
     *  immediately for optimization purposes. 
     * 
     * @param maze
     * @param curPoint
     * @param coins
     * @param totalCoins
     * @param row
     * @param col
     * @return
     */
    private static int makeMove(char[][] maze, char curPoint, int coins, int totalCoins, int row, int col) {
        int result = 0;

        // Move up
        result = Math.max(result, mazeHelper(maze, coins, totalCoins, row - 1, col));
        if (result == 2) {
            return 2;
        }

        // Move down
        result = Math.max(result, mazeHelper(maze, coins, totalCoins, row + 1, col));
        if (result == 2) {
            return 2;
        }

        // Move left
        result = Math.max(result, mazeHelper(maze, coins, totalCoins, row, col - 1));
        if (result == 2) {
            return 2;
        }

        // Move right
        result = Math.max(result, mazeHelper(maze, coins, totalCoins, row, col + 1));
        if (result == 2) {
            return 2;
        }

        // Undo moves
        maze[row][col] = curPoint;

        return result;
    }

    /**
     * helper method for canEscapeMaze (overloaded w/ another helper)
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     * 
     * @param mat
     * @return
     */
    private static boolean isRectangular(char[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Finds the start point
     * 
     * @param maze
     * @return
     */
    private static boolean hasCorrectChars(char[][] maze) {

        boolean hasStart = false;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                // 'S', 'E', '$', 'G', 'Y', and '*'
                char curChar = maze[i][j];
                if (curChar != '*' && 
                    curChar != 'G' &&
                    curChar != 'Y' &&
                    curChar != '$' &&
                    curChar != 'E' &&
                    curChar != 'S') {
                    return false;
                }
                if(curChar == 'S'){
                    hasStart = true;
                }
            }
        }
        return hasStart;
    }


}


/**
 * Class for the FairTeams problem
 */
class Team {
    private int totalAbility;
    private boolean atLeastOnePerson;

    public int getTotalAbility() {
        return totalAbility;
    }

    public boolean getAtLeastOnePerson() {
        return atLeastOnePerson;
    }

    public void addPlayer(int player) {
        this.totalAbility += player;
    }

    public void removePlayer(int player) {
        this.totalAbility -= player;
    }

    public void setAtLeastOnePerson(boolean atLeastOnePerson) {
        this.atLeastOnePerson = atLeastOnePerson;
    }

}
