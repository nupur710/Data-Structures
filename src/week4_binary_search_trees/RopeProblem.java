package week4_binary_search_trees;

import java.io.*;
import java.util.*;

//does not pass all test cases
class RopeProblem {
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

    class RopeNode {
        RopeNode left, right;
        String data;
        int weight; // Weight of the subtree rooted at this node

        RopeNode(String s) {
            this.data = s;
            this.weight = s.length();
            this.left = null;
            this.right = null;
        }

        RopeNode(RopeNode left, RopeNode right) {
            this.left = left;
            this.right = right;
            this.weight = (left != null ? left.weight : 0) + (right != null ? right.weight : 0);
        }
    }

    class Rope {
        RopeNode root;

        void process(int i, int j, int k) {
            // Extract substring from i to j
            RopeNode[] split1 = split(root, i);
            RopeNode[] split2 = split(split1[1], j - i + 1);

            // Merge the substrings
            RopeNode leftSubstr = split1[0];
            RopeNode middleSubstr = split2[0];
            RopeNode rightSubstr = split2[1];

            if (k == 0) {
                root = merge(middleSubstr, merge(leftSubstr, rightSubstr));
            } else {
                RopeNode[] split3 = split(root, k - 1);
                root = merge(merge(split3[0], leftSubstr), merge(middleSubstr, rightSubstr));
            }
        }

        String result() {
            return inorderTraversal(root);
        }

        Rope(String s) {
            root = buildRope(s);
        }

        // Helper function to perform inorder traversal
        String inorderTraversal(RopeNode node) {
            if (node == null)
                return "";
            return inorderTraversal(node.left) + node.data + inorderTraversal(node.right);
        }

        // Helper function to build a Rope from a string
        RopeNode buildRope(String s) {
            if (s == null || s.isEmpty())
                return null;
            return new RopeNode(s);
        }

        // Helper function to split the rope at a given index
        RopeNode[] split(RopeNode node, int index) {
            if (node == null)
                return new RopeNode[]{null, null};

            if (index == 0)
                return new RopeNode[]{null, node};

            if (index >= node.weight)
                return new RopeNode[]{node, null};

            if (node.left == null && index > 0) {
                // Special case when node.left is null but index is > 0
                RopeNode[] splitNodes = split(node.right, index - 1);
                return new RopeNode[]{null, merge(node, splitNodes[0])};
            }

            if (node.right == null && index < node.weight) {
                // Special case when node.right is null but index < node.weight
                RopeNode[] splitNodes = split(node.left, index);
                return new RopeNode[]{merge(splitNodes[0], node), null};
            }

            // Split the rope into two parts
            if (index <= node.left.weight) {
                RopeNode[] splitNodes = split(node.left, index);
                return new RopeNode[]{splitNodes[0], merge(splitNodes[1], node.right)};
            } else {
                RopeNode[] splitNodes = split(node.right, index - node.left.weight);
                return new RopeNode[]{merge(node.left, splitNodes[0]), splitNodes[1]};
            }
        }

        // Helper function to merge two ropes
        RopeNode merge(RopeNode left, RopeNode right) {
            if (left == null)
                return right;
            if (right == null)
                return left;
            return new RopeNode(left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        Rope rope = new Rope(in.next());
        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            rope.process(i, j, k);
        }
        out.println(rope.result());
        out.close();
    }
}
