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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class AnagramSolver {

    // Instance variables
    private HashMap<String, LetterInventory> dictionary;
    private List<List<String>> list;
    private List<String> viableWords;
    private List<LetterInventory> viableLetInvs;

    /**
     * pre: list != null
     * 
     * @param dictionary list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        this.dictionary = new HashMap<>();
        for (String word : dictionary) {
            this.dictionary.put(word, new LetterInventory(word));
        }
    }

    /**
     * pre: maxWords >= 0, s != null, s contains at least one
     * English letter.
     * 
     * @param s
     * @param maxWords
     * @return a list of anagrams that can be formed from s with
     *         no more than maxWords, unless maxWords is 0 in which case
     *         there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String curString, int maxWords) {

        // Check preconditions
        if (maxWords < 0 || curString == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "maxWords >= 0, s != null");
        }

        // Reset viableWords and viableLetInvs
        LetterInventory curLetInv = new LetterInventory(curString);
        viableWords = new ArrayList<>();
        viableLetInvs = new ArrayList<>();

        /**
         * One TA (Namish) suggested that I only use viableWords and then access the dictionary
         * using the word from viableWords as the key.
         * 
         * I thought it seemed like a good idea that saves memory - however for some reason that 
         * approach was noticably slower by 2 seconds. 
         * 
         * Namish mentioned this could be due to "collisions," and lead to marginal time 
         * differences
         * 
         * For now, I believe that the memory used by creating another ArrayList is worth to 
         * achieve even marginal time saves on the CS Lab Computer, as it really shouldn't be 
         * toooooo much more for the computer to handle RAM-wise (right?)
         */

        // Pre processing step
        // creating a dictionary subset
        for (Map.Entry<String, LetterInventory> set : dictionary.entrySet()) {

            // If a word is larger than the input it isn't considered
            if (set.getKey().length() <= curString.length()) {

                LetterInventory difference = curLetInv.subtract(set.getValue());

                if (difference != null) {
                    viableWords.add(set.getKey());
                    viableLetInvs.add(set.getValue());
                }
            }
        }

        // If maxWords is 0, then the maxWords is the numCharacters
        int numWords = (maxWords == 0) ? curString.length() : maxWords;

        // Reset list and create the curWordComb
        list = new ArrayList<>();
        List<String> curWordComb = new ArrayList<>();

        getAnagramsHelper(curWordComb, curLetInv, numWords, 0);
        Collections.sort(list, new AnagramComparator());
        return list;
    }

    /**
     * Helper for the getAnagrams method
     * 
     * @param curWordComb
     * @param curLetInv
     * @param maxWords
     */
    private void getAnagramsHelper(List<String> curWordComb, LetterInventory curLetInv,
            int maxWords, int index) {

        // Base Case 1: Max Words == 0 -> Do nothing
        // Base Case 2: curLetInv.size() == 0 -> add curWordComb to list
        if (curLetInv.size() == 0) {
            List<String> listToAdd = new ArrayList<>(curWordComb);
            Collections.sort(listToAdd);
            list.add(listToAdd);
        }

        if (maxWords != 0 && curLetInv.size() != 0) {

            // Go through every element in smaller dictionary
            for (int i = index; i < viableWords.size(); i++) {

                // Find if dictionary entry is a subset of curLetInv
                LetterInventory difference = curLetInv.subtract(viableLetInvs.get(i));

                // If a match is found, add the word to the curWordComb and make a recursive
                // call
                if (difference != null) {
                    curWordComb.add(viableWords.get(i));
                    getAnagramsHelper(curWordComb, difference, maxWords - 1, i);

                    // Remove word after recursive call is made.
                    curWordComb.remove(curWordComb.size() - 1);
                }

            }

        }
    }

    /**
     * Class that implements a comparator to sort a List of Lists containing anagrams
     */
    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> a1, List<String> a2) {
            if(a1.size() != a2.size()){
                return a1.size() - a2.size();
            }
            for(int i = 0; i < a1.size(); i++){
                if(a1.get(i).compareTo(a2.get(i)) != 0){
                     return a1.get(i).compareTo(a2.get(i));
                }
            }
            return 0;
        }
    }

}