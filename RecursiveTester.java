/*  Student information for assignment:
*
*  On <MY|OUR> honor, <NAME1> (and <NAME2),
*  this programming assignment is <MY|OUR> own work
*  and <I|WE> have not provided this code to any other student.
*
*  Number of slip days used:
*
*  Student 1:
*  UTEID:
*  email address:
*
*  Student 2:
*  UTEID:
*  email address:
*
*  Grader name:
*  Section number:
*/

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        doNextIsDoubleTests();
        doCarpetTest();
        doFairTeamsTests();
    }

    private static void doNextIsDoubleTests() {
        int[] numsForDouble = { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 8;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 1 passed. next is double.");
        } else {
            System.out.println("Test 1 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 1, 3, 4, 2, 32, 8, 128, -5, 6 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 2 passed. next is double.");
        } else {
            System.out.println("Test 2 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 1, 0, 0, -5, -10, 32, 64, 128, 2, 9, 18 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 5;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 3 passed. next is double.");
        } else {
            System.out.println("Test 3 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 37 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 4 passed. next is double.");
        } else {
            System.out.println("Test 4 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 37, 74 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 1;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 5 passed. next is double.");
        } else {
            System.out.println("Test 5 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }
        System.out.println();
    }

    // Test the Sierpinski carpet method.
    private static void doCarpetTest() {
        // Recursive.drawCarpet(729, 4);
        // Recursive.drawCarpet(729, 1);
    }

    private static void doFairTeamsTests() {
//         System.out.println("Stress test for minDifference - may take up to a minute");
//         int[] testerArr = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60,
//         65, 70, 75, 100000};
//         Stopwatch s = new Stopwatch();
//         s.start();
//         int actualInt = Recursive.minDifference(4, testerArr);
//         s.stop();
//         System.out.println("Time to solve for 16 people on 4 teams: " + s.time() +
//         "\n");
//         System.out.println(actualInt);

        int[] abilities = { 1, 2, 3, 4, 5, 6, 7 };
        showFairTeamsResults(Recursive.minDifference(3, abilities), 1, 1);
        showFairTeamsResults(Recursive.minDifference(5, abilities), 2, 2);
        showFairTeamsResults(Recursive.minDifference(6, abilities), 4, 3);

        abilities = new int[] { 1, 12, 46, 60, 53, 86, 72, 79, 44, 7 };
        showFairTeamsResults(Recursive.minDifference(3, abilities), 3, 4);
        showFairTeamsResults(Recursive.minDifference(5, abilities), 19, 5);

        abilities = new int[] { 10, 10, 7, 7, 7 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 1, 6);

        abilities = new int[] { -10, -10, -8, -8, -8 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 4, 7);

        abilities = new int[] { -5, 5, 10, 5, 10, -15 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 0, 8);
    }

    // Show the results of a fair teams test by comparing actual and expected
    // result.
    private static void showFairTeamsResults(int actual, int expected, int testNum) {
        if (actual == expected) {
            System.out.println("Test " + testNum + " passed. min difference.");
        } else {
            System.out.println("Test " + testNum + " failed. min difference.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
    }
}