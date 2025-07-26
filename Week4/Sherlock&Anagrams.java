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
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
    // Write your code here
    // Map to store the number of times each sorted substring appears
    Map<String, Integer> map = new HashMap<>();
    int n = s.length();

    // Looping through all possible substrings
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j <= n; j++) {
            // Extracting substring from index i to j-1
            String sub = s.substring(i, j);

            // Converting to char array and sorting it to get a signature
            
            char[] chars = sub.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Counting the number of times each signature appears
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
    }

    int count = 0;

    // For each signature, if it appears more than once,
    // number of anagram pairs can be formed using nC2 = n*(n-1)/2
    for (int freq : map.values()) {
        if (freq > 1) {
            count += freq * (freq - 1) / 2;
        }
    }

    // Return the total count of anagrammatic pairs
    return count;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

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
