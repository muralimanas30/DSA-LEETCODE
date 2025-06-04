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

The "Merge Intervals" problem requires you to merge overlapping intervals in a given list of intervals.  Each interval is represented as a pair of numbers, where the first number is the start point and the second number is the end point of the interval. The goal is to return a new list of non-overlapping intervals that cover all the intervals in the input.

For example:

**Input:** `[[1,3],[2,6],[8,10],[15,18]]`
**Output:** `[[1,6],[8,10],[15,18]]`

Explanation: The intervals `[1,3]` and `[2,6]` overlap, so they are merged into `[1,6]`.

**Input:** `[[1,4],[4,5]]`
**Output:** `[[1,5]]`

Explanation: The intervals `[1,4]` and `[4,5]` overlap, so they are merged into `[1,5]`.

## 📊 **Algorithm**
*   Sort the intervals based on their start times using a priority queue.
*   Create an ArrayList to store the merged intervals.
*   Iterate while the priority queue is not empty.
*   Poll the first interval from the priority queue.
*   If the priority queue is not empty, check if the current interval overlaps with the next interval (peeked).
*   If they overlap, merge them and add the merged interval back into the priority queue.
*   If they don't overlap, add the current interval to the ArrayList.
*   If the priority queue is empty, add the current interval to the ArrayList.
*   Finally, convert the ArrayList to an array and return it.

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

## 📊 **ASCII Representation**

This problem doesn't directly lend itself to a clear ASCII representation, as it mainly involves comparing and merging intervals which is more conceptual.

## 📊 **TABLE Representation**

Let's trace the execution with the input `[[1,3],[2,6],[8,10],[15,18]]`.

| Step | PQ (PriorityQueue)                                            | arrs (ArrayList) | first   | second  | Condition (first[1] >= second[0]) | Action                                                                           |
|------|--------------------------------------------------------------|------------------|---------|---------|------------------------------------|-----------------------------------------------------------------------------------|
| 1    | `[[1,3], [2,6], [8,10], [15,18]]` (sorted by start)             | `[]`             |         |         |                                    |                                                                                   |
| 2    | `[[2,6], [8,10], [15,18]]`                                   | `[]`             | `[1,3]` | `[2,6]` | `3 >= 2` (True)                      | poll second, offer `[1,6]`                                                         |
| 3    | `[[1,6], [8,10], [15,18]]`                                   | `[]`             |         |         |                                    |                                                                                   |
| 4    | `[[8,10], [15,18]]`                                        | `[]`             | `[1,6]` | `[8,10]`| `6 >= 8` (False)                     | add `[1,6]` to `arrs`                                                              |
| 5    | `[[15,18]]`                                                 | `[[1,6]]`        |         |         |                                    |                                                                                   |
| 6    | `[[15,18]]`                                                 | `[[1,6]]`        | `[8,10]`| `[15,18]`| `10 >= 15` (False)                    | add `[8,10]` to `arrs`                                                             |
| 7    | `[]`                                                        | `[[1,6], [8,10]]`| `[15,18]`|         |                                    | add `[15,18]` to `arrs`                                                            |
| 8    | `[]`                                                        | `[[1,6], [8,10], [15,18]]`|         |         |                                    |                                                                                   |
| 9    |                                                                 |                 |         |         |                                    | return `[[1,6], [8,10], [15,18]]`                                                  |

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is adding and retrieving elements from the priority queue, which takes **O(log n)** where n is the number of intervals. Since we iterate through all intervals and potentially add/remove them from the queue, the overall time complexity is **O(n log n)**.

*   **Space Complexity:**  The priority queue can potentially hold all intervals in the worst case.  Additionally, the `arrs` ArrayList will, in the worst case (no overlapping intervals), hold all the intervals. Therefore, the space complexity is **O(n)**.
    