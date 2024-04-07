package week4_binary_search_trees;

import java.util.*;
import java.io.*;

public class is_bst {
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
                return true;
            return isBSTOrNot(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean isBSTOrNot(int index, int min, int max) {
            if (index == -1)
                return true;

            if (tree[index].key < min || tree[index].key > max)
                return false;

            return isBSTOrNot(tree[index].left, min, tree[index].key - 1) &&
                    isBSTOrNot(tree[index].right, tree[index].key + 1, max);
        }
    }

    public static void main(String[] args) throws IOException {
        new is_bst().run();
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
