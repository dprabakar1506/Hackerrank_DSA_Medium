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
     * Complete the 'redJohn' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */

    public static int redJohn(int n) {
    // Write your code here
    // Step 1: Calculate M, the number of ways to tile a 4xn wall
        long M = countTilings(n);
        
        // Step 2: Calculate P, the number of primes less than or equal to M
        int P = countPrimes((int) M);
        
        return P;
    }

    /**
     * Calculates the number of ways to tile a 4xn wall.
     */
    private static long countTilings(int n) {
        if (n <= 0) return 0;
        // The number of tilings can be large, so use long to avoid overflow for M
        long[] ways = new long[n + 1];

        // Base cases
        // For n=1, 2, 3, only 1 way (all vertical 1x4 tiles or all horizontal 4x1 tiles, 
        // the description seems to imply 4x1 and 1x4, so it's a 4 row, n column grid)
        // Re-reading problem, it's 4*n wall with 4*1 and 1*4 bricks
        // This means a vertical brick is 4x1 (width 1), horizontal is 1x4 (width 4).
        // The examples confirm this: n=1 -> 1 way; n=4 -> 2 ways; n=5 -> 3 ways.

        if (n >= 1) ways[1] = 1;
        if (n >= 2) ways[2] = 1;
        if (n >= 3) ways[3] = 1;
        if (n >= 4) ways[4] = 2; // All 4 vertical, or all 1 horizontal

        for (int i = 5; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 4];
        }

        return ways[n];
    }

    /**
     * Counts the number of prime numbers up to m using the Sieve of Eratosthenes.
     */
    private static int countPrimes(int m) {
        if (m < 2) return 0;

        boolean[] isPrime = new boolean[m + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int p = 2; p * p <= m; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= m; i += p)
                    isPrime[i] = false;
            }
        }

        int count = 0;
        for (int i = 2; i <= m; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int result = Result.redJohn(n);

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
