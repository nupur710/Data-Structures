package week4_binary_search_trees;

import java.util.*;
import java.io.*;

/**
 *
 * incorrect does not work for input
 * 3
 * 2 1 2
 * 1 -1 -1
 * 2 -1 -1
 *
 */
public class is_bst_hard {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            if (nodes == 0)
                return true; // Empty tree is a valid BST
            return isBSTUtil(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean isBSTUtil(int index, int min, int max) {
            if (index == -1)
                return true; // Empty subtree is a valid BST

            if (tree[index].key < min || tree[index].key > max)
                return false; // Current node violates the BST property

            // Check recursively for left and right subtrees
            return isBSTUtil(tree[index].left, min, tree[index].key - 1) &&
                    isBSTUtil(tree[index].right, tree[index].key + 1, max);
        }
    }

    public static void main(String[] args) throws IOException {
        new is_bst_hard().run();
    }

    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
