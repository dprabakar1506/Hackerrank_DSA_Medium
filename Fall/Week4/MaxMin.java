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
     * Complete the 'maxMin' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int maxMin(int k, List<Integer> arr) {
    // Write your code here
    // Step 1: Sort the array
        // We use Collections.sort for List<Integer>
        Collections.sort(arr);

        int minUnfairness = Integer.MAX_VALUE;

        // Step 2: Iterate through all windows of size k
        // The loop runs from the first element up to the point where a full window of k elements can be formed.
        // If the array size is n, the last possible starting index is n - k.
        for (int i = 0; i <= arr.size() - k; i++) {
            // The minimum element in the current window is at index i.
            int minVal = arr.get(i);
            // The maximum element in the current window is at index i + k - 1.
            int maxVal = arr.get(i + k - 1);
            
            // Step 3 & 4: Calculate the difference and update the minimum unfairness
            int currentUnfairness = maxVal - minVal;
            if (currentUnfairness < minUnfairness) {
                minUnfairness = currentUnfairness;
            }
        }

        return minUnfairness;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maxMin(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
