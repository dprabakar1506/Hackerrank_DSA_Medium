import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Q = Integer.parseInt(scanner.nextLine()); // Number of operations

        StringBuilder text = new StringBuilder(); // Current text content
        Stack<String> history = new Stack<>();    // Stack to store previous states for undo

        for (int i = 0; i < Q; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int command = Integer.parseInt(parts[0]);

            switch (command) {
                case 1:
                    // Append operation
                    history.push(text.toString());
                    text.append(parts[1]);
                    break;

                case 2:
                    // Delete last k characters
                    history.push(text.toString());
                    int k = Integer.parseInt(parts[1]);
                    text.delete(text.length() - k, text.length());
                    break;

                case 3:
                    // Print the kth character (1-based index)
                    int idx = Integer.parseInt(parts[1]);
                    System.out.println(text.charAt(idx - 1));
                    break;

                case 4:
                    // Undo last append/delete
                    if (!history.isEmpty()) {
                        text = new StringBuilder(history.pop());
                    }
                    break;
            }
        }

        scanner.close();
    }
}
