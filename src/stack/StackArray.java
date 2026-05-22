package stack;

import java.util.EmptyStackException;

/**
 * Stack - Array-Based Implementation
 * Operations: push, pop, peek, isEmpty, isFull
 */
public class StackArray {
    private int[] data;
    private int top;
    private int capacity;

    public StackArray(int capacity) {
        this.capacity = capacity;
        data = new int[capacity];
        top = -1;
    }

    public void push(int val) {
        if (isFull()) throw new RuntimeException("Stack Overflow");
        data[++top] = val;
    }

    public int pop() {
        if (isEmpty()) throw new EmptyStackException();
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) throw new EmptyStackException();
        return data[top];
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull()  { return top == capacity - 1; }
    public int size()        { return top + 1; }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack: []";
        StringBuilder sb = new StringBuilder("Stack (top->bottom): [");
        for (int i = top; i >= 0; i--) {
            sb.append(data[i]);
            if (i > 0) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    // ==================== CLASSIC STACK PROBLEMS ====================

    /** Balanced Parentheses */
    public static boolean isBalanced(String s) {
        java.util.Stack<Character> st = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char top = st.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        return st.isEmpty();
    }

    /** Infix to Postfix conversion */
    public static String infixToPostfix(String exp) {
        java.util.Stack<Character> st = new java.util.Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') result.append(st.pop());
                st.pop();
            } else {
                while (!st.isEmpty() && precedence(c) <= precedence(st.peek())) result.append(st.pop());
                st.push(c);
            }
        }
        while (!st.isEmpty()) result.append(st.pop());
        return result.toString();
    }

    private static int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        if (c == '^') return 3;
        return -1;
    }

    /** Evaluate Postfix expression */
    public static int evaluatePostfix(String exp) {
        java.util.Stack<Integer> st = new java.util.Stack<>();
        for (char c : exp.toCharArray()) {
            if (Character.isDigit(c)) {
                st.push(c - '0');
            } else {
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

    /** Next Greater Element for each index */
    public static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];
        java.util.Stack<Integer> st = new java.util.Stack<>();
        java.util.Arrays.fill(result, -1);
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()])
                result[st.pop()] = arr[i];
            st.push(i);
        }
        return result;
    }

    /** Sort a stack using recursion */
    public static void sortStack(java.util.Stack<Integer> st) {
        if (!st.isEmpty()) {
            int top = st.pop();
            sortStack(st);
            insertSorted(st, top);
        }
    }

    private static void insertSorted(java.util.Stack<Integer> st, int val) {
        if (st.isEmpty() || val > st.peek()) { st.push(val); return; }
        int top = st.pop();
        insertSorted(st, val);
        st.push(top);
    }

    /** Reverse a string using stack */
    public static String reverseString(String s) {
        java.util.Stack<Character> st = new java.util.Stack<>();
        for (char c : s.toCharArray()) st.push(c);
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Stack (Array-based) ===");
        StackArray stack = new StackArray(5);
        stack.push(10); stack.push(20); stack.push(30);
        System.out.println(stack);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println(stack);

        System.out.println("\n=== Stack Problems ===");
        System.out.println("Balanced '({[]})': " + isBalanced("({[]})"));
        System.out.println("Balanced '({[})': " + isBalanced("({[})"));

        String infix = "a+b*(c-d)";
        String postfix = infixToPostfix(infix);
        System.out.println("Infix: " + infix + " -> Postfix: " + postfix);

        System.out.println("Evaluate '231*+9-': " + evaluatePostfix("231*+9-"));

        int[] arr = {4, 5, 2, 10, 8};
        System.out.println("NGE: " + java.util.Arrays.toString(nextGreaterElement(arr)));

        System.out.println("Reverse 'Hello': " + reverseString("Hello"));

        java.util.Stack<Integer> sortMe = new java.util.Stack<>();
        sortMe.push(3); sortMe.push(1); sortMe.push(4); sortMe.push(2);
        sortStack(sortMe);
        System.out.println("Sorted Stack: " + sortMe);
    }
}
