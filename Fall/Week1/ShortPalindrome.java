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
     * Complete the 'shortPalindrome' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int shortPalindrome(String s) {
    // Write your code here
   
      long MOD = 1000000000L+7;
        long[] cnt = new long[26];
        long[][] sumK = new long[26][26];
        long[][] delta = new long[26][26];
        long ans = 0;
        for(int i=0; i<s.length(); i++) {
            int c = s.charAt(i) - 'a';
            for(int j=0; j<26; j++) {
                ans = (ans + delta[c][j]) % MOD;
            }
            for(int j=0; j<26; j++) {
                delta[j][c] = (delta[j][c] + sumK[j][c]) % MOD;
                sumK[j][c] = (sumK[j][c] + cnt[j]) % MOD;
            }
            
            cnt[c] += 1;
             }
             return (int)(ans % MOD);

    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = Result.shortPalindrome(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
