package queue;

import java.util.*;

/**
 * Queue Implementations:
 * 1. Circular Queue (Array-based)
 * 2. Queue using two Stacks
 * 3. Deque (Double-ended Queue)
 * + Classic Queue Problems
 */
public class QueueImplementations {

    // ==================== 1. CIRCULAR QUEUE ====================
    static class CircularQueue {
        private int[] data;
        private int front, rear, size, capacity;

        public CircularQueue(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            front = 0; rear = -1; size = 0;
        }

        public void enqueue(int val) {
            if (isFull()) throw new RuntimeException("Queue Full");
            rear = (rear + 1) % capacity;
            data[rear] = val;
            size++;
        }

        public int dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue Empty");
            int val = data[front];
            front = (front + 1) % capacity;
            size--;
            return val;
        }

        public int peek()     { if (isEmpty()) throw new NoSuchElementException(); return data[front]; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull()  { return size == capacity; }
        public int size()        { return size; }

        @Override
        public String toString() {
            if (isEmpty()) return "CircularQueue: []";
            StringBuilder sb = new StringBuilder("CircularQueue (front->rear): [");
            for (int i = 0; i < size; i++) {
                sb.append(data[(front + i) % capacity]);
                if (i < size - 1) sb.append(", ");
            }
            return sb.append("]").toString();
        }
    }

    // ==================== 2. QUEUE USING TWO STACKS ====================
    static class QueueUsingStacks {
        private Stack<Integer> inbox  = new Stack<>();
        private Stack<Integer> outbox = new Stack<>();

        public void enqueue(int val) { inbox.push(val); }

        public int dequeue() {
            if (outbox.isEmpty())
                while (!inbox.isEmpty()) outbox.push(inbox.pop());
            if (outbox.isEmpty()) throw new NoSuchElementException("Queue Empty");
            return outbox.pop();
        }

        public int peek() {
            if (outbox.isEmpty())
                while (!inbox.isEmpty()) outbox.push(inbox.pop());
            return outbox.peek();
        }

        public boolean isEmpty() { return inbox.isEmpty() && outbox.isEmpty(); }
    }

    // ==================== 3. DEQUE (Double-Ended Queue) ====================
    static class Deque {
        private int[] data;
        private int front, rear, size, capacity;

        public Deque(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            front = capacity / 2;
            rear = capacity / 2 - 1;
            size = 0;
        }

        public void addFront(int val) {
            if (isFull()) throw new RuntimeException("Deque Full");
            front = (front - 1 + capacity) % capacity;
            data[front] = val; size++;
        }

        public void addRear(int val) {
            if (isFull()) throw new RuntimeException("Deque Full");
            rear = (rear + 1) % capacity;
            data[rear] = val; size++;
        }

        public int removeFront() {
            if (isEmpty()) throw new NoSuchElementException();
            int val = data[front];
            front = (front + 1) % capacity; size--;
            return val;
        }

        public int removeRear() {
            if (isEmpty()) throw new NoSuchElementException();
            int val = data[rear];
            rear = (rear - 1 + capacity) % capacity; size--;
            return val;
        }

        public int peekFront() { return data[front]; }
        public int peekRear()  { return data[rear]; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull()  { return size == capacity; }
    }

    // ==================== LINKED LIST BASED QUEUE ====================
    static class QueueLinkedList {
        private static class Node { int data; Node next; Node(int d) { data = d; } }
        private Node front, rear;
        private int size;

        public void enqueue(int val) {
            Node node = new Node(val);
            if (rear != null) rear.next = node;
            rear = node;
            if (front == null) front = rear;
            size++;
        }

        public int dequeue() {
            if (front == null) throw new NoSuchElementException();
            int val = front.data;
            front = front.next;
            if (front == null) rear = null;
            size--;
            return val;
        }

        public int peek()    { return front.data; }
        public boolean isEmpty() { return front == null; }
        public int size()    { return size; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Queue (front->rear): [");
            Node cur = front;
            while (cur != null) { sb.append(cur.data); if (cur.next != null) sb.append(", "); cur = cur.next; }
            return sb.append("]").toString();
        }
    }

    // ==================== PRIORITY QUEUE ====================
    static class MinPriorityQueue {
        private PriorityQueue<Integer> pq = new PriorityQueue<>();
        public void add(int val)    { pq.offer(val); }
        public int poll()           { return pq.poll(); }
        public int peek()           { return pq.peek(); }
        public boolean isEmpty()    { return pq.isEmpty(); }
    }

    // ==================== CLASSIC PROBLEMS ====================

    /** Sliding Window Maximum using Deque */
    public static int[] slidingWindowMax(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) dq.pollLast();
            dq.offerLast(i);
            if (i >= k - 1) result[i - k + 1] = arr[dq.peekFirst()];
        }
        return result;
    }

    /** First non-repeating character in stream */
    public static char[] firstNonRepeating(String stream) {
        int[] freq = new int[26];
        Queue<Character> q = new LinkedList<>();
        char[] result = new char[stream.length()];
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            freq[c - 'a']++;
            q.offer(c);
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) q.poll();
            result[i] = q.isEmpty() ? '#' : q.peek();
        }
        return result;
    }

    /** Interleave first and second half of queue */
    public static Queue<Integer> interleaveQueue(Queue<Integer> q) {
        int size = q.size();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < size / 2; i++) st.push(q.poll());
        while (!st.isEmpty()) q.offer(st.pop());
        for (int i = 0; i < size / 2; i++) q.offer(q.poll());
        for (int i = 0; i < size / 2; i++) st.push(q.poll());
        while (!st.isEmpty()) { q.offer(st.pop()); q.offer(q.poll()); }
        return q;
    }

    public static void main(String[] args) {
        System.out.println("=== Circular Queue ===");
        CircularQueue cq = new CircularQueue(5);
        cq.enqueue(10); cq.enqueue(20); cq.enqueue(30);
        System.out.println(cq);
        System.out.println("Dequeue: " + cq.dequeue());
        System.out.println(cq);

        System.out.println("\n=== Queue using Two Stacks ===");
        QueueUsingStacks qs = new QueueUsingStacks();
        qs.enqueue(1); qs.enqueue(2); qs.enqueue(3);
        System.out.println("Dequeue: " + qs.dequeue());
        System.out.println("Peek: " + qs.peek());

        System.out.println("\n=== Linked List Queue ===");
        QueueLinkedList llq = new QueueLinkedList();
        llq.enqueue(5); llq.enqueue(10); llq.enqueue(15);
        System.out.println(llq);

        System.out.println("\n=== Sliding Window Max (k=3) ===");
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(slidingWindowMax(arr, 3)));

        System.out.println("\n=== First Non-Repeating in Stream ===");
        System.out.println(new String(firstNonRepeating("aabcbc")));

        System.out.println("\n=== Priority Queue ===");
        MinPriorityQueue mpq = new MinPriorityQueue();
        mpq.add(5); mpq.add(1); mpq.add(3); mpq.add(2);
        while (!mpq.isEmpty()) System.out.print(mpq.poll() + " ");
        System.out.println();
    }
}
