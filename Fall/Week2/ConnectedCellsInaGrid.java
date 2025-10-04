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
     * Complete the 'connectedCell' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int connectedCell(List<List<Integer>> matrix) {
    // Write your code here
    int maxRegionSize = 0;
        int rows = matrix.size();
        if (rows == 0) {
            return 0;
        }
        int cols = matrix.get(0).size();

        // Create a visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix.get(i).get(j) == 1 && !visited[i][j]) {
                    // Start a DFS from this unvisited '1' cell
                    int currentRegionSize = dfs(matrix, visited, i, j, rows, cols);
                    maxRegionSize = Math.max(maxRegionSize, currentRegionSize);
                }
            }
        }
        return maxRegionSize;
    }

    private static int dfs(List<List<Integer>> matrix, boolean[][] visited, int r, int c, int rows, int cols) {
        // Base cases for DFS
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] || matrix.get(r).get(c) == 0) {
            return 0;
        }

        // Mark the current cell as visited
        visited[r][c] = true;
        int currentSize = 1; // Count the current cell

        // Explore all 8 neighbors (including diagonals)
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) { // Skip the current cell itself
                    continue;
                }
                currentSize += dfs(matrix, visited, r + dr, c + dc, rows, cols);
            }
        }
        return currentSize;
    }

    }



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
