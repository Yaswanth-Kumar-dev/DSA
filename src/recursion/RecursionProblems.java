package recursion;

import java.util.*;

/**
 * Recursion DSA
 * Topics: Factorial, Fibonacci, Power, GCD, Palindrome, Subset Sum,
 *         Permutations, Tower of Hanoi, Flood Fill, Binary Search (recursive)
 */
public class RecursionProblems {

    // 1. FACTORIAL
    public static long factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    // 2. FIBONACCI
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3. POWER  x^n
    public static double power(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) return 1.0 / power(x, -n);
        if (n % 2 == 0) { double half = power(x, n / 2); return half * half; }
        return x * power(x, n - 1);
    }

    // 4. GCD (Euclidean)
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 5. SUM OF DIGITS
    public static int sumOfDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumOfDigits(n / 10);
    }

    // 6. PALINDROME CHECK
    public static boolean isPalindrome(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return isPalindrome(s, l + 1, r - 1);
    }

    // 7. REVERSE A STRING
    public static String reverseString(String s) {
        if (s.isEmpty()) return s;
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    // 8. TOWER OF HANOI
    public static void hanoi(int n, char from, char to, char aux) {
        if (n == 1) { System.out.println("Move disk 1 from " + from + " to " + to); return; }
        hanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        hanoi(n - 1, aux, to, from);
    }

    // 9. GENERATE ALL SUBSETS
    public static void generateSubsets(int[] arr, int idx, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = idx; i < arr.length; i++) {
            current.add(arr[i]);
            generateSubsets(arr, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // 10. GENERATE PERMUTATIONS
    public static void permutations(String s, String current, List<String> result) {
        if (s.isEmpty()) { result.add(current); return; }
        for (int i = 0; i < s.length(); i++) {
            permutations(s.substring(0, i) + s.substring(i + 1), current + s.charAt(i), result);
        }
    }

    // 11. SUBSET SUM EXISTS
    public static boolean subsetSum(int[] arr, int n, int target) {
        if (target == 0) return true;
        if (n == 0) return false;
        if (arr[n-1] > target) return subsetSum(arr, n - 1, target);
        return subsetSum(arr, n - 1, target - arr[n-1]) || subsetSum(arr, n - 1, target);
    }

    // 12. COUNT WAYS TO CLIMB STAIRS (1 or 2 steps)
    public static int climbStairs(int n) {
        if (n <= 1) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== RECURSION PROBLEMS ===\n");

        System.out.println("Factorial(10) = " + factorial(10));
        System.out.println("Fibonacci(10) = " + fibonacci(10));
        System.out.println("2^10 = " + (int)power(2, 10));
        System.out.println("GCD(48, 18) = " + gcd(48, 18));
        System.out.println("Sum of digits(12345) = " + sumOfDigits(12345));
        System.out.println("Palindrome 'racecar': " + isPalindrome("racecar", 0, 6));
        System.out.println("Palindrome 'hello': " + isPalindrome("hello", 0, 4));
        System.out.println("Reverse 'hello': " + reverseString("hello"));
        System.out.println("Climb stairs (5 steps): " + climbStairs(5));

        System.out.println("\nTower of Hanoi (3 disks):");
        hanoi(3, 'A', 'C', 'B');

        System.out.println("\nAll subsets of [1,2,3]:");
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(new int[]{1, 2, 3}, 0, new ArrayList<>(), subsets);
        System.out.println(subsets);

        System.out.println("\nPermutations of 'ABC':");
        List<String> perms = new ArrayList<>();
        permutations("ABC", "", perms);
        System.out.println(perms);

        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println("\nSubset sum 9 in [3,34,4,12,5,2]: " + subsetSum(arr, arr.length, 9));
        System.out.println("Subset sum 30 in [3,34,4,12,5,2]: " + subsetSum(arr, arr.length, 30));
    }
}
