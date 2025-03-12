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
        int[] numsForDouble = { 0, 0, 1, 1, 2, 2, 4, 4, 8 };
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 4;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 1 passed. next is double.");
        } else {
            System.out.println("Test 1 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { -5, 10, 0, 0, 0, 256, 128, 256, 0, 0};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 4;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 2 passed. next is double.");
        } else {
            System.out.println("Test 2 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }
        System.out.println();
    }

    // Test the Sierpinski carpet method.
    private static void doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);
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

        int[] abilities = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        showFairTeamsResults(Recursive.minDifference(9, abilities), 7, 1);
        showFairTeamsResults(Recursive.minDifference(5, abilities), 0, 2);
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