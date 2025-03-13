import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
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

public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {

        letterInventoryTests();

        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver
                = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }

    private static void letterInventoryTests() {
        LetterInventory lets1 = new LetterInventory("");
        showTestResults("", lets1.toString(), 1, "Constructor with empty string");
    
        LetterInventory lets2 = new LetterInventory("Stanford University Jr. G");
        showTestResults("adefgiijnnorrrssttuvy", lets2.toString(), 2, "Constructor with mixed characters");

        lets1 = new LetterInventory("Hello");
        showTestResults(1, lets1.get('h'), 3, "Get method for letter 'h'");
        showTestResults(0, lets1.get('z'), 4, "Get method for non-existing letter 'z'");

        lets1 = new LetterInventory("");
        showTestResults(0, lets1.size(), 5, "Size of empty inventory");
    
        lets2 = new LetterInventory("Hello World!");
        showTestResults(10, lets2.size(), 6, "Size of 'Hello World!'");

        lets1 = new LetterInventory("");
        showTestResults(true, lets1.isEmpty(), 7, "isEmpty on empty inventory");
    
        lets2 = new LetterInventory("data");
        showTestResults(false, lets2.isEmpty(), 8, "isEmpty on non-empty inventory");

        lets1 = new LetterInventory("mmmmm");
        showTestResults("mmmmm", lets1.toString(), 9, "toString on 'mmmmm'");
    
        lets2 = new LetterInventory("A b C d");
        showTestResults("abcd", lets2.toString(), 10, "toString with mixed case and spaces");

        lets1 = new LetterInventory("data");
        lets2 = new LetterInventory("science");
        showTestResults("aaccdeeinst", lets1.add(lets2).toString(), 11, "Adding 'data' and 'science'");
    
        LetterInventory lets3 = new LetterInventory("abc");
        LetterInventory lets4 = new LetterInventory("xyz");
        showTestResults("abcxyz", lets3.add(lets4).toString(), 12, "Adding 'abc' and 'xyz'");

        lets1 = new LetterInventory("stand");
        lets2 = new LetterInventory("stanford");
        showTestResults("for", lets2.subtract(lets1).toString(), 13, "Subtracting 'stand' from 'stanford'");
    
        lets3 = new LetterInventory("apple");
        lets4 = new LetterInventory("banana");
        showTestResults(null, lets3.subtract(lets4), 14, "Subtracting 'banana' from 'apple' (should return null)");

        lets1 = new LetterInventory("ISAbelle!!");
        lets2 = new LetterInventory("abeeills");
        showTestResults(true, lets1.equals(lets2), 15, "Checking equality of two equivalent LetterInventories");
    
        lets3 = new LetterInventory("hello");
        lets4 = new LetterInventory("world");
        showTestResults(false, lets3.equals(lets4), 16, "Checking equality of two different LetterInventories");
    }

    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here. 
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0
                && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual,
                                           int testNum, String featureTested) {

        System.out.println("Test Number " + testNum + " testing "
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null)
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     *
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver,
                                        boolean displayAnagrams) {

        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams
                        = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: " 
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                    " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: "
                    + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                                        List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                                               List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: "
                + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: "
                    + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   "
                    + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                        + "but either a difference in anagrams or"
                        + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams
                    : "Wrong number of angrams read or expected";
        }
    }
}