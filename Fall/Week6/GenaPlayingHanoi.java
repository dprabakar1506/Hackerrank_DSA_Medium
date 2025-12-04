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
     * Complete the 'hanoi' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY posts as parameter.
     */

    public static int hanoi(List<Integer> posts) {
    int ndisc = posts.size();
        int start = 0;

        // Encode initial state as a bitmask: 2 bits per disk for rod index
        for (int h = 1; h <= ndisc; ++h) {
            int rod = posts.get(h - 1) - 1; // Convert to 0-based index
            start = move(start, rod, h);
        }

        // Run BFS to find minimum moves
        return (int) solve(ndisc, start);
    }

    // Encode the position of a single disk in the state
    private static int move(int state, int rod, int disc) {
        return (state & ~(3 << 2 * (disc - 1))) | (rod << 2 * (disc - 1));
    }

    // Get the smallest disk on a given rod
    private static int getDisc(int ndisc, int state, int rod) {
        int disc = ndisc + 1;
        for (int h = ndisc; h >= 1; --h) {
            int r = (state >> (2 * (h - 1))) & 3;
            if (r == rod) disc = h;
        }
        return disc;
    }

    // BFS to compute minimum moves to reach all disks on rod 0 (encoded as 0)
    private static long solve(int ndisc, int start) {
        final int win = 0; // goal state: all disks on rod 0
        if (start == win) return 0;

        LinkedList<Integer> bfs = new LinkedList<>();
        bfs.addLast(start);

        // Track depth (number of moves) for each state
        List<Integer> depth = new ArrayList<>(Collections.nCopies(1 << (2 * ndisc), null));
        depth.set(start, 0);

        while (!bfs.isEmpty()) {
            int par = bfs.removeFirst();

            // Find the smallest disk on each rod
            int[] d = new int[4];
            for (int rod = 0; rod < 4; ++rod) d[rod] = getDisc(ndisc, par, rod);

            // Try moving each smallest disk to every valid rod
            for (int from = 0; from < 4; ++from) {
                if (d[from] == ndisc + 1) continue; // no disk to move
                for (int to = 0; to < 4; ++to) {
                    if (d[to] > d[from]) { // can place only on larger disk or empty
                        int ch = move(par, to, d[from]);
                        if (ch == win) return 1 + depth.get(par);
                        if (depth.get(ch) == null) {
                            depth.set(ch, 1 + depth.get(par));
                            bfs.addLast(ch);
                        }
                    }
                }
            }
        }
        return -1; // should never reach here
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> loc = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int res = Result.hanoi(loc);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
