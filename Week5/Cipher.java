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
     * Complete the 'cipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. STRING s
     */

    public static String cipher(int k, String s) {
    // Write your code here
    int n = s.length();
    int[] res = new int[n];
    int xor = 0; // running xor of last k-1 decoded bits

    for (int i = 0; i < n; i++) {
        int curBit = s.charAt(i) - '0';
        // Decrypted bit = current encrypted bit XOR xor of previous k-1 decrypted bits
        res[i] = xor ^ curBit;

        // Update xor with this new decrypted bit
        xor ^= res[i];

        // Remove the bit that goes out of the window of size k
        if (i >= k - 1) xor ^= res[i - (k - 1)];
    }

    // Build result from first n - k + 1 decrypted bits (actual message length)
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= n - k; i++) {
        sb.append(res[i]);
    }
    return sb.toString();

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

        String result = Result.cipher(k, s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
