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

public class LetterInventory {

    // Instance Variables
    private int[] letterOccurance;
    private int size;

    // Constants
    private final int NUM_LETTERS = 26;
    private final int ASCII_NUM_LOWER = 97;
    private final int ASCII_NUM_UPPER = 65;
    private final int ASCII_CASE_DIFF = ASCII_NUM_LOWER - ASCII_NUM_UPPER;

    /**
     * Constructor for the LetterInventory class
     * pre: input != null
     * 
     * @param input
     */
    public LetterInventory(String input) {

        // Check preconditions
        if (input == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "input != null");
        }

        letterOccurance = new int[NUM_LETTERS];
        String lowercase = input.toLowerCase();

        char[] inputArray = lowercase.toCharArray();
        for (char ch : inputArray) {
            if ('a' <= ch && ch <= 'z') {
                // The Dec# for 'a' on the ASCII table starts at 97
                letterOccurance[ch - ASCII_NUM_LOWER]++;
                size++;
            }
        }

    }

    /**
     * Accepts a char and returns the frequency of that letter in this
     * LetterInventory
     * 
     * pre: ch must be an English letter
     * 
     * @param ch
     * @return frequency of ch in the Letter Inventory
     */
    public int get(char ch) {
        // Check preconditions
        if (('a' > ch && ch > 'z') || ('A' > ch && ch > 'Z')) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "ch must be an English letter");
        }

        char lowerChar = ch;

        // If the letter is uppercase turn it lowercase
        if ('A' <= ch && ch <= 'Z') {
            lowerChar = (char) (ch + ASCII_CASE_DIFF);
        }

        // The Dec# for 'a' on the ASCII table starts at 97
        return letterOccurance[lowerChar - ASCII_NUM_LOWER];
    }

    /**
     * The method returns a new LetterInventory created by adding the frequencies
     * from the calling LetterInventory object to the frequencies of the letters in
     * the explicit parameter.
     * 
     * pre: letInv != null
     * 
     * @param letInv LetterInventory object
     * @return new LetterInventory created by adding the
     *         frequencies from the calling LetterInventory object to the
     *         frequencies of the letters in the explicit parameter
     */
    public LetterInventory add(LetterInventory letInv) {

        // Check preconditions
        if (letInv == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "letInv != null");
        }

        LetterInventory output = new LetterInventory("");

        for (int i = 0; i < NUM_LETTERS; i++) {

            int thisOccurance = this.letterOccurance[i];
            int otherOccurance = letInv.letterOccurance[i];
            int sum = otherOccurance + thisOccurance;

            if (sum >= 0) {
                output.letterOccurance[i] = sum;
                output.size += sum;
            }
        }

        return output;
    }

    /**
     * The method returns a new LetterInventory created by subtracting the
     * frequencies
     * from the calling LetterInventory object to the frequencies of the letters in
     * the explicit parameter.
     * 
     * pre: letInv != null
     * 
     * @param letInv LetterInventory object
     * @return new LetterInventory created by subtracting the
     *         frequencies from the calling LetterInventory object to the
     *         frequencies of the letters in the explicit parameter
     */
    public LetterInventory subtract(LetterInventory letInv) {

        // Check preconditions
        if (letInv == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "letInv != null");
        }

        LetterInventory output = new LetterInventory("");

        for (int i = 0; i < NUM_LETTERS; i++) {

            int thisOccurance = this.letterOccurance[i];
            int otherOccurance = letInv.letterOccurance[i];
            int difference = thisOccurance - otherOccurance;

            if (difference >= 0) {
                output.letterOccurance[i] = difference;
                output.size += difference;
            } else {
                return null;
            }

        }

        return output;
    }

    /**
     * Returns the total number of letters in this LetterInventory.
     * 
     * @return total num letters in the LetterInventory
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the size of this LetterInventory is 0, false otherwise
     * 
     * @return true if the size of this LetterInventory is 0, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * toString for the LetterInventory class
     */
    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < letterOccurance[i]; j++) {
                output.append((char) (i + ASCII_NUM_LOWER));
            }
        }
        return output.toString();
    }

    /**
     * Overrides the equals method
     * Two Letter Inventories are equal if their letter occurances are the same
     */
    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof LetterInventory)){
            return false;
        }

        LetterInventory other = (LetterInventory) obj;

        for(int i = 0; i < NUM_LETTERS; i++){
            if(this.letterOccurance[i] != other.letterOccurance[i]){
                return false;
            }
        }
        return true;
    }

}
