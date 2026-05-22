package hashing;

import java.util.*;

/**
 * Hashing DSA
 * Topics: Custom Hash Map, Frequency Count, Two Sum, Anagram Check,
 *         Longest Subarray with sum K, First Non-Repeating char,
 *         Group Anagrams, Intersection of Arrays
 */
public class HashingOperations {

    // ── 1. Custom HashMap (Chaining) ──
    static class MyHashMap {
        private static final int SIZE = 16;
        private LinkedList<int[]>[] table;

        @SuppressWarnings("unchecked")
        public MyHashMap() {
            table = new LinkedList[SIZE];
            for (int i = 0; i < SIZE; i++) table[i] = new LinkedList<>();
        }

        private int hash(int key) { return key % SIZE; }

        public void put(int key, int val) {
            int h = hash(key);
            for (int[] pair : table[h]) { if (pair[0] == key) { pair[1] = val; return; } }
            table[h].add(new int[]{key, val});
        }

        public int get(int key) {
            for (int[] pair : table[hash(key)]) if (pair[0] == key) return pair[1];
            return -1;
        }

        public void remove(int key) {
            table[hash(key)].removeIf(pair -> pair[0] == key);
        }

        public boolean containsKey(int key) { return get(key) != -1; }
    }

    // ── 2. FREQUENCY COUNT ──
    public static Map<Integer, Integer> frequencyCount(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) freq.put(x, freq.getOrDefault(x, 0) + 1);
        return freq;
    }

    // ── 3. TWO SUM (return indices) ──
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    // ── 4. CHECK ANAGRAM ──
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] count = new int[26];
        for (char c : a.toCharArray()) count[c - 'a']++;
        for (char c : b.toCharArray()) { if (--count[c - 'a'] < 0) return false; }
        return true;
    }

    // ── 5. FIRST NON-REPEATING CHARACTER ──
    public static char firstNonRepeating(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        for (Map.Entry<Character, Integer> e : freq.entrySet()) if (e.getValue() == 1) return e.getKey();
        return '#';
    }

    // ── 6. LONGEST SUBARRAY WITH SUM K ──
    public static int longestSubarrayWithSumK(int[] arr, int k) {
        Map<Integer, Integer> prefixIndex = new HashMap<>();
        int prefixSum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (prefixSum == k) maxLen = i + 1;
            if (prefixIndex.containsKey(prefixSum - k))
                maxLen = Math.max(maxLen, i - prefixIndex.get(prefixSum - k));
            if (!prefixIndex.containsKey(prefixSum)) prefixIndex.put(prefixSum, i);
        }
        return maxLen;
    }

    // ── 7. GROUP ANAGRAMS ──
    public static Map<String, List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return map;
    }

    // ── 8. INTERSECTION OF TWO ARRAYS ──
    public static int[] intersection(int[] a, int[] b) {
        Set<Integer> setA = new HashSet<>();
        for (int x : a) setA.add(x);
        Set<Integer> result = new HashSet<>();
        for (int x : b) if (setA.contains(x)) result.add(x);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // ── 9. SUBARRAY WITH ZERO SUM ──
    public static boolean hasSubarrayWithZeroSum(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int sum = 0;
        for (int x : arr) { sum += x; if (!seen.add(sum)) return true; }
        return false;
    }

    // ── MAIN ──
    public static void main(String[] args) {
        System.out.println("=== HASHING OPERATIONS ===\n");

        // Custom HashMap
        MyHashMap map = new MyHashMap();
        map.put(1, 100); map.put(2, 200); map.put(3, 300);
        System.out.println("MyHashMap get(2): " + map.get(2));
        map.remove(2);
        System.out.println("After remove(2), get(2): " + map.get(2));

        // Frequency
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        System.out.println("\nFrequency: " + frequencyCount(arr));

        // Two Sum
        int[] ts = {2, 7, 11, 15};
        System.out.println("TwoSum([2,7,11,15], target=9): " + Arrays.toString(twoSum(ts, 9)));

        // Anagram
        System.out.println("\nAnagram('listen','silent'): " + isAnagram("listen", "silent"));
        System.out.println("Anagram('hello','world'): " + isAnagram("hello", "world"));

        // First Non-Repeating
        System.out.println("\nFirst non-repeating in 'leetcode': " + firstNonRepeating("leetcode"));

        // Longest subarray sum K
        int[] la = {1, -1, 5, -2, 3};
        System.out.println("Longest subarray with sum 3: " + longestSubarrayWithSumK(la, 3));

        // Group Anagrams
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("\nGroup Anagrams: " + groupAnagrams(words));

        // Intersection
        int[] a = {1, 2, 2, 1}, b = {2, 2};
        System.out.println("Intersection: " + Arrays.toString(intersection(a, b)));

        // Zero Sum Subarray
        int[] zs = {1, 4, -2, -2, 5, -4, 3};
        System.out.println("Subarray with zero sum exists: " + hasSubarrayWithZeroSum(zs));
    }
}
