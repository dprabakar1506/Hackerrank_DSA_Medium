import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    // Represents a cell on the chessboard
    static class Cell {
        int row, col, moves;
        public Cell(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }
    }

    /*
     * Complete the 'knightlOnAChessboard' function below.
     *
     * The function returns a 2D list where result[a-1][b-1] = minimum moves
     * needed for a knight with step (a, b) to reach (n-1, n-1) from (0, 0),
     * or -1 if it's not possible.
     */
    public static List<List<Integer>> knightlOnAChessboard(int n) {
        int[][] result = new int[n - 1][n - 1]; // Store results temporarily

        // Try all possible (a, b) pairs
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < n; b++) {
                result[a - 1][b - 1] = solveForKnightL(n, a, b);
            }
        }

        // Convert 2D int array -> List<List<Integer>> before returning
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n - 1; j++) {
                row.add(result[i][j]);
            }
            output.add(row);
        }
        return output;
    }

    // BFS to find minimum moves for a given (a, b)
    private static int solveForKnightL(int n, int a, int b) {
        Queue<Cell> queue = new LinkedList<>();
        int[][] minMoves = new int[n][n];

        // Mark all cells as unvisited (-1)
        for (int i = 0; i < n; i++) {
            Arrays.fill(minMoves[i], -1);
        }

        // Start BFS from (0, 0)
        queue.offer(new Cell(0, 0, 0));
        minMoves[0][0] = 0;

        // Possible 8 moves for knight (a, b)
        int[] dr = {-a, -a, a, a, -b, -b, b, b};
        int[] dc = {-b, b, -b, b, -a, a, -a, a};

        // BFS traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // If we reached the destination, return number of moves
            if (current.row == n - 1 && current.col == n - 1) {
                return current.moves;
            }

            for (int i = 0; i < 8; i++) {
                int nextRow = current.row + dr[i];
                int nextCol = current.col + dc[i];

                // Check if within bounds and not visited
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n
                        && minMoves[nextRow][nextCol] == -1) {
                    minMoves[nextRow][nextCol] = current.moves + 1;
                    queue.offer(new Cell(nextRow, nextCol, current.moves + 1));
                }
            }
        }
        return -1; // Unreachable
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        // Call the function from Result class
        List<List<Integer>> result = Result.knightlOnAChessboard(n);

        // Output each row of the matrix
        result.stream()
            .map(r -> r.stream().map(Object::toString).collect(joining(" ")))
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
