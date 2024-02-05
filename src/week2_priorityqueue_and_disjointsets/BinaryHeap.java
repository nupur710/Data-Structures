package week2_priorityqueue_and_disjointsets;

public class BinaryHeap {

    private int[] heap;
    private int size;

    public BinaryHeap(int capacity) {
        heap = new int[capacity + 1]; // Index 0 is not used, so we add 1 to the capacity
        size = 0;
    }

    int parent(int i) {
        return i / 2;
    }

    int left(int i) {
        return 2 * i;
    }

    int right(int i) {
        return 2 * i + 1;
    }

    void siftUp(int i) {
        while (i > 1 && heap[parent(i)] < heap[i]) {
            // Swap values
            int temp = heap[parent(i)];
            heap[parent(i)] = heap[i];
            heap[i] = temp;
            i = parent(i);
        }
    }

    void siftDown(int i) {
        int maxIndex = i;
        int left = left(i);
        if (left <= size && heap[left] > heap[maxIndex]) {
            maxIndex = left;
        }
        int right = right(i);
        if (right <= size && heap[right] > heap[maxIndex]) {
            maxIndex = right;
        }
        if (i != maxIndex) {
            // Swap values
            int temp = heap[i];
            heap[i] = heap[maxIndex];
            heap[maxIndex] = temp;
            siftDown(maxIndex);
        }
    }

    void insert(int p) {
        if (size == heap.length - 1) {
            // Heap is full
            return;
        }
        size = size + 1;
        heap[size] = p;
        siftUp(size);
    }

    int extractMax() {
        if (size < 1) {
            // Heap is empty
            return -1; // or some sentinel value
        }
        int result = heap[1];
        heap[1] = heap[size];
        size = size - 1;
        siftDown(1);
        return result;
    }

    void remove(int i) {
        if (i > size || i < 1) {
            // Invalid index
            return;
        }
        heap[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    void changePriority(int i, int p) {
        if (i > size || i < 1) {
            // Invalid index
            return;
        }
        int oldp = heap[i];
        heap[i] = p;
        if (p > oldp) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }

    public static void main(String[] args) {
        BinaryHeap bh= new BinaryHeap(13);
        bh.insert(5);
        bh.insert(11);
        bh.insert(12);
        bh.insert(18);
        bh.insert(7);
        bh.insert(14);
        bh.insert(18);
        bh.insert(29);
        bh.insert(42);
    }
}
