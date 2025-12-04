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
     * Complete the 'hackerrankCity' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY A as parameter.
     */

    public static int hackerrankCity(List<Integer> A) {
    // Write your code here
    int n = A.size();
        long[] cluster_size = new long[n + 1];
        long[] total = new long[n + 1];
        long[] p = new long[n + 1];
        long[] line = new long[n + 1];
        long m = (long)1e9 + 7;

        cluster_size[0] = 1;
        p[0] = 0;
        total[0] = 0;
        line[0] = 0;

        for (int i = 1; i <= n; i++) {
            long a = A.get(i - 1);
            long k = cluster_size[i - 1];
            cluster_size[i] = (k * 4 + 2) % m;
            line[i] = (line[i - 1] * 2 + 3 * a) % m;

            p[i] = (p[i - 1]
                + p[i - 1] + k * (2 * a + line[i - 1]) % m
                + 2 * (k * (line[i - 1] + 3 * a) % m + p[i - 1]) % m
                + line[i - 1] * 2 + 3 * a % m
                ) % m;

            total[i] = (4 * total[i - 1] % m
                + 4 * (p[i - 1] + k * a) % m
                + 4 * (p[i - 1] + k * a * 2) % m
                + 2 * ((p[i - 1] * k) % m * 2 + ((k * k) % m) * a * 2) % m
                + 4 * ((p[i - 1] * k) % m * 2 + ((k * k) % m) * a * 3) % m
                + a) % m;
        }

        return (int)(total[n] % m);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int ACount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.hackerrankCity(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
