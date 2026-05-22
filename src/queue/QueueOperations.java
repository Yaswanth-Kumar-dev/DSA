package queue;

import java.util.*;

/**
 * Queue DSA - Array-based Queue + Circular Queue + Deque + Priority Queue
 * Topics: Enqueue, Dequeue, Peek, Circular Queue, Deque, PriorityQueue,
 *         Sliding Window Maximum, Generate Binary Numbers
 */
public class QueueOperations {

    // ── 1. Custom Array-Based Linear Queue ──
    static class ArrayQueue {
        private int[] data;
        private int front, rear, size, capacity;

        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            front = 0; rear = -1; size = 0;
        }

        public void enqueue(int val) {
            if (isFull()) { System.out.println("Queue is Full!"); return; }
            rear = (rear + 1) % capacity;
            data[rear] = val;
            size++;
        }

        public int dequeue() {
            if (isEmpty()) { System.out.println("Queue is Empty!"); return -1; }
            int val = data[front];
            front = (front + 1) % capacity;
            size--;
            return val;
        }

        public int peek()     { return isEmpty() ? -1 : data[front]; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull()  { return size == capacity; }
        public int size()        { return size; }

        public void display() {
            System.out.print("Queue (front→rear): ");
            for (int i = 0; i < size; i++) System.out.print(data[(front + i) % capacity] + " ");
            System.out.println();
        }
    }

    // ── 2. Custom Circular Queue ──
    static class CircularQueue {
        private int[] data;
        private int head, tail, size, cap;

        public CircularQueue(int cap) {
            this.cap = cap; data = new int[cap];
            head = 0; tail = 0; size = 0;
        }

        public boolean enQueue(int val) {
            if (size == cap) return false;
            data[tail] = val; tail = (tail + 1) % cap; size++;
            return true;
        }

        public boolean deQueue() {
            if (size == 0) return false;
            head = (head + 1) % cap; size--;
            return true;
        }

        public int Front() { return size == 0 ? -1 : data[head]; }
        public int Rear()  { return size == 0 ? -1 : data[(tail - 1 + cap) % cap]; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull()  { return size == cap; }
    }

    // ── 3. QUEUE USING TWO STACKS ──
    static class QueueUsingStacks {
        private Stack<Integer> inbox  = new Stack<>();
        private Stack<Integer> outbox = new Stack<>();

        public void enqueue(int val) { inbox.push(val); }

        public int dequeue() {
            if (outbox.isEmpty())
                while (!inbox.isEmpty()) outbox.push(inbox.pop());
            return outbox.isEmpty() ? -1 : outbox.pop();
        }

        public int peek() {
            if (outbox.isEmpty())
                while (!inbox.isEmpty()) outbox.push(inbox.pop());
            return outbox.isEmpty() ? -1 : outbox.peek();
        }
    }

    // ── 4. GENERATE BINARY NUMBERS 1..N ──
    public static void generateBinary(int n) {
        Queue<String> q = new LinkedList<>();
        q.add("1");
        System.out.print("Binary numbers 1 to " + n + ": ");
        for (int i = 0; i < n; i++) {
            String s = q.poll();
            System.out.print(s + " ");
            q.add(s + "0");
            q.add(s + "1");
        }
        System.out.println();
    }

    // ── 5. SLIDING WINDOW MAXIMUM ──
    public static int[] slidingWindowMax(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && dq.peek() < i - k + 1) dq.poll();
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) dq.pollLast();
            dq.offer(i);
            if (i >= k - 1) result[i - k + 1] = arr[dq.peek()];
        }
        return result;
    }

    // ── 6. PRIORITY QUEUE (Min-Heap & Max-Heap) ──
    public static void demoPriorityQueue() {
        // Min-Heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(30); minHeap.add(10); minHeap.add(20);
        System.out.print("MinHeap poll order: ");
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");

        // Max-Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(30); maxHeap.add(10); maxHeap.add(20);
        System.out.print("\nMaxHeap poll order: ");
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " ");
        System.out.println();
    }

    // ── 7. DEQUE (Double Ended Queue) ──
    public static void demoDeque() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(10); dq.addFirst(5);
        dq.addLast(20); dq.addLast(25);
        System.out.println("Deque: " + dq);
        System.out.println("PeekFirst: " + dq.peekFirst() + " | PeekLast: " + dq.peekLast());
        dq.removeFirst(); dq.removeLast();
        System.out.println("After removeFirst & removeLast: " + dq);
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== QUEUE OPERATIONS ===\n");

        ArrayQueue q = new ArrayQueue(5);
        q.enqueue(10); q.enqueue(20); q.enqueue(30); q.enqueue(40);
        q.display();
        System.out.println("Dequeue: " + q.dequeue());
        q.display();

        System.out.println("\nQueue using Two Stacks:");
        QueueUsingStacks qs = new QueueUsingStacks();
        qs.enqueue(1); qs.enqueue(2); qs.enqueue(3);
        System.out.println("Dequeue: " + qs.dequeue() + " | Peek: " + qs.peek());

        System.out.println();
        generateBinary(8);

        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] maxWindow = slidingWindowMax(arr, 3);
        System.out.print("Sliding Window Max (k=3): ");
        for (int x : maxWindow) System.out.print(x + " ");
        System.out.println();

        System.out.println("\nPriority Queue:");
        demoPriorityQueue();

        System.out.println("\nDeque:");
        demoDeque();

        System.out.println("\nCircular Queue:");
        CircularQueue cq = new CircularQueue(3);
        cq.enQueue(1); cq.enQueue(2); cq.enQueue(3);
        System.out.println("Front: " + cq.Front() + " | Rear: " + cq.Rear() + " | Full: " + cq.isFull());
        cq.deQueue();
        System.out.println("After dequeue, Front: " + cq.Front());
    }
}
