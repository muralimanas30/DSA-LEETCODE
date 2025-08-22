# 00264 - Ugly Number Ii
    
**Language:** Java  
**Runtime:** 48 ms (Beats 31.35% of users)  
**Memory:** 44.6 MB (Beats 27.47% of users)  

## 📝 LeetCode Problem
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 264 | UGLY NUMBER II | [LeetCode Problem](https://leetcode.com/problems/ugly-number-ii/) |

---

## 🧩 Problem Statement
An **ugly number** is a positive integer whose prime factors are limited to `2`, `3`, and `5`. Given an integer `n`, return the `n`-th ugly number.

The first ugly number is `1`.

**Constraints:**
*   `1 <= n <= 1690`
*   The `n`-th ugly number is guaranteed to fit in a 32-bit signed integer.

**Statistics (as of problem creation/last update):**
*   **Difficulty:** Medium

## 💡 Problem Explanation
The core idea is to understand what constitutes an ugly number. Any ugly number can be formed by multiplying a previous ugly number by `2`, `3`, or `5`. For example, `1` is ugly. Then `1*2=2`, `1*3=3`, `1*5=5` are ugly. From `2`, we get `2*2=4`, `2*3=6`, `2*5=10`. The challenge is to find the *n*-th smallest among all these generated numbers efficiently, ensuring no duplicates and maintaining sorted order.

## 🧪 Examples

**Example 1:**
```
Input: n = 1
Output: 1
Explanation: 1 is the first ugly number.
```

**Example 2:**
```
Input: n = 10
Output: 12
Explanation: The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12.
```

## 🧭 Algorithm Overview
This problem can be efficiently solved using a **Min-Heap (Priority Queue)** combined with a **HashSet** to manage generated ugly numbers.

### Key Steps:
*   🏁 Initialize a min-heap with `1` (as `1` is the first ugly number).
*   📚 Use a hash set to keep track of all ugly numbers seen so far to avoid duplicates and redundant calculations. Add `1` to this set as well.
*   🔄 Iterate `n` times:
    *   Extract the smallest ugly number from the min-heap. This is our current `uglyNumber`.
    *   For each of the prime factors (`2`, `3`, `5`):
        *   Multiply `uglyNumber` by the factor to generate a `nextUgly`.
        *   If `nextUgly` has not been seen before (check using the hash set), add it to both the hash set and the min-heap.
*   🎯 After `n` iterations, the last `uglyNumber` extracted from the heap will be the `n`-th ugly number.

### Why this approach?
*   **Min-Heap**: Guarantees that we always process the smallest available ugly number next, ensuring we generate ugly numbers in ascending order.
*   **HashSet**: Prevents duplicate numbers from being added to the heap. Without it, we would add numbers like `2*3=6` and `3*2=6` multiple times, leading to inefficiency and incorrect ordering if duplicates were not handled.
*   This approach is a variant of **Dijkstra's algorithm** or **Prim's algorithm** on an implicit graph where nodes are ugly numbers and edges are multiplications by factors 2, 3, 5, always exploring the smallest next value.

## 🧱 Variables & Data Structures
| Variable/Data Structure | Type                                | Description                                                                                                                              |
| :---------------------- | :---------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------- |
| `minHeap`               | `PriorityQueue<Long>`               | Stores candidate ugly numbers in ascending order. `Long` is used to prevent overflow during multiplication before casting back to `int`. |
| `seen`                  | `Set<Long>`                         | Stores all unique ugly numbers that have been added to the `minHeap` to prevent duplicates.                                              |
| `factors`               | `long[]` (initialized with `{2, 3, 5}`) | An array containing the prime factors used to generate new ugly numbers.                                                                 |
| `n`                     | `int`                               | The input integer, representing the `n`-th ugly number we need to find.                                                                |
| `uglyNumber`            | `long`                              | Stores the current smallest ugly number popped from `minHeap` in each iteration. This will be the result after `n` iterations.          |
| `next`                  | `long`                              | Temporary variable to store the result of `uglyNumber * factor`.                                                                       |

## 🧰 Programming Workflow
This section outlines the logical flow of the Java implementation.

### 1. Numbered List Workflow
1.  **Initialization**:
    *   Create an empty `PriorityQueue<Long>` named `minHeap`.
    *   Create an empty `HashSet<Long>` named `seen`.
    *   Define an array `factors` containing `2L`, `3L`, and `5L`.
    *   Add `1L` to both `minHeap` and `seen` (as `1` is the first ugly number).
2.  **Iteration**: Loop `n` times to find the `n`-th ugly number.
    *   **Extract Smallest**: In each iteration, retrieve and remove the smallest element from `minHeap`. Store this value in `uglyNumber`.
    *   **Generate Next Ugly Numbers**: For each `factor` in the `factors` array:
        *   Calculate `next = uglyNumber * factor`.
        *   **Check for Duplicates**: Use `seen.add(next)` which returns `true` if `next` was not already in the set and adds it, or `false` if `next` was already present.
        *   **Add to Heap**: If `seen.add(next)` returns `true` (meaning `next` is a new ugly number), add `next` to `minHeap`.
3.  **Return Result**: After the loop finishes, `uglyNumber` will hold the `n`-th ugly number. Cast it to `int` and return it.

### 2. ASCII Flowchart
```
+------------------+
|      START       |
+------------------+
        |
        V
+------------------+
| Init minHeap = {1}|
| Init seen = {1}  |
| Init factors = {2,3,5}|
+------------------+
        |
        V
+------------------+
|  loop i from 0   |
|     to n-1       |
+------------------+
        |
        V
+------------------+
| uglyNumber =     |
|   minHeap.poll() |
+------------------+
        |
        V
+------------------+
| For each factor  |
|    in factors    |
+------------------+
        |
        V
+------------------+
|  next =          |
|  uglyNumber *    |
|    factor        |
+------------------+
        |
        V
+------------------+
|  IF seen.add(next) |
|    returns TRUE  |
+------------------+
        |  YES        | NO
        V            V
+------------------+ +------------------+
| minHeap.add(next)| |     (Skip)       |
+------------------+ +------------------+
        |            |
        +------------+
        |
        V
+------------------+
|  (End For loop)  |
+------------------+
        |
        V
+------------------+
| (End Main loop)  |
+------------------+
        |
        V
+------------------+
| Return (int)     |
|   uglyNumber     |
+------------------+
        |
        V
+------------------+
|       END        |
+------------------+
```

## 🔥 Code Implementation
```java
class Solution {
    public int nthUglyNumber(int n) {
        // Use a min-priority queue to store ugly numbers and retrieve the smallest one efficiently.
        // Using Long to prevent potential overflow during multiplication (e.g., 5 * MAX_INT / 2 > MAX_INT)
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        
        // Use a hash set to keep track of all ugly numbers added to the heap
        // This prevents duplicates and ensures each number is processed only once.
        Set<Long> seen = new HashSet<>();
        
        // The prime factors for generating ugly numbers
        long[] factors = {2, 3, 5};

        // Initialize with the first ugly number (1)
        minHeap.add(1L);
        seen.add(1L);

        long uglyNumber = 1L; // Variable to store the current ugly number extracted
        
        // Loop n times to find the n-th ugly number
        for (int i = 0; i < n; i++) {
            // Get the smallest ugly number from the heap
            uglyNumber = minHeap.poll();
            
            // For each prime factor, generate the next potential ugly numbers
            for (long factor : factors) {
                long next = uglyNumber * factor;
                
                // If this 'next' number has not been seen before,
                // add it to the set and the heap.
                if (seen.add(next)) { // seen.add() returns true if the element was not present
                    minHeap.add(next);
                }
            }
        }

        // After n iterations, uglyNumber will hold the n-th ugly number.
        // Cast it back to int as per problem constraints.
        return (int) uglyNumber;  
    }
}

```

## 🧪 Working Demo & Step-by-Step Walkthrough
Let's trace the algorithm with `n = 10` to find the 10th ugly number.

**Initialization:**
*   `minHeap = {1}`
*   `seen = {1}`
*   `factors = {2, 3, 5}`
*   `uglyNumber = 1`

| Iteration `i` | `minHeap` (before poll) | `uglyNumber` (polled) | `next` candidates (factor: val) | `seen` (after adds)       | `minHeap` (after adds)                |
| :------------ | :---------------------- | :-------------------- | :------------------------------ | :------------------------ | :------------------------------------ |
| **0**         | `{1}`                   | `1`                   | `2: 2`, `3: 3`, `5: 5`          | `{1, 2, 3, 5}`            | `{2, 3, 5}`                           |
| **1**         | `{2, 3, 5}`             | `2`                   | `2: 4`, `3: 6`, `5: 10`         | `{1, 2, 3, 4, 5, 6, 10}`  | `{3, 4, 5, 6, 10}`                    |
| **2**         | `{3, 4, 5, 6, 10}`      | `3`                   | `2: 6` (seen), `3: 9`, `5: 15`  | `{..., 9, 15}`            | `{4, 5, 6, 9, 10, 15}`                |
| **3**         | `{4, 5, 6, 9, 10, 15}`  | `4`                   | `2: 8`, `3: 12`, `5: 20`        | `{..., 8, 12, 20}`        | `{5, 6, 8, 9, 10, 12, 15, 20}`        |
| **4**         | `{5, 6, 8, 9, 10, 12, 15, 20}` | `5`                   | `2: 10` (seen), `3: 15` (seen), `5: 25` | `{..., 25}`               | `{6, 8, 9, 10, 12, 15, 20, 25}`       |
| **5**         | `{6, 8, 9, 10, 12, 15, 20, 25}` | `6`                   | `2: 12` (seen), `3: 18`, `5: 30`| `{..., 18, 30}`           | `{8, 9, 10, 12, 15, 18, 20, 25, 30}`  |
| **6**         | `{8, 9, 10, 12, 15, 18, 20, 25, 30}` | `8`                   | `2: 16`, `3: 24`, `5: 40`       | `{..., 16, 24, 40}`       | `{9, 10, 12, 15, 16, 18, 20, 24, 25, 30, 40}` |
| **7**         | `{9, 10, 12, 15, 16, 18, 20, 24, 25, 30, 40}` | `9`                   | `2: 18` (seen), `3: 27`, `5: 45`| `{..., 27, 45}`           | `{10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 40, 45}` |
| **8**         | `{10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 40, 45}` | `10`                  | `2: 20` (seen), `3: 30` (seen), `5: 50`| `{..., 50}`               | `{12, 15, 16, 18, 20, 24, 25, 27, 30, 40, 45, 50}` |
| **9**         | `{12, 15, 16, 18, 20, 24, 25, 27, 30, 40, 45, 50}` | `12`                  | `2: 24` (seen), `3: 36`, `5: 60`| `{..., 36, 60}`           | `{15, 16, 18, 20, 24, 25, 27, 30, 36, 40, 45, 50, 60}` |

After 10 iterations (i=0 to 9), the `uglyNumber` variable holds `12`.
Therefore, the 10th ugly number is **12**. ✅

## 🚀 Time & Space Complexity

*   **Time Complexity**: `O(N log N)`
    *   We perform `N` iterations to find the `n`-th ugly number.
    *   In each iteration, we perform a `poll()` operation on the `minHeap` (which takes `O(log K)` time, where `K` is the size of the heap).
    *   We also perform up to 3 `add()` operations (for factors 2, 3, 5), each taking `O(log K)` time.
    *   The maximum size of the heap `K` can be up to `3N` (if all `N` ugly numbers generate 3 new unique candidates that are stored). Thus, `log K` is `log(3N)`, which simplifies to `O(log N)`.
    *   Therefore, the total time complexity is `N * O(log N) = O(N log N)`.

*   **Space Complexity**: `O(N)`
    *   The `minHeap` stores at most `3N` ugly numbers at any given time (roughly 3 times the count of ugly numbers already processed).
    *   The `seen` set stores all `N` ugly numbers generated up to that point.
    *   Both data structures contribute `O(N)` space.

## 🔗 References
*   [LeetCode Discussion - Solution using Min Heap](https://leetcode.com/problems/ugly-number-ii/discuss/69362/Java-Python-Solution-with-heap)
*   [GeeksForGeeks - Ugly Numbers](https://www.geeksforgeeks.org/ugly-numbers/)
*   This problem is also solvable using a Dynamic Programming approach with three pointers, which achieves `O(N)` time complexity: [LeetCode Official Solution - DP](https://leetcode.com/problems/ugly-number-ii/solution/) (While not used in this specific implementation, it's a valuable alternative to be aware of).
```
    