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


public class Solution {
    static int unboundedKnapsack(int k, List<Integer> arr) {
        int[] dp = new int[k + 1];  // dp[i] = max sum <= i
        
        for (int i = 0; i <= k; i++) {
            for (int val : arr) {
                if (val <= i) {
                    dp[i] = Math.max(dp[i], dp[i - val] + val);
                }
            }
        }
        
        return dp[k]; // best achievable sum not exceeding k
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(sc.nextInt());
            }
            
            System.out.println(unboundedKnapsack(k, arr));
        }
        sc.close();
    }
}
