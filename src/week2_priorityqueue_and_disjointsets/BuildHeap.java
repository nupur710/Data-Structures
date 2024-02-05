package week2_priorityqueue_and_disjointsets;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    int left(int i) {
        return (2 * i + 1);
    }

    int right(int i) {
        return  (2 * i + 2);
    }

    int parent(int i) {
        return (i-1)/2;
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    void minHeapify(int i) {
        int lt= left(i);
        int rt= right(i);
        int smallest= i;
        if(lt < data.length && data[lt] < data[i]) smallest= lt;
        if(rt < data.length && data[rt] < data[smallest]) smallest= rt;
        if(smallest!=i) {
            swaps.add(new Swap(i, smallest));
            int temp= data[i];
            data[i]= data[smallest];
            data[smallest]= temp;
            minHeapify(smallest);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        for(int i= (data.length-2)/2; i>=0; i--) {
            minHeapify(i);
        }
    }




    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
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
