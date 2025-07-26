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
     * Complete the 'highestValuePalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER k
     */

    public static String highestValuePalindrome(String s, int n, int k) {
    char[] a = s.toCharArray();         // Convert string to char array
    boolean[] diff = new boolean[n];    // Track positions where digits differ
    int cnt = 0;

    // First pass: make it a palindrome using minimal changes
    for (int i = 0; i < n / 2; i++) {
        if (a[i] != a[n - 1 - i]) {
            // Set both to the larger digit
            a[i] = a[n - 1 - i] = (char) Math.max(a[i], a[n - 1 - i]);
            diff[i] = true;
            cnt++;
        }
    }

    // If more changes needed than allowed, return -1
    if (cnt > k) return "-1";
    int rem = k - cnt;

    // Second pass: maximize digits to '9' greedily
    for (int i = 0; i < n / 2 && rem > 0; i++) {
        if (a[i] != '9') {
            if (diff[i] && rem >= 1) {
                a[i] = a[n - 1 - i] = '9'; rem--;       // Already changed once
            } else if (!diff[i] && rem >= 2) {
                a[i] = a[n - 1 - i] = '9'; rem -= 2;     // Change both digits
            }
        }
    }

    // For odd-length string, try to set middle digit to '9'
    if (n % 2 == 1 && rem > 0) a[n / 2] = '9';

    return new String(a); // Return the final palindrome
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = Result.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
