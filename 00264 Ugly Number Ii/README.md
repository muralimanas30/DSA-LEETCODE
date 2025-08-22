# 00264 - Ugly Number Ii
    
**Language:** Java  
**Runtime:** 48 ms (Beats 31.35% of users)  
**Memory:** 44.6 MB (Beats 27.47% of users)  

## 📝 LeetCode Problem
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 264 | Ugly Number II | [LeetCode Problem](https://leetcode.com/problems/ugly-number-ii/) |

---

## 🧩 Problem Statement
Write a program to find the `n`-th ugly number.

Ugly numbers are positive numbers whose prime factors only include `2, 3, 5`.

**Note:**

*   `1` is typically treated as an ugly number.
*   `n` does not exceed 1690.

## 💡 Problem Explanation
The problem asks us to find the n-th ugly number in a sequence where ugly numbers are defined as positive numbers whose prime factors are limited to 2, 3, and 5.  The key challenge is to efficiently generate these numbers in ascending order and avoid duplicates.

## 🧪 Examples
**Example:**

```
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
```

---

## 🧭 Algorithm Overview
*   Initialize a min-heap to store potential ugly numbers.
*   Use a hash set to keep track of ugly numbers that have already been added to the heap. This prevents duplicates and optimizes performance.
*   Start with 1 as the first ugly number and add it to the heap and the hash set.
*   Iterate `n` times, each time extracting the smallest ugly number from the heap.
*   For each extracted ugly number, multiply it by 2, 3, and 5 (the allowed prime factors).
*   If the resulting number hasn't been seen before, add it to the heap and the hash set.
*   After `n` iterations, the last extracted ugly number is the `n`-th ugly number.

This approach was chosen because the heap efficiently keeps track of the smallest generated ugly number at each step, ensuring that we obtain the sequence in ascending order. Using a `HashSet` optimizes the performance, drastically reducing the frequency of redundant calculations and preventing duplication.

## 🧱 Variables & Data Structures

| Variable    | Data Type         | Description                                                                                 |
|-------------|-------------------|---------------------------------------------------------------------------------------------|
| `minHeap`   | `PriorityQueue<Long>` | A min-heap used to store potential ugly numbers, ensuring the smallest is always at the top. |
| `seen`      | `HashSet<Long>`     | A set to store ugly numbers already processed, preventing duplicates.                     |
| `factors`   | `long[]`          | An array containing the prime factors allowed (2, 3, and 5).                             |
| `uglyNumber`| `long`            | Variable to store the current ugly number being processed.                                 |
| `n`         | `int`             | The input, representing which ugly number in the sequence to find.                        |
| `next`      | `long` | The candidate number to be added to the heap in the next step.                                 |

## 🔢 Step-by-Step Breakdown

Let's trace the algorithm with `n = 6`.

1.  Initialize `minHeap = [1]`, `seen = {1}`, `factors = [2, 3, 5]`.
2.  Loop 6 times:
    *   i = 0: `uglyNumber = 1`.  Multiply by 2, 3, 5.
        *   2 is new: `minHeap.add(2)`, `seen.add(2)`.
        *   3 is new: `minHeap.add(3)`, `seen.add(3)`.
        *   5 is new: `minHeap.add(5)`, `seen.add(5)`.
        *   `minHeap = [2, 3, 5]`, `seen = {1, 2, 3, 5}`.
    *   i = 1: `uglyNumber = 2`.  Multiply by 2, 3, 5.
        *   4 is new: `minHeap.add(4)`, `seen.add(4)`.
        *   6 is new: `minHeap.add(6)`, `seen.add(6)`.
        *   10 is new: `minHeap.add(10)`, `seen.add(10)`.
        *   `minHeap = [3, 4, 5, 6, 10]`, `seen = {1, 2, 3, 4, 5, 6, 10}`.
    *   i = 2: `uglyNumber = 3`. Multiply by 2, 3, 5.
        *   6 is seen.
        *   9 is new: `minHeap.add(9)`, `seen.add(9)`.
        *   15 is new: `minHeap.add(15)`, `seen.add(15)`.
        * `minHeap = [4, 5, 6, 9, 10, 15]`, `seen = {1, 2, 3, 4, 5, 6, 9, 10, 15}`
    *   i = 3: `uglyNumber = 4`. Multiply by 2, 3, 5.
        *   8 is new: `minHeap.add(8)`, `seen.add(8)`.
        *   12 is new: `minHeap.add(12)`, `seen.add(12)`.
        *   20 is new: `minHeap.add(20)`, `seen.add(20)`.
        * `minHeap = [5, 6, 8, 9, 10, 12, 15, 20]`, `seen = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20}`
    *   i = 4: `uglyNumber = 5`. Multiply by 2, 3, 5.
        *   10 is seen.
        *   15 is seen.
        *   25 is new: `minHeap.add(25)`, `seen.add(25)`.
        * `minHeap = [6, 8, 9, 10, 12, 15, 20, 25]`, `seen = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20, 25}`
    *   i = 5: `uglyNumber = 6`. Multiply by 2, 3, 5.
        *   12 is seen.
        *   18 is new: `minHeap.add(18)`, `seen.add(18)`.
        *   30 is new: `minHeap.add(30)`, `seen.add(30)`.
        * `minHeap = [8, 9, 10, 12, 15, 18, 20, 25, 30]`, `seen = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 25, 30}`
3.  Return 6.

## 🧰 Programming Workflow

1.  Initialize a `PriorityQueue` called `minHeap` to store potential ugly numbers.
2.  Initialize a `HashSet` called `seen` to keep track of seen ugly numbers to avoid duplicates.
3.  Initialize an array `factors` with the values {2, 3, 5}.
4.  Add 1 to both the `minHeap` and the `seen` set.
5.  Iterate `n` times.
6.  In each iteration:
    *   Poll the smallest number from `minHeap` and store it in `uglyNumber`.
    *   Iterate through the `factors` array.
    *   Multiply `uglyNumber` by each `factor` to generate a new potential ugly number (`next`).
    *   If `next` is not in `seen`:
        *   Add `next` to `seen`.
        *   Add `next` to `minHeap`.
7.  Return `uglyNumber`.

```
[Start] --> [Initialize minHeap, seen, factors]
[Initialize minHeap, seen, factors] --> [Add 1 to minHeap and seen]
[Add 1 to minHeap and seen] --> Loop n times: i = 0 to n-1
Loop n times: i = 0 to n-1 --> [Poll minHeap to uglyNumber]
[Poll minHeap to uglyNumber] --> Loop through factors
Loop through factors --> [Calculate next = uglyNumber * factor]
[Calculate next = uglyNumber * factor] --> [Is next in seen?]
[Is next in seen?] -- Yes --> Loop through factors
[Is next in seen?] -- No --> [Add next to seen and minHeap]
[Add next to seen and minHeap] --> Loop through factors
Loop through factors --> Loop n times: i = 0 to n-1
Loop n times: i = 0 to n-1 --> [Return uglyNumber]
[Return uglyNumber] --> [End]
```

## 🔥 Code Implementation
```java
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>(); // Min-heap to store ugly numbers.
        Set<Long> seen = new HashSet<>(); // HashSet to prevent duplicates.
        long[] factors = {2, 3, 5}; // Prime factors.

        minHeap.add(1L); // Add the first ugly number.
        seen.add(1L);

        long uglyNumber = 1L; // Initialize the ugly number.
        for (int i = 0; i < n; i++) {
            uglyNumber = minHeap.poll(); // Get the smallest ugly number.
            for (long factor : factors) {
                long next = uglyNumber * factor; // Multiply by each factor.
                if (seen.add(next)) { // If not seen before
                    minHeap.add(next); // Add to heap.
                }
            }
        }

        return (int) uglyNumber;  // Return the nth ugly number.
    }
}
```

## 🧮 ASCII Representation

N/A

## 🗂️ Table Representation

N/A

## 🧪 Working Demo

Let's trace the algorithm with `n = 5`.

| Iteration | uglyNumber | factor | next | seen                        | minHeap                                  |
| --------- | ---------- | ------ | ---- | --------------------------- | ---------------------------------------- |
| Initial   | N/A        | N/A    | N/A  | {1}                         | [1]                                      |
| 0         | 1          | 2      | 2    | {1, 2}                      | [2]                                      |
|           |            | 3      | 3    | {1, 2, 3}                   | [2, 3]                                   |
|           |            | 5      | 5    | {1, 2, 3, 5}                | [2, 3, 5]                                |
| 1         | 2          | 2      | 4    | {1, 2, 3, 4, 5}             | [3, 4, 5]                                |
|           |            | 3      | 6    | {1, 2, 3, 4, 5, 6}          | [3, 4, 5, 6]                             |
|           |            | 5      | 10   | {1, 2, 3, 4, 5, 6, 10}       | [3, 4, 5, 6, 10]                          |
| 2         | 3          | 2      | 6    | {1, 2, 3, 4, 5, 6, 10}       | [4, 5, 6, 10]                          |
|           |            | 3      | 9    | {1, 2, 3, 4, 5, 6, 9, 10}    | [4, 5, 6, 9, 10]                       |
|           |            | 5      | 15   | {1, 2, 3, 4, 5, 6, 9, 10, 15} | [4, 5, 6, 9, 10, 15]                     |
| 3         | 4          | 2      | 8    | {1, 2, 3, 4, 5, 6, 8, 9, 10, 15} | [5, 6, 8, 9, 10, 15]                   |
|           |            | 3      | 12   | {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15} | [5, 6, 8, 9, 10, 12, 15]                 |
|           |            | 5      | 20   | {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20} | [5, 6, 8, 9, 10, 12, 15, 20]               |
| 4         | 5          | 2      | 10   | {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20} | [6, 8, 9, 10, 12, 15, 20]               |
|           |            | 3      | 15   | {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20} | [6, 8, 9, 10, 12, 15, 20]               |
|           |            | 5      | 25   | {1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 20, 25} | [6, 8, 9, 10, 12, 15, 20, 25]             |

Return 5.

## 🚀 Time & Space Complexity

*   **Time Complexity:** **O(n log n)**, because each of the n ugly numbers requires a heap insertion and deletion (both O(log n)).
*   **Space Complexity:** **O(n)**, because the `minHeap` and `seen` set can grow up to size `n`.

## 🔗 References

*   [PriorityQueue (Java)](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)
*   [HashSet (Java)](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)
    