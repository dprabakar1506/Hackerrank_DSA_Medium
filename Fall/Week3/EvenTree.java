import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

// HackerRank: Even Forest Problem
public class Solution {

    // Function to calculate the maximum number of edges that can be removed
    // so that all resulting subtrees have even numbers of nodes.
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        // Step 1: Build adjacency list to represent the undirected tree
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= t_nodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 2: Add all edges (bidirectional)
        for (int i = 0; i < t_edges; i++) {
            int u = t_from.get(i);
            int v = t_to.get(i);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Step 3: Use DFS to find subtrees and count removable edges
        int[] removableEdges = {0}; // using array to modify inside DFS
        dfs(1, -1, graph, removableEdges); // assuming node 1 is the root

        return removableEdges[0]; // number of edges that can be removed
    }

    // DFS helper function: returns size of subtree rooted at 'node'
    private static int dfs(int node, int parent, List<List<Integer>> graph, int[] removableEdges) {
        int subtreeSize = 1; // start with current node

        // Visit all connected nodes (children)
        for (int child : graph.get(node)) {
            if (child != parent) { // avoid going back to parent
                int childSubtreeSize = dfs(child, node, graph, removableEdges);

                // If child's subtree size is even, we can safely remove the edge
                if (childSubtreeSize % 2 == 0) {
                    removableEdges[0]++;
                } else {
                    // Otherwise, include its nodes in the current subtree
                    subtreeSize += childSubtreeSize;
                }
            }
        }
        return subtreeSize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        // Read first line: number of nodes and edges
        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int t_nodes = Integer.parseInt(tNodesEdges[0]);
        int t_edges = Integer.parseInt(tNodesEdges[1]);

        // Read all edges
        List<Integer> t_from = new ArrayList<>();
        List<Integer> t_to = new ArrayList<>();

        IntStream.range(0, t_edges).forEach(i -> {
            try {
                String[] edge = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                t_from.add(Integer.parseInt(edge[0]));
                t_to.add(Integer.parseInt(edge[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

       
        int res = evenForest(t_nodes, t_edges, t_from, t_to);

        // Write output
        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
