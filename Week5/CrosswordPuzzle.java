import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'crosswordPuzzle' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY crossword
     *  2. STRING words
     */

    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
    // Write your code here
    char[][] board = new char[10][10];
        for (int i = 0; i < 10; i++) board[i] = crossword.get(i).toCharArray();

        String[] wordList = words.split(";");

        solve(board, wordList, 0);

        List<String> result = new ArrayList<>();
        for (char[] row : board) result.add(new String(row));
        return result;
    }

    // Backtracking function to try placing all words
    private static boolean solve(char[][] board, String[] words, int index) {
        if (index == words.length) return true;

        String word = words[index];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                // Try placing horizontally
                if (canPlaceH(board, word, row, col)) {
                    boolean[] placed = placeH(board, word, row, col);
                    if (solve(board, words, index + 1)) return true;
                    unplaceH(board, placed, row, col);
                }

                // Try placing vertically
                if (canPlaceV(board, word, row, col)) {
                    boolean[] placed = placeV(board, word, row, col);
                    if (solve(board, words, index + 1)) return true;
                    unplaceV(board, placed, row, col);
                }
            }
        }
        return false;
    }

    // --- Horizontal Placement Helpers ---
    private static boolean canPlaceH(char[][] b, String w, int r, int c) {
        if (c + w.length() > 10) return false;
        for (int i = 0; i < w.length(); i++) {
            if (b[r][c + i] != '-' && b[r][c + i] != w.charAt(i)) return false;
        }
        return true;
    }

    private static boolean[] placeH(char[][] b, String w, int r, int c) {
        boolean[] changed = new boolean[w.length()];
        for (int i = 0; i < w.length(); i++) {
            if (b[r][c + i] == '-') {
                b[r][c + i] = w.charAt(i);
                changed[i] = true;
            }
        }
        return changed;
    }

    private static void unplaceH(char[][] b, boolean[] changed, int r, int c) {
        for (int i = 0; i < changed.length; i++) {
            if (changed[i]) b[r][c + i] = '-';
        }
    }

    // --- Vertical Placement Helpers ---
    private static boolean canPlaceV(char[][] b, String w, int r, int c) {
        if (r + w.length() > 10) return false;
        for (int i = 0; i < w.length(); i++) {
            if (b[r + i][c] != '-' && b[r + i][c] != w.charAt(i)) return false;
        }
        return true;
    }

    private static boolean[] placeV(char[][] b, String w, int r, int c) {
        boolean[] changed = new boolean[w.length()];
        for (int i = 0; i < w.length(); i++) {
            if (b[r + i][c] == '-') {
                b[r + i][c] = w.charAt(i);
                changed[i] = true;
            }
        }
        return changed;
    }

    private static void unplaceV(char[][] b, boolean[] changed, int r, int c) {
        for (int i = 0; i < changed.length; i++) {
            if (changed[i]) b[r + i][c] = '-';

    }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<String> crossword = IntStream.range(0, 10).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        String words = bufferedReader.readLine();

        List<String> result = Result.crosswordPuzzle(crossword, words);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
