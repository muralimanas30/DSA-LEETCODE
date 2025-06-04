# 00056 - Merge Intervals
    
**Language:** Java  
**Runtime:** 10 ms (Beats 22.98% of users)  
**Memory:** 46.5 MB (Beats 71.44% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 56 | MERGE INTERVALS | [LeetCode Problem](https://leetcode.com/problems/merge-intervals/) |

---

## 💡 **Problem Explanation**

The "Merge Intervals" problem requires you to merge overlapping intervals in a given list of intervals.  Each interval is represented as a pair `[start, end]`. The goal is to produce a new list of non-overlapping intervals that cover the same range as the input.

**Example:**

Input: `intervals = [[1,3],[2,6],[8,10],[15,18]]`
Output: `[[1,6],[8,10],[15,18]]`

Explanation: The intervals `[1,3]` and `[2,6]` overlap, so they are merged into `[1,6]`.

Input: `intervals = [[1,4],[4,5]]`
Output: `[[1,5]]`

Explanation: The intervals `[1,4]` and `[4,5]` overlap, so they are merged into `[1,5]`.

---

## 📊 **Algorithm**

Here's a step-by-step algorithm to solve the Merge Intervals problem:

*   **Sort Intervals:** Sort the intervals based on their start times in ascending order. This makes it easier to identify overlapping intervals.

*   **Initialize a Result List:** Create an empty list to store the merged intervals.

*   **Iterate and Merge:**
    *   Start with the first interval.
    *   For each subsequent interval, check if it overlaps with the last interval added to the result list.
        *   If they overlap, merge the current interval with the last interval in the result list by updating the end time of the last interval to the maximum of the end times of both intervals.
        *   If they don't overlap, add the current interval to the result list.

*   **Return the Result:** Return the list of merged intervals.

---

## 🔥 **Code Implementation**

```java
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            pq.offer(interval);
        }
        
        ArrayList<int[]> arrs = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (!pq.isEmpty()) {
                int[] second = pq.peek();
                if (first[1] >= second[0]) {
                    pq.poll();
                    pq.offer(new int[]{first[0], Math.max(first[1], second[1])});
                } else {
                    arrs.add(first);
                }
            } else {
                arrs.add(first);
            }
        }
        
        return arrs.toArray(new int[0][]);
    }
}
```

---

## 📊 **WORKING**

Let's trace the execution with the input `intervals = [[1,3],[2,6],[8,10],[15,18]]`

1.  **Initial Input:** `[[1,3],[2,6],[8,10],[15,18]]`
2.  **PriorityQueue initialization:** `pq = [[1,3],[2,6],[8,10],[15,18]]` (sorted based on the starting value).
3.  **Loop 1:**
    *   `first = [1,3]` is polled.
    *   `second = [2,6]` is peeked.
    *   `first[1] >= second[0]` which translates to `3 >= 2` is true, so intervals are merged.
    *   `pq.poll()` removes `[2,6]` from the queue.
    *   New merged interval `[1,6]` is inserted into the `pq`. `pq = [[1,6],[8,10],[15,18]]`.
4.  **Loop 2:**
    *   `first = [1,6]` is polled.
    *   `second = [8,10]` is peeked.
    *   `first[1] >= second[0]` which translates to `6 >= 8` is false, so intervals are not merged.
    *   `[1,6]` is added to `arrs`, `arrs = [[1,6]]`.
5.  **Loop 3:**
    *   `first = [8,10]` is polled.
    *   `second = [15,18]` is peeked.
    *   `first[1] >= second[0]` which translates to `10 >= 15` is false, so intervals are not merged.
    *   `[8,10]` is added to `arrs`, `arrs = [[1,6],[8,10]]`.
6.  **Loop 4:**
    *   `first = [15,18]` is polled.
    *   `pq` is empty. `else` block is hit, so `[15,18]` is added to `arrs`, `arrs = [[1,6],[8,10],[15,18]]`.
7.  The ArrayList `arrs` is converted to an array `[[1,6],[8,10],[15,18]]` and returned.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is sorting the intervals which takes **O(n log n)** time, where n is the number of intervals. The rest of the operations (PriorityQueue and ArrayList manipulations) take O(n) time. Therefore, the overall time complexity is **O(n log n)**.
*   **Space Complexity:** The space complexity is **O(n)** because we are using a PriorityQueue and an ArrayList which can hold up to `n` intervals in the worst case. In the case where no merging happens, the ArrayList stores all intervals.
    