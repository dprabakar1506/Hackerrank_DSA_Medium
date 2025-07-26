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
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static int commonChild(String s1, String s2) {
    // Write your code here
    int m = s1.length();
    int n = s2.length();

    // Create a 2D array to store lengths of longest common subsequence
    int[][] dp = new int[m + 1][n + 1];

    // Fill the dp table using bottom-up dynamic programming
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            // If characters match, extend the subsequence
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                // Else, take the maximum from previous results
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // The bottom-right cell contains the answer
    return dp[m][n];

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = Result.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
