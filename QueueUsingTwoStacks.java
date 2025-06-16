import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create instance of custom queue
        MyQueue<Integer> queue = new MyQueue<Integer>();

        // Read number of operations
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                // Enqueue operation
                int x = sc.nextInt();
                queue.enqueue(x);
            } else if (type == 2) {
                // Dequeue operation
                queue.dequeue();
            } else if (type == 3) {
                // Print front of queue
                System.out.println(queue.peek());
            }
        }

        sc.close();
    }
}

// Custom queue implementation using two stacks
class MyQueue<T> {
    Stack<T> leftStack = new Stack<T>();  // Used for enqueueing
    Stack<T> rightStack = new Stack<T>(); // Used for dequeuing and peeking

    // Adds item to the end of the queue
    void enqueue(T item) {
        leftStack.push(item);
    }

    // Removes and returns the front item of the queue
    T dequeue() {
        transferIfNeeded();  // Ensure rightStack has elements
        return rightStack.pop();
    }

    // Returns the front item without removing it
    T peek() {
        transferIfNeeded();  // Ensure rightStack has elements
        return rightStack.peek();
    }

    // Transfers elements from leftStack to rightStack if rightStack is empty
    void transferIfNeeded() {
        if (rightStack.empty()) {
            while (!leftStack.empty()) {
                rightStack.push(leftStack.pop());
            }
        }
    }
}
