package sorting;

import java.util.Arrays;

/**
 * Sorting Algorithms DSA
 * Topics: Bubble, Selection, Insertion, Merge, Quick, Counting, Radix, Heap Sort
 */
public class SortingAlgorithms {

    // 1. BUBBLE SORT  O(n²)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j+1]) { int t = arr[j]; arr[j] = arr[j+1]; arr[j+1] = t; swapped = true; }
            }
            if (!swapped) break; // already sorted
        }
    }

    // 2. SELECTION SORT  O(n²)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) if (arr[j] < arr[minIdx]) minIdx = j;
            int t = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = t;
        }
    }

    // 3. INSERTION SORT  O(n²) best O(n)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) { arr[j+1] = arr[j]; j--; }
            arr[j+1] = key;
        }
    }

    // 4. MERGE SORT  O(n log n)
    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] left = Arrays.copyOfRange(arr, l, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length)
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // 5. QUICK SORT  O(n log n) avg
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) { i++; int t = arr[i]; arr[i] = arr[j]; arr[j] = t; }
        }
        int t = arr[i+1]; arr[i+1] = arr[high]; arr[high] = t;
        return i + 1;
    }

    // 6. COUNTING SORT  O(n+k)
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        for (int x : arr) count[x]++;
        int idx = 0;
        for (int i = 0; i <= max; i++) while (count[i]-- > 0) arr[idx++] = i;
    }

    // 7. HEAP SORT  O(n log n)
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int t = arr[0]; arr[0] = arr[i]; arr[i] = t;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2*i+1, r = 2*i+2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int t = arr[i]; arr[i] = arr[largest]; arr[largest] = t;
            heapify(arr, n, largest);
        }
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== SORTING ALGORITHMS ===\n");
        int[] original = {64, 25, 12, 22, 11, 90, 34, 55};

        int[] arr;

        arr = original.clone(); bubbleSort(arr);
        System.out.println("Bubble Sort:    " + Arrays.toString(arr));

        arr = original.clone(); selectionSort(arr);
        System.out.println("Selection Sort: " + Arrays.toString(arr));

        arr = original.clone(); insertionSort(arr);
        System.out.println("Insertion Sort: " + Arrays.toString(arr));

        arr = original.clone(); mergeSort(arr, 0, arr.length - 1);
        System.out.println("Merge Sort:     " + Arrays.toString(arr));

        arr = original.clone(); quickSort(arr, 0, arr.length - 1);
        System.out.println("Quick Sort:     " + Arrays.toString(arr));

        arr = original.clone(); countingSort(arr);
        System.out.println("Counting Sort:  " + Arrays.toString(arr));

        arr = original.clone(); heapSort(arr);
        System.out.println("Heap Sort:      " + Arrays.toString(arr));
    }
}
