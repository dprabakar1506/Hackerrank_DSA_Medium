import java.io.*;
import java.util.*;

public class Solution {
    static final int MOD = 1_000_000_007, MAX = 100005;
    static long[] fac = new long[MAX], ifac = new long[MAX];

    // Precompute factorials and inverse factorials
    static void precompute() {
        fac[0] = ifac[0] = 1;
        for (int i = 1; i < MAX; i++) {
            fac[i] = fac[i - 1] * i % MOD;
            ifac[i] = modInv(fac[i]);
        }
    }

    // Modular inverse using Fermat's Little Theorem
    static long modInv(long x) {
        return pow(x, MOD - 2);
    }

    static long pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            y >>= 1;
        }
        return res;
    }

    // Calculate result for one query
    static long calc(int[] freq) {
        int half = 0, odd = 0;
        long denom = 1;
        for (int f : freq) {
            half += f / 2;
            denom = denom * ifac[f / 2] % MOD;
            if (f % 2 == 1) odd++;
        }
        long res = fac[half] * denom % MOD;
        return odd == 0 ? res : res * odd % MOD;
    }

    public static void main(String[] args) throws Exception {
        precompute();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = (" " + br.readLine()).toCharArray(); // 1-based
        int q = Integer.parseInt(br.readLine());

        int B = (int) Math.sqrt(s.length);
        int[][] queries = new int[q][3];
        for (int i = 0; i < q; i++) {
            String[] parts = br.readLine().split(" ");
            queries[i][0] = Integer.parseInt(parts[0]);
            queries[i][1] = Integer.parseInt(parts[1]);
            queries[i][2] = i;
        }

        // Sort queries for MO's Algorithm
        Arrays.sort(queries, (a, b) -> {
            int blockA = a[0] / B, blockB = b[0] / B;
            return blockA != blockB ? blockA - blockB : a[1] - b[1];
        });

        long[] res = new long[q];
        int[] freq = new int[26];
        int l = 1, r = 0;
        for (int[] query : queries) {
            int ql = query[0], qr = query[1], idx = query[2];
            while (r < qr) freq[s[++r] - 'a']++;
            while (l > ql) freq[s[--l] - 'a']++;
            while (r > qr) freq[s[r--] - 'a']--;
            while (l < ql) freq[s[l++] - 'a']--;
            res[idx] = calc(freq);
        }

        for (long val : res) System.out.println(val);
    }
}
