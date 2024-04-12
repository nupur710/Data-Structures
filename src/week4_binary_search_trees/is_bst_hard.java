package week4_binary_search_trees;

import java.util.Scanner;
import java.util.Stack;

public class is_bst_hard {
    static class Node {
        long key;
        long left;
        long right;

        Node(long key, long left, long right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static class BinaryTree {
        static boolean isBinarySearchTree(Node[] tree) {
            Stack<NodeBounds> stack = new Stack<>();
            stack.push(new NodeBounds(Long.MIN_VALUE, tree[0], Long.MAX_VALUE));
            while (!stack.isEmpty()) {
                NodeBounds nb = stack.pop();
                long min = nb.min;
                Node root = nb.node;
                long max = nb.max;
                if ((long) root.key < min || (long) root.key >= max) {
                    return false;
                }
                if (root.left != -1) {
                    stack.push(new NodeBounds(min, tree[(int) root.left], (long) root.key));
                }
                if (root.right != -1) {
                    stack.push(new NodeBounds((long) root.key, tree[(int) root.right], max));
                }
            }
            return true;
        }


        static class NodeBounds {
            long min;
            Node node;
            long max;

            NodeBounds(long min, Node node, long max) {
                this.min = min;
                this.node = node;
                this.max = max;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n_nodes = scanner.nextInt();
        Node[] nodes = new Node[n_nodes];
        for (int i = 0; i < n_nodes; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            Node node = new Node(a, b, c);
            nodes[i] = node;
        }
        if (n_nodes == 0 || BinaryTree.isBinarySearchTree(nodes)) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
