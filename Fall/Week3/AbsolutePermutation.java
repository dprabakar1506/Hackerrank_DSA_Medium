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
     * Complete the 'absolutePermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */

    public static List<Integer> absolutePermutation(int n, int k) {
    // Write your code here
    List<Integer> result = new ArrayList<>();
        boolean[] used = new boolean[n + 1]; // To keep track of used numbers

        // If k is 0, the identity permutation (1, 2, ..., n) is the solution.
        if (k == 0) {
            for (int i = 1; i <= n; i++) {
                result.add(i);
            }
            return result;
        }

        // If n is not divisible by 2k, no absolute permutation exists.
        // This is a key condition for the existence of such a permutation.
        if (n % (2 * k) != 0) {
            result.add(-1);
            return result;
        }

        // Construct the permutation
        for (int i = 1; i <= n; i++) {
            int candidate1 = i + k;
            int candidate2 = i - k;

            // Prioritize the smaller candidate for lexicographical order
            if (candidate2 > 0 && candidate2 <= n && !used[candidate2]) {
                result.add(candidate2);
                used[candidate2] = true;
            } else if (candidate1 > 0 && candidate1 <= n && !used[candidate1]) {
                result.add(candidate1);
                used[candidate1] = true;
            } else {
                // No valid candidate found for the current position, so no absolute permutation exists.
                result.clear();
                result.add(-1);
                return result;
            }
        }
        return result;
    

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> result = Result.absolutePermutation(n, k);

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
