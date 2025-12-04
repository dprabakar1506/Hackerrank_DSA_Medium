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
     * Complete the 'xorAndSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static int xorAndSum(String a, String b) {
    // Write your code here
    long mod = 1000000007;
        long max = 314159;

        int nA[] = new int[a.length()];
        int nB[] = new int[b.length()];
        setInverse(nA, a);
        setInverse(nB, b);

        long sum = 0;
        long pow = 1;
        long count = 0;
        int len = Math.max(nA.length, nB.length);

        for (int i = 0; i < max; i++) {
            if (i < nB.length)
                count += nB[i];
            long multiplier = (i < nA.length && nA[i] == 1) ? max - count + 1 : count;
            sum = (sum + pow * multiplier) % mod;
            pow = (pow << 1) % mod;
        }

        for (int i = 0; i < nB.length; ++i) {
            sum = (sum + pow * count) % mod;
            pow = (pow << 1) % mod;
            count -= nB[i];
        }

        return (int) sum;
    }

    private static void setInverse(int[] n, String s) {
        StringBuffer sb = new StringBuffer(s);
        sb.reverse();
        for (int i = 0; i < s.length(); i++) {
            n[i] = sb.charAt(i) - '0';
        }
    }
}
    


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        int result = Result.xorAndSum(a, b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
