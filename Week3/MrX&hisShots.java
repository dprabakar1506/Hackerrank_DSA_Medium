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
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY shots
     *  2. 2D_INTEGER_ARRAY players
     */

    public static int solve(List<List<Integer>> shots, List<List<Integer>> players) {
    // Write your code here
    int n = shots.size();

    int[] starts = new int[n];
    int[] ends = new int[n];

    for (int i = 0; i < n; i++) {
        starts[i] = shots.get(i).get(0);
        ends[i] = shots.get(i).get(1);
    }

    Arrays.sort(starts);
    Arrays.sort(ends);

    int totalStrength = 0;

    for (List<Integer> player : players) {
        int c = player.get(0); // start
        int d = player.get(1); // end

        // Shots with start <= d
        int shotsStartingBeforeD = upperBound(starts, d);

        // Shots with end < c
        int shotsEndingBeforeC = lowerBound(ends, c);

        int overlapCount = shotsStartingBeforeD - shotsEndingBeforeC;
        totalStrength += overlapCount;
    }

    return totalStrength;
}

// lower_bound: first index where arr[i] >= val
private static int lowerBound(int[] arr, int val) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = (left + right) / 2;
        if (arr[mid] < val)
            left = mid + 1;
        else
            right = mid;
    }
    return left;
}

// upper_bound: first index where arr[i] > val
private static int upperBound(int[] arr, int val) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = (left + right) / 2;
        if (arr[mid] <= val)
            left = mid + 1;
        else
            right = mid;
    }
    return left;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> shots = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                shots.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> players = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                players.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.solve(shots, players);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
