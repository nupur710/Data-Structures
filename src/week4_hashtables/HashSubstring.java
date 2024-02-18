package week4_hashtables;
//Rabin Karp implementation
import java.io.*;
import java.util.*;

public class HashSubstring {

    static final int d= 256;
    static final int q= 101;

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {

        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        int h=1;
        for(int i=1;i<=m-1;i++)
            h=(h*d)%q;

        //Compute p and to
        int p=0,ti=0;
        for(int i=0;i<m;i++){
            p=(p*d+input.pattern.charAt(i))%q;
            ti=(ti*d+input.text.charAt(i))%q;
        }

        for(int i=0;i<=(n-m);i++){
            //Check for hit
            if(p==ti){
                boolean flag=true;
                for(int j=0;j<m;j++)
                    if(input.text.charAt(i+j)!=input.pattern.charAt(j)){flag=false;break;}
                if(flag==true) occurrences.add(i);
            }
            //Compute ti+1 using ti
            if(i<n-m){
                ti=((d*(ti-input.text.charAt(i)*h))+input.text.charAt(i+m))%q;
                if(ti<0)ti=ti+q;
            }
        }
        return occurrences;
//        List<Integer> occurrences = new ArrayList<Integer>();
//        for (int i = 0; i + m <= n; ++i) {
//            boolean equal = true;
//            for (int j = 0; j < m; ++j) {
//                if (s.charAt(j) != t.charAt(i + j)) {
//                    equal = false;
//                    break;
//                }
//            }
//            if (equal)
//                occurrences.add(i);
//        }
//        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

