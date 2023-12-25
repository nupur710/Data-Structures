package week1_basic_data_structures;

import java.util.*;
import java.io.*;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> aux_stack= new Stack<Integer>();
        aux_stack.push(0);
        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
                if(value >= aux_stack.peek()) {
                    aux_stack.push(value);
//                    System.out.println("pushing " + value + " to stack");
                } else {
                    aux_stack.push(aux_stack.peek());
                }
            } else if ("pop".equals(operation)) {
                if(!stack.isEmpty()) {
                    stack.pop();
                    aux_stack.pop();
                }
            } else if ("max".equals(operation)) {
                System.out.println(aux_stack.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}

