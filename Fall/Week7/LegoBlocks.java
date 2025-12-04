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
     * Complete the 'legoBlocks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    public static int legoBlocks(int n, int m) {
    // Write your code here
     final long MOD = 1000000007;

    // Step 1: ways to build one row of width m
    long[] row = new long[m + 1];
    row[0] = 1;
    for (int i = 1; i <= m; i++) {
        row[i] = row[i - 1];
        if (i >= 2) row[i] = (row[i] + row[i - 2]) % MOD;
        if (i >= 3) row[i] = (row[i] + row[i - 3]) % MOD;
        if (i >= 4) row[i] = (row[i] + row[i - 4]) % MOD;
    }

    // Step 2: total ways = (row[m])^n
    long[] total = new long[m + 1];
    for (int i = 1; i <= m; i++) {
        total[i] = modPow(row[i], n, MOD);
    }

    // Step 3: valid ways using inclusion-exclusion
    long[] valid = new long[m + 1];
    valid[0] = 1;
    for (int i = 1; i <= m; i++) {
        long bad = 0;
        for (int j = 1; j < i; j++) {
            bad = (bad + (valid[j] * total[i - j]) % MOD) % MOD;
        }
        valid[i] = (total[i] - bad + MOD) % MOD;
    }

    return (int) valid[m];
}

// Fast exponentiation
private static long modPow(long base, long exp, long mod) {
    long result = 1;
    while (exp > 0) {
        if ((exp & 1) == 1) result = (result * base) % mod;
        base = (base * base) % mod;
        exp >>= 1;
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

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.legoBlocks(n, m);

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
