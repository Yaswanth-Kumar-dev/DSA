# DSA Java — Complete Data Structures & Algorithms

Eclipse-importable Java project covering all core DSA topics (excluding Trees & Graphs).

---

## 📁 Project Structure

```
DSA_Java/
├── src/
│   ├── arrays/          → ArrayOperations.java
│   ├── stack/           → StackOperations.java
│   ├── queue/           → QueueOperations.java
│   ├── linkedlist/      → LinkedListOperations.java
│   ├── sorting/         → SortingAlgorithms.java
│   ├── searching/       → SearchingAlgorithms.java
│   ├── hashing/         → HashingOperations.java
│   └── recursion/       → RecursionProblems.java
├── .project             (Eclipse config)
├── .classpath           (Eclipse config)
└── .gitignore
```

---

## 📚 Topics Covered

### 🔢 Arrays (`arrays/ArrayOperations.java`)
- Traversal, Insert, Delete at position
- Reverse, Left Rotate by K (reversal trick)
- Max, Min, Second Largest
- Check Sorted, Remove Duplicates
- Prefix Sum + Range Query
- Kadane's Algorithm (Max Subarray Sum)
- Sliding Window (Max sum of window size k)
- Two Pointer (Pair with target sum)
- Move Zeros to End
- 2D Matrix operations

### 📚 Stack (`stack/StackOperations.java`)
- Custom Array-based Stack (push, pop, peek)
- Balanced Parentheses checker
- Reverse a string using stack
- Next Greater Element (O(n))
- Stock Span Problem
- Infix to Postfix conversion
- Postfix Expression Evaluator

### 📬 Queue (`queue/QueueOperations.java`)
- Custom Array-based Circular Queue
- Circular Queue class (LeetCode-style)
- Queue using Two Stacks
- Generate Binary Numbers using Queue
- Sliding Window Maximum (Deque)
- Priority Queue (Min-Heap & Max-Heap)
- Deque (Double Ended Queue)

### 🔗 LinkedList (`linkedlist/LinkedListOperations.java`)
- **Singly**: Insert/Delete (front, end, position), Search, Reverse
- Floyd's: Find Middle, Detect Cycle
- Remove Nth node from end
- Merge Two Sorted Lists
- Palindrome check
- **Doubly**: Insert/Delete, Forward & Backward display
- **Circular**: Insert, Delete, Display

### 🔃 Sorting (`sorting/SortingAlgorithms.java`)
| Algorithm | Time | Space |
|---|---|---|
| Bubble Sort | O(n²) | O(1) |
| Selection Sort | O(n²) | O(1) |
| Insertion Sort | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n) |
| Quick Sort | O(n log n) avg | O(log n) |
| Counting Sort | O(n+k) | O(k) |
| Heap Sort | O(n log n) | O(1) |

### 🔍 Searching (`searching/SearchingAlgorithms.java`)
- Linear Search
- Binary Search (iterative + recursive)
- First & Last Occurrence
- Search in Rotated Sorted Array
- Square Root using Binary Search
- Find Peak Element
- Count Occurrences

### #️⃣ Hashing (`hashing/HashingOperations.java`)
- Custom HashMap (chaining)
- Frequency Count
- Two Sum (with indices)
- Anagram Check
- First Non-Repeating Character
- Longest Subarray with Sum K
- Group Anagrams
- Intersection of Two Arrays
- Subarray with Zero Sum

### 🔁 Recursion (`recursion/RecursionProblems.java`)
- Factorial, Fibonacci, Power (fast exponentiation)
- GCD (Euclidean), Sum of Digits
- Palindrome Check, Reverse String
- Tower of Hanoi
- Generate All Subsets (Power Set)
- Permutations
- Subset Sum Problem
- Count Ways to Climb Stairs

---

## 🚀 How to Import in Eclipse

1. Open Eclipse → `File` → `Import`
2. Choose `General` → `Existing Projects into Workspace`
3. Click `Browse` → select the `DSA_Java` folder
4. Click `Finish`
5. Right-click any `.java` file → `Run As` → `Java Application`

---

## 🐙 Push to GitHub

```bash
cd DSA_Java
git init
git add .
git commit -m "Initial commit: DSA in Java - Arrays, Stack, Queue, LinkedList, Sorting, Searching, Hashing, Recursion"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/DSA_Java.git
git push -u origin main
```

---

## ▶️ Run from Terminal

```bash
cd DSA_Java/src
javac arrays/ArrayOperations.java && java arrays.ArrayOperations
javac stack/StackOperations.java && java stack.StackOperations
javac queue/QueueOperations.java && java queue.QueueOperations
javac linkedlist/LinkedListOperations.java && java linkedlist.LinkedListOperations
javac sorting/SortingAlgorithms.java && java sorting.SortingAlgorithms
javac searching/SearchingAlgorithms.java && java searching.SearchingAlgorithms
javac hashing/HashingOperations.java && java hashing.HashingOperations
javac recursion/RecursionProblems.java && java recursion.RecursionProblems
```
