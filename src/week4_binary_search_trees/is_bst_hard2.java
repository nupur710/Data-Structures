//package week4_binary_search_trees;
//
//import java.util.*;
//import java.io.*;
//
////wrong
//public class is_bst_hard2 {
//    class FastScanner {
//        StringTokenizer tok = new StringTokenizer("");
//        BufferedReader in;
//
//        FastScanner() {
//            in = new BufferedReader(new InputStreamReader(System.in));
//        }
//
//        String next() throws IOException {
//            while (!tok.hasMoreElements())
//                tok = new StringTokenizer(in.readLine());
//            return tok.nextToken();
//        }
//        int nextInt() throws IOException {
//            return Integer.parseInt(next());
//        }
//    }
//
//    public class IsBST {
//        class Node {
//            int key;
//            int left;
//            int right;
//
//            Node(int key, int left, int right) {
//                this.left = left;
//                this.right = right;
//                this.key = key;
//            }
//        }
//
//        int nodes;
//        Node[] tree;
//
//        void read() throws IOException {
//            FastScanner in = new FastScanner();
//            nodes = in.nextInt();
//            tree = new Node[nodes];
//            for (int i = 0; i < nodes; i++) {
//                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
//            }
//        }
//
//        boolean isBinarySearchTree() {
//            if(nodes == 0) return true;
//            return isBinaryUtils(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        }
//        boolean solve() {
//            return isBinarySearchTree();
//        }
//
//
//        boolean isBinaryUtils(int nodeIndex, int min, int max) {
//            if (nodeIndex == -1) {
//                return true;
//            }
//
//            Node node = tree[nodeIndex];
//            if (node.key < min || node.key >= max) {
//                return false;
//            }
//
//            if (node.right == -1) {
//                // If there's no right child, we don't pass max to the right subtree
//                return isBinaryUtils(node.left, min, node.key);
//            }
//
//            return isBinaryUtils(node.left, min, node.key) &&
//                    isBinaryUtils(node.right, node.key, max);
//        }
//    }
//
//    static public void main(String[] args) throws IOException {
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
//                    new is_bst_hard().run();
//                } catch (IOException e) {
//                }
//            }
//        }, "1", 1 << 26).start();
//    }
//    public void run() throws IOException {
//        IsBST tree = new IsBST();
//        tree.read();
//        if (tree.solve()) {
//            System.out.println("CORRECT");
//        } else {
//            System.out.println("INCORRECT");
//        }
//    }
//}
