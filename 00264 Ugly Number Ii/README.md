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
An **ugly number** is a positive integer whose prime factors are limited to 2, 3, and 5. The problem asks us to find the `n`th ugly number.

For example, 1 is an ugly number. The first few ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

**Constraints:**
*   `1 <= n <= 1690`
*   The `n`th ugly number is guaranteed to fit in a 32-bit signed integer.

## 💡 Problem Explanation
The core idea is to generate ugly numbers in increasing order. Since ugly numbers are formed by multiplying previous ugly numbers by 2, 3, or 5, we can use a data structure to keep track of potential next ugly numbers and always pick the smallest one. This greedy approach ensures we build the sequence correctly. The main challenge is efficiently finding the next smallest ugly number and avoiding duplicates.

## 🧪 Examples
**Example 1:**
*   **Input:** `n = 10`
*   **Output:** `12`
*   **Walkthrough:**
    The first 10 ugly numbers are:
    1.  1 (base case)
    2.  2 (1 * 2)
    3.  3 (1 * 3)
    4.  4 (2 * 2)
    5.  5 (1 * 5)
    6.  6 (2 * 3 or 3 * 2)
    7.  8 (4 * 2)
    8.  9 (3 * 3)
    9.  10 (2 * 5 or 5 * 2)
    10. 12 (4 * 3 or 6 * 2)
    Thus, the 10th ugly number is 12.

## 🧭 Algorithm Overview
This problem can be effectively solved using a Min-Heap (PriorityQueue) combined with a Set to manage generated ugly numbers.

*   **Initialization:** Start with `1` as the first ugly number, adding it to both a min-heap and a set (`seen`) to track unique numbers.
*   **Iterative Generation:** Loop `n` times. In each iteration:
    1.  Extract the smallest number (`uglyNumber`) from the min-heap. This `uglyNumber` is the next in the sequence.
    2.  For each prime factor (2, 3, 5), multiply `uglyNumber` by the factor to generate `nextUglyNumber`.
    3.  If `nextUglyNumber` has not been seen before (check using the `seen` set), add it to both the `seen` set and the min-heap.
*   **Result:** After `n` iterations, the last `uglyNumber` extracted from the heap is the `n`th ugly number.

**Why this approach?**
This approach is chosen because:
1.  **Guaranteed Order:** A min-heap naturally provides the smallest available ugly number at each step, ensuring the sequence is generated in increasing order.
2.  **Efficiency:** It avoids checking every number for primality. Instead, it only considers numbers that are multiples of existing ugly numbers by 2, 3, or 5, which significantly prunes the search space.
3.  **Duplicate Handling:** The `Set` efficiently handles duplicates (e.g., 6 can be `2*3` or `3*2`) by ensuring each unique ugly number is added to the heap only once.

## 🧱 Variables & Data Structures
| Variable/Structure | Type                          | Description                                                                                             |
| :----------------- | :---------------------------- | :------------------------------------------------------------------------------------------------------ |
| `n`                | `int`                         | The input integer, representing the `n`th ugly number to find.                                          |
| `minHeap`          | `PriorityQueue<Long>`         | A min-heap to store candidate ugly numbers. Always extracts the smallest element. Uses `Long` to prevent overflow during multiplication before casting back to `int`. |
| `seen`             | `HashSet<Long>`               | A set to keep track of all ugly numbers that have been generated and added to `minHeap` (or processed). Prevents duplicates. |
| `factors`          | `long[]`                      | An array `[2, 3, 5]` containing the prime factors allowed for ugly numbers.                             |
| `uglyNumber`       | `long`                        | Stores the current `n`th ugly number popped from the `minHeap`.                                         |
| `next`             | `long`                        | Temporary variable to store the result of `uglyNumber * factor`.                                        |
| `i`                | `int`                         | Loop counter, iterating `n` times to find the `n`th ugly number.                                       |

## 🔢 Step-by-Step Breakdown
Let's find the **7th ugly number** (`n = 7`) using the algorithm.

1.  **Initialization:**
    *   `minHeap = {1L}`
    *   `seen = {1L}`
    *   `factors = {2, 3, 5}`
    *   `uglyNumber = 1L` (initial dummy value)

2.  **Iteration 1 (i=0):**
    *   `uglyNumber = minHeap.poll()` which is `1L`.
    *   Generate next candidates:
        *   `1 * 2 = 2L`. `seen.add(2L)` is true. `minHeap.add(2L)`.
        *   `1 * 3 = 3L`. `seen.add(3L)` is true. `minHeap.add(3L)`.
        *   `1 * 5 = 5L`. `seen.add(5L)` is true. `minHeap.add(5L)`.
    *   `minHeap = {2L, 3L, 5L}` (sorted by heap property)
    *   `seen = {1L, 2L, 3L, 5L}`

3.  **Iteration 2 (i=1):**
    *   `uglyNumber = minHeap.poll()` which is `2L`.
    *   Generate next candidates:
        *   `2 * 2 = 4L`. `seen.add(4L)` is true. `minHeap.add(4L)`.
        *   `2 * 3 = 6L`. `seen.add(6L)` is true. `minHeap.add(6L)`.
        *   `2 * 5 = 10L`. `seen.add(10L)` is true. `minHeap.add(10L)`.
    *   `minHeap = {3L, 4L, 5L, 6L, 10L}`
    *   `seen = {1L, 2L, 3L, 4L, 5L, 6L, 10L}`

4.  **Iteration 3 (i=2):**
    *   `uglyNumber = minHeap.poll()` which is `3L`.
    *   Generate next candidates:
        *   `3 * 2 = 6L`. `seen.add(6L)` is false (already seen). Do not add to heap.
        *   `3 * 3 = 9L`. `seen.add(9L)` is true. `minHeap.add(9L)`.
        *   `3 * 5 = 15L`. `seen.add(15L)` is true. `minHeap.add(15L)`.
    *   `minHeap = {4L, 5L, 6L, 9L, 10L, 15L}`
    *   `seen = {1L, 2L, 3L, 4L, 5L, 6L, 9L, 10L, 15L}`

5.  **Iteration 4 (i=3):**
    *   `uglyNumber = minHeap.poll()` which is `4L`.
    *   Generate next candidates:
        *   `4 * 2 = 8L`. `seen.add(8L)` is true. `minHeap.add(8L)`.
        *   `4 * 3 = 12L`. `seen.add(12L)` is true. `minHeap.add(12L)`.
        *   `4 * 5 = 20L`. `seen.add(20L)` is true. `minHeap.add(20L)`.
    *   `minHeap = {5L, 6L, 8L, 9L, 10L, 12L, 15L, 20L}`
    *   `seen = {..., 8L, 12L, 20L}`

6.  **Iteration 5 (i=4):**
    *   `uglyNumber = minHeap.poll()` which is `5L`.
    *   Generate next candidates:
        *   `5 * 2 = 10L`. `seen.add(10L)` is false.
        *   `5 * 3 = 15L`. `seen.add(15L)` is false.
        *   `5 * 5 = 25L`. `seen.add(25L)` is true. `minHeap.add(25L)`.
    *   `minHeap = {6L, 8L, 9L, 10L, 12L, 15L, 20L, 25L}`
    *   `seen = {..., 25L}`

7.  **Iteration 6 (i=5):**
    *   `uglyNumber = minHeap.poll()` which is `6L`.
    *   Generate next candidates:
        *   `6 * 2 = 12L`. `seen.add(12L)` is false.
        *   `6 * 3 = 18L`. `seen.add(18L)` is true. `minHeap.add(18L)`.
        *   `6 * 5 = 30L`. `seen.add(30L)` is true. `minHeap.add(30L)`.
    *   `minHeap = {8L, 9L, 10L, 12L, 15L, 18L, 20L, 25L, 30L}`
    *   `seen = {..., 18L, 30L}`

8.  **Iteration 7 (i=6):**
    *   `uglyNumber = minHeap.poll()` which is `8L`.
    *   This is the 7th ugly number we extracted. The loop finishes.

**Result:** The final `uglyNumber` is `8L`. The 7th ugly number is 8.

## 🧰 Programming Workflow
1.  **Initialize Data Structures:** Create a `PriorityQueue` (`minHeap`) for candidates and a `HashSet` (`seen`) for tracking unique numbers. Define the prime `factors` array.
2.  **Seed the Process:** Add `1L` to both `minHeap` and `seen` as it's the first ugly number.
3.  **Iterate `n` Times:** Loop from `i = 0` to `n - 1`.
4.  **Extract Current Ugly Number:** In each iteration, `poll()` the smallest number from `minHeap`. This is the `uglyNumber` for the current step.
5.  **Generate Next Candidates:** For each `factor` in `[2, 3, 5]`:
    *   Calculate `next = uglyNumber * factor`.
    *   **Check for Duplicates:** Use `seen.add(next)` which returns `true` if `next` was not present and `false` otherwise.
    *   **Add to Heap (if new):** If `next` is truly new (i.e., `seen.add(next)` returned `true`), add `next` to `minHeap`.
6.  **Return Result:** After the loop completes, the last `uglyNumber` extracted is the `n`th ugly number. Cast it to `int` before returning.

```mermaid
graph TD
    A[Start] --> B{Initialize minHeap={1}, seen={1}, factors={2,3,5}};
    B --> C{Loop for i = 0 to n-1};
    C -- Yes --> D[uglyNumber = minHeap.poll()];
    D --> E{For each factor in factors (2,3,5)};
    E -- Yes --> F[next = uglyNumber * factor];
    F --> G{Is next in seen?};
    G -- No --> H[Add next to seen and minHeap];
    G -- Yes --> E;
    H --> E;
    E -- No more factors --> C;
    C -- No, loop ends --> I[Return (int)uglyNumber];
    I --> J[End];
```
```
+--------------------------+
|          Start           |
+--------------------------+
            |
            v
+--------------------------+
| Initialize minHeap = {1} |
| seen = {1}               |
| factors = {2, 3, 5}      |
+--------------------------+
            |
            v
+--------------------------+
|    Loop for i = 0 to n-1 |
+--------------------------+
            |
            v  (Yes)
+--------------------------+
| uglyNumber = minHeap.poll() |
+--------------------------+
            |
            v
+--------------------------+
|  For each factor in      |
|  factors (2, 3, 5)       |
+--------------------------+
   |             |
   v             v
+--------------------------+
| next = uglyNumber * factor |
+--------------------------+
            |
            v
+--------------------------+
|   Is next in 'seen'?     |
+--------------------------+
   | (No)          | (Yes)
   v               v
+--------------------------+   +--------------------------+
| Add 'next' to 'seen' and |   |       Skip 'next'        |
| 'minHeap'                |   | (already processed/pending)|
+--------------------------+   +--------------------------+
   |                           |
   +----------->---------------+
            |
            v
+--------------------------+
| More factors?            |
+--------------------------+
   | (Yes)         | (No)
   +---------------+
            |
            v
+--------------------------+
| Loop 'i' continues?      |
+--------------------------+
   | (Yes)         | (No)
   +---------------+
            |
            v
+--------------------------+
| Return (int)uglyNumber   |
+--------------------------+
            |
            v
+--------------------------+
|           End            |
+--------------------------+
```

## 🔥 Code Implementation
```java
class Solution {
    public int nthUglyNumber(int n) {
        // Use a Min-Heap (PriorityQueue) to store candidate ugly numbers.
        // We use Long to prevent potential overflow during multiplication
        // before casting back to int at the end.
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        
        // Use a Set to keep track of ugly numbers already seen/added to the heap.
        // This prevents adding duplicates and ensures unique ugly numbers.
        Set<Long> seen = new HashSet<>();
        
        // The prime factors that define an ugly number.
        long[] factors = {2, 3, 5};

        // The first ugly number is 1. Add it to both heap and set.
        minHeap.add(1L);
        seen.add(1L);

        // This variable will store the current ugly number extracted from the heap.
        long uglyNumber = 1L; 
        
        // We need to find the n-th ugly number.
        // We iterate n times, each time extracting the smallest ugly number
        // and generating its multiples.
        for (int i = 0; i < n; i++) {
            // Extract the smallest ugly number from the heap.
            uglyNumber = minHeap.poll();
            
            // For each prime factor (2, 3, 5):
            for (long factor : factors) {
                // Calculate the next potential ugly number.
                long next = uglyNumber * factor;
                
                // If this 'next' ugly number has not been seen before,
                // add it to the 'seen' set and the 'minHeap'.
                // seen.add(next) returns true if the element was not present, false otherwise.
                if (seen.add(next)) {
                    minHeap.add(next);
                }
            }
        }

        // After n iterations, 'uglyNumber' will hold the n-th ugly number.
        // Cast it back to int as per problem constraints.
        return (int) uglyNumber;  
    }
}

```

## 🗂️ Table Representation & 🧪 Working Demo

Let's trace for `n = 7` to see the heap and set evolution.

| Iteration `i` | `uglyNumber` (popped) | Multiples generated (`uglyNumber * factor`) | `seen.add()` status | `minHeap` (state after iteration) | `seen` (state after iteration) |
| :------------ | :-------------------- | :------------------------------------------ | :------------------ | :-------------------------------- | :----------------------------- |
| **Initial**   | N/A                   | N/A                                         | N/A                 | `{1}`                             | `{1}`                          |
| `i = 0`       | `1`                   | `1*2=2`, `1*3=3`, `1*5=5`                   | `T, T, T`           | `{2, 3, 5}`                       | `{1, 2, 3, 5}`                 |
| `i = 1`       | `2`                   | `2*2=4`, `2*3=6`, `2*5=10`                  | `T, T, T`           | `{3, 4, 5, 6, 10}`                | `{1, 2, 3, 4, 5, 6, 10}`       |
| `i = 2`       | `3`                   | `3*2=6`, `3*3=9`, `3*5=15`                  | `F, T, T`           | `{4, 5, 6, 9, 10, 15}`            | `{1..6, 9, 10, 15}`            |
| `i = 3`       | `4`                   | `4*2=8`, `4*3=12`, `4*5=20`                 | `T, T, T`           | `{5, 6, 8, 9, 10, 12, 15, 20}`    | `{1..6, 8..10, 12, 15, 20}`    |
| `i = 4`       | `5`                   | `5*2=10`, `5*3=15`, `5*5=25`                | `F, F, T`           | `{6, 8, 9, 10, 12, 15, 20, 25}`   | `{1..6, 8..10, 12, 15, 20, 25}`|
| `i = 5`       | `6`                   | `6*2=12`, `6*3=18`, `6*5=30`                | `F, T, T`           | `{8, 9, 10, 12, 15, 18, 20, 25, 30}`| `{1..6, 8..10, 12, 15, 18, 20, 25, 30}`|
| `i = 6`       | `8`                   | `8*2=16`, `8*3=24`, `8*5=40`                | `T, T, T`           | `{9, 10, 12, 15, 16, 18, 20, 24, 25, 30, 40}`| `{..., 16, 24, 40}`|
| **Result**    | `8` (last popped)     | N/A                                         | N/A                 | N/A                               | N/A                            |

The 7th ugly number is **8**.

## 🚀 Time & Space Complexity
*   **Time Complexity:** `O(N log N)`
    *   We perform `N` iterations. In each iteration:
        *   `minHeap.poll()` takes `O(log K)` time, where `K` is the number of elements in the heap. In the worst case, `K` can be up to `N * 3` (or `3N` to be precise, as each extracted ugly number adds at most 3 new candidates). So, `O(log (3N))` which simplifies to `O(log N)`.
        *   We then iterate through `3` factors.
        *   `seen.add()` takes `O(1)` on average for a `HashSet`.
    *   Overall: `N * (O(log N) + 3 * O(1))` which is `O(N log N)`.

*   **Space Complexity:** `O(N)`
    *   The `minHeap` can store up to `N` elements in the worst case (e.g., if all generated numbers are unique before the `N`th one is found).
    *   The `seen` set can also store up to `N` unique ugly numbers.
    *   Therefore, the space complexity is dominated by the heap and set, resulting in `O(N)`.

## 🔗 References
*   **Heap (Priority Queue):** [GeeksforGeeks - Priority Queue](https://www.geeksforgeeks.org/priority-queue-in-java/)
*   **Hash Set:** [GeeksforGeeks - HashSet in Java](https://www.geeksforgeeks.org/hashset-in-java/)
*   **Ugly Number Problem (similar approaches):** Often, a three-pointer dynamic programming approach is also used for this problem. While the heap solution is more generalizable, the three-pointer method (often seen for "Ugly Number III") can be `O(N)` time. This solution uses the heap, which is a common and intuitive approach.
    *   [LeetCode Solution Article for Ugly Number II](https://leetcode.com/problems/ugly-number-ii/solutions/) (Check for other approaches like DP)
    