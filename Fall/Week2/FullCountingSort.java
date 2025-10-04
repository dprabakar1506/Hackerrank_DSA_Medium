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
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) {
    // Write your code here
    int n = arr.size();
        
        // Finding the highest index value in the input to create adequate buckets
        int maxIndex = 0;
        for (List<String> element : arr) {
            maxIndex = Math.max(maxIndex, Integer.parseInt(element.get(0)));
        }

        // Creating buckets for each possible index value
        List<List<String>> buckets = new ArrayList<>(Collections.nCopies(maxIndex + 1, null));
        for (int i = 0; i <= maxIndex; i++) {
            buckets.set(i, new ArrayList<>()); // Initialize each bucket
        }

        // Filling the buckets while replacing first half of strings with "-"
        for (int i = 0; i < n; i++) {
            int index = Integer.parseInt(arr.get(i).get(0));
            String value = (i < n / 2) ? "-" : arr.get(i).get(1);
            buckets.get(index).add(value);
        }

        // Collecting the result from all buckets in order and print
        StringBuilder result = new StringBuilder();
        for (List<String> bucket : buckets) {
            for (String str : bucket) {
                result.append(str).append(" ");
            }
        }
        
        // Printing the final result
        System.out.println(result.toString().trim());
    }

    }



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.countSort(arr);

        bufferedReader.close();
    }
}
