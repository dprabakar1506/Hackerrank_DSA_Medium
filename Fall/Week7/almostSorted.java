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
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
     int n = arr.size();
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);

        // Find first index where arr differs from sorted
        int left = 0;
        while (left < n && arr.get(left).equals(sorted.get(left))) {
            left++;
        }

        // Already sorted
        if (left == n) {
            System.out.println("yes");
            return;
        }

        // Find last index where arr differs from sorted
        int right = n - 1;
        while (right >= 0 && arr.get(right).equals(sorted.get(right))) {
            right--;
        }

        // Try swapping left and right
        Collections.swap(arr, left, right);
        if (arr.equals(sorted)) {
            System.out.println("yes");
            System.out.println("swap " + (left + 1) + " " + (right + 1));
            return;
        }

        // Undo swap and try reversing the segment
        Collections.swap(arr, left, right);
        reverse(arr, left, right);
        if (arr.equals(sorted)) {
            System.out.println("yes");
            System.out.println("reverse " + (left + 1) + " " + (right + 1));
            return;
        }

        // If neither works, not possible
        System.out.println("no");
    }

    private static void reverse(List<Integer> arr, int l, int r) {
        while (l < r) {
            Collections.swap(arr, l, r);
            l++;
            r--;
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
