package linkedlist;

/**
 * LinkedList DSA - Singly + Doubly + Circular
 * Topics: Insert, Delete, Reverse, Detect Cycle, Find Middle,
 *         Merge Sorted Lists, Remove Nth from End, Doubly LL, Circular LL
 */
public class LinkedListOperations {

    // ── Node ──
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    // ── Doubly Node ──
    static class DNode {
        int data;
        DNode prev, next;
        DNode(int data) { this.data = data; }
    }

    // ════════════════════════════════════════
    // SINGLY LINKED LIST
    // ════════════════════════════════════════
    static class SinglyLinkedList {
        Node head;

        // Insert at beginning
        public void insertFront(int val) {
            Node node = new Node(val);
            node.next = head;
            head = node;
        }

        // Insert at end
        public void insertEnd(int val) {
            Node node = new Node(val);
            if (head == null) { head = node; return; }
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = node;
        }

        // Insert at position (0-indexed)
        public void insertAt(int pos, int val) {
            if (pos == 0) { insertFront(val); return; }
            Node node = new Node(val), curr = head;
            for (int i = 0; i < pos - 1 && curr != null; i++) curr = curr.next;
            if (curr == null) return;
            node.next = curr.next;
            curr.next = node;
        }

        // Delete by value
        public void delete(int val) {
            if (head == null) return;
            if (head.data == val) { head = head.next; return; }
            Node curr = head;
            while (curr.next != null && curr.next.data != val) curr = curr.next;
            if (curr.next != null) curr.next = curr.next.next;
        }

        // Delete at position
        public void deleteAt(int pos) {
            if (head == null) return;
            if (pos == 0) { head = head.next; return; }
            Node curr = head;
            for (int i = 0; i < pos - 1 && curr.next != null; i++) curr = curr.next;
            if (curr.next != null) curr.next = curr.next.next;
        }

        // Display
        public void display() {
            Node curr = head;
            System.out.print("List: ");
            while (curr != null) { System.out.print(curr.data + " → "); curr = curr.next; }
            System.out.println("null");
        }

        // Length
        public int length() {
            int count = 0; Node curr = head;
            while (curr != null) { count++; curr = curr.next; }
            return count;
        }

        // Search
        public boolean search(int val) {
            Node curr = head;
            while (curr != null) { if (curr.data == val) return true; curr = curr.next; }
            return false;
        }

        // Reverse (iterative)
        public void reverse() {
            Node prev = null, curr = head, next;
            while (curr != null) { next = curr.next; curr.next = prev; prev = curr; curr = next; }
            head = prev;
        }

        // Find Middle (Floyd's two-pointer)
        public int findMiddle() {
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
            return slow != null ? slow.data : -1;
        }

        // Detect Cycle (Floyd's)
        public boolean hasCycle() {
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next; fast = fast.next.next;
                if (slow == fast) return true;
            }
            return false;
        }

        // Remove Nth node from end
        public void removeNthFromEnd(int n) {
            Node dummy = new Node(0); dummy.next = head;
            Node fast = dummy, slow = dummy;
            for (int i = 0; i <= n; i++) fast = fast.next;
            while (fast != null) { fast = fast.next; slow = slow.next; }
            slow.next = slow.next.next;
            head = dummy.next;
        }

        // Merge two sorted linked lists
        public static Node mergeSorted(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.data <= b.data) { a.next = mergeSorted(a.next, b); return a; }
            else { b.next = mergeSorted(a, b.next); return b; }
        }

        // Check Palindrome
        public boolean isPalindrome() {
            if (head == null || head.next == null) return true;
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
            // Reverse second half
            Node prev = null, curr = slow;
            while (curr != null) { Node next = curr.next; curr.next = prev; prev = curr; curr = next; }
            Node left = head, right = prev;
            while (right != null) { if (left.data != right.data) return false; left = left.next; right = right.next; }
            return true;
        }
    }

    // ════════════════════════════════════════
    // DOUBLY LINKED LIST
    // ════════════════════════════════════════
    static class DoublyLinkedList {
        DNode head;

        public void insertFront(int val) {
            DNode node = new DNode(val);
            node.next = head;
            if (head != null) head.prev = node;
            head = node;
        }

        public void insertEnd(int val) {
            DNode node = new DNode(val);
            if (head == null) { head = node; return; }
            DNode curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = node; node.prev = curr;
        }

        public void delete(int val) {
            DNode curr = head;
            while (curr != null && curr.data != val) curr = curr.next;
            if (curr == null) return;
            if (curr.prev != null) curr.prev.next = curr.next;
            else head = curr.next;
            if (curr.next != null) curr.next.prev = curr.prev;
        }

        public void displayForward() {
            DNode curr = head;
            System.out.print("Forward: ");
            while (curr != null) { System.out.print(curr.data + " ⇄ "); curr = curr.next; }
            System.out.println("null");
        }

        public void displayBackward() {
            DNode curr = head;
            while (curr != null && curr.next != null) curr = curr.next;
            System.out.print("Backward: ");
            while (curr != null) { System.out.print(curr.data + " ⇄ "); curr = curr.prev; }
            System.out.println("null");
        }
    }

    // ════════════════════════════════════════
    // CIRCULAR LINKED LIST
    // ════════════════════════════════════════
    static class CircularLinkedList {
        Node head;

        public void insert(int val) {
            Node node = new Node(val);
            if (head == null) { head = node; node.next = head; return; }
            Node curr = head;
            while (curr.next != head) curr = curr.next;
            curr.next = node; node.next = head;
        }

        public void display() {
            if (head == null) return;
            System.out.print("Circular: ");
            Node curr = head;
            do { System.out.print(curr.data + " → "); curr = curr.next; } while (curr != head);
            System.out.println("(back to head)");
        }

        public void delete(int val) {
            if (head == null) return;
            if (head.data == val) {
                Node last = head;
                while (last.next != head) last = last.next;
                if (head == last) { head = null; return; }
                last.next = head.next; head = head.next; return;
            }
            Node curr = head;
            while (curr.next != head && curr.next.data != val) curr = curr.next;
            if (curr.next != head) curr.next = curr.next.next;
        }
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== SINGLY LINKED LIST ===\n");
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.insertEnd(10); sll.insertEnd(20); sll.insertEnd(30); sll.insertEnd(40);
        sll.insertFront(5);
        sll.display();
        System.out.println("Length: " + sll.length());
        System.out.println("Search 20: " + sll.search(20));
        System.out.println("Middle: " + sll.findMiddle());
        sll.delete(20); sll.display();
        sll.reverse(); sll.display();
        System.out.println("Has Cycle: " + sll.hasCycle());

        System.out.println("\nPalindrome check (1→2→1): ");
        SinglyLinkedList pal = new SinglyLinkedList();
        pal.insertEnd(1); pal.insertEnd(2); pal.insertEnd(1);
        System.out.println(pal.isPalindrome());

        System.out.println("\n=== DOUBLY LINKED LIST ===\n");
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertEnd(10); dll.insertEnd(20); dll.insertEnd(30);
        dll.insertFront(5);
        dll.displayForward();
        dll.displayBackward();
        dll.delete(20);
        dll.displayForward();

        System.out.println("\n=== CIRCULAR LINKED LIST ===\n");
        CircularLinkedList cll = new CircularLinkedList();
        cll.insert(10); cll.insert(20); cll.insert(30);
        cll.display();
        cll.delete(20); cll.display();
    }
}
