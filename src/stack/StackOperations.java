package stack;

import java.util.Stack;

/**
 * Stack DSA - Array-based Stack + Built-in Stack usage
 * Topics: Push, Pop, Peek, isEmpty, isFull, Balanced Parentheses,
 *         Next Greater Element, Stock Span, Reverse string, Infix→Postfix
 */
public class StackOperations {

    // ── Custom Array-Based Stack ──
    static class ArrayStack {
        private int[] data;
        private int top;
        private int capacity;

        public ArrayStack(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            top = -1;
        }

        public void push(int val) {
            if (isFull()) { System.out.println("Stack Overflow!"); return; }
            data[++top] = val;
        }

        public int pop() {
            if (isEmpty()) { System.out.println("Stack Underflow!"); return -1; }
            return data[top--];
        }

        public int peek() {
            if (isEmpty()) { System.out.println("Stack is empty!"); return -1; }
            return data[top];
        }

        public boolean isEmpty() { return top == -1; }
        public boolean isFull()  { return top == capacity - 1; }
        public int size()        { return top + 1; }

        public void display() {
            System.out.print("Stack (top→bottom): ");
            for (int i = top; i >= 0; i--) System.out.print(data[i] + " ");
            System.out.println();
        }
    }

    // ── 1. BALANCED PARENTHESES ──
    public static boolean isBalanced(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char top = st.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) return false;
            }
        }
        return st.isEmpty();
    }

    // ── 2. REVERSE A STRING ──
    public static String reverseString(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) st.push(c);
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        return sb.toString();
    }

    // ── 3. NEXT GREATER ELEMENT (to the right) ──
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i]) st.pop();
            result[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return result;
    }

    // ── 4. STOCK SPAN PROBLEM ──
    public static int[] stockSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> st = new Stack<>(); // stores indices
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && prices[st.peek()] <= prices[i]) st.pop();
            span[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }
        return span;
    }

    // ── 5. INFIX TO POSTFIX ──
    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }

    public static String infixToPostfix(String expr) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (Character.isLetterOrDigit(c)) { result.append(c); }
            else if (c == '(') { st.push(c); }
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') result.append(st.pop());
                if (!st.isEmpty()) st.pop();
            } else {
                while (!st.isEmpty() && precedence(c) <= precedence(st.peek()))
                    result.append(st.pop());
                st.push(c);
            }
        }
        while (!st.isEmpty()) result.append(st.pop());
        return result.toString();
    }

    // ── 6. EVALUATE POSTFIX ──
    public static int evalPostfix(String expr) {
        Stack<Integer> st = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) { st.push(c - '0'); }
            else {
                int b = st.pop(), a = st.pop();
                switch (c) {
                    case '+': st.push(a + b); break;
                    case '-': st.push(a - b); break;
                    case '*': st.push(a * b); break;
                    case '/': st.push(a / b); break;
                }
            }
        }
        return st.pop();
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== STACK OPERATIONS ===\n");

        // Custom Stack
        ArrayStack stack = new ArrayStack(5);
        stack.push(10); stack.push(20); stack.push(30); stack.push(40);
        stack.display();
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.display();

        // Balanced Parentheses
        System.out.println("\nBalanced Parentheses:");
        System.out.println("{[()]} → " + isBalanced("{[()]}"));
        System.out.println("{[(])} → " + isBalanced("{[(])}"));

        // Reverse
        System.out.println("\nReverse 'hello': " + reverseString("hello"));

        // Next Greater Element
        int[] arr = {4, 5, 2, 10, 8};
        int[] nge = nextGreater(arr);
        System.out.print("\nNext Greater Elements: ");
        for (int x : nge) System.out.print(x + " ");

        // Stock Span
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = stockSpan(prices);
        System.out.print("\nStock Span: ");
        for (int x : span) System.out.print(x + " ");

        // Infix to Postfix
        String expr = "a+b*(c^d-e)";
        System.out.println("\n\nInfix: " + expr);
        System.out.println("Postfix: " + infixToPostfix(expr));

        // Evaluate Postfix
        System.out.println("Eval '231*+9-': " + evalPostfix("231*+9-"));
    }
}
