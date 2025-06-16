import java.io.*;

import java.util.*;

public class AVLTree {

    // Node class
    static class Node {
        int val;
        int ht;
        Node left, right;
    }

    // Get height
    static int height(Node node) {
        return (node == null) ? -1 : node.ht;
    }

    // Max height of children
    static int heightMax(Node root) {
        return Math.max(height(root.left), height(root.right));
    }

    // Balance factor
    static int balanceFactor(Node root) {
        return height(root.left) - height(root.right);
    }

    // Right rotation
    static Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;

        x.ht = heightMax(x) + 1;
        y.ht = heightMax(y) + 1;

        return y;
    }

    // Left rotation
    static Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        x.ht = heightMax(x) + 1;
        y.ht = heightMax(y) + 1;

        return y;
    }

    // Insert value into AVL tree
    static Node insert(Node root, int val) {
        if (root == null) {
            Node node = new Node();
            node.val = val;
            node.ht = 0;
            return node;
        }

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        root.ht = heightMax(root) + 1;

        int balance = balanceFactor(root);

        // Left heavy
        if (balance > 1) {
            if (val < root.left.val) {
                return rotateRight(root); // LL
            } else {
                root.left = rotateLeft(root.left); // LR
                return rotateRight(root);
            }
        }

        // Right heavy
        if (balance < -1) {
            if (val > root.right.val) {
                return rotateLeft(root); // RR
            } else {
                root.right = rotateRight(root.right); // RL
                return rotateLeft(root);
            }
        }

        return root;
    }

    // Inorder traversal
    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + "(BF=" + balanceFactor(node) + ") ");
        inorder(node.right);
    }

    // Preorder traversal
    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.val + "(BF=" + balanceFactor(node) + ") ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Node root = null;

        // Read number of values (not needed)
        int count = Integer.parseInt(in.nextLine());

        // Read remaining input values (can be in multiple lines)
        List<Integer> values = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                values.add(Integer.parseInt(p));
            }
        }

        // Insert into AVL tree
        for (int val : values) {
            root = insert(root, val);
        }

        inorder(root);
        System.out.println();
        preorder(root);
    }
}


