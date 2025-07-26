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
     * Complete the 'steadyGene' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING gene as parameter.
     */

    public static int steadyGene(String gene) {
    // Write your code here
    int n = gene.length();
    int target = n / 4;
    int[] count = new int[128]; // ASCII table size to directly index characters

    // Count frequency of each character in the gene
    for (char c : gene.toCharArray()) {
        count[c]++;
    }

    // If already balanced, return 0
    if (count['A'] <= target && count['C'] <= target && count['G'] <= target && count['T'] <= target) {
        return 0;
    }

    int minLen = n;
    int left = 0;

    // Try to shrink the window [left, right]
    for (int right = 0; right < n; right++) {
        count[gene.charAt(right)]--; // remove char from outside

        // While remaining string outside window is balanced
        while (count['A'] <= target && count['C'] <= target && count['G'] <= target && count['T'] <= target) {
            minLen = Math.min(minLen, right - left + 1); // update min length
            count[gene.charAt(left)]++; // put back left char
            left++; // move left side forward
        }
    }

    return minLen;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String gene = bufferedReader.readLine();

        int result = Result.steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
