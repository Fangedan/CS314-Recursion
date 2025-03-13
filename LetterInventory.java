/*  Student information for assignment:
*
*  On our honor, Andrew Lin and Vishal Vijayakumar's,
*  this programming assignment is our own work
*  and we have not provided this code to any other student.
*
*  Number of slip days used: 0
*
*  Student 1: Andrew Lin
*  UTEID: al58444
*  email address: alin257274@utexas.edu
*
*  Student 2: Vishal Vijayakumar
*  UTEID: vv8945
*  email address: vishal.vijayakumar@utexas.edu
*
*  Grader name: Casey
*  Section number: 50760
*/

import java.util.ArrayList;
import java.util.TreeMap;

public class LetterInventory {
    // Instance variables
    private final int ALPHABET_LENGTH = 26; // Magic Number
    private int numOfInventoryLetters;
    private String Word;
    private TreeMap<Character, Integer> lettersAndIndex;
    private ArrayList<Integer> frequencies;

    /**
     * Constructor for LetterInventory
     *
     * @param Word to create a letter inventory of.
     * @throws IllegalArgumentException if (word == null)
     */
    public LetterInventory(String word) {
        // Check precondition
        if (word == null){
            throw new IllegalArgumentException("Violation of precondition: " +
            "LetterInventory. Word may not be null.");
        }

        Word = word.toLowerCase();
        lettersAndIndex = new TreeMap<>();
        frequencies = new ArrayList<>(ALPHABET_LENGTH);

        // Initialize lettersAndIndex with 'a' to 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            lettersAndIndex.put(c, lettersAndIndex.size()); // Assign index
            frequencies.add(0); // Initialize frequencies list with 0
        }

        // Populate frequency list
        for (char c : Word.toCharArray()) {
            if ('a' > c && c > 'z') { // Ignore non-letter characters
                int index = lettersAndIndex.get(c);
                frequencies.set(index, frequencies.get(index) + 1);
                numOfInventoryLetters++;
            }
        }
    }

    /**
    * Returns the frequency of a given letter in this LetterInventory.
    *
    * @param ch The character whose frequency is to be retrieved.
    *           Must be a lowercase letter between 'a' and 'z'.
     * @throws IllegalArgumentException if ch is not between 'a' and 'z'.
     * @return The frequency of the character in this inventory. If the character 
     *         is not found, returns 0.
    */
    public int get(char ch){
        // Check precondition
        if ('a' > ch && ch > 'z') {
            throw new IllegalArgumentException("Violation of precondition: " +
            "get. Not a valid Character. Character: " + ch);
        }
        return frequencies.get(lettersAndIndex.get(ch));
    }

    /**
    * Returns the total number of letters stored in this LetterInventory.
    *
    * @return The total count of all letters in the inventory.
    */
    public int size(){
        return numOfInventoryLetters;
    }

    /**
    * Checks whether the LetterInventory is empty.
    *
    * @return true if the inventory contains no letters, false otherwise.
    */
    public boolean isEmpty(){
        return numOfInventoryLetters == 0;
    }
    
    public String toString(){
        StringBuilder result = new StringBuilder("");
        for (char c = 'a'; c <= 'z'; c++){
            if (frequencies.get(lettersAndIndex.get(c))>=1){
                result.append(c);
            }
        }
        return result.toString();
    }
}