/*  Student information for assignment:
 *
 *  On our honor, Andrew Lin and Vishal Vijayakumar,
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

import java.awt.Color;
import java.awt.Graphics; 
/**
 * Various recursive methods to be implemented.
 */
public class Recursive {

    /**
     * Problem 1: Returns the number of elements in data that are followed
     * directly by value that is double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed
     * immediately by double the value
     *
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by
     * a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "nextIsDouble. Parameter may not be null.");
        }
        // If the array has less than 2 elements, no pairs can exist
        if (data.length < 2) {
            return 0;
        }
        return countPairs(data, 0);
    }

    private static int countPairs(int[] data, int index) {
        // If we are at the end of the array, no more pairs can exist
        if (index >= data.length - 1) {
            return 0; 
        }
        
        // Check if current element is followed by double the next element
        if (data[index] * 2 == data[index + 1]) {
            // Found a pair, count it and move to the next index
            return 1 + countPairs(data, index + 1); 
        } else {
            // No pair found, move to the next index
            return countPairs(data, index + 1); 
        }
    }

    /**
     * Problem 2: Draw a Sierpinski Carpet.
     *
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     *
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
        if (size < limit) {
            return; // Base case: Stop when the square size is too small
        }

        int newSize = size / 3; // Size of the subdivided squares

        // "Cut out" the center square by drawing a white rectangle
        g.fillRect((int) (x + newSize), (int) (y + newSize), newSize, newSize);

        // Recursively draw the 8 surrounding squares
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue; // Skip the center square
                }
                drawSquares(g, newSize, limit, x + i * newSize, y + j * newSize);
            }
        }
    }

    /**
     * Problem 3: Find the minimum difference possible between teams based on
     * ability scores. The number of teams may be greater than 2. The goal is
     * to minimize the difference between the team with the maximum total
     * ability and the team with the minimum total ability.
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * post: return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     *
     * @param numTeams the number of teams to form
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability. The
     * return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        return -1;
    }
}