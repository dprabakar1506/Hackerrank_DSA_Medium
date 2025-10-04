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
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
    // Write your code here
    // Create adjacency list representation
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }
    
    // Build the graph
    for (List<Integer> edge : edges) {
        int u = edge.get(0);
        int v = edge.get(1);
        adj.get(u).add(v);
        adj.get(v).add(u); // Since it's an undirected graph
    }
    
    // Initialize distances array with -1 (unreachable)
    List<Integer> distances = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
        distances.add(-1);
    }
    
    // BFS queue
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(s);
    distances.set(s, 0);
    
    // BFS
    while (!queue.isEmpty()) {
        int current = queue.poll();
        int currentDist = distances.get(current);
        
        // Process all neighbors
        for (int neighbor : adj.get(current)) {
            if (distances.get(neighbor) == -1) { // Not visited
                distances.set(neighbor, currentDist + 6); // Each edge has weight 6
                queue.offer(neighbor);
            }
        }
    }
    
    // Prepare result excluding the start node and node 0
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
        if (i != s) {
            result.add(distances.get(i));
        }
    }
    
    return result;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = Result.bfs(n, m, edges, s);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
