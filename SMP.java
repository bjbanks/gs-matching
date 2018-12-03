// Bryson Banks

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class SMP {
    
    private static int size;
    private static int[][] menPrefs;
    private static int[][] womenPrefs;
    private static int[] matches;

    public static void main(String[] args) {
        // verify two arguments: inputFile, m/w
        if (args.length != 2) {
            System.out.println("Error. Java program SMP expects 2 arguments specifying an input file and m/w.");
            return;
        }
        String inputFile = args[0];
        
        char optimality = Character.toLowerCase(args[1].charAt(0));
        if (optimality != 'm' && optimality != 'w') {
            System.out.println("Error. Invalid argument: " + optimality);
            return;
        }
                
        // parse the input file - will initialize size, menPrefs, and womenPrefs
        if (!parseInput(inputFile)) {
            return;
        }
        
        long startTime=System.nanoTime();
        
        if (optimality == 'm') {
            matches = match(menPrefs, womenPrefs);
        } else if (optimality == 'w') {
            matches = match(womenPrefs, menPrefs);
        }
        printMatches(matches);

        long endTime=System.nanoTime();
        long totalTime=endTime-startTime;
        System.out.println("Total time taken for SMP is " + totalTime);
    }
    
    private static void printMatches(int[] matches) {
        for (int i = 0; i < matches.length; i++) {
            System.out.println("(" + (i+1) + "," + (matches[i]+1) + ")");
        }
    }
    
    private static int[] match(int[][] xPrefs, int[][] yPrefs) {
        // initialize variables
        int[] xMatches = new int[size];
        int[] yMatches = new int[size];
        int[] xPrefIndex = new int[size];
        LinkedList<Integer> freeX = new LinkedList<>();
        HashSet<Integer> freeY = new HashSet<>();
        for (int i = 0; i < size; i++) {
            xMatches[i] = -1;
            yMatches[i] = -1;
            xPrefIndex[i] = 0;
            freeX.add(i);
            freeY.add(i);
        }
        // algorithm
        while (!freeX.isEmpty()) {
            int x = freeX.removeFirst();
            int y = xPrefs[x][xPrefIndex[x]];
            xPrefIndex[x] = xPrefIndex[x] + 1;
            if (freeY.contains(y)) {
                xMatches[x] = y;
                yMatches[y] = x;
                freeY.remove(y);
            } else {
                int oldX = yMatches[y];
                for (int i = 0; i < size; i++) {
                    if (yPrefs[y][i] == x) {
                        xMatches[x] = y;
                        yMatches[y] = x;
                        freeX.add(oldX);
                        break;
                    }
                    if (yPrefs[y][i] == oldX) {
                        freeX.add(x);
                        break;
                    }
                }
            }
        }
        return xMatches;
    }
    
    // parse given input file
    // returns true if success, or false if parsing fails
    private static boolean parseInput(String inputFile) {
        // create file scanner object
        Scanner scanner;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found: " + inputFile);
            return false;
        }
                    
        // parse the size from first line
        size = parseSize(scanner);
        if (size < 0) {
            System.out.println("Error. Failed to parse matrix size. Input file is incorrectly formatted.");
            scanner.close();
            return false;
        }

        // parse the square matrix of men preferences
        menPrefs = parseMatrix(scanner, size);
        if (menPrefs == null) {
            System.out.println("Error. Failed to parse men prefs. Input file is incorrectly formatted.");
            scanner.close();
            return false;
        }

        // parse the square matrix of women preferences
        womenPrefs = parseMatrix(scanner, size);
        if (womenPrefs == null) {
            System.out.println("Error. Failed to parse women prefs. Input file is incorrectly formatted.");
            scanner.close();
            return false;
        }
            
        // done parsing file, success
        scanner.close();
        return true;
    }

    // parse input file to retrieve matrix size
    // returns size, or -1 if parsing fails
    private static int parseSize(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            return -1;
        }
    }       

    // parse input file to retrieve square matrix
    // returns matrix, or null if parsing fails
    private static int[][] parseMatrix(Scanner scanner, int size) {
        int[][] matrix = new int[size][size];
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                if (scanner.hasNextInt()) {
                    matrix[rowIndex][colIndex] = scanner.nextInt() - 1;
                } else {
                    return null;
                }
            }
        }
        return matrix;
    }

}
