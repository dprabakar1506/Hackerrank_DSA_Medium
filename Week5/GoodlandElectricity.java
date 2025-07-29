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
     * Complete the 'pylons' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int pylons(int k, List<Integer> arr) {
    // Write your code here
    int n = arr.size();
        int count = 0;   // Number of pylons placed
        int i = 0;       // Current position to be covered

        while (i < n) {
            // place the pylon as far right as possible within range [i - k + 1, i + k - 1]
            int pylon = Math.min(i + k - 1, n - 1); // Max right bound

            // Move left until we find a valid pylon location
            while (pylon >= i - k + 1 && (pylon < 0 || arr.get(pylon) == 0)) {
                pylon--;
            }

            // If no pylon can cover position i
            if (pylon < i - k + 1 || pylon < 0) return -1;

            count++;        // Place pylon
            i = pylon + k;  // Move to the next uncovered position
        }

        return count;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.pylons(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
