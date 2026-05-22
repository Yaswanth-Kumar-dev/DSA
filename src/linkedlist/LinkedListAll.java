package linkedlist;

/**
 * Linked List Implementations:
 * 1. Singly Linked List (with classic problems)
 * 2. Doubly Linked List
 * 3. Circular Linked List
 */
public class LinkedListAll {

    // ==================== 1. SINGLY LINKED LIST ====================
    static class SinglyLinkedList {
        static class Node { int data; Node next; Node(int d) { data = d; } }
        Node head;

        public void addFirst(int val) { Node n = new Node(val); n.next = head; head = n; }
        public void addLast(int val)  {
            Node n = new Node(val);
            if (head == null) { head = n; return; }
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }

        public void addAtIndex(int index, int val) {
            if (index == 0) { addFirst(val); return; }
            Node cur = head;
            for (int i = 0; i < index - 1 && cur != null; i++) cur = cur.next;
            if (cur == null) throw new IndexOutOfBoundsException();
            Node n = new Node(val); n.next = cur.next; cur.next = n;
        }

        public void deleteFirst() { if (head != null) head = head.next; }
        public void deleteLast() {
            if (head == null || head.next == null) { head = null; return; }
            Node cur = head;
            while (cur.next.next != null) cur = cur.next;
            cur.next = null;
        }

        public void deleteByValue(int val) {
            if (head == null) return;
            if (head.data == val) { head = head.next; return; }
            Node cur = head;
            while (cur.next != null && cur.next.data != val) cur = cur.next;
            if (cur.next != null) cur.next = cur.next.next;
        }

        public boolean search(int val) {
            Node cur = head;
            while (cur != null) { if (cur.data == val) return true; cur = cur.next; }
            return false;
        }

        public int length() { int c = 0; Node cur = head; while (cur != null) { c++; cur = cur.next; } return c; }

        // ---- CLASSIC PROBLEMS ----

        /** Reverse the linked list */
        public void reverse() {
            Node prev = null, cur = head, next;
            while (cur != null) { next = cur.next; cur.next = prev; prev = cur; cur = next; }
            head = prev;
        }

        /** Find middle node (slow/fast pointer) */
        public Node findMiddle() {
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
            return slow;
        }

        /** Detect cycle (Floyd's algorithm) */
        public boolean hasCycle() {
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next; fast = fast.next.next;
                if (slow == fast) return true;
            }
            return false;
        }

        /** Remove Nth node from end */
        public void removeNthFromEnd(int n) {
            Node dummy = new Node(0); dummy.next = head;
            Node fast = dummy, slow = dummy;
            for (int i = 0; i <= n; i++) fast = fast.next;
            while (fast != null) { slow = slow.next; fast = fast.next; }
            slow.next = slow.next.next;
            head = dummy.next;
        }

        /** Check if palindrome */
        public boolean isPalindrome() {
            if (head == null || head.next == null) return true;
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
            // Reverse second half
            Node prev = null, cur = slow;
            while (cur != null) { Node next = cur.next; cur.next = prev; prev = cur; cur = next; }
            // Compare
            Node left = head, right = prev;
            while (right != null) { if (left.data != right.data) return false; left = left.next; right = right.next; }
            return true;
        }

        /** Remove duplicates from sorted list */
        public void removeDuplicatesSorted() {
            Node cur = head;
            while (cur != null && cur.next != null) {
                if (cur.data == cur.next.data) cur.next = cur.next.next;
                else cur = cur.next;
            }
        }

        /** Merge two sorted linked lists */
        public static Node mergeSorted(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.data <= b.data) { a.next = mergeSorted(a.next, b); return a; }
            else { b.next = mergeSorted(a, b.next); return b; }
        }

        /** Rotate list by k */
        public void rotateByK(int k) {
            if (head == null || k == 0) return;
            int len = length();
            k = k % len;
            if (k == 0) return;
            Node cur = head;
            for (int i = 0; i < len - k - 1; i++) cur = cur.next;
            Node newHead = cur.next;
            cur.next = null;
            Node tail = newHead;
            while (tail.next != null) tail = tail.next;
            tail.next = head;
            head = newHead;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("SLL: head -> ");
            Node cur = head;
            while (cur != null) { sb.append(cur.data).append(" -> "); cur = cur.next; }
            return sb.append("null").toString();
        }
    }

    // ==================== 2. DOUBLY LINKED LIST ====================
    static class DoublyLinkedList {
        static class Node { int data; Node prev, next; Node(int d) { data = d; } }
        Node head, tail;

        public void addFirst(int val) {
            Node n = new Node(val);
            if (head == null) { head = tail = n; return; }
            n.next = head; head.prev = n; head = n;
        }

        public void addLast(int val) {
            Node n = new Node(val);
            if (tail == null) { head = tail = n; return; }
            n.prev = tail; tail.next = n; tail = n;
        }

        public void deleteFirst() {
            if (head == null) return;
            if (head == tail) { head = tail = null; return; }
            head = head.next; head.prev = null;
        }

        public void deleteLast() {
            if (tail == null) return;
            if (head == tail) { head = tail = null; return; }
            tail = tail.prev; tail.next = null;
        }

        public void deleteByValue(int val) {
            Node cur = head;
            while (cur != null) {
                if (cur.data == val) {
                    if (cur.prev != null) cur.prev.next = cur.next; else head = cur.next;
                    if (cur.next != null) cur.next.prev = cur.prev; else tail = cur.prev;
                    return;
                }
                cur = cur.next;
            }
        }

        public void reverse() {
            Node cur = head;
            Node temp = null;
            while (cur != null) {
                temp = cur.prev; cur.prev = cur.next; cur.next = temp; cur = cur.prev;
            }
            if (temp != null) head = temp.prev;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("DLL: null <-> ");
            Node cur = head;
            while (cur != null) { sb.append(cur.data).append(" <-> "); cur = cur.next; }
            return sb.append("null").toString();
        }
    }

    // ==================== 3. CIRCULAR LINKED LIST ====================
    static class CircularLinkedList {
        static class Node { int data; Node next; Node(int d) { data = d; } }
        Node last; // Points to last node (last.next = head)

        public void addFirst(int val) {
            Node n = new Node(val);
            if (last == null) { last = n; last.next = last; return; }
            n.next = last.next; last.next = n;
        }

        public void addLast(int val) {
            addFirst(val);
            last = last.next; // make new node the last
        }

        public void deleteFirst() {
            if (last == null) return;
            if (last.next == last) { last = null; return; }
            last.next = last.next.next;
        }

        public void traverse() {
            if (last == null) return;
            Node cur = last.next;
            System.out.print("CLL: ");
            do { System.out.print(cur.data + " -> "); cur = cur.next; } while (cur != last.next);
            System.out.println("(back to head)");
        }

        /** Josephus Problem: last survivor when every k-th person is eliminated */
        public static int josephus(int n, int k) {
            if (n == 1) return 0;
            return (josephus(n - 1, k) + k) % n;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Singly Linked List ===");
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.addLast(1); sll.addLast(2); sll.addLast(3); sll.addLast(4); sll.addLast(5);
        System.out.println(sll);
        sll.reverse();
        System.out.println("Reversed: " + sll);
        sll.reverse();

        System.out.println("Middle: " + sll.findMiddle().data);
        System.out.println("Has Cycle: " + sll.hasCycle());
        System.out.println("Is Palindrome: " + sll.isPalindrome());

        sll.removeNthFromEnd(2);
        System.out.println("After removing 2nd from end: " + sll);

        System.out.println("\n=== Doubly Linked List ===");
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addLast(1); dll.addLast(2); dll.addLast(3); dll.addLast(4);
        System.out.println(dll);
        dll.reverse();
        System.out.println("Reversed: " + dll);
        dll.deleteByValue(3);
        System.out.println("After delete(3): " + dll);

        System.out.println("\n=== Circular Linked List ===");
        CircularLinkedList cll = new CircularLinkedList();
        cll.addLast(1); cll.addLast(2); cll.addLast(3); cll.addLast(4);
        cll.traverse();
        System.out.println("Josephus(7, 3): survivor at position " + (CircularLinkedList.josephus(7, 3) + 1));

        System.out.println("\n=== Merge Two Sorted Lists ===");
        SinglyLinkedList l1 = new SinglyLinkedList();
        l1.addLast(1); l1.addLast(3); l1.addLast(5);
        SinglyLinkedList l2 = new SinglyLinkedList();
        l2.addLast(2); l2.addLast(4); l2.addLast(6);
        SinglyLinkedList.Node merged = SinglyLinkedList.mergeSorted(l1.head, l2.head);
        System.out.print("Merged: ");
        for (SinglyLinkedList.Node c = merged; c != null; c = c.next) System.out.print(c.data + " ");
        System.out.println();
    }
}
