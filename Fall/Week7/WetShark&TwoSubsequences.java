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
     * Complete the 'twoSubsequences' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY x
     *  2. INTEGER r
     *  3. INTEGER s
     */

public static final int MOD = 1000000007;
    public static int twoSubsequences(List<Integer> x, int r, int s) {
    // Write your code here
    int M = x.size();

        // Invalid if (r + s) is odd or s >= r
        if ((r + s) % 2 != 0 || s >= r) {
            return 0;
        }

        int P = (r + s) / 2;
        int Q = (r - s) / 2;

        long[][] nways = new long[M + 1][P + 1];
        nways[0][0] = 1;

        for (int idx = 1; idx <= M; idx++) {
            int val = x.get(idx - 1);

            for (int j = M; j >= 1; j--) {
                for (int k = P; k >= val; k--) {
                    nways[j][k] += nways[j - 1][k - val];
                    if (nways[j][k] >= MOD)
                        nways[j][k] -= MOD;
                }
            }
        }

        long total = 0;
        for (int i = 0; i <= M; i++) {
            total = (total + (nways[i][P] * nways[i][Q]) % MOD) % MOD;
        }

        return (int) total;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int r = Integer.parseInt(firstMultipleInput[1]);

        int s = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.twoSubsequences(x, r, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
