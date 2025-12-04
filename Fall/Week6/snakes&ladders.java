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
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY ladders
     *  2. 2D_INTEGER_ARRAY snakes
     */

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
    // Write your code here
    // Creating a board mapping from 1 to 100
    int[] board = new int[101];
    for (int i = 1; i <= 100; i++) board[i] = i;

    // Map ladders
    for (List<Integer> ladder : ladders) {
        int start = ladder.get(0);
        int end = ladder.get(1);
        board[start] = end;
    }

    // Map snakes
    for (List<Integer> snake : snakes) {
        int start = snake.get(0);
        int end = snake.get(1);
        board[start] = end;
    }

    // BFS queue: each element is {position, moves so far}
    Queue<int[]> queue = new LinkedList<>();
    boolean[] visited = new boolean[101];

    queue.offer(new int[]{1, 0}); // start at square 1, 0 moves
    visited[1] = true;

    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int pos = curr[0];
        int moves = curr[1];

        // Checking if we reached the last square
        if (pos == 100) return moves;

        // Checking all dice rolls from 1 to 6
        for (int dice = 1; dice <= 6; dice++) {
            int nextPos = pos + dice;
            if (nextPos <= 100 && !visited[board[nextPos]]) {
                visited[board[nextPos]] = true;
                queue.offer(new int[]{board[nextPos], moves + 1});
            }
        }
    }

    // If square 100 is unreachable
    return -1;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> ladders = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        ladders.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int m = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> snakes = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        snakes.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.quickestWayUp(ladders, snakes);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
