/*  Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: RAHIK N SIKDER
 *  email address: sikder.rahik@utexas.edu
 *  UTEID: rns2359
 *  Section 5 digit ID: 52679
 *  Grader name: Casey, the Cool
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

// Last TODOs here: 
// TODO: Every method checks preconditions

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance variables / fields
    private TreeSet<String> words;
    private boolean debug;

    private String curPattern;
    private int numGuesses;
    private ArrayList<Character> guessesMade;
    private ArrayList<String> curWords;
    private HangmanDifficulty difficulty;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * 
     * @param words   A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {

        // Check preconditions
        if (words == null || words.size() == 0) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "words != null, words.size() > 0");
        }

        this.debug = debugOn;
        // Make a deep copy of words
        this.words = new TreeSet<String>(words);
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * 
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        this(words, false);
    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * 
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     *         with the given length
     */
    public int numWords(int length) {
        int counter = 0;
        // Flourless Chocolate Powdered Cake or whatever
        // Just incrementns counter whenever the for loop encounters a word
        // that equals the length param.
        for (String s : words) {
            counter += s.length() == length ? 1 : 0;
        }
        return counter;
    }

    /**
     * Get for a new round of Hangman. Think of a round as a
     * complete game of Hangman.
     * 
     * @param wordLen    the length of the word to pick this time.
     *                   numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     *                   player loses the round. numGuesses >= 1
     * @param diff       The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {

        // Check preconditions
        if (numWords(wordLen) <= 0 || numGuesses < 1) {
            throw new IllegalArgumentException("Violation of preconditions: " +
                    "numWords(wordLen) > 0, numGuesses >= 1");
        }

        this.numGuesses = numGuesses;
        this.guessesMade = new ArrayList<Character>();

        // Seems like overkill for such a small number of operations
        // But theoretically wordLen can be incredibly large
        // So this is done to prevent weird stress tests like that
        StringBuilder tempPattern = new StringBuilder();
        for (int i = 0; i < wordLen; i++) {
            tempPattern.append('-');
        }
        this.curPattern = tempPattern.toString();

        this.curWords = new ArrayList<String>();
        for (String word : words) {
            if (word.length() == wordLen) {
                curWords.add(word);
            }
        }

        this.difficulty = diff;
    }

    /**
     * The number of words still possible (live) based on the guesses so far.
     * Guesses will eliminate possible words.
     * 
     * @return the number of words that are still possibilities based on the
     *         original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return curWords.size();
    }

    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * 
     * @return the number of wrong guesses the user has left
     *         in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numGuesses - guessesMade.size();
    }

    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * 
     * @return a String that contains the letters the user
     *         has guessed so far during this round.
     */
    public String getGuessesMade() {
        // In Mike Scott's example, he displayed the guesses
        // in alphabetical order
        Collections.sort(guessesMade);
        return guessesMade.toString();
    }

    /**
     * Check the status of a character.
     * 
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     *         false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return guessesMade.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-''s for
     * unrevealed (or guessed) characters and the actual character
     * for "correctly guessed" characters.
     * 
     * @return the current pattern.
     */
    public String getPattern() {
        return curPattern;
    }

    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * 
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     *         words in each of the new patterns.
     *         The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {

        // Check preconditions
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("That guess has already been made");
        }
        // Add guess to the guessesMade
        guessesMade.add(guess);

        // This method generates two maps and a list

        // The first map is <String , ArrayList<String>> and it keeps the actual list of
        // all
        // the patterns

        TreeMap<String, ArrayList<String>> allPatterns = new TreeMap<>();

        // Goes through all the curWords and puts it into a corresponding
        // ArrayList in allPatterns based on it's pattern
        for (String word : curWords) {
            String pattern = findPattern(guess, word);
            if (allPatterns.containsKey(pattern)) {
                allPatterns.get(pattern).add(word);
            } else {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(word);
                allPatterns.put(pattern, tempList);
            }

        }

        // The second map is <String, Integer> and it's made using the first map
        // where the integer is just the length of the List
        // This is the map that is returned.
        TreeMap<String, Integer> allPatternLengths = new TreeMap<>();

        // The list holda all the <String, Integer> entries found in allPatternsLengths
        // Done through a Comparable nested class called PatternEntry
        // Primary purpose is so that Collections.sort() can be called
        // to find the top choices for the next pattern.
        ArrayList<PatternEntry> patternList = new ArrayList<>(guess);

        // Use entrySet() to get all the key-value pairs from allPatterns
        // and put them into allPatternLengths and patternList
        for (Map.Entry<String, ArrayList<String>> entry : allPatterns.entrySet()) {
            allPatternLengths.put(entry.getKey(), entry.getValue().size());
            patternList.add(new PatternEntry(entry.getKey(), entry.getValue().size()));
        }

        Collections.sort(patternList);

        // Difficulty determining which list should be chosen
        boolean pickSecondHardest = false;
        switch (difficulty) {
            case EASY:
                if (guessesMade.size() % 2 == 0) {
                    pickSecondHardest = true;
                }
                break;
            case MEDIUM:
                if (guessesMade.size() % 4 == 0) {
                    pickSecondHardest = true;
                }
                break;
            case HARD: // Redundant code sure, but I like being explicit
                pickSecondHardest = false;
                break;
        }

        if (patternList.size() <= 1) {
            pickSecondHardest = false;
            displayDebug("Should pick second hardest pattern this turn," +
                    " but only one pattern available.\n");
        }

        // The curPattern is updated based on difficulty
        // And curWords is updated based on the changed pattern
        if (pickSecondHardest) {
            displayDebug("Picking the second hardest list");
            curPattern = patternList.get(1).pat;
            curWords = allPatterns.get(curPattern);
        } else {
            displayDebug("Picking hardest list.");
            curPattern = patternList.get(0).pat;
            curWords = allPatterns.get(curPattern);
        }

        displayDebug("Current Pattern: " + curPattern);
        displayDebug("Guess made: " + guess);
        displayDebug("All pattern entries \n" + patternList);

        return allPatternLengths;
    }

    /**
     * A nested class that stores a pattern and the number of words that corresspond
     * with that pattern.
     * This class implements the Comparable interface so that these can be put into
     * a list and sorted by hardest diffifultly using Collections.sort
     */
    private static class PatternEntry implements Comparable<PatternEntry> {

        // Instance variables
        // Even though these are private, they can be accesed within HangmanManager
        private String pat;
        private int numOfPatterns;

        /**
         * Constructor for a PatternEntry object
         * 
         * @param pat           pat != null
         * @param numOfPatterns number of words that fit the pattern
         */
        public PatternEntry(String pat, int numOfPatterns) {

            // Check preconditions
            if (pat == null) {
                throw new IllegalArgumentException("Violation of precondition: " +
                        "pat != null");
            }

            this.pat = pat;
            this.numOfPatterns = numOfPatterns;
        }

        /**
         * 
         * @return The number of '-'s or unknown letters within pat
         */
        private int getNumUnknowns() {
            int counter = 0;
            for (char c : pat.toCharArray()) {
                if (c == '-') {
                    counter++;
                }
            }
            return counter;
        }

        /**
         * pre: other != null
         * 
         * @param other Another PatternEntry object that
         *              this is being compared to
         * 
         * @return a negative integer, zero, or a positive integer
         *         as this object is harder than, equal to, or easier than
         *         the specified object
         */
        public int compareTo(PatternEntry other) {

            // Check preconditions
            // NullPointer instead of IllegalArgument based on the Java
            // documentation for the Comparable interface
            if (other == null) {
                throw new NullPointerException(
                        "Cannot compare PatteryEntry with null object");
            }

            // Want to do (other - this) because in this case having
            // a larger val is better than a smaller one
            if (this.numOfPatterns != other.numOfPatterns) {
                return other.numOfPatterns - this.numOfPatterns;
            }
            // If both values are equal then look at which pattern
            // reveals the least
            if (this.getNumUnknowns() != other.getNumUnknowns()) {
                return other.getNumUnknowns() - this.getNumUnknowns();
            }
            // Last case, return lexigraphical ordering
            return this.pat.compareTo(other.pat);
        }

        @Override
        /**
         * @return A String depiction of a PatternEntry object
         *         Shown as a pair (pat, numOfPatterns)
         */
        public String toString() {
            return "( " + pat + ", " + numOfPatterns + " )";
        }
    }

    /**
     * Takes in a guess and a word and finds the correlated
     * pattern the word falls under based on the previous
     * pattern.
     * 
     * @param guess a char of the user's guess
     * @param word  word != null
     * @return the pattern for word based on the curPattern
     *         and the given guess
     */
    public String findPattern(char guess, String word) {

        // Check preconditions
        if (word == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "word != null");

        }

        StringBuilder newPattern = new StringBuilder();
        // Go through character by character and compare
        // with the current pattern
        char[] wordCharArray = word.toCharArray();
        char[] patternCharArray = curPattern.toCharArray();

        for (int i = 0; i < wordCharArray.length; i++) {
            if (wordCharArray[i] == guess) {
                newPattern.append(guess);
            } else {
                newPattern.append(patternCharArray[i]);
            }
        }

        return newPattern.toString();
    }

    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * 
     * pre: numWordsCurrent() > 0
     * 
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {

        // Check preconditions
        if (numWordsCurrent() <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "numWordsCurrent() > 0");
        }

        // Case 1: There is only 1 word left
        if (curWords.size() == 1) {
            return curWords.get(0);
        }

        // Case 2: There are more than 1 words left
        // Generates a random number between [0, curWords.size() - 1]
        int randomIndex = (int) (Math.random() * curWords.size());
        return curWords.get(randomIndex);
    }

    /**
     * Method used to reduce excessive if statements from writing
     * debug methods
     * 
     * @param message != null. The debug message to be displayed
     */
    private void displayDebug(String message) {

        // Check preconditions
        if (message == null) {
            throw new IllegalArgumentException("Violation of precondition:"
                    + " message != null");
        }

        if (debug) {
            System.out.println("DEBUGGING: " + message);
        }
    }
}