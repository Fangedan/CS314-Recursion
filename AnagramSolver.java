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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramSolver {

    // Instance Variables
    //private ArrayList<Map<String, LetterInventory>> wordsAndInventory;
    private Map<String, LetterInventory> wordMap;
    private Set<String> smallDictionary;
	/*private LetterInventory current;
	private Map<String, LetterInventory> map1;*/

    /*
     * pre: list != null
     *
     *
     * @param list Contains the words to form anagrams from.
     */
	/*public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null){
            throw new IllegalArgumentException("Violation of precondition: " +
            "AnagramSolver. Dictionary may not be null.");
        }
        wordsAndInventory = new ArrayList<>();
        for(String item : dictionary) {
            current = new LetterInventory(item);
            map1 = new HashMap<>();
			map1.put(item, current);
            wordsAndInventory.add(map1);
        }
    }*/
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary may not be null.");
        }
        //wordsAndInventory = new ArrayList<>();
        wordMap = new HashMap<>();
        
        for (String word : dictionary) {
            LetterInventory inventory = new LetterInventory(word);
            wordMap.put(word, inventory);
        }
    }

    /*
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        // Check Preconditions
        if (s == null || maxWords < 0) {
            throw new IllegalArgumentException("Invalid arguments: " +
            "String cannot be null, and maxWords must be >= 0.");
        }

        List<List<String>> result = new ArrayList<>();
        List<String> validWords = new ArrayList<>();
        LetterInventory targetInventory = new LetterInventory(s);
        
        /*
         * Iterate through dictionary, comparing accordingly with a LetterInventory
         * of String s to get a list of validWords to start getting anagrams of
         */
        for (String word : wordMap.keySet()) {
            if (targetInventory.subtract(wordMap.get(word)) != null) {
                validWords.add(word);
            }
        }

        System.out.println("DEBUGGING: validWords before: " + validWords);

        // Sort, then utilize validWords recursively in backTrack method
        Collections.sort(validWords);
        backTrack(result, new ArrayList<>(), targetInventory, validWords, 0, maxWords);
        return result;
    }

    // Helper method for getAnagrams, 
    private void backTrack(List<List<String>> result, List<String> tempList, 
                            LetterInventory remaining, List<String> words, 
                            int start, int maxWords) {
        // Base Case: used all letters in targetInventory, add tempList to result
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // Case where there are too many words added to tempList: early return
        if (maxWords > 0 && tempList.size() >= maxWords) {
            return;
        }

        /*
         * Recursive Case: Iterate through remaining targetInventory using start index,
         * create a reduced LetterInventory with current word, and if, after subtraction,
         * it is not null, recursively call backTrack with appropriate parameters.
         */
        for (int i = start; i < words.size(); i++) {
            String word = words.get(i);
            LetterInventory reduced = remaining.subtract(wordMap.get(word));

            if (reduced != null) {
                tempList.add(word);
                backTrack(result, tempList, reduced, words, i, maxWords);
                // Actual back tracking: remove tempList.size() - 1
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}