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
     * Complete the 'cutTheTree' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY data
     *  2. 2D_INTEGER_ARRAY edges
     */

    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
    // Write your code here

    int n = data.size();
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> e : edges) {
            int u = e.get(0) - 1;
            int v = e.get(1) - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        long totalSum = 0;
        for (int val : data) totalSum += val;

        long[] subtreeSum = new long[n];
        boolean[] visited = new boolean[n];

        // DFS to calculate subtree sums
        dfs(0, adj, data, visited, subtreeSum);

        long minDiff = Long.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            long sum1 = subtreeSum[i];
            long sum2 = totalSum - sum1;
            long diff = Math.abs(sum1 - sum2);
            minDiff = Math.min(minDiff, diff);
        }

        return (int) minDiff;
    }

    private static long dfs(int node, List<List<Integer>> adj, List<Integer> data,
                            boolean[] visited, long[] subtreeSum) {
        visited[node] = true;
        long sum = data.get(node);
        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                sum += dfs(nei, adj, data, visited, subtreeSum);
            }
        }
        subtreeSum[node] = sum;
        return sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
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

        int result = Result.cutTheTree(data, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
