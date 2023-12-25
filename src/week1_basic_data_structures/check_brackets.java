package week1_basic_data_structures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        boolean res= false;
        int pos= 0;
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            //      System.out.println(position);
            char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next,position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.empty()) {
                    pos= position;
                    System.out.println(pos+1);
                    return;
                }
                // Process closing bracket, write your code here
                char top= opening_brackets_stack.pop().type;
                Bracket bracket= new Bracket(top, position);
                res= bracket.Match(next);
                pos= position;
                if(res == false) break;
            }
        }
        if(opening_brackets_stack.empty() == false && res == true) {
            int c= opening_brackets_stack.peek().position;
            System.out.println(c+1);
        }
        if(res == true && opening_brackets_stack.empty() == true)
            System.out.println("Success");

        else if(res == false)
            System.out.println(pos+1);
        // Printing answer, write your code here
//        else if (res == false && opening_brackets_stack.empty()) {
//
//        }
    }

}
