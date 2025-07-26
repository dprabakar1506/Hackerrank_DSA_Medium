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
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
    // Write your code here
    // Counting frequency of each character
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }

    // Counting no.of times each frequency appears
    Map<Integer, Integer> freqCount = new HashMap<>();
    for (int f : freq) {
        if (f > 0) {
            freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
        }
    }

    // If there's only one frequency, it's already valid
    if (freqCount.size() == 1) return "YES";

    // If there are more than 2 different frequencies, it's invalid
    if (freqCount.size() > 2) return "NO";

    // Extracting two frequencies and their counts
    List<Integer> keys = new ArrayList<>(freqCount.keySet());
    int f1 = keys.get(0), f2 = keys.get(1);
    int count1 = freqCount.get(f1), count2 = freqCount.get(f2);

    // Checking if one of the frequencies is 1 and appears once which an be removed
    if ((f1 == 1 && count1 == 1) || (f2 == 1 && count2 == 1)) return "YES";

    // Checking if difference between the frequencies is 1 and the higher one appears only once
    if ((Math.abs(f1 - f2) == 1) &&
        ((f1 > f2 && count1 == 1) || (f2 > f1 && count2 == 1))) return "YES";

    return "NO";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
