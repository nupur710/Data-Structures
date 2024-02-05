package week2_priorityqueue_and_disjointsets;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * n threads to process m users. If there is a free thread, it immediately takes the next job
 * from the list. If several threads are free & try to take a job, the thread with smaller index
 * takes the job (Hint: min heap for n threads). We know the time taken to process a job. Need
 * to determine each job will be processed by which thread and when it will start processing.
 *
 */
public class JobQueue {

    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<Worker> minHeap = new PriorityQueue<>();

        for (int i = 0; i < numWorkers; i++) {
            minHeap.offer(new Worker(i, 0));
        }

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Worker nextWorker = minHeap.poll();
            assignedWorker[i] = nextWorker.workerId;
            startTime[i] = nextWorker.nextFreeTime;
            nextWorker.nextFreeTime += duration;
            minHeap.offer(nextWorker);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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

    static class Worker implements Comparable<Worker> {
        int workerId;
        long nextFreeTime;

        Worker(int workerId, long nextFreeTime) {
            this.workerId = workerId;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public int compareTo(Worker other) {
            if (nextFreeTime == other.nextFreeTime) {
                return Integer.compare(workerId, other.workerId);
            }
            return Long.compare(nextFreeTime, other.nextFreeTime);
        }
    }
}
