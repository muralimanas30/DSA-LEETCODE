# 00264 - Ugly Number Ii
    
**Language:** Java  
**Runtime:** 48 ms (Beats 31.35% of users)  
**Memory:** 44.6 MB (Beats 27.47% of users)  

# 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 264 | UGLY NUMBER II | [LeetCode Problem](https://leetcode.com/problems/ugly-number-ii/) |

---

## 💡 **Problem Explanation**

An **ugly number** is a positive integer whose prime factors are limited to `2`, `3`, and `5`. The problem asks us to find the `n`-th ugly number in the sequence.

The sequence of ugly numbers starts with `1`. The first few are:
`1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, ...`

> For example, `14` is not an ugly number because it has a prime factor of `7`. `6` is an ugly number because `6 = 2 × 3`.

**Sample Inputs & Outputs:**

*   **Input:** `n = 10`
*   **Output:** `12` (The 10th ugly number is 12)

*   **Input:** `n = 1`
*   **Output:** `1` (The 1st ugly number is 1)

---

## 📊 **Algorithm**

The core idea is to generate ugly numbers in ascending order and stop when we find the `n`-th one. We can think of this as a search problem where each ugly number `u` can generate three new potential ugly numbers: `u * 2`, `u * 3`, and `u * 5`.

We use a **Min-Heap** to always get the smallest available ugly number and a **Set** to avoid adding duplicate numbers to the heap.

*   **Initialization:**
    *   Create a `minHeap` to store potential ugly numbers, always keeping the smallest at the top.
    *   Create a `seen` set to track numbers we've already added to the heap, preventing duplicates.
    *   Start by adding the first ugly number, `1`, to both the `minHeap` and the `seen` set.

*   **Iteration:**
    *   Loop `n` times to find the `n`-th ugly number.
    *   In each iteration, extract the smallest number from the `minHeap`. This is our current ugly number.
    *   Generate new candidates by multiplying the current ugly number with the allowed factors (`2`, `3`, `5`).
    *   For each new candidate, if it hasn't been `seen` before, add it to both the `minHeap` and the `seen` set.

*   **Result:**
    *   After `n` iterations, the last number extracted from the heap is the `n`-th ugly number.

---

## 🔥 **Code Implementation**

```java
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int nthUglyNumber(int n) {
        // A min-heap to store ugly numbers and always get the smallest one.
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        
        // A set to keep track of numbers already in the heap to avoid duplicates.
        Set<Long> seen = new HashSet<>();
        
        // The prime factors for ugly numbers.
        long[] factors = {2, 3, 5};

        // Start with the first ugly number, 1.
        minHeap.add(1L);
        seen.add(1L);

        // This will hold the n-th ugly number at the end.
        long uglyNumber = 1L;

        // Loop n times to find the n-th ugly number.
        for (int i = 0; i < n; i++) {
            // Get the smallest ugly number from the heap.
            uglyNumber = minHeap.poll();
            
            // Generate next ugly numbers by multiplying with 2, 3, and 5.
            for (long factor : factors) {
                long next = uglyNumber * factor;
                // If we haven't seen this number before, add it to the heap and set.
                if (seen.add(next)) {
                    minHeap.add(next);
                }
            }
        }

        return (int) uglyNumber;  
    }
}
```

---

## 📊 **ASCII Representation**

We can visualize the generation of ugly numbers as a tree-like structure. We start with `1` and branch out by multiplying by `2`, `3`, and `5`. The Min-Heap ensures we always process the smallest number across all branches.

```
                  1
                / | \
               /  |  \
              2   3   5
             /|\ /|\ /|\
            4 6 8 6 9 15 10 15 25
            ...
```
> The Min-Heap effectively performs a breadth-first search on this conceptual tree, always expanding the smallest node. The `seen` set handles duplicates (like `6` from `2*3` and `3*2`).

---

## 📊 **WORKING**

Let's trace the algorithm to find the `7th` ugly number (`n = 7`).

**Initial State:**
*   `minHeap`: `{1}`
*   `seen`: `{1}`

---

**Step 1:** `i = 0`
*   Poll `1` from heap. `uglyNumber` = 1.
*   Generate next: `1*2=2`, `1*3=3`, `1*5=5`.
*   Add `2`, `3`, `5` to `minHeap` and `seen`.
*   **State:**
    *   `minHeap`: `{2, 3, 5}`
    *   `seen`: `{1, 2, 3, 5}`

---

**Step 2:** `i = 1`
*   Poll `2` from heap. `uglyNumber` = 2. (This is the 2nd ugly number)
*   Generate next: `2*2=4`, `2*3=6`, `2*5=10`.
*   Add `4`, `6`, `10` to `minHeap` and `seen`.
*   **State:**
    *   `minHeap`: `{3, 4, 5, 6, 10}`
    *   `seen`: `{1, 2, 3, 5, 4, 6, 10}`

---

**Step 3:** `i = 2`
*   Poll `3` from heap. `uglyNumber` = 3. (3rd ugly number)
*   Generate next: `3*2=6` (already seen), `3*3=9`, `3*5=15`.
*   Add `9`, `15` to `minHeap` and `seen`.
*   **State:**
    *   `minHeap`: `{4, 5, 6, 9, 10, 15}`
    *   `seen`: `{1, 2, 3, 5, 4, 6, 10, 9, 15}`

---

**Step 4:** `i = 3`
*   Poll `4` from heap. `uglyNumber` = 4. (4th ugly number)
*   Generate next: `4*2=8`, `4*3=12`, `4*5=20`.
*   Add `8`, `12`, `20` to `minHeap` and `seen`.
*   **State:**
    *   `minHeap`: `{5, 6, 8, 9, 10, 12, 15, 20}`
    *   ...and so on.

---

This process continues for `n` steps. The table below summarizes the ugly numbers found in order.

| Iteration (i) | Polled `uglyNumber` | Heap State After Polling & Adding |
|---------------|-----------------------|-----------------------------------|
| 0             | 1                     | `{2, 3, 5}`                       |
| 1             | 2                     | `{3, 4, 5, 6, 10}`                |
| 2             | 3                     | `{4, 5, 6, 9, 10, 15}`            |
| 3             | 4                     | `{5, 6, 8, 9, 10, 12, 15, 20}`    |
| 4             | 5                     | `{6, 8, 9, 10, 12, 15, 20, 25}`   |
| 5             | 6                     | `{8, 9, 10, 12, 15, 18, 20, 25, 30}` |
| 6             | 8                     | ...                               |

When `i = 6`, we poll `8`. This is the 7th ugly number (`n=7`). The loop runs one last time, polls `8`, and then terminates. The final value of `uglyNumber` is `8`.

> Wait, the 7th ugly number is 8. My loop condition is `i < n`. So when `i=6`, this is the 7th iteration (0 to 6). The last `poll()` inside the loop will be the correct answer. The 7th ugly number is indeed `8`. So the example is correct. Let's fix the wording slightly.
> After the 6th iteration (`i=5`), the polled number is `6`.
> On the 7th iteration (`i=6`), we poll `8`. This `uglyNumber` is our answer. The loop finishes. The final `uglyNumber` returned is `8`. Perfect.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **`O(n log n)`**
    *   The main loop runs `n` times.
    *   In each iteration, we perform one `poll()` operation and up to three `add()` operations on the Min-Heap.
    *   Both `add` and `poll` on a priority queue take `O(log k)` time, where `k` is the number of elements in the heap.
    *   The size of the heap `k` grows but is related to `n`. At most, it will contain about `2n` elements. So, the complexity of heap operations is `O(log n)`.
    *   Therefore, the total time complexity is `n` iterations multiplied by `O(log n)` work per iteration.

*   **Space Complexity:** **`O(n)`**
    *   We use a `PriorityQueue` (minHeap) and a `HashSet` (seen) to store the ugly number candidates.
    *   In the worst case, both data structures will store a number of elements proportional to `n`.
    *   Thus, the space required is linear with respect to `n`.
    