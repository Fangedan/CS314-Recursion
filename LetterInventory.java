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
    private String lowerCaseWord;
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

        lowerCaseWord = word.toLowerCase();
        lettersAndIndex = new TreeMap<>();
        frequencies = new ArrayList<>(ALPHABET_LENGTH);

        // Initialize lettersAndIndex with 'a' to 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            lettersAndIndex.put(c, lettersAndIndex.size()); // Assign index
            frequencies.add(0); // Initialize frequencies list with 0
        }

        // Populate frequency list
        for (char c : lowerCaseWord.toCharArray()) {
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
    
    /**
    * Returns a string representation of the LetterInventory.
    * The string contains all letters in sorted order based on their frequency,
    * with each letter appearing as many times as it occurs in the inventory.
    *
    * @return A string representation of the inventory containing only the stored letters.
    */
    public String toString(){
        StringBuilder result = new StringBuilder("");
        for (char c = 'a'; c <= 'z'; c++){
            if (frequencies.get(lettersAndIndex.get(c))>=1){
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Creates and returns a new LetterInventory object that represents
     * the sum of the frequencies of letters in this inventory and the given inventory.
     *
     * @param obj The LetterInventory object to add to this inventory.
     * @return A new LetterInventory containing the combined letter counts.
     * @throws IllegalArgumentException if obj is null.
     */
    public LetterInventory add(LetterInventory obj) {
        // Check precondition
        if (obj == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                                    "add. Input inventory cannot be null.");
        }

        // Create a new inventory to store the sum of frequencies
        LetterInventory result = new LetterInventory(lowerCaseWord);

        // Loop through all letter frequencies and add them
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            result.frequencies.set(i, this.frequencies.get(i) + 
            		obj.frequencies.get(i));
        }

        return result;
    }

    /**
    * Creates and returns a new LetterInventory object that represents
    * the difference between this inventory and the given inventory.
    * If any letter count becomes negative, returns null.
    *
    * @param obj The LetterInventory object to subtract from this inventory.
    * @return A new LetterInventory containing the subtracted letter counts, 
    *         or null if subtraction results in negative counts.
    * @throws IllegalArgumentException if obj is null.
    */
    public LetterInventory subtract(LetterInventory obj) {
        // Check precondition
        if (obj == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                                    "subtract. Input inventory cannot be null.");
        }

        // Create a new inventory to store the difference of frequencies
        LetterInventory result = new LetterInventory(lowerCaseWord);

        // Loop through all letter frequencies and subtract them
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            int newFrequency = this.frequencies.get(i) - obj.frequencies.get(i);

            // If subtraction results in a negative frequency, return null
            if (newFrequency < 0) {
                return null;
            }

            // Else, set the new value after subtracting
            result.frequencies.set(i, newFrequency);
        }

        return result;
    }

    /**
    * Checks whether this LetterInventory is equal to another object.
    * Two LetterInventory objects are considered equal if they have 
    * the same letter frequencies.
    *
    * @param obj The object to compare with this LetterInventory.
    * @return true if obj is a LetterInventory with identical frequencies, 
    *         false otherwise.
    */
    public boolean equals(Object obj) {
        // Check if obj is null
        if (obj == null) {
            return false;
        }

        // Ensure obj is an instance of LetterInventory before casting
        if (!(obj instanceof LetterInventory)) {
            return false;
        }

        LetterInventory other = (LetterInventory) obj;

        // Return false if the sizes do not match
        if (other.frequencies.size() != this.frequencies.size()) {
            return false;
        }

        // Compare each letter frequency
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            if (!this.frequencies.get(i).equals(other.frequencies.get(i))) {
                return false;
            }
        }

        return true;
    }
}