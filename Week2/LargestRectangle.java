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
public static long largestRectangle(List<Integer> h) {
    Stack<Integer> stack = new Stack<>();
    long maxArea = 0;
    int n = h.size();

    // Iterate through all bars
    for (int i = 0; i <= n; i++) {
        // Get current height or 0 if beyond last index
        int currentHeight = (i == n) ? 0 : h.get(i);

        // Maintain increasing stack
        while (!stack.isEmpty() && currentHeight < h.get(stack.peek())) {
            int height = h.get(stack.pop());
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            long area = (long) height * width;
            maxArea = Math.max(maxArea, area);
        }

        stack.push(i);
    }

    return maxArea;
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
