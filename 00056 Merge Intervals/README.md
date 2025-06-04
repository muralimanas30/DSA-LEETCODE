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

The Merge Intervals problem requires you to merge overlapping intervals in a given list of intervals. Each interval is represented as a pair of numbers, where the first number is the start and the second is the end of the interval.

For example, given the input `[[1,3],[2,6],[8,10],[15,18]]`, the overlapping intervals `[1,3]` and `[2,6]` should be merged into `[1,6]`. The final output should be `[[1,6],[8,10],[15,18]]`.

Another example: Input `[[1,4],[4,5]]` should output `[[1,5]]`

## 📊 **Algorithm**
*   Sort the intervals based on their start times using a priority queue.
*   Iterate through the sorted intervals.
*   If the current interval overlaps with the next interval (i.e., `first[1] >= second[0]`), merge them by creating a new interval with the start time of the first interval and the maximum end time of both intervals. Add this merged interval back to the priority queue.
*   If the current interval does not overlap with the next interval, add the current interval to the result list.
*   After processing all intervals, convert the result list to an array.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.PriorityQueue;

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

## 📊 **WORKING**

Let's trace the execution with the input `[[1,3],[2,6],[8,10],[15,18]]`.

1.  **Initialization**:

    *   `intervals = [[1,3],[2,6],[8,10],[15,18]]`
    *   `pq` (PriorityQueue) is created and populated with the intervals. It automatically sorts intervals based on the starting point
2.  **Loop execution**:
    *   `first = [1, 3]` is polled from `pq`. `pq` now contains `[[2,6],[8,10],[15,18]]`
    *   `second = [2, 6]` is peeked from `pq`.
    *   Since `first[1] (3) >= second[0] (2)`, the intervals overlap.
    *   `second` is polled from `pq`. `pq` now contains `[[8,10],[15,18]]`
    *   A merged interval `[1, Math.max(3, 6)] = [1, 6]` is created and offered to `pq`. `pq` now contains `[[1,6],[8,10],[15,18]]`
    *   `first = [1,6]` is polled from `pq`. `pq` now contains `[[8,10],[15,18]]`
    *   `second = [8, 10]` is peeked from `pq`.
    *   Since `first[1] (6) < second[0] (8)`, the intervals do not overlap.
    *   `[1,6]` is added to `arrs`. `arrs` now contains `[[1, 6]]`
    *   `first = [8,10]` is polled from `pq`. `pq` now contains `[[15,18]]`
    *   `second = [15, 18]` is peeked from `pq`.
    *   Since `first[1] (10) < second[0] (15)`, the intervals do not overlap.
    *   `[8,10]` is added to `arrs`. `arrs` now contains `[[1, 6], [8, 10]]`
    *   `first = [15,18]` is polled from `pq`. `pq` is now empty.
    *   Since `pq` is empty, `[15,18]` is added to `arrs`. `arrs` now contains `[[1, 6], [8, 10], [15,18]]`
3.  **Final Result**:
    *   `arrs` is converted to `[[1, 6], [8, 10], [15, 18]]` and returned.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**:  The time complexity is **O(n log n)**, where n is the number of intervals. This is primarily due to the priority queue's sorting of the intervals. Adding each element to the queue requires O(log n) time, and we do this for n elements.

*   **Space Complexity**: The space complexity is **O(n)**, where n is the number of intervals. In the worst-case scenario, where no intervals overlap, the priority queue and the array list will store all the intervals.
    