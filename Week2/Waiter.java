import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // Number of Plates
        int Q = sc.nextInt(); // Number of iterations

        Stack<Integer> A = new Stack<Integer>();
        // Read the plates and push into stack A (top of stack is last element read)
        for (int i = 0; i < N; i++) {
            A.push(sc.nextInt());
        }

        int prime = 2;
        for (int i = 0; i < Q; i++) {
            Stack<Integer> B = new Stack<Integer>(); // Stack for plates divisible by current prime
            Stack<Integer> nextA = new Stack<Integer>(); // Stack for plates not divisible
            // Process each plate in current A
            while (!A.empty()) {
                int number = A.pop();
                if (number % prime == 0) {
                    B.push(number);      //if number is divisible by prime then add the number to B
                } else {
                    nextA.push(number);         //if not then add the number to nextA
                }
            }

            printStack(B);
            A = nextA;
            prime = nextPrime(prime);
        }
        printStack(A);

        sc.close();
    }

    static void printStack(Stack<Integer> s) {
        while (!s.empty()) {
            System.out.println(s.pop());
        }
    }
//function for checking next number is prime or not
    static int nextPrime(int begin) {
        for (int i = begin + 1;; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }
   
// function for checking number is prime or not
    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {            
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
