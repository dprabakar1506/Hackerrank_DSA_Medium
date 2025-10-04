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
     * Complete the 'countLuck' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY matrix
     *  2. INTEGER k
     */

    public static String countLuck(List<String> matrix, int k) {
    // Write your code here
    int n = matrix.size();
        int m = matrix.get(0).length();

        // Finding starting position 'M'
        int startRow = -1, startCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).charAt(j) == 'M') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
            if (startRow != -1) break;
        }

        // Using a queue for BFS, storing {row, col, guesses_made}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});

        // Keeping a track of visited cells to avoid cycles
        boolean[][] visited = new boolean[n][m];
        visited[startRow][startCol] = true;

        int[] dr = {-1, 1, 0, 0}; // Row changes: Up, Down,
        int[] dc = {0, 0, -1, 1}; // Col changes: Left, Right

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int guesses = current[2];

            // If destination '*' is reached
            if (matrix.get(r).charAt(c) == '*') {
                return (guesses == k) ? "Impressed" : "Oops!";
            }

            int possibleMoves = 0;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && matrix.get(nr).charAt(nc) != 'X' && !visited[nr][nc]) {
                    possibleMoves++;
                }
            }

            // If it's a decision point (more than one valid path, excluding the one just taken)
            // For the starting point, if it has more than one path, it's a decision point.
            // For other points, if it has more than two paths (including the one just taken), it's a decision point.

            // If current cell is not 'M', and has more than one valid next move, it's a decision point.
            // If current cell is 'M', and has more than one valid next move, it's also a decision point.
            int nextGuesses = guesses;
            if (possibleMoves > 1 || (matrix.get(r).charAt(c) == 'M' && possibleMoves > 1)) {
                nextGuesses++;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && matrix.get(nr).charAt(nc) != 'X' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, nextGuesses});
                }
            }
        }

        // If destination is not reachable
        return "Oops!";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<String> matrix = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                String result = Result.countLuck(matrix, k);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
