package arrays;

import java.util.Arrays;

/**
 * Arrays DSA - Covers all key array operations and algorithms
 * Topics: Traversal, Insertion, Deletion, Rotation, Searching, 2D arrays,
 *         Sliding Window, Two Pointer, Prefix Sum, Kadane's Algorithm
 */
public class ArrayOperations {

    // 1. TRAVERSAL
    public static void traverse(int[] arr) {
        System.out.print("Array: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    // 2. INSERT AT POSITION
    public static int[] insertAt(int[] arr, int pos, int val) {
        int[] result = new int[arr.length + 1];
        for (int i = 0; i < pos; i++) result[i] = arr[i];
        result[pos] = val;
        for (int i = pos; i < arr.length; i++) result[i + 1] = arr[i];
        return result;
    }

    // 3. DELETE AT POSITION
    public static int[] deleteAt(int[] arr, int pos) {
        int[] result = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++)
            if (i != pos) result[j++] = arr[i];
        return result;
    }

    // 4. REVERSE
    public static void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) { int tmp = arr[l]; arr[l] = arr[r]; arr[r] = tmp; l++; r--; }
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) { int tmp = arr[l]; arr[l] = arr[r]; arr[r] = tmp; l++; r--; }
    }

    // 5. LEFT ROTATE BY K (using reversal trick)
    public static void leftRotate(int[] arr, int k) {
        k = k % arr.length;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    // 6. MAX & MIN
    public static int max(int[] arr) { int m = arr[0]; for (int x : arr) if (x > m) m = x; return m; }
    public static int min(int[] arr) { int m = arr[0]; for (int x : arr) if (x < m) m = x; return m; }

    // 7. SECOND LARGEST
    public static int secondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > first) { second = first; first = x; }
            else if (x > second && x != first) second = x;
        }
        return second;
    }

    // 8. CHECK SORTED
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) if (arr[i] < arr[i-1]) return false;
        return true;
    }

    // 9. REMOVE DUPLICATES (sorted array in-place)
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        int idx = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] != arr[idx]) arr[++idx] = arr[i];
        return idx + 1;
    }

    // 10. PREFIX SUM
    public static int[] prefixSum(int[] arr) {
        int[] p = new int[arr.length]; p[0] = arr[0];
        for (int i = 1; i < arr.length; i++) p[i] = p[i-1] + arr[i];
        return p;
    }
    public static int rangeSum(int[] prefix, int l, int r) {
        return l == 0 ? prefix[r] : prefix[r] - prefix[l-1];
    }

    // 11. KADANE'S ALGORITHM - Max Subarray Sum
    public static int maxSubarraySum(int[] arr) {
        int maxSum = arr[0], curr = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }

    // 12. SLIDING WINDOW - Max sum of window size k
    public static int maxSumWindow(int[] arr, int k) {
        int sum = 0; for (int i = 0; i < k; i++) sum += arr[i];
        int max = sum;
        for (int i = k; i < arr.length; i++) { sum += arr[i] - arr[i-k]; max = Math.max(max, sum); }
        return max;
    }

    // 13. TWO POINTER - Pair with target sum (sorted input)
    public static boolean hasPairWithSum(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) return true;
            else if (sum < target) l++; else r--;
        }
        return false;
    }

    // 14. MOVE ZEROS TO END
    public static void moveZeros(int[] arr) {
        int pos = 0;
        for (int x : arr) if (x != 0) arr[pos++] = x;
        while (pos < arr.length) arr[pos++] = 0;
    }

    // 15. 2D MATRIX - Print & Row Sums
    public static void print2D(int[][] m) {
        for (int[] row : m) { for (int v : row) System.out.printf("%4d", v); System.out.println(); }
    }
    public static int[] rowSums(int[][] m) {
        int[] s = new int[m.length];
        for (int i = 0; i < m.length; i++) for (int v : m[i]) s[i] += v;
        return s;
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== ARRAY OPERATIONS ===\n");
        int[] arr = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        traverse(arr);
        System.out.println("Max: " + max(arr) + " | Min: " + min(arr));
        System.out.println("Second Largest: " + secondLargest(arr));

        int[] sorted = arr.clone(); Arrays.sort(sorted);
        System.out.println("Sorted: " + Arrays.toString(sorted));

        reverse(arr); System.out.print("Reversed: "); traverse(arr);
        leftRotate(arr, 2); System.out.print("Left Rotated by 2: "); traverse(arr);

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Kadane Max Subarray Sum: " + maxSubarraySum(nums));

        int[] win = {1, 4, 2, 9, 7, 3, 8};
        System.out.println("Max window sum (k=3): " + maxSumWindow(win, 3));

        int[] tp = {1, 2, 3, 4, 5, 6};
        System.out.println("Pair with sum 9 exists: " + hasPairWithSum(tp, 9));

        int[] pArr = {2, 4, 6, 8, 10};
        int[] prefix = prefixSum(pArr);
        System.out.println("Prefix: " + Arrays.toString(prefix));
        System.out.println("Range sum [1,3]: " + rangeSum(prefix, 1, 3));

        System.out.println("\n2D Matrix:");
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        print2D(matrix);
        System.out.println("Row sums: " + Arrays.toString(rowSums(matrix)));
    }
}
