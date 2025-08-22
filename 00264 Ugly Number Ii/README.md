# 00264 - Ugly Number II

**Language:** Java  
**Runtime:** 48 ms (Beats 31.35% of users)  
**Memory:** 44.6 MB (Beats 27.47% of users)  

---

## 📎 External Link

- [LeetCode Problem 264: Ugly Number II](https://leetcode.com/problems/ugly-number-ii/)

---

## 📝 Problem Statement

Given an integer `n`, return the *n*th ugly number.  
Ugly numbers are positive numbers whose prime factors only include 2, 3, and 5. The sequence starts with 1, and each subsequent ugly number is the smallest number greater than the previous ugly number that can be formed by multiplying previous ugly numbers by 2, 3, or 5.

---

## 📚 Example Inputs & Outputs

| Input | Output | Explanation |
|-------|--------|-------------|
| `n = 10` | `12` | The sequence is `[1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...]`. The 10th ugly number is 12. |
| `n = 1`  | `1`  | The first ugly number is always 1. |
| `n = 7`  | `8`  | The sequence up to the 7th ugly number is `[1, 2, 3, 4, 5, 6, 8]`. |

---

## 🏆 Solution Overview

This solution uses a **min-heap (priority queue)** and a **set** to generate ugly numbers in ascending order and avoid duplicates.

**Why this approach?**
- The min-heap ensures we always process the smallest available ugly number.
- The set prevents duplicate entries in the heap.
- Multiplying each extracted ugly number by 2, 3, and 5 generates new candidates efficiently.

---

## 🧩 Variables & Data Structures

| Name        | Type                      | Purpose                                                      |
|-------------|---------------------------|--------------------------------------------------------------|
| `minHeap`   | `PriorityQueue<Long>`     | Stores ugly numbers in ascending order for extraction.       |
| `seen`      | `Set<Long>`               | Tracks numbers already added to the heap to avoid duplicates.|
| `factors`   | `long[]`                  | Array of allowed prime factors: `[2, 3, 5]`.                 |
| `uglyNumber`| `long`                    | Current ugly number being processed.                         |
| `n`         | `int`                     | The position of the ugly number to find.                     |

---

## 🛠️ Step-by-Step Algorithm

1. **Initialize Data Structures:**
   - Add `1` to both the min-heap and the set.
2. **Iterate `n` Times:**
   - Extract the smallest ugly number from the heap.
   - For each factor (2, 3, 5):
     - Multiply the extracted number by the factor.
     - If the result is not in the set, add it to both the heap and the set.
3. **After `n` iterations, return the last extracted ugly number.**

---

## 🎨 Visual Diagram

### Table Representation of Heap Operations

| Iteration | Extracted Ugly Number | Multiplied By | Next Candidates | Added to Heap |
|-----------|----------------------|---------------|-----------------|---------------|
| 1         | 1                    | 2, 3, 5       | 2, 3, 5         | 2, 3, 5       |
| 2         | 2                    | 2, 3, 5       | 4, 6, 10        | 4, 6, 10      |
| 3         | 3                    | 2, 3, 5       | 6, 9, 15        | 9, 15         |
| 4         | 4                    | 2, 3, 5       | 8, 12, 20       | 8, 12, 20     |
| 5         | 5                    | 2, 3, 5       | 10, 15, 25      | 25            |
| 6         | 6                    | 2, 3, 5       | 12, 18, 30      | 18, 30        |

---

## 🧮 Step-by-Step Walkthrough

**Input:** `n = 7`

1. Start with `1` in the heap and set.
2. Extract `1`, add `2`, `3`, `5`.
3. Extract `2`, add `4`, `6`, `10`.
4. Extract `3`, add `9`, `15`.
5. Extract `4`, add `8`, `12`, `20`.
6. Extract `5`, add `25`.
7. Extract `6`, add `18`, `30`.
8. Extract `8` (7th iteration).

**Result:** The 7th ugly number is `8`.

---

## 💻 Java Code Implementation

```java
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        long[] factors = {2, 3, 5};

        minHeap.add(1L);
        seen.add(1L);

        long uglyNumber = 1L;
        for (int i = 0; i < n; i++) {
            uglyNumber = minHeap.poll();
            for (long factor : factors) {
                long next = uglyNumber * factor;
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

## 🛠️ Programming Workflow

### Logical Flow (Numbered List)

1. Initialize min-heap and set with `1`.
2. Repeat `n` times:
    - Extract smallest ugly number.
    - For each factor (2, 3, 5):
        - Multiply and add to heap/set if not seen.
3. Return the last extracted ugly number.

### Flowchart (ASCII Art)

```
+-------------------------------+
| 1. Add 1 to heap and set      |
+---------------+---------------+
                |
                v
+-------------------------------+
| 2. Repeat n times:            |
|   a. Poll min from heap       |
|   b. For each factor:         |
|      - Multiply               |
|      - If not seen, add       |
+---------------+---------------+
                |
                v
+-------------------------------+
| 3. Return last polled number  |
+-------------------------------+
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** O(n log n)  
  - Each heap operation is O(log n), repeated n times.
- **Space Complexity:** O(n)  
  - Heap and set store up to n ugly numbers.

---

## 📚 References

- [LeetCode Problem 264: Ugly Number II](https://leetcode.com/problems/ugly-number-ii/)
- [PriorityQueue (Java SE Documentation)](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)