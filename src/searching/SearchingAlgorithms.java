package searching;

/**
 * Searching Algorithms DSA
 * Topics: Linear Search, Binary Search (iterative + recursive),
 *         Search in Rotated Array, First & Last Occurrence,
 *         Square Root using Binary Search, Peak Element
 */
public class SearchingAlgorithms {

    // 1. LINEAR SEARCH  O(n)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == target) return i;
        return -1;
    }

    // 2. BINARY SEARCH (Iterative)  O(log n)
    public static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    // 3. BINARY SEARCH (Recursive)
    public static int binarySearchRec(int[] arr, int l, int r, int target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) return binarySearchRec(arr, mid + 1, r, target);
        else return binarySearchRec(arr, l, mid - 1, target);
    }

    // 4. FIRST OCCURRENCE
    public static int firstOccurrence(int[] arr, int target) {
        int l = 0, r = arr.length - 1, result = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) { result = mid; r = mid - 1; }
            else if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return result;
    }

    // 5. LAST OCCURRENCE
    public static int lastOccurrence(int[] arr, int target) {
        int l = 0, r = arr.length - 1, result = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) { result = mid; l = mid + 1; }
            else if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return result;
    }

    // 6. SEARCH IN ROTATED SORTED ARRAY
    public static int searchRotated(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            if (arr[l] <= arr[mid]) {
                if (target >= arr[l] && target < arr[mid]) r = mid - 1;
                else l = mid + 1;
            } else {
                if (target > arr[mid] && target <= arr[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    // 7. FIND SQUARE ROOT (integer) using binary search
    public static int sqrtBinarySearch(int n) {
        if (n < 2) return n;
        int l = 1, r = n / 2, result = 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid == n) return mid;
            else if ((long)mid * mid < n) { result = mid; l = mid + 1; }
            else r = mid - 1;
        }
        return result;
    }

    // 8. PEAK ELEMENT (element greater than both neighbors)
    public static int findPeakElement(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 9. COUNT OCCURRENCES of target in sorted array
    public static int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0;
        return lastOccurrence(arr, target) - first + 1;
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== SEARCHING ALGORITHMS ===\n");

        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Linear Search (12):    idx = " + linearSearch(arr, 12));
        System.out.println("Binary Search (12):    idx = " + binarySearch(arr, 12));
        System.out.println("Binary Search Rec(12): idx = " + binarySearchRec(arr, 0, arr.length-1, 12));
        System.out.println("Binary Search (99):    idx = " + binarySearch(arr, 99));

        int[] dup = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\nDuplicate array: " + java.util.Arrays.toString(dup));
        System.out.println("First occurrence of 2: " + firstOccurrence(dup, 2));
        System.out.println("Last occurrence of 2:  " + lastOccurrence(dup, 2));
        System.out.println("Count of 2: " + countOccurrences(dup, 2));

        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("\nRotated Array: " + java.util.Arrays.toString(rotated));
        System.out.println("Search 6: idx = " + searchRotated(rotated, 6));
        System.out.println("Search 0: idx = " + searchRotated(rotated, 0));

        System.out.println("\nSqrt(25) = " + sqrtBinarySearch(25));
        System.out.println("Sqrt(30) = " + sqrtBinarySearch(30));

        int[] peaks = {1, 3, 20, 4, 1, 0};
        System.out.println("\nPeak element index in " + java.util.Arrays.toString(peaks) + " = " + findPeakElement(peaks));
    }
}
